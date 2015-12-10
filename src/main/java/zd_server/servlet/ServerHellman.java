package zd_server.servlet;

import zd_server.Utils.Log;
import zd_server.client.OnRequestListener;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Dmitry on 09.12.2015.
 */
public class ServerHellman implements RequestController {

    //debug only
    public void setP(int p) {
        this.p = p;
    }

    public void setQ(int q) {

    }

    public ServerHellman() {
        generateP();
        computeA();
    }

    int p; //велике просте число
    int a; //первісний корінь p
    int x; //згенероване ціле велике x або y (в залежності від того, який це клієнт)
    int y; //прийняте з іншого боку Y
    BigInteger k; //ключ
    BigInteger Y;
    BigInteger X;

    public void generateP() {
        BigInteger prime = BigInteger.probablePrime(13, new Random());
        p = prime.intValue();
    }

    public void computeA() { //знаходить первісний корінь
        boolean array[] = new boolean[p];
        boolean aFound;

        for (int a = 2; ; a++) { //необмежений спочатку цикл, працює поки не знайдемо потрібне a
            aFound = true; //припускаємо, що нам підійде це a
            for(int i=0; i<p; i++) {
                array[i] = false; //обнуляємо масив, де тримаємо інформацію про вже записані
            }
//            System.out.println("Current a: " + a + "\n");
//            if (a==6) {
                for (int i = 0; i < p - 1; i++) { //перебираємо усі степені числа A mod p
                    BigInteger p = new BigInteger(Integer.toString(this.p));
                    BigInteger elem = new BigInteger(Integer.toString(a));
                    elem = elem.pow(i).mod(p);  //A^i mod p
//                    System.out.println(i + ": " + elem);
                    if (array[elem.intValue()] != true) { //якщо такого елементу ще не було
                        array[elem.intValue()] = true; //записуємо
                    } else { //такий елемент вже був, значить це число не підійшло
                        aFound = false; //
                        break;
                    }
                }
                if (aFound == true) { //число підійшло, записуємо у властивість об'єкту, виходимо з методу
                    this.a = a;
                    return;
                }
            }
        }
//    }

    public void generateX() {
        x = 1000 + new Random().nextInt(1000); //рендом від 0 до 999 включно і зміщаємо його на 1000, щоб мінімум 1000 було (велике число за умовою)
    }

    public void sendParams() { //відправляє доступні обом відкриті p, a
        //TODO: відправити
    }

    public void receiveParams() {
        //TODO: отримати p, a від іншої сторони
    }

    public void sendX() {
        this.X = new BigInteger(Integer.toString(this.a));
        X = X.pow(x).mod(new BigInteger(Integer.toString(p)));
    }

    public void receiveY() {
        //TODO: отримати те, що іншою машиною було відправлено як X, записати в Y методом setY
    }


    public void computeK() {
        BigInteger big = this.Y;
        big = big.pow(x).mod(new BigInteger(Integer.toString(p))); // Y^x mod p
        this.k = big;
    }

    public String getStringK() {
        return this.k.toString();
    }


    public int getP() {
        Log.e("p "+p);
        return p;
    }

    public int getA() {
        Log.e("a "+a);
        return a;
    }

    public BigInteger getX() {
        sendX();
        Log.e("X "+X);
        return X;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(BigInteger y) {
        Log.e("Y " + y);
        Y = y;
    }
}
