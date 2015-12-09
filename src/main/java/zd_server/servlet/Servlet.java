package zd_server.servlet;

import zd_server.Utils.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vova on 10.12.15.
 */
public class Servlet extends HttpServlet {

    RequestController requestController = new RequestController();

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
            response = requestController.getP() + ","+requestController.getQ();
        } else if (par.equals("x")){
            response = ""+requestController.getX();
        } else {
            requestController.setX(Integer.valueOf(par));
            response = "ok";
        }

        PrintWriter out = resp.getWriter();
        out.println(response);
    }
}
