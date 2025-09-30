package dev.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*") // 모든 요청에 적용
public class EncodingFilter implements Filter {

    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
        System.out.println("[EncodingFilter] init 실행됨, encoding = " + encoding);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);

        System.out.println("[EncodingFilter] 요청 처리 전 - encoding 적용 완료");

        chain.doFilter(request, response);

        System.out.println("[EncodingFilter] 요청 처리 후 - 필터 통과 완료");
    }

    @Override
    public void destroy() {
        System.out.println("[EncodingFilter] destroy 실행됨");
    }
}
