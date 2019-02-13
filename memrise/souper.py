import requests
from bs4 import BeautifulSoup
import json
def main():
    """ Scrapes the memrise pages for words and their definitions. """
    masterDict = {}
    for i in [1] + range(3, 37):
        r = requests.get("https://www.memrise.com/course/1992222/descubre-2-5/" + str(i) + "/")
        data = r.text
        soup = BeautifulSoup(data, features="html5lib")
        for row in soup.findAll("div", {"class": "thing text-text"}):
            texts = row.findAll("div", {"class": "text"})
            masterDict[texts[0].text] = texts[2].text
            masterDict[texts[2].text] = texts[0].text       
    with open('data.json', 'w') as fp:
        json.dump(masterDict, fp, indent=4)
if __name__ == "__main__":
    main()