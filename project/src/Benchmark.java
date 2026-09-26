import java.util.Random;

public class Benchmark {
    private static final int[] n_values={100,1000,10000,100000};
    private static final int seed = 42;
    private static final int repetitions = 5;

    private static void runWorkLoad1(){
        int randomIndices = 10000;
        long totalDaAccesses = 0;
        long totalLlAccesses = 0;
        for(int n : n_values){
            long totalDynamicArray = 0;
            long totalLinkedList = 0;
            for(int run =0; run<repetitions;run++){
                Random random = new Random(seed+run);
                DynamicArray dynamicArray = new DynamicArray();
                LinkedList linkedList = new LinkedList();
                for(int i=0;i<n;i++){
                    int val=random.nextInt();
                    dynamicArray.add(val);
                    linkedList.add(val);
                }
                int[] indices = new int[randomIndices];
                for (int i = 0; i < randomIndices; i++) {
                    indices[i]=random.nextInt(n);
                }
                dynamicArray.accesses = 0;
                linkedList.accesses = 0;

                long startDA = System.nanoTime();
                for (int i = 0; i < randomIndices; i++) {
                    dynamicArray.get(indices[i]);
                }
                totalDynamicArray += (System.nanoTime() - startDA);
                totalDaAccesses += dynamicArray.accesses;

                long startLL = System.nanoTime();
                for (int i = 0; i < randomIndices; i++) {
                    linkedList.get(indices[i]);
                }
                totalLinkedList += (System.nanoTime()-startLL);
                totalLlAccesses += linkedList.accesses;
            }
            System.out.println("n = "+n+ "\nDynamic Array time: "+totalDynamicArray/repetitions+"ns\nLinkedList time: "+totalLinkedList/repetitions+"ns");
            System.out.println("Dynamic Array accesses: " +totalDaAccesses/repetitions);
            System.out.println("Linked List accesses: " + totalLlAccesses/repetitions);
        }
        System.out.println("WorkLoad 1 finished");
    }

    private static void runWorkLoad2(){
        int searchVal = 1000;
        for(int n:n_values){
            long totalDynArrayTime = 0;
            long totalLinkedListTime = 0;
            long totalDAComparisons=0;
            long totalLLComparisons =0;

            for(int run =0; run<repetitions;run++){
                Random random = new Random(seed+run);
                DynamicArray dynamicArray = new DynamicArray();
                LinkedList linkedList = new LinkedList();
                for (int i = 0; i < n; i++) {
                    int val = random.nextInt();
                    dynamicArray.add(val);
                    linkedList.add(val);
                }
                int[] searches = new int[searchVal];
                for (int i = 0; i < searchVal; i++) {
                    searches[i] = random.nextInt();
                }
                dynamicArray.comparisons = 0;
                long startDA = System.nanoTime();
                for (int i = 0; i < searchVal; i++) {
                    dynamicArray.contains(searches[i]);
                }
                totalDynArrayTime += (System.nanoTime() - startDA);
                totalDAComparisons += dynamicArray.comparisons;

                linkedList.comparisons = 0;
                long startLL = System.nanoTime();
                for (int i = 0; i < searchVal; i++) {
                    linkedList.contains(searches[i]);
                }
                totalLinkedListTime += (System.nanoTime() - startLL);
                totalLLComparisons += linkedList.comparisons;
            }
            System.out.println("n = "+n+"\nDynamic Array time: "+totalDynArrayTime/repetitions+"ns\nLinkedList time: "+totalLinkedListTime/repetitions+"ns");
            System.out.println("Dynamic Array comparisons: "+totalDAComparisons);
            System.out.println("Linked LIst comparisons: "+totalLLComparisons);
        }
        System.out.println("WorkLoad 2 finished");
    }

    private static void runWorkLoad3(){
        int operations =1000;
        for(int n:n_values){
            long daTimeInsert0=0, llTimeInsert0=0;
            long daTimeRemove0=0, llTimeRemove0=0;
            long daTimeInsertMid=0, llTimeInsertMid=0;
            long daTimeRemoveMid=0, llTimeRemoveMid=0;
            long daMovementsIns0 = 0, llAccessesIns0 = 0;
            long daMovementsRem0 = 0, llAccessesRem0 = 0;
            long daMovementsInsMid = 0, llAccessesInsMid = 0;
            long daMovementsRemMid = 0, llAccessesRemMid = 0;

            for(int run =0; run<repetitions; run++){
                Random random = new Random(seed+run);
                DynamicArray dynamicArray = new DynamicArray();
                LinkedList linkedList = new LinkedList();
                for (int i = 0; i < n; i++) {
                    int val = random.nextInt();
                    dynamicArray.add(val);
                    linkedList.add(val);
                }
                //A
                dynamicArray.movements=0;
                linkedList.accesses=0;
                long startDAInsertions0=System.nanoTime();
                for(int i=0; i<operations; i++) dynamicArray.add(0,-1);
                daTimeInsert0 += (System.nanoTime()-startDAInsertions0);
                daMovementsIns0+=dynamicArray.movements;

                long startLLInsertions0 =System.nanoTime();
                for (int i = 0; i <operations ; i++) linkedList.add(0,-1);
                llTimeInsert0+=(System.nanoTime()-startLLInsertions0);
                llAccessesIns0+=linkedList.accesses;

                //B
                dynamicArray.movements=0;
                linkedList.accesses=0;
                long startDARemoves0 = System.nanoTime();
                for (int i = 0; i < operations; i++) dynamicArray.remove(0);
                daTimeRemove0+=(System.nanoTime()-startDARemoves0);
                daMovementsRem0+=dynamicArray.movements;

                long startLLRemoves0 = System.nanoTime();
                for (int i = 0; i < operations; i++) linkedList.remove(0);
                llTimeRemove0+=(System.nanoTime()-startLLRemoves0);
                llAccessesRem0+=linkedList.accesses;

                //C
                dynamicArray.movements=0;
                linkedList.accesses=0;
                long startDAInsertionsMid=System.nanoTime();
                for(int i=0; i<operations; i++) dynamicArray.add(dynamicArray.getSize()/2,-1);
                daTimeInsertMid += (System.nanoTime()-startDAInsertionsMid);
                daMovementsInsMid+=dynamicArray.movements;

                long startLLInsertionsMid =System.nanoTime();
                for (int i = 0; i <operations ; i++) linkedList.add(linkedList.getSize()/2,-1);
                llTimeInsertMid+=(System.nanoTime()-startLLInsertionsMid);
                llAccessesInsMid+=linkedList.accesses;

                dynamicArray.movements=0;
                linkedList.accesses=0;
                long startDARemovesMid = System.nanoTime();
                for (int i = 0; i < operations; i++) dynamicArray.remove(dynamicArray.getSize()/2);
                daTimeRemoveMid+=(System.nanoTime()-startDARemovesMid);
                daMovementsRemMid+=dynamicArray.movements;

                long startLLRemovesMid = System.nanoTime();
                for (int i = 0; i < operations; i++) linkedList.remove(linkedList.getSize()/2);
                llTimeRemoveMid+=(System.nanoTime()-startLLRemovesMid);
                llAccessesRemMid+=linkedList.accesses;
            }
            System.out.println("n= "+n);
            System.out.println("Insert 0; Dynamic Array: " + (daTimeInsert0 / repetitions) + "ns (Movements: " + (daMovementsIns0 / repetitions) + ") Linked List: " + (llTimeInsert0 / repetitions) + "ns (Accesses: " + (llAccessesIns0 / repetitions) + ")");
            System.out.println("Remove 0; Dynamic Array: " + (daTimeRemove0 / repetitions) + "ns (Movements: " + (daMovementsRem0 / repetitions) + ") Linked List: " + (llTimeRemove0 / repetitions) + "ns (Accesses: " + (llAccessesRem0 / repetitions) + ")");
            System.out.println("Insert n/2; Dynamic Array: " + (daTimeInsertMid / repetitions) + "ns (Movements: " + (daMovementsInsMid / repetitions) + ") Linked List: " + (llTimeInsertMid / repetitions) + "ns (Accesses: " + (llAccessesInsMid / repetitions) + ")");
            System.out.println("Remove n/2; Dynamic Array: " + (daTimeRemoveMid / repetitions) + "ns (Movements: " + (daMovementsRemMid / repetitions) + ") Linked List: " + (llTimeRemoveMid / repetitions) + "ns (Accesses: " + (llAccessesRemMid / repetitions) + ")");

        }
        System.out.println("WorkLoad 3 finished");
    }

    private static void runWorkLoad4() {
        for (int n : n_values) {
            long totalInsertTime = 0;
            long totalExtractTime = 0;
            long totalInsertComparisons = 0;
            long totalExtractComparisons = 0;
            boolean isSortedCorrectly = true;

            for (int run = 0; run < repetitions; run++) {
                Random random = new Random(seed + run);
                MinHeap minHeap = new MinHeap();

                int[] dataToInsert = new int[n];
                for (int i = 0; i < n; i++) dataToInsert[i] = random.nextInt();

                minHeap.comparisons = 0;
                long startInsert = System.nanoTime();
                for (int i = 0; i < n; i++) minHeap.insert(dataToInsert[i]);
                totalInsertTime += (System.nanoTime() - startInsert);
                totalInsertComparisons += minHeap.comparisons;

                minHeap.comparisons = 0;
                int prevExtracted = Integer.MIN_VALUE;

                long startExtract = System.nanoTime();
                for (int i = 0; i < n; i++) {
                    int currentExtracted = minHeap.extractMin();
                    if (currentExtracted < prevExtracted) isSortedCorrectly = false;
                    prevExtracted = currentExtracted;
                }
                totalExtractTime += (System.nanoTime() - startExtract);
                totalExtractComparisons += minHeap.comparisons;
            }

            System.out.println("n = " +n);
            System.out.println("Insert " +n+" elements, Time: " + (totalInsertTime / repetitions) + " ns, Comparisons: " + (totalInsertComparisons / repetitions));
            System.out.println("Extract " + n + " elements, Time: " + (totalExtractTime / repetitions) + " ns, Comparisons: " + (totalExtractComparisons / repetitions));
            System.out.println("Sorted correctly: " + isSortedCorrectly);
        }
        System.out.println("WorkLoad 4 finished");
    }



    public static void main(String[] args){
        System.out.println("Work load 1: Random access");
        runWorkLoad1();
        System.out.println();

        System.out.println("WorkLoad 2: Search");
        runWorkLoad2();
        System.out.println();

        System.out.println("WorkLoad 3: Insertion and Removal");
        runWorkLoad3();
        System.out.println();

        System.out.println("WorkLoad 4: Priority Processing");
        runWorkLoad4();
        System.out.println();


    }
}
