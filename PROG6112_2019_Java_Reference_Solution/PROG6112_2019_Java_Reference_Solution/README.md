# PROG6112 Programming 1B — 2019 reference solutions

This archive contains three independent Java projects, one for each programming question.

## Projects
- `Question1_PetrolPrices`: Swing GUI, city list, year combo box, Submit button, File/Tools menus and average-price report.
- `Question2_CourseRegistrations`: two-dimensional registration array, course-name array, averages and run/not-run decision.
- `Question3_SportVehicles`: Swing GUI with three radio buttons, a vehicle-details list, and data loaded from `cars.txt`.

## Run
Each folder is a standalone Maven project. Open its `pom.xml` in IntelliJ IDEA or NetBeans. The source uses Java 17 and Swing (included with the JDK). You can also compile the main source directly with `javac`.

For Question 3, keep `cars.txt` in the project root when running from the project directory. The application also checks `src/main/resources/cars.txt`.

These are study/reference implementations based on the supplied 2019 paper, not an official marking memorandum. Review the code and practise explaining each array, event handler, file operation and calculation.
