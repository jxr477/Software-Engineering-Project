import java.util.*;

public class HighPriorityCourse extends Course
{

    private static ArrayList<Integer> EGERCourses = new ArrayList<>();
    private static ArrayList<Integer> CSCRCourses = new ArrayList<>();
    private static ArrayList<Integer> CSDRCourses = new ArrayList<>();
    private static ArrayList<Integer> CSBRCourses = new ArrayList<>();
    private static ArrayList<Integer> SRCourses = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> plans = new ArrayList<>();

    public static final int SE = 1;
    public static final int AT = 2;
    public static final int CSNS = 3;
    public static final int DDM = 4;
    public static final int BIO = 5;
    public static final int AI = 6;



    public static void setEGERCourses(ArrayList<Integer> input) // only for testing purpose at this moment,
    // should get data from database in the end.
    {
        input.clear();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);
        input.add(6);
        input.add(7);
        input.add(8);
    }

    public static void setCSCRCourses(ArrayList<Integer> input) // only for testing purpose at this moment,
    // should get data from database in the end.
    {
        input.clear();
        input.add(3);
        input.add(5);
        input.add(7);

    }

    public static void setCSDRCourses(ArrayList<Integer> input) // only for testing purpose at this moment,
    // should get data from database in the end.
    {
        input.clear();
        input.add(1);
        input.add(4);
        input.add(6);
    }

    public static void setCSBRCourses(ArrayList<Integer> input) // only for testing purpose at this moment,
    // should get data from database in the end.
    {
        input.clear();
        input.add(1);
        input.add(4);
        input.add(6);
    }

    public static void setSRCourses(ArrayList<Integer> input) // only for testing purpose at this moment,
    // should get data from database in the end.
    {
        input.clear();
        input.add(1);
        input.add(4);
        input.add(6);
    }

    public HighPriorityCourse(int courseIDs,int credit, int priority, String courseCodes, String courseNames, String timeSlots,
                              String prerequisites, String courseTypes, String substituteCourseCodes)  // constructor
    {
        super(courseIDs, credit, priority, courseCodes, courseNames,timeSlots, prerequisites, courseTypes, substituteCourseCodes);
    }

    public String toString()
    {
        return super.toString();
    }

    public static ArrayList<Integer> getHpCourses() // should return a list of all high priority courses
    {
        ArrayList<Integer> output = new ArrayList<Integer>();
        output.addAll(EGERCourses);
        output.addAll(CSCRCourses);
        output.addAll(CSDRCourses);
        output.addAll(SRCourses);
        return output;
    }

    public static ArrayList<Integer> getEGERoptions(ArrayList<Integer> coursesTaken) // should return a list of EGER courses
    // still need to take
    {
        HighPriorityCourse.setEGERCourses(EGERCourses);
        ArrayList<Integer> output = new ArrayList<Integer>();
        output.addAll(EGERCourses);
        output.removeAll(coursesTaken);
        return output;
    }

    public static ArrayList<Integer> getCSCRoptions(ArrayList<Integer> coursesTaken) // should return a list of CSCR courses
    // still need to take
    {
        HighPriorityCourse.setCSCRCourses(CSCRCourses);
        ArrayList<Integer> output = new ArrayList<>(CSCRCourses);
        output.removeAll(coursesTaken);
        return output;
    }

    public static ArrayList<Integer> getCSDRoptions(ArrayList<Integer> coursesTaken) // should return a list of CSDR courses
    // still need to take
    {
        HighPriorityCourse.setCSDRCourses(CSDRCourses);
        ArrayList<Integer> output = new ArrayList<>(CSDRCourses);
        output.removeAll(coursesTaken);
        return output;
    }

    public static ArrayList<Integer> getCSBRoptions(ArrayList<Integer> coursesTaken) // should return a list of CSBR courses
    // still need to take
    {
        HighPriorityCourse.setCSBRCourses(CSBRCourses);
        ArrayList<Integer> output = new ArrayList<>(CSBRCourses);
        output.removeAll(coursesTaken);
        return output;
    }

    public static ArrayList<Integer> getSRoptions(ArrayList<Integer> coursesTaken) // should return a list of SR courses
    // still need to take
    {
        HighPriorityCourse.setSRCourses(SRCourses);
        ArrayList<Integer> output = new ArrayList<>(SRCourses);
        output.removeAll(coursesTaken);
        return output;
    }



}