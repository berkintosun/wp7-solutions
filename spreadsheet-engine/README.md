## [Task 2: Spreadsheet Engine in Java](spreadsheet-engine/)

### Problem Statement
Developed a simple spreadsheet engine that passes a predefined set of tests. The task was approached using a Test-Driven Development (TDD) methodology.

### Implementation Details
- Language Used: **Java (21)**
- Dependencies: None (only Java's standard libraries).
- Steps Followed:
    1. Implemented only the minimum code required to pass the first test.
    2. Gradually added functionality to handle additional test cases.
    3. Refactored code iteratively in order to demonstrate code evolution for clarity.

### Thoughts & Assumptions
- No third-party libraries were used for the core functionality.
- For passing the test cases, we actually do not need to initialize cells with an empty string `""` and I believe that approach reduces the performance of the engine.
- I added a flag `INITIALIZE_EMPTY` to allow users to skip the initializing cells with `""` and handle the null values as empty strings.

exporter outputs in the tests were a bit weird to me as we would have string values more than the amount of column than we have.
I assume the purpose is to use the dash and star as delimiter but still why would we need delimiter at the end of the last cell as well.

### Testing
- Used the exact test cases based on the provided test suite.
- Used JUnit 4 for testing as provided cases were using that.

---