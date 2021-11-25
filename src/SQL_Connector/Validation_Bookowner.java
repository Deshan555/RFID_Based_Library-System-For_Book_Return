/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL_Connector;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.Statement;

/**
 *
 * @author Jayashanka Deshan
 */
public class Validation_Bookowner 
{
    public static int book_data_load(int ID,int UID)
    {
        Connection connection = Connector.connection();
        
        String SQL = "SELECT * FROM LEND_TABLE WHERE BOOK_ID = "+ID+" AND USER_ID = "+UID+";";
        
        int value = 0;
        
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            if(!rs.next())
            {
                value = 0;
            }
            else
            {
                value = 1;
            }
        }
        catch(Exception ERROR)
        {
            
        }
        
        return value;
    }  

    
}
