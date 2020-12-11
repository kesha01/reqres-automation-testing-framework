# Reqres automation testing framework

## Config
- String *BASE_URL* - base url for all other URLs
- String *BROWSER* - browser to run UI tests

## Logger
- void logInConsole (Response response, String name) - log
Response object data in console
- void logInConsole (String log, String name) - log String data
in console

## DataProvider

- String getUserName() - return some user name
- String getUserJob() - return some user job
- String getUserEmail() - return some user email
- String getUserPassword() - return some user password
- int getValidUserId() - return some valid user id
- int getInvalidUserId() - return some invalid user id
- int getValidResourceId () - return some valid resource id
- int getInvalidResourceId () - return some invalid resource id

## Endpoints

- String *USERS* - users endpoint
- String *REGISTER* - register endpoint
- String *LOGIN* - login endpoint
- String *UNKNOWN* - unknown endpoint
- String *SUPPORT_ANCHOR* - support anchor 

## ApiClientUsers

- String usersUrl () - returns prepared users URL
- String userUrl (int id) - returns prepared user URL
- String registerUrl () - returns prepared register URL
- String loginUrl () - returns prepared login URL
- String unknownUrl () - returns prepared unknown URL
- String unknownUrl (int id) - returns prepared unknown URL with id


- HttpURLConnection usersResource () - returns users resource
- HttpURLConnection unknownResource () - returns unknown resource
- HttpURLConnection unknownResource (int id) - returns unknown
  resource with id
- HttpURLConnection userResource (int id) - returns user resource
- HttpURLConnection registerResource () - returns register resource
- HttpURLConnection loginResource () - returns login resource


- String rawResponse (HttpURLConnection connection) - returns raw response
- JSONObject jsonResponse (HttpURLConnection connection) - returns JSON response


- void setOutProperties(HttpURLConnection connection, String method) -setup
output properties
- void post (HttpURLConnection connection, String body) - sends
request body with POST method
- void put (HttpURLConnection connection, String body) - sends
  request body with PUT method
  

- Response getUser (int id) - get user by API
- Response getUsers () - get users by API
- Response createUser (String name, String job) - create user by API
- Response createUser (String name) - create wrong user by API
- Response updateUserPut (int id, String name, String job) update user
  via PUT method by API
- Response register (String email, String password) - register user
  by API
- Response register (String email) - register wrong user by API
- Response login (String email, String password) - login user by API
- Response login (String email) - login wrong user by API
- Response deleteUser (int id) - delete user by API
- Response getUnknown () - get unknown resource by API
- Response getUnknown (int id) - get unknown resource with id by API

## Response

- int statusCode - response status code
- String message - response message
- JSONObject body - response body

## BasePage
- WebDriver driver - main entity for working with browser

- WebElement find(By locator, int timeOut) - finds element on page
  with wait
- void click(WebElement element, int timeOut) - clicks in element
  on page with wait
- String getCurrentUrl() - returns current URL
- void switchTo (int index) - switch to tab with index
- void open(String url) - open page with URL
- void close() - close browser

### ReqresPage
extends BasePage

- String *INITIAL_PARTIAL_URL* - initial reqres URL
- String *SUPPORT_ANCHOR_URL* - URL with support anchor
- String *PAY_SYSTEM_PARTIAL_URL* - pay system URL
- String *SUBSCRIBE_PARTIAL_URL* - subscribe URL


- By endpoint - returns endpoint locator
- By responseCode - returns response code locator
- By requestBody - returns request body locator
- By responseBody - returns response body locator


- By supportAnchorButton - returns support anchor button locator
- By upgradeButton - returns endpoint locator
- By subscribeButton - returns upgrade button locator
- By supportOneTimeRadioButton - returns support one time
  radiobutton locator
- By supportRecurringRadioButton - returns support recurring
   radiobutton locator
- By supportButton - returns support button locator


- void open() - open reqres page

- WebElement getEndpointElement() - returns endpoint element
- WebElement getResponseCodeElement() - returns response code
  element
- WebElement getRequestBodyElement() - returns request body
  element
- WebElement getResponseBodyElement() - returns response body element
- WebElement getSupportAnchorButton() - returns support anchor button
- WebElement getUpgradeButton() - returns upgrade button
- WebElement getSubscribeButton() - returns subscribe button
- WebElement getSupportOneTimeButton () - returns support one time
  button
- WebElement getSupportRecurringRadioButton () - returns support
  recurring radiobutton
- WebElement getSupportButton () - returns support button


- void clickOnSupportAnchorButton() -- clicks on support anchor button
- void clickOnUpgradeButton() - clicks on upgrade button
- void clickOnSubscribeButton() - clicks on subscribe button
- void clickOnSupportButton () - clicks on support button
- void selectSupportOneTime () - selects support one time
- void selectSupportRecurring () - selects support recurring

- String getEndpoint() - returns endpoint text
- String getResponseCode() - returns response code text
- String getRequestBody() - returns request body text
- String getResponseBody() - returns response body text


## BaseTest

- int testId - test id for counting tests
- boolean logsOn - logs option
- void passed () - reports test is passed
- void log (Response response) - do Response entity log
- void log (String log) - do String entity log

### TestUsersApi

- void main(String[] args) - main method

tests:
- void itCanCreateUser ()
- void itCantCreateUserWithoutJob ()
- void itCanUpdateUserPut()
- void itCanDeleteUser ()
- void itCanGetUser ()
- void itCantGetUserNotFound ()
- void itCantRegisterWithoutPassword ()
- void itCantLoginWithoutPassword ()
- void itCanGetUsers ()
- void itCanRegister ()
- void itCanLogin ()
- void itCantGetUnknownResourceNotFound()
- void itCanGetUnknownResource()
- void itCanGetUnknownResourceWithId ()

service methods:
- void assertCode (Response response, int code) - asserts response
  code
- void userDataValidate(JSONObject data) - asserts user data
- void unknownDataValidate(JSONObject data) - asserts unknown data
- void pageStatsValidate(JSONObject page) - asserts page stats
- void supportValidate (JSONObject support) - asserts support data

### TestReqresUi


- void main(String[] args) - main method

test methods:
- void itCanDoSupport()
- void itCanDoSubscribe()

service methods:
- void assertFullUrl (BasePage page, String urlExpected) - asserts
  full URL
- void assertPartialUrl (BasePage page, String urlExpected) - asserts
  partial URL
- WebDriver driver() - returns new browser
