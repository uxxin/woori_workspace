package dev.security;

import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

// 시큐리티가 생성한 CsrfToken 값을 조회하기 위해 작성한 커스텀 필터 클래스
public class CsrfTokenLoggingFilter implements Filter {
    private Logger logger = Logger.getLogger(CsrfTokenLoggingFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");

        logger.info("CSRF TOKEN: " + csrfToken.getToken());

        chain.doFilter(request, response);
    }
}
