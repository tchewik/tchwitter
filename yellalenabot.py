# -*- coding: utf-8 -*-
 
import tweepy, time, sys, random, mykeys, re
 
#argfile = str(sys.argv[1])
whatReplace = str(sys.argv[1])
replaceWith = str(sys.argv[2])
timer = int(str(sys.argv[3]))
times = int(str(sys.argv[4]))
 
#enter the corresponding information from your Twitter application:
CONSUMER_KEY = mykeys.CONSUMER_KEY
CONSUMER_SECRET = mykeys.CONSUMER_SECRET
ACCESS_KEY = mykeys.ACCESS_KEY
ACCESS_SECRET = mykeys.ACCESS_SECRET
auth = tweepy.OAuthHandler(CONSUMER_KEY, CONSUMER_SECRET)
auth.set_access_token(ACCESS_KEY, ACCESS_SECRET)
api = tweepy.API(auth)
 
toReplace = re.compile(re.escape(whatReplace), re.IGNORECASE)
reply = re.compile('@[A-z]*')
hashtag = re.compile('#[A-z]*')
link = re.compile('https://[a-z, 0-9, ./]*')
 
tweet = tweepy.Cursor(api.search, q=whatReplace).items(times)
 
for tw in tweet:
    txt=''
    tweetText = toReplace.sub(replaceWith, tw.text)
    for word in tweetText.split():
        exclusions = (reply.match(word) or word=='RT' or hashtag.match(word) or link.match(word))
        if not exclusions:
            txt+=(word+' ')
 
    api.update_status(txt.lower()) 
    time.sleep(timer)