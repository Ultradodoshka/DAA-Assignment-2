public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;
    public long comparisons=0;

    public MinHeap() {
        this.capacity = 10;
        this.heap = new int[capacity];
        this.size = 0;
    }
    public int peekMin() {
        if (size == 0) {throw new IllegalStateException("heap is empty");}
        return heap[0];
    }

    public void insert(int x) {
        if(size == capacity) {
            resize();
        }
        heap[size] = x;
        siftUp(size);
        size++;
    }

    public int extractMin() {
        if (size == 0) {throw new IllegalStateException("heap is empty");}
        int min = heap[0];
        heap[0] = heap[size-1];
        size--;
        siftDown(0);
        return min;
    }

    private void resize() {
        capacity *= 2;
        int[] newHeap = new int[capacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    private void siftDown(int i){
        while(2*i+1 < size){
            int left = 2*i+1;
            int right = 2*i+2;
            int min = left;
            if(right<size && heap[right] < heap[left]){
                min = right;
            }
            comparisons++;
            if(heap[i]<=heap[min]){break;}
            int temp = heap[i];
            heap[i] = heap[min];
            heap[min] = temp;
            i = min;
        }
    }

    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            comparisons++;
            if (heap[parent] <= heap[i]) break;
            int temp = heap[parent];
            heap[parent] = heap[i];
            heap[i] = temp;
            i = parent;
        }
    }
}
