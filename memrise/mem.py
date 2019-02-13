import os
from splinter import Browser
from selenium.webdriver.common.keys import Keys
import selenium
import time
import json
def main():
    with open('data.json', 'r') as fp:
        data = json.load(fp)
    print "Translations loaded."
    password = raw_input("password-->")+"\n" # newline equivalent to typing enter
    browser = Browser('chrome')
    browser.visit('https://www.memrise.com/course/1992222/descubre-2-5/garden/speed_review/')
    browser.fill("username", "iwilliams10@stuy.edu")
    browser.fill("password", password)
    time.sleep(4)
    lastQuestion = "?"
    looping = True
    loops = 0
    while looping:
        question = browser.find_by_css('div[class="qquestion qtext  "]').first.text #Read question
        if question != lastQuestion: # Check if still on old question
            loops = 0
            for val in browser.find_by_css('span[class="val  "]'):#Checks possible answers
                if ( question in data and val.text == data[question]) or (val.text in data and data[val.text] == question): # Hopefully deals with definition repetion creating json problems
                    val.click()
                    break
            lastQuestion = question      
        loops += 1
        time.sleep(.5)
        if loops > 6:
            try:
                active_web_element = browser.driver.switch_to_active_element()  
                active_web_element.send_keys(Keys.PAGE_DOWN)
                active_web_element.send_keys(Keys.ENTER) # Presses enter to continue to next page
            except:
                pass
            print browser.find_by_css('span[class="pts"]')[3].txt
            looping = False
            break
    
        
    browser.quit()        
if __name__ == "__main__":
    main()