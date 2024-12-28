## [Task 1: Detecting Duplicate Elements in a List](duplicate-finder/)

### Problem Statement
A function was implemented to detect duplicate elements in a list and return them in the order they first appeared. For example:

**Input:** `["b", "a", "c", "c", "e", "a", "c", "d", "c", "d"]`
**Output:** `["a", "c", "d"]`

### Implementation Details
- Language Used: **Java (21)**
- Dependencies: None (only Java's standard libraries).
- It supports CLI for ease of testing with custom input  
- Approach:
    1. Iterated through the list to count occurrences of each element in a `HashMap`.
    2. Selected elements that appeared more than once and preserved the order of elements by going through initial input list and using `HashSet`.
- Complexity:
  - Time: O(N) with amortized hashing or O(N&#178;) with hash collusion
  - Space: O(N)

### Thoughts & Assumptions
- If we amortize the hash collusion this approach should be `O(N)`
- Thought about adding a memory optimized alternative in case the environment is memory limited such as embedded devices by using  O(N&#178;) solution but if we dont amortize and consider the hashmap collusion case, then the complexity would be same.

### Testing
- Used JUnit to validate the function with various edge cases and typical inputs.
- Added support for different implementations of the `DuplicateFinder` interface 
---