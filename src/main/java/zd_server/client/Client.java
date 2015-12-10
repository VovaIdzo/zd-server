package zd_server.client;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vova on 09.12.15.
 */
public class Client {
    final ClientHellman hellman;


    public Client(ClientHellman hellman) {
        this.hellman = hellman;
    }

    public void start() throws IOException, InterruptedException {

    }

    public static String makeRequest(String targetURL, String message) throws IOException {
        URL url = new URL(targetURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setUseCaches(false);
        connection.setDoOutput(true);

        //Send request
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes("request="+message);
        wr.close();

        //Get Response
        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
        String line;
        while((line = rd.readLine()) != null) {
            response.append(line);
        }
        rd.close();
        return response.toString();
    }
}
