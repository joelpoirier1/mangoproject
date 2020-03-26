package EventSourcing.Database;

public interface DatabaseCredentials {
    String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    //Local URL:
    String Database_URL = "jdbc:mysql://localhost:3306/mango?serverTimezone=UTC";
    //Local Username:
    String DB_Username = "newuser";
    //Local Password:
    String DB_Password = "password";
}
