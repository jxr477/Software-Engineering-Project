package dbconnect;

import java.sql.*;
import java.util.ArrayList;
import generator.Course;

public class CourseDBConnect {
    private static CourseDBConnect CourseDBConnectSingleton = new CourseDBConnect();

    private static final ArrayList<Integer> breadthCourseList;
    private static final ArrayList<Integer> depthCourseList;
    private static final ArrayList<Integer> coreCourseList;
    private static final ArrayList<Integer> statisticsCourseList;
    private static final ArrayList<Integer> generalCourseList;
    private static final ArrayList<Integer> elective1CourseList;
    private static final ArrayList<Integer> elective2CourseList;
    private static final ArrayList<String> breadthCourseCodeList;
    private static final ArrayList<String> depthCourseCodeList;
    private static final ArrayList<String> coreCourseCodeList;
    private static final ArrayList<String> statisticsCourseCodeList;
    private static final ArrayList<String> generalCourseCodeList;
    private static final ArrayList<String> elective1CourseCodeList;
    private static final ArrayList<String> elective2CourseCodeList;

    static {
        breadthCourseList = CourseDBConnect.getCourseDBConnectInstance().getBreadthCourseList();
        depthCourseList = CourseDBConnect.getCourseDBConnectInstance().getDepthCourseList();
        coreCourseList = CourseDBConnect.getCourseDBConnectInstance().getCoreCourseList();
        statisticsCourseList = CourseDBConnect.getCourseDBConnectInstance().getStatisticsCourseList();
        generalCourseList = CourseDBConnect.getCourseDBConnectInstance().getGeneralCourseList();
        elective1CourseList = CourseDBConnect.getCourseDBConnectInstance().getElectiveGroup1CourseList();
        elective2CourseList = CourseDBConnect.getCourseDBConnectInstance().getElectiveGroup2CourseList();
        breadthCourseCodeList = CourseDBConnect.getCourseDBConnectInstance().getBreadthCourseCodeList();
        depthCourseCodeList = CourseDBConnect.getCourseDBConnectInstance().getDepthCourseCodeList();
        coreCourseCodeList = CourseDBConnect.getCourseDBConnectInstance().getCoreCourseCodeList();
        generalCourseCodeList = CourseDBConnect.getCourseDBConnectInstance().getGeneralCourseCodeList();
        statisticsCourseCodeList = CourseDBConnect.getCourseDBConnectInstance().getStatisticsCourseCodeList();
        elective1CourseCodeList = CourseDBConnect.getCourseDBConnectInstance().getElectiveGroup1CourseCodeList();
        elective2CourseCodeList = CourseDBConnect.getCourseDBConnectInstance().getElectiveGroup2CourseCodeList();
    }

    //Return the singleton instance of DBConnect Class
    public static CourseDBConnect getCourseDBConnectInstance() {
        return CourseDBConnectSingleton;
    }

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/CSCGS";

    //  Database credentials
    private static final String USER = "postgres";
    private static final String PASS = "Jasky981013#";

    private CourseDBConnect(){}

    public String getCourseName(int id){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String output = "ERROR";
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT name FROM courses.course Where course_id = " + id;
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            if (rs.next()){
                 output = rs.getString("name");
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public String getCourseCode(int id){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String output = "ERROR";
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT course_code FROM courses.course Where course_id =" + id;
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            if(rs.next()) {
                output = rs.getString("course_code");
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public String getCourseTimeSlots(int id){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String output = "ERROR";
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT time_slots FROM courses.course Where course_id =" + id;
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            if(rs.next()) {
                output = rs.getString("time_slots");
            }

            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public String getPrerequisiteCourses(int id){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String output = "ERROR";
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT prerequisite_courses FROM courses.course Where course_id =" + id;
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            if(rs.next()) {
                output = rs.getString("prerequisite_courses");
            }

            if(output == null)
                output = "";

            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public String getCourseTypeFromDB(int id){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String output = "ERROR";

        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT type FROM courses.course Where course_id =" + id;
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            if(rs.next()) {
                output = rs.getString("type");
            }

            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public int getCourseDepth(int id){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int output = -1;
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM courses.course Where course_id =" + id;
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            if(rs.next()) {
                output = rs.getInt("depth");
            }

            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public int getCourseCreditHour(int id){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int output = -1;
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT credit_hour FROM courses.course Where course_id =" + id;
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            if(rs.next()) {
                output = rs.getInt("credit_hour");
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public String getCourseType(int id){
        CourseDBConnect courseDBConnect = CourseDBConnect.getCourseDBConnectInstance();
        String courseCode = courseDBConnect.getCourseCode(id);
        String output = courseDBConnect.getCourseTypeFromDB(id);
        if (courseCode.equals("MATH380")||courseCode.equals("STAT312")){
            output = "000001";
        }
        if (!courseCode.equals("MATH380") && !courseCode.equals("STAT312")){
            output = output + "0";
        }
        return output;
    }

    public ArrayList<Integer> getGeneralCourseList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT course_id FROM courses.course Where type = '10000'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getInt("course_id"));
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            output.remove(Integer.valueOf(39));
            output.remove(Integer.valueOf(40));
            output.remove(Integer.valueOf(41));
            output.remove(Integer.valueOf(56));
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<Integer> getCoreCourseList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT course_id FROM courses.course Where type = '01000' or type = '01010'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getInt("course_id"));
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<Integer> getBreadthCourseList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT course_id FROM courses.course Where type = '00111' or type = '00101'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getInt("course_id"));
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<Integer> getDepthCourseList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT course_id FROM courses.course Where type = '00111' or type = '00011' or type = '01010'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getInt("course_id"));
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<Integer> getElectiveGroup1CourseList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT course_id FROM courses.course Where type = '00111' or type = '00101' or type = '00011' or type = '00001'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getInt("course_id"));
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<Integer> getElectiveGroup2CourseList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT course_id FROM courses.course Where type = '00002'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getInt("course_id"));
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public String getPrerequisiteCode(String course_code){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String output = "ERROR";
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT prerequisite_courses FROM courses.course Where course_code ='" + course_code +"'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            if(rs.next()) {
                output = rs.getString("prerequisite_courses");
            }

            if(output == null)
                output = "";
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<Integer> getStatisticsCourseList(){
        ArrayList<Integer> output = new ArrayList<>();
        output.add(Integer.valueOf(39));
        output.add(Integer.valueOf(40));
        output.add(Integer.valueOf(41));
        output.add(Integer.valueOf(56));
        return output;
    }

    public ArrayList<String> getGeneralCourseCodeList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT distinct course_code FROM courses.course Where type = '10000'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getString("course_code"));
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            output.remove(Integer.valueOf(39));
            output.remove(Integer.valueOf(40));
            output.remove(Integer.valueOf(41));
            output.remove(Integer.valueOf(56));
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<String> getCoreCourseCodeList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT distinct course_code FROM courses.course Where type = '01000' or type = '01010'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getString("course_code"));
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<String> getBreadthCourseCodeList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT distinct course_code FROM courses.course Where type = '00111' or type = '00101'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getString("course_code"));
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<String> getDepthCourseCodeList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT distinct course_code FROM courses.course Where type = '00111' or type = '00011' or type = '01010'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getString("course_code"));
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<String> getElectiveGroup1CourseCodeList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT distinct course_code FROM courses.course Where type = '00111' or type = '00101' or type = '00011' or type = '00001'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getString("course_code"));
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<String> getElectiveGroup2CourseCodeList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT distinct course_code FROM courses.course Where type = '00002'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getString("course_code"));
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<Integer> getIDsFromCourseCode(String course_code){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> output = new ArrayList<>();
        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT course_id FROM courses.course Where course_code = '" + course_code + "'";
            rs = stmt.executeQuery(sql);

            //Extract data from result set
            //Retrieve by column name
            while(rs.next()) {
                output.add(rs.getInt("course_id"));
            }

            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return output;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<String> getStatisticsCourseCodeList(){
        ArrayList<String> output = new ArrayList<>();
        output.add("STAT312");
        output.add("MATH380");
        return output;
    }

     public Course getCourse(int id){
         Connection conn = null;
         Statement stmt = null;
         ResultSet rs = null;
         try{
             int credit = -1;
             String courseCode = "";
             String courseName = "";
             String timeSlot = "";
             String prerequisite = "";
             String courseType = "";
             //Register JDBC driver
             Class.forName(JDBC_DRIVER);

             //Open a connection
             conn = DriverManager.getConnection(DB_URL,USER,PASS);

             //Execute a query
             stmt = conn.createStatement();
             String sql = "SELECT * FROM courses.course Where course_id = " + id;
             rs = stmt.executeQuery(sql);

             //Extract data from result set
             //Retrieve by column name
             if (rs.next()){
                 credit = rs.getInt("credit_hour");
                 courseCode = rs.getString("course_code");
                 courseName = rs.getString("name");
                 timeSlot = rs.getString("time_slots");
                 prerequisite = rs.getString("prerequisite_courses");
                 courseType = rs.getString("type");
             }
             //Clean-up environment
             rs.close();
             stmt.close();
             conn.close();

             if(prerequisite == null)
                 prerequisite = "";

             String substituteCourseCode = Course.NO_SUBSTITUTES;
             if(courseCode.equals("MATH380")){
                 substituteCourseCode = "STAT312";
             }
             if(courseCode.equals("STAT312")){
                 substituteCourseCode = "MATH380";
             }
             if(courseCode.equals("MATH201")){
                 substituteCourseCode = "MATH307";
             }
             if(courseCode.equals("MATH307")){
                 substituteCourseCode = "MATH201";
             }
             if(CourseDBConnect.statisticsCourseCodeList.contains(courseCode) || CourseDBConnect.generalCourseCodeList.contains(courseCode)){
                 return new Course(id, credit, 5, courseCode, courseName, timeSlot, prerequisite, courseType, substituteCourseCode);
             }
             if(CourseDBConnect.coreCourseCodeList.contains(courseCode)){
                 return new Course(id, credit, 4, courseCode, courseName, timeSlot, prerequisite, courseType, substituteCourseCode);
             }
             if(CourseDBConnect.breadthCourseCodeList.contains(courseCode)){
                 return new Course(id, credit, 3, courseCode, courseName, timeSlot, prerequisite, courseType, substituteCourseCode);
             }
             if(CourseDBConnect.depthCourseCodeList.contains(courseCode)) {
                 return new Course(id, credit, 2, courseCode, courseName, timeSlot, prerequisite, courseType, substituteCourseCode);
             }
             if(CourseDBConnect.elective1CourseCodeList.contains(courseCode) || CourseDBConnect.elective2CourseCodeList.contains(courseCode)){
                 return new Course(id, credit, 1, courseCode, courseName, timeSlot, prerequisite, courseType, substituteCourseCode);
             }
             return new Course(id, credit, -1, courseCode, courseName, timeSlot, prerequisite, courseType, substituteCourseCode);
         }
         catch(SQLException se){
             //Handle errors for JDBC
             se.printStackTrace();
         }
         catch(Exception e){
             //Handle errors for Class.forName
             e.printStackTrace();
         }
         finally{
             //finally block used to close resources
             try{
                 if(stmt!=null)
                     stmt.close();
             }
             catch(SQLException se2){
             }// nothing we can do
             try{
                 if(conn!=null)
                     conn.close();
             }
             catch(SQLException se){
                 se.printStackTrace();
             }
         }
         return null;
     }

     public ArrayList<Course> getAllHighPriorityCoursesByPriority(){
        ArrayList<Course> output = new ArrayList<>();
        for(int i: CourseDBConnect.generalCourseList){
            output.add(CourseDBConnect.getCourseDBConnectInstance().getCourse(i));
        }
         for(int i: CourseDBConnect.statisticsCourseList){
             output.add(CourseDBConnect.getCourseDBConnectInstance().getCourse(i));
         }
         for(int i: CourseDBConnect.coreCourseList){
             output.add(CourseDBConnect.getCourseDBConnectInstance().getCourse(i));
         }
         for(int i: CourseDBConnect.breadthCourseList){
             output.add(CourseDBConnect.getCourseDBConnectInstance().getCourse(i));
         }
         for(int i: CourseDBConnect.depthCourseList){
             output.add(CourseDBConnect.getCourseDBConnectInstance().getCourse(i));
         }
         return output;
     }

     public ArrayList<Course> getCourseListByDepth(int depth){
         Connection conn = null;
         Statement stmt = null;
         ResultSet rs = null;
         try{
             ArrayList<Course> output = new ArrayList<>();
             int id = -1;
             int credit = -1;
             String courseCode = "";
             String courseName = "";
             String timeSlot = "";
             String prerequisite = "";
             String courseType = "";
             //Register JDBC driver
             Class.forName(JDBC_DRIVER);

             //Open a connection
             conn = DriverManager.getConnection(DB_URL,USER,PASS);

             //Execute a query
             stmt = conn.createStatement();
             String sql = "";

             if(depth == 1)
                 sql = "SELECT * FROM courses.course Where depth = 1 or depth = 13";
             if(depth == 2)
                 sql = "SELECT * FROM courses.course Where depth = 2 or depth = 23 or depth = 25";
             if(depth == 3)
                 sql = "SELECT * FROM courses.course Where depth = 3 or depth = 13 or depth = 23";
             if(depth == 4)
                 sql = "SELECT * FROM courses.course Where depth = 4 or depth = 45";
             if(depth == 5)
                 sql = "SELECT * FROM courses.course Where depth = 5 or depth = 25 or depth = 45";
             if(depth == 6)
                 sql = "SELECT * FROM courses.course Where depth = 6";

             rs = stmt.executeQuery(sql);

             //Extract data from result set
             //Retrieve by column name
             while (rs.next()){
                 id = rs.getInt("course_id");
                 credit = rs.getInt("credit_hour");
                 courseCode = rs.getString("course_code");
                 courseName = rs.getString("name");
                 timeSlot = rs.getString("time_slots");
                 prerequisite = rs.getString("prerequisite_courses");
                 courseType = rs.getString("type");
                 if(prerequisite == null)
                     prerequisite = "";
                 output.add(new Course(id, credit, 2, courseCode, courseName, timeSlot, prerequisite, courseType, Course.NO_SUBSTITUTES));
             }
             //Clean-up environment
             rs.close();
             stmt.close();
             conn.close();
             return output;
         }
         catch(SQLException se){
             //Handle errors for JDBC
             se.printStackTrace();
         }
         catch(Exception e){
             //Handle errors for Class.forName
             e.printStackTrace();
         }
         finally{
             //finally block used to close resources
             try{
                 if(stmt!=null)
                     stmt.close();
             }
             catch(SQLException se2){
             }// nothing we can do
             try{
                 if(conn!=null)
                     conn.close();
             }
             catch(SQLException se){
                 se.printStackTrace();
             }
         }
         return null;
     }
}