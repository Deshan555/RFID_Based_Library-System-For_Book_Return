
package SQL_Connector;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

public class date 
{
    public static String load_dates(int book_id) throws SQLException
    {
        String SQL = "SELECT * FROM LEND_TABLE WHERE BOOK_ID = "+book_id+" ";
        
        Connection connect = Connector.connection();
        
        String range = "Empty Slot";
        
        try
        {
            Statement stmt = connect.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
            {
               String due = rs.getString("DUE_DATE");
               
               String issue = rs.getString("ISSUE_DATE");
               
               range = issue+" - "+due;
            }
        }
        catch(Exception ERROR)
        {
            System.out.println(ERROR);
        }
        
        return range;
    }
 
}
