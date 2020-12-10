package test;

import apiclient.Response;
import logs.Logger;

//base class for all test classes

public class BaseTest {

    private int testId = 1;
    public boolean logsOn = false;

    //reporting
    public void passed () {
        System.out.println(this.testId +": passed");
        this.testId++;
    }

    //logging
    public void log (Response response) {
        if (this.logsOn) {
            Logger.logInConsole(response, Integer.toString(testId));
        }
    }

    public void log (String log) {
        if (this.logsOn) {
            Logger.logInConsole(log, Integer.toString(testId));
        }
    }

}