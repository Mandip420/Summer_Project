import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class student
{
    private Connection connection;
    private Scanner scanner;
    public student(Connection connection,Scanner scanner)
    {
        this.connection=connection;
        this.scanner=scanner;
    }
    public void insertion()
    {
        String insertion_query="insert into student(name,gender,age,St_father_name,contact,St_email,St_address)values(?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement preparedStatement=connection.prepareStatement(insertion_query);
            while (true)
            {
                System.out.println("Enter name");
                String name=scanner.next();
                System.out.println("Enter sex in the form of (M,F,O)");
                String gender=scanner.next();
                System.out.println("Enter age");
                int age=scanner.nextInt();
                System.out.println("Enter Student father name");
                String St_father= scanner.next();
                System.out.println("Enter contact number");
                String contact=scanner.next();
                scanner.nextLine();
                System.out.println("Enter email address");
                String email=scanner.next();
                scanner.nextLine();
                System.out.println("Enter Student Address");
                String address=scanner.next();
                 preparedStatement.setString(1,name);
                 preparedStatement.setString(2,gender);
                 preparedStatement.setInt(3,age);
                preparedStatement.setString(4,St_father);
                 preparedStatement.setString(5,contact);
                 preparedStatement.setString(6,email);
                 preparedStatement.setString(7,address);
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
        String deletion_query="delete from student where St_id=?";
        try
        {
            PreparedStatement preparedStatement= connection.prepareStatement(deletion_query);
            System.out.println("Enter id");
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
        String query="select * from student";

        try
        {
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next())
            {
                int sid=rs.getInt("St_id");
                String name=rs.getString("name");
                String gender=rs.getString("gender");
                int age=rs.getInt("age");
                String father=rs.getString("St_father_name");
                String contact=rs.getString("contact");
                String email=rs.getString("St_email");
                String address=rs.getString("St_address");
                System.out.println("St_id :"+sid);
                System.out.println("Name :"+name);
                System.out.println("Gender :"+gender);
                System.out.println("Age :"+age);
                System.out.println("St_Father_name:"+father);
                System.out.println("Contact :"+contact);
                System.out.println("St_email :"+email);
                System.out.println("St_address :"+address);
                System.out.println("**********************************");
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void st()
    {
        student student=new student(connection,scanner);
        System.out.println("Enter your choice\t");
        System.out.println("1.Add student");
        System.out.println("2.delete student");
        System.out.println("3.Get all student_detail");
        int x= scanner.nextInt();
        switch (x)
        {
            case 1:
                student.insertion();
                break;
            case 2:
                student.deletion();
                break;
            case 3:
                student.all_detail();
                break;
            default:
                System.out.println("Wrong input choose the valid one");
        }
    }


}
