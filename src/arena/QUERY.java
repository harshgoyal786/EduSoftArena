/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;
import arena.db.common.ConnectionFactory;
import arena.RESOLUTION;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class QUERY 
{
    private int qUserId,qId;
    private String qDesc;
    private Date qDate;	
    private byte flag;
    private static String TABLENAME="QUERY",USERID="USER_ID" ,QID="QID" , QDESCRIPTION="QDESCRIPTION",QUERYDATE="QUERY_DATE" , ANSWEREDFLAG="ANSWERED";
    /**
     * @return the qUserId
     */
    public int getqUserId() {
        return qUserId;
    }

    /**
     * @param qUserId the qUserId to set
     */
    public void setqUserId(int qUserId) {
        this.qUserId = qUserId;
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

    /**
     * @return the qDesc
     */
    public String getqDesc() {
        return qDesc;
    }

    /**
     * @param qDesc the qDesc to set
     */
    public void setqDesc(String qDesc) {
        this.qDesc = qDesc;
    }

    /**
     * @return the qDate
     */
    public Date getqDate() {
        return qDate;
    }

    /**
     * @param qDate the qDate to set
     */
    public void setqDate(Date qDate) {
        this.qDate = qDate;
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
    
   public static ArrayList<QUERY> getAllQueries() throws SQLException//,NoQueryFoundException
    {
    	
      ArrayList<QUERY> ilist = new ArrayList<QUERY>();
      Connection con=null;
      PreparedStatement st = null;
      ResultSet rs = null;
    		
    	 try
         {
              con = ConnectionFactory.getConnection();
              st = con.prepareStatement("select * from "+TABLENAME);
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

                  //throw new NoQueryFoundException("No query Found With the matching the topic");
                return null;
              }
              do
              {
                 QUERY q=new QUERY();
                 q.setqDate(rs.getDate(QUERYDATE));
                 q.setFlag(rs.getByte(ANSWEREDFLAG));
                 q.setqUserId(rs.getInt(USERID));
                 q.setqDesc(rs.getString(QDESCRIPTION));
                 q.setqId(rs.getInt(QID));
                 ilist.add(q);
                 
              }while(rs.next());
              
              

         }
      /*   catch(NoFAQFoundException e)
         {
             JOptionPane.showMessageDialog(null,e.getMessage());
         }*/
        catch(SQLException e)
     {
        //JOptionPane.showMessageDialog(null,e.toString());
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
   
  public  static QUERY getQueryByQueryId(int qid) throws SQLException//,NoQueryFoundException
   {
	  
              QUERY q =null;
	      Connection con =null ;
              ResultSet rs=null;
              PreparedStatement st = null;

	        try
	        {
	             con = ConnectionFactory.getConnection();
	              st = con.prepareStatement("Select * from "+TABLENAME+" where "+QID+" = " + qid);
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
                         return null;
                     }
	            else
	             {
	                 q=new QUERY();
                         q.setqDate(rs.getDate(QUERYDATE));
                         q.setFlag(rs.getByte(ANSWEREDFLAG));
                         q.setqUserId(rs.getInt(USERID));
                         q.setqDesc(rs.getString(QDESCRIPTION));
                         q.setqId(rs.getInt(QID));
                       
	              
	             }
	         
	             

	        }
	     /*   catch(NoFAQFoundException e)
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
	      return q;
   }
  
  public  static ArrayList<QUERY> getQueryByUserId(int userid) throws SQLException//,NoQueryFoundException
   {
              ArrayList<QUERY> ilist = new ArrayList<QUERY>();
	      Connection con =null ;
              PreparedStatement st = null;
              ResultSet rs = null;
	        try
	        {
	             con = ConnectionFactory.getConnection();
	             st = con.prepareStatement("Select * from "+TABLENAME+" where "+USERID+" = " + userid);
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


                          //  throw new NoQueryFoundException("No UnAnswered Queries Found");
                         return null;
	             }


	             do
	             {
	                 QUERY q=new QUERY();
                         q.setqDate(rs.getDate(QUERYDATE));
                         q.setFlag(rs.getByte(ANSWEREDFLAG));
                         q.setqUserId(rs.getInt(USERID));
                         q.setqDesc(rs.getString(QDESCRIPTION));
                         q.setqId(rs.getInt(QID));
                         ilist.add(q); 
	             }while (rs.next());
	        
	  


	        }
	     /*   catch(NoFAQFoundException e)
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
	      return ilist;
   }
  
  public static ArrayList<QUERY> getAllUnAnsweredQueries() throws SQLException
   {
	   
	      ArrayList<QUERY> ilist = new ArrayList<QUERY>();
	      Connection con =null ;
              PreparedStatement st = null;
              ResultSet rs = null;
	        try
	        {
	             con = ConnectionFactory.getConnection();
	             st = con.prepareStatement("Select * from "+TABLENAME+" where "+ANSWEREDFLAG+" = 0" );
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

                           
                           // throw new NoQueryFoundException("No UnAnswered Queries Found");
                           return null;
	             }


	             do
	             {
	                 QUERY q=new QUERY();
                         q.setqDate(rs.getDate(QUERYDATE));
                         q.setFlag(rs.getByte(ANSWEREDFLAG));
                         q.setqUserId(rs.getInt(USERID));
                         q.setqDesc(rs.getString(QDESCRIPTION));
                         q.setqId(rs.getInt(QID));
                         ilist.add(q); 
	             }while (rs.next());
	       
	        }
	/*        catch(NoQueryFoundException e)
	        {
	          //  JOptionPane.showMessageDialog(null,e.getMessage());
	        } */
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
  
  public static ArrayList<QUERY> getAllAnsweredQueries() throws SQLException//,NoQueryFoundException{
    {
     
      ArrayList<QUERY> ilist = new ArrayList<QUERY>();
      Connection con =null ;
      PreparedStatement st = null;
      ResultSet rs = null;
        try
        {
             con = ConnectionFactory.getConnection();
             st = con.prepareStatement("Select * from "+TABLENAME+" where "+ANSWEREDFLAG+" = 1" );
             rs = st.executeQuery();

               if(!rs.next())
             {
               // throw new NoQueryFoundException("No Answered Queries Found");

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
                 QUERY q=new QUERY();
                  q.setqDate(rs.getDate(QUERYDATE));
                  q.setFlag(rs.getByte(ANSWEREDFLAG));
                  q.setqUserId(rs.getInt(USERID));
                  q.setqDesc(rs.getString(QDESCRIPTION));
                  q.setqId(rs.getInt(QID));
                  ilist.add(q);
             }while (rs.next());
        
         

        }
     /*   catch(NoQueryFoundException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
*/     	catch(SQLException e)
       	{
          //JOptionPane.showMessageDialog(null,e.toString());
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
      return ilist;
  }
  
   
  public static ArrayList<QUERY> getAllAnsweredQueriesByReservedWords(String word) throws SQLException, NoQueryFoundException//,NoQueryFoundException{
    {
       ArrayList<QUERY> q1=new ArrayList<QUERY>();
      
        try{  
      ArrayList<QUERY> q=new ArrayList<QUERY>();
      q=QUERY.getQueryByReservedWords(word);
      System.out.println(q);
      int i=0;
      
      ArrayList<Integer> c = new ArrayList<Integer>();
      c=RESOLUTION.getResoltuionByReservedWords(word);
       System.out.println(c);
       //c = new ArrayList<Integer>();
      if(q!=null){
		  if(c==null)
		    c=new ArrayList<Integer>();
      while(i<q.size())
      {
          c.add(q.get(i).getqId());
          i++;
      }}
      
      if(c==null||c.size()==0)
         return null;
      HashSet<Integer> hs=new HashSet<Integer>();
      hs.addAll(c);
      c.clear();
      c.addAll(hs);
      i=0;
      
    
     while ( i<c.size())
      {
          q1.add(QUERY.getQueryByQueryId(c.get(i)));
          i++;
      }
      System.out.println(c);
      
      return q1;
       }catch(SQLException se){}
      return q1;
    }
  
  public static ArrayList<QUERY> getQueryByReservedWords(String word) throws SQLException {
  
        ArrayList<QUERY> ilist = new ArrayList<QUERY>();
        Connection con =null ;
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
             con = ConnectionFactory.getConnection();
             st = con.prepareStatement("Select * from "+TABLENAME+" where LOWER("+QDESCRIPTION+") like LOWER('%"+word+"%') and "+ANSWEREDFLAG+" = 1 ");
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

                 //throw new NoFAQFoundException("No FAQ Found With the matching the word");
                 return null;
             }


             do
             {
                  QUERY q=new QUERY();
                  q.setqDate(rs.getDate(QUERYDATE));
                  q.setFlag(rs.getByte(ANSWEREDFLAG));
                  q.setqUserId(rs.getInt(USERID));
                  q.setqDesc(rs.getString(QDESCRIPTION));
                  q.setqId(rs.getInt(QID));
                  ilist.add(q);
             }while(rs.next());

        }
      /*  catch(NoFAQFoundException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }*/
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
  
   public static ArrayList<QUERY> getQueryByWordsUserId(String word,int userid) throws SQLException {
  
        ArrayList<QUERY> ilist = new ArrayList<QUERY>();
        Connection con =null ;
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
             con = ConnectionFactory.getConnection();
             st = con.prepareStatement("Select * from "+TABLENAME+" where LOWER("+QDESCRIPTION+") like LOWER('%"+word+"%') and "+USERID+" = "+ userid);
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


                 //throw new NoFAQFoundException("No FAQ Found With the matching the word");
                 return null;
             }


             do
             {
                  QUERY q=new QUERY();
                  q.setqDate(rs.getDate(QUERYDATE));
                  q.setFlag(rs.getByte(ANSWEREDFLAG));
                  q.setqUserId(rs.getInt(USERID));
                  q.setqDesc(rs.getString(QDESCRIPTION));
                  q.setqId(rs.getInt(QID));
                  ilist.add(q);
             }while(rs.next());
        }
      /*  catch(NoFAQFoundException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }*/
       catch(SQLException e)
       {
           // JOptionPane.showMessageDialog(null,e.toString());
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
  
  public  int addQuery(QUERY i) throws SQLException{
  
     Connection con=null;
     PreparedStatement st = null;
  try
   {
        con = ConnectionFactory.getConnection();
        st = con.prepareStatement("Insert into "+TABLENAME+" ( "+USERID+","+QID+","+QDESCRIPTION+","+QUERYDATE+","+ANSWEREDFLAG+" ) values ( ? , ? ,? ,? , ? )");
         st.setInt(1, i.getqUserId());
         st.setInt(2, i.getqId());
         st.setDate(4,(java.sql.Date) i.getqDate());

         if(i.getqDesc().equals(""))
       	 {
             st.setNull(3, Types.INTEGER);
         }
         else
         {
              st.setString(3, i.getqDesc());
         }


         if(i.getFlag()==0 || i.getFlag()==1){
           st.setByte(5, i.getFlag());}
         else
             st.setNull(5,Types.INTEGER);
          int res = st.executeUpdate();

        if(res>0)
        {
            //JOptionPane.showMessageDialog(null ,"Data added successfully ");

           try
          {
             if(con!=null)
                 con.close();

            if(st!=null)
                   st.close();
         }catch(SQLException e) {}

            return 1;
        }
        else
        {

           try
          {
             if(con!=null)
                 con.close();

            if(st!=null)
                   st.close();
         }catch(SQLException e) {}

            //JOptionPane.showMessageDialog(null , "Sorry !! Couldnt add your data ");
            return 0;
        }
    }


     catch(SQLException e)
       {
               // JOptionPane.showMessageDialog(null,e.toString());
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

            if(st!=null)
                   st.close();
         }catch(SQLException e) {}
     }
    return 0;


}
public static void setAnsweredFlag(int qid) throws SQLException {


        Connection con =null ;
        Statement st = null;
        try
        {
             con = ConnectionFactory.getConnection();
             st = con.createStatement();
            int rs = st.executeUpdate(" update  "+TABLENAME+" set "+ANSWEREDFLAG+" =1 where "+QID+ " = "+qid);
        }

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
            if(con!=null)
                 con.close();
            if(st!= null)
                st.close();
        }
     
  }
   
}
