import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class address
{
    private Connection connection;
    private Scanner scanner;
    public address(Connection connection,Scanner scanner)
    {
        this.connection=connection;
        this.scanner=scanner;
    }
    public void update()
    {
        String query="update address set address_name=? where address_id=?";
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            while(true) {
                scanner.nextLine();
                System.out.println("Enter updation id");
                int id= scanner.nextInt();
                System.out.println("Rename address");
                String name = scanner.next();
                preparedStatement.setString(1,name);
                preparedStatement.setInt(2, id);
                preparedStatement.addBatch();
                System.out.println("Update more (Y/N)");
                String choice= scanner.next();
                if (choice.toUpperCase().equals("N"))
                {
                    break;

                }
            }
                     int [] result=preparedStatement.executeBatch();
                      System.out.println("Updation successfull");
                       return;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
              public void insertion()
    {
        String insertion_query="insert into address(address_name)values(?)";
        try
       {
           System.out.println("Enter name");
           String name=scanner.next();
           PreparedStatement preparedStatement= connection.prepareStatement(insertion_query);
           preparedStatement.setString(1,name);
          int affect= preparedStatement.executeUpdate();
          if(affect>0)
          {
              System.out.println("insertion successfull");
          }
          else
          {
              System.out.println("insertion Failed");
          }
       }
       catch (SQLException e)
       {
           System.out.println(e.getMessage());
       }
    }
    public void delete()
    {
        String query="delete from address where address_id=?";
        try
        {
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            scanner.nextLine();
            System.out.println("Enter deletion id");
            int id= scanner.nextInt();
            preparedStatement.setInt(1,id);
           int affect= preparedStatement.executeUpdate();
           if(affect>0)
           {
               System.out.println("Deletion successfull");
           }
           else
           {
               System.out.println("Failed");
           }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
            public void address_diplay()
            {
                String query="select * from address";
                try
                {
                    PreparedStatement preparedStatement= connection.prepareStatement(query);
                    ResultSet rs= preparedStatement.executeQuery();
                    while (rs.next())
                    {
                      int id=  rs.getInt("address_id");
                      String name=rs.getString("address_name");
                        System.out.println("Address_id :"+id);
                        System.out.println("Address_name :"+name);
                        System.out.println("*******************************************");
                    }
                }
                catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }

}
