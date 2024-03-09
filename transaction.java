import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class transaction
{
    private Connection connection;
    private Scanner scanner;
    public transaction(Connection connection,Scanner scanner)
    {
        this.connection=connection;
        this.scanner=scanner;
    }
    public void insertion()
    {
        String insertion_query="insert into transaction(transaction_name,Transaction_date,St_id,amount)values(?,?,?,?)";
        try
        {
            PreparedStatement preparedStatement= connection.prepareStatement(insertion_query);
            while(true) {
                System.out.println("Enter the transaction_name");
                String name = scanner.next();
                scanner.nextLine();
                System.out.println("Enter the transaction Date");
                String date = scanner.nextLine();
                System.out.println("Enter the student_id");
                int id= scanner.nextInt();
                System.out.println("Enter the amount\t");
                int amount= scanner.nextInt();

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, date);
                preparedStatement.setInt(3,id);
                preparedStatement.setInt(4,amount);
                preparedStatement.addBatch();
                System.out.println("Do you want to add more (Y/N)");
                String choice = scanner.next();
                if (choice.toUpperCase().equals("N"))
                {
                  break;
                }
            }
            int[] affect=preparedStatement.executeBatch();
            System.out.println("Insertion successfull");


        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
