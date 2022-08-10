/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;
import arena.db.common.ConnectionFactory;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class Likes {

    
    private int lUserId,resolutionId;
    private static String TABLENAME="LIKES", RESOLUTIONID = "RESOLUTION_ID" , USERID="USER_ID"; 

    /**
     * @return the lUserId
     */
    public int getlUserId() {
        return lUserId;
    }

    /**
     * @param lUserId the lUserId to set
     */
    public void setlUserId(int lUserId) {
        this.lUserId = lUserId;
    }

    /**
     * @return the resolutionId
     */
    public int getResolutionId() {
        return resolutionId;
    }

    /**
     * @param resolutionId the resolutionId to set
     */
    public void setResolutionId(int resolutionId) {
        this.resolutionId = resolutionId;
    }
   public  void addLikes(Likes i) throws SQLException{
	 
     Connection con = null;
     PreparedStatement st=null;
     try
    {
         con = ConnectionFactory.getConnection();
         st = con.prepareStatement("Insert into "+TABLENAME+" ( "+USERID+","+RESOLUTIONID+" ) values ( ? , ? )");
         st.setInt(1, i.getlUserId());
         st.setInt(2, i.getResolutionId());
         int res = st.executeUpdate();

        if(res>0)
        {
            //JOptionPane.showMessageDialog(null ,"Data added successfully ");
        }
        else
        {
            //JOptionPane.showMessageDialog(null , "Sorry !! Couldnt add your data ");
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

            if(st!=null)
                   st.close();
         }catch(SQLException e) {}
     }

}
public static boolean checkLikesByrIduId(int rid,int uid)
{
   
      Connection con =null ;
      PreparedStatement st = null;
      ResultSet rs = null;
        try
        {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("Select * from "+TABLENAME+" where "+RESOLUTIONID+" = "+rid+" and "+USERID+" = "+uid);
            rs = st.executeQuery();
             
               if(!rs.next())
              {

                      try
                     {
                        if(con!=null)
                           con.close();

                        if(st!=null)
                             st.close();
               }catch(SQLException e) {}


                   //throw new NoFAQFoundException("No FAQ Found");
                return false;
              }
        }

     	catch(SQLException e)
       	{
         // JOptionPane.showMessageDialog(null,e.toString());
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

            if(st!=null)
                   st.close();
         }catch(SQLException e) {}
     }
      return true;
  }
}
