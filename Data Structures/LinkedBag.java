package lab05generictester;

import java.util.Random;

/**
 *
 * @author Yusdel Lima
 */
public class LinkedBag<E> {

    private int numElements;
    private Node<E> head;
      /**
     * This method sets head equal to null and number of elements equal to zero.
     */
    public LinkedBag() {
        head = null;
        numElements = 0;
    }
/**
     * This method returns the size of the linked list.
     */
    public int getSize() {
        return numElements;
    }
    /**
     * This method adds new elements to the linked list and adds a counter to 
     * the number of elements.
     */
    public void add(E element) {
        head = new Node<E>(element, head);
        numElements++;
    }
    /**
     * This method marches the cursor down the list and if the cursor equals
     * the target it counts the number of occurrences and then returns that
     * number.
     */
    public int countOccurrences(E target) {

        int numOccurences = 0;
        Node<E> cursor;
        for (cursor = head; cursor != null; cursor = cursor.getLink()) {
            if (cursor.getData().equals(target)) {
                numOccurences++;
            }
        }
        return numOccurences;
    }
    /**
     * This method uses a boolean to check if the cursor equals the target and
     * returns true if it exists.
     */
    public boolean exists(E target) {
        boolean found = false;
        Node<E> cursor = head;

        while (cursor != null && !found) {
            if (cursor.getData().equals(target)) {
                found = true;
            } else {
                cursor = cursor.getLink();
            }
        }
        return found;
    }
    /**
    * 
     * @param target this boolean is the data "E" in the linked list
    * @return true if the target is found 
     */
    public boolean remove(E target) {
        Node<E> cursor = head;
        boolean found = false;

        while (cursor != null && !found) {
            {
                if (cursor.getData().equals(target)) {
                    found = true;
                } else {
                    cursor = cursor.getLink();
                }
            }
            if (found) {
                cursor.setData(head.getData());
                head = head.getLink();
                numElements--;
            }
        }
        return found;
    }
    /**
     * This method checks which string to return and display.
     */
    public String toString() {
        String stringToReturn = "";
        Node<E> cursor;

        if (numElements == 0) {
            stringToReturn = "empty";
        } else {
            for (cursor = head; cursor != null; cursor = cursor.getLink()) {
                stringToReturn = stringToReturn + cursor.getData();
                if (cursor.getLink() != null) {
                    stringToReturn = stringToReturn + ", ";
                }
            }
        }
        return stringToReturn;
        }
    /**
     * This method garbs a random node in the linked list
     * @return the data of the node to delete 
     */
     public E grab() {
        int i = 0;
        Node<E> targetNode = head;
        Node<E> delete = null;

        if (numElements == 0) {
            return null;
        } else if (i == 0) {
            delete = head;
            head = head.getLink();
            numElements--;
            return delete.getData();
        }

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(numElements);

        while (randomInt - 1 > 1) {
            targetNode = targetNode.getLink();
            i++;
        }
        delete = targetNode.getLink();
        targetNode = targetNode.getLink();
        numElements--;

        return delete.getData();
    }
}
