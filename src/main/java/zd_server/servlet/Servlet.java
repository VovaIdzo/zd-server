package zd_server.servlet;

import zd_server.Utils.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * Created by vova on 10.12.15.
 */
public class Servlet extends HttpServlet {

    ServerHellman requestController = new ServerHellman();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        Log.e("get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String par = req.getParameter("request");
        String response = "";
        if (par.equals("qp")){
            response = requestController.getP() + ","+requestController.getA();
            requestController.generateX();
        } else if (par.equals("x")){
            response = ""+requestController.getX();
        } else {
            requestController.setY(new BigInteger(par));
            requestController.computeK();
            Log.e("Server K "+requestController.getStringK());
            response = "ok";
        }

        PrintWriter out = resp.getWriter();
        out.println(response);
    }
}
