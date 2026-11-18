# Student Grade Management System (Java)

## Requirements
- JDK 21 or compatible Java version
- VS Code with Java support, or any Java IDE

## Files
- Student.java: Student data and calculation methods.
- StudentGradeManagementSystem.java: Console menu, adding/searching/listing students, summary report.

## Run from terminal
Open this folder in VS Code, then run:

```bash
javac Student.java StudentGradeManagementSystem.java
java StudentGradeManagementSystem
```

## Features
- Add students with marks for 3 subjects (marks must be 0-100)
- Calculate average, highest, and lowest marks per student
- Assign a letter grade
- Display all student records
- Search by student name
- Print class summary report including class average and overall highest/lowest marks

## Grade scale
- 90-100: A+
- 80-89.99: A
- 70-79.99: B
- 60-69.99: C
- 50-59.99: D
- Below 50: F

Student records are held in memory and are cleared when the program closes.
