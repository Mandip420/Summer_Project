import java.util.Scanner;
import java.sql.*;

public class register
{
    private Connection connection;
    private Scanner scanner;
    public register(Connection connection,Scanner scanner)
    {
        this.connection=connection;
        this.scanner=scanner;
    }
    public void register()
    {

        System.out.println("Enter your full name");
        String full_name=scanner.next();
        scanner.nextLine();
        System.out.println("Enter your Email");
        String email= scanner.next();
        scanner.nextLine();
        System.out.println("Enter your password");
        String password=scanner.next();
        if (user_exist(email))
        {
            System.out.println("User already exist");
            return;
        }

        String register_query="insert into admin(Name,Email,password)values(?,?,?)";
        try
        {
            PreparedStatement preparedStatement=connection.prepareStatement(register_query);
            preparedStatement.setString(1,full_name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
            int affect=preparedStatement.executeUpdate();
            if (affect>0)
            {
                System.out.println("Register successfull");

            }
            else
            {
                System.out.println("Registration failed");
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());

        }
        throw new RuntimeException();

    }
    public String login()
    {
        Teacher teacher=new Teacher(connection,scanner);
        student student=new student(connection,scanner);
        transaction transaction=new transaction(connection,scanner);
        register user=new register(connection,scanner);
        System.out.println("Enter your email");
        String email= scanner.next();
        scanner.nextLine();
        System.out.println("Enter your password");
        String password=scanner.nextLine();
        String login_query="Select * from admin where email=? and password=?";
        try
        {
            PreparedStatement preparedStatement= connection.prepareStatement(login_query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();

            if (resultSet.next())
            {
                System.out.println("In which field you want to do operation");
                System.out.println("1.Studentt\t\t\t2.Teacher");
                System.out.println();
                System.out.println("3.Transaction\t\t\t4.Delete_Admin");
                int choice=scanner.nextInt();
                switch (choice)
                {
                    case 1:
                        student.st();
                        break;
                    case 2:
                        teacher.teacher();
                        break;
                    case 3:
                        transaction.insertion();
                        break;
                    case 4:
                        user.admin_delete();
                        break;

                }


                teacher.teacher();
                return "Found";
            }
            else
            {
                System.out.println("Incorrect email or password");
                return null;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public boolean user_exist(String email)
    {
        String query="Select * from admin where email=?";
        try
        {
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            ResultSet resultSet= preparedStatement.executeQuery();

            if (resultSet.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return false;
    }
    public void admin_delete()

        {
            String deletion_query="delete from admin where email=?";
            try
            {
                PreparedStatement preparedStatement= connection.prepareStatement(deletion_query);
                System.out.println("Enter email");
                String email=scanner.next();
                preparedStatement.setString(1,email);
                int affect=preparedStatement.executeUpdate();
                if(affect>0)
                {
                    System.out.println("Deletion successfull");
                }
                else {
                    System.out.println("Failed");
                }
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }


public static void menu(Connection connection,Scanner scanner)
{
    int x=scanner.nextInt();
    System.out.println("1.");
}
}
