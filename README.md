
# Student Management and Algorithms App

This project contains a **Student Management System** with services for creating, updating, and reporting student data, as well as a collection of **algorithm utilities** including numeral-to-words conversion, digit summing, and duplicate removal.

---

## Table of Contents

- [Assumptions](#assumptions)  
- [Design Decisions](#design-decisions)  
- [Modules](#modules)  
- [Setup and Running](#setup-and-running)  
- [Testing](#testing)  

---

## Assumptions

1. **StudentService**
   - Each student has five subjects: Biology, Chemistry, Mathematics, Physics, and Computer Science.
   - Student IDs are generated automatically from the student's name and a random 6-digit number.
   - Only unique student IDs are allowed.
   - Report filters include:
     - `name` (partial match, case-insensitive)
     - `minMean` and `maxMean` (filter by average score)
   - Mean, median, and mode calculations are delegated to `CalculatorUtil`.
   - Updating a student requires an existing student ID; otherwise, a `NotFoundException` is thrown.

2. **Algorithms**
   - `ConvertNumeralsToWords` handles time-to-words conversion in 12-hour format.
   - `DigitSumming` calculates the sum of digits recursively and computes the digital root.
   - `DuplicateRemover` removes duplicates within each row of a 2D array by setting duplicates to `0`.
   - All inputs are assumed to be valid within the described domain (non-negative integers for digit summing, arrays of integers for duplicate removal, valid hour/minute ranges for time conversion).

3. **Testing**
   - Unit tests are implemented using **JUnit 5**.
   - Mockito is used for mocking dependencies in `StudentService`.
   - Tests cover normal scenarios, edge cases, invalid inputs, and exception handling.

---

## Design Decisions

1. **Service-Oriented Design**
   - `StudentService` encapsulates all business logic related to students.
   - DTOs (`CreateStudentRequest`, `StudentResponse`, `ReportResponse`) separate internal models from external representation.
   - Exceptions (`BadRequestException`, `NotFoundException`) provide meaningful error feedback.

2. **Algorithms as Utilities**
   - Algorithm classes (`ConvertNumeralsToWords`, `DigitSumming`, `DuplicateRemover`) are designed as static utility classes for simplicity.
   - Recursion is used in `DigitSumming` for clarity and compactness.
   - `DuplicateRemover` uses a simple array-based approach assuming all integers are non-negative.

3. **Testing**
   - `StudentServiceTest` uses **Mockito** to mock `StudentRepository`.
   - Edge-case tests are included for algorithms to verify correctness for multiple scenarios.

4. **Code Quality**
   - Methods are modular with single responsibility.
   - All classes are documented with author/date annotations.
   - Naming conventions follow Java standards for readability.

---

## Modules

### 1. Student Management
- **Classes**:
  - `StudentService` – create, update, report, and map student data.
  - `Student` – entity/model representing a student.
  - `StudentRepository` – Spring Data repository interface.
  - `CalculatorUtil` – utility for mean, median, mode calculations.
- **DTOs**:
  - `CreateStudentRequest`
  - `StudentResponse`
  - `ReportResponse`
- **Exceptions**:
  - `BadRequestException`
  - `NotFoundException`

### 2. Algorithms
- **ConvertNumeralsToWords**
  - Converts hour (`H`) and minute (`M`) to a textual description.
- **DigitSumming**
  - Computes sum of digits and digital root of a numeric string.
- **DuplicateRemover**
  - Removes duplicates in 2D integer arrays, keeping only first occurrence per row.

---

## Setup and Running

### Requirements
- Java 17 or higher
- Maven or Gradle
- Spring Boot (for StudentService module)

### Running the Student Service
1. Clone the repository:

```bash
git clone <repository-url>
cd studentapp
````

2. Build the project using Maven:

```bash
mvn clean install
```

3. Run the Spring Boot application:

```bash
mvn spring-boot:run
```

4. Use the service via REST endpoints (if implemented) or test the service methods directly in your IDE.

### Running Algorithm Utilities

* Each algorithm has a `main` method. For example:

```bash
# Convert time to words
java -cp target/classes com.techieplanet.algos.ConvertNumeralsToWords
```

* Enter input as prompted in the console.

---

## Testing

### Running Unit Tests

* All tests are written using JUnit 5.
* Run tests via Maven:

```bash
mvn test
```

* Or run directly from your IDE using the test classes:

    * `StudentServiceTest`
    * `ConvertNumeralsToWordsTest`
    * `DigitSummingTest`
    * `DuplicateRemoverTest`

### Test Coverage

* **StudentServiceTest**

    * `create`: normal, null request, duplicate student ID
    * `update`: normal, student not found, null request
    * `report`: filters by name and mean
    * `toResponse`: mean, median, mode calculations
* **Algorithm Tests**

    * Time conversion: exact hour, quarter past/to, half past, minutes past/to, invalid input
    * Digit summing: empty string, single digit, multi-digit, digital root
    * Duplicate remover: normal, empty array, single row, multiple rows, all duplicates

---

## Author

* **Timiolowookere**
* **Date: 29-11-2025**

---

