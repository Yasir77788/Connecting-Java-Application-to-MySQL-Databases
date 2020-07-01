// connecting Java Application to MySQL through Connector/J.
// Creating a Java application and pulling 
// information from a MySQL database.

package test1;
import java.sql.*;


// class 
public class NewMySql 
{
    Connection connection; // Connection object
    
    // private method to handle SQLException
    private void displaySQLErrors(SQLException e)
    {
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
    
    // constructor to handle the initialization.
    // It locates and instantiate the Connector/J JDBC driver.
    public NewMySql() throws InstantiationException, IllegalAccessException 
    {
        try 
        {
            // forName()dynamically loads a Java class at runtime.
            // newInstance() method instantiates a -
            // new object from the Driver class
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        }
        catch (ClassNotFoundException e) 
        {
            System.err.println("Unable to find and load driver");
            System.exit(1);
        }
    }
    
    // getting the connection to the database
    public void connectToDB() 
    {
        String url       = "jdbc:mysql://localhost:3306/accounts";
        String user      = "root";
        String password  = "MipFor#99";
	
        // create a connection to the database
        try 
        {
            connection = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException e) 
        {
            displaySQLErrors(e);
        }
    }
    
    // exectue query on the database
    public void executeSQL() 
    {
        try 
        {
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM Tname";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                System.out.print(rs.getInt(1) + " " +
                                   rs.getString(2) + " " +
                                   rs.getString(3));
                System.out.println();
                
            }
            rs.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) 
        {
            displaySQLErrors(e);
        }
    }
    
    // main method
    public static void main(String[] args) throws InstantiationException, IllegalAccessException 
    {
        // instantiate an object for NewMySql class
        NewMySql sqlObj = new NewMySql();
        sqlObj.connectToDB();
        sqlObj.executeSQL();
 
    }
}
