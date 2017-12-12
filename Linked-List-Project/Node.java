package p2;
/**
 *
 * @author Yusdel Lima Lorenzo
 */
public class Node<E> {

    private E data;
    private Node<E> link;

    /**
     * 
     * @param initialData this is the data of the linked list 
     * @param initialLink this is where the node points to in the linked list
     */
    public Node(E initialData, Node<E> initialLink) {
        data = initialData;
        link = initialLink;
    }
    /**
     * This gets the data
     * @return the data of the linked list
     */
    public E getData() {
        return data;
    }
    /**
     * This gets the link of the node
     * @return This returns the pointer of the node
     */
    public Node<E> getLink() {
        return link;
    }
    /**
     * This sets the initaial data to data
     * @param initialData 
     */
    public void setData(E initialData) {
        data = initialData;
    }
    /**
     * This sets the initaial link to a variable link
     * @param initialLink 
     */
    public void setLink(Node<E> initialLink) {
        link = initialLink;
    }
}
