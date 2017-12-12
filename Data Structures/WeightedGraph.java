package lab09graphf16;

/**
 * a minimal number of stubs have been provided 
 * to get the starting point file to run
 * @author Yusdel Lima
 */
public class WeightedGraph 
{
    private Object[ ] labels;
    private int[ ][ ] weight;
    
    public WeightedGraph(int n)
    {
        // All values initially false
      labels = new Object[n];
      weight = new int[n][n];
    }
       
    public void addEdge(int source, int target, int value)
    {
      weight[source][target]= value;
    }
    
    public Object getLabel(int vertex)
    {
       return labels[vertex];
    }
    
    public boolean isEdge(int source, int target)
    {
        if (weight[source][target] > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int[ ] neighbors(int vertex)
    {
        int [ ] answer = { };
        
        // First count how many edges have the vertex as their source
        int count = 0;
        int i;
      for (i = 0; i < labels.length; i++)
      {
         if (weight[vertex][i]>0)
            count++;
      }

      // Allocate the array for the answer
      answer = new int[count];

      // Fill the array for the answer
      count = 0;
      for (i = 0; i < labels.length; i++)
      {
         if (weight[vertex][i]>0)
            answer[count++] = i;
      }
        return answer;
    }
    
    public void removeEdge(int source, int target)
    {
       weight[source][target] = 0;
    }
    public void setLabel(int vertex, Object newLabel)
    {
        labels[vertex] = newLabel;
    }
    
    public int size( )
    {
        return labels.length;
    }

    public int getWeight(int source, int target)
    {
        return weight[source][target];
        
    }
    public void setWeight(int source, int target, int value)
    {
       weight[source][target]= value;
    }
}
