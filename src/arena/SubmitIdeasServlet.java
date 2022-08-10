import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import arena.User;
import arena.Idea;


/**
 *
 * @author User
 */
public class SubmitIdeasServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Idea i=new Idea();
        User u=new User();
        User unew=null;
        String message = null;
        HttpSession session= request.getSession(false);
        String title=request.getParameter("title");
        String desc=request.getParameter("description");
         String uname=(String)session.getAttribute("user");
         try{
         unew=User.getUserByUserName(uname);
         }catch(Exception e){}
         if(unew!=null){
         int uid=unew.getUserId();
         
         
       // int iid=i.getIdeaId();
        //int possibScore=i.getPossibleScore();
        //int noofsub=i.getNoOfSubmissions();
       // Date subdate=i.getDateOfSubmission();
        System.out.println(uid);
         
                                System.out.println(title);
                                        System.out.println(desc);
                                               


        i.setDateOfSubmission(new java.sql.Date(new java.util.Date().getTime()));
        i.setNoOfSubmissions(10);
        i.setPossibleScore(10);
        i.setDescription(desc);
        i.setTitle(title);
        i.setUserId(uid);
        
	 try{
	 
        Idea.addIdea(i);
        message = "Thank You.. Your idea is Submited.";
       
        }
        catch(Exception e){
       	message = "Please try again...";
        }
	RequestDispatcher rd = request.getRequestDispatcher("ideasolutionupload.jsp?message1="+message);
	rd.forward(request,response);
        out.close();
       //response.sendRedirect("ideauploadpage.htm");
    
}
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
}
   
