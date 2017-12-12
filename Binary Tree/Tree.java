package lab07treestesterf16;
/**
 *
 * @author Yusdel Lima Lorenzo
 */
public class Tree<E extends Comparable<E>> {

    private BTNode<E> root;
    private int numItems;

    public Tree() {
        root = null;
        numItems = 0;
    }

    public void add(E element) {
        if (root == null) {
            new BTNode<E>(element, null, null);
        } else {
            BTNode<E> cursor = root;
            boolean done = false;

            while (!done) {
                if (element.compareTo(cursor.getData()) <= 0) {
                    if (cursor.getLeft() == null) {
                        cursor.setLeft(new BTNode<E>(element, null, null));
                        done = true;
                    } else {
                        cursor = cursor.getLeft();
                    }
                } else if (cursor.getRight() == null) {
                    cursor.setRight(new BTNode<E>(element, null, null));
                    done = true;
                } else {
                    cursor.getRight();
                }
            }
        }
        numItems++;
    }

    public boolean remove(E target) {
        boolean found = false;
        BTNode<E> cursor = root;
        BTNode<E> parentOfCursor = null;

        {
            while (cursor != null & !found) {
                if (cursor.getData().equals(target)) {
                    found = true;
                } else if ((target.compareTo(cursor.getData())) <= 0) {
                    parentOfCursor = cursor;
                    cursor = cursor.getLeft();
                } else {
                    parentOfCursor = cursor;
                    cursor = cursor.getRight();
                }
            }
        }
        {
            if (cursor == null) 
            {
                found = false;
            } 
            else if (cursor == root && cursor.getLeft() == null)
            {
                root = cursor.getRight();
            } 
            else if (cursor != root && cursor.getLeft() == null)
            {
                if (cursor == parentOfCursor.getLeft()) 
                {
                    parentOfCursor.setLeft(cursor.getRight());
                }
                else 
                {
                    cursor.setRight(cursor.getRight());
                }
            }
            else
            {
                cursor.setLeft(cursor.getLeft().removeRightMost());
                cursor.setData(cursor.getLeft().getRightMostData()); 
            }
            
        }
        return found;
    }

    public int size() {
        return numItems;
    }

    public void printTree() {
        if (root != null) {
            root.inOrderPrint();
        }
    }
}
