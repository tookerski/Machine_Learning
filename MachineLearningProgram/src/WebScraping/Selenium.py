#coding=gbk
'''
Created on 2016年4月25日

@author: Administrator
'''
from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By

driver = webdriver.PhantomJS(executable_path='F:/DevelopTools/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/bin/phantomjs')
driver.get("http://pythonscraping.com/pages/javascript/ajaxDemo.html")
try:
    element = WebDriverWait(driver,10).until(EC.presence_of_element_located(By.ID,"loadedButton"))

finally:
    print(driver.find_element_by_id("content").text)
    driver.close()
