package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {
    public static final String URL = "jdbc:mysql://localhost:3307/sportnova";
    public static final String USER = "root";
    public static final String PWD = "";

    private Connection cnx;
    private static MyDataBase myDataBase;

    private MyDataBase() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static MyDataBase getMyDataBase() {
        if (myDataBase == null)
            myDataBase = new MyDataBase();
        return myDataBase;
    }

    public Connection getCnx() {
        return cnx;
    }
}
