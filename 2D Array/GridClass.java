import java.util.Random;
/**
 * @author
 */
public class GridClass {

    public int row;
    public int column;
    public int maxRow;
    public int maxColumn;

    public GridClass(int newNumRows, int newNumColumns) {
        row = newNumRows;
        column = newNumColumns;
        maxRow = row;
        maxColumn = column;
    }

   
    public void jump() {
        Random randomGenerator = new Random();
        row = randomGenerator.nextInt(maxRow);
        column = randomGenerator.nextInt(maxColumn);
        System.out.println("You are in " + "(" + row + "," + column + ")");
    }

 
    public int currentRow() {
        return row;
    }

   
    public int currentColumn() {
        return column;
    }

    
    public void goNorth() {
        if (row > 0) {
            row--;
        }
        System.out.println("You are in " + "(" + row + "," + column + ")");
    }

   
    public void goSouth() {
        if (row < maxRow) {
            row++;
        }
        System.out.println("You are in " + "(" + row + "," + column + ")");
    }

   
    public void goEast() {
        if (column < maxColumn) {
            column++;
        }
        System.out.println("You are in " + "(" + row + "," + column + ")");
    }

   
    public void goWest() {
        if (column > 0) {
            column--;
        }
        System.out.println("You are in " + "(" + row + "," + column + ")");
    }
}
