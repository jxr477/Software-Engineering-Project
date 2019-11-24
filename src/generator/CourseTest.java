package generator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CourseTest {

    /**
     * Structured Basis: nominal case
     *  Good data: nominal case: input is a time slot with lab/recitation sessions
     *  input: "1351140123020014001450"
     *  expected output: "Regular Section: Monday Wednesday Friday 11:40 - 12:30, Lab/Recitation Section: Tuesday 14:00 - 14:50"
     */
    @Test
    public void convertTimeSlotReadableTest1()
    {
        String input = "1351140123020014001450";
        String expected_output = "Regular Section: Monday Wednesday Friday 11:40 - 12:30, Lab/Recitation Section: Tuesday 14:00 - 14:50";
        Assert.assertEquals(expected_output, Course.convertTimeSlotReadable(input));
    }

    /**
     * Structured Basis: nominal case
     *  Good data: nominal case: input is a time slot without lab/recitation sessions
     *  input: "13511401230"
     *  expected output: "Regular Section: Monday Wednesday Friday 11:40 - 12:30"
     */
    @Test
    public void convertTimeSlotReadableTest2()
    {
        String input = "13511401230";
        String expected_output = "Regular Section: Monday Wednesday Friday 11:40 - 12:30";
        Assert.assertEquals(expected_output, Course.convertTimeSlotReadable(input));
    }

    /**
     * Structured Basis: nominal case
     *  Good data: nominal case: the first digit is zero
     *  input: "01311401230"
     *  expected output: ""
     */
    @Test
    public void convertTimeSlotReadableTest3()
    {
        String input = "01311401230";
        String expected_output = "";
        Assert.assertEquals(expected_output, Course.convertTimeSlotReadable(input));
    }

    /**
     * Structured Basis: nominal case
     *  Bad data: the length of the time slot is not correct
     *  input: "1311401230"
     *  expected output: "Wrong format of the time slot"
     */
    @Test
    public void convertTimeSlotReadableTest4()
    {
        String input = "1311401230";
        String expected_output = "Wrong format of the time slot";
        Assert.assertEquals(expected_output, Course.convertTimeSlotReadable(input));
    }


    /**
     * Tested method: convertBlankToNone
     * Structured Basis: nominal case
     *  Good data: nominal case: input string is empty
     *  input: ""
     *  expected output: "None"
     */
    @Test
    public void convertBlankToNoneTest1()
    {
        String input = "";
        String expected_output = "None";
        Assert.assertEquals(expected_output, Course.convertBlankToNone(input));
    }

    /**
     * Tested method: convertBlankToNone
     * Structured Basis: nominal case
     *  Good data: nominal case: input string is not empty
     *  input: "hello"
     *  expected output: "hello"
     */
    @Test
    public void convertBlankToNoneTest2()
    {
        String input = "hello";
        String expected_output = "hello";
        Assert.assertEquals(expected_output, Course.convertBlankToNone(input));
    }

    /**
     * Tested method: convertBlankToNone
     * Structured Basis: nominal case
     *  Good data: nominal case: a normal course STAT312
     *  expected output: "credit: 3  courseID: 1  courseCode: STAT312  courseName: Statistics  timeSlot: Regular Section: Tuesday Thursday 14:30 - 15:45  prerequisite: None  courseType: statistics requirement  substituteCourseCode: STAT308"
     */
    @Test
    public void toStringTest()
    {
        Course course = new Course(1, 3, 5, "STAT 312",
                "Statistics", "24014301545", "",
                "statistics requirement", "STAT 308");
        String expected_output = "credit: 3  courseID: 1  courseCode: STAT 312  courseName: Statistics  " +
                "timeSlot: Regular Section: Tuesday Thursday 14:30 - 15:45  prerequisite: None  " +
                "courseType: statistics requirement  substituteCourseCode: STAT 308";
        Assert.assertEquals(expected_output, course.toString());
    }


}
