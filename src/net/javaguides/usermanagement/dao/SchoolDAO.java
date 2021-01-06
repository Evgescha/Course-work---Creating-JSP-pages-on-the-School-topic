package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.School;

public class SchoolDAO {
	private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/schooljsp?useUnicode=true&serverTimezone=UTC&useSSL=false";
	private String jdbcSchoolname = "root";
	private String jdbcPassword = "1234";

	private static final String INSERT_SQL = "INSERT INTO school" + "  (yearOfFoundation, name, adres) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_BY_ID = "select id,yearOfFoundation, name, adres from school where id =?";
	private static final String SELECT_ALL = "select * from school";
	private static final String DELETE_SQL = "delete from school where id = ?;";
	private static final String UPDATE_SQL = "update school set yearOfFoundation = ?,name= ?, adres=? where id = ?;";

	public SchoolDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcSchoolname, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void insert(School School) throws SQLException {
		System.out.println(INSERT_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
			preparedStatement.setInt(1, School.getYearOfFoundation());
			preparedStatement.setString(2, School.getName());
			preparedStatement.setString(3, School.getAdres());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public School select(int id) {
		School School = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int year = Integer.parseInt(rs.getString("yearOfFoundation"));
				String name = rs.getString("name");
				String adres = rs.getString("adres");
				School = new School(id, year, name, adres);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return School;
	}

	public List<School> selectAll() {

		List<School> Schools = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int year = Integer.parseInt(rs.getString("yearOfFoundation"));
				String name = rs.getString("name");
				String adres = rs.getString("adres");
				Schools.add(new School(id, year, name, adres));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return Schools;
	}

	public boolean delete(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean update(School School) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);) {
			statement.setInt(1, School.getYearOfFoundation());
			statement.setString(2, School.getName());
			statement.setString(3, School.getAdres());
			statement.setInt(4, School.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
