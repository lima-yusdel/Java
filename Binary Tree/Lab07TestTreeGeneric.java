package lab07treestesterf16;
public class Lab07TestTreeGeneric<E extends Comparable<E>>
{
    public void test(E[] testDataAdd, E testDataSingleItemRemove, 
                        String header)
    {
        // creates a Tree of E
        Tree<E> myGenericTree = new Tree<E>();
        
        System.out.println("===========================================================");
        System.out.println("\t\t\t\t"+header);

        // show initial Tree
        displayTree("Display Tree/Size on startup",myGenericTree);

        // add some stuff to the Tree
        System.out.println("\n===========\n<<Start adds:");
        for (E item : testDataAdd)
        {
            System.out.print("Adding: " + item);
            myGenericTree.add(item);
            System.out.println(" \tadded...now size is " + myGenericTree.size());
        }
        System.out.println("Stopped adding>>\n===========");

        // show Tree after adds
        displayTree("\nDisplay Tree/Size after adds",myGenericTree);

    }


    /**
        the removeValue method attempts to remove aValue from a Tree
        @param aValue the E to remove
        @param aTree the Tree<E> to delete from
    */


    //  *******  remove prior line for Lab 9 testing    ******
    private void removeValue(E aValue, Tree<E> aTree)
    {
        if (aTree.remove(aValue))
        {
            System.out.println("\n"+aValue+" stated as removed from Tree...double check output");
        }
        else
        {
            System.out.println("\n"+aValue+" is stated as not in the Tree - remove failed...double check output");
        }

        displayTree("Display Tree after trying to remove: "+aValue,aTree);
    }
    //    *******  remove next line for Lab 9 testing    ******


    /**
        the displayTree method displays the Tree and its size
        @param heading a String to display before the Tree
        @param aTree the Tree to display
    */
    private void displayTree(String heading, Tree<E> aTree)
    {
        System.out.println("\n" + heading);

        if (aTree.size() == 0)
        {
            System.out.println("\nTree is empty...Tree says: ");
            aTree.printTree();
        }
        else
        {
            aTree.printTree();
            System.out.println("Size: " + aTree.size());
        }
    }
}