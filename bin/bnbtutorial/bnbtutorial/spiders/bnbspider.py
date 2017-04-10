# -*- coding: utf-8 -*-
import scrapy


class BnbspiderSpider(scrapy.Spider):
    name = "bnbspider"

    #allowed_domains = ["airbnb.com"]

    """
    def __init__(self, domain='', *args, **kwargs):
        super(BnbspiderSpider, self).__init__(*args, **kwargs)
        #self.start_urls = [domain]
    """

    def start_requests(self):
        #to_scrape = self.website,
        #print(to_scrape),
        url = self.website
        yield scrapy.Request(url=url, callback=self.parse)

    def parse(self, response):
        #for quote in response.css('div.txt1'):
            yield {

                'text': response.css('p::text').extract(),

                #'text': quote.css('p::text').extract(),

                #'author': quote.css('small.author::text').extract_first(),
                #'tags': quote.css('div.tags a.tag::text').extract(),
            }
