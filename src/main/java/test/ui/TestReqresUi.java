package test.ui;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import test.BaseTest;
import pageobjects.BasePage;
import pageobjects.ReqresPage;
import org.junit.Assert;

//class for testing Reqres UI

public class TestReqresUi extends BaseTest {

    static TestReqresUi test = new TestReqresUi();

    //main method with executing tests
    public static void main(String[] args) {
        test.logsOn = true;

    }

    //test methods
    public void itShowsMethodsProperly() {
        //precondition
        ReqresPage reqres = new ReqresPage(driver());
        reqres.open();

        //assert url is correct
        assertPartialUrl(reqres, ReqresPage.INITIAL_PARTIAL_URL);
        log("initial url: "+reqres.getCurrentUrl());

        //do I need testing of info showing?

        //close browser
        reqres.close();

        //report
        passed();
    }

    public void itCanDoSupport() {
        //precondition
        ReqresPage reqres = new ReqresPage(driver());
        reqres.open();

        //assert url is correct
        assertPartialUrl(reqres, ReqresPage.INITIAL_PARTIAL_URL);
        log("initial url: "+reqres.getCurrentUrl());

        //click on anchor
        reqres.clickOnSupportAnchorButton();

        //assert url is correct
        assertFullUrl(reqres, ReqresPage.SUPPORT_ANCHOR_URL);
        log("anchor url: "+reqres.getCurrentUrl());

        //select type of pay and click support
        reqres.selectSupportRecurring();
        reqres.clickOnSupportButton();

        //assert url is correct (pay system)
        assertPartialUrl(reqres, ReqresPage.PAY_SYSTEM_PARTIAL_URL);
        log("final url (pay system): "+reqres.getCurrentUrl());

        //close browser
        reqres.close();

        //report
        passed();
    }

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


    //service methods
    private void assertFullUrl (BasePage page, String urlExpected) {
        Assert.assertEquals(page.getCurrentUrl(), urlExpected);
    }

    private void assertPartialUrl (BasePage page, String urlExpected) {
        Assert.assertTrue(page.getCurrentUrl().contains(urlExpected));
    }

    static private WebDriver driver() {
        if (Config.BROWSER.equals("firefox")) {
            return new FirefoxDriver();
        } else  {
            return new ChromeDriver();
        }
    }

}