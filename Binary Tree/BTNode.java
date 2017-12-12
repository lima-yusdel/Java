package lab07treestesterf16;

/**
 *
 * @author Yusdel Lima Lorenzo
 * @param <E>
 */
public class BTNode<E> {

    private E data;
    private BTNode<E> left, right;

    /**
     * This method initializes the BTNode
     *
     * @param newData
     * @param newLeft
     * @param newRight
     */
    public BTNode(E newData, BTNode<E> newLeft, BTNode<E> newRight) {
        data = newData;
        left = newLeft;
        right = newRight;
    }

    /**
     * This gets the data
     *
     * @return the data
     */
    public E getData() {
        return data;
    }

    /**
     * This gets the left link of the node
     *
     * @return This returns the pointer of the left node
     */
    public BTNode<E> getLeft() {
        return left;
    }

    /**
     * This gets the right link of the node
     *
     * @return This returns the pointer of the right node
     */
    public BTNode<E> getRight() {
        return right;
    }

    /**
     * This gets the right most data. Useful for the remove in a tree.
     *
     *
     * @return This returns the data of the right most node
     */
    public E getRightMostData()
    {
        if (right == null)
        {
            return data;
        }
        else 
        {
            return right.getRightMostData();
        }
    }

    /**
     * This removes right most data. 
     * @return the node which is the right most
     * 
     * the first condition is if the rightmost node is at the root 
     * because there is no right child.
     * 
     * The second condition is a recursive call 
     * removes the rightmost node from my own right child.
     */
    public BTNode<E> removeRightMost() {
        if (right == null) {
            return left;
        } else {
            right = right.removeRightMost();
            return this;
        }
    }

    /**
     * Prints the BTNode in order.
     *
     */
    public void inOrderPrint() {
        if (left != null) {
            left.inOrderPrint();
        }
        System.out.println(data);

        if (right != null) {
            right.inOrderPrint();
        }
    }

    /**
     * This sets the new data to data
     *
     * @param newData
     */
    public void setData(E newData) {
        data = newData;
    }

    /**
     * This sets the left new link to a variable called left
     *
     * @param newLeft
     */
    public void setLeft(BTNode<E> newLeft) {
        left = newLeft;
    }

    /**
     * This sets the new right link to a variable called right
     *
     * @param newRight
     */
    public void setRight(BTNode<E> newRight) {
        right = newRight;
    }

}
