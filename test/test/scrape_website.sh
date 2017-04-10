#!/bin/bash

echo Printing Folder Name
pwd
script_dir = $(dirname $0)
echo $script_dir
#cd ..
pwd
cd test/SCPY/bin
pwd
source activate
cd ../.. 
pwd
cd bnbtutorial
pwd
rm scrapedWebsite.json
echo Earlier File Removed Successfully
#scrapy crawl bnbspider -o website_scrape.json
#scrapy crawl bnbspider -a website='http://www.mitid.edu.in/institute.html' -o scrapedWebsite.json
echo scraping $1
scrapy crawl bnbspider -a website=$1 -o scrapedWebsite.json
python json_parser.py
cd ..
pwd
cp bnbtutorial/website_scrape.txt test/inputBaseline
echo File Copied Successfully
echo Done
