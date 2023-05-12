from appium.webdriver.common.mobileby import MobileBy

def Login(driver, email_ID, pwd_PassWord):
    email = driver.find_element(by=MobileBy.ID, value="com.coupang.mobile:id/member_core_edit_email")
    email.click()
    email.send_keys(email_ID)
    pwd = driver.find_element(by=MobileBy.ID, value="com.coupang.mobile:id/edit_password")
    pwd.click()
    pwd.send_keys(pwd_PassWord)
    login_btn = driver.find_element(by=MobileBy.ID, value="com.coupang.mobile:id/login_btn")
    login_btn.click()
