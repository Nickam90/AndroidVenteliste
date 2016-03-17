package com.f2016.dtu.androidventeliste;

/**
 * Created by nicka on 17-03-2016.
 */
import android.util.Log;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DataAccess {
    private static final String url = "jdbc:mysql://<server>:<port>/<database>";
    private static final String user = "<username>";
    private static final String pass = "<password>";

    public void testDBConn() {

        try {
            String test = DriverManager.getDrivers().toString();
            Log.d("DB", test);
            //DriverManager.registerDriver();

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
        /* System.out.println("Database connection success"); */

            String result = "Database connection success\n";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from table_name");
            ResultSetMetaData rsmd = rs.getMetaData();

            while(rs.next()) {
                result += rsmd.getColumnName(1) + ": " + rs.getInt(1) + "\n";
                result += rsmd.getColumnName(2) + ": " + rs.getString(2) + "\n";
                result += rsmd.getColumnName(3) + ": " + rs.getString(3) + "\n";
            }
           Log.d("DBResult", result);
        }
        catch(Exception e) {
            e.printStackTrace();

        }
    }
}
