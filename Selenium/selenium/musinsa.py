from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import datetime

driver = webdriver.Chrome()

# 로그인 페이지로 이동
driver.get("https://www.musinsa.com")

time.sleep(3)

login = driver.find_element_by_class_name("header-member__login")
login.click()

# 이메일/사용자 이름 입력 요소 찾기 및 로그인 정보 입력
#username = driver.find_element_by_id("username")
username = driver.find_element_by_name("id")
username.click()
username.send_keys("bin1121")

# 비밀번호 입력 요소 찾기 및 비밀번호 입력
password = driver.find_element_by_name("pw")
password.click()
password.send_keys("han0322")

# 제출 버튼 찾기 및 클릭하여 로그인
submit = driver.find_element_by_css_selector("button[type='submit']")
submit.click()

time.sleep(3)

# 패스워드 변경 페이지로 이동되는 경우에 따른 if 문 처리

if driver.title == "비밀번호 변경 | 무신사 스토어":
    pwnChg = driver.find_element_by_class_name("login-button__link--bottom")
    pwnChg.click()
else:
    print("메인 화면 접속 확인")

time.sleep(3)

now = datetime.datetime.now()
screenshot_name = "homescreenshot_" + now.strftime("%Y-%m-%d %H-%M-%S") + ".png"

driver.save_screenshot(screenshot_name)
# 로그인 성공 후 드라이버 종료
driver.quit()
