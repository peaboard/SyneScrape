#!/usr/bin/env python

import sys
reload(sys)
sys.setdefaultencoding('utf-8')

import json

para = [""]
lines = [" "]
l = ""

text_file = open('website_scrape.txt', 'w')

with open('scrapedWebsite.json') as json_file:
    data = json.load(json_file)


    for p in data:
        print p['text']
        para += p['text']
        print("\n")

# Consider appending new line after fullstop
# instead of deleting the fullstop.

    for n in para:
        print ("n: %s" %n)
#	print("\n\n\n\n")
#       n = n.replace(".","\n")
    	l += n
#    	print l
#        print("Bello")
#         if (l.find(".") > 0):
# #            l.replace(".","\n")
#             text_file.write("%s" % l)
#             print("Hello")
#             l = ""
    l = l.replace(".","\n")
    print("YOLO")
    print(l)
    text_file.write(l)


    """
    for n in para:
        print n
        for m in n:
            print m
            print ("\n")
            if (m == '.'):
                print("Inside Loop")
                m = 'X'
        print n
        print("\n")
    """

    """
    for p in para:
        if p == ',':
            p = " "
    """



    """
    for p in data:
        print data['text']
        print("\n")
    """


    """
    for p in data['text']:
        #print('Name: ' + p['name'])
        #print('Website: ' + p['website'])
        #print('From: ' + p['from'])
        print('Actual Output' + p['text'])
        print('')
    """
