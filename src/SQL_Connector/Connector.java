package SQL_Connector;

import Sql_Lite.Load_SQL;

import java.sql.Connection;

import java.sql.DriverManager;

public class Connector
{
    public static Connection connection()
    {
        Connection conn = null;
        
        String host = Load_SQL.load_settings("HOST");
        
        String port = Load_SQL.load_settings("PORT");
        
        String user_name = Load_SQL.load_settings("UNAME");
        
        String password = Load_SQL.load_settings("PASSWORD");
        
        String db_name = Load_SQL.load_settings("DBNAME");

        String database_url = "jdbc:mysql://"+host+":"+port+"/"+db_name;

        try
        {
           conn = DriverManager.getConnection(database_url, user_name, password);

            System.out.println("Connected !!");
        }
        catch (Exception ERROR)
        {
            System.out.println("Error Message : "+ERROR);
        }
        return conn;
    }
}
