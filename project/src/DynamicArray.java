public class DynamicArray {
    private int[] arr;
    private int size;
    private int capacity;
    public long accesses=0;

    public DynamicArray(){
        this.capacity= 10;
        this.arr = new int[capacity];
        this.size=0;
    }

    public int getSize() {
        accesses++;
        return size;
    }

    private void resize(){
        capacity *=2;
        int[] newArr = new int[capacity];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    public void add(int x){
        if(size == capacity){
            resize();
        }
        arr[size++] = x;
    }
    public void add(int index, int x){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        if(size==capacity) resize();
        for (int i = size; i >index; i--) {
            arr[i]=arr[i-1];
        }
        arr[index] = x;
        size++;
    }

    public int remove(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        int removed = arr[index];
        for (int i = index; i < size-1; i++) {
            arr[i] = arr[i+1];
        }
        size--;
        return removed;
    }

    public void printArr(){
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] +" ");
        }
    }
    public int get(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
    return arr[index];
    }
    public boolean contains(int x){
        for (int i = 0; i < size; i++) {
            if(arr[i]==x){
                return true;
            }
        }
        return false;
    }
}

