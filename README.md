# Introduction

In this POC below test cases are automated in Android and iOS platforms. Mobile app used for testing is SauceLab's demo mobile application.

# Test Cases
1. Verify login with Invalid Credentials
2. Verify login with Credentials and verify 'Products' Label
3. Verify whether user can place an order for a product
   1. Login to application
   2. Tap on 'Add To Cart' button in 'Sauce Labs Backpack' product
   3. Verify whether 'Remove' button is displayed
   4. Tap on Checkout icon
   5. Tap on 'CONTINUE SHOPPING'
   6. Tap on Checkout icon
   7. Tap on Checkout button
   8. Input First Name
   9. Input Last Name
   10. Input ZIP/PostalCode
   11. Tap on 'Continue' button
   12. Tap on 'FINISH' and verify Message
   13. Tap on 'BACK HOME'
4. Verify Cancel order in Check Out Screen
5. Verify Cancel order in Checkout Overview Screen
6. Verify Logout Functionality


# Important Tools
1. Appium used as Automation tool.
2. Hybrid framework is developed in Java program language.


# Mobile App Reference
https://github.com/saucelabs/sample-app-mobile