package dbconnect;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CourseDBConnectTest {

    public static CourseDBConnect testConnection = CourseDBConnect.getCourseDBConnectInstance();
    @Test
    public void testGetCourseSeries(){
        Assert.assertEquals(testConnection.getCourseName(1), "Principles of Chemistry for Engineers");
        Assert.assertEquals(testConnection.getCourseCode(1), "CHEM111");
        Assert.assertEquals(testConnection.getCourseTimeSlots(1), "1351035112520010001050");
        Assert.assertEquals(testConnection.getPrerequisiteCourses(1), "");
        Assert.assertEquals(testConnection.getCourseTypeFromDB(1), "10000");
        Assert.assertEquals(testConnection.getCourseDepth(1), 0);
        Assert.assertEquals(testConnection.getCourseCreditHour(1), 4);
        Assert.assertEquals(testConnection.getCourseType(1), "100000");
        Assert.assertEquals(testConnection.getPrerequisiteCode("CHEM111"), "");
        Assert.assertTrue(testConnection.getIDsFromCourseCode("CHEM111").contains(1));
        Assert.assertEquals(1, testConnection.getCourse(1).getCourseID());
        Assert.assertNotNull(testConnection.getAllHighPriorityCoursesByPriority());
        Assert.assertNotNull(testConnection.getCourseListByDepth(1));

    }

}
