package generator;

import dbconnect.CourseDBConnect;

import java.util.*;

public class HighPriorityCourse extends Course
{
    private static CourseDBConnect database = CourseDBConnect.getCourseDBConnectInstance();

    public static final int SE = 1;
    public static final int AT = 2;
    public static final int CSNS = 3;
    public static final int DDM = 4;
    public static final int BIO = 5;
    public static final int AI = 6;


    public HighPriorityCourse(int courseIDs,int credit, int priority, String courseCodes, String courseNames, String timeSlots,
                              String prerequisites, String courseTypes, String substituteCourseCodes)  // constructor
    {
        super(courseIDs, credit, priority, courseCodes, courseNames,timeSlots, prerequisites, courseTypes, substituteCourseCodes);
    }

    /*
    public static ArrayList<Integer> getHpCourses() // should return a list of all high priority courses
    {
        ArrayList<Integer> output = new ArrayList<Integer>();
        output.addAll(EGERCourses);
        output.addAll(CSCRCourses);
        output.addAll(CSDRCourses);
        output.addAll(SRCourses);
        return output;
    }
     */

    public static ArrayList<Integer> getEGERoptions(ArrayList<Integer> coursesTaken) // should return a list of EGER courses
    // still need to take
    {
        ArrayList<Integer> output = database.getGeneralCourseList();
        output.removeAll(coursesTaken);
        return output;
    }

    public static ArrayList<Integer> getCSCRoptions(ArrayList<Integer> coursesTaken) // should return a list of CSCR courses
    // still need to take
    {
        ArrayList<Integer> output = database.getCoreCourseList();
        output.removeAll(coursesTaken);
        return output;
    }

    public static ArrayList<Integer> getCSDRoptions(ArrayList<Integer> coursesTaken) // should return a list of CSDR courses
    // still need to take
    {
        ArrayList<Integer> output = database.getDepthCourseList();
        output.removeAll(coursesTaken);
        return output;
    }

    public static ArrayList<Integer> getCSBRoptions(ArrayList<Integer> coursesTaken) // should return a list of CSBR courses
    // still need to take
    {
        ArrayList<Integer> output = database.getBreadthCourseList();
        output.removeAll(coursesTaken);
        return output;
    }

    public static ArrayList<Integer> getSRoptions(ArrayList<Integer> coursesTaken) // should return a list of SR courses
    // still need to take
    {
        ArrayList<Integer> output = database.getStatisticsCourseList();
        output.removeAll(coursesTaken);
        return output;
    }



}