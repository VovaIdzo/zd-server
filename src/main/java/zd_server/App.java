package zd_server;

import zd_server.Utils.Log;
import zd_server.client.Client;
import zd_server.client.OnRequestListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App implements OnRequestListener{
    public static void main(String[] args) {
            new App().start();
    }

    public void start() {
        Client client = new Client(this);
        try {
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return 20;
    }

    public void setP(int p) {

    }

    public void setQ(int q) {

    }
}
