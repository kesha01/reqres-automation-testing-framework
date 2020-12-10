package logs;

import apiclient.Response;

//class for logs and making reports

public class Logger {

    static public void logInConsole (Response response, String name) {
        System.out.println("\n"+name+": "+response.statusCode+", "+response.message+", "+response.body);
    }

    static public void logInConsole (String log, String name) {
        System.out.println("\n"+name+": "+log);
    }

}