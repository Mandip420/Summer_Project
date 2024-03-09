import java.sql.*;
import java.util.Scanner;

public class Teacher
{
    private Connection connection;
    private Scanner scanner;
    public Teacher(Connection connection,Scanner scanner)
    {
        this.connection=connection;
        this.scanner=scanner;
    }
    public void insertion()
    {
        String insertion_query="insert into teacher(name,gender,age,contact,email,Salary,join_date,subject)values(?,?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement preparedStatement=connection.prepareStatement(insertion_query);
            while (true)
            {
                System.out.println("Enter Teacher name");
                String name=scanner.next();
                System.out.println("Enter sex in the form of (M,F,O)");
                String gender=scanner.next();
                System.out.println("Enter age");
                int age=scanner.nextInt();
                System.out.println("Enter teacher contact");
                String contact= scanner.next();
                System.out.println("Enter email");
                String email=scanner.next();
                scanner.nextLine();
                System.out.println("Enter salary");
                int salary=scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter join_date");
                String date=scanner.next();
                System.out.println("Enter teaching subject");
                String subject=scanner.next();
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,gender);
                preparedStatement.setInt(3,age);
                preparedStatement.setString(4,contact);
                preparedStatement.setString(5,email);
                preparedStatement.setInt(6,salary);
                preparedStatement.setString(7,date);
                preparedStatement.setString(8,subject);
                preparedStatement.addBatch();
                System.out.println("Add more values Y/N");
                String choice=scanner.next();
                if (choice.toUpperCase(). equals("N"))
                {
                    break;
                }
            }
            int [] batchresult=preparedStatement.executeBatch();
            System.out.println("Insertion successfull");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void deletion()
    {
        String deletion_query="delete from teacher where teacher_id=?";
        try
        {
            PreparedStatement preparedStatement= connection.prepareStatement(deletion_query);
            System.out.println("Enter Teacher_id");
            int id=scanner.nextInt();
            preparedStatement.setInt(1,id);
            int affect=   preparedStatement.executeUpdate();
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

    public void all_detail()
    {
        String query="select * from teacher";

        try
        {
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next())
            {
                int id=rs.getInt("teacher_id");
                String name=rs.getString("name");
                String gender=rs.getString("gender");
                int age=rs.getInt("age");
                String contact1=rs.getString("contact");
                String email1=rs.getString("email");
                int salary=rs.getInt("Salary");
                String date=rs.getString("join_date");
                String subject= rs.getNString("subject");
                System.out.println("Teacher_id :"+id);
                System.out.println("Name :"+name);
                System.out.println("Gender :"+gender);
                System.out.println("Age :"+age);
                System.out.println("Contact :"+contact1);
                System.out.println("email :"+email1);
               System.out.println("Salary :"+salary);
                System.out.println("Join_date :"+date);
                System.out.println("Subject :"+subject);
                System.out.println("**********************************");
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void teacher()
    {
        Teacher teacher=new Teacher(connection,scanner);
        System.out.println("1.Add the Teacher");
        System.out.println("2.Remove the Teacher");
        System.out.println("3.Get teacher Detail");
        System.out.println("Choose the option\t");
        int choice=scanner.nextInt();
        switch (choice)
        {
            case 1:
                teacher.insertion();
            case 2:
                teacher.deletion();
            case 3:
                teacher.all_detail();

        }

    }

}