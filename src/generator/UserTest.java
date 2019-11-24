package generator;

import dbconnect.CourseDBConnect;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void metPrerequisiteTest()
    {
        Course testCourse = CourseDBConnect.getCourseDBConnectInstance().getCourse(18);
        User user = new User("jieyu", 1);
        Assert.assertTrue(user.metPrerequisite(testCourse));
    }

    @Test
    public void gettterAndSetterTest(){
        User user = new User("jieyu", 1);
        Assert.assertEquals(user.getUserName(), "jieyu");
        Assert.assertEquals(user.getTrack(), 1);
        Assert.assertEquals(user.toString(), "jieyu\t1");
    }
}
