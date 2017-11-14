/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.persistency;

import booksys.storage.*;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class CustomerMapper {

	/** 싱글톤패턴: */
	private static CustomerMapper uniqueInstance;

	public static CustomerMapper getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new CustomerMapper();
		}
		return uniqueInstance;
	}

	/** 생성자 */

	private CustomerMapper() {
		cache = new Hashtable();
	}

	private Hashtable cache; // Implementation of hidden cache

	/** 해시테이블 cache에 삽입하는 메소드 */
	private void addToCache(PersistentCustomer c)// (key,value)=(oid,PersistentCustomer)로
													// 넣음
	{
		Integer key = new Integer(c.getId());
		cache.put(key, c);
	}

	/** 해시테이블 value 리턴 */
	private PersistentCustomer getFromCache(int oid) {
		Integer key = new Integer(oid);
		return (PersistentCustomer) cache.get(key);
	}

	/** 이름과 전화번호가 동일한 PersistentCustomer 얻기 */
	private PersistentCustomer getFromCacheByDetails(String name, String phone) {
		PersistentCustomer c = null;
		Enumeration<PersistentCustomer> e = cache.elements();
		while (c == null && e.hasMoreElements()) {
			PersistentCustomer tmp = (PersistentCustomer) e.nextElement();
			if (name.equals(tmp.getName()) && phone.equals(tmp.getPhoneNumber())) {
				c = tmp;
			}
		}
		return c;
	}

	/** name과 phone을 갖는 Customer가 해시테이블 cache에 없다면 DB에서 찾고 DB에 없다면  DB에 create, 있다면 캐쉬에 add */
	public PersistentCustomer getCustomer(String n, String p) {
		PersistentCustomer c = getFromCacheByDetails(n, p);
		if (c == null) {
			c = getCustomer("SELECT * FROM Customer WHERE name = '" + n + "' AND phoneNumber = '" + p + "'");
			if (c == null) {
				c = createCustomer(n, p);
			}
			addToCache(c);
		}
		return c;
	}

	/** oid를 갖고 있는 Customer 해시테이블 cache에 없다면 DB에서 찾고 그마저 없으면 add */
	PersistentCustomer getCustomerForOid(int oid) {
		PersistentCustomer c = getFromCache(oid);
		if (c == null) {
			c = getCustomer("SELECT * FROM Customer WHERE oid ='" + oid + "'");
			if (c != null) {
				addToCache(c);
			}
		}
		return c;
	}
	//여기서부터 DB에 직접적 관련 메소드
	/** DB에 Customer 생성하기 */
	PersistentCustomer createCustomer(String name, String phone) {
		PersistentCustomer c = getFromCacheByDetails(name, phone);
		if (c == null) {
			try {
				Statement stmt = Database.getInstance().getConnection().createStatement();
				int updateCount = stmt.executeUpdate(
						"INSERT INTO Customer (name, phoneNumber)" + "VALUES ('" + name + "', '" + phone + "')");
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			c = getCustomer(name, phone);
		}
		return c;
	}

	private PersistentCustomer getCustomer(String sql) {
		PersistentCustomer c = null;
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			while (rset.next()) {
				int oid = rset.getInt(1);
				String name = rset.getString(2);
				String phone = rset.getString(3);
				c = new PersistentCustomer(oid, name, phone);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
