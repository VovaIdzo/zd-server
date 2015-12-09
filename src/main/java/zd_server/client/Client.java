package zd_server.client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vova on 09.12.15.
 */
public class Client {
    final OnRequestListener requestListener;
    private final static String BASE_URL = "http://127.0.0.1:8080/server/";

    public Client(OnRequestListener requestListener) {
        this.requestListener = requestListener;
    }

    public void start() throws IOException, InterruptedException {
        String[] pq = makeRequest(BASE_URL, "qp").split(",");
        requestListener.setP(Integer.valueOf(pq[0]));
        requestListener.setQ(Integer.valueOf(pq[1]));
        String x = makeRequest(BASE_URL, "x");
        requestListener.setQ(Integer.valueOf(x));
        makeRequest(BASE_URL, ""+requestListener.getX());
    }

    private String makeRequest(String targetURL, String message) throws IOException {
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
