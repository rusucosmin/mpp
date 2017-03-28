package ro.ubb.bookstore.server.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import java.lang.reflect.Field;
import java.sql.*;

public class DatabaseConnection {
    private Connection conn = null;

    public DatabaseConnection(String DB_URL, String USER, String PASS) {
        try {
            // STEP 2: Register JDBC driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: Open a connection
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(SQLException se) {
            // Handle errors for JDBC
            System.err.println(se);
            System.exit(0);
            // se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            System.err.println(e);
            // e.printStackTrace();
        }
    }

    protected ResultSet executeQuery(String sqlQuery) {
        Statement stmt = null;
        ResultSet ret = null;

        try {
            stmt = this.conn.createStatement();
            ret = stmt.executeQuery(sqlQuery);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // finally {


        //     try {
        //         if(stmt != null)
        //             stmt.close();
        //     } catch(SQLException e) {
        //         e.printStackTrace();
        //     }

            // better close the connection when the repo gets destroyed
            // try {
            //     if(this.conn != null)
            //         this.conn.close();
            // } catch(SQLException e) {
            //     e.printStackTrace();
            // }
        // }
        return ret;
    }

    protected void executeUpdate(String sqlQuery) {
        Statement stmt = null;

        try {
            stmt = this.conn.createStatement();
            stmt.executeUpdate(sqlQuery);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
