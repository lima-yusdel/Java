package lab07treestesterf16;
import java.util.Scanner;
public class Lab07TreesTesterF16 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        String userSelection;
        
        do
        {
            System.out.println("\n=======================================");
            System.out.println("Lab 7 Runner...Test with:\n");
            System.out.println("I - Integer Class");
            System.out.println("C - Car Class");

            System.out.print("\nEnter Choice: (Q to quit): ");
            userSelection = input.nextLine();
            
            switch (userSelection.toLowerCase())
            {
                case"i":
                        System.out.println("\n===================INTEGER====================");
                        Lab07RunTestTreeWithInteger.runMe();
                        break;
                case "c":
                        System.out.println("\n===================CAR====================");
                        Lab07RunTestTreeWithCar.runMe();
                        break;
                case "q":
                        System.out.println("\n===================QUIT====================");
                        System.out.println("\nbye bye!");
                        break;
                default:
                    System.out.println("\nhuh?");
            }
            
        } while ( !(userSelection.equalsIgnoreCase("Q")) );
    }
    
}
