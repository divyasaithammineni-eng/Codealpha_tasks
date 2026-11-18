public class Student {
    private String name;
    private int[] marks;

    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int[] getMarks() {
        return marks;
    }

    public double getAverage() {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return (double) sum / marks.length;
    }

    public int getHighest() {
        int highest = marks[0];
        for (int mark : marks) {
            if (mark > highest) {
                highest = mark;
            }
        }
        return highest;
    }

    public int getLowest() {
        int lowest = marks[0];
        for (int mark : marks) {
            if (mark < lowest) {
                lowest = mark;
            }
        }
        return lowest;
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "F";
    }

    public void displayStudent() {
        System.out.println("--------------------------------");
        System.out.println("Student Name : " + name);
        System.out.print("Marks       : ");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
        System.out.printf("%nAverage     : %.2f%n", getAverage());
        System.out.println("Highest Mark: " + getHighest());
        System.out.println("Lowest Mark : " + getLowest());
        System.out.println("Grade       : " + getGrade());
    }
}
