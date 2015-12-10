package zd_server.servlet;

import java.math.BigInteger;

/**
 * Created by vova on 10.12.15.
 */
public interface RequestController {
    int getP();
    int getA();
    BigInteger getX();
    void setX(int x);
}
