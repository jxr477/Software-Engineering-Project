
import java.io.*;
import java.util.*;

public class LowPriorityCourse extends Course
{

    private static ArrayList<Integer> LpCourses = new ArrayList<Integer>();

    public LowPriorityCourse(int courseID,int credit, int priority, String courseCodes, String courseNames, String timeSlots,
                             String prerequisites, String courseTypes, String substituteCourseCodes)  // constructor
    {
        super(courseID, credit, priority, courseCodes, courseNames,timeSlots, prerequisites, courseTypes, substituteCourseCodes);
    }

    public static void setLpCourses(ArrayList<Integer> input) // only for testing purpose at this moment,
    // should get data from database in the end.
    {
        // do nothing
        input.clear();
        input.add(5);
        input.add(9);
        input.add(19);
    }

    public static ArrayList<Integer> getLPoptions(ArrayList<Integer> coursesTaken) // should return a list of Lp courses
// still need to take
    {
        LowPriorityCourse.setLpCourses(LpCourses);
        ArrayList<Integer> output = new ArrayList<Integer>();
        output.addAll(LpCourses);
        output.removeAll(coursesTaken);
        return output;
    }

    public boolean checkGroup2(User user)
    {

        boolean output = false;
        int count = 0;
        Database sample = new Database();
        for (int i = 0;  i < user.getCourses().size(); i++) {
            if (sample.getCourse(user.getCourses().get(i)).getCourseType().charAt(4) == ('2'))
                count++;
        }

        if (count >= 2) // if the user has taken >= 2 courses from group2, return false
            output = false;
        else // otherwise, return true
            output = true;

        return output;
    }

    public ArrayList<Integer> getLpCourses() // should return a list of all low priority courses
    {
        return LpCourses;
    }

    public String toString()
    {
        return super.toString();
    }
}