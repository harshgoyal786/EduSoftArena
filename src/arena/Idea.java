/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package arena;
import arena.db.common.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.Connection;
/**
 *
 * @author Sony
 */

class NoIdeaFoundException extends Exception
{
   private String msg = null;
    NoIdeaFoundException()
    {
        super();
    }
    NoIdeaFoundException(String msg)
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

public class Idea
{

    private static String TABLENAME="IDEA" , USERID="USER_ID" ,IDEAID="IDEA_ID" , SUBMISSIONDATE="SUBMISSION_DATE" ,TITLE="TITLE" , DESCRIPTION="DESCRIPTION" , POSSIBLESCORE ="POSSIBLE_SCORE", NOOFSUBMISSIONS="NO_OF_SUBMISSIONS" , CDESCRIPTION , SOLUTIONID , SOLUTIONFILENAME , DOCUMENTATIONFILENAME , UPVOTE , DOWNVOTE , SSUBMISSIONDATE , CSDESCRIPTION  ;
	
    private int userId,ideaId , possibleScore ,noOfSubmissions ;
    private Date dateOfSubmission ;
    private String title , description ;
   

Connection con = null;

     CommentOnIdeas ci = new CommentOnIdeas();
        CommentOnSolutionOfIdeas csi = new CommentOnSolutionOfIdeas();
        SolutionOfIdeas si = new SolutionOfIdeas(); 

        
    /**
     * @return the possibleScore
     */


    public int getPossibleScore() {
        return possibleScore;
    }

    /**
     * @param possibleScore the possibleScore to set
     */
    public void setPossibleScore(int possibleScore) {
        this.possibleScore = possibleScore;
    }

    /**
     * @return the noOfSubmissions
     */
    public int getNoOfSubmissions() {
        return noOfSubmissions;
    }

    /**
     * @param noOfSubmissions the noOfSubmissions to set
     */
    public void setNoOfSubmissions(int noOfSubmissions) {
        this.noOfSubmissions = noOfSubmissions;
    }

    /**
     * @return the dateOfSubmission
     */
    public Date getDateOfSubmission() {
        return dateOfSubmission;
    }

    /**
     * @param dateOfSubmission the dateOfSubmission to set
     */
    public void setDateOfSubmission(Date dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the ideaId
     */
    public int getIdeaId() {
        return ideaId;
    }

    /**
     * @param ideaId the ideaId to set
     */
    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    /**
     * @return the UserId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param UserId the UserId to set
     */
    public void setUserId(int UserId) {
        this.userId = UserId;
    }


public Idea getIdea(int ideaId) throws SQLException
    {
        Idea i = new Idea();
        Connection con =null ;
        PreparedStatement st = null;
         ResultSet rs = null;
        try
        {
             con =  ConnectionFactory.getConnection();
             st = (PreparedStatement) con.prepareStatement("Select * from "+TABLENAME+" where "+IDEAID+" = " + ideaId);
             rs = st.executeQuery();


             if(rs.next())
             {
                 i.setDateOfSubmission(rs.getDate(SUBMISSIONDATE));
                 i.setPossibleScore(rs.getInt(POSSIBLESCORE));
                 i.setNoOfSubmissions(rs.getInt(NOOFSUBMISSIONS));
                 i.setDescription(rs.getString(DESCRIPTION));
                 i.setTitle(rs.getString(TITLE));
                 i.setIdeaId(rs.getInt(IDEAID));
             }
             else
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

                 return null;
             }

        }
     /*   catch(NoIdeaFoundException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } */
       catch(SQLException e)
    {
      //  JOptionPane.showMessageDialog(null,e.toString());
    }
      catch(Exception ob)

    {

    //    JOptionPane.showMessageDialog(null,ob.toString());
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
        return i;
    }


    public ArrayList<Idea> getIdeaSubmittedOn(Date date) throws NoIdeaFoundException, SQLException
    {
        ArrayList<Idea> ilist = new ArrayList<Idea>();
        Connection con =null ;
        PreparedStatement st = null;
        ResultSet rs = null;
             

        try
        {
            con =  ConnectionFactory.getConnection();
            st = (PreparedStatement) con.prepareStatement("Select * from "+TABLENAME+" where "+SUBMISSIONDATE+ " = " + date);
            rs = st.executeQuery();


             if(!rs.next())
             {
               // throw new NoIdeaFoundException("No Idea Found With the matching the Submitted date");

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

             do
             {
                 Idea i =new Idea();
                 i.setUserId(rs.getInt(USERID));
                 i.setDateOfSubmission(rs.getDate(SUBMISSIONDATE));
                 i.setPossibleScore(rs.getInt(POSSIBLESCORE));
                 i.setNoOfSubmissions(rs.getInt(NOOFSUBMISSIONS));
                 i.setDescription(rs.getString(DESCRIPTION));
                 i.setTitle(rs.getString(TITLE));
                 i.setIdeaId(rs.getInt(IDEAID));
                 i.ci.setDescription(rs.getString(CDESCRIPTION));
                 i.si.setSolutionFileName(rs.getString(CSDESCRIPTION));
                 i.si.setSolutionId(rs.getInt(SOLUTIONID));
                 i.si.setUpvote(rs.getInt(UPVOTE));
                 i.si.setSubmittedDate(rs.getDate(SSUBMISSIONDATE));
                 i.si.setDocumentationFileName(rs.getString(DOCUMENTATIONFILENAME));
                 i.si.setDownvote(rs.getInt(DOWNVOTE));
                 i.csi.setDescription(rs.getString(CSDESCRIPTION));
                 ilist.add(i);
             }while(rs.next());

             return ilist;
        }
   /*     catch(NoIdeaFoundException e)
        {
                JOptionPane.showMessageDialog(null,e.getMessage());
        }*/
       catch(SQLException e)
        {
        //      JOptionPane.showMessageDialog(null,e.toString());
        }
      catch(Exception ob)
      {
        //     JOptionPane.showMessageDialog(null,ob.toString());
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
      return ilist;
}

 public ArrayList<Idea> getIdeaSubmittedBetween(Date startDate , Date endDate) throws SQLException
 {
     ArrayList<Idea> ilist = new ArrayList<Idea>();
      Connection con =null ;
      PreparedStatement st = null;
      ResultSet rs = null;
     
     if(startDate.before(endDate))
     {
         try
        {
             con =  ConnectionFactory.getConnection();
             st = (PreparedStatement) con.prepareStatement("Select * from "+TABLENAME+" where "+SUBMISSIONDATE+" BETWEEN " + startDate+ " and " + endDate);
             rs = st.executeQuery();

             if(!rs.next())
             {
               // throw new NoIdeaFoundException("No Idea Found With the matching the Submitted date");

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

             do
             {
                 Idea i = new Idea();
                 i.setUserId(rs.getInt(USERID));
                 i.setDateOfSubmission(rs.getDate(SUBMISSIONDATE));
                 i.setPossibleScore(rs.getInt(POSSIBLESCORE));
                 i.setNoOfSubmissions(rs.getInt(NOOFSUBMISSIONS));
                 i.setDescription(rs.getString(DESCRIPTION));
                 i.setTitle(rs.getString(TITLE));
                 i.setIdeaId(rs.getInt(IDEAID));
                 i.ci.setDescription(rs.getString(CDESCRIPTION));
                 i.si.setSolutionFileName(rs.getString(CSDESCRIPTION));
                 i.si.setSolutionId(rs.getInt(SOLUTIONID));
                 i.si.setUpvote(rs.getInt(UPVOTE));
                 i.si.setSubmittedDate(rs.getDate(SSUBMISSIONDATE));
                 i.si.setDocumentationFileName(rs.getString(DOCUMENTATIONFILENAME));
                 i.si.setDownvote(rs.getInt(DOWNVOTE));
                 i.csi.setDescription(rs.getString(CSDESCRIPTION));
                 ilist.add(i);
             }while(rs.next());

             return ilist;

        }
    /*    catch(NoIdeaFoundException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }*/
       catch(SQLException e)
    {
      //  JOptionPane.showMessageDialog(null,e.toString());
    }
      catch(Exception ob)
      {
       // JOptionPane.showMessageDialog(null,ob.toString());
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

       else
     {
         JOptionPane.showMessageDialog(null , "End date is greater than Submission date ");
     }
     return ilist;

 }

 public ArrayList<Idea> getAllIdea() throws SQLException
 {
        ArrayList<Idea> ilist = new ArrayList<Idea>();
      Connection con =null ;
      PreparedStatement st = null;
      ResultSet rs = null;
        
        try
        {
            con =  ConnectionFactory.getConnection();
            st = (PreparedStatement) con.prepareStatement("Select * from  "+ TABLENAME);
            rs = st.executeQuery();

             if(!rs.next())
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


                 //throw new NoIdeaFoundException("No Idea Found With the matching the Submitted date");
                return null;
             }

             do
             {
                 Idea i = new Idea();
                 i.setUserId(rs.getInt(USERID));
                
                 i.setDateOfSubmission(rs.getDate(SUBMISSIONDATE));
                 
                 i.setPossibleScore(rs.getInt(POSSIBLESCORE));
                 
                 i.setNoOfSubmissions(rs.getInt(NOOFSUBMISSIONS));
                 
                 i.setDescription(rs.getString(DESCRIPTION));
                 
                 i.setTitle(rs.getString(TITLE));
                 
                 i.setIdeaId(rs.getInt(IDEAID));
                
                /* i.ci.setDescription(rs.getString(CDESCRIPTION));
                 i.si.setSolutionFileName(rs.getString(CSDESCRIPTION));
                 i.si.setSolutionId(rs.getInt(SOLUTIONID));
                 i.si.setUpvote(rs.getInt(UPVOTE));
                 i.si.setSubmittedDate(rs.getDate(SSUBMISSIONDATE));
                 i.si.setDocumentationFileName(rs.getString(DOCUMENTATIONFILENAME));
                 i.si.setDownvote(rs.getInt(DOWNVOTE));
                 i.csi.setDescription(rs.getString(CSDESCRIPTION));*/
                 ilist.add(i);
             }while(rs.next());

            return ilist;
        }
       
       catch(SQLException e)
       {
        //JOptionPane.showMessageDialog(null,e.toString());
     }
      catch(Exception ob)
     {
           //JOptionPane.showMessageDialog(null,ob.toString());
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
    
        return ilist;
    }

public static void addIdea( Idea i) throws SQLException
{	
	System.out.println("Hello i am a bad idea");
	Connection con=null;
      PreparedStatement st = null;
	
    try
   {
   
         con =  ConnectionFactory.getConnection();
        st = (PreparedStatement) con.prepareStatement("Insert into "+TABLENAME+" ( "+USERID+","+SUBMISSIONDATE+","+TITLE+","+DESCRIPTION+","+POSSIBLESCORE+","+NOOFSUBMISSIONS+" ) values ( ? ,? ,? , ? , ? , ? )");
        st.setInt(1, i.getUserId());
        //st.setInt(2, i.getIdeaId());
        st.setDate(2,(java.sql.Date) i.getDateOfSubmission());
        
       

        if(i.getTitle().equals(""))
       {
             st.setNull(3, Types.INTEGER);
       }
        else
        {
              st.setString(3, i.getTitle());
        }
        if(i.getDescription().equals(""))
       {
             st.setNull(4, Types.INTEGER);
       }
        else
        {
              st.setString(4, i.getDescription());
        }
        
            st.setInt(5, i.getPossibleScore());
       
              st.setInt(6, i.getNoOfSubmissions());
        


        int res = st.executeUpdate();

        if(res>0)
        {
         //  JOptionPane.showMessageDialog(null ,"Data added successfully ");
        }
        else
        {
           // JOptionPane.showMessageDialog(null , "Sorry !! Couldnt add your data ");
        }
    }
       catch(SQLException e)
       {
            //  JOptionPane.showMessageDialog(null,e.toString());
       }
      catch(Exception ob)
      {
         //   JOptionPane.showMessageDialog(null,ob.toString());
      }

      finally
     {
           try
          {
             if(con!=null)
                 con.close();
            if(st!=null)
                   st.close();
         }catch(SQLException e) {}
     }
}

class CommentOnIdeas
{

    private int userId , ideaId ;
    private String description ;

    /**
     * @return the description
     */

//    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
  //  @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
}

class SolutionOfIdeas
{
    /**
     * @return the upvote
     */
    public static int getUpvote() {
        return upvote;
    }

    /**
     * @param aUpvote the upvote to set
     */
    public static void setUpvote(int aUpvote) {
        upvote = aUpvote;
    }

    /**
     * @return the downvote
     */
    public static int getDownvote() {
        return downvote;
    }

    /**
     * @param aDownvote the downvote to set
     */
    public static void setDownvote(int aDownvote) {
        downvote = aDownvote;
    }
    private int userId , solutionId , ideaId ;
    private static int upvote , downvote ;
    private String solutionFileName , documentationFileName ;
    private Date submittedDate ;

    /**
     * @return the solutionId
     */
    public int getSolutionId() {
        return solutionId;
    }

    /**
     * @param solutionId the solutionId to set
     */
    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }

    /**
     * @return the solutionFileName
     */
    public String getSolutionFileName() {
        return solutionFileName;
    }

    /**
     * @param solutionFileName the solutionFileName to set
     */
    public void setSolutionFileName(String solutionFileName) {
        this.solutionFileName = solutionFileName;
    }

    /**
     * @return the documentationFileName
     */
    public String getDocumentationFileName() {
        return documentationFileName;
    }

    /**
     * @param documentationFileName the documentationFileName to set
     */
    public void setDocumentationFileName(String documentationFileName) {
        this.documentationFileName = documentationFileName;
    }

    /**
     * @return the submittedDate
     */
    public Date getSubmittedDate() {
        return submittedDate;
    }

    /**
     * @param submittedDate the submittedDate to set
     */
    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

}
class CommentOnSolutionOfIdeas extends SolutionOfIdeas {
    private String description ;
    /**
     * @return the description
     */
//    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
//    @Override
    public void setDescription(String description) {
        this.description = description;
    }


}
