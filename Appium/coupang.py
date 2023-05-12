import time  # sleep 을 활용하기 위한 time import
from appium import webdriver  # appium webdriver를 통해 실행하기 때문에 import
from appium.webdriver.common.mobileby import MobileBy  # element의 id를 기준으로 찾기 위한 mobileBy import
from coupang_login import Login
from selenium.common import NoSuchElementException
import requests
import json

slack_token = 'xoxb-4788393319077-5232574314546-QAqE8EkvsX5xZZDhUHyUFXRy'
slack_webhook_url = 'https://hooks.slack.com/services/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'


desired_cap = {
    "appium:deviceName": "RFCM80845PW",
    "platformName": "Android",
    "appium:platformVersion": "11",
    "appium:appPackage": "com.coupang.mobile",
    "appium:appActivity": "com.coupang.mobile.domain.home.presentation.view.MainActivity"
}

driver = webdriver.Remote("http://localhost:4723/wd/hub", desired_cap)
driver.implicitly_wait(10)

try:
    permission = driver.find_element(by=MobileBy.ID, value="com.coupang.mobile:id/alert_image")
    confirm = driver.find_element(by=MobileBy.ID, value="com.coupang.mobile:id/confirm_button")
    confirm.click()
    permission_result = "권한 안내 팝업 확인 완료"
    print(permission_result)
except:
    permission_result = "권한 안내 팝업 미 노출"
    print(permission_result)

myCoupang = driver.find_element(by=MobileBy.XPATH, value="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout[4]/android.widget.TextView")
myCoupang.click()
myCoupang_result = "MyCoupang 선택 완료"

login = driver.find_element(by=MobileBy.XPATH, value="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[1]")
login.click()
login_result = "login 페이지 진입 완료"

Login(driver, "bin1121@naver.com", "han0322")

login_name = driver.find_element(by=MobileBy.XPATH, value="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[1]")
login_name.click()
myInformation = "내정보관리 진입완료"

def take_screenshot():
    filename = f"screenshot_{int(time.time())}.png"
    driver.save_screenshot(filename)
    return filename


screenshot_file = take_screenshot()
message = {
    'text': 'Appium 테스트가 완료되었습니다.',
    'attachments': [
        {'title': '테스트 결과', 'text': permission_result},
        {'title': '테스트 결과', 'text': login_result},
        {'title': '테스트 결과', 'text': myInformation},
        {'title': '캡쳐 이미지', 'image_url': f'https://example.com/screenshots/{screenshot_file}'}
    ]
}

# Slack 메시지를 전송합니다.
response = requests.post(
    slack_webhook_url,
    data=json.dumps(message),
    headers={'Content-Type': 'application/json'}
)

# Slack 메시지 전송 결과를 출력합니다.
print(response.status_code, response.text)