import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Listeners(Listener.class)
public class DBTest {
// Connection object
static Connection con = null;
// Statement object
private static Statement stmt;
// Constant for Database URL
public static String DB_URL = "jdbc:mysql://localhost:3306/test_schema";
//Database Username
public static String DB_USER = "root";
// Database Password
public static String DB_PASSWORD = "root";

@BeforeTest
public void setUp() throws Exception {
try{
// Database connection
String dbClass = "com.mysql.cj.jdbc.Driver";
Class.forName(dbClass).newInstance();
// Get connection to DB
Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
// Statement object to send the SQL statement to the Database
stmt = con.createStatement();
}
catch (Exception e)
{
e.printStackTrace();
}
}

@Test
public void test() {
try{
String query = "select * from test_table";
// Get the contents of userinfo table from DB
ResultSet res = stmt.executeQuery(query);
// Print the result untill all the records are printed
// res.next() returns true if there is any next record else returns false
while (res.next())
{
System.out.print("\n"+res.getString(1));
System.out.print("\t" + res.getString(2));

}
}
catch(Exception e)
{
e.printStackTrace();
}
}

@AfterTest
public void tearDown() throws Exception {
// Close DB connection
if (con != null) {
con.close();
}
}
}
