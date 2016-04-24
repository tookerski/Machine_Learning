#coding=gbk
'''
Created on 2016年4月24日

@author: Administrator
'''
import requests

session = requests.Session()

params = {'username':'rabbit','password':'password'}
r = session.post("http://pythonscraping.com/pages/cookies/welcome.php",params)
print("Cookie is set to")
print(r.cookies.get_dict())
print("-----------")
print("Going to profile page...")
r = session.get("http://pythonscraping.com/pages/cookies/profile.php",cookies = r.cookies)
print(r.text)