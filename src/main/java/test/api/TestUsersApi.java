package test.api;

import test.BaseTest;
import apiclient.ApiClientUsers;
import apiclient.Response;
import data.DataProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.IOException;

//class for testing Reqres API

public class TestUsersApi extends BaseTest {

    static ApiClientUsers api = new ApiClientUsers();
    static TestUsersApi test = new TestUsersApi();

    //main method with executing tests
    public static void main(String[] args) {
        test.logsOn = false;

        try {
            //positive
            test.itCanCreateUser();
            test.itCanUpdateUserPut();
            test.itCanDeleteUser();
            test.itCanGetUser();
            test.itCanGetUsers();
            test.itCanRegister();
            test.itCanLogin();
            test.itCanGetUnknownResource();
            test.itCanGetUnknownResourceWithId();

            //negative
            test.itCantGetUserNotFound();
            test.itCantRegisterWithoutPassword();
            test.itCantLoginWithoutPassword();
            test.itCantGetUnknownResourceNotFound();
            test.itCantCreateUserWithoutJob();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //test methods
    public void itCanCreateUser () throws IOException {
        //data
        String name = DataProvider.getUserName();
        String job = DataProvider.getUserJob();
        Response user = api.createUser(name, job);

        //logs
        log(user);

        //asserts
        assertCode(user, 201);
        Assert.assertEquals(name, user.body.getString("name"));
        Assert.assertEquals(job, user.body.getString("job"));
        Assert.assertTrue(user.body.toString().contains("\"id\""));
        Assert.assertTrue(user.body.toString().contains("\"createdAt\""));

        //report
        passed();
    }

    public void itCantCreateUserWithoutJob () throws IOException {
        //data
        String name = DataProvider.getUserName();
        Response user = api.createUser(name);

        //logs
        log(user);

        //asserts
        assertCode(user, 400);

        //report
        passed();
    }

    public void itCanUpdateUserPut() throws IOException {
        //data
        String name = DataProvider.getUserName();
        String job = DataProvider.getUserJob();
        int id = DataProvider.getValidUserId();
        Response user = api.updateUserPut(id, name, job);

        //logs
        log(user);

        //asserts
        assertCode(user, 200);
        Assert.assertEquals(name, user.body.getString("name"));
        Assert.assertEquals(job, user.body.getString("job"));
        Assert.assertTrue(user.body.toString().contains("\"updatedAt\""));

        //report
        passed();
    }

    public void itCanUpdateUserPatch(int userId, String name, String job) throws IOException {
        //data
        Response user = api.updateUserPatch(userId, name, job);

        //logs
        log(user);

        //asserts
        assertCode(user, 200);
        Assert.assertEquals(name, user.body.getString("name"));
        Assert.assertEquals(job, user.body.getString("job"));
        Assert.assertTrue(user.body.toString().contains("\"updatedAt\""));

        //report
        passed();
    }

    public void itCanDeleteUser () throws IOException {
        //data
        Response user = api.deleteUser(DataProvider.getValidUserId());

        //logs
        log(user);

        //asserts
        assertCode(user, 204);

        //report
        passed();
    }

    public void itCanGetUser () throws IOException {
        //data
        Response user = api.getUser(DataProvider.getValidUserId());
        JSONObject data = user.body.getJSONObject("data");
        JSONObject support = user.body.getJSONObject("support");

        //logs
        log(user);

        //asserts
        assertCode(user, 200);
        userDataValidate(data);
        supportValidate(support);

        //report
        passed();
    }

    public void itCantGetUserNotFound () throws IOException {
        //data
        Response user = api.getUser(DataProvider.getInvalidUserId());

        //logs
        log(user);

        //asserts
        assertCode(user, 404);

        //report
        passed();
    }

    public void itCantRegisterWithoutPassword () throws IOException {
        //data
        Response user = api.register(DataProvider.getUserEmail());

        //logs
        log(user);

        //asserts
        assertCode(user, 400);
        Assert.assertTrue(user.body.toString().contains("\"error\":\"Missing password\""));

        //report
        passed();
    }

    public void itCantLoginWithoutPassword () throws IOException {
        //data
        Response user = api.login(DataProvider.getUserEmail());

        //logs
        log(user);

        //asserts
        assertCode(user, 400);
        Assert.assertTrue(user.body.toString().contains("\"error\":\"Missing password\""));

        //report
        passed();
    }

    public void itCanGetUsers () throws IOException {
        //data
        Response users = api.getUsers();
        JSONArray data = users.body.getJSONArray("data");
        JSONObject support = users.body.getJSONObject("support");

        //logs
        log(users);

        //asserts
        assertCode(users, 200);
        pageStatsValidate(users.body);
        pageStatsValidate(users.body);
        for (int i = 0; i < data.length(); i++) {
            userDataValidate(data.getJSONObject(i));
        }
        supportValidate(support);

        //report
        passed();
    }

    public void itCanRegister () throws IOException {
        //data
        Response user = api.register(DataProvider.getUserEmail(), DataProvider.getUserPassword());

        //logs
        log(user);

        //asserts
        assertCode(user, 200);
        Assert.assertTrue(user.body.toString().contains("id"));
        Assert.assertTrue(user.body.toString().contains("token"));

        //report
        passed();
    }

    public void itCanLogin () throws IOException {
        //data
        Response user = api.login(DataProvider.getUserEmail(), DataProvider.getUserPassword());

        //logs
        log(user);

        //asserts
        assertCode(user, 200);
        Assert.assertTrue(user.body.toString().contains("token"));

        //report
        passed();
    }

    public void itCantGetUnknownResourceNotFound() throws IOException {
        //data
        Response resource = api.getUnknown(DataProvider.getInvalidResourceId());

        //logs
        log(resource);

        //asserts
        assertCode(resource, 404);
        Assert.assertEquals(resource.body.toString(), "{}");

        //report
        passed();
    }

    public void itCanGetUnknownResource() throws IOException {
        //data
        Response resource = api.getUnknown();
        JSONArray data = resource.body.getJSONArray("data");
        JSONObject support = resource.body.getJSONObject("support");

        //logs
        log(resource);

        //asserts
        assertCode(resource, 200);
        pageStatsValidate(resource.body);
        for (int i = 0; i < data.length(); i++) {
            unknownDataValidate(data.getJSONObject(i));
        }
        supportValidate(support);

        //report
        passed();
    }

    public void itCanGetUnknownResourceWithId () throws IOException {
        //data
        Response resource = api.getUnknown(DataProvider.getValidResourceId());
        JSONObject data = resource.body.getJSONObject("data");
        JSONObject support = resource.body.getJSONObject("support");

        //logs
        log(resource);

        //asserts
        assertCode(resource, 200);
        unknownDataValidate(data);
        supportValidate(support);

        //report
        passed();
    }


    //service methods
    private static void assertCode (Response response, int code) {
        Assert.assertEquals(code, response.statusCode);
    }

    private static void userDataValidate(JSONObject data) {
        Assert.assertTrue(data.toString().contains("id"));
        Assert.assertTrue(data.toString().contains("email"));
        Assert.assertTrue(data.toString().contains("first_name"));
        Assert.assertTrue(data.toString().contains("last_name"));
        Assert.assertTrue(data.toString().contains("avatar"));
    }

    private static void unknownDataValidate(JSONObject data) {
        Assert.assertTrue(data.toString().contains("id"));
        Assert.assertTrue(data.toString().contains("name"));
        Assert.assertTrue(data.toString().contains("year"));
        Assert.assertTrue(data.toString().contains("color"));
        Assert.assertTrue(data.toString().contains("pantone_value"));
    }

    private static void pageStatsValidate(JSONObject page) {
        Assert.assertTrue(page.toString().contains("page"));
        Assert.assertTrue(page.toString().contains("per_page"));
        Assert.assertTrue(page.toString().contains("total"));
        Assert.assertTrue(page.toString().contains("total_pages"));
    }

    private static void supportValidate (JSONObject support) {
        Assert.assertTrue(support.toString().contains("url"));
        Assert.assertTrue(support.toString().contains("text"));

    }

}