/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.storage;

import java.sql.*;
import java.util.Date;
import java.util.Vector;

public class Database {
	private static Connection con;

	// 싱글톤 패턴

	private static Database uniqueInstance;

	public static Database getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Database();
		}
		return uniqueInstance;
	}

	private Database() {
		// DB 연결하기
		con = Connectivity.getConnection("properties/booksys.properties");
	}

	public static Connection getConnection() {
		return con;
	}

	// DB의 Oid(object ID)어트리뷰트를 모두 가져오기

	public int getId() {
		int id = 0;
		try {
			Statement stmt = con.createStatement();

			ResultSet rset = stmt.executeQuery("SELECT * FROM Oid");
			while (rset.next()) {
				id = rset.getInt(1);
			}
			rset.close();

			id++;

			int updateCount = stmt.executeUpdate("UPDATE Oid SET last_id = '" + id + "'");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}
}
