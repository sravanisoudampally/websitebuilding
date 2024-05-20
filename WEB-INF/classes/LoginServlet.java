import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Database connection and user authentication
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Dell/Desktop/mydatabase.db");

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                     // User found, set session attribute and display welcome page
                     HttpSession session = request.getSession();
                     session.setAttribute("username", username);
                // User found, display welcome page
                out.println("<html><head><title>Welcome</title>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
                out.println("</head><body>");
                out.println("<div class='navbar'>");
                out.println("<a href='contact'>Contact Me</a>");
                out.println("<a href='about'>About Me</a>");
                out.println("<a href='logout'>Logout</a>");
                out.println("</div>");
                out.println("<div class='container'>");
                out.println("<h1>Welcome, " + username + "!</h1>");
                out.println("</div>");
                out.println("</body></html>");
            } else {
                // User not found, display error message
                out.println("<html><head><title>Login Error</title>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
                out.println("</head><body>");
                out.println("<div class='container'>");
                out.println("<h1>Login failed. Invalid username or password.</h1>");
                out.println("<a href='login.html'>Try Again</a>");
                out.println("</div>");
                out.println("</body></html>");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>Database connection error.</h1>");
            out.println("</body></html>");
        }
    }
}
