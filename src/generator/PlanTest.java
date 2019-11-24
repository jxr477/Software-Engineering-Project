package generator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class PlanTest {

    private static Plan plan = new Plan();
    private static Course course1 = new Course(1, 3, 10000, "STAT 312",
            "Statistics", "24014301545", "",
            "statistics requirement", "STAT 308");
    private static Course course2 = new Course(2, 3, 00111, "EECS 366",
            "Statistics", "24014301545", "",
            "statistics requirement", "");
    private static Course course3 = new Course(3, 4, 00111, "EECS 340",
            "Statistics", "24014301545", "EECS 233",
            "statistics requirement", "");
    private static Course course4 = new Course(4, 3, 00111, "EECS 366",
            "Statistics", "24014301545", "",
            "statistics requirement", "");
    private static Course course5 = new Course(5, 4, 00111, "EECS 340",
            "Statistics", "24014301545", "",
            "statistics requirement", "");

    @Test
    public void clearTest() {
        plan.addCourse(course1);
        plan.clear();
        Assert.assertEquals(0, plan.getCourseList().size());
    }

    @Test
    public void getCourseIDsTest() {
        plan.clear();
        plan.addCourse(course1);
        plan.addCourse(course2);
        plan.addCourse(course3);
        Assert.assertEquals(1, plan.getCourseList().get(0).getCourseID());
        Assert.assertEquals(2, plan.getCourseList().get(1).getCourseID());
        Assert.assertEquals(3, plan.getCourseList().get(2).getCourseID());

    }

    @Test
    public void isFullPlanTest() {
        plan.clear();
        plan.addCourse(course1);
        plan.addCourse(course2);
        plan.addCourse(course3);
        plan.addCourse(course4);
        plan.addCourse(course5);
        Assert.assertTrue(plan.isFullPlan());
    }

    @Test
    public void getCourseAtTest() {
        plan.clear();
        plan.addCourse(course1);
        plan.addCourse(course2);
        plan.addCourse(course3);
        plan.addCourse(course4);
        plan.addCourse(course5);
        Assert.assertEquals(3, plan.getCourseAt(2).getCourseID());
    }
}
