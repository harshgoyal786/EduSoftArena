
package arena;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 */
//package arena.db.common.user;
//import arena.ConnectionFactory;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Timestamp;
//import javax.swing.JOptionPane;
import arena.db.common.ConnectionFactory;

/*The user class is used the create a user object to access the data stored in the database. */



 public class User {
   
   private String strUserName,strFirstName,strLastName,strEmailId,strContactNo, strHighestDegree,strInstituteName,strPhoto,strRole,strIPAddress , Password , profile ,  GameFileName;
   private int iUserId,iState,iRoleId=0,iLangId,iApproved,iShowMobile,iShowPhone,iShowEmail,iShowPhoto,iShowAddress,iShowOrganisation,  iScore ;
   private float iGameScore ;
   private Timestamp dRegistrationDate;
   
   private static String USERNAME="username";
   private static String TABLENAME="login";
   private static String UID="UID";
   private static String FIRSTNAME="firstname";
   private static String LASTNAME="lastname";
   private static String EMAILID="email";
   private static String PASSWORD="Password";
   private static String CONTACTNO="mobile";
   private static String HIGHESTDEGREE="qualification";
   private static String STATE="state";
   private static String INSTITUTENAME="organisation";
   private static String REGISTRATIONDATE="reg_on";
   private static String USERID="userid";
   private static String SCORE="Score";
   private static String PHOTO="user_photo";
   private static String TABLE="ekshiksha_users";
   private static String ROLE="role";
   private static String ROLEID="roleid";
   private static String LANGID="langid";
   private static String SHOWMOBILE ="showmobile";
   private static String SHOWPHONE ="showphone";
   private static String SHOWEMAIL ="showemail";
   private static String SHOWADDRESS ="showaddress";
   private static String SHOWPHOTO ="showphoto";
   private static String SHOWORGANISTION ="showorganisation";
   private static String APPROVED ="approved";
   private static String IPADDRESS ="ip_address";
   private static String PROFILE="profile";
   private static String GAMESCORE="GameScore";
   private static String GAMEFILENAME="GameFileName";

     /**
     * @return the GameFileName
     */
    public String getGameFileName() {
        return GameFileName;
    }

    /**
     * @param GameFileName the GameFileName to set
     */
    public void setGameFileName(String GameFileName) {
        this.GameFileName = GameFileName;
    }

    /**
     * @return the iGameScore
     */
    public float getiGameScore() {
        return iGameScore;
    }

    /**
     * @param iGameScore the iGameScore to set
     */
    public void setiGameScore(float iGameScore) {
        this.iGameScore = iGameScore;
    }



  
  
   /*The getter/setter function definitions are as follows.*/
   
   public String getName()
   {
     return this.strUserName;
   }
   public String getFname()
   {
     return this.strFirstName;
   }
   public String getLname()
   {
     return this.strLastName;
   }
   public String getEmail()
   {
     return this.strEmailId;
   }
   public String getContactNo()
   {
     return this.strContactNo;
   }
   public String getHighestDegree()
   {
     return this.strHighestDegree;
   }
   public String getInstituteName()
   {
     return this.strInstituteName;
   }
   public int getUserId()
   {
     return this.iUserId;
   }
   public Timestamp getRegistrationDate()
   {
     return this.dRegistrationDate;
   }
   public String getRole()
   {
       int r = getIRoleId();
       if(r==0)
           return "student";
       else
           return "mentor";

   }
   public String getPhoto()
   {
       return this.strPhoto;
   }
   public void setName(String arg)
   {
     strUserName=arg;
   }
   public void setFname(String arg)
   {
     strFirstName=arg;
   }
   public void setLname(String arg)
   {
     strLastName=arg;
   }
   public void setEmail(String arg)
   {
     strEmailId=arg;
   }
    public void setContactNo(String arg)
   {
     strContactNo=arg;
   }
    public void setHighestDegree(String arg)
   {
     strHighestDegree=arg;
   }
   
   public void setInstituteName(String arg)
   {
     strInstituteName=arg;
   }
   public void setUserId(int arg)
   {
     iUserId=arg;
   }
  
   public void setRegistrationDate(Timestamp arg)
   {
      dRegistrationDate=arg;
   }
   public void setRole(String role)
   {
       strRole=role;
   }
   public void setPhoto(String photo)
   {
       strPhoto=photo;
   }
   
   public int getIShowMobile() {
        return iShowMobile;
    }

    public void setIShowMobile(int iShowMobile) {
        this.iShowMobile = iShowMobile;
    }

   public String getStrIPAddress() {
        return strIPAddress;
    }

    public void setStrIPAddress(String strIPAddress) {
        strIPAddress = "1.1.1.1";
        this.strIPAddress = strIPAddress;
    }

    public int getState() {
        return iState;
    }

    public void setState(int iState) {
        this.iState = iState;
    }

    public int getIRoleId() {
        return iRoleId;
    }

    public void setIRoleId(int iRoleId) {
        if(iRoleId==0){
        this.iRoleId = iRoleId;
        setRole("student");
        }
        else
           {
        this.iRoleId = iRoleId;
        setRole("mentor");
        }
    }

    public int getILangId() {
        return iLangId;
    }

    public void setILangId(int iLangId) {
        this.iLangId = iLangId;
    }

    public int getIApproved() {
        return iApproved;
    }

    public void setIApproved(int iApproved) {
        this.iApproved = iApproved;
    }

    public int getIShowPhone() {
        return iShowPhone;
    }

    public void setIShowPhone(int iShowPhone) {
        this.iShowPhone = iShowPhone;
    }

    public int getIShowEmail() {
        return iShowEmail;
    }

    public void setIShowEmail(int iShowEmail) {
        this.iShowEmail = iShowEmail;
    }

    public int getIShowPhoto() {
        return iShowPhoto;
    }

    public void setIShowPhoto(int iShowPhoto) {
        this.iShowPhoto = iShowPhoto;
    }

    public int getIShowAddress() {
        return iShowAddress;
    }

    public void setIShowAddress(int iShowAddress) {
        this.iShowAddress = iShowAddress;
    }

    public int getIShowOrganisation() {
        return iShowOrganisation;
    }

    public void setIShowOrganisation(int iShowOrganisation) {
        this.iShowOrganisation = iShowOrganisation;
    }

     /**
     * @return the iPassword
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param iPassword the iPassword to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the iScore
     */
    public int getScore() {
        return iScore;
    }

    /**
     * @param iScore the iScore to set
     */
    public void setScore(int iScore) {
        this.iScore = iScore;
    }

      /**
     * @return the profile
     */
    public String getProfile() {
        return profile;
    }

    /**
     * @param profile the profile to set
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    
  /*The following function is used to add user data to the database via a user object.*/
   public  void addUser(User i) throws SQLException
   {
   
       Connection con=null;
        ResultSet res3 = null;
    PreparedStatement st = null;
    PreparedStatement st1 = null;
    PreparedStatement st2 = null;
   try
   {
       
        con = (Connection) ConnectionFactory.getConnection();
        st = (PreparedStatement) con.prepareStatement("Insert into "+TABLE+" ( "+USERID+","+FIRSTNAME+","+LASTNAME+","+USERNAME+","+EMAILID+","+IPADDRESS+","+CONTACTNO+","+HIGHESTDEGREE+","+PHOTO+","+STATE+","+INSTITUTENAME+","+REGISTRATIONDATE+","+ROLEID+" , "+PROFILE+ ") values ( ? , ? ,? ,? , ? , ? , ? , ? , ? ,? ,? , ? ,? ,? )");
        st1 = (PreparedStatement) con.prepareStatement("Insert into "+TABLENAME+" ("+UID+" , "+PASSWORD+" ) values  ( ? , ? )");
        st2=(PreparedStatement) con.prepareStatement("Select max("+USERID+") as max from "+TABLE );
        st.setInt(1, i.getUserId());
        st.setString(2, i.getFname());
        st.setString(3,i.getLname());
        st.setString(4,i.getName());
        st.setString(5,i.getEmail());
        st.setString(6,"1.1.1.1");
        st.setString(7,i.getContactNo());
        st.setString(8,i.getHighestDegree());
        st.setString(9,i.getPhoto());
        st.setInt(10,i.getState());
        
        st.setString(11,i.getInstituteName());
        st.setTimestamp(12,i.getRegistrationDate());
        st.setInt(13,0);
        st.setString(14,"student");
        
        int res = st.executeUpdate();
        res3=st2.executeQuery();
        res3.next();
      
        st1.setInt(1,res3.getInt("max"));
        st1.setString(2 , i.getPassword());


      //Execute the sql query to insert values into the database
        
        int res2 = st1.executeUpdate();

        if(res>0 && res2>0)
        {
//            JOptionPane.showMessageDialog(null ,"Data added successfully ");
			System.out.println("Data added successfully ");
        }
        else
        {
//            JOptionPane.showMessageDialog(null , "Sorry !! Couldnt add your data ");
			System.out.println("Sorry !! Couldnt add your data ");
        }
    
   }
       catch(SQLException e)
       {
               // JOptionPane.showMessageDialog(null,e.toString());
               System.out.println("Error"+e.getMessage());
       }
      catch(Exception ob)
      {
            //JOptionPane.showMessageDialog(null,ob.toString());
            System.out.println("Error"+ob.getMessage());
      }
     finally
    {
       try
       {
           if(con!=null)
         con.close();
        if(res3!=null)
            res3.close();
        if(st!=null)
           st.close();
         if(st1!=null)
           st1.close();  
           if(st2!=null)
           st2.close();
       }catch(SQLException e) {}
    }
   
  }

  
  /*The following function is used to dispaly a list of all users in the database. It returns an array of user objects*/

  public static ArrayList<User> getAllUsers() throws SQLException//, NoUserFoundException,
  {
    ArrayList<User> ilist = new ArrayList<User>();
    Connection conn=null;
    PreparedStatement st = null;
    ResultSet rs = null;
   
   try
   {
       conn = (Connection) ConnectionFactory.getConnection();
       st = (PreparedStatement) conn.prepareStatement("Select * from "+TABLE+" , "+TABLENAME+" where "+TABLE+"."+USERID+" = "+TABLENAME+"."+UID);
       rs = st.executeQuery();
      
        if(!rs.next())
        {
                           
                 try
                {
                        if(conn!=null)
                             conn.close();
                        if(rs!=null)
                             rs.close();
                        if(st!=null)
                             st.close();
                }catch(SQLException e) {}

            //throw new NoUserFoundException("No Idea Found With the matching the Submitted date");
            return null;
             }

       //Insert the data extracted into an array of user objects
        do
        {
       
           User user=new User();

           user.setName(rs.getString(USERNAME));
           user.setFname(rs.getString(FIRSTNAME));
           user.setLname(rs.getString(LASTNAME));
           user.setEmail(rs.getString(EMAILID));
           user.setContactNo(rs.getString(CONTACTNO));
           user.setPassword(rs.getString(PASSWORD));  
           user.setHighestDegree(rs.getString(HIGHESTDEGREE));
           user.setState(rs.getInt(STATE));
           user.setInstituteName(rs.getString(INSTITUTENAME));
           user.setUserId(rs.getInt(USERID));
           user.setScore(rs.getInt(SCORE));
           user.setRegistrationDate(rs.getTimestamp(REGISTRATIONDATE));
           user.setPhoto(rs.getString(PHOTO));
           user.setIRoleId(rs.getInt(ROLEID));
           user.setStrIPAddress(rs.getString(IPADDRESS));
           
           ilist.add(user);

        }while(rs.next());

     }
    /*   catch(NoUserFoundException e)
        {
                JOptionPane.showMessageDialog(null,e.getMessage());
        } */
       catch(SQLException e)
        {
              //JOptionPane.showMessageDialog(null,e.toString());
              System.out.println("Error"+e.getMessage());
        }
      catch(Exception ob)
      {
//             JOptionPane.showMessageDialog(null,ob.toString());
			System.out.println("Error"+ob.getMessage());
        }
     finally
     {
           try
          {
             if(conn!=null)
                 conn.close();
            if(rs!=null)
                  rs.close();
            if(st!=null)
                   st.close();
         }catch(SQLException e) {}
     }

      return ilist;
 
  }


   /*The following function is used to return a user object containing details of the user
     with a particular username and password.*/
 
public  static User getUser(String uname, String passwrd) throws SQLException //,NoUserFoundException
{
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement st = null;
    User user=null;
   
     try
     {
        conn = (Connection) ConnectionFactory.getConnection();
        st = (PreparedStatement) conn.prepareStatement("Select * from "+TABLE+" , "+TABLENAME+" where "+TABLE+"."+USERNAME+" = '" + uname+"' and "+TABLENAME+"."+PASSWORD+" = '"+passwrd+"' and "+TABLENAME+"."+UID+" = "+TABLE+"."+USERID);
        rs = st.executeQuery();
     //create a connection
     
     
     //execute sql query to select the user details with the given username and password 
      
        if(rs.next())
         {
            user= new User();
            user.setName(rs.getString(USERNAME));
                user.setFname(rs.getString(FIRSTNAME));
                user.setLname(rs.getString(LASTNAME));
                user.setEmail(rs.getString(EMAILID));
                user.setContactNo(rs.getString(CONTACTNO));     
               user.setPassword(rs.getString(PASSWORD));
                user.setHighestDegree(rs.getString(HIGHESTDEGREE));
                user.setState(rs.getInt(STATE));
                user.setInstituteName(rs.getString(INSTITUTENAME));
                user.setUserId(rs.getInt(USERID));
                user.setScore(rs.getInt(SCORE));
                user.setRegistrationDate(rs.getTimestamp(REGISTRATIONDATE));
                user.setPhoto(rs.getString(PHOTO));
                user.setIRoleId(rs.getInt(ROLEID));//If no user is
         }
        else
        {

           try
          {
             if(conn!=null)
                 conn.close();
            if(rs!=null)
                  rs.close();
            if(st!=null)
                   st.close();
         }catch(SQLException e) {}

          //  throw new NoUserFoundException("No user found");
            return null;
        }
   
    }
      catch(SQLException e)
    {
        //JOptionPane.showMessageDialog(null,e.toString());
        System.out.println("Error"+e.getMessage());
    }
      catch(Exception ob)

    {

//        JOptionPane.showMessageDialog(null,ob.toString());
		System.out.println("Error"+ob.getMessage());
    }
    finally
    {
       try
       {
           if(conn!=null)
         conn.close();
        if(rs!=null)
            rs.close();
        if(st!=null)
            st.close();
       }catch(SQLException e) {}
    }
      return user;
   
  }   
  
  
  public  static User getUserByUserId(int iUserId) throws SQLException//,NoUserFoundException
  {
    Connection conn = null;
    User user=null;
    PreparedStatement st = null;
    ResultSet rs = null;
   
     try
     {
        conn = (Connection) ConnectionFactory.getConnection();
        st = (PreparedStatement) conn.prepareStatement("Select * from "+TABLE+" , "+TABLENAME+" where "+TABLE+"."+USERID+" = " +TABLENAME+"."+UID+" AND "+TABLENAME+"."+UID+" = "+iUserId);
        rs = st.executeQuery();
     //create a connection
     
     
     //execute sql query to select the user details with the given UserID
      
        if(rs.next())
         {
                user = new User();
                user.setName(rs.getString(USERNAME));
                user.setFname(rs.getString(FIRSTNAME));
                user.setLname(rs.getString(LASTNAME));
                user.setEmail(rs.getString(EMAILID));
                user.setContactNo(rs.getString(CONTACTNO));
                user.setPassword(rs.getString(PASSWORD));  
                user.setHighestDegree(rs.getString(HIGHESTDEGREE));
                user.setState(rs.getInt(STATE));
                user.setInstituteName(rs.getString(INSTITUTENAME));
                user.setUserId(rs.getInt(USERID));
                user.setScore(rs.getInt(SCORE));
                user.setRegistrationDate(rs.getTimestamp(REGISTRATIONDATE));
                user.setPhoto(rs.getString(PHOTO));
                user.setIRoleId(rs.getInt(ROLEID));
                user.setStrIPAddress(rs.getString(IPADDRESS));
         }
        else
        {

           try
          {
             if(conn!=null)
                 conn.close();
            if(rs!=null)
                  rs.close();
            if(st!=null)
                   st.close();
         }catch(SQLException e) {}

            //  throw new NoUserFoundException("No user found");
            return null;
        }
   
    }
      catch(SQLException e)
    {
//        JOptionPane.showMessageDialog(null,e.toString());
		System.out.println("Error"+e.getMessage());
    }
      catch(Exception ob)

    {

  //      JOptionPane.showMessageDialog(null,ob.toString());
		  System.out.println("Error"+ob.getMessage());
    }
      finally
     {
           try
          {
             if(conn!=null)
                 conn.close();
            if(rs!=null)
                  rs.close();
            if(st!=null)
                   st.close();
         }catch(SQLException e) {}
     }
      return user;
   
  }
  
  public static User getUserByUserName(String uname) throws SQLException {
      Connection con= null;
      User user = null;
      PreparedStatement st = null;
      ResultSet rs = null;
      
      try {
          con = (Connection) ConnectionFactory.getConnection();
          st = (PreparedStatement) con.prepareStatement("Select " + USERID +" from " + TABLE + " where " + USERNAME + " = '" + uname + "' ");
          rs = st.executeQuery();

          int uid;
          
         if(rs.next())
         {
             uid = rs.getInt("userid");
             System.out.println(uid);
             user= new User();
              user = getUserByUserId(uid);
          }
          else {

           try
          {
             if(con!=null)
                 con.close();
            if(rs!=null)
                  rs.close();
            if(st!=null)
                   st.close();
         }catch(SQLException e) {}

              //throw new NoUserFoundException("No user found");
              return null;
          }
        
      }
      catch(SQLException e){
   //     JOptionPane.showMessageDialog(null,e.toString());
		   System.out.println("Error"+e.getMessage());
      }
      catch(Exception ob){
  //      JOptionPane.showMessageDialog(null,ob.toString());
  		System.out.println("Error"+ob.getMessage());
      }
       finally
     {
           try
          {
             if(con!=null)
                 con.close();
            if(rs!=null)
                  rs.close();
            if(st!=null)
                   st.close();
         }catch(SQLException e) {}
     }
      return user;
      
  }

  public  static Boolean userExist(String uname) throws SQLException // ,  NoUserFoundException,
  {
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

     try
     {
        conn = (Connection) ConnectionFactory.getConnection();
        st = (PreparedStatement) conn.prepareStatement("Select * from "+TABLE+" where "+USERNAME+" = '" + uname+"'");
        rs = st.executeQuery();
     //create a connection


     //execute sql query to select the user details with the given username and password

        if(rs.next())
         {

           try
          {
             if(conn!=null)
                 conn.close();
            if(rs!=null)
                  rs.close();
            if(st!=null)
                   st.close();
         }catch(SQLException e) {}




            return true;
         
         }
        else
        {

           try
          {
             if(conn!=null)
                 conn.close();
            if(rs!=null)
                  rs.close();
            if(st!=null)
                   st.close();
         }catch(SQLException e) {}

            return false;
        }

    }
      catch(SQLException e)
    {
        //JOptionPane.showMessageDialog(null,e.toString());
        System.out.println("Error"+e.getMessage());
    }
      catch(Exception ob)

    {

        //JOptionPane.showMessageDialog(null,ob.toString());
        System.out.println("Error"+ob.getMessage());
    }
    finally
     {
           try
          {
             if(conn!=null)
                 conn.close();
            if(rs!=null)
                  rs.close();
            if(st!=null)
                   st.close();
         }catch(SQLException e) {}
     }
      return false;

  }

  public static void updateGameScore(float score , int UserId) throws SQLException
   {

       Connection con=null;
       ResultSet rs = null;
       Statement st = null;

   try
   {

        con = (Connection) ConnectionFactory.getConnection();
        st = (Statement) con.createStatement();
        int res = st.executeUpdate("update "+TABLENAME+" set " +GAMESCORE+ " = "+score + " where "+UID+ " = "+UserId);


        if(res>0)
        {
       //     JOptionPane.showMessageDialog(null ,"Data added successfully ");
            System.out.println("updated game score ");
        }
        else
        {
       //     JOptionPane.showMessageDialog(null , "Sorry !! Couldnt add your data ");
            System.out.println("could not update your game score ");
        }

   }
       catch(SQLException e)
       {
      //          JOptionPane.showMessageDialog(null,e.toString());
            System.out.println("Error"+e.getMessage());
       }
      catch(Exception ob)
      {
        //    JOptionPane.showMessageDialog(null,ob.toString());
           System.out.println("Error"+ob.getMessage());
      }
     finally
    {
       try
       {
           if(con!=null)
         con.close();
        if(rs!=null)
            rs.close();
        if(st!=null)
            st.close();
       }catch(SQLException e) {}
    }

  }

 public  void setGameFileName(String file , int UserId) throws SQLException
   {

       Connection con=null;
       ResultSet rs = null;
       Statement st = null;

   try
   {

        con = (Connection) ConnectionFactory.getConnection();
        st = (Statement) con.createStatement();
        int res = st.executeUpdate("update "+TABLENAME+" set " +GAMEFILENAME+ " = "+file + " where "+UID+ " = "+UserId);


        if(res>0)
        {
       //     JOptionPane.showMessageDialog(null ,"Data added successfully ");
            System.out.println("updated game file ");
        }
        else
        {
       //     JOptionPane.showMessageDialog(null , "Sorry !! Couldnt add your data ");
            System.out.println("could not update your game file ");
        }

   }
       catch(SQLException e)
       {
      //          JOptionPane.showMessageDialog(null,e.toString());
            System.out.println("Error"+e.getMessage());
       }
      catch(Exception ob)
      {
        //    JOptionPane.showMessageDialog(null,ob.toString());
           System.out.println("Error"+ob.getMessage());
      }
     finally
    {
       try
       {
           if(con!=null)
         con.close();
        if(rs!=null)
            rs.close();
        if(st!=null)
            st.close();
       }catch(SQLException e) {}
    }

  }


  
   
 
} 
