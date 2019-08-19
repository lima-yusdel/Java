import pkg.*;
import java.util.Scanner;

public class Main 
{
	static void getGrid(Scanner input, Grid grids[])
	{
	  while (true) {
      System.out.println("Enter the number of the grid you want to work with (0 - 49 only): ");
      int num = input.nextInt();
      if (num < 0 || num > 49) {
        System.out.println("Error: invalid grid number. Try again");
        continue;
      }
      getAction(input, num, grids);
    }
	}

	static void getAction(Scanner input, int num, Grid grids[])
	{
    while (true) {
			Agent A = new Agent(grids[num]);
      System.out.println("What action to perform on this grid?");
      System.out.println("(1) repeated forward A*");
      System.out.println("(2) repeated backward A*");
      System.out.println("(3) adaptive A*");
      System.out.println("(4) choose a new grid");

      int choice = input.nextInt();
      if (choice < 1 || choice > 4) {
        System.out.println("Error: invalid option. Try again");
        continue;
      } 
		
			if (choice == 4) {
				getGrid(input, grids); // ask for a new grid
			}
	
			System.out.println("\n\n");
      if (choice == 1) {
        A.repeatedForwardAStar();
      } else if (choice == 2) {
        A.repeatedBackwardAStar();
      } else {
        A.adaptiveAStar(); 
      }
			A.visualize();
			grids[num].resetAgentView();
			System.out.println("\n\n");
    }
	}


	public static void main(String[] args)
	{
		/* generate the 50 grid worlds */
		Grid grids[] = new Grid[50];
		for (int i = 0; i < 50; i++) {
			grids[i] = new Grid();
		}
		Scanner input = new Scanner(System.in);
		getGrid(input, grids);
		
		/*
		Grid grid = new Grid();
		grid.grid[0][0].blocked = false;
		grid.grid[0][1].blocked = false;
		grid.grid[0][2].blocked = false;
    grid.grid[0][3].blocked = false;
    grid.grid[0][4].blocked = false;
    grid.grid[1][0].blocked = false;
    grid.grid[1][1].blocked = false;
    grid.grid[1][2].blocked = false;
    grid.grid[1][3].blocked = false;
    grid.grid[1][4].blocked = false;
    grid.grid[2][0].blocked = false;
    grid.grid[2][1].blocked = false;
    grid.grid[2][2].blocked = false;
    grid.grid[2][3].blocked = false;
    grid.grid[2][4].blocked = false;
    grid.grid[3][0].blocked = false;
    grid.grid[3][1].blocked = false;
    grid.grid[3][2].blocked = false;
    grid.grid[3][3].blocked = false;
    grid.grid[3][4].blocked = false;
    grid.grid[4][0].blocked = false;
    grid.grid[4][1].blocked = false;
    grid.grid[4][2].blocked = false;
    grid.grid[4][3].blocked = false;
    grid.grid[4][4].blocked = false;
		grid.start = grid.grid[0][0];
		grid.goal  = grid.grid[4][4];
		
		Agent A = new Agent(grid);
		A.repeatedForwardAStar();
		A.visualize();*/
	}
}
