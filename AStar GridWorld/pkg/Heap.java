package pkg;

import java.util.ArrayList;
import java.util.Random;

/* custom min-heap class; implemented with an arraylist for easy resizing*/
public class Heap
{
	ArrayList<Block> heap = new ArrayList<Block>();

	/* inserts Block cur into the heap */
	public void insert(Block cur)
	{
		if (heap.isEmpty()) {
			heap.add(cur);
			cur.in_frontier = true;
			return;
		}

		/* heap not empty, so have to sift up inserted Block */
		heap.add(cur);
		cur.in_frontier = true;
		int len = heap.size();
		
		int i = len - 1; // index of elem to sift up
		int parent = (i - 1) / 2;

		while (compare(heap.get(i), heap.get(parent)) == -1 && i > 0) {
			Block c = heap.get(i);
			Block  p = heap.get(parent); 
			Block temp = heap.get(i);
			heap.set(i, heap.get(parent));
			heap.set(parent, temp);
			i = parent;
			parent = (i - 1)/2;
		}
	}


	/* removes the top of the heap and returns it */
	public Block pop()
	{
		if (heap.isEmpty()) {
			return null;
		}

		/* copy last element in heap to root, remove last elem */
		Block ret = heap.get(0);
		ret.in_frontier = false;
	
		if (heap.isEmpty()) {
			return ret;
		}
		
		heap.set(0, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		
		siftDown(0);

		return ret;
	}


	public void siftDown(int start)
	{
		int i = start; // index of elem to sift down

    while (true) {
      int left = (2 * i) + 1;
      int right = left + 1;
      int min;

      /* find smallest child */
      if (left < heap.size()) {
        if (right < heap.size()) {
          if (compare(heap.get(left), heap.get(right)) == -1) {
            min = left;
          } else {
            min = right;
          }
        } else {
          min = left;
        }
      } else {
        break; // no children, so done here
      }

      if (compare(heap.get(i), heap.get(min)) == 1) {
        Block temp = heap.get(i);
        heap.set(i, heap.get(min));
        heap.set(min, temp);
        i = min;
      } else {
        break;
      }
    }
	}


	/* remove a specific block from the heap */
	public void removeItem(Block elem)
	{
		int loc = heap.indexOf(elem);
		if (loc == -1) { return; }

		heap.set(loc, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);
		siftDown(loc);		
	}

	/* reset all blocks in heap to not in frontier */
	public void clear(int h)
	{
		for (Block b : heap) {
			b.in_frontier = false;
			if (h == 1) {
				b.h = Integer.MAX_VALUE;
				b.g = Integer.MAX_VALUE;
			}
		}
	}


	/* checks if heap is empty */
	public boolean isEmpty()
	{
		return heap.isEmpty();
	}


	/* compares two Blocks; returns -1 if b1 smaller than b2, 0 if even, 1 if b2 smaller */
	public int compare(Block b1, Block b2)
	{
    int f1 = b1.g + b1.h;
    int f2 = b2.g + b2.h;
		

    if (f1 == f2) {
      if (b1.g > b2.g) { 
				return -1; 
			} else if (b1.g < b2.g) {
				return 1;
			} else { // random tie breaking
				/*Random r = new Random();
				int num = r.nextInt(2);
				if (num == 0) {
					return -1;
				} else {
					return 1;
				}*/
				return 0;
			}
    } else if (f1 < f2) {
      return -1;
    } else {
      return 1;
		}
	}


	public void print()
	{
		System.out.println("********************************");
		for (Block b : heap) {
			System.out.println("location: (" + b.row + ", " + b.col + ")");
			System.out.println("g: " + b.g);
			System.out.println("h: " + b.h);
		}
		System.out.println("*********************************");
	}
}
