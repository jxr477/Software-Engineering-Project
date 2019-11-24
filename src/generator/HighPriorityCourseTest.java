package generator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import dbconnect.CourseDBConnect;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HighPriorityCourseTest {

    private static CourseDBConnect database = CourseDBConnect.getCourseDBConnectInstance();

    /**
     * Tested method: getEGERoptions
     *
     */
    @Test
    public void getEGERoptionsTest()
    {
        ArrayList<Integer> coursesTaken = database.getGeneralCourseList();
        int removedCourse1 = coursesTaken.remove(0);
        int removedCourse2 = coursesTaken.remove(1);

        assertEquals(removedCourse1, HighPriorityCourse.getEGERoptions(coursesTaken).get(0),
                "course at index 0 is incorrect");
        assertEquals(removedCourse2, HighPriorityCourse.getEGERoptions(coursesTaken).get(1),
                "course at index 1 is incorrect");

    }

    @Test
    public void getCSCRoptionsTest() {
        ArrayList<Integer> coursesTaken = database.getCoreCourseList();
        int removedCourse1 = coursesTaken.remove(0);
        int removedCourse2 = coursesTaken.remove(1);

        assertEquals(removedCourse1, HighPriorityCourse.getCSCRoptions(coursesTaken).get(0),
                "course at index 0 is incorrect");
        assertEquals(removedCourse2, HighPriorityCourse.getCSCRoptions(coursesTaken).get(1),
                "course at index 1 is incorrect");

    }

        @Test
    public void getCSDRoptionsTest()
    {
        ArrayList<Integer> coursesTaken = database.getDepthCourseList();
        int removedCourse1 = coursesTaken.remove(0);
        int removedCourse2 = coursesTaken.remove(1);

        assertEquals(removedCourse1, HighPriorityCourse.getCSDRoptions(coursesTaken).get(0),
                "course at index 0 is incorrect");
        assertEquals(removedCourse2, HighPriorityCourse.getCSDRoptions(coursesTaken).get(1),
                "course at index 1 is incorrect");
    }

    @Test
    public void getCSBRoptionsTest()
    {
        ArrayList<Integer> coursesTaken = database.getBreadthCourseList();
        int removedCourse1 = coursesTaken.remove(0);
        int removedCourse2 = coursesTaken.remove(1);

        assertEquals(removedCourse1, HighPriorityCourse.getCSBRoptions(coursesTaken).get(0),
                "course at index 0 is incorrect");
        assertEquals(removedCourse2, HighPriorityCourse.getCSBRoptions(coursesTaken).get(1),
                "course at index 1 is incorrect");
    }

    @Test
    public void getSRoptionsTest()
    {
        ArrayList<Integer> coursesTaken = database.getStatisticsCourseList();
        int removedCourse1 = coursesTaken.remove(0);
        int removedCourse2 = coursesTaken.remove(1);

        assertEquals(removedCourse1, HighPriorityCourse.getSRoptions(coursesTaken).get(0),
                "course at index 0 is incorrect");
        assertEquals(removedCourse2, HighPriorityCourse.getSRoptions(coursesTaken).get(1),
                "course at index 1 is incorrect");
    }

}