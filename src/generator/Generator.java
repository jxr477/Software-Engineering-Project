package generator;

import dbconnect.CourseDBConnect;
import dbconnect.UserInfoDBConnect;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Generator {
    public static final int MAX_NUM_COURSES = 5;

    public Generator() {
    }

    public static ArrayList<Plan> generate(ArrayList<Course> courses, User user) throws Exception {
        // the input courses should be ranked from high to low priority exactly.
        // initialization
        Plan planA = new Plan();
        Plan planB = new Plan();
        Plan planC = new Plan();
        Plan planD = new Plan();
        Plan planE = new Plan();

        ArrayList<Plan> plans = new ArrayList<Plan>(); // only generate five plans here, could generate more
        plans.add(planA);
        plans.add(planB);
        plans.add(planC);
        plans.add(planD);
        plans.add(planE);

        //Delete all taken course
        ArrayList<Course> qualifiedCourses = new ArrayList<>();
        ArrayList<String> courseTaken = UserInfoDBConnect.getCourseCodeTaken(user.getUserName());
        for (Course course : courses)
            if (!courseTaken.contains(course.getCourseCode()))
                qualifiedCourses.add(course);

        ArrayList<Course> viableCourses = new ArrayList<Course>();

        for (int i = 0; i < qualifiedCourses.size(); i++) {
            if (user.metPrerequisite(qualifiedCourses.get(i)) == true) // first, find all courses that meet prerequisites and save them in
                // an arraylist called viableCourses
                viableCourses.add(qualifiedCourses.get(i));
        }

        System.out.println("-----"); // print viable courses for testing purpose
        for (Course c : viableCourses)
            System.out.println(c.toString());
        System.out.println("-----");

        // plan1
        plans.get(0).clear();
        System.out.println("plan1");
        plans.get(0).addCourse(viableCourses.get(0)); // add the first course to plan1
        ArrayList<Course> C1 = viableCourses;
        for (int j = 0; j < MAX_NUM_COURSES - 1; j++) { // first find every viable course that does not overlap with the
            // first course in plan1 and save it in array c1, then add the first course in c1 to plan1. Then find every viable
            // course that does not overlap with the second course in plan1, which is saved in c1 again, add the first course in
            // c1 to plan1. Repeat until reach the maximum number of courses allowed in one plan.
            if (C1.size() > 0) { // avoid running out of boundary
                String timeslot1 = plans.get(0).getCourseAt(j).getTimeSlot();
                C1 = noOverlapCourses(timeslot1, C1); // update C1 at each step
                if (C1.size() > 0) { // avoid running out of boundary
                    if (isInPlan(C1.get(0), plans.get(0)) == false) { // check if the course is already in current plan
                        if (isBreadth(C1.get(0))) { // if the course is breadth, check breadth requirement
                            if (satisfyBreadth(C1.get(0), user) == true)
                                plans.get(0).addCourse(C1.get(0));
                        }
                        else if (isDepth(C1.get(0))){ // if the course is depth, check depth requirement
                            if (satisfyDepth(C1.get(0), user) == true)
                                plans.get(0).addCourse(C1.get(0));
                        }
                        else if (isStat(C1.get(0))){
                            if (satisfyStat(user) == true)
                                plans.get(0).addCourse(C1.get(0));
                        }
                        else if (C1.get(0).getCourseCode() == "MATH201" || C1.get(0).getCourseCode() == "MATH307"){
                            if (hardCode(user) == true)
                                plans.get(0).addCourse(C1.get(0));
                        }
                        else // otherwise, add it to current plan
                            plans.get(0).addCourse(C1.get(0));
                    }
                    else if (isInPlan(C1.get(0), plans.get(0)) == true) { // if the course is already in current plan, find another viable course to replace it
                        for (int i = 0; i < C1.size(); i++) {
                            String timeslot2 = C1.get(i).getTimeSlot();
                            if (isSplit(timeslot1, timeslot2) == false && isInPlan(C1.get(i), plans.get(0)) == false) { // make sure the new course is not in current plan and does not conflict with current course
                                if (isBreadth(C1.get(i))) { // check breadth requirement
                                    if (satisfyBreadth(C1.get(i), user) == true) {
                                        plans.get(0).addCourse(C1.get(i));
                                        break;
                                    }
                                }
                                else if (isDepth(C1.get(i))) { // check depth requirement
                                    if (satisfyDepth(C1.get(i), user) == true) {
                                        plans.get(0).addCourse(C1.get(i));
                                        break;
                                    }
                                }
                                else if (isStat(C1.get(i))){ // check stat requirement
                                    if (satisfyStat(user) == true){
                                        plans.get(0).addCourse(C1.get(i));
                                        break;
                                    }
                                }
                                else if (C1.get(i).getCourseCode() == "MATH201" || C1.get(i).getCourseCode() == "MATH307"){
                                    if (hardCode(user) == true) {
                                        plans.get(0).addCourse(C1.get(i));
                                        break;
                                    }
                                }
                                else {
                                    plans.get(0).addCourse(C1.get(i)); // otherwise, add it to current plan
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < plans.get(0).getCourseList().size(); i++) { // print plan
            System.out.println(plans.get(0).getCourseList().get(i));
        }

        System.out.println("");

        // plan2
        plans.get(1).clear();
        System.out.println("plan2");
        for (int i = 1; i < viableCourses.size(); i++) {
            if (!isInPlan2(viableCourses.get(i), plans.get(0))) {
                if (viableCourses.get(i).getCourseCode() == "MATH201" || viableCourses.get(i).getCourseCode() == "MATH307")
                {
                    if (hardCode(user) == true) {
                        plans.get(1).addCourse(viableCourses.get(i)); // add the first course to plan2
                        break;
                    }
                }
                else {
                    plans.get(1).addCourse(viableCourses.get(i)); // add the first course to plan2
                    break;
                }
            }
        }
        ArrayList<Course> C2 = viableCourses;

        for (int k = 0; k < MAX_NUM_COURSES - 1; k++) { // the only difference is that the first course is not in plan1
            if (C2.size() > 2) {
                String timeslot1 = plans.get(0).getCourseAt(k).getTimeSlot();
                C2 = noOverlapCourses(timeslot1, C2);
                if (C2.size() > 2) {
                    if (isInPlan(C2.get(0), plans.get(1)) == false) {
                        if (isBreadth(C2.get(0))) {
                            if (satisfyBreadth(C2.get(0), user) == true)
                                plans.get(1).addCourse(C2.get(0));
                        }
                        else if (isDepth(C2.get(0)))
                        {
                            if (satisfyDepth(C2.get(0), user) == true)
                                plans.get(1).addCourse(C2.get(0));
                        }
                        else if (isStat(C2.get(0))){
                            if (satisfyStat(user) == true)
                                plans.get(1).addCourse(C2.get(0));
                        }
                        else if (C2.get(0).getCourseCode() == "MATH201" || C2.get(0).getCourseCode() == "MATH307"){
                            if (hardCode(user) == true)
                                plans.get(1).addCourse(C2.get(0));
                        }
                        else
                            plans.get(1).addCourse(C2.get(0));
                    }
                    else if (isInPlan(C2.get(0), plans.get(1)) == true) {
                        for (int i = 0; i < C2.size(); i++) {
                            String timeslot2 = C2.get(i).getTimeSlot();
                            if (isSplit(timeslot1, timeslot2) == false && isInPlan(C2.get(i), plans.get(1)) == false) {
                                if (isBreadth(C2.get(i))) {
                                    if (satisfyBreadth(C2.get(i), user) == true) {
                                        plans.get(1).addCourse(C2.get(i));
                                        break;
                                    }
                                }
                                else if (isDepth(C2.get(i))) {
                                    if (satisfyDepth(C2.get(i), user) == true) {
                                        plans.get(1).addCourse(C2.get(i));
                                        break;
                                    }
                                }
                                else if (isStat(C2.get(i))){
                                    if (satisfyStat(user) == true) {
                                        plans.get(1).addCourse(C2.get(i));
                                        break;
                                    }
                                }
                                else if (C1.get(i).getCourseCode() == "MATH201" || C1.get(i).getCourseCode() == "MATH307"){
                                    if (hardCode(user) == true) {
                                        plans.get(1).addCourse(C2.get(i));
                                        break;
                                    }
                                }
                                else {
                                    plans.get(1).addCourse(C2.get(i));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < plans.get(1).getCourseList().size(); i++) {
            System.out.println(plans.get(1).getCourseList().get(i));
        }
        System.out.println("");

        // plan3
        plans.get(2).clear();
        System.out.println("plan3");
        for (int i = 1; i < viableCourses.size(); i++) {
            if (!isInPlan2(viableCourses.get(i), plans.get(0)) && !isInPlan2(viableCourses.get(i), plans.get(1))) {
                if (viableCourses.get(i).getCourseCode() == "MATH201" || viableCourses.get(i).getCourseCode() == "MATH307") {
                    if (hardCode(user) == true) {
                        plans.get(2).addCourse(viableCourses.get(i)); // add the first course to plan2
                        break;
                    }
                } else {
                    plans.get(2).addCourse(viableCourses.get(i)); // add the first course to plan2
                    break;
                }
            }
        }
        ArrayList<Course> C3 = viableCourses;
        for (int m = 0; m < MAX_NUM_COURSES - 1; m++) { // the only difference is that the first course is not in previous plans
            if (C3.size() > 2) {
                String timeslot1 = plans.get(2).getCourseAt(m).getTimeSlot();
                C3 = noOverlapCourses(timeslot1, C3);
                if (C3.size() > 2) {
                    if (isInPlan(C3.get(0), plans.get(2)) == false) {
                        if (isBreadth(C3.get(0))) {
                            if (satisfyBreadth(C3.get(0), user) == true)
                                plans.get(2).addCourse(C3.get(0));
                        }
                        else if (isDepth(C3.get(0)))
                        {
                            if (satisfyDepth(C3.get(0), user) == true)
                                plans.get(2).addCourse(C3.get(0));
                        }
                        else if (isStat(C3.get(0))){
                            if (satisfyStat(user) == true)
                                plans.get(2).addCourse(C3.get(0));
                        }
                        else if (C3.get(0).getCourseCode() == "MATH201" || C3.get(0).getCourseCode() == "MATH307"){
                            if (hardCode(user) == true)
                                plans.get(2).addCourse(C3.get(0));
                        }
                        else
                            plans.get(2).addCourse(C3.get(0));
                    }
                    else if (isInPlan(C3.get(0), plans.get(2)) == true) {
                        for (int i = 0; i < C3.size(); i++) {
                            String timeslot2 = C3.get(i).getTimeSlot();
                            if (isSplit(timeslot1, timeslot2) == false && isInPlan(C3.get(i), plans.get(2)) == false) {
                                if (isBreadth(C3.get(i))) {
                                    if (satisfyBreadth(C3.get(i), user) == true) {
                                        plans.get(2).addCourse(C3.get(i));
                                        break;
                                    }
                                }
                                else if (isDepth(C3.get(i))) {
                                    if (satisfyDepth(C3.get(i), user) == true) {
                                        plans.get(2).addCourse(C3.get(i));
                                        break;
                                    }
                                }
                                else if (isStat(C3.get(i))){
                                    if (satisfyStat(user) == true) {
                                        plans.get(2).addCourse(C3.get(i));
                                        break;
                                    }
                                }
                                else if (C3.get(i).getCourseCode() == "MATH201" || C3.get(i).getCourseCode() == "MATH307"){
                                    if (hardCode(user) == true) {
                                        plans.get(2).addCourse(C3.get(i));
                                        break;
                                    }
                                }
                                else {
                                    plans.get(2).addCourse(C3.get(i));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < plans.get(2).getCourseList().size(); i++) {
            System.out.println(plans.get(2).getCourseList().get(i));
        }
        System.out.println("");

        // plan4
        plans.get(3).clear();
        System.out.println("plan4");
        for (int i = 1; i < viableCourses.size(); i++) {
            if (!isInPlan2(viableCourses.get(i), plans.get(0)) && !isInPlan2(viableCourses.get(i), plans.get(1)) && !isInPlan2(viableCourses.get(i), plans.get(2))) {
                if (viableCourses.get(i).getCourseCode() == "MATH201" || viableCourses.get(i).getCourseCode() == "MATH307")
                {
                    if (hardCode(user) == true) {
                        plans.get(3).addCourse(viableCourses.get(i)); // add the first course to plan2
                        break;
                    }
                }
                else {
                    plans.get(3).addCourse(viableCourses.get(i)); // add the first course to plan2
                    break;
                }
            }
        }
        ArrayList<Course> C4 = viableCourses;
        for (int n = 0; n < MAX_NUM_COURSES - 1; n++) { // the only difference is that the first course is not in previous plans
            if (C4.size() > 0) {
                String timeslot1 = plans.get(3).getCourseAt(n).getTimeSlot();
                C4 = noOverlapCourses(timeslot1, C4);
                if (C4.size() > 0) {
                    if (isInPlan(C4.get(0), plans.get(3)) == false) {
                        if (isBreadth(C1.get(0))) {
                            if (satisfyBreadth(C4.get(0), user) == true)
                                plans.get(3).addCourse(C4.get(0));
                        }
                        else if (isDepth(C4.get(0)))
                        {
                            if (satisfyDepth(C4.get(0), user) == true)
                                plans.get(3).addCourse(C4.get(0));
                        }
                        else if (isStat(C4.get(0))){
                            if (satisfyStat(user) == true)
                                plans.get(3).addCourse(C4.get(0));
                        }
                        else if (C4.get(0).getCourseCode() == "MATH201" || C4.get(0).getCourseCode() == "MATH307"){
                            if (hardCode(user) == true)
                                plans.get(3).addCourse(C4.get(0));
                        }
                        else
                            plans.get(3).addCourse(C4.get(0));
                    }
                    else if (isInPlan(C4.get(0), plans.get(3)) == true) {
                        for (int i = 0; i < C4.size(); i++) {
                            String timeslot2 = C4.get(i).getTimeSlot();
                            if (isSplit(timeslot1, timeslot2) == false && isInPlan(C4.get(i), plans.get(3)) == false) {
                                if (isBreadth(C4.get(i))) {
                                    if (satisfyBreadth(C4.get(i), user) == true) {
                                        plans.get(3).addCourse(C4.get(i));
                                        break;
                                    }
                                }
                                else if (isDepth(C4.get(i))) {
                                    if (satisfyDepth(C4.get(i), user) == true) {
                                        plans.get(3).addCourse(C4.get(i));
                                        break;
                                    }
                                }
                                else if (isStat(C4.get(i))){
                                    if (satisfyStat(user) == true) {
                                        plans.get(3).addCourse(C4.get(i));
                                        break;
                                    }
                                }
                                else if (C4.get(i).getCourseCode() == "MATH201" || C4.get(i).getCourseCode() == "MATH307"){
                                    if (hardCode(user) == true) {
                                        plans.get(3).addCourse(C4.get(i));
                                        break;
                                    }
                                }
                                else {
                                    plans.get(3).addCourse(C4.get(i));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < plans.get(3).getCourseList().size(); i++) {
            System.out.println(plans.get(3).getCourseList().get(i));
        }
        System.out.println("");

        // plan5
        plans.get(4).clear();
        System.out.println("plan5");
        for (int i = 1; i < viableCourses.size(); i++) {
            if (!isInPlan2(viableCourses.get(i), plans.get(0)) && !isInPlan2(viableCourses.get(i), plans.get(1)) && !isInPlan2(viableCourses.get(i), plans.get(2)) && !isInPlan2(viableCourses.get(i), plans.get(3)))
            {
                if (viableCourses.get(i).getCourseCode() == "MATH201" || viableCourses.get(i).getCourseCode() == "MATH307")
                {
                    if (hardCode(user) == true) {
                        plans.get(4).addCourse(viableCourses.get(i)); // add the first course to plan2
                        break;
                    }
                }
                else {
                    plans.get(4).addCourse(viableCourses.get(i)); // add the first course to plan2
                    break;
                }
            }
        }
        ArrayList<Course> C5 = viableCourses;
        for (int g = 0; g < MAX_NUM_COURSES - 1; g++) { // the only difference is that the first course is not in previous plans
            if (C5.size() > 1) {
                String timeslot1 = plans.get(0).getCourseAt(g).getTimeSlot();
                C5 = noOverlapCourses(timeslot1, C5);
                if (C5.size() > 1) {
                    if (isInPlan(C5.get(0), plans.get(4)) == false) {
                        if (isBreadth(C5.get(0))) {
                            if (satisfyBreadth(C5.get(0), user) == true)
                                plans.get(4).addCourse(C5.get(0));
                        }
                        else if (isDepth(C5.get(0)))
                        {
                            if (satisfyDepth(C5.get(0), user) == true)
                                plans.get(4).addCourse(C5.get(0));
                        }
                        else if (isStat(C5.get(0))){
                            if (satisfyStat(user) == true)
                                plans.get(4).addCourse(C5.get(0));
                        }
                        else if (C5.get(0).getCourseCode() == "MATH201" || C5.get(0).getCourseCode() == "MATH307"){
                            if (hardCode(user) == true)
                                plans.get(4).addCourse(C5.get(0));
                        }
                        else
                            plans.get(4).addCourse(C5.get(0));
                    }
                    else if (isInPlan(C5.get(0), plans.get(4)) == true) {
                        for (int i = 0; i < C5.size(); i++) {
                            String timeslot2 = C5.get(i).getTimeSlot();
                            if (isSplit(timeslot1, timeslot2) == false && isInPlan(C5.get(i), plans.get(4)) == false) {
                                if (isBreadth(C5.get(i))) {
                                    if (satisfyBreadth(C5.get(i), user) == true) {
                                        plans.get(4).addCourse(C5.get(i));
                                        break;
                                    }
                                }
                                else if (isDepth(C5.get(i))) {
                                    if (satisfyDepth(C5.get(i), user) == true) {
                                        plans.get(4).addCourse(C5.get(i));
                                        break;
                                    }
                                }
                                else if (isStat(C5.get(i))){
                                    if (satisfyStat(user) == true) {
                                        plans.get(4).addCourse(C5.get(i));
                                        break;
                                    }
                                }
                                else if (C5.get(i).getCourseCode() == "MATH201" || C5.get(i).getCourseCode() == "MATH307"){
                                    if (hardCode(user) == true)
                                        plans.get(4).addCourse(C5.get(i));
                                }
                                else {
                                    plans.get(4).addCourse(C5.get(i));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < plans.get(4).getCourseList().size(); i++) {
            System.out.println(plans.get(4).getCourseList().get(i));
        }


        user.setPlans(plans);
        return plans;
    }

    public static void addLPCourses(Plan plan, ArrayList<Course> UserChoice)
    {
        for (int i = plan.getCourseList().size(); i < MAX_NUM_COURSES; i++)
            plan.addCourse(UserChoice.get(i));
    }

    public static ArrayList<Course> getLPoptions(User user, Plan plan) throws Exception { // Given a not full plan,
        // this method should return viable low priority courses that the user could choose from
        ArrayList<Integer> Group1CourseIDs = CourseDBConnect.getCourseDBConnectInstance().getElectiveGroup1CourseList();
        ArrayList<Integer> Group2CourseIDs = CourseDBConnect.getCourseDBConnectInstance().getElectiveGroup2CourseList();
        Group1CourseIDs.addAll(Group2CourseIDs);
        ArrayList<Course> Group1Courses = new ArrayList<>();
        for (int i = 0; i < Group1CourseIDs.size(); i++)
            Group1Courses.add(CourseDBConnect.getCourseDBConnectInstance().getCourse(Group1CourseIDs.get(i)));

        ArrayList<Course> qualifiedCourses = new ArrayList<>();
        ArrayList<String> courseTaken = UserInfoDBConnect.getCourseCodeTaken(user.getUserName());
        for (Course course : Group1Courses)
            if (!courseTaken.contains(course.getCourseCode()))
                qualifiedCourses.add(course);

        ArrayList<Course> viableCourses = new ArrayList<>();

        for (int i = 0; i < qualifiedCourses.size(); i++) {
            if (user.metPrerequisite(qualifiedCourses.get(i)) == true)
                viableCourses.add(qualifiedCourses.get(i));
        }

        ArrayList<Course> C = viableCourses;
        ArrayList<Course> output = new ArrayList<Course>();

        for (int i = 0; i < plan.getNumOfCourses(); i++) {
            String timeslot1 = plan.getCourseAt(i).getTimeSlot();
            C = noOverlapCourses(timeslot1, C);
                if (!isInPlan(C.get(0), plan)) {
                    if (isGroup1(C.get(0))) {
                        if (satisfyElective(C.get(0), user))
                            output.add(C.get(0));
                    }
                    else if (isGroup2(C.get(0))){
                        if(satisfyGroup2(C.get(0), user) && satisfyElective(C.get(0), user))
                            output.add(C.get(0));
                    }
                }
            }
        return output;

    }

    public static boolean isGroup1(Course course) // check if a course is group1
    {
        if (course.getCourseType().charAt(4) == '1')
            return true;
        else
            return false;
    }

    public static boolean isGroup2(Course course) // check if a course is group2
    {
        if (course.getCourseType().charAt(4) == '2')
            return true;
        else
            return false;
    }

    public static boolean satisfyElective(Course course, User user)
    {
        boolean output = false;
        int electiveCount = 0;
        ArrayList<String> Group1Courses = CourseDBConnect.getCourseDBConnectInstance().getElectiveGroup1CourseCodeList();
        ArrayList<String> Group2Courses = CourseDBConnect.getCourseDBConnectInstance().getElectiveGroup2CourseCodeList();
        Group1Courses.addAll(Group2Courses);

        ArrayList<String> coursesTaken = UserInfoDBConnect.getCourseCodeTaken((user.getUserName()));

        for (int i = 0; i < Group1Courses.size(); i++) {
            if (coursesTaken.contains(Group1Courses.get(i)))
                electiveCount++;
        }

        if(isGroup2(course) || isGroup1(course))
            electiveCount++;

        if (electiveCount < 6)
            output = true;

        return output;
    }

    public static boolean satisfyGroup2(Course course, User user)
    {
        boolean output = false;
        int Group2count = 0;
        ArrayList<String> Group2Courses = CourseDBConnect.getCourseDBConnectInstance().getElectiveGroup2CourseCodeList();
        ArrayList<String> coursesTaken = UserInfoDBConnect.getCourseCodeTaken((user.getUserName()));

        for (int i = 0;  i < Group2Courses.size(); i++) {
            if (coursesTaken.contains(Group2Courses.get(i)))
                Group2count++;
        }

        if (isGroup2(course))
            Group2count++;

        if (Group2count < 3) // if the user has taken no more than 2 courses from group2, return true
            output = true;

        return output;
    }

    public static boolean hardCode(User user)
    {
        boolean output = true;
        ArrayList<String> coursesTaken = UserInfoDBConnect.getCourseCodeTaken(user.getUserName());

        if (coursesTaken.contains("MATH201") || coursesTaken.contains("MATH307"))
            output = false;
        return output;
    }

    public static boolean isStat(Course course) // check if a course is statistics
    {
        if (course.getCourseType().charAt(5) == '1')
            return true;
        else
            return false;
    }

    public static boolean satisfyStat(User user)
    {
        boolean output = true;
        ArrayList<String> statCourses = CourseDBConnect.getCourseDBConnectInstance().getStatisticsCourseCodeList();
        ArrayList<String> coursesTaken = UserInfoDBConnect.getCourseCodeTaken(user.getUserName());
        for (int i = 0; i < statCourses.size(); i++) {
            if (coursesTaken.contains(statCourses.get(i)))
                output = false;
        }

        return output;
    }

    public static boolean isDepth(Course course) // check if a course is depth
    {
        if (course.getCourseType().charAt(3) == '1')
            return true;
        else
            return false;
    }

    public static boolean satisfyDepth(Course course, User user)
    {
        boolean output = false;
        ArrayList<Course> depthCourses = CourseDBConnect.getCourseDBConnectInstance().getCourseListByDepth(user.getTrack());
        int depthCount = 0;
        ArrayList<String> coursesTaken = UserInfoDBConnect.getCourseCodeTaken(user.getUserName());
        for (int i = 0; i < depthCourses.size(); i++) {
            if (coursesTaken.contains(depthCourses.get(i).getCourseCode()))
                depthCount++;
        }

        if (isDepth(course))
            depthCount++;

        if (depthCount < 5)
            output = true;

        return output;
    }

    public static boolean isBreadth(Course course) // check if a course is breadth
    {
        if (course.getCourseType().charAt(2) == '1')
            return true;
        else
            return false;
    }

    public static boolean satisfyBreadth(Course course, User user)
    {
        boolean output = false;
        ArrayList<String> breadthCourses = CourseDBConnect.getCourseDBConnectInstance().getBreadthCourseCodeList(); //should get all breadth courses from the database
        int breadthCount = 0;
        ArrayList<String> coursesTaken = UserInfoDBConnect.getCourseCodeTaken(user.getUserName());
        for (int i = 0; i < breadthCourses.size(); i++) {
            if (coursesTaken.contains(breadthCourses.get(i)))
                breadthCount++;
        }

        if (isBreadth(course))
            breadthCount++;

        if (breadthCount < 6)
            output = true;

        return output;

    }

    public static boolean isInPlan(Course course, Plan plan) // check if a course is already in current plan by courseCode
    {
        boolean output = false;
        for (int i = 0; i < plan.getCourseList().size(); i++)
        {
            if (plan.getCourseList().get(i).getCourseCode().equals(course.getCourseCode()))
                output = true;
        }
        return output;
    }

    public static boolean isInPlan2(Course course, Plan plan) // check if a course is already in current plan by courseID
    {
        boolean output = false;
        for (int i = 0; i < plan.getCourseList().size(); i++)
        {
            if (plan.getCourseList().get(i).getCourseID() == (course.getCourseID()))
                output = true;
        }
        return output;
    }

    public static ArrayList<Course> noOverlapCourses(String timeslot1, ArrayList<Course> courses) throws Exception {
        ArrayList<Course> output = new ArrayList<Course>();
        for (int i =0; i < courses.size(); i++)
        {
            String timeslot2 = courses.get(i).getTimeSlot();
            if (Generator.isSplit(timeslot1,timeslot2) == false)
            {
                output.add(courses.get(i));
            }
        }
        return output;
    }

    
    public static boolean isSplit(String timeSlot1, String timeSlot2) throws Exception {
      boolean overlap = false;
      if (timeSlot1.length()>11) {
        String a1 = timeSlot1.substring(0, 11);
        String a2 = timeSlot1.substring(11, 22);
        if (timeSlot2.length()>11) { //if both courses have labs
          String b1 = timeSlot2.substring(0, 11);
          String b2 = timeSlot2.substring(11, 22);
          overlap = ifOverlap(a1, b1);
          if (overlap) return overlap;
          overlap = ifOverlap(a1, b2);
          if (overlap) return overlap;
          overlap = ifOverlap(a2, b1);
          if (overlap) return overlap;
          overlap = ifOverlap(a2, b2);
        }else{ //if the first course has lab
          overlap = ifOverlap(a1, timeSlot2);
          if (overlap) return overlap;
          overlap = ifOverlap(a2, timeSlot2);
        }
      }else{
        if (timeSlot2.length()>11) { //if the second course has lab
          String b1 = timeSlot2.substring(0, 11);
          String b2 = timeSlot2.substring(11, 22);
          
          overlap = ifOverlap(timeSlot1, b1);
          if (overlap) return overlap;
          overlap = ifOverlap(timeSlot1, b2);
        }else{ //if both courses don't have labs
          overlap = ifOverlap(timeSlot1, timeSlot2);
        }
      }
      return overlap;
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
        User xx = new User("jieyu",1);

        Instant start = Instant.now();
        Generator.generate(CourseDBConnect.getCourseDBConnectInstance().getAllHighPriorityCoursesByPriority(), xx);
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
        System.out.println("The execution time is " + timeElapsed / 1000 + " seconds."); //print in seconds
    }
}
