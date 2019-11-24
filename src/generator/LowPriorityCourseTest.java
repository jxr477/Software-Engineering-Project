package generator;

import dbconnect.CourseDBConnect;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LowPriorityCourseTest {

    private static CourseDBConnect database = CourseDBConnect.getCourseDBConnectInstance();

    /**
     * Tested method: getGroup1options
     */
    @Test
    public void getGroup1optionsTest()
    {
        ArrayList<Integer> coursesTaken = database.getElectiveGroup1CourseList();
        int removedCourse1 = coursesTaken.remove(0);
        int removedCourse2 = coursesTaken.remove(1);

        assertEquals(removedCourse1, LowPriorityCourse.getGroup1options(coursesTaken).get(0),
                "course at index 0 is incorrect");
        assertEquals(removedCourse2, LowPriorityCourse.getGroup1options(coursesTaken).get(1),
                "course at index 1 is incorrect");
    }

    /**
     * Tested method: getGroup2options
     */
    @Test
    public void getGroup2optionsTest()
    {
        ArrayList<Integer> coursesTaken = database.getElectiveGroup2CourseList();
        int removedCourse1 = coursesTaken.remove(0);
        int removedCourse2 = coursesTaken.remove(1);

        assertEquals(removedCourse1, LowPriorityCourse.getGroup2options(coursesTaken).get(0),
                "course at index 0 is incorrect");
        assertEquals(removedCourse2, LowPriorityCourse.getGroup2options(coursesTaken).get(1),
                "course at index 1 is incorrect");
    }
}
