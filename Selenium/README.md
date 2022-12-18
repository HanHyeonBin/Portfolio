# Selenium 웹 자동화 테스트

## 가상환경 셋팅

- webdriver(chrome) selenium 폴더 안에 세팅
- venv 를 활용한 파이썬 셋팅
- pip3 install selenium==3.141 => selenium 설치 코드

'''python
from selenium import webdriver

from selenium.webdriver.common.keys import Keys

driver = webdriver.Chrome()

driver.get("http://www.google.com")
'''
