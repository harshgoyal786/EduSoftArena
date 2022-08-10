

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import arena.RESOLUTION;
import arena.FAQ;
import arena.User;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Sony
 */
public class SubmitanswerServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String rdesc = request.getParameter("desc");
        String addtofaq = (String) request.getParameter("addtofaq");
        String important = (String) request.getParameter("important");
        HttpSession session = request.getSession(false);
        String uname = (String) session.getAttribute("user");
        System.out.println("uname = "+uname);
        System.out.println("rdesc = "+rdesc);
        String sqid = request.getParameter("qid");
        System.out.println("sqid = " + sqid);
        String success=null;
        int qid=0,rid=-1;
        int userid=0,i=0;
        User u= new User();
        try {
            u = User.getUserByUserName(uname);
            userid= u.getUserId();
            qid = Integer.parseInt(sqid);

        } catch (SQLException ex) {
            Logger.getLogger(SubmitanswerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

         System.out.println("qid = "+qid);
        System.out.println("userid = "+userid);
        RESOLUTION r = new RESOLUTION();
        r.setLikes(0);
        r.setqId(qid);
        r.setrDate(new java.sql.Date (new java.util.Date().getTime()));
        r.setrDesc(rdesc);
        r.setrUserId(userid);
        try {
            success = RESOLUTION.addResolution(r);
           System.out.println("success session set = "+success);
         
          // request.setAttribute("success" , success);
          RequestDispatcher rd = request.getRequestDispatcher("submitanswer.jsp?success="+success);
          rd.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(SubmitanswerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<RESOLUTION> rlist = new ArrayList<RESOLUTION>();
        try {
            rlist = RESOLUTION.getAllResponsesByQueryId(qid);
        } catch (SQLException ex) {
            Logger.getLogger(SubmitanswerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        rid = rlist.get(i).getrId();
        System.out.println("ADD TO FAQ " + addtofaq);

        if(addtofaq==null) addtofaq = "";

        else if(addtofaq.equals("addtofaq"))
        {
            FAQ f  = new FAQ();
            f.setrId(rid);
            f.setqId(qid);
            try {
                FAQ.addFaq(f);
            } catch (SQLException ex) {
                Logger.getLogger(SubmitanswerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(important==null) important = "";

        else if(important.equals("important"))
        {


            try {
                RESOLUTION.updateImportant(qid);
            } catch (SQLException ex) {
                Logger.getLogger(SubmitanswerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  //  @Override
/*    protected void@Override
 void doGet(HttpServletRequest request, HttpServletResponse  response)
    throws ServletException, IOException {
        processRequest(request, response);
    } */

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 //   @Override
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse  response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    }

