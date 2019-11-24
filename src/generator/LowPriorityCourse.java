package generator;

import dbconnect.CourseDBConnect;

import java.io.*;
import java.util.*;

public class LowPriorityCourse extends Course
{

    private static CourseDBConnect database = CourseDBConnect.getCourseDBConnectInstance();

    public LowPriorityCourse(int courseID,int credit, int priority, String courseCodes, String courseNames, String timeSlots,
                             String prerequisites, String courseTypes, String substituteCourseCodes)  // constructor
    {
        super(courseID, credit, priority, courseCodes, courseNames,timeSlots, prerequisites, courseTypes, substituteCourseCodes);
    }

    public static ArrayList<Integer> getGroup1options(ArrayList<Integer> coursesTaken) // should return a list of Lp courses
// still need to take
    {
        ArrayList<Integer> output = database.getElectiveGroup1CourseList();
        output.removeAll(coursesTaken);
        return output;
    }

    public static ArrayList<Integer> getGroup2options(ArrayList<Integer> coursesTaken) // should return a list of Lp courses
// still need to take
    {
        ArrayList<Integer> output = database.getElectiveGroup2CourseList();
        output.removeAll(coursesTaken);
        return output;
    }


}