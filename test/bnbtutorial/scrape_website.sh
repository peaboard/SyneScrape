#!/bin/sh

cd ~/SCPY/bin
pwd
source activate
pwd
cd ~/Software\ Snippets/Scrapy/Airbnb/bnbtutorial
pwd
rm scrapedWebsite.json
echo Earlier File Removed Successfully
#scrapy crawl bnbspider -o website_scrape.json
#scrapy crawl bnbspider -a website='http://www.mitid.edu.in/institute.html' -o scrapedWebsite.json
echo scraping $1
scrapy crawl bnbspider -a website=$1 -o scrapedWebsite.json
python json_parser.py
cp website_scrape.txt ~/Open_Source/Synesketch/test/test/inputBaseline
echo File Copied Successfully
echo Done
