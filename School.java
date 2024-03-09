import javax.print.MultiDocPrintService;
import java.sql.*;
import java.util.Scanner;

public class School {
    private static final String url = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String username = "root";
    private static final String password = "Mandip420@";

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("connection established");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            register user = new register(connection, scanner);
            System.out.println("1.Register");
            System.out.println("2.Login");
            System.out.println("3.Exit");
            int choice= scanner.nextInt();
            switch (choice)
            {
                case 1:
                    user.register();
                case 2:
                    user.login();
                case 3:
                    return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}