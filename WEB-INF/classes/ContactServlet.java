import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Contact Me</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
        out.println("</head><body>");
        out.println("<div class='navbar'>");
        out.println("<a href='contact'>Contact Me</a>");
        out.println("<a href='about'>About Me</a>");
        out.println("<a href='logout'>Logout</a>");   
             out.println("</div>");
        out.println("<div class='container'>");
        out.println("<h1>Contact Me</h1>");
        out.println("<p>You can contact me at example@example.com.</p>");
        out.println("</div>");
        out.println("</body></html>");
    }
}
