

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
 * @author Sony
 */

class NoTutorialFoundException extends Exception
{
   private String msg = null;
    NoTutorialFoundException()
    {
        super();
    }
    NoTutorialFoundException(String msg)
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

public class Tutorial {

    private int iTutorialId ;
    private String strTopic ;
	private String strURL;
    private static String TABLENAME="TUTORIAL" , TUTORIALID="TUTORAIL_ID", TOPIC="topic",URL="URL" ;

    Connection con = null;

    /**
     * @return the FAQ_id
     */
    public int getTutorialId() {
        return iTutorialId;
    }

    /**
     * @param FAQ_id the FAQ_id to set
     */
    public void setTutorialId(int Tutorail_id) {
        this.iTutorialId = Tutorail_id;
    }


    /**
     * @return the topic
     */
    public String getTopic() {
        return strTopic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(String topic) {
        this.strTopic = topic;
    }
	
    /**
     * @return the URL
     */
    public String getURL() {
        return strURL;
    }

    /**
     * @param topic the topic to set
     */
    public void setURL(String URL) {
        this.strURL = URL;
    }




public static ArrayList<Tutorial> getTutorialByTopic(String topic) throws SQLException, NoFAQFoundException
{
        Tutorial i ;//= new Tutorial();
        ArrayList<Tutorial> ilist = new ArrayList<Tutorial>();
      Connection con =null ;
      PreparedStatement st=null;
      ResultSet rs=null;
        try
        {
             con = ConnectionFactory.getConnection();
				/*SELECT TUTORIAL.URL FROM TUTORIAL  NATURAL JOIN topics WHERE topics.topic='Pointers';*/
             st = con.prepareStatement("SELECT TUTORIAL.URL FROM TUTORIAL NATURAL JOIN topics WHERE topics.topic=?");
			 st.setString(1,topic);
             
			 rs = st.executeQuery();
			 
             if(!rs.next())
             {
                //throw new NoTutorialFoundException("No Tutorial Found With the matching the topic"); 
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


             do
             {
				i = new Tutorial();
				//System.out.println(rs.getString(URL));
                 i.setTutorialId(1);
				 i.setURL(rs.getString(URL));
				 ilist.add(i);
             }while(rs.next());
             for(int j=0;j<ilist.size();j++)
			 {
				System.out.println(ilist.get(j).getURL());
			 }
             

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
 }
