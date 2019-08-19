package pkg;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;

import java.awt.*;
import java.applet.Applet;

/* object representing the grid/search space */
public class Grid
{
	static final int ROWS = 101;
	static final int COLS = 101;
  
	public Block[][] grid = new Block[ROWS][COLS];
	public Block original_start;
	public Block original_goal;
	public Block start = null;
	public Block goal = null;

	public Grid() {
		initGrid();
		genGrid();
		setStart();
		setGoal();
	}


	/* initialize grid of Blocks */
	void initGrid()
	{
		for(int i=0; i<this.ROWS; i++) {
			for(int j=0; j<this.COLS; j++) {
				grid[i][j] = new Block(i, j);
			}
		}
	}


	/* reset all values dealing with the agent:
   *  agent_view, in_frontier, g, h, parent*/
	public void resetAgentView()
	{
		for(int i=0; i<this.ROWS; i++) {
			for(int j=0; j<this.COLS; j++) {
				grid[i][j].agent_view  = '0';
				grid[i][j].in_frontier = false;
				grid[i][j].traveled = false;
				grid[i][j].g = Integer.MAX_VALUE;
				grid[i][j].h = Integer.MAX_VALUE;
				grid[i][j].parent = null;
				start = original_start;
				goal = original_goal;
			}
		}
	}

	/* fetches all UNVISITED neighbors of Block cur */
	private ArrayList<Block> getUnvisitedNeighbors(Block cur, boolean[][] visited)
	{
		ArrayList<Block> neighbors = new ArrayList<Block>();

		if ((cur.row-1) >= 0 && visited[cur.row-1][cur.col] == false)   { neighbors.add(grid[cur.row-1][cur.col]);}
		if ((cur.col+1) < COLS && visited[cur.row][cur.col+1] == false) { neighbors.add(grid[cur.row][cur.col+1]);}
		if ((cur.row+1) < ROWS && visited[cur.row+1][cur.col] == false) { neighbors.add(grid[cur.row+1][cur.col]);}
		if ((cur.col-1) >= 0 && visited[cur.row][cur.col-1] == false)   { neighbors.add(grid[cur.row][cur.col-1]);}
		
		return neighbors;
	}


	/* fetch ALL neighbors of Block cur */
	public ArrayList<Block> getNeighbors(Block cur)
	{
		ArrayList<Block> neighbors = new ArrayList<Block>();
	
		if ((cur.row-1) >= 0)   { neighbors.add(grid[cur.row-1][cur.col]); }
		if ((cur.col+1) < COLS) { neighbors.add(grid[cur.row][cur.col+1]); }
		if ((cur.row+1) < ROWS) { neighbors.add(grid[cur.row+1][cur.col]); }
		if ((cur.col-1) >= 0)   { neighbors.add(grid[cur.row][cur.col-1]); }
		
		return neighbors;
	}

	
	/* observes all neighbors of Block cur; sets neighbors agent_view field
 	 * to true if the neighbor is a blocked cell
 	 */
	public void observeNeighbors(Block cur)
	{
		Block neighbor;
		
		if ((cur.row-1) >= 0) {
			neighbor = grid[cur.row-1][cur.col];
			if (neighbor.blocked == true) { 
				neighbor.agent_view = '2'; 
			} else {
				neighbor.agent_view = '1';
			}
		}
		if ((cur.col+1) < COLS) {
			neighbor = grid[cur.row][cur.col+1];
			if (neighbor.blocked == true) { 
				neighbor.agent_view = '2'; 
			} else {
				neighbor.agent_view = '1';
			}
		}
		if ((cur.row+1) < ROWS) {
			neighbor = grid[cur.row+1][cur.col];
			if (neighbor.blocked == true) { 
				neighbor.agent_view = '2'; 
			} else {
				neighbor.agent_view = '1';
			}
		}
		if ((cur.col-1) >= 0) {
			neighbor = grid[cur.row][cur.col-1];
			if (neighbor.blocked == true) { 
				neighbor.agent_view = '2'; 
			} else {
				neighbor.agent_view = '1';
			}		
		}
	}


	/* calculates h-value for Block cur */
	public int calculateH(Block cur)
	{
		return Math.abs(cur.row - this.goal.row) + Math.abs(cur.col - this.goal.col);
	}


	/* fetches a random grid block that isn't blocked, the start block, nor the goal block */
	private Block randomUnblocked()
	{
		Random rand = new Random();
		
		int row = rand.nextInt(ROWS);
		int col = rand.nextInt(COLS);

		while (grid[row][col].blocked == true || grid[row][col] == start || grid[row][col] == goal) {
			row = rand.nextInt(ROWS);
			col = rand.nextInt(COLS);
		}

		return grid[row][col];
	}


	public void setGoal()
	{
		this.start = randomUnblocked();
		this.original_start = this.start;
	}


	public void setStart()
	{
		this.goal = randomUnblocked();
		this.original_goal = this.goal;
	}


  /* returns true if all blocks visited; false is not */
  boolean allVisited(boolean[][] visited)
  {
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        if (visited[i][j] == false) {
          return false;
        }
      }
    }

    return true;
  }

	
	/* restarts genGridDFS until it visits all Blocks in grid */
	void genGrid()
	{
		boolean visited[][] = new boolean[ROWS][COLS];
		
		do {
			genGridDFS(visited);
		} while(!allVisited(visited));
	}


	/* generate grid with DFS */
	void genGridDFS(boolean[][] visited)
	{
		Random rand = new Random();
		int strt_row    = rand.nextInt(this.ROWS);
		int strt_col    = rand.nextInt(this.COLS);

		Stack<Block> frontier = new Stack<Block>();
		frontier.push(grid[strt_row][strt_col]);

		while(!frontier.empty()) {
			Block cur = frontier.pop(); // pop first item off stack
			visited[cur.row][cur.col] = true;
			
			ArrayList<Block> neighbors = getUnvisitedNeighbors(cur, visited);
			if (neighbors.size() == 0) {
				continue; // dead-end
			}
			
			/* pick a random unvisited neighbor and probabilistically block/unblock it */
			int i = rand.nextInt(neighbors.size());
			Block chosen = neighbors.remove(i);
			int p = rand.nextInt(10);
			if (p < 3) {
				chosen.blocked = true;
				continue;
			}
				
			frontier.push(chosen);						
		}	
	}


  public void printGrid()
  {
    for (int i=0; i < this.ROWS; i++) {
      for (int j=0; j < this.COLS; j++) {
        System.out.print(this.grid[i][j].blocked ? "x" : "o");       
			}
			System.out.println();
    }
	}

	public void printVals()
	{
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				System.out.println("block: (" + this.grid[i][j].row + ", " + this.grid[i][j].col + ")");
				System.out.println("g : " + this.grid[i][j].g);
				System.out.println("h : " + this.grid[i][j].h);
			}
			System.out.println("");
		}
	}

	/* output the agents view of the board */
	public void printAgentView() {
		for (int i = 0; i < this.ROWS; i++) {
			for (int j = 0; j < this.COLS; j++) {
				if (this.grid[i][j].agent_view == '0') {
					System.out.println("o");
				} else if (this.grid[i][j].agent_view == '1') {
					System.out.println("s");
				} else if (this.grid[i][j].traveled == true) {
					System.out.println("w");
				} else {
					System.out.println("b");
				}
			}
			System.out.println();
		}
	}
}
