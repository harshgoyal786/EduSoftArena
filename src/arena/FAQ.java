 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class FAQ {
    
    private int faqId , qId , rId , likes;
    private static String TABLENAME = "FAQ" , LIKES = "LIKES" , QID="QID" , RID="RESOLUTION_ID" , FAQID="FAQ_ID"; 

    /**
     * @return the faqId
     */
    public int getFaqId() {
        return faqId;
    }

    /**
     * @param faqId the faqId to set
     */
    public void setFaqId(int faqId) {
        this.faqId = faqId;
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

   
    
    
    public  static void addFaq(FAQ i) throws SQLException{
	 
        Connection con = null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try
        {
               con = ConnectionFactory.getConnection();
               st = con.prepareStatement("Insert into "+TABLENAME+" ( "+FAQID+","+QID+","+RID+" ) values ( ? , ? ,?)");
               st.setInt(1, i.getFaqId());
               st.setInt(2, i.getqId());
               st.setInt(3, i.getrId());
              // st.setInt(4, i.getLikes());

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
              //  JOptionPane.showMessageDialog(null,e.toString());
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
}
public static boolean isFAQ(int qid) throws SQLException
{
       Connection con =null ;
       PreparedStatement st =null;
        ResultSet rs=null;
        try
        {
             con = ConnectionFactory.getConnection();
             st = con.prepareStatement("Select "+QID+" from "+TABLENAME+" where "+QID+" = "+ qid  );
             rs = st.executeQuery();

               if(!rs.next())
             {
                //trow new NoQueryFoundException("No  Found");
                 return false;
             } 
             else
              return true;

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

 public static ArrayList<FAQ> getFAQOrderByLikes() throws SQLException
 {
      
     FAQ i = null ;
     ArrayList<FAQ> ilist = new ArrayList<FAQ>();
      Connection con =null ;
      PreparedStatement st=null;
      ResultSet rs=null;
        try
        {
             con = ConnectionFactory.getConnection();
              st = con.prepareStatement("Select * from "+TABLENAME );
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
                     i = new FAQ();    
                    // i.setLikes(rs.getInt(LIKES));
                     i.setqId(rs.getInt(QID));
                     i.setrId(rs.getInt(RID));
                     i.setFaqId(rs.getInt(FAQID));
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
 
 public static FAQ getFAQByQueryId(int qid)throws SQLException//,NoFAQFoundException
 {
     
      FAQ i = null ;
      
      Connection con =null ;
      PreparedStatement st=null;
       ResultSet rs=null;
        try
        {
             con = ConnectionFactory.getConnection();
              st = con.prepareStatement("Select * from "+TABLENAME+" where "+ QID + " = " +qid );
            rs = st.executeQuery();

               if(rs.next())
             {
                    i = new FAQ();    
                     //i.setLikes(rs.getInt(LIKES));
                     i.setqId(rs.getInt(QID));
                     i.setrId(rs.getInt(RID));
                     i.setFaqId(rs.getInt(FAQID));
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
      
      return i;
 }
 
 public static ArrayList<FAQ> getFAQByReservedWords(String word) throws SQLException
 {
 
       ArrayList<FAQ> f = new ArrayList<FAQ>();
       
       
      try{  
       ArrayList<QUERY> q=new ArrayList<QUERY>();
       q=QUERY.getQueryByReservedWords(word);
     
      int i=0;
      
      ArrayList<Integer> c = new ArrayList<Integer>();
      c=RESOLUTION.getResoltuionByReservedWords(word);
      if(q!=null){
		   if(c==null)
		    c=new ArrayList<Integer>();
      while(i<q.size())
      {
          c.add(q.get(i).getqId());
          i++;
      }}
      if(c==null || c.size()==0)
         return null;
      
      HashSet<Integer> hs=new HashSet<Integer>();
      hs.addAll(c);
      c.clear();
      c.addAll(hs);
      i=0;
      
      
      while ( i<c.size())
      {
		  if(isFAQ(c.get(i)))
          f.add(getFAQByQueryId(c.get(i)));
          i++;
      }
      
       }catch(SQLException se){}
    
		
      return f;

 }
 
 public static ArrayList<FAQ> getAllFAQs() throws SQLException
 {
      
     FAQ i = null ;
     ArrayList<FAQ> ilist = new ArrayList<FAQ>();
      Connection con =null ;
      PreparedStatement st=null;
      ResultSet rs=null;
        try
        {
             con = ConnectionFactory.getConnection();
             st = con.prepareStatement("Select * from "+TABLENAME );
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
                     i = new FAQ();    
                     i.setFaqId(rs.getInt(FAQID));
                     i.setqId(rs.getInt(QID));
                     i.setrId(rs.getInt(RID));
                     ilist.add(i);
             }while (rs.next());
        
        	
        }
        
     	catch(SQLException e)
       	{
			System.out.println("Error getting FAQ"+e.getMessage());
         // JOptionPane.showMessageDialog(null,e.toString());
      	}
      catch(Exception ob)
        {
			System.out.println("Error getting FAQ"+ob.getMessage());
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
