#coding=gbk
'''
Created on 2016年4月23日

@author: Administrator
'''
import requests

# params = {'firstname':'liu','lastname':'daiyuan'}
# r = requests.post("http://pythonscraping.com/files/processing.php",data=params)
params = {'email_addr':'fdliudaiyuan1986@hotmail.com'}
r = requests.post("http://post.oreilly.com/client/o/oreilly/forms/quicksignup.cgi", data=params)
print(r.text)