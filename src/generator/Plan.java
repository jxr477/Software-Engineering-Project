package generator;

import java.util.ArrayList;

public class Plan {
    private ArrayList<Course> courseList = new ArrayList<Course>();

    public Plan() {}

    public Plan(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void clear()
    {
        this.courseList.clear();
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

    public Course getCourseAt(int index) {
        return this.getCourseList().get(index);
    }
}
