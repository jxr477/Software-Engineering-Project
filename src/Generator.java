import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Generator {
    public static final int MAX_NUM_COURSES = 5;
    private static int numOfFullPlans = 0;

    public Generator() {
    }


    public static ArrayList<Plan> generate(ArrayList<Course> courses, User user) throws Exception {
        Plan planA = new Plan();
        Plan planB = new Plan();
        Plan planC = new Plan();

        ArrayList<Plan> plans = new ArrayList<Plan>();
        plans.add(planA);
        plans.add(planB);
        plans.add(planC);

        ArrayList<Course> viableCourses = new ArrayList<Course>();

        for (int i = 0; i < courses.size(); i++) {
            if (user.metPrerequisite(courses.get(i)) == true) // first, check prerequisites
            {
                viableCourses.add(courses.get(i));
            }
        }

        plans.get(0).clear();
        System.out.println("plan1");
        // plan1
        plans.get(0).addCourse(viableCourses.get(0));
        plans.get(0).printPlan();
        ArrayList<Course> C1 = viableCourses;
        for(int j = 0; j < MAX_NUM_COURSES - 1; j++) {
            String timeslot1 = plans.get(0).getCourseAt(j).getTimeSlot();
            C1 = noOverlapCourses(timeslot1, C1);
            plans.get(0).addCourse(C1.get(0));
            plans.get(0).printPlan();
        }

        // plan2
        plans.get(1).clear();
        System.out.println("plan2");
        plans.get(1).addCourse(viableCourses.get(0));
        plans.get(1).printPlan();
        ArrayList<Course> C2 = viableCourses;
        for(int k = 0; k < MAX_NUM_COURSES - 1; k++) {
            String timeslot1 = plans.get(1).getCourseAt(k).getTimeSlot();
            C2 = noOverlapCourses(timeslot1, C2);
            plans.get(1).addCourse(C2.get(1));
            plans.get(1).printPlan();
        }

        // plan3
        plans.get(2).clear();
        System.out.println("plan3");
        plans.get(2).addCourse(viableCourses.get(0));
        plans.get(2).printPlan();
        ArrayList<Course> C3 = viableCourses;
        for(int m = 0; m < MAX_NUM_COURSES - 1; m++) {
            String timeslot1 = plans.get(2).getCourseAt(m).getTimeSlot();
            C3 = noOverlapCourses(timeslot1, C3);
            plans.get(2).addCourse(C3.get(2));
            plans.get(2).printPlan();
        }
        user.setPlans(plans);
        return plans;
    }

    public static ArrayList<Course> noOverlapCourses(String timeslot1, ArrayList<Course> courses) throws Exception {
        ArrayList<Course> output = new ArrayList<Course>();
        for (int i =0; i < courses.size(); i++)
        {
            String timeslot2 = courses.get(i).getTimeSlot();
            if (Generator.ifOverlap(timeslot1,timeslot2) == false)
            {
                output.add(courses.get(i));
            }
        }
        return output;
    }

    public static boolean ifOverlap(String timeSlot1, String timeSlot2) throws Exception {
        boolean overlap = false;

        char[] timeSlot1charArray = timeSlot1.toCharArray();
        char[] timeSlot2charArray = timeSlot2.toCharArray();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (timeSlot1charArray[i]!='0' && timeSlot1charArray[i]==timeSlot2charArray[j]) {
                    //if they have the same date
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    StringBuffer dateStr1_1 = new StringBuffer()
                            .append(timeSlot1charArray[3])
                            .append(timeSlot1charArray[4])
                            .append(":")
                            .append(timeSlot1charArray[5])
                            .append(timeSlot1charArray[6]);
                    StringBuffer dateStr1_2 = new StringBuffer()
                            .append(timeSlot1charArray[7])
                            .append(timeSlot1charArray[8])
                            .append(":")
                            .append(timeSlot1charArray[9])
                            .append(timeSlot1charArray[10]);
                    StringBuffer dateStr2_1 = new StringBuffer()
                            .append(timeSlot2charArray[3])
                            .append(timeSlot2charArray[4])
                            .append(":")
                            .append(timeSlot2charArray[5])
                            .append(timeSlot2charArray[6]);
                    StringBuffer dateStr2_2 = new StringBuffer()
                            .append(timeSlot2charArray[7])
                            .append(timeSlot2charArray[8])
                            .append(":")
                            .append(timeSlot2charArray[9])
                            .append(timeSlot2charArray[10]);
                    Date s1 = sdf.parse(dateStr1_1.toString());
                    Date e1 = sdf.parse(dateStr1_2.toString());
                    Date s2 = sdf.parse(dateStr2_1.toString());
                    Date e2 = sdf.parse(dateStr2_2.toString());
                    long ss1 = s1.getTime();
                    long ee1 = e1.getTime();
                    long ss2 = s2.getTime();
                    long ee2 = e2.getTime();

                    if (ss1-ss2<0) {//the first class is earlier
                        ee1 = ee1 + 15*60*1000;
                    }else if(ss1-ss2>0){//the second class is earlier
                        ee2 = ee2 + 15*60*1000;
                    }else{//if they are at the same time
                        overlap = true;
                        return overlap;
                    }
                    if ((ss1 < ss2) && (ee1 > ss2)) {
                        overlap = true;
                        return overlap;
                    } else if ((ss1 > ss2) && (ss1 < ee2)) {
                        overlap = true;
                        return overlap;
                    } else {
                        overlap = false;
                    }
                }
            }
        }
        return overlap;
    }

    public static void main(String[] args) throws Exception {


        ArrayList<Integer> coursesTaken = new ArrayList<Integer>();
        coursesTaken.add(1);
        coursesTaken.add(4);


        Generator test = new Generator();
        User xx = new User(123,1 , coursesTaken);

        test.generate(Database.getAllCourse(), xx);
    }


}
