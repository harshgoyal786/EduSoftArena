

import Connection.ConnectionFactory;
import Connection.ConnectionFactory;
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
 * @author Adidas430
 */

public class QUERY{
	
	private int userId,qId;
	private String desc,sourceCode,title;
    private Date date;	
    private Byte flag;
	


	private static String TABLENAME="query" , USERID="USER_ID" ,QID="QID" , DESCRIPTION="DESCRIPTION" , SOURCECODE="SOURCE_CODE" , QUERYDATE="QUERY_DATE", TITLE="TITLE" , ANSWEREDFLAG="ANSWERED_FLAG";

	Connection con = null;

	    /**
     * @return the USER_ID
     */
    public int getUSERId() {
        return userId;
    }


     /**
     * @param USER_ID the USER_ID to set
     */
    public void setUSERId(int USER_id) {
        this.userId = USER_id;
    }



     /**
     * @return the q_id
     */
    public int getQid() {
        return qId;
    }

    /**
     * @param q_id the q_id to set
     */
    public void setQid(int Q_id) {
        this.qId = Q_id;
    }

     /**
     * @return the desc
     */
    public String getDescription() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDescription(String desc) {
        this.desc = desc;
    }


     /**
     * @return the sourceCode
     */
    public String getSource() {
        return sourceCode;
    }

    /**
     * @param src the src to set
     */
    public void setSource(String src) {
        this.sourceCode = src;
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
     * @return the date
     */
    public Date getQdate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setQdate(Date date) {
        this.date = date;
    }


     /**
     * @return the flag
     */
    public byte getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(byte flag) {
        this.flag = flag;
    }

    static ArrayList<QUERY> getAllQueries() throws SQLException,NoQueryFoundException
    {
    	QUERY q=new QUERY();
    	 ArrayList<QUERY> ilist = new ArrayList<QUERY>();
    	Connection con=null;
    		
    	 try
         {
              con = ConnectionFactory.getConnection();
              PreparedStatement st = con.prepareStatement("select * from"+TABLENAME);
              ResultSet rs = st.executeQuery();


              if(!rs.next())
              {
                 throw new NoQueryFoundException("No query Found With the matching the topic");
              }
              do
              {
                 q.setQdate(rs.getDate(QUERYDATE));
                 q.setFlag(rs.getByte(ANSWEREDFLAG));
                 q.setUSERId(rs.getInt(USERID));
                 q.setTitle(rs.getString(TITLE));
                 q.setDescription(rs.getString(DESCRIPTION));
                 q.setSource(SOURCECODE);
                 q.setQid(rs.getInt(QID));
                 ilist.add(q);
                 
              }while(rs.next());
              
              

         }
      /*   catch(NoFAQFoundException e)
         {
             JOptionPane.showMessageDialog(null,e.getMessage());
         }*/
        catch(SQLException e)
     {
         JOptionPane.showMessageDialog(null,e.toString());
     }
       catch(Exception ob)

     {

         JOptionPane.showMessageDialog(null,ob.toString());
     }
       con.close();
       return ilist;
    }

   static ArrayList<QUERY> getQueryByQId(int qid) throws SQLException,NoQIdFoundException
   {
	   QUERY i = new QUERY();
	   ArrayList<QUERY> ilist = new ArrayList<QUERY>();
	      Connection con =null ;
	        try
	        {
	             con = ConnectionFactory.getConnection();
	             PreparedStatement st = con.prepareStatement("Select * from "+TABLENAME+" where "+QID+" = " + QID);
	             ResultSet rs = st.executeQuery();


	             if(rs.next())
	             {
	                 i.setQdate(rs.getDate(QUERYDATE));
	                 i.setUSERId(rs.getInt(USERID));
	                 i.setQid(rs.getInt(QID));
	                 i.setDescription(rs.getString(DESCRIPTION));
	                 i.setSource(rs.getString(SOURCECODE));
	                 i.setTitle(rs.getString(TITLE));
	                 i.setFlag(rs.getByte(ANSWEREDFLAG));
	                 ilist.add(i);
	             }
	         else
	             {
	                 throw new NoQIdFoundException("No query Found With the matching QId");
	             }
	             
	             return ilist;

	        }
	     /*   catch(NoFAQFoundException e)
	        {
	            JOptionPane.showMessageDialog(null,e.getMessage());
	        }*/
	       catch(SQLException e)
	    {
	        JOptionPane.showMessageDialog(null,e.toString());
	    }
	      catch(Exception ob)

	    {

	        JOptionPane.showMessageDialog(null,ob.toString());
	    }
	      con.close();
	      return ilist;
   }
   
   static ArrayList<QUERY> getAllUnAnsweredQueries() throws SQLException
   {
	   QUERY i = new QUERY();
	      ArrayList<QUERY> ilist = new ArrayList<QUERY>();
	      Connection con =null ;
	        try
	        {
	             con = ConnectionFactory.getConnection();
	             PreparedStatement st = con.prepareStatement("Select * from "+TABLENAME+" where "+ANSWEREDFLAG+" = 0" );
	             ResultSet rs = st.executeQuery();

	               if(!rs.next())
	             {
	                throw new NoQueryFoundException("No UnAnswered Queries Found");
	             }


	             do
	             {
	                 i.setQdate(rs.getDate(QUERYDATE));
	                 i.setUSERId(rs.getInt(USERID));
	                 i.setQid(rs.getInt(QID));
	                 i.setDescription(rs.getString(DESCRIPTION));
	                 i.setSource(rs.getString(SOURCECODE));
	                 i.setTitle(rs.getString(TITLE));
	                 i.setFlag(rs.getByte(ANSWEREDFLAG));
	                 ilist.add(i);
	             }while (rs.next());
	        
	        	return ilist;

	        }
	        catch(NoQueryFoundException e)
	        {
	            JOptionPane.showMessageDialog(null,e.getMessage());
	        }
	     	catch(SQLException e)
	       	{
	          JOptionPane.showMessageDialog(null,e.toString());
	      	}
	      catch(Exception ob)
	        {
	           JOptionPane.showMessageDialog(null,ob.toString());
	    	}
	      con.close();
	      return ilist;
   }

}
