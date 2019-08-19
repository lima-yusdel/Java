package pkg;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

public class Layout
{
	final int ROWS = 101;
	final int COLS = 101;

	Grid grid;
	Block original_start;
	Block original_goal;

	JFrame frame = new JFrame("Grid");
	JPanel panel_container = new JPanel(); // contains the three panels side by side
	JPanel actual_panel = new JPanel(); // panel for actual grid; will contain actual_grid
	JPanel agent_panel = new JPanel(); // panel for agent grid; will contain agent_grid
	JButton[][] actual_grid = new JButton[ROWS][COLS]; 
	JButton[][] agent_grid  = new JButton[ROWS][COLS];

	public Layout(Grid grid, Block start, Block goal) {
		this.grid = grid;
		this.original_start = start;
		this.original_goal  = goal;

		panel_container.setLayout(new GridLayout(1,3,0,0));
		actual_panel.setLayout(new GridLayout(ROWS, COLS, 0, 0));
		actual_panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 10));
		agent_panel.setLayout(new GridLayout(ROWS, COLS, 0, 0));
		agent_panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 0));

		fillActualGrid();
		fillAgentGrid();
		
		panel_container.add(actual_panel);
		panel_container.add(agent_panel);
		frame.add(panel_container);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}


	public void fillActualGrid()
	{
		for (int i=0; i < ROWS; i++) {
			for (int j =0; j < COLS; j++) {
				actual_grid[i][j] = new JButton();
				if (grid.grid[i][j].blocked == true) {
					actual_grid[i][j].setBackground(Color.BLACK);
				} else if (grid.grid[i][j] == original_start) {
					actual_grid[i][j].setBackground(Color.GREEN);
				} else if (grid.grid[i][j] == original_goal) {
					actual_grid[i][j].setBackground(Color.RED);
				} else {
					actual_grid[i][j].setBackground(Color.WHITE);
				}
				actual_panel.add(actual_grid[i][j]);
			}
		}
	}


	public void fillAgentGrid()
	{
		grid.observeNeighbors(grid.start);		

		for (int i=0; i < ROWS; i++) {
			for (int j=0; j < COLS; j++) {
				agent_grid[i][j] = new JButton();
				if (grid.grid[i][j] == original_start) {
					agent_grid[i][j].setBackground(Color.GREEN);
				} else if (grid.grid[i][j] == original_goal) {
					agent_grid[i][j].setBackground(Color.RED);
				} else if (grid.grid[i][j].traveled == true) {
					agent_grid[i][j].setBackground(Color.YELLOW);
				} else  if (grid.grid[i][j] == grid.goal) {
					agent_grid[i][j].setBackground(Color.RED);
				} else if (grid.grid[i][j].agent_view == '2') {
					agent_grid[i][j].setBackground(Color.BLACK);
				} else if (grid.grid[i][j].agent_view == '1') {
					agent_grid[i][j].setBackground(Color.WHITE);
				} else {
					agent_grid[i][j].setBackground(Color.GRAY);
				}
				agent_panel.add(agent_grid[i][j]);
			}
		}
	}
}

