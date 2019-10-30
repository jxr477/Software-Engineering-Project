import java.util.*;

public class User {
    private int track;
    private static ArrayList<Integer> coursesTaken = new ArrayList<Integer>();
    private int userID;
    private static ArrayList<Plan> plans = new ArrayList<Plan>();

    public User(int userID, int trackChoice, ArrayList<Integer> courses) {
        this.userID = userID;
        this.track = trackChoice;
        coursesTaken = courses;
    }

    public ArrayList<Plan> getPlans() {
        return this.plans;
    }

    public void setPlans(ArrayList<Plan> newPlans) {
        this.plans.addAll(newPlans);
    }

    public void addPlans(Plan newPlans) {
        this.plans.add(newPlans);
    }

    public int getTrack() {
        return track;
    }

    public static ArrayList<Integer> getCourses() {
        return coursesTaken;
    }

    public static ArrayList<String> getCourseCode() {
        ArrayList<String> output = new ArrayList<String>();
        Database sample = new Database();
        for (int i = 0; i < coursesTaken.size(); i++) {
            output.add(sample.getCourse(coursesTaken.get(i)).getCourseCode());
        }
        return output;
    }

    public int getUserID() {
        return this.userID;
    }

    public void updateTrack(int input) {
        this.track = input;
    }

    public void updateUserID(int input) {
        this.userID = input;
    }

    public void updateCourses(ArrayList<Integer> input) {
        coursesTaken.clear();
        coursesTaken.addAll(input);
    }

    public String toString() {
        return (this.userID + "\t" + track + "\t" + coursesTaken);
    }


    public boolean metPrerequisite(Course course) // return true if the input course has no prerequisite or the user has already taken the prerequisite,
    // return false otherwise.
    {
        Database sample = new Database();
        boolean output = false;
        if (course.getPrerequisite() == "")
            output = true;
        else if (User.getCourseCode().contains(course.getPrerequisite()) == true)
            output = true;
        return output;
    }

    public static void main(String[] args) throws Exception {


        ArrayList<Integer> coursesTaken = new ArrayList<Integer>();
        coursesTaken.add(1);
        coursesTaken.add(2);
        coursesTaken.add(3);
        coursesTaken.add(4);
        coursesTaken.add(5);

        User xx = new User(123,1 , coursesTaken);
        Course EECS132 = new Course(1, 4, 4,"EECS132",
                "Introduction to Programming in Java", "13514151505",
                "", "010000", HighPriorityCourse.NO_SUBSTITUTES);
        Course EECS233 = new Course(2, 4, 4,"EECS233",
                "Introduction to Data Structures", "24013001415",
                "EECS132", "010000", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS302 = new Course(3, 3, 4,"EECS302",
                "Discrete Mathematics", "13509301020",
                "MATH122", "010000", HighPriorityCourse.NO_SUBSTITUTES);

        System.out.println(xx.getCourseCode());
        System.out.println(EECS132.getPrerequisite());
        System.out.println(xx.metPrerequisite(EECS132));
    }
}