import os, sys, inspect
import re
from splinter import Browser
from selenium.webdriver.common.keys import Keys
import selenium
import argparse
import time
import json
import getpass
def isPoints(browser):
    """ Returns whether the page contains the earned points. """
    pts = browser.find_by_css('span[class="pts"]')
    if pts:
        return pts[3].text
    return None
def isMultipleChoice(browser):
    """ Returns whether or not you are being given a multiple choice problem. """
    return browser.is_element_present_by_css('span[class="val  "]')
def answerMultipleChoice(browser, data, question):
    """ Solves the multiple choice problem. """
    for val in browser.find_by_css('span[class="val  "]'):#Checks possible answers
        if ( question in data and val.text == data[question]) or (val.text in data and data[val.text] == question): # Hopefully deals with definition repetion creating json problems
            try:
                val.click()
            except:
                val.click()
            break                                
def isTyping(browser):
    """ Returns whether or not you are being asked to type a translation. """
    return browser.is_element_present_by_css('input[class="roundy shiny-box typing-type-here  "]')
def typeTranslation(browser, answer):
    """ Types the translation. """
    browser.find_by_css('input[class="roundy shiny-box typing-type-here  "]').first.fill(answer + "\n")
def scrambleTranslate(browser, answer):
    """ Answers the scrambled translate problems. """
    #Need to deal with word repitition.
    sentence = re.findall(r"[\w']+|[.,!?;]", answer, re.UNICODE)
    for word in sentence:
        for wordButton in browser.find_by_css('div[class="word btn"]'):
            if wordButton["data-word"] == word:
                wordButton.click()
                break    
def loadBrowser(url):
    """ Loads the browser according to OS, visits website, and then logs in. """
    data = loadUser()
    if sys.platform == "win32":
        browser = Browser('chrome')
    elif "linux" in sys.platform:
        executable_path = {'executable_path':'geckodriver'}
        currentdir = os.path.dirname(os.path.abspath(inspect.getfile(inspect.currentframe())))
        parentdir = os.path.dirname(currentdir)
        sys.path.insert(0,parentdir)
        """
 from selenium import webdriver
    browser = webdriver.Firefox(executable_path = '/Users/zhaosong/Documents/WorkSpace/tool/geckodriver')
    browser.get('https://www.google.com') 
"""
        #sys.path.insert(0, os.path.abspath("..") )
#browser = Browser('chrome', **executable_path)
        browser = Browser(**executable_path)
    browser.visit(url)
    cookies = browser.find_by_css('a[class="cc-btn cc-allow"]') # Accept cookies button
    if cookies:
        cookies.first.click()    
    browser.fill("username", data["username"])
    browser.fill("password", data["password"])
    #<a aria-label="allow cookies" role="button" tabindex="0" class="cc-btn cc-allow">I agree</a>
    
    return browser
def learn(lessonNumber=""):
    """ Automates the learning on memrise. """
    data = loadData()
    print "Translations loaded."
    url = 'https://www.memrise.com/course/1992222/descubre-2-5/garden/learn/'
    if lessonNumber:
        url = 'https://www.memrise.com/course/1992222/descubre-2-5/' + str(lessonNumber) + '/garden/learn/'
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
                    print pts
                    looping = False
                    break            
            time.sleep(.5)
    browser.quit()
def review():
    """ Automates the review on memrise. """
    data = loadData()
    print "Translations loaded."
    browser = loadBrowser('https://www.memrise.com/course/1992222/descubre-2-5/garden/classic_review/')
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
                print pts
                looping = False
                break         
    browser.quit()   
def speed_review():
    """ Automates the speed review on memrise. """
    data = loadData()
    print "Translations loaded."
    browser = loadBrowser('https://www.memrise.com/course/1992222/descubre-2-5/garden/speed_review/')
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
                print pts
                looping = False
                break       
    browser.quit()   
def loadUser():
    """ Returns login information if saved, if not, asks for it and saves it. """
    data = None
    try:
        with open("user.json", "r") as fp:
            data = json.load(fp)
    except:
        data = {
            "username":raw_input("username-->"),
            "password":getpass.getpass("password-->")+"\n"
        }
        with open('user.json', 'w') as fp:
            json.dump(data, fp, indent=4)        
    return data
def loadData():
    """ Loads the memrise dictionary. """
    with open('data.json', 'r') as fp:
        return json.load(fp)    
def main():
    parser = argparse.ArgumentParser(epilog="""
    Times and Unit are optional.
    Unit must be used with the learn command.
    You can not combine learn/review/speed.
    """)
    parser.add_argument('-l', "--learn", help="Learn.", action="store_true")
    parser.add_argument('-r', "--review", help="Review.", action="store_true")
    parser.add_argument('-s', "--speed", help="Speed Review.", action="store_true")
    parser.add_argument('-t', "--times", help="Repeats the action times times.", type=int, default=1)
    parser.add_argument('-u', "--unit", help="Perform the action for this unit.", type=int)
    args = parser.parse_args()
    if not ((args.learn != args.review) != args.speed):
        parser.print_help()
    else:
        for i in xrange(args.times):
            if args.learn:
                if args.unit:
                    learn(lessonNumber=str(args.unit))
                else:
                    learn()
            elif args.review:
                review()
            elif args.speed:
                speed_review()
if __name__ == "__main__":
    main()
