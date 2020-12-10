package apiclient;

import config.Config;
import data.Endpoints;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

//class for using API: sending HTTP-requests, parse HTTP-response and get it

public class ApiClientUsers {

    //URLs
    private static String usersUrl () {return Config.BASE_URL + Endpoints.USERS;}
    private static String userUrl (int id) {return Config.BASE_URL + Endpoints.USERS + "/" + id;}
    private static String registerUrl () {return Config.BASE_URL + Endpoints.REGISTER;}
    private static String loginUrl () {return Config.BASE_URL + Endpoints.LOGIN;}
    private static String unknownUrl () {return Config.BASE_URL + Endpoints.UNKNOWN;}
    private static String unknownUrl (int id) {return Config.BASE_URL + Endpoints.UNKNOWN+"/"+id;}

    //connections (resources)
    private static HttpURLConnection usersResource () throws IOException {
        URL url = new URL (usersUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        return connection;
    }

    private static HttpURLConnection unknownResource () throws IOException {
        URL url = new URL(unknownUrl());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        return  connection;
    }

    private static HttpURLConnection unknownResource (int id) throws IOException {
        URL url = new URL(unknownUrl(id));
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        return  connection;
    }

    private static HttpURLConnection userResource (int id) throws IOException {
        URL url = new URL(userUrl(id));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        return connection;
    }

    private static HttpURLConnection registerResource () throws IOException {
        URL url = new URL(registerUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        return connection;
    }
    private static HttpURLConnection loginResource () throws IOException {
        URL url = new URL(loginUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        return connection;
    }

    //response parsing
    protected static String rawResponse (HttpURLConnection connection) throws IOException {
        InputStream stream = connection.getErrorStream();
        if (stream == null) stream = connection.getInputStream();
        String line;
        StringBuilder response = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

        return response.toString();
    }

    protected static JSONObject jsonResponse (HttpURLConnection connection) throws IOException {
        JSONObject response = new JSONObject(rawResponse(connection));

        return response;
    }

    //setup methods with request body
    private static void setOutProperties(HttpURLConnection connection, String method) throws ProtocolException {
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setRequestMethod(method);
    }

    private static void post (HttpURLConnection connection, String body) throws IOException {
        setOutProperties(connection, "POST");
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            for (int i = 0; i < input.length; i++) {
                os.write(input[i]);
            }
        }
    }

    private static void put (HttpURLConnection connection, String body) throws IOException {
        setOutProperties(connection, "PUT");
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            for (int i = 0; i < input.length; i++) {
                os.write(input[i]);
            }
        }
    }

    private static void patch (HttpURLConnection connection, String body) throws IOException {
        connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        setOutProperties(connection, "POST");
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            for (int i = 0; i < input.length; i++) {
                os.write(input[i]);
            }
        }
    }

    //interactive with endpoints
    public Response getUser (int id) throws IOException {
        HttpURLConnection connection = userResource(id);
        connection.setRequestMethod("GET");
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }

    public Response getUsers () throws IOException {
        HttpURLConnection connection = usersResource();
        connection.setRequestMethod("GET");
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }

    public Response createUser (String name, String job ) throws IOException {
        HttpURLConnection connection = usersResource();
        post(connection, "{\"name\": \"" + name + "\", \"job\": \""+job+"\"}");
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }

    public Response createUser (String name) throws IOException {
        HttpURLConnection connection = usersResource();
        post(connection, "{\"name\": \"" + name + "\"}");
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }

    public Response updateUserPut (int id, String name, String job ) throws IOException {
        HttpURLConnection connection = userResource(id);
        put(connection, "{\"name\": \"" + name + "\", \"job\": \""+job+"\"}");
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }

    public Response updateUserPatch (int id, String name, String job ) throws IOException {
        HttpURLConnection connection = userResource(id);
        patch(connection, "{\"name\": \"" + name + "\", \"job\": \""+job+"\"}");
        //System.out.println("Status code: "+connection.getResponseCode());
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }

    public Response register (String email, String password) throws IOException {
        HttpURLConnection connection = registerResource();
        post(connection, "{\"email\": \"" + email + "\", \"password\": \""+password+"\"}");
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }

    public Response register (String email) throws IOException {
        HttpURLConnection connection = registerResource();
        post(connection, "{\"email\": \"" + email + "\"}");
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }

    public Response login (String email, String password) throws IOException {
        HttpURLConnection connection = loginResource();
        post(connection, "{\"email\": \"" + email + "\", \"password\": \""+password+"\"}");
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }

    public Response login (String email) throws IOException {
        HttpURLConnection connection = loginResource();
        post(connection, "{\"email\": \"" + email + "\"}");
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }

    public Response deleteUser (int id) throws IOException {
        HttpURLConnection connection = userResource(id);
        connection.setRequestMethod("DELETE");

        Response response = new Response(connection);

        return response;
    }

    public Response getUnknown () throws IOException {
        HttpURLConnection connection = unknownResource();
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }

    public Response getUnknown (int id) throws IOException {
        HttpURLConnection connection = unknownResource(id);
        connection.disconnect();

        Response response = new Response(connection);

        return response;
    }

}