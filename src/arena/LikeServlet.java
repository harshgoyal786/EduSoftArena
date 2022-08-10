
import arena.db.common.ConnectionFactory;

import arena.Likes;
import arena.RESOLUTION;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LikeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    static String url=null;
    private final ResultSet rs=null;
    
    private static Connection connection;
    private static Statement statement;
    
        
 
  
  
    @Override

  
     
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException 
  { 
      
  }
     
    @Override
    public void service(HttpServletRequest request,HttpServletResponse response)
    {
        /*
            This method is useful for backend processing of the like button functionality....
            This will take care about all the database operations and sending responce to the html file.....
        */
            int uid=0;
            int rid=0;
            int num_likes=0;
            Connection conn = null;
            PrintWriter out = null;
        try{
         conn = ConnectionFactory.getConnection();
          out = response.getWriter();
       

        String str_rid = (String) request.getParameter("rid");
        String str_uid = (String) request.getParameter("uid");
        try{
       	
        rid = Integer.parseInt(str_rid);
        uid = Integer.parseInt(str_uid);
    	}catch(Exception e){}
      boolean flag = Likes.checkLikesByrIduId(rid,uid);
	   if(!flag){   
       Likes like = new Likes();
       like.setlUserId(uid);
       like.setResolutionId(rid);
       try{like.addLikes(like);
       	RESOLUTION.addLikeByResId(rid);
       	num_likes = RESOLUTION.getLikesByRid(rid);
       }catch(Exception e){}
       }
       else{
       	try{num_likes = RESOLUTION.getLikesByRid(rid);}catch(Exception e){}
       }
       out.println(" "+num_likes);
        }catch(Exception e){}
        finally
      {
        try
        {
            if(out!=null)
                 out.close();
            if(conn!=null)
            conn.close();
        }catch(Exception e) {}

      }
   	}

   }
        

