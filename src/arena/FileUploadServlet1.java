
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

/**
 *
 * @author
 */
@MultipartConfig
public class FileUploadServlet1 extends HttpServlet {

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

    final String path = "seaBattle1";
    final Part filePart = request.getPart("file");
    final String fileName = getFileName(filePart);

   
     System.out.println("fileName == "+fileName);
    String ext = FilenameUtils.getExtension(fileName);
     System.out.println("fileextention  == "+ext);
     OutputStream out = null;
    InputStream filecontent = null;
    final PrintWriter writer = response.getWriter();
     String message = null;
     String strProjectDir = "";
     strProjectDir = request.getRealPath("seaBattle1");
     File ProjectDir = new File(strProjectDir);
     if(! ProjectDir.exists())
        {
            ProjectDir.mkdir();
        } 
     if(ext.equals("c"))
        {

    
        try {
            out = new FileOutputStream(new File(strProjectDir+ File.separator
                    +fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
          
            message = "Your file is uploaded Successfully. Thank you!";
            
        } catch (FileNotFoundException fne) {
    		
            message = "You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.";

          
        }
    }
else{
    message = "Please check File extention and Try Again!";
}
    
   response.sendRedirect("executeSB.jsp?message="+message);
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

    
