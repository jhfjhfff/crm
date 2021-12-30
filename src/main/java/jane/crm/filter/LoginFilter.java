package jane.crm.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();

        if (requestURI.contains("login")){
            chain.doFilter(req,resp);
            return;
        }

        if (request.getSession().getAttribute("user") != null){
            chain.doFilter(req,resp);
            return;
        }

        response.sendRedirect(contextPath + "/login.jsp");
    }

    @Override
    public void destroy() {

    }
}
