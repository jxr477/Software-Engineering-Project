import java.util.ArrayList;

public class Plan {
    private static ArrayList<Course> courseList = new ArrayList<Course>();
    private int numOfCourses;

    public Plan() {}

    public Plan(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public Plan(int size) {
        ArrayList<Course> temp = new ArrayList<Course>(size);
        this.courseList = temp;

    }



    public static ArrayList<Course> getCourseList() {
        return courseList;
    }

    public static void clear()
    {
        courseList.clear();
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public boolean isFullPlan() {
        if (this.courseList.size() >= Generator.MAX_NUM_COURSES)
            return true;
        else
            return false;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void setCourse(int index, Course course)
    {
        courseList.set(index, course);
    }

    public int getNumOfCourses() {
        return numOfCourses;
    }

    public void setNumOfCourses(int numOfCourses) {
        this.numOfCourses = numOfCourses;
    }

    public Course getCourseAt(int index) {
        return this.getCourseList().get(index);
    }

    public void printPlan() {
        ArrayList<String> output = new ArrayList<String>();
        for (int i = 0; i < courseList.size(); i++)
            output.add(courseList.get(i).getCourseCode());

        System.out.println(output);
    }


}
