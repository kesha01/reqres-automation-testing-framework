# How to write UI tests in Reqres automation testing framework

Logic of UI tests looks this way (Page object pattern):
- Each page has its own class (f.e. ReqresPage)
  which provide
  communication with it
- test methods of class TestReqresUi use this
  class for work
  with this page and test it
  
So firstly you should write communication with page object
and then write test in TestReqresUi.

## Communication with page object

Remember that all page objects inheriting BasePage class.
For now reqres has only one page so you don't need to create new
page object. You can just modify already existing class
ReqresPage.

So next steps can help you to write some communication 
with this page.

1. Firstly create locators of elements with which you are going to
   interact:
   
```
    By upgradeButton = By.id("trigger-pro");
    By subscribeButton = By.id("mc-embedded-subscribe");
```

2. Then create getters to those elements:
```
    private WebElement getUpgradeButton() {
        return find(upgradeButton, 4);
    }
    private WebElement getSubscribeButton() {
        return find(subscribeButton, 4);
    }
```

3. Then create methods to interact with them:
```
    public void clickOnUpgradeButton() { click(getUpgradeButton(), 4); }
    public void clickOnSubscribeButton() {click(getSubscribeButton(), 4); }
```

Now you can interact with them by Page object class 
and can write test method.

## Writing test

Now go to TestReqresUi and write test logic:

```
    public void itCanDoSubscribe() {
        //precondition
        ReqresPage reqres = new ReqresPage(driver());
        reqres.open();

        //assert url is correct
        assertPartialUrl(reqres, Config.BASE_URL);
        log("initial url: " + reqres.getCurrentUrl());

        //click on upgrade and subscribe
        reqres.clickOnUpgradeButton();
        reqres.clickOnSubscribeButton();
        reqres.switchTo(2);


        //assert url is correct (subscribe)
        assertPartialUrl(reqres, ReqresPage.SUBSCRIBE_PARTIAL_URL);
        log("final url (subscribe): "+reqres.getCurrentUrl());

        //close browser
        reqres.close();

        //report
        passed();
    }
```

Well, now test is done and you can run it.

   