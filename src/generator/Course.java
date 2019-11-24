package generator;

import java.util.*;

public class Course
{
    public static final String NO_SUBSTITUTES = "";

    private int credit;
    private int courseID;
    private int priority;
    private String courseCode;
    private String courseName;
    private String timeSlot;
    private String prerequisite;
    private String courseType;
    private String substituteCourseCode;

    public Course(int courseIDs, int credits, int prioritys, String courseCodes, String courseNames, String timeSlots,
                  String prerequisites, String courseTypes,String substituteCourseCodes)
    {
        credit = credits;
        priority = prioritys;
        courseID = courseIDs;
        courseCode = courseCodes;
        courseName = courseNames;
        timeSlot = timeSlots;
        prerequisite = prerequisites;
        courseType = courseTypes;
        substituteCourseCode = substituteCourseCodes;
    }

    /*
    public void printCourse()
    {
        System.out.println("Course id: " + courseID);
        System.out.println("Course code: " + courseCode);
        System.out.println("Course name: " + courseName);
        System.out.println("Time slot: " + timeSlot);
        System.out.println("Prerequisites: " + prerequisite);
        System.out.println("Type: " + courseType);
        System.out.println("Substitute code: "+ substituteCourseCode);
    }
    */

    public int getPriority()
    {
        return priority;
    }

    public int getCourseID()
    {
        return courseID;
    }

    public String getCourseCode()
    {
        return courseCode;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public String getTimeSlot()
    {
        return timeSlot;
    }

    public String getPrerequisite()
    {
        return prerequisite;
    }

    public String getCourseType()
    {
        return courseType;
    }

    public String getSubstituteCourseCode()
    {
        return substituteCourseCode;
    }

    public static String convertTimeSlotReadable(String timeSlot){
        StringBuilder sb = new StringBuilder();

        if (timeSlot.length()!=11 && timeSlot.length()!=22)
            return "Wrong format of the time slot";
        else {

            sb.append("Regular Section: ");
            for (int i = 0; i < 3; i++) {
                if (timeSlot.charAt(i) == '0')
                    break;
                if (timeSlot.charAt(i) == '1')
                    sb.append("Monday ");
                if (timeSlot.charAt(i) == '2')
                    sb.append("Tuesday ");
                if (timeSlot.charAt(i) == '3')
                    sb.append("Wednesday ");
                if (timeSlot.charAt(i) == '4')
                    sb.append("Thursday ");
                if (timeSlot.charAt(i) == '5')
                    sb.append("Friday ");
            }
            if (!sb.toString().equals("Regular Section: ")) {
                sb.append(timeSlot, 3, 5);
                sb.append(":");
                sb.append(timeSlot, 5, 7);
                sb.append(" - ");
                sb.append(timeSlot, 7, 9);
                sb.append(":");
                sb.append(timeSlot, 9, 11);
            } else {
                return "";
            }

            if (timeSlot.length() > 11) {
                sb.append(", Lab/Recitation Section: ");
                for (int i = 11; i < 14; i++) {
                    if (timeSlot.charAt(i) == '0')
                        break;
                    if (timeSlot.charAt(i) == '1')
                        sb.append("Monday ");
                    if (timeSlot.charAt(i) == '2')
                        sb.append("Tuesday ");
                    if (timeSlot.charAt(i) == '3')
                        sb.append("Wednesday ");
                    if (timeSlot.charAt(i) == '4')
                        sb.append("Thursday ");
                    if (timeSlot.charAt(i) == '5')
                        sb.append("Friday ");
                }
                sb.append(timeSlot, 14, 16);
                sb.append(":");
                sb.append(timeSlot, 16, 18);
                sb.append(" - ");
                sb.append(timeSlot, 18, 20);
                sb.append(":");
                sb.append(timeSlot, 20, 22);
            }
            return sb.toString();
        }
    }

    public static String convertBlankToNone(String blank){
       if(blank.equals(""))
           return "None";
       return blank;
    }

    public String toString()
    {
        return ("credit: " + credit + "  "  + "courseID: " + courseID + "  " + "courseCode: " + courseCode + "  " + "courseName: " + courseName + "  " + "timeSlot: " + convertTimeSlotReadable(timeSlot) + "  " +
                "prerequisite: " + convertBlankToNone(prerequisite) + "  " + "courseType: " + courseType + "  " + "substituteCourseCode: " + convertBlankToNone(substituteCourseCode));
    }

    public static void main(String[] args) {
        System.out.println(convertTimeSlotReadable("24010001115"));
        System.out.println(convertTimeSlotReadable("2401000111530013001500"));
    }
}