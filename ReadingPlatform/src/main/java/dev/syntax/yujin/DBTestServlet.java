package dev.syntax.yujin;
import util.DBConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/dbtest")
public class DBTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            out.println("<h2>DB 연결 성공!</h2>");
        } else {
            out.println("<h2>DB 연결 실패...</h2>");
        }
    }
}
