# How to execute tests in Reqres automation testing framework

To execute tests you should create some test class object
depending on what tests you need to run. If it is API tests, you
need `TestUsersApi`:

```
    TestUsersApi test = new TestUsersApi ();
```
Else if it is UI test, you need `TestReqresUi`:
```
    TestReqresUi test = new TestReqresUi ();
```

Then if you want to toggle logging on, you can change `logsOn`
variable:
```
    test.logsOn = true;
```

And then you can call methods of this object which implement tests:
```
    //API
    test.itCanCreateUser();
    test.itCanUpdateUserPut();
    test.itCanDeleteUser();
    
    //UI
    test.itCanDoSupport();
    test.itCanDoSubscribe();
```