# Data Structures Implementation and Performance Analysis

## 1. Overview
This project involves the implementation, validation, and benchmarking of three fundamental data structures: **Dynamic Array**, **Linked List**, and **Min-Heap**. The objective is to evaluate their theoretical asymptotic complexities against empirical performance using various workloads (random access, searching, insertion/removal, and priority processing).

## 2. Complexity Analysis
The table below summarizes the theoretical time and space complexity for operations within each data structure.

| Data Structure | Operation | Average Time | Worst-case Time | Space Complexity |
| :--- | :--- | :--- | :--- | :--- |
| **Dynamic Array** | Get | O(1) | O(1) | O(n) |
| | Search | O(n) | O(n) | O(n) |
| | Insert end | O(1)* | O(n) | O(n) |
| | Insert 0 / Mid | O(n) | O(n) | O(n) |
| | Remove end | O(1) | O(1) | O(n) |
| | Remove 0 / Mid | O(n) | O(n) | O(n) |
| **Linked List** | Get | O(n) | O(n) | O(n) |
| | Search | O(n) | O(n) | O(n) |
| | Insert 0 / end | O(1) | O(1) | O(n) |
| | Insert Mid | O(n) | O(n) | O(n) |
| | Remove 0 | O(1) | O(1) | O(n) |
| | Remove Mid/end | O(n) | O(n) | O(n) |
| **Min-Heap** | Insert | O(log n) | O(log n) | O(n) |
| | Extract Min | O(log n) | O(log n) | O(n) |
| | Peek Min | O(1) | O(1) | O(n) |

## 3. Correctness
A comprehensive unit testing suite (`Tests.java`) was developed to validate all operations, handling edge cases such as empty structures, duplicate values, and boundary indices.

**Loop Invariant Example (Dynamic Array - Search):**
*   **Initialization:** Before the loop starts, no elements have been checked. The invariant holds: the target has not been found in the checked portion (which is empty).
*   **Maintenance:** In each iteration `i`, if `arr[i]` is not the target, the checked portion increases by 1. The invariant holds: the target is not in `arr[0...i]`.
*   **Termination:** The loop terminates either when the element is found (returning true) or when `i == size` (returning false). This proves the linear search is correct.

## 4. Experimental Setup
*   **Inputs (n):** 100, 1000, 10000, 100000
*   **Randomization:** Random seed `42` was used for reproducibility.
*   **Measurement:** Execution time measured using `System.nanoTime()`. Each workload was executed `5` times (repetitions), and the average time and metrics were recorded to minimize OS-level noise.

## 5. Experimental Results

### Plot 1: Execution Time vs. n (Workload 1)
![Execution Time](project/results/plots/Execution%20Time%20vs%20n.png)

### Plot 2: Operations vs. n (Workload 3)
![Operations](project/results/plots/Operations%20vs%20n.png)

### Tables
### WorkLoad 1
![WorkLoad1](project/results/tables/Table%20workLoad1.png)
### WorkLoad 2
![WorkLoad2](project/results/tables/Table%20workLoad2.png)
### WorkLoad 3
![WorkLoad3](project/results/tables/Table%20workLoad3.png)
### WorkLoad 4
![WorkLoad4](project/results/tables/Table%20workLoad4.png)

## 6. Discussion and Performance Analysis
**Do theoretical results match empirical data?**
Yes, the experimental results perfectly align with theoretical predictions. For instance, in Workload 3 (Insert at index 0), the Linked List executed in constant time O(1) with exactly 1,000 accesses regardless of $n$. Conversely, the Dynamic Array demonstrated O(n) behavior, with execution times and data movements scaling linearly by a factor of 10 as $n$ increased from 10,000 to 100,000.

**Why do algorithms with the same Big-O perform differently in reality?**
In Workload 2 (Search), both Dynamic Array and Linked List perform O(n) comparisons. However, the Dynamic Array executes significantly faster in practice. This is due to **memory caching and spatial locality**. Elements in a Dynamic Array are stored in contiguous memory blocks, allowing the CPU cache to prefetch adjacent elements. Linked List nodes are scattered across the heap, causing frequent cache misses and slower RAM access, increasing the hidden constant factor in O(n).

## 7. Design Recommendations
Based on the empirical evidence:
*   **Dynamic Arrays** should be chosen when frequent random access by index is required or when iterating through elements sequentially, as they highly benefit from CPU caching. They are poor choices for frequent insertions/removals at the beginning.
*   **Linked Lists** are strictly preferable when an application demands frequent insertions and deletions at the ends of the collection (e.g., implementing Queues or Deques) where O(1) operations outweigh the O(n) traversal cost.
*   **Min-Heaps** are the optimal structure for priority-based processing. With O(log n) guarantees for insertion and minimum extraction, they elegantly handle dynamic datasets where the smallest (or largest) element must be repeatedly accessed, such as in Dijkstra's algorithm or task scheduling.

## 8. Conclusion
The benchmarking highlighted that while theoretical Big-O complexity accurately predicts scalability trends, constant factors like CPU cache locality play a massive role in real-world performance. Choosing the correct data structure requires understanding not just the algorithmic theory, but the underlying physical memory architecture.