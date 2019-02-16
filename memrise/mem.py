import os
import sys
import inspect
import re
import argparse
import json
import time
import getpass
from splinter import Browser
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options  
def loadBrowser(url):
    """ Loads the browser according to OS, visits website, and then logs in. """
    data = loadUser()
    if sys.platform == "win32":
        chrome_options = Options()  
        chrome_options.add_argument("--headless") 
        chrome_options.add_argument("--silent") 
        browser = Browser('chrome', options=chrome_options)
    elif "linux" in sys.platform:
        executable_path = {'executable_path':'geckodriver'}
        currentdir = os.path.dirname(os.path.abspath(inspect.getfile(inspect.currentframe())))
        parentdir = os.path.dirname(currentdir)
        sys.path.insert(0, parentdir)
        # from selenium import webdriver
        #browser = webdriver.Firefox(executable_path = '/Users/zhaosong/Documents/WorkSpace/tool/geckodriver')
        #browser.get('https://www.google.com') 
        #sys.path.insert(0, os.path.abspath("..") )
        #browser = Browser('chrome', **executable_path)
        browser = Browser(**executable_path)
    browser.visit(data["course_url"] + url)
    cookies = browser.find_by_css('a[class="cc-btn cc-allow"]') # Accept cookies button
    if cookies:
        cookies.first.click()    
    browser.fill("username", data["username"])
    browser.fill("password", data["password"])    
    return browser


def isPoints(browser):
    """ Returns whether the page contains the earned points. """
    pts = browser.find_by_css('span[class="pts"]')
    if pts:
        return pts[3].text
    return None
def isMultipleChoice(browser):
    """ Returns whether or not you are being given a multiple choice problem. """
    return browser.is_element_present_by_css('span[class="val  "]')
def isTyping(browser):
    """ Returns whether or not you are being asked to type a translation. """
    return browser.is_element_present_by_css('input[class="roundy shiny-box typing-type-here  "]')


def answerMultipleChoice(browser, data, question):
    """ Solves the multiple choice problem. """
    for val in browser.find_by_css('span[class="val  "]'):#Checks possible answers
        if (question in data and val.text == data[question]) or (val.text in data and data[val.text] == question): # Hopefully deals with definition repetion creating json problems
            try:
                val.click()
            except:
                val.click()
            break                                

def typeTranslation(browser, answer):
    """ Types the translation. """
    browser.find_by_css('input[class="roundy shiny-box typing-type-here  "]').first.fill(answer + "\n")
def scrambleTranslate(browser, answer):
    """ Answers the scrambled translate problems. """
    #Need to deal with word repitition.
    sentence = re.findall(r"[\w']+|[.,!?;]", answer, re.UNICODE)
    wordBox = browser.find_by_css('div[class="word-box word-box-choice  "]').first
    for word in sentence:
        for wordButton in wordBox.find_by_css('div[class="word btn"]'):
            if wordButton["data-word"] == word:
                wordButton.click()
                time.sleep(.05)
                break    


def learn(lessonNumber=""):
    """ Automates the learning on memrise. """
    data = loadData()
    url = "garden/learn/"
    if lessonNumber:
        url =  str(lessonNumber) + '/garden/learn/'
    browser = loadBrowser(url)
    time.sleep(2)
    lastQuestion = "?"
    looping = True
    loops = 0
    #raw_input(1)
    while looping:
        question = browser.find_by_css('div[class="qquestion qtext  "]') #Read question 
        loops += 1
        if question and question.first.text != lastQuestion: # Check if still on old question
            question = question.first.text
            loops = 0
            lastQuestion = question
            if isMultipleChoice(browser):
                answerMultipleChoice(browser, data, question)   
            elif isTyping(browser):
                typeTranslation(browser, data[question])
            else:
                scrambleTranslate(browser, data[question])
            time.sleep(.5)
            
        else:#Definition or endpage
            if browser.is_element_present_by_css('span[class="next-icon"]'):
                browser.find_by_css('span[class="next-icon"]').first.click()
            else:
                pts = isPoints(browser)
                if pts:
                    browser.quit()
                    return int("".join([i for i in pts if i in "1234567890"]))           
            time.sleep(.5)
def review():
    """ Automates the review on memrise. """
    data = loadData()
    browser = loadBrowser("garden/classic_review/")
    time.sleep(2)
    lastQuestion = "?"
    looping = True
    loops = 0
    while looping:
        question = browser.find_by_css('div[class="qquestion qtext  "]') #Read question
        loops += 1
        if question and question.first.text != lastQuestion: # Check if still on old question
            question = question.first.text
            loops = 0
            lastQuestion = question
            if isMultipleChoice(browser):
                answerMultipleChoice(browser, data, question)                               
            elif isTyping(browser):
                typeTranslation(browser, data[question])
            time.sleep(.5)
        else:
            pts = isPoints(browser)
            if pts:
                browser.quit()
                return int("".join([i for i in pts if i in "1234567890"]))  
def speed_review():
    """ Automates the speed review on memrise. """
    data = loadData()
    browser = loadBrowser("garden/speed_review/")
    time.sleep(4)
    lastQuestion = "?"
    looping = True
    loops = 0
    while looping:
        question = browser.find_by_css('div[class="qquestion qtext  "]').first.text #Read question
        loops += 1
        if question != lastQuestion: # Check if still on old question
            loops = 0
            answerMultipleChoice(browser, data, question)
            lastQuestion = question      
            #time.sleep(.5)
            time.sleep(.1)
        elif loops > 6:
            try:
                active_web_element = browser.driver.switch_to_active_element()  
                active_web_element.send_keys(Keys.PAGE_DOWN)
                active_web_element.send_keys(Keys.ENTER) # Presses enter to continue to next page
            except:
                pass            
            pts = isPoints(browser)
            if pts:
                browser.quit()
                return int("".join([i for i in pts if i in "1234567890"]))      

def loadUser(change=False, view=False):
    """ Returns login information if saved, if not, asks for it and saves it. """
    data = None
    try:
        if change:
            raise EnvironmentError
        with open("user.json", "r") as fp:
            data = json.load(fp)
    except:
        if view:
            return None
        data = {
            "course_url":raw_input("course url, ex: https://www.memrise.com/course/1992222/descubre-2-5/ -->"),
            "username":raw_input("username-->"),
            "password":getpass.getpass("password-->")+"\n"
        }
        if data["course_url"][-1] != "/":
            data["course_url"] += "/"
        with open('user.json', 'w') as fp:
            json.dump(data, fp, indent=4)        
    return data
def loadData():
    """ Loads the memrise dictionary. """
    with open('data.json', 'r') as fp:
        return json.load(fp)    


def main():
    """ Handles CLI. """
    parser = argparse.ArgumentParser(epilog="""
    Times and Unit are optional.
    Unit must be used with the learn command.
    You can not combine learn/review/speed.
    """)
    parser.add_argument('-l', "--learn", help="Learn.", action="store_true")
    parser.add_argument('-r', "--review", help="Review.", action="store_true")
    parser.add_argument('-s', "--speed", help="Speed Review.", action="store_true")
    parser.add_argument('-cp', "--change_profile", help="Change profile data.", action="store_true")
    parser.add_argument('-p', "--profile", help="View profile data.", action="store_true")
    parser.add_argument('-t', "--times", help="Repeats the action times times.", type=int, default=1)
    parser.add_argument('-u', "--unit", help="Perform the action for this unit.", type=int)
    args = parser.parse_args()
    if not sum([args.learn, args.review, args.speed, args.change_profile, args.profile]) == 1:
        parser.print_help()
    else:
        if args.learn or args.review or args.speed:
            for i in xrange(1, args.times + 1):
                print "Round ", i
                start = time.time()
                if args.learn:
                    if args.unit:
                        learn(lessonNumber=str(args.unit))
                    else:
                        learn()
                elif args.review:
                    review()
                elif args.speed:
                    print speed_review(), "Points Gained."
                print time.time() - start, " seconds."
        elif args.change_profile:
            loadUser(change=True)
        elif args.profile: 
            u = loadUser(view=True)
            if u:
                for key in u:
                    print key, " : ", u[key]
            else:
                print "No profile saved."
if __name__ == "__main__":
    main()
