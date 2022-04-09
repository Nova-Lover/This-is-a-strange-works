package daoIMP;

import bean.Student;
import dao.StudentDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connection.DataBaseConnection;

public class StudentDAOIMP implements StudentDAO {
    // 添加操作

    private PreparedStatement pstmt;
    private DataBaseConnection conn;

    @Override
    public void insert(Student s) {
        String sql = "INSERT INTO student (id, name) values (?,?)";
        //针对数据库的具体操作
        try {
            conn = new DataBaseConnection();

            pstmt = conn.getConnection().prepareStatement(sql);
            pstmt.setLong(1, s.getID());
            //pstmt.setString(1,s.getID());
            pstmt.setString(2, s.getName());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void update(Student s) {
        String sql = "UPDATE student set name = ? where id = ?";

        //针对数据库的具体操作
        try {
            conn = new DataBaseConnection();

            pstmt = conn.getConnection().prepareStatement(sql);
            pstmt.setLong(1, s.getID());
            pstmt.setString(2, s.getName());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void delete(String iD) {
        String sql = "DELETE FROM student WHERE id = ?";

        //针对数据库的具体操作
        try {
            conn = new DataBaseConnection();

            pstmt = conn.getConnection().prepareStatement(sql);
            pstmt.setLong(1, Long.parseLong(iD));

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
        }
    }

    @Override
    public Student findByID(long iD) {
        String sql = "Select * FROM student where id = ?";

        //针对数据库的具体操作
        try {
            conn = new DataBaseConnection();

            pstmt = conn.getConnection().prepareStatement(sql);
            pstmt.setLong(1, iD);

            ResultSet resultSet = pstmt.executeQuery();
            Student temp = new Student();

            if(resultSet.next()){
                temp.setID(resultSet.getInt(1));
                temp.setName(resultSet.getString(2));
            }

            pstmt.close();
            conn.close();
            return temp;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        String sql = "Select * FROM student ";

        //针对数据库的具体操作
        try {
            conn = new DataBaseConnection();

            pstmt = conn.getConnection().prepareStatement(sql);

            ResultSet resultSet = pstmt.executeQuery();
            List<Student> list = new ArrayList<>();
            Student temp = new Student();

            if(resultSet.next()){
                temp.setID(resultSet.getInt(1));
                temp.setName(resultSet.getString(2));
                list.add(temp);
            }


            pstmt.close();
            conn.close();

            return list;
        } catch (Exception e) {
        }
        return null;
    }
}
