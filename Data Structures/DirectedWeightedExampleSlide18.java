package lab09graphf16;
import java.util.Scanner;
/**
 * @author Yusdel Lima
 */

public class DirectedWeightedExampleSlide18
{
    public static void main(String[] args)
    {
        int currentVertex;
        int value=0;

        // create graph using your WeightedGraph based on author's Graph
        WeightedGraph myGraph = new WeightedGraph(4);

        // add labels
        myGraph.setLabel(0,"Spot zero");
        myGraph.setLabel(1,"Spot one");
        myGraph.setLabel(2,"Spot two");
        myGraph.setLabel(3,"Spot three");

        // Add each edge (this directed Graph has 5 edges,
        //                  so we add 5 edges)
        myGraph.addEdge(0,2,9);

        myGraph.addEdge(1,0,7);

        myGraph.addEdge(2,3,12);

        myGraph.addEdge(3,0,15);
        myGraph.addEdge(3,1,6);

        // let's pretend we are on v2
        currentVertex = 2;
        
        int command=0;
        {
            do
            {
        
        // display the current vertex
        System.out.println("So far you have accumilated "+value);
        System.out.println("\nYou are currently at vertex " + currentVertex + 
                            "-" + myGraph.getLabel(currentVertex));

        // who are our neighbors?
        System.out.println("neighbors of " + currentVertex + " are:\n");


        if (myGraph.neighbors(currentVertex).length == 0)
            System.out.println("No Neighbors");
        else
        {
            System.out.printf("%3s %-10s %4s\n","#","Neighbor","Cost");
            System.out.printf("%3s %-10s %4s\n","===","==========","====");
            for (int neighbor : myGraph.neighbors(currentVertex))
            {
                System.out.printf("%3d %-10s %4d\n",
                                    neighbor,
                                    myGraph.getLabel(neighbor),
                                    myGraph.getWeight(currentVertex,neighbor));
            }
            System.out.println();
        }
        
            Scanner i =new Scanner(System.in);
            System.out.println("Where would you like to go? (-1 to exit): ");
             command = i.nextInt();
             
             
            if (command == -1)
            {
             System.out.println("good bye!");      
            }
            else if(command >=myGraph.size())
            {
             System.out.println(command+" is an invalid entry.");
            }
            else if(command<=-2)
            {
            System.out.println(command+" is an invalid entry.");
            }
            else if (myGraph.isEdge(currentVertex,command))
            {
                value = value + myGraph.getWeight(currentVertex, command);
               currentVertex= command;
            }
            else
            {
               System.out.println("Vertex entered is unreachable");
            }
    }  
        while (command != -1); 
    }
    }
}
/* 1: if their choice is -1 then say goodbye
           2: else if the user enters a value too large or too small report an out of range error
           3: else if their choice is a valid neighbor then change the current vertex to that choice
           4: else then report the vertex they entered is unreachable


*/