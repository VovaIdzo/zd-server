package zd_server.client;

import java.math.BigInteger;

/**
 * Created by vova on 10.12.15.
 */
public interface OnRequestListener {
    BigInteger getX();
    void setP(int p);
    void setA(int q);
    void setY(BigInteger x);
}
