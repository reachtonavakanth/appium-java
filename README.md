# Introduction

In this POC below test cases are automated in Android and iOS platforms. Mobile app used for testing is SauceLab's demo mobile application.

# Simple Test Cases
1. Verify login with Invalid Credentials
   1. Launch the app
   2. Enter invalid username
   3. Enter invalid password
   4. Tap on Login button
   5. Verify the Error message
2. Verify login with Credentials
   1. Launch the app
   2. Enter valid username
   3. Enter valid password
   4. Verify 'Products' Label in Products screen
3. Verify logout
   1. Launch the app
   2. Enter valid user name
   3. Enter valid password
   4. Click on Menu icon
   5. Click on logout button
   6. Verify username text field in login screen
# End - End Test Case
4. Verify whether user can place an order for a product
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
   12. Scroll to 'Finish' button
   13. Tap on 'FINISH' and verify Message
   14. Tap on 'BACK HOME'
   
# Important Tools
1. Appium used as Automation tool.
2. Hybrid framework developed in Java program language.


# Mobile App Reference
https://github.com/saucelabs/sample-app-mobile