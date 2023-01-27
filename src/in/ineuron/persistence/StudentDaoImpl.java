package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Student;
import in.ineuron.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao {

	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;

	@Override
	public String addStudent(String sname, Integer sage, String saddress) {

		// Establish the connection
		try {
			connection = JdbcUtil.getConnection();
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int rowsAffected = 0;
		String insert = "Insert into Student(sname, sage,saddress)values(?,?,?)";
		try {

			if (connection != null)
				// create pstmt
				pstmt = connection.prepareStatement(insert);

			if (pstmt != null) {
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddress);
			}

			if (pstmt != null) {
				rowsAffected = pstmt.executeUpdate();
			}

			if (rowsAffected == 1)
				return "success";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "failed";
	}

	@Override
	public Student searchStudent(Integer sid) {
		Student student = null;
		String selectQuery = "Select sid, sname, sage,saddress from student where sid =?";
		try {
			connection = JdbcUtil.getConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(selectQuery);

			if (pstmt != null) {
				pstmt.setInt(1, sid);
				resultSet = pstmt.executeQuery();
			}

			if (resultSet != null) {

				if (resultSet.next()) {

					student = new Student();
					student.setSid(resultSet.getInt(1));
					student.setSname(resultSet.getString(2));
					student.setSage(resultSet.getInt(3));
					student.setSaddress(resultSet.getString(4));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return student;
	}

	@Override
	public String updateStudent(Integer sid, String sname, Integer sage, String saddress) {
		try {
			connection = JdbcUtil.getConnection();
			String updateQuery = "Update student set sname=?, sage=?, saddress=? where sid = ?";
			int row = 0;
			if (connection != null)
				pstmt = connection.prepareStatement(updateQuery);

			if (pstmt != null)
				pstmt.setString(1, sname);
			pstmt.setInt(2, sage);
			pstmt.setString(3, saddress);
			pstmt.setInt(4, sid);

			if (pstmt != null)
				row = pstmt.executeUpdate();

			if (row == 1)
				return "success";
			else
				return "not available";

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return "Failed";
	}

	@Override
	public String deleteStudent(Integer sid) {

		try {
			connection = JdbcUtil.getConnection();
			String deleteQuery = "Delete from student where sid = ?";
			int row = 0;
			if (connection != null)
				pstmt = connection.prepareStatement(deleteQuery);

			if (pstmt != null)
				pstmt.setInt(1, sid);

			if (pstmt != null)
				row = pstmt.executeUpdate();

			if (row == 1)
				return "success";
			else
				return "not available";

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return "Failed";

	}

}
