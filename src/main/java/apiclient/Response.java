package apiclient;

import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;

//specific entity for all HTTP-responses

public class Response {

    public int statusCode;
    public String message;
    public JSONObject body;

    public Response (HttpURLConnection connection) throws IOException {
        this.statusCode = connection.getResponseCode();
        this.message = connection.getResponseMessage();
        String body = ApiClientUsers.rawResponse(connection);
        if (body != "") this.body = new JSONObject(body);
    }

}