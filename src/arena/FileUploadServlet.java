


import org.apache.commons.io.FilenameUtils;//to know file extention
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import arena.SolutionToIdeas;
import arena.User;
import java.sql.Date;

/**
 *
 * @author kumar
 */
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
    // Create path components to save the file
    //final String path = request.getParameter("destination");
  //  final String path = "/home/kumar/apache-tomcat-7.0.54/webapps/ROOT/solutions";
    
    final Part filePart = request.getPart("file");
    
    final String fileName = getFileName(filePart);
   System.out.println("Part "+filePart);

    String login_user = (String)request.getParameter("login_user");
    
    String str_id = (String) request.getParameter("idea_id");
    
    int idea_id =0;
    int user_id =0;
     User user = new User();
     SolutionToIdeas solution = new SolutionToIdeas();
   try{ 
       
    user = User.getUserByUserName(login_user);
    user_id= user.getUserId();
	}catch(Exception e){e.printStackTrace();}
    
    try{
        idea_id = Integer.parseInt(str_id);
    }
    catch(Exception e){}
    
     System.out.println("fileName == "+fileName);
     System.out.println("login_user = "+login_user);
     System.out.println("idea_id = "+idea_id);
     
     String ext = FilenameUtils.getExtension(fileName);
     
     System.out.println("fileextention  == "+ext);
     
     OutputStream out = null;
    InputStream filecontent = null;
    final PrintWriter writer = response.getWriter();
     String message = null;
    // System.out.println("file separator "+File.separator);
     String strProjectDir = "";
     strProjectDir = request.getRealPath("solutions/"+login_user);
     File ProjectDir = new File(strProjectDir);
     if(! ProjectDir.exists())
        {
            ProjectDir.mkdir();
        } 
     if(ext.equals("c"))
        {
			solution.setSolutionFileName(str_id+"."+ext);
			solution.setUserId(user_id);
			solution.setIdeaId(idea_id);
			solution.setSubmittedDate(new java.sql.Date(new java.util.Date().getTime()));
			solution.setDocumentationFileName("");
			solution.setDownvote(0);
			solution.setUpvote(0);
			try{
			SolutionToIdeas.submitSolution(solution);
			}catch(Exception e){e.printStackTrace();}
    
        try {
            out = new FileOutputStream(new File(strProjectDir+ File.separator
                    +str_id+".c"));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
           // writer.println("New file " + fileName + " created at " + path);
            message = "Your file is uploaded Successfully. Thank you!";
            
        } catch (FileNotFoundException fne) {
    		
            message = "You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.";

           /* writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());*/
        }
    }
else if(fileName==""){
    message = "Please select file!";
}
else
	message = "Chose correct File.";
    //request.setAttribute("message",message);
    RequestDispatcher rd = request.getRequestDispatcher("ideasolutionupload.jsp?message="+message);
    rd.forward(request,response);
    out.close();
}




private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
   
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
}
    }

    
