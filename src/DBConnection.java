/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mayank
 */
public class DBConnection {

    /**
     * Creates a new instance of DBConnection
     */
    public DBConnection() {
    }

    /*
     *
     */
    public static Connection getDBConnection() {
        Connection con = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not load driver class " + e);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:8080/arena", "root", "12345");
        } catch (SQLException e) {
            System.out.println("Could not get connection " + e);
        }
        return con;
    }

    public synchronized static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                System.out.println("Could not close Statement " + e);
            }
        }
    }

    public synchronized static void closePStatement(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
                pstmt = null;
            } catch (SQLException e) {
                System.out.println("Could not close Prepared Statement " + e);
            }
        }
    }

    public synchronized static void closeResultSet(ResultSet res) {
        if (res != null) {
            try {
                res.close();
                res = null;
            } catch (SQLException e) {
                System.out.println("Could not close ResultSet " + e);
            }
        }
    }

    public static synchronized boolean isClosed(Connection con) {
        boolean isClosed = false;
        try {
            if (con == null || con.isClosed()) {
                isClosed = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isClosed;
    }

    public static void closeDBConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            System.out.println("Could not close connection " + e);
        }
    }
}
