#coding=gbk
'''
Created on 2016年4月24日

@author: Administrator
'''
import requests
from requests.auth import HTTPBasicAuth
#from requests.auth import AuthBase

auth = HTTPBasicAuth('Rabbit','password')
r = requests.post("http://pythonscraping.com/pages/auth/login.php",auth=auth)
print(r.text)