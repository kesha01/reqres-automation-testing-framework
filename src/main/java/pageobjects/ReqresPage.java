package pageobjects;

import config.Config;
import data.Endpoints;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//class for main reqres page

public class ReqresPage extends BasePage {

    public ReqresPage(WebDriver driver) {
        super(driver);
    }

    //URLs
    public static final String INITIAL_PARTIAL_URL = Config.BASE_URL;
    public static final String SUPPORT_ANCHOR_URL = Config.BASE_URL+ Endpoints.SUPPORT_ANCHOR;
    public static final String PAY_SYSTEM_PARTIAL_URL = "https://checkout.stripe.com/pay/cs_live_a";
    public static final String SUBSCRIBE_PARTIAL_URL = "https://benhowdle.us20.list-manage.com/subscribe/post";
    public static final String SANDBOX_URL = "https://codesandbox.io/s/polished-butterfly-j17lt?from-embed";

    //locators
    By endpoint = By.cssSelector(".url");
    By responseCode = By.cssSelector(".response-code");
    By requestBody = By.cssSelector(".request > pre:nth-child(2)");
    By responseBody = By.cssSelector(".output > div:nth-child(2) > pre:nth-child(3)");

    By supportAnchorButton = By.cssSelector("div.t-center:nth-child(2) > button:nth-child(1) > a:nth-child(1)");
    By upgradeButton = By.id("trigger-pro");
    By subscribeButton = By.id("mc-embedded-subscribe");
    By supportOneTimeRadioButton = By.id("supportOneTime");
    By supportRecurringRadioButton = By.id("supportRecurring");
    By supportButton = By.cssSelector("#supportForm > button:nth-child(3)");

    public void open() {
        open(INITIAL_PARTIAL_URL);
    }

    private By methodButtonLocator(int order) {
        return By.cssSelector(".endpoints > ul:nth-child(1) > li:nth-child(" + order + ")");
    }

    //elements
    private WebElement getMethodButton(int number) {
        return find(methodButtonLocator(number), 4);
    }
    private WebElement getEndpointElement() {
        return find(endpoint, 4);
    }
    private WebElement getResponseCodeElement() {
        return find(responseCode, 4);
    }
    private WebElement getRequestBodyElement() {
        return find(requestBody, 4);
    }
    private WebElement getResponseBodyElement() {
        return find(responseBody, 4);
    }
    private WebElement getSupportAnchorButton() {
        return find(supportAnchorButton, 4);
    }
    private WebElement getUpgradeButton() {
        return find(upgradeButton, 4);
    }
    private WebElement getSubscribeButton() {
        return find(subscribeButton, 4);
    }
    private WebElement getSupportOneTimeButton () {return find(supportOneTimeRadioButton, 4);}
    private WebElement getSupportRecurringRadioButton () {return find(supportRecurringRadioButton, 4);}
    private WebElement getSupportButton () {return find(supportButton, 4);}

    //clicks, selects
    public void clickOnMethodButton(int number) {
        click(getMethodButton(number), 4);
    }
    public void clickOnSupportAnchorButton() {
        click(getSupportAnchorButton(), 4);
    }
    public void clickOnUpgradeButton() { click(getUpgradeButton(), 4); }
    public void clickOnSubscribeButton() {click(getSubscribeButton(), 4); }
    public void clickOnSupportButton () {click(getSupportButton(), 4); }
    public void selectSupportOneTime () {click(getSupportOneTimeButton(), 4); }
    public void selectSupportRecurring () {click(getSupportRecurringRadioButton(), 4); }

    //getting data from page
    public String getMethod(int number) {
        WebElement methodButton = getMethodButton(number);

        return methodButton.getAttribute("data-http");
    }

    public String getEndpoint() {
        return getEndpointElement().getText();
    }
    public String getResponseCode() {
        return getResponseCodeElement().getText();
    }
    public String getRequestBody() {
        return getRequestBodyElement().getText();
    }
    public String getResponseBody() {
        return getResponseBodyElement().getText();
    }

}