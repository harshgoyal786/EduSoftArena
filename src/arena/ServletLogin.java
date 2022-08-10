import arena.db.common.ConnectionFactory;
import arena.User;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.Cookie;

public class ServletLogin extends HttpServlet
{
    private Connection con;
    private Statement stmt;
    private HttpSession session;
    private String invalid;
    public void init() 
    {
        try{
			Connection con = ConnectionFactory.getConnection();
           stmt = con.createStatement();
        }catch(Exception e){System.out.println("Unable to connect!");}
    }
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
    {
      try{
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String uname = req.getParameter("username"); 
        String passwrd= req.getParameter("password");
        User user =null;
		
		user = User.getUser(uname,passwrd);
	
		if(user!=null){
			String role=user.getRole();
			HttpSession session= req.getSession();
			session.setAttribute("user",uname);
			session.setAttribute("role",role);
			//printing session id :P
			//System.out.println(session.getId());
			
			/***cookie code start***/
			if(uname==null) uname="";
			Cookie cookie = new Cookie ("username",uname);
			cookie.setMaxAge(60 * 60);
			res.addCookie(cookie);
			/***cookie code end**/
			
		 // String role=user.getRole();
          //System.out.println("role ="+role);
          
        //  HttpSession session = request.getSession(true);
         // session.setAttribute("user",uname);
          
          //response.sendRedirect("index.jsp");
			
			
			res.sendRedirect("index.jsp?uname="+uname);         
	   }		
        else{
			invalid="Invalid Username or password";
			res.sendRedirect("login.jsp?invalid="+invalid);
			}
        pw.close();
      }catch(Exception e){e.printStackTrace();}  
    }
    public void destroy()
    {
        try{
            stmt.close();
            con.close();
        }catch(Exception e){e.printStackTrace();}
    }
}
