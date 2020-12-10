# How to write API test in Reqres automation testing framework

Logic of API tests looks like:
- Response - entity for HTTP responses
- ApiClientUsers sends requests, parses responses and returns
Response objects
- test methods of TestUsersApi works with responses from 
  ApiClientUsers and asserts them
  
So firstly you should create communication with endpoint in 
ApiClientUsers and then write test in TestUsersApi.

## Communication with endpoint

Further steps you have to do in ApiClientUsers class.
1. Prepare URL:
```
    private static String usersUrl () {return Config.BASE_URL + Endpoints.USERS;}
```

2. Prepare connection (resource):
```
    private static HttpURLConnection usersResource () throws IOException {
        URL url = new URL (usersUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        return connection;
    }
```

Raw and JSON response parsing are already done, so this step you
can skip. Setup output properties as well.

3. Write HTTP-request method returning Response object:
```
    public Response createUser (String name, String job ) throws IOException {
        HttpURLConnection connection = usersResource();
        post(connection, "{\"name\": \"" + name + "\", \"job\": \""+job+"\"}");
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }
```

Well, now you can work with it in TestUsersApi class.

## Writing test

This step you have to do in TestUsersApi class.

Write test logic in separate test method

```
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
```

Well, now test is done and you can run it.