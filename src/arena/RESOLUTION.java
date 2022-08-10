/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;

import arena.db.common.ConnectionFactory;
import com.mysql.jdbc.Statement;
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
public class RESOLUTION {
    
        private int rUserId,rId,qId,likes , important;
	private String rDesc;
	private Date rDate;
        private static String TABLENAME="RESOLUTION", USERID="USER_ID"  , RDESCRIPTION="RDESCRIPTION",RESOLUTIONID="RESOLUTION_ID",RESOLUTIONDATE="RESOLUTION_DATE" , QID="QID",LIKES="LIKES" , IMPORTANT = "IMPORTANT";

    /**
     * @return the rUserId
     */
        
    public RESOLUTION ()
    {}
 /*   public RESOLUTION ( int rUserId,int  rId, int  qId ,int  likes , String rdesc , Date date )
    {
        this.rUserId = rUserId;
        this.rId= rId;
        this.qId=qId;
        this.likes = likes;
        this.rDesc = rdesc;
        this.rDate = date;

    }*/
    public int getrUserId() {
        return rUserId;
    }

    /**
     * @param rUserId the rUserId to set
     */
    public void setrUserId(int rUserId) {
        this.rUserId = rUserId;
    }

    /**
     * @return the rId
     */
    public int getrId() {
        return rId;
    }

    /**
     * @param rId the rId to set
     */
    public void setrId(int rId) {
        this.rId = rId;
    }

    /**
     * @return the rDesc
     */
    public String getrDesc() {
        return rDesc;
    }

    /**
     * @param rDesc the rDesc to set
     */
    public void setrDesc(String rDesc) {
        this.rDesc = rDesc;
    }

    /**
     * @return the rDate
     */
    public Date getrDate() {
        return rDate;
    }

    /**
     * @param rDate the rDate to set
     */
    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    /**
     * @return the qId
     */
    public int getqId() {
        return qId;
    }

    /**
     * @param qId the qId to set
     */
    public void setqId(int qId) {
        this.qId = qId;
    }
 
    public void setLikes(int likes)
    {
      this.likes=likes;
    }

    public int getLikes()
    {
      return this.likes;
    }

    /**
     * @return the important
     */
    public int getImportant() {
        return important;
    }

    /**
     * @param important the important to set
     */
    public void setImportant(int important) {
        this.important = important;
    }


    public static ArrayList<RESOLUTION> getAllResponsesByQueryId(int QueryId) throws SQLException //,NoResolutionFoundException
    {
      
      ArrayList<RESOLUTION> ilist = new ArrayList<RESOLUTION>();
      Connection con =null ;
       PreparedStatement st =null;
       ResultSet rs = null;
       
        try
        {
             con = ConnectionFactory.getConnection();
             st = con.prepareStatement("Select * from "+TABLENAME+" where "+QID+" = "+ QueryId  );
              rs = st.executeQuery();

               if(!rs.next())
              {
                //throw new NoQueryFoundException("No Responses Found");
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
 
               RESOLUTION q=new RESOLUTION();
                    q.setrDate(rs.getDate(RESOLUTIONDATE));
                    q.setrUserId(rs.getInt(USERID));
                    q.setrDesc(rs.getString(RDESCRIPTION));
                    q.setqId(rs.getInt(QID));
                    q.setrId(rs.getInt(RESOLUTIONID));
                    q.setLikes(rs.getInt(LIKES));
                    q.setImportant(rs.getInt(IMPORTANT));
                 ilist.add(q);
             }while (rs.next());
             
             
        
        	

        }
        
     	catch(SQLException e)
       	{
   //       JOptionPane.showMessageDialog(null,e.toString());
      	}
      catch(Exception ob)
        {
         //  JOptionPane.showMessageDialog(null,ob.toString());
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
  

public static int getLikesByRid(int rId) throws SQLException //,NoResolutionFoundException
    {
      int likes=0;
      ResultSet res =null;
      Connection con =null ;
      PreparedStatement st=null;

        try
        {
             con = ConnectionFactory.getConnection();
              st = con.prepareStatement("select "+LIKES+" from "+TABLENAME+" where "+RESOLUTIONID+" = ?");
             st.setInt(1,rId);
              res = st.executeQuery();
              if(res.next())
              {
                  likes= res.getInt(LIKES);
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
        if(res!=null)
            res.close();
        if(st!=null)
            st.close();
       }catch(SQLException e) {}
    }
      return likes;
  } 



public static void addLikeByResId(int rId) throws SQLException //,NoResolutionFoundException
    {
      
  
      Connection con =null ;
       PreparedStatement st=null;
       ResultSet rs=null;
        try
        {
             con = ConnectionFactory.getConnection();
            st = con.prepareStatement("update "+TABLENAME+" set "+LIKES+" = "+ LIKES+"+1 where "+RESOLUTIONID+" = "+rId);
             int res = st.executeUpdate();
            
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
        if(rs!=null)
            rs.close();
        if(st!=null)
            st.close();
       }catch(SQLException e) {}
    }
      
      
  } 

 public static ArrayList<Integer> getQidfromResponseByUserId(int UserId) throws SQLException//,NoResolutionFoundException
 {
      
      ArrayList<Integer> ilist = new ArrayList<Integer>();
      Connection con =null ;
      PreparedStatement st=null;
      ResultSet  rs=null;
        try
        {
             con = ConnectionFactory.getConnection();
             st = con.prepareStatement("Select DISTINCT("+QID+") from "+TABLENAME+" where "+USERID+" = "+ UserId  );
             rs = st.executeQuery();

               if(!rs.next())
             {
              //  throw new NoQueryFoundException("No Responses Found");
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
                  int i = rs.getInt(QID);
                   ilist.add(i);
             }while (rs.next());
        
        	

        }
        
     	catch(SQLException e)
       	{
       //   JOptionPane.showMessageDialog(null,e.toString());
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
      return ilist;
  } 
 
 public   static String addResolution(RESOLUTION i) throws SQLException{
	 PreparedStatement st = null;
     Connection con = null;
     ResultSet rs=null;
     try
    {
         con = ConnectionFactory.getConnection();
         st = con.prepareStatement("Insert into "+TABLENAME+" ( "+USERID+","+QID+","+RDESCRIPTION+","+RESOLUTIONDATE+","+RESOLUTIONID+","+LIKES +" ) values ( ? , ? ,? ,? , ? ,? )");
         st.setInt(1, i.getrUserId());
         st.setInt(2, i.getqId());
         st.setDate(4,(java.sql.Date) i.getrDate());

         if(i.getrDesc().equals(""))
       	 {
             st.setNull(3, Types.INTEGER);
         }
         else
         {
              st.setString(3, i.getrDesc());
         }


         
         st.setInt(5, i.getrId());
         st.setInt(6,i.getLikes());

          int res = st.executeUpdate();
          System.out.println("Qid_resolution="+i.getqId());
          QUERY.setAnsweredFlag(i.getqId());

        if(res>0)
        {
          //  JOptionPane.showMessageDialog(null ,"Data added successfully ");
            return "1";
        }
        else
        {
      //      JOptionPane.showMessageDialog(null , "Sorry !! Couldnt add your data ");
            return "0";
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
    
    return "0";

}
 
 public static ArrayList<Integer> getResoltuionByReservedWords(String word) throws SQLException {
  
        ArrayList<Integer> ilist = new ArrayList<Integer>();
        Connection con =null ;
        PreparedStatement st=null;
        ResultSet rs=null;
        try
        {
             con = ConnectionFactory.getConnection();
             st = con.prepareStatement("Select DISTINCT("+QID+") from "+TABLENAME+" where LOWER("+RDESCRIPTION+") like LOWER('%"+word+"%')");
             rs = st.executeQuery();

             if(!rs.next())
             {
               // throw new NoResolutionFoundException("No FAQ Found With the matching the word");
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
                   ilist.add(rs.getInt(QID));
             }while(rs.next());

             

        }
       catch(SQLException e)
       {
          JOptionPane.showMessageDialog(null,e.toString());
       }
      catch(Exception ob)
      {
           JOptionPane.showMessageDialog(null,ob.toString());
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

    public static ArrayList<RESOLUTION> getResOrderByLikes() throws SQLException//,NoFAQFoundException
 {

      RESOLUTION i = null ;
     ArrayList<RESOLUTION> ilist = new ArrayList<RESOLUTION>();
      Connection con =null ;
      PreparedStatement st=null;
      ResultSet rs=null;
        try
        {
            con = ConnectionFactory.getConnection();
             st = con.prepareStatement("Select * , MAX("+LIKES+") from "+TABLENAME+" GROUP BY "+QID+" ORDER BY MAX("+LIKES+") DESC");
             rs = st.executeQuery();

               if(!rs.next())
             {
                //throw new NoFAQFoundException("No FAQ Found");
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
                     i = new RESOLUTION();
                     i.setLikes(rs.getInt(LIKES));
                     i.setqId(rs.getInt(QID));
                     i.setrId(rs.getInt(RESOLUTIONID));
                     i.setrDesc(rs.getString(RDESCRIPTION));
                     i.setrDate(rs.getDate(RESOLUTIONDATE));
                     i.setrUserId(rs.getInt(USERID));
                     i.setImportant(rs.getInt(IMPORTANT));
                     ilist.add(i);
             }while (rs.next());

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
        if(rs!=null)
            rs.close();
        if(st!=null)
            st.close();
       }catch(SQLException e) {}
    }
      return ilist;
  }

   

public static ArrayList<Integer> getMostLikedContributor() throws SQLException//,NoFAQFoundException
 {

      
     ArrayList<Integer> ilist = new ArrayList<Integer>();
      Connection con =null ;
      PreparedStatement  st=null;
       ResultSet rs=null;
        try
        {
            con = ConnectionFactory.getConnection();
             st = con.prepareStatement("Select SUM("+LIKES+") , "+USERID+" from "+TABLENAME+" GROUP BY "+USERID+" ORDER BY SUM("+LIKES+") desc");
            rs = st.executeQuery();

               if(!rs.next())
             {
                //throw new NoFAQFoundException("No FAQ Found");
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
                     ilist.add(rs.getInt(USERID));
             }while (rs.next());


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
        if(rs!=null)
            rs.close();
        if(st!=null)
            st.close();
       }catch(SQLException e) {}
    }
      return ilist;
  }


 public static int getSumLikes(int userid) throws SQLException//,NoFAQFoundException
 {
      int likes=0;
      Connection con =null ;
      PreparedStatement st=null;
      ResultSet rs =null;
        try
        {
            con = ConnectionFactory.getConnection();
             st = con.prepareStatement("Select SUM("+LIKES+") from "+TABLENAME+"  where "+USERID+" = "+userid+" GROUP BY "+USERID);
             rs = st.executeQuery();

               if(!rs.next())
             {
                //throw new NoFAQFoundException("No FAQ Found");
       try
       {
           if(con!=null)
         con.close();
        if(rs!=null)
            rs.close();
        if(st!=null)
            st.close();
       }catch(SQLException e) {}
    
                return 0;
             }
             else
             {
                  likes=rs.getInt("SUM("+LIKES+")");
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
        if(rs!=null)
            rs.close();
        if(st!=null)
            st.close();
       }catch(SQLException e) {}
    }
      return likes;
  }

 public static void updateImportant(int qid) throws SQLException
   {

       Connection con=null;
       ResultSet rs = null;
       Statement st = null;

   try
   {

        con = (Connection) ConnectionFactory.getConnection();
        st = (Statement) con.createStatement();
        int res = st.executeUpdate("update "+TABLENAME+" set " +IMPORTANT+ " = 1 where "+QID+ " = "+qid);


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

 public static ArrayList<RESOLUTION> getAllImportant() throws SQLException //,NoResolutionFoundException
    {

      ArrayList<RESOLUTION> ilist = new ArrayList<RESOLUTION>();
      Connection con =null ;
       PreparedStatement st =null;
       ResultSet rs = null;

        try
        {
             con = ConnectionFactory.getConnection();
             st = con.prepareStatement("Select * from "+TABLENAME+" where "+IMPORTANT+" = 1" );
              rs = st.executeQuery();

               if(!rs.next())
              {
                //throw new NoQueryFoundException("No Responses Found");
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

               RESOLUTION q=new RESOLUTION();
                    q.setrDate(rs.getDate(RESOLUTIONDATE));
                    q.setrUserId(rs.getInt(USERID));
                    q.setrDesc(rs.getString(RDESCRIPTION));
                    q.setqId(rs.getInt(QID));
                    q.setrId(rs.getInt(RESOLUTIONID));
                    q.setLikes(rs.getInt(LIKES));
                    q.setImportant(rs.getInt(IMPORTANT));
                 ilist.add(q);
             }while (rs.next());





        }

     	catch(SQLException e)
       	{
   //       JOptionPane.showMessageDialog(null,e.toString());
      	}
      catch(Exception ob)
        {
         //  JOptionPane.showMessageDialog(null,ob.toString());
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

 public static boolean isImportant(int qid) throws SQLException
{
       Connection con =null ;
       PreparedStatement st = null;
       ResultSet rs = null;
        try
        {
             con = ConnectionFactory.getConnection();
              st = con.prepareStatement("Select "+QID+" from "+TABLENAME+" where "+QID+" = "+ qid  );
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

                //trow new NoQueryFoundException("No  Found");
                 return false;
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

                return true;
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


      return false;
 }
}

    
        

