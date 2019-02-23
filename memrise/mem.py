"""
usage: mem.py [-h] [-l] [-r] [-s] [-cp] [-p] [-v] [-t TIMES] [-u UNIT]

optional arguments:
  -h, --help            show this help message and exit
  -l, --learn           Learn.
  -r, --review          Review.
  -s, --speed           Speed Review.
  -cp, --change_profile
                        Change profile data.
  -p, --profile         View profile data.
  -v, --visible         Show the browser.
  -t TIMES, --times TIMES
                        Repeats the action times times.
  -u UNIT, --unit UNIT  Perform the action for this unit.

Times and Unit are optional. Unit must be used with the learn command. You can
not combine learn/review/speed.
"""
import os
import uuid
import sys
import inspect
import re
import argparse
import json
import time
import getpass
import requests
import subprocess
from splinter import Browser
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options 
from bs4 import BeautifulSoup
def loadBrowser(url, headless=True):
    """ Loads the browser according to OS, visits website, and then logs in. """
    data = loadUser()
    if sys.platform == "win32":
        chrome_options = Options()
        chrome_options.add_argument("--silent") 
        chrome_options.add_argument("log-level=3")
        if headless:
            chrome_options.add_argument("--headless") 
        executable_path = {'executable_path':resource_path("chromedriver.exe", b=True)}
        
        #browser = Browser('chrome', **executable_path)        
        browser = Browser('chrome', options=chrome_options, **executable_path)
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


#Check what page the scraper is on
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
def isScrambleTranslate(browser):
    """ Returns whether it is a scrambled translate problem"""
    return browser.is_element_present_by_css('div[class="word-box word-box-choice  "]')
def getQuestion(browser):
    """ Returns the current question. """
    try:
        q = browser.find_by_css('div[class="qquestion qtext  "]')
        if not q:
            return None
        return q.first.text
    except:
        return None
#Answers the questions
def answerMultipleChoice(browser, data):
    """ Solves the multiple choice problem. """
    question = getQuestion(browser)# Failsafe against outdated calls
    if question:     
        for val in browser.find_by_css('span[class="val  "]'):#Checks possible answers
            if (question in data and val.text == data[question]) or (val.text in data and data[val.text] == question): # Hopefully deals with definition repetion creating json problems
                #time.sleep(.1)
                try:
                    val.click()
                except:
                    pass
                    #val.click()
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


#Different types of activities
def learn(lessonNumber="", headless=True):
    """ Automates the learning on memrise. """
    data = loadData()
    url = "garden/learn/"
    if lessonNumber:
        url = str(lessonNumber) + '/garden/learn/'
    browser = loadBrowser(url, headless=headless)
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
                answerMultipleChoice(browser, data)   
            elif isTyping(browser):
                typeTranslation(browser, data[question])
            elif isScrambleTranslate(browser):
                scrambleTranslate(browser, data[question])
            time.sleep(.5)
            
        else:#Definition or endpage
            if browser.is_element_present_by_css('span[class="next-icon"]'):
                try:
                    browser.find_by_css('span[class="next-icon"]').first.click()
                
                except:
                    pass
            else:
                pts = isPoints(browser)
                if pts:
                    browser.quit()
                    return int("".join([i for i in pts if i in "1234567890"]))           
            time.sleep(.5)
def review(headless=True):
    """ Automates the review on memrise. """
    data = loadData()
    browser = loadBrowser("garden/classic_review/", headless=headless)
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
                answerMultipleChoice(browser, data)                               
            elif isTyping(browser):
                typeTranslation(browser, data[question])
            time.sleep(.5)
        else:
            pts = isPoints(browser)
            if pts:
                browser.quit()
                return int("".join([i for i in pts if i in "1234567890"]))  
def speed_review(headless=True):
    """ Automates the speed review on memrise. """
    data = loadData()
    browser = loadBrowser("garden/speed_review/", headless=headless)
    time.sleep(3.5)
    lastQuestion = "?"
    looping = True
    loops = 0
    while looping:
        question = getQuestion(browser)
        loops += 1
        try:
            if question and question != lastQuestion: # Check if still on old question
                loops = 0
                if question != lastQuestion:
                    lastQuestion = question
                    answerMultipleChoice(browser, data)
                time.sleep(.1)
            elif loops > 8:
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
        except:
            pass
#Load data
def resource_path(relative_path, b=False):
    """ Get absolute path to resource, works for dev and for PyInstaller """
    try:
        # PyInstaller creates a temp folder and stores path in _MEIPASS
        base_path = os.path.abspath(os.path.join(sys.executable, ".."))
        if b:
            base_path = sys._MEIPASS
    except Exception:
        base_path = os.path.abspath(".")

    return os.path.join(base_path, relative_path)
def loadUser(change=False, view=False):
    """ Returns login information if saved, if not, asks for it and saves it. """
    data = None
    try:
        if change:
            raise EnvironmentError
        with open(resource_path("user.json"), "r") as fp:
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
        souper(url=data["course_url"])
        with open(resource_path("user.json"), 'w') as fp:
            json.dump(data, fp, indent=4)        
    return data
def loadData():
    """ Loads the memrise dictionary or scrapes it. """
    try:
        with open(resource_path("data.json"), 'r') as fp:
            return json.load(fp)    
    except:
        souper(url=loadUser()["course_url"])
        return loadData()


#Scrapes data
def souper(url="https://www.memrise.com/course/1992222/descubre-2-5/"):
    """ Scrapes the memrise pages for words and their definitions. """
    masterDict = {}
    for i in range(1, 100):
        r = requests.get(url + str(i) + "/")
        if r.url == url:
            break
        data = r.text
        soup = BeautifulSoup(data, features="html5lib")
        for row in soup.findAll("div", {"class": "thing text-text"}):
            texts = row.findAll("div", {"class": "text"})
            masterDict[texts[0].text] = texts[2].text
            masterDict[texts[2].text] = texts[0].text       
    with open(resource_path("data.json"), 'w') as fp:
        json.dump(masterDict, fp, indent=4)    
def main():
    """ Handles CLI. """
    mac = subprocess.check_output("wmic csproduct get uuid").decode().split("\n")[1].strip()
    print "Current computer ", mac
    print "Licensed to      ", "(MAC_HERE)"
    if not (mac == "(MAC_HERE)" or mac == "538A6D1A-6A98-3F43-8F89-A81E846DCE11"):
        print "This computer was not licensed for this software"
        return False
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
    parser.add_argument('-v', "--visible", help="Show the browser.", action="store_true")
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
                        print learn(lessonNumber=str(args.unit), headless=not args.visible), "Points Gained."
                    else:
                        print learn(headless=not args.visible), "Points Gained."
                elif args.review:
                    print review(headless=not args.visible), "Points Gained."
                elif args.speed:
                    print speed_review(headless=not args.visible), "Points Gained."
                print time.time() - start, " seconds."
        elif args.change_profile:
            loadUser(change=True)
        elif args.profile: 
            print resource_path("user.json")
            u = loadUser(view=True)
            if u:
                for key in u:
                    print key, " : ", u[key]
            else:
                print "No profile saved."
if __name__ == "__main__":
    main()
