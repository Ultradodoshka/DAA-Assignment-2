import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Tests {
    public static void testDynamicArray() {
        System.out.println("Testing Dynamic Array...");
        DynamicArray dynamicArray = new DynamicArray();
        java.util.ArrayList<Integer> javaList =new ArrayList<>();

        if (dynamicArray.getSize()!=0) throw new AssertionError("Empty structure size should be 0");

        dynamicArray.add(42);
        javaList.add(42);
        if (dynamicArray.getSize() != 1 || dynamicArray.get(0) != javaList.get(0)) throw new AssertionError("One element test failed");

        dynamicArray.add(10); dynamicArray.add(42); dynamicArray.add(10);
        javaList.add(10); javaList.add(42); javaList.add(10);
        if (dynamicArray.getSize() != 4 || dynamicArray.get(2) != 42) throw new AssertionError("Multiple and duplicate elements test failed");

        dynamicArray.add(0, 99);
        javaList.add(0, 99);
        dynamicArray.add(dynamicArray.getSize(), 100);
        javaList.add(javaList.size(), 100);

        if (dynamicArray.get(0)!=99||dynamicArray.get(dynamicArray.getSize()-1) != 100) throw new AssertionError("Boundary insertion failed");

        int removedFirst=dynamicArray.remove(0);
        int removedJava=javaList.remove(0);
        if (removedFirst!=removedJava) throw new AssertionError("Boundary removal (0) failed");

        int removedLast =dynamicArray.remove(dynamicArray.getSize()-1);
        int removedJavaLast= javaList.remove(javaList.size()-1);
        if (removedLast!=removedJavaLast) throw new AssertionError("Boundary removal (size-1) failed");

        try {
            dynamicArray.get(-1);
            throw new AssertionError("Should have thrown IndexOutOfBoundsException for -1");
        } catch (IndexOutOfBoundsException e) {}

        try {
            dynamicArray.add(dynamicArray.getSize() + 1, 50);
            throw new AssertionError("Should have thrown IndexOutOfBoundsException for size+1");
        } catch (IndexOutOfBoundsException e) {}

        DynamicArray largeDa = new DynamicArray();
        for (int i = 0; i < 10000; i++) {
            largeDa.add(i);
        }
        if (largeDa.getSize() != 10000||largeDa.get(9999) != 9999) throw new AssertionError("Large input test failed");

        System.out.println("Dynamic Array tests passed");
    }

    public static void testLinkedList() {
        System.out.println("Testing Linked List...");
        LinkedList linkedList = new LinkedList();
        java.util.LinkedList<Integer> javaList = new java.util.LinkedList<>();

        if (linkedList.getSize() !=0) throw new AssertionError("Empty structure size should be 0");

        linkedList.add(42);
        javaList.add(42);
        if (linkedList.getSize()!= 1||linkedList.get(0) !=javaList.get(0)) throw new AssertionError("One element test failed");

        linkedList.add(10); linkedList.add(42); linkedList.add(10);
        javaList.add(10); javaList.add(42); javaList.add(10);
        if (linkedList.getSize() != 4 || linkedList.get(2) != 42) throw new AssertionError("Multiple and duplicate elements test failed");

        linkedList.add(0, 99);
        javaList.add(0, 99);
        linkedList.add(linkedList.getSize(), 100);
        javaList.add(javaList.size(), 100);

        if (linkedList.get(0) != 99 || linkedList.get(linkedList.getSize() - 1) != 100) throw new AssertionError("Boundary insertion failed");

        int removedFirst = linkedList.remove(0);
        int removedJava = javaList.remove(0);
        if (removedFirst != removedJava) throw new AssertionError("Boundary removal (0) failed");

        int removedLast = linkedList.remove(linkedList.getSize() - 1);
        int removedJavaLast = javaList.remove(javaList.size() - 1);
        if (removedLast != removedJavaLast) throw new AssertionError("Boundary removal (size-1) failed");

        try {
            linkedList.get(linkedList.getSize());
            throw new AssertionError("Should have thrown IndexOutOfBoundsException for size");
        } catch (IndexOutOfBoundsException e) {}

        try {
            linkedList.remove(-1);
            throw new AssertionError("Should have thrown IndexOutOfBoundsException for -1");
        } catch (IndexOutOfBoundsException e) {}

        LinkedList largeLl = new LinkedList();
        for (int i = 0; i < 10000; i++) {
            largeLl.add(i);
        }
        if(largeLl.getSize()!=10000||largeLl.get(9999)!=9999) throw new AssertionError("Large input test failed");

        System.out.println("Linked List tests passed");
    }

    public static void testMinHeap() {
        System.out.println("Testing Min-Heap...");
        MinHeap minHeap = new MinHeap();
        PriorityQueue<Integer> javaHeap = new PriorityQueue<>();
        Random rand = new Random(42);

        try {
            minHeap.peekMin();
            throw new AssertionError("Should throw Exception on empty peek");
        } catch (IllegalStateException e) {}

        try {
            minHeap.extractMin();
            throw new AssertionError("Should throw Exception on empty extract");
        } catch (IllegalStateException e) {}

        minHeap.insert(50);
        javaHeap.add(50);
        if (minHeap.peekMin() != javaHeap.peek()) throw new AssertionError("One element test failed");

        minHeap.insert(20); minHeap.insert(50); minHeap.insert(10); minHeap.insert(20);
        javaHeap.add(20); javaHeap.add(50); javaHeap.add(10); javaHeap.add(20);

        if (minHeap.peekMin()!=javaHeap.peek()) throw new AssertionError("Heap property after insertion failed");

        MinHeap largeHeap = new MinHeap();
        PriorityQueue<Integer> largeJavaHeap = new PriorityQueue<>();

        for (int i = 0; i < 10000; i++) {
            int val = rand.nextInt(5000);
            largeHeap.insert(val);
            largeJavaHeap.add(val);
        }

        int prevExtracted = Integer.MIN_VALUE;
        for (int i = 0; i < 10000; i++) {
            int currentExtracted =largeHeap.extractMin();
            int currentJavaExtracted =largeJavaHeap.poll();

            if (currentExtracted!=currentJavaExtracted) {
                throw new AssertionError("Heap extract differs from Java PriorityQueue");
            }

            if (currentExtracted<prevExtracted) {
                throw new AssertionError("Heap elements are not extracted in non-decreasing order");
            }
            prevExtracted = currentExtracted;
        }
        System.out.println("Min-Heap tests passed");
    }

    public static void main(String[] args) {
        System.out.println("Running Comprehensive Validation Tests");
        try {
            testDynamicArray();
            testLinkedList();
            testMinHeap();
            System.out.println("All comprehensive tests passed successfully");
        } catch (AssertionError e) {
            System.err.println("TEST FAILED: "+e.getMessage());
        }
    }
}