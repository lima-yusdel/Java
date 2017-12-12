package lab06stacktester;

/**
 *
 * @author Yusdel Lima
 */
public class Stack<E> {

    private Node<E> top;
    private int numElements;

    /**
     * This method sets top to null and numElements to zero.
     */
    public Stack() {
        top = null;
        numElements = 0;
    }

    /**
     *This method creates a new node and puts it on top, Also adds one to the
     * counter numElements
     * @param newValue
     */
    public void push(E newValue) {
        top = new Node<E>(newValue, top);
        numElements++;
    }

    /**
     *This method checks if top is nothing and if so it prints out an error 
     * message. Else it removes the top node and removes 1 from numElements.
     * @return the value of the top element @throws EmptyStack
     */
    public E pop() throws EmptyStack {
        E returnValue;
        if (top == null) {
            throw new EmptyStack();
        } else {
            returnValue = top.getData();
            top = top.getLink();
            numElements--;
        }
        return returnValue;
    }

    /**
     *This method returns the number of elements in the stack
     * @return numElements which is a counter.
     */
    public int size() {
        return numElements;
    }
}
