import java.util.ArrayList;

public class Database {

    private static ArrayList<Course> courseList = new ArrayList<Course>();



    public Database() {
    }

    public static void setDatabase()
    {
        Course EECS132 = new Course(1, 4, 4,"EECS132",
                "Introduction to Programming in Java", "13514151505",
                "", "010000", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS233 = new Course(2, 4, 4,"EECS233",
                "Introduction to Data Structures", "24011001215",
                "EECS132", "010000", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS302 = new Course(3, 3, 4,"EECS302",
                "Discrete Mathematics", "13509301020",
                "MATH122", "010000", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS281 = new Course(4, 4, 4,"EECS281",
                "Logic Design and Computer Organization", "24017451835",
                "EECS132", "010000", HighPriorityCourse.NO_SUBSTITUTES);

        Course ENGR210 = new Course(5, 4, 0,"EECS210",
                "Circuit Design", "13514151505",
                "", "000020", HighPriorityCourse.NO_SUBSTITUTES);

        Course PHSY201 = new Course(6, 4, 0,"PHSY201",
                "Introduction to Logic", "24014301545",
                "", "000020", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS290 = new Course(7, 3, 0,"EECS290",
                "Introduction to Game Design", "24008300945",
                "EECS132", "000010", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS301 = new Course(8, 2, 0,"EECS301",
                "Digital Logic Lab", "50014151505",
                "EECS281", "000010", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS340 = new Course(9, 3, 4,"EECS340",
                "Algorithms", "13012451400",
                "EECS233", "010000", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS395 = new Course(10, 4, 4,"EECS395",
                "Senior Project", "13511401230",
                "", "010000", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS314 = new Course(11, 3, 3,"EECS314",
                "Computer Architecture", "13516251715",
                "EECS281", "001000", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS325 = new Course(12, 3, 3,"EECS325",
                "Digital Logic Lab", "50014151505",
                "EECS233", "001000", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS341 = new Course(13, 3, 3,"EECS341",
                "Databases", "24014301545",
                "EECS302", "001000", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS338 = new Course(14, 4, 3,"EECS338",
                "Operating Systems", "24016001715",
                "EECS233", "001000", HighPriorityCourse.NO_SUBSTITUTES);

        Course EECS391 = new Course(15, 3, 3,"EECS391",
                "AI", "24010001115",
                "EECS132", "001000", HighPriorityCourse.NO_SUBSTITUTES);


        courseList.clear();
        courseList.add(EECS132);
        courseList.add(EECS233);
        courseList.add(EECS302);
        courseList.add(EECS281);
        courseList.add(ENGR210);
        courseList.add(PHSY201);
        courseList.add(EECS290);
        courseList.add(EECS301);
        courseList.add(EECS340);
        courseList.add(EECS395);
        courseList.add(EECS314);
        courseList.add(EECS325);
        courseList.add(EECS341);
        courseList.add(EECS338);
        courseList.add(EECS391);

    }

    public static Course getCourse(int courseID) {
        Database.setDatabase();
        return courseList.get(courseID - 1);
    }

    public static ArrayList<Course> getAllCourse() {
        Database.setDatabase();
        return courseList;
    }

    public static String getCourseType(int courseID) {
        return courseList.get(courseID).getCourseType();
    }

    public static String getCourseCode(int courseID) {
        return courseList.get(courseID).getCourseCode();
    }

    public ArrayList<String> getAllCourseCode() {
        ArrayList<String> output = new ArrayList<String>();
        for (int i = 0; i < courseList.size(); i++)
            output.add(Database.getCourseCode(i));
        return output;
    }

    public static void main(String[] args) throws Exception {


        Database sample = new Database();
        System.out.println(sample.getCourse(8).getCourseCode());

    }

}
