
'''
Created on 2016��4��25��

@author: Administrator
'''
from selenium import webdriver
import time
import subprocess
from urllib.request import urlretrieve

driver = webdriver.PhantomJS(executable_path='F:/DevelopTools/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/bin/phantomjs')
driver = webdriver.Firefox()
driver.get("http://www.amazon.com/War-Peace-Leo-Nikolayevich-Tolstoy/dp/1427030200")
time.sleep(2)

driver.find_element_by_id("sitbLogoImg").click()
imageList = set()

time.sleep(5)

while "pointer" in driver.find_element_by_id("sitbReaderRightPageTurner").get_attribute("style"):
    driver.find_element_by_id("sitbReaderRightPageTurner").click()
    time.sleep(2)
    pages = driver.find_elements_by_xpath("//div[@class='pageImage']/div/img")
    for page in pages:
        image = page.get_attribute("src")
        imageList.add(image)

driver.quit()

for image in sorted(imageList):
    urlretrieve(image,"page.jpg")
    p=subprocess.Popen(["tesseract","page.jpg","page"],stdout=subprocess.PIPE,stderr=subprocess.PIPE)
    p.wait()
    f=open("page.txt","r")
    print(f.read())
