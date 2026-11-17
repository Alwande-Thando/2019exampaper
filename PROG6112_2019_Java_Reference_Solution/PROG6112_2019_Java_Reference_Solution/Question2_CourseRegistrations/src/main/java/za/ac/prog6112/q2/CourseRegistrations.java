package za.ac.prog6112.q2;

/** Calculates average registrations and determines which courses will run. */
public class CourseRegistrations {
    public static void main(String[] args) {
        String[] courses = {
            "Programming 101", "Web Dev 101", "Databases 101",
            "Logic 101", "Management 101"
        };

        // Each row contains the three registration figures for one course.
        int[][] registrations = {
            {50, 10, 50},
            {10, 50, 20},
            {10, 10, 20},
            {10, 7, 5},
            {30, 10, 50}
        };

        System.out.printf("%-20s %-12s %-12s%n", "Course", "Average", "Will run?");
        System.out.println("------------------------------------------------");
        for (int course = 0; course < courses.length; course++) {
            int total = 0;
            for (int figure : registrations[course]) {
                total += figure;
            }
            double average = (double) total / registrations[course].length;
            boolean willRun = average >= 70;
            System.out.printf("%-20s %-12.2f %-12s%n",
                    courses[course], average, willRun ? "Yes" : "No");
        }
    }
}
