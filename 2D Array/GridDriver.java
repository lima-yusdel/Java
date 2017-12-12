
import java.util.Scanner;

/**
 *
 * @author
 */
public class GridDriver {

    public static void main(String[] args) 
	{ 
        int numRows;
        int numColumns;
        String command;
        Scanner f = new Scanner(System.in);
        
        System.out.print("Enter number of rows: ");
        numRows = f.nextInt();
        
        System.out.print("Enter number of Columns: ");
        numColumns = f.nextInt();

        GridClass myGrid = new GridClass(numRows, numColumns);
      
        myGrid.jump();
        f.nextLine();
        
        do {
            System.out.print("Enter command: ");
            command = f.nextLine();

            if (command.equalsIgnoreCase("n")) {
                myGrid.goNorth();
            } else {
                if (command.equalsIgnoreCase("s")) {
                    myGrid.goSouth();
            } else {
                if (command.equalsIgnoreCase("e")) {
                    myGrid.goEast();
            } else {
                if (command.equalsIgnoreCase("w")) {
                    myGrid.goWest();
            } else {
                if (command.equalsIgnoreCase("j")) {
                    myGrid.jump();
            } else {
                if (command.equalsIgnoreCase("q")) {
                    System.out.println("Exiting from (" + myGrid.currentRow() + "," + myGrid.currentColumn() + ") ");
            } else {
                    System.out.println("Invalid command, please enter either N, S, W, E, J, or Q");
                    }
					}		
                    }
                    }
                }
            }

        } while (!(command.equalsIgnoreCase("q")));

    }
}
