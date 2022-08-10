/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;
import arena.db.common.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */

class NoSolutionFoundException extends Exception
{
   private String msg = null;
    NoSolutionFoundException()
    {
        super();
    }
    NoSolutionFoundException(String msg)
    {
        super(msg);
        this.msg = msg;
    }
    @Override
    public String toString() {
        return msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

}


public class SolutionToIdeas
{
    /**
     * @return the upvote
     */
    private int iUserId , iSolutionId , iIdeaId ;
    private int iUpvote , iDownvote ;
    private String strSolutionFileName , strDocumentationFileName ;
    private Date dSubmittedDate ;
    
    private static String USERID="USER_ID";
    private static String SOLUTIONID="SOLUTION_ID";
    private static String IDEAID="IDEA_ID";
    private static String UPVOTE="UPVOTE";
    private static String DOWNVOTE="DOWNVOTE";
    private static String SOLUTIONFILENAME="SOLUTION_FILE_NAME";
    private static String DOCUMENTATIONFILENAME="DOCUMENTATION_FILE_NAME";
    private static String SUBMITTEDDATE="SUBMISSION_DATE";
    private static String TABLENAME="SOLUTION_TO_IDEAS";
    public int getUpvote() {
        return iUpvote;
    }

    /**
     * @param aUpvote the upvote to set
     */
    public void setUpvote(int aUpvote) {
        iUpvote = aUpvote;
    }

    /**
     * @return the downvote
     */
    public int getDownvote() {
        return iDownvote;
    }

    /**
     * @param aDownvote the downvote to set
     */
    public void setDownvote(int aDownvote) {
        iDownvote = aDownvote;
    }
    

    /**
     * @return the solutionId
     */
    public int getSolutionId() {
        return iSolutionId;
    }

    /**
     * @param solutionId the solutionId to set
     */
    public void setSolutionId(int solutionId) {
        this.iSolutionId = solutionId;
    }

    /**
     * @return the solutionFileName
     */
    public String getSolutionFileName() {
        return strSolutionFileName;
    }

    /**
     * @param solutionFileName the solutionFileName to set
     */
    public void setSolutionFileName(String solutionFileName) {
        this.strSolutionFileName = solutionFileName;
    }

    /**
     * @return the documentationFileName
     */
    public String getDocumentationFileName() {
        return strDocumentationFileName;
    }

    /**
     * @param documentationFileName the documentationFileName to set
     */
    public void setDocumentationFileName(String documentationFileName) {
        this.strDocumentationFileName = documentationFileName;
    }

    /**
     * @return the submittedDate
     */
    public Date getSubmittedDate() {
        return dSubmittedDate;
    }

    /**
     * @param submittedDate the submittedDate to set
     */
    public int getUserId(){
       return iUserId;
    }
    
    public void setUserId(int userid){
        this.iUserId=userid;
    }
    
    public void setIdeaId(int ideaid){
        this.iIdeaId=ideaid;
    }
    
    public int getIdeaId(){
        return iIdeaId;
    }
    
    public void setSubmittedDate(Date submittedDate) {
        this.dSubmittedDate = submittedDate;
    }
    
   public  static void submitSolution(SolutionToIdeas i) throws SQLException
   {
       Connection con=null;
       try
       {
          con = (Connection)ConnectionFactory.getConnection();
          PreparedStatement st = (PreparedStatement) con.prepareStatement("Insert into "+TABLENAME+" ( "+USERID+","+SOLUTIONID+","+IDEAID+","+SOLUTIONFILENAME+","+DOCUMENTATIONFILENAME+","+SUBMITTEDDATE+","+UPVOTE+","+DOWNVOTE+" ) values ( ? , ? ,? ,? , ? , ? , ?, ? )");
          st.setInt(1, i.getUserId());
          st.setInt(2, i.getSolutionId());
          st.setInt(3, i.getIdeaId());
          st.setDate(6,(java.sql.Date) i.getSubmittedDate());
         //st.setNull(6, Types.INTEGER);
        if(i.getSolutionFileName().equals(""))
       {
             st.setNull(4, Types.INTEGER);
       }
        else
        {
              st.setString(4, i.getSolutionFileName());
        }
        if(i.getDocumentationFileName().equals(""))
       {
             st.setNull(5, Types.VARCHAR);
       }
        else
        {
              st.setString(5, i.getDocumentationFileName());
        }
        if(i.getUpvote()==0)
       {
             st.setNull(7, Types.INTEGER);
       }
        else
        {
              st.setInt(7, i.getUpvote());
        }
        if(i.getDownvote()==0)
       {
             st.setNull(8, Types.INTEGER);
       }
        else
        {
              st.setInt(8, i.getDownvote());
        }


        int res = st.executeUpdate();

        if(res>0)
        {
             //JOptionPane.showMessageDialog(null ,"Data added successfully ");
             System.out.println("Data added successfully.");
        }
        else
        {
             //JOptionPane.showMessageDialog(null , "Sorry !! Couldnt add your data ");
             System.out.println("Sorry !! Couldnt add your data ");
        }
    }
       catch(SQLException e)
       {
                //JOptionPane.showMessageDialog(null,e.toString());
                System.out.println(e.toString());
       }
      catch(Exception ob)
      {
		  
             //JOptionPane.showMessageDialog(null,ob.toString());
             System.out.println(ob.toString());
      }
    con.close();
}
    public static ArrayList<SolutionToIdeas> getSolutionByUserName(String username) throws SQLException
 {
        ArrayList<SolutionToIdeas> ilist = new ArrayList<SolutionToIdeas>();
        Connection con=null;
        try
        {
            con = (Connection) ConnectionFactory.getConnection();
            User user=User.getUserByUserName(username);
            
            PreparedStatement st = (PreparedStatement) con.prepareStatement("Select * from "+ TABLENAME+" where "+USERID+"="+user.getUserId());
            ResultSet rs = st.executeQuery();

             if(!rs.next())
             {
                throw new NoSolutionFoundException("No Idea Found With the matching the Submitted date");
                // return null;
             }

             do
             {
                 SolutionToIdeas i = new SolutionToIdeas();
                 i.setUserId(rs.getInt(USERID));
                 i.setIdeaId(rs.getInt(IDEAID));
                 i.setSolutionId(rs.getInt(SOLUTIONID));
                 i.setUpvote(rs.getInt(UPVOTE));
                 i.setSubmittedDate(rs.getDate(SUBMITTEDDATE));
                 i.setDocumentationFileName(rs.getString(DOCUMENTATIONFILENAME));
                 i.setDownvote(rs.getInt(DOWNVOTE));
                 i.setSolutionFileName(rs.getString(SOLUTIONFILENAME));
                 ilist.add(i);
             }while(rs.next());

            return ilist;
        }
      /*  catch(NoSolutionFoundException e)
        {
           // JOptionPane.showMessageDialog(null,e.getMessage());
        }*/
       catch(SQLException e)
       {
        JOptionPane.showMessageDialog(null,e.toString());
     }
      catch(Exception ob)
     {
           //JOptionPane.showMessageDialog(null,ob.toString());
           System.out.println(ob.toString());
    }
        con.close();
        return ilist;
    }
    
    
 public static SolutionToIdeas getSolutionBySolutionId(int solutionid) throws SQLException
    {
        SolutionToIdeas i = new SolutionToIdeas();
        Connection con =null ;
        try
        {
             con = (Connection) ConnectionFactory.getConnection();
             PreparedStatement st = (PreparedStatement) con.prepareStatement("Select * from "+TABLENAME+" where "+SOLUTIONID+" = " + solutionid);
             ResultSet rs = st.executeQuery();


             if(rs.next())
             {
                 i.setUserId(rs.getInt(USERID));
                 i.setIdeaId(rs.getInt(IDEAID));
                 i.setSolutionId(rs.getInt(SOLUTIONID));
                 i.setUpvote(rs.getInt(UPVOTE));
                 i.setSubmittedDate(rs.getDate(SUBMITTEDDATE));
                 i.setDocumentationFileName(rs.getString(DOCUMENTATIONFILENAME));
                 i.setDownvote(rs.getInt(DOWNVOTE));
                 i.setSolutionFileName(rs.getString(SOLUTIONFILENAME));
                 return i;
             }
             else
             {
                 throw new NoSolutionFoundException("No Idea Found With the matching IdeaId");
             }

        }
        catch(NoSolutionFoundException e)
        {
           // JOptionPane.showMessageDialog(null,e.getMessage());
        }
       catch(SQLException e)
    {
       // JOptionPane.showMessageDialog(null,e.toString());
    }
      catch(Exception ob)

    {

    //    JOptionPane.showMessageDialog(null,ob.toString());
    }
      con.close();
        return i;
    }
 
 public static SolutionToIdeas getSolutionByIdeaId(int ideaid) throws SQLException
    {
        SolutionToIdeas i = new SolutionToIdeas();
        Connection con =null ;
        try
        {
             con = (Connection) ConnectionFactory.getConnection();
             PreparedStatement st = (PreparedStatement) con.prepareStatement("Select * from "+TABLENAME+" where "+IDEAID+" = " + ideaid);
             ResultSet rs = st.executeQuery();


             if(rs.next())
             {
                 i.setUserId(rs.getInt(USERID));
                 i.setIdeaId(rs.getInt(IDEAID));
                 i.setSolutionId(rs.getInt(SOLUTIONID));
                 i.setUpvote(rs.getInt(UPVOTE));
                 i.setSubmittedDate(rs.getDate(SUBMITTEDDATE));
                 i.setDocumentationFileName(rs.getString(DOCUMENTATIONFILENAME));
                 i.setDownvote(rs.getInt(DOWNVOTE));
                 i.setSolutionFileName(rs.getString(SOLUTIONFILENAME));
                 return i;
             }
             else
             {
                 throw new NoSolutionFoundException("No Idea Found With the matching IdeaId");
             }

        }
        catch(NoSolutionFoundException e)
        {
           // JOptionPane.showMessageDialog(null,e.getMessage());
        }
       catch(SQLException e)
    {
       // JOptionPane.showMessageDialog(null,e.toString());
    }
      catch(Exception ob)

    {

    //    JOptionPane.showMessageDialog(null,ob.toString());
    }
      con.close();
        return i;
    }

}
