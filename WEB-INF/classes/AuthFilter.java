import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String loginURI = req.getContextPath() + "/login";
        String registerURI = req.getContextPath() + "/register";
        String loginHTML = req.getContextPath() + "/login.html";
        String registerHTML = req.getContextPath() + "/register.html";

        boolean loggedIn = session != null && session.getAttribute("username") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI) || req.getRequestURI().equals(loginHTML);
        boolean registerRequest = req.getRequestURI().equals(registerURI) || req.getRequestURI().equals(registerHTML);

        if (loggedIn || loginRequest || registerRequest) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginHTML);
        }
    }

    public void destroy() {
        // Cleanup code, if needed
    }
}
