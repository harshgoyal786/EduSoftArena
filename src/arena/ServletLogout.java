import arena.db.common.ConnectionFactory;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import javax.servlet.ServletException;
import java.io.IOException;
 
/**
 * Servlet implementation class LogoutServlet
 */

public class ServletLogout extends HttpServlet {
	     
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        HttpSession session1 = req.getSession(false);
        if(session1!= null){
			session1.invalidate();
			res.sendRedirect("index.jsp");
			//rd.forward(req, res);
            }
        }
       // response.sendRedirect("login.htm");
    }
