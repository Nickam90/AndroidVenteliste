package com.f2016.dtu.androidventeliste;

/**
 * Created by nicka on 17-03-2016.
 */
import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DataAccess{
    private static final String url = "jdbc:mysql://db4free.net:3306/dtutest";
    private static final String user = "nickam90";
    private static final String pass = "Zitech90";

    public void testDBConn() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    String test = DriverManager.getDrivers().toString();
                    Log.d("DB", test);
                    //DriverManager.registerDriver();

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    //Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, user, pass);
        System.out.println("Database connection success");

                    String result = "Database connection success\n";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from Employee");
                    ResultSetMetaData rsmd = rs.getMetaData();

                    while (rs.next()) {
                        result += rsmd.getColumnName(1) + ": " + rs.getInt(1) + "\n";
                        result += rsmd.getColumnName(2) + ": " + rs.getString(2) + "\n";
                        result += rsmd.getColumnName(3) + ": " + rs.getString(3) + "\n";
                    }
                    Log.d("DBResult", result);
                } catch (Exception e) {
                    e.printStackTrace();

                }
                return null;
            }
        }.execute();

    }
}
