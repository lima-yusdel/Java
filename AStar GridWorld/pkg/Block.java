package pkg;

/* object representing a block in the overall grid */
public class Block
{
	public boolean blocked    = false;		// true = block is blocked; false = otherwise
	public char agent_view = '0';    // 0 = unseen; 1 = seen and not blocked; 2 = seen and blocked
	public boolean in_frontier = false;   // true = block in frontier; false = otherwise
	public boolean traveled = false; // true = the agent has walked on this block (it is part of the path)	
	 
	public int row;   			// grid row of block
	public int col;   			// grid col of block
	public int g = Integer.MAX_VALUE;			// g-value = distance from start state
	public int h = Integer.MAX_VALUE;			// h-value = manhattan distance to goal
	
	public Block parent = null; // pointer to the Blocks' parent

	public Block(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

