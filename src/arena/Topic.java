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
import java.util.ArrayList;

/**
 *
 * @author Sony
 */
public class Topic {

    private int topic_id , parent_id,lang_id,type;
    private String topic_name,links;
    private static String TABLENAME1="topics" , TABLENAME2="contents", TOPICNAME="topic_name" , TOPICID="topic_id", PARENTID="parent_id",LANGID="lang_id",TYPE="type",LINKS="links";
    /**
     * @return the topic_id
     */
    public int getTopic_id() {
        return topic_id;
    }

    /**
     * @param topic_id the topic_id to set
     */
    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    /**
     * @return the parent_id
     */
    public int getParent_id() {
        return parent_id;
    }

    /**
     * @param parent_id the parent_id to set
     */
    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    /**
     * @return the topic_name
     */
    public String getTopic_name() {
        return topic_name;
    }

    /**
     * @param topic_name the topic_name to set
     */
    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }
     /**
     * @return the lang_id
     */
    public int getLang_id() {
        return lang_id;
    }

    /**
     * @param lang_id the lang_id to set
     */
    public void setLang_id(int lang_id) {
        this.lang_id = lang_id;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
     /**
     * @return the links
     */
    public String getLinks() {
        return links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks(String links) {
        this.links = links;
    }




public static ArrayList<Topic> getAllTopics() throws SQLException
{
     ArrayList<Topic> ilist = new ArrayList<Topic>();

      Connection con =null ;
        try
        {
             con = (Connection) ConnectionFactory.getConnection();
             PreparedStatement st = (PreparedStatement) con.prepareStatement("Select * from "+TABLENAME1+" , "+TABLENAME2+" where "+TABLENAME1+"."+TOPICID+" = "+TABLENAME2+"."+TOPICID );
             ResultSet rs = st.executeQuery();

             if(!rs.next())
             {
              //  throw new NoTopicFoundException("No Topic Found");
                 return null;
             }


             do
             {
                 Topic i = new Topic();
                i.setTopic_id(rs.getInt(TOPICID));
                i.setParent_id(rs.getInt(PARENTID));
                i.setTopic_name(rs.getString(TOPICNAME));
                i.setLang_id(rs.getInt(LANGID));
                i.setType(rs.getInt(TYPE));
                i.setLinks(rs.getString(LINKS));
                ilist.add(i);
             }while(rs.next());
        }
        
       catch(SQLException e)
       {
      //    JOptionPane.showMessageDialog(null,e.toString());
       }
      catch(Exception ob)
      {
      //     JOptionPane.showMessageDialog(null,ob.toString());
    }
      con.close();
      return ilist;
  }

public static ArrayList<Topic> getAllSubTopicsByTopic(int parentid) throws SQLException
{
     ArrayList<Topic> ilist = new ArrayList<Topic>();

      Connection con =null ;
        try
        {
             con = (Connection) ConnectionFactory.getConnection();
             PreparedStatement st = (PreparedStatement) con.prepareStatement("Select * from "+TABLENAME1+" , "+TABLENAME2+" where "+TABLENAME1+"."+TOPICID+" = "+TABLENAME2+"."+TOPICID+" and "+TABLENAME1+"."+PARENTID+ " = "+ parentid);
             ResultSet rs = st.executeQuery();

             if(!rs.next())
             {
              //  throw new NoTopicFoundException("No Topic Found");
                 return null;
             }


             do
             {
                 
                Topic i = new Topic();
                i.setTopic_id(rs.getInt(TOPICID));
                i.setParent_id(rs.getInt(PARENTID));
                i.setTopic_name(rs.getString(TOPICNAME));
                i.setLang_id(rs.getInt(LANGID));
                i.setType(rs.getInt(TYPE));
                i.setLinks(rs.getString(LINKS));
                ilist.add(i);
             }while(rs.next());
        }

       catch(SQLException e)
       {
      //    JOptionPane.showMessageDialog(null,e.toString());
       }
      catch(Exception ob)
      {
      //     JOptionPane.showMessageDialog(null,ob.toString());
    }
      con.close();
      return ilist;
  }

   
   


}
