/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.mooc.beans.Question;
import org.mooc.beans.QuestionType;
import org.mooc.beans.Topic;

/**
 *
 * @author Meenal
 */
public class Database {

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet res;
    private Statement stmt;

    public Database() {
        try {
            con = DBConnection.getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getQuestionType() method gets all the types of question present in the
     * database.This methods searches the database table question_type and for
     * each type it creates a object of QuestionType class and save the
     * information in it and then add to the list.
     *
     * @return List typeList containing question types
     */
    public List getQuestionType() {
        //creating a list to store the objects of QuestionType class
        List typeList = new ArrayList();
        try {
            if (con == null || con.isClosed()) { //checking whether con is null or not
                con=DBConnection.getDBConnection();
            }
            //creating a query statement to get the question type details.
            pstmt = con.prepareStatement("select typeid, typename, description from question_type order by typeid");
            //executing the query
            res = pstmt.executeQuery();
            while (res.next()) { //iterating throug the result set after executing the query]
                //creating an object of QuestionType class
                QuestionType type = new QuestionType();
                //setting values into variable of QuestionType class
                type.setTypeId(res.getInt("typeid"));
                type.setTypeName(res.getString("typename"));
                type.setDescription(res.getString("description"));
                //adding the type object to the list.
                typeList.add(type);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            DBConnection.closePStatement(pstmt);
            DBConnection.closeResultSet(res);
        }
        //return the list of all question types
        return typeList;
    }
    
    /**
     * getQuestionType() method gets all the types of question present in the
     * database.This methods searches the database table question_type and for
     * each type it creates a object of QuestionType class and save the
     * information in it and then add to the list.
     *
     * @return List typeList containing question types
     */
    public List getQuestionList() {
        //creating a list to store the objects of QuestionType class
        List QList = new ArrayList();
        try {
            if (con == null || con.isClosed()) { //checking whether con is null or not
                DBConnection.getDBConnection();
            }
            //creating a query statement to get the question type details.
            pstmt = con.prepareStatement("select qid, qtype, qdescription from question");
            //executing the query
            res = pstmt.executeQuery();
            while (res.next()) { //iterating throug the result set after executing the query]
                //creating an object of QuestionType class
                Question ques = new Question();
                //setting values into variable of QuestionType class
                ques.setqId(res.getInt("qid"));
                ques.setqType(res.getInt("qtype"));
                ques.setDescription(res.getString("qdescription"));
                //adding the type object to the list.
                QList.add(ques);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            DBConnection.closePStatement(pstmt);
            DBConnection.closeResultSet(res);
        }
        //return the list of all question types
        return QList;
    }
    
    
    

    /**
     * getTopicType() method gets all the types of topic present in the
     * database.This methods searches the database table topic and for each type
     * it creates a object of Topic class and save the information in it and
     * then add to the list.
     *
     * @return List topicList containing topic types
     */
    public List getTopicType() {
        //creating a list to store the objects of Topic class
        List topicList = new ArrayList();
        try {
            if (con == null || con.isClosed()) { //checking whether con is null or not
                DBConnection.getDBConnection();
            }
            //creating a query statement to get the topic details.
            pstmt = con.prepareStatement("select topicid, topicname from grader_topic order by topicname");
            //executing the query
            res = pstmt.executeQuery();
            while (res.next()) { //iterating through the result set after executing the query]
                //creating an object of Topic class
                Topic topic = new Topic();
                //setting values into variable of Topic class
                topic.setTopicId(res.getInt("topicid"));
                topic.setTopicName(res.getString("topicname"));
                //adding the topic object to the list.
                topicList.add(topic);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            DBConnection.closePStatement(pstmt);
            DBConnection.closeResultSet(res);
            DBConnection.closeDBConnection(con);
        }
        //return the list of all topic
        return topicList;
    }

    public int insertQDetails(Question quest) {
        int inserted = 0;
        int maxInt = 0;
        PreparedStatement pstmt2;
        try {
            if (con == null || con.isClosed()) {
                DBConnection.getDBConnection();
            }
            pstmt = con.prepareStatement("insert into question(qtype,qdescription,complexity,topicid, presentedby, verifiedby) values (?,?,?,?,?,?)");
            pstmt.setInt(1, quest.getqType());
            pstmt.setString(2, quest.getDescription());
            pstmt.setInt(3, quest.getComplexity());
            pstmt.setInt(4, quest.getTopicId());
            pstmt.setInt(5, quest.getPresentedBy());
            pstmt.setInt(6, 1);
            inserted = pstmt.executeUpdate();
            if(inserted == 1) {
                pstmt2 = con.prepareStatement("select max(qid) as maxid from question");
                res = pstmt2.executeQuery();
                if(res.next()) {
                    inserted = res.getInt("maxid");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closePStatement(pstmt);
            DBConnection.closeResultSet(res);
            DBConnection.closeDBConnection(con);
        }
        return inserted;
    }
    
    
    public Question getQuestion(int id) {
        //creating a list to store the objects of QuestionType class
        Question ques= new Question();
        try {
            if (con == null || con.isClosed()) { //checking whether con is null or not
                DBConnection.getDBConnection();
            }
            //creating a query statement to get the question type details.
            pstmt = con.prepareStatement("select qid, qtype, qdescription from question where qid = ?");
            pstmt.setInt(1, id);
            //executing the query
            res = pstmt.executeQuery();
                         //creating an object of QuestionType class
                //setting values into variable of QuestionType class
                while(res.next())
                {
                ques.setqId(res.getInt("qid"));
                ques.setqType(res.getInt("qtype"));
                ques.setDescription(res.getString("qdescription"));
                
                }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            DBConnection.closePStatement(pstmt);
            DBConnection.closeResultSet(res);
        }
        //return the list of all question types
        return ques;
    }
    
}
