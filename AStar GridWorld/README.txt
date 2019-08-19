[RUNNING]

To run, compile all java files in pkg
with "javac pkg/*.java" and thenjust 
run Main.java and follow the prompts.

[RANDOM]

A custom heap class for the open-list/frontier is in pkg/Heap.java.
Also, the PDF for this assignment is written with LaTeX. 

[THE GRIDWORLDS]

50 gridworlds are generated upon runtime of Main.java.
The 50 worlds change for each run of the program, but
while a program is running, the gridworlds stay the same
and you can run any of the 3 A* variants on each one as many
times as you would like. 

[VISUALIZATION]

For the visualizations of the grids, the left grid corresponds to the
actual grid, while the right grid corresponds to the agent's view of
the grid. Also, the green block represents the starting block, the red
block the goal block, and all yellow blocks are blocks that at some point
were traveled on by the agent. In other words, the yellow blocks don't represent the shortest
path found from the start to the goal, though the shortest path lies within those
yellow blocks. 
