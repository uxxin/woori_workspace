package dev.syntax.step02_servlet_processing;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet 객체가 제공하는 라이프사이클 메서드들(init, service, destroy() 등)이
 * 의도한대로 동작하고 호출되는지 간단한 출력문 등으로 작성 후 동작 확인
 *
 * 서블릿 객체가 생성되었음을 확인하기 위한 방법도 생각해서 작성해보기
 * -> 어디에 출력문을 찍어서 확인할 수 있을지?
 *
 * 사용자의 요청에 따라 서블릿 객체가 생성되고, 동작하는 순서를 이해하기
 */
@WebServlet("/servlets/LifeCycleServlet")
public class LifeCycleServlet extends HttpServlet {
    private String creationTime;
    // 생성자 - 서블릿 객체가 언제 생성되는지 확인
    public LifeCycleServlet() {
        creationTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("=== 서블릿 객체 생성 ===");
        System.out.println("생성 시간: " + creationTime);
        System.out.println("========================");
    }
    @Override
    public void init() throws ServletException {
        System.out.println("\n*** init() 메서드 호출 ***");
        System.out.println("init() 메서드는 서블릿이 처음 로드될 때 한 번만 호출됩니다.");
        super.init();
        System.out.println("서블릿 초기화 완료!");
        System.out.println("**********************\n");
    }
    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        System.out.println("\n>>> service() 메서드 호출 <<<");
        System.out.println("클라이언트 IP: " + req.getRemoteAddr());
        System.out.println("요청 시간: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // HTTP 응답 설정
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>서블릿 라이프사이클 테스트</title></head>");
        out.println("<body>");
        out.println("<h1>서블릿 라이프사이클 테스트</h1>");
        out.println("<h2>서블릿 정보:</h2>");
        out.println("<ul>");
        out.println("<li>생성 시간: " + creationTime + "</li>");
        out.println("<li>현재 시간: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "</li>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
        // 부모 클래스의 service 메서드 호출
        super.service(req, res);
        System.out.println("요청 처리 완료!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>\n");
    }
    @Override
    public void destroy() {
        System.out.println("\n!!! destroy() 메서드 호출 !!!");
        System.out.println("생성 시간: " + creationTime);
        System.out.println("종료 시간: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        super.destroy();
        System.out.println("서블릿 종료 완료!");
        System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!\n");
    }
    // 추가적인 HTTP 메서드별 처리 확인
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("    -  > doGet() 메서드 호출됨");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("    -> doPost() 메서드 호출됨");
    }
}