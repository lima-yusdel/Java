package pkg;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Stack;

/* object that acts as agent and performs searches and traversal on a grid */
public class Agent
{
	public Grid grid; // grid the agent is operating on
	public Block original_start;	// original start of this grid
	public Block original_goal;   // original goal of this grid
	
	public int expansions = 0;		// number of cell expansions for a search

	public Agent(Grid grid) {
		this.grid = grid;
		this.original_start = grid.start;
		this.original_goal  = grid.goal;
	}

	/* makes java Swing grid visualization */
	public void visualize()
	{
		Layout swing_grid = new Layout(grid, original_start, original_goal);
	}


  /* returns a stack with the path from start block to goal. relies
   * on A* filling parent node for each block on path */
  public Stack<Block> tracePath(int type)
  {
    Stack<Block> pathStack = new Stack<Block>();

    Block ptr = grid.goal;

    while(ptr != grid.start)
    {
      pathStack.push(ptr);
      ptr = ptr.parent;
    }

    pathStack.push(ptr);

		if (type == 1) {
			Stack<Block> reverseStack = new Stack<Block>();
			while (pathStack.empty() == false) {
				reverseStack.push(pathStack.pop());
			}
			return reverseStack;
		} else {
    	return pathStack;
		}
  }

	
  /* executes on generated path; returns goal if reached goal; else, new starting block */
  public Block executePath(Stack<Block> pathStack, int type)
  {
    Block prev = null;

    while(!pathStack.empty()) {
      Block cur = pathStack.pop();
			if (type == 1) {
				if (cur == grid.start) {
					return grid.start;
				}
			} else {
      	if (cur == grid.goal) {
        	return grid.goal;
      	}
			}

      if (cur.blocked == true) {
        return prev; // new-starting node
      }

      grid.observeNeighbors(cur);
			if (type == 1) {
				if (cur != original_goal) { cur.traveled = true; }
			} else {
				if (cur != original_start) { cur.traveled = true; } // important line: makes sure traveled blocks turn YELLOW
			}
      prev = cur;
    }

    return null;
  }


  public void repeatedAStar(int type)
  {
    while (true) {
			if (type == 2) {
				if (runAdaptiveAStar() == -1) {
					System.out.println("Cannot reach goal state from start state");
					return;
				}
			} else {
      	if (AStar() == -1) {
        	System.out.println("cannot reach goal state from start state");
        	return;
      	}
			}

      Stack<Block> route = tracePath(type);
      Block ret = executePath(route, type);

			if (type == 1) {
				if (ret == grid.start) {
					System.out.println("reached goal. exiting");
					return;
				}
			} else {
      	if (ret == grid.goal) {
        	System.out.println("reached goal. exiting");
        	return;
      	}
			}

			if (type == 1) {
				grid.goal = ret; // backward
			} else {
      	grid.start = ret; // sets new start state to run A* again
			}
    }
  }


	public void repeatedForwardAStar()
	{
		grid.start = original_start;
		grid.goal  = original_goal;
		repeatedAStar(0);
		printStatistics();
	}


	public void repeatedBackwardAStar()
	{
		grid.start = original_goal;
		grid.goal  = original_start;
		repeatedAStar(1);
		printStatistics();
	}
	

	public void adaptiveAStar()
	{
		grid.start = original_start;
		grid.goal  = original_goal;
		repeatedAStar(2);
		printStatistics();
	}


	/* runs adaptive A* */
	private int runAdaptiveAStar()
	{
    Heap frontier = new Heap();
    LinkedList<Block> closed = new LinkedList<Block>();

    grid.observeNeighbors(grid.start);
    grid.start.g = 0;
    grid.start.h = grid.calculateH(grid.start);
    frontier.insert(grid.start);

    while (true) {
      if (frontier.isEmpty() == true) { return -1; }
      Block cur = frontier.pop();
      if (cur == grid.goal) {
        frontier.clear(0);
				updateClosedH(closed); // updates the h-value of Blocks in closed list so they get used for the next run of adaptive A*
        return 1; 
      }

      closed.add(cur);
			this.expansions++;

      ArrayList<Block> neighbors = grid.getNeighbors(cur);
      for (Block neighbor : neighbors) {
        if (neighbor.agent_view == '2') {
          continue;  // dead-end; agent knows it is blocked
        }

        int g = cur.g + 1;

        if (closed.contains(neighbor) == false && neighbor.in_frontier == false) {
          neighbor.g = g;
          if (neighbor.h == Integer.MAX_VALUE) { /* don't reset h-value of an already expanded block */
    	      neighbor.h = grid.calculateH(neighbor); 
          }
          neighbor.parent = cur;
          frontier.insert(neighbor);
        } else if (neighbor.in_frontier == true && g < neighbor.g) {
          neighbor.g = g;
          neighbor.parent = cur;
          frontier.removeItem(neighbor);
          frontier.insert(neighbor);
        } 
      }
    }	
	}


	/* updates the h-value of all expanded blocks in a search using the adaptive A* heuristic*/
	private void updateClosedH(LinkedList<Block> closed)
	{
		for (Block b : closed) {
			b.h = grid.goal.g - b.g;
		}
	}


	/* runs A* */
  private int AStar()
  {
		Heap frontier = new Heap();
		LinkedList<Block> closed = new LinkedList<Block>();

    grid.observeNeighbors(grid.start);
    grid.start.g = 0;
		grid.start.h = grid.calculateH(grid.start);
    frontier.insert(grid.start);
	
    while (true) {
      if (frontier.isEmpty() == true) { return -1; }
      Block cur = frontier.pop();
      if (cur == grid.goal) {
				frontier.clear(0);
				return 1; 
			}

      closed.add(cur);	
			this.expansions++;

      ArrayList<Block> neighbors = grid.getNeighbors(cur);
      for (Block neighbor : neighbors) {
        if (neighbor.agent_view == '2') {
          continue;  // dead-end
        }

        int g = cur.g + 1;

        if (closed.contains(neighbor) == false && neighbor.in_frontier == false) {
          neighbor.g = g;
					neighbor.h = grid.calculateH(neighbor);
          neighbor.parent = cur;
          frontier.insert(neighbor);
        } else if (neighbor.in_frontier == true && g < neighbor.g) {
          neighbor.g = g;
          neighbor.parent = cur;
          frontier.removeItem(neighbor);
          frontier.insert(neighbor);
        }
      }
    }
  }


	/* prints statistics on a run */
	void printStatistics()
	{
		System.out.println("Total Block expansions: " + this.expansions);
		this.expansions = 0;
	}	
}
