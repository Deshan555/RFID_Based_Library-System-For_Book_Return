/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL_Connector;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

public class take_count 
{
    public static String get_RowCount(int ID)
    {
        Connection connection = Connector.connection();

        String SQL = "SELECT count(LEND_ID) FROM LEND_TABLE WHERE USER_ID = "+ID+";";
        
        String count = null;
        
        try
        {
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next())
        {
            count = resultSet.getString("count(LEND_ID)");
        }
        }
        catch(SQLException ERROR)
        {
            System.out.println(ERROR);
        }
        return count;
    }

}
