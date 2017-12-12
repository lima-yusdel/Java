package lab06queuetesterf16;

/**
 *
 * @author Yusdel Lima
 */
public class Queue<E> {

    private Node<E> front;
    private Node<E> rear;
    private int numElements;
    /**
     * This sets front and rear to null, and numElements to zero. This 
     * will be useful in the rest of the program.
     */
    public Queue() {
        front = null;
        rear = null;
        numElements = 0;
    }
    /**
     * This adds a new node to the Queue and adds 1 to numElements.
     * @param newValue 
     */
    public void add(E newValue) {
        if (rear == null) {
            front = new Node<E>(newValue, null);
            rear = front;
        } else {
            rear.setLink(new Node<E>(newValue, null));
            rear = rear.getLink();
        }
        numElements++;
    }
    /**
     * removes a node from the Queue and also removes 1 from numElements
     * @return the data from the node that was removed.
     * @throws EmptyQueue is a exception which tells the user that the Queue is 
     * Empty
     */
    public E remove() throws EmptyQueue {
        E value;
        if (front == null) {
            throw new EmptyQueue();
        } else {
            value = front.getData();
            if (front != rear) {
                front = front.getLink();
            } else {
                front = null;
                rear = null;
            }
            numElements--;
        }
        return value;
    }
    /**
     * This method is used to get the size of the Queue
     * @return the variable numElements which is a counter.
     */
    public int size() {
        return numElements;
    }
}
