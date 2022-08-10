//Sign up Servlet for validations and signup if valid user. 
import arena.db.common.ConnectionFactory;
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

/***image upload imports :P **/
import java.io.File;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/***** imports for date*********************/
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/******imports for Captcha :P *************/
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import arena.User;
 
public class SignUpServlet extends HttpServlet{
	private Connection con;
    private Statement stmt;
    private HttpSession session;
    private int userid;
    private String firstname;
    private String lastname;
    private String username;
    private String emailid;
    private String password;
    private String repassword;
	private String contactno;
	private String photo;
    private String hidegree;
    private int state;
    private int score;
    private String institutename;
    //private String captcha_challenge;
	//private String captcha_responce;
    private static int count=6;
	private String result;
	boolean allOk=true;
	private String challenge;
	private String uresponse;
    public void init(){
		try{
			
			count++;
			score=-1;
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
		}catch(Exception e){System.out.println("Unable to Connect!");}
	}
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		String errorMsg="";
		System.out.println("i am called");
		res.setContentType("text/html");
		
		/*
		//System.out.println("challenge="+captcha_challenge);
		//System.out.println("response"+captcha_responce);
		*/
		
		/***********************************Image upload code here :P ************************************************************/
		Map<String, String> messages = new HashMap<String, String>();
           req.setAttribute("messages", messages);
               
            boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        
           if (isMultipart) {
             // Create a factory for disk-based file items
             FileItemFactory factory = new DiskFileItemFactory();

             // Create a new file upload handler
             ServletFileUpload upload = new ServletFileUpload(factory);
        
            try {
				
                List items = upload.parseRequest(req);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                FileItem imageFile = (FileItem) iterator.next();
                if(imageFile.getSize() > 100000){
                messages.put("image", "Image size more than 50kb not allowed");
                req.getRequestDispatcher("signup.jsp").forward(req, res);
                return ;
                }
                if (!imageFile.isFormField()) {
                String fileName = imageFile.getName();       
                String fileBase = getServletContext().getInitParameter("IMAGE_FILEBASE");
                File filePath  = new File(fileBase  + File.separator);
                              
                if (!filePath.exists()) {
                    boolean status = filePath.mkdirs();
                   }
                         
                File uploadedFile = new File(filePath + "/" + username);/***Note : i have modified it from filename to username to make photos distinguishable :P */
                imageFile.write(uploadedFile);
                // Save the below path in database
				System.out.println(  "Save Path in Database: "+getServletContext().getInitParameter("imgsd") + "user_images/" + username);
                   /******************Image upload code ends here :P ****************************************************************/        
              }
			  else{
					System.out.println("hi,420:"+imageFile.getFieldName()+" "+imageFile.getString());
					
					score=-1;
					if(imageFile.getFieldName().equals("recaptcha_challenge_field"))
					challenge = imageFile.getString();
					else if(imageFile.getFieldName().equals("recaptcha_response_field"))
					uresponse = imageFile.getString();
					else if(imageFile.getFieldName().equals("User_name"))
					username = imageFile.getString();
					else if(imageFile.getFieldName().equals("First_name"))
					firstname = imageFile.getString();
					else if(imageFile.getFieldName().equals("Last_name"))
					lastname = imageFile.getString();
					else if(imageFile.getFieldName().equals("Password"))
					password = imageFile.getString();
					else if(imageFile.getFieldName().equals("Email_id"))
					emailid = imageFile.getString();
					else if(imageFile.getFieldName().equals("Highest_degree"))
					hidegree = imageFile.getString();
					else if(imageFile.getFieldName().equals("Repassword"))
					repassword = imageFile.getString();
					else if(imageFile.getFieldName().equals("Contact_no"))
					contactno = imageFile.getString();
					else if(imageFile.getFieldName().equals("Photo"))
					photo=username;
					else if(imageFile.getFieldName().equals("State")){
					if(imageFile.getString()!=null){
					try{
					state = Integer.parseInt(imageFile.getString());
					}catch(Exception e){System.out.println("State Excep");}
					}
					}
					/*else if(imageFile.getFieldName().equals("Score")){
					if(imageFile.getString()!=null)
					try{
							score = Integer.parseInt(imageFile.getString());
					}
					catch(NullPointerException npe){
							System.out.println(npe);
					}
					else*/
					//score=-1;
					else if(imageFile.getFieldName().equals("Institute_Name"))
					institutename = imageFile.getString();
					
				  }
				   }
                } catch (FileUploadException e) {
                       e.printStackTrace();
                } catch (Exception e) {
                       e.printStackTrace();
                 }
				
	
				 /*****************************Validation starts from here***********************************************/
				
				 if (firstname==null||firstname.equals("")) {
				errorMsg+="*Please enter your first name                <br/>";
				firstname="";
				allOk=false;
				}
		
				if (lastname==null||lastname.equals("")) {
				errorMsg+="*Please enter your last name                 <br/>";
				lastname="";
				allOk=false;
				}
				if (emailid==null||emailid.equals("") || (emailid.indexOf('@') == -1)) {
				errorMsg+="*Please enter a valid email address       <br/>";
				emailid="";
				allOk=false;
				}
				if (username==null||username.equals("")) {
				errorMsg+="*Please enter a User Name                <br/>";
				username="";
				allOk=false;
				}
				if (password==null||password.equals("") ) {
				errorMsg+="*Please enter a valid password                 <br/>";
				password="";
				allOk=false;
				}
				if (repassword==null||repassword.equals("") ) {
				errorMsg+="Please enter a valid password                <br/>";
				repassword="";
				allOk=false;
				}
				if (password.equals(repassword)==false) {
				errorMsg+="Please confirm your password                <br/>";
				repassword="";
				allOk=false;
				}	
				if (hidegree==null||hidegree.equals("") ) {
				errorMsg+="*Please enter a  Highest Degree obtained                <br/>";
				hidegree="";
				allOk=false;
				}
		
				/*if (score==-1) {
				errorMsg+="*Please enter score obtained                 <br/>";
				score=-1;
				allOk=false;
				}*/
				if (institutename==null||institutename.equals("") ) {
				errorMsg+="*Please enter a  Organisation/Institute Name           <br/>";
				institutename="";
				allOk=false;
				}
				if(challenge==null||challenge.equals(""))
				{
					errorMsg+="*Please refresh the page to load captcha again   </br>";
					allOk=false;
				}
				if(uresponse==null||uresponse.equals(""))
				{
					errorMsg+="*Please enter a  Captcha code properly    <br/>";
					uresponse="";
					allOk=false;
				
				}
				/***************Validations ends here **************************************************/

				 /**************Captcha Verification Starts here *********************************************/
					if(allOk){
					String remoteAddr = req.getRemoteAddr();
					System.out.println(remoteAddr);
					ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
					reCaptcha.setPrivateKey("6Ld6oPQSAAAAAIln5NFp5SE_VzajWs_BrQUcfpiy");
					
					System.out.println(challenge);
					System.out.println(uresponse);
				
					ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
					
					}
					/********Captcha verification over ****************/
	
					/*****Checking whether the user with the username exist already*****************************************/
					try{
						System.out.println("Checking if user"+username+" exists.");
					if(User.getUserByUserName(username)!=null)
						{
							allOk=false;
							System.out.println("Sorry that user name  already registered!!!");
							errorMsg+="Sorry that user name  already registered!!!";
						}
					
					}catch(Exception e)
						{
							allOk=false;
							System.out.println("Could not fetch user details");
							errorMsg+="Could not fetch user details"+username;
							
						}
					
					/*******Checking whether the user with the username exist already ends here**********************/
					System.out.println("Get User returned"+allOk);
					if(allOk){		
								User newUser=new User();
								count=count+1;
								//newUser.setUserId(count);
								newUser.setName(username);
								newUser.setFname(firstname);
								newUser.setLname(lastname);
								newUser.setEmail(emailid);
								newUser.setPassword(password);
								newUser.setContactNo(contactno);
								newUser.setHighestDegree(hidegree);
								newUser.setPhoto(username);
								newUser.setState(state);
								newUser.setScore(score);
								newUser.setInstituteName(institutename);
								newUser.setRole("student");
								/***************for date *************/
								DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								//get current date time with Date()
							//	Date date = new Date();
								
								try
								{
									//Date d = dateFormat.format(date);
									newUser.setRegistrationDate(new Timestamp(new java.util.Date().getTime()));
									System.out.println("Date "+dateFormat.format(new Timestamp(new java.util.Date().getTime())));
								}
								catch(Exception e)
								{
									System.out.println("Error parsing date");
									result="Error parsing date";
									res.sendRedirect("login.jsp?myparam="+result);
								}
							
								
								//if(newUser.addUser(newUser)==1){
								try{
									System.out.println("Adding user to database "+username);
									newUser.addUser(newUser);
								}
								catch(Exception e)
								{
									System.out.println("Excedption"+e.getMessage());
									result="Error adding user"+username;
									res.sendRedirect("login.jsp?myparam="+result);
								}
								result="Congratulation!!!<br/>"+username+" you are sucessfully registered.";
								res.sendRedirect("login.jsp?myparam="+result);
								//}
								//else{
								// errorMsg+="Sorry that user name  already registered!!!";
								// res.sendRedirect("signup.jsp?errors="+errorMsg);
								//}
					}
					else{
								res.sendRedirect("signup.jsp?errors="+errorMsg);
								allOk=true;
					}	 
					
                 
            }
            
        }

        public void destroy()
        {
            try{
            if(con!=null)
                con.close();
            if(stmt!=null)
                stmt.close();
            }catch(Exception e){}
        }
		
		
            
}
