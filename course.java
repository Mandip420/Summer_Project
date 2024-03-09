import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class course
 {


    private Connection connection;
    private Scanner scanner;

    public course(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void insertion() {

        try {
            String query = "insert into course(cname,course_des,school_year,trans_date)values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            while (true) {
                System.out.println("Enter the course_name");
                String name = scanner.next();
                System.out.println("Enter the course_description");
                String course_des = scanner.next();
                System.out.println("Enter the school_year");
                int school_year = scanner.nextInt();
                System.out.println("Enter the transaction date");
                String trans = scanner.next();
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, course_des);
                preparedStatement.setInt(3, school_year);
                preparedStatement.setString(4, trans);
                preparedStatement.addBatch();
                System.out.println("Add more (Y/N)");
                String choice = scanner.next();
                if (choice.toUpperCase().equals("N")) {
                    break;
                }
            }
            int[] result = preparedStatement.executeBatch();
            System.out.println("Insertion successfull");
        }    catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        public void deletion()
        {
            String deletion_query="delete from course where cid=?";
            try
            {
                PreparedStatement preparedStatement= connection.prepareStatement(deletion_query);
                System.out.println("Enter course id for delete");
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
     public void course_diplay()
     {
         String query="select * from course";
         try
         {
             PreparedStatement preparedStatement= connection.prepareStatement(query);
             ResultSet rs= preparedStatement.executeQuery();
             while (rs.next())
             {
                 int id=  rs.getInt("Cid");
                 String name=rs.getString("Cname");
                 String course_des=rs.getString("course_des");
                 int year=rs.getInt("School_year");
                 String trans=rs.getString("trans_date");
                 System.out.println("Course_id :"+id);
                 System.out.println("Course_name :"+name);
                 System.out.println("Course_description :"+course_des);
                 System.out.println("School_year :"+year);
                 System.out.println("Transactin date :"+trans);
                 System.out.println("*******************************************");
             }
         }
         catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
     }


 }


