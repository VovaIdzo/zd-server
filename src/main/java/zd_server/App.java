package zd_server;

import zd_server.Utils.Log;
import zd_server.client.Client;
import zd_server.client.ClientHellman;
import zd_server.client.OnRequestListener;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App{
    private final static String BASE_URL = "http://127.0.0.1:8080/server/";
    ClientHellman clientHellman;
    public static void main(String[] args) {
            new App().start();
    }

    public void start() {
        clientHellman = new ClientHellman();
        Client client = new Client(clientHellman);
        try {
            String[] pq = Client.makeRequest(BASE_URL, "qp").split(",");
            clientHellman.setP(Integer.valueOf(pq[0]));
            clientHellman.setA(Integer.valueOf(pq[1]));
            clientHellman.generateX();
            String x = Client.makeRequest(BASE_URL, "x");
            clientHellman.setY(new BigInteger(x));
            Client.makeRequest(BASE_URL, ""+ clientHellman.getX());
            clientHellman.computeK();
            Log.e("Client K "+clientHellman.getStringK());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
