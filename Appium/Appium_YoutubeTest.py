import time  # sleep 을 활용하기 위한 time import
from appium import webdriver  # appium webdriver를 통해 실행하기 때문에 import
from appium.webdriver.common.mobileby import MobileBy  # element의 id를 기준으로 찾기 위한 mobileBy import
from selenium.common import NoSuchElementException
import requests
import json

desired_cap = {
    "appium:deviceName": "emulator-5554",
    "platformName": "Android",
    "appium:platformVersion": "13",
    "appium:appPackage": "com.google.android.youtube",
    "appium:appActivity": "com.google.android.youtube.HomeActivity"
}

slack_token = 'xoxb-4788393319077-5146066448803-rM2xG9uVHXh7FroA3kiz2i0K'
slack_webhook_url = 'https://hooks.slack.com/services/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'

driver = webdriver.Remote("http://localhost:4723/wd/hub", desired_cap)

time.sleep(20)

try:
    deny_allow = driver.find_element(by=MobileBy.ID, value="com.android.permissioncontroller:id/permission_deny_button")
    deny_allow.click()
    result_deny_allow = "deny allow"
except NoSuchElementException:
    result_deny_allow = "allow 팝업 미 노출"

time.sleep(10)

try:
    dismiss = driver.find_element(by=MobileBy.ID, value="com.google.android.youtube:id/mealbar_dismiss_button")
    dismiss.click()
    result_dismiss = "dismiss"
except NoSuchElementException:
    result_dismiss = "dismiss 팝업 미 노출"

shorts = driver.find_element(by=MobileBy.XPATH, value="//android.widget.Button[@content-desc='Shorts']")
shorts.click()
result_shorts = "shorts 진입 성공"

# HardWare BackKey Keycode 2번 반복
for i in range(2):
    driver.press_keycode(4)

subscriptions = driver.find_element(by=MobileBy.XPATH,
                                    value="//android.widget.Button[@content-desc='Subscriptions']/android.widget.TextView")
subscriptions.click()
result_subscriptions = "subscription 진입 성공"

time.sleep(1)

library = driver.find_element(by=MobileBy.XPATH,
                              value="//android.widget.Button[@content-desc='Library']/android.widget.TextView")
library.click()
result_library = "library 진입 성공"

time.sleep(1)

mainLogo = driver.find_element(by=MobileBy.ID, value="com.google.android.youtube:id/youtube_logo")
mainLogo.click()
result_mainlogo = "mainlogo 선택 성공"

time.sleep(1)

searchbar = driver.find_element(by=MobileBy.ID, value="com.google.android.youtube:id/menu_item_1")
searchbar.click()
result_searchbar = "검색 바 선택 성공"

edtSearch = driver.find_element(by=MobileBy.ID, value="com.google.android.youtube:id/search_edit_text")
edtSearch.send_keys("랄로")
driver.press_keycode(66)
result_edtsearch = "검색어 입력 후 엔터 성공"

time.sleep(3)

subscribe = driver.find_element(by=MobileBy.ID, value="com.google.android.youtube:id/subscribe_button")
subscribe.click()
result_subscribe = "구독 성공"

# Slack 메시지를 생성합니다.
message = {
    'text': 'Appium 테스트가 완료되었습니다.',
    'attachments': [
        {'title': '테스트 결과', 'text': result_deny_allow},
        {'title': '테스트 결과', 'text': result_dismiss},
        {'title': '테스트 결과', 'text': result_shorts},
        {'title': '테스트 결과', 'text': result_subscriptions},
        {'title': '테스트 결과', 'text': result_library},
        {'title': '테스트 결과', 'text': result_mainlogo},
        {'title': '테스트 결과', 'text': result_searchbar},
        {'title': '테스트 결과', 'text': result_edtsearch},
        {'title': '테스트 결과', 'text': result_subscribe}
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