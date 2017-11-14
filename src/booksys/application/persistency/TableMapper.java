/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.persistency;

import booksys.storage.Database;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class TableMapper {
	/**생성자*/
	private TableMapper() {
		cache = new Hashtable();
	}

	/**싱글톤 패턴*/
	private static TableMapper uniqueInstance;

	public static TableMapper getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TableMapper();
		}
		return uniqueInstance;
	}

	// Implementation of hidden cache
	private Hashtable cache;
	/** 해시테이블 cache에 삽입하는 메소드 */
	private void addToCache(PersistentTable t) {
		Integer key = new Integer(t.getId());
		cache.put(key, t);
	}
	/** 해시테이블 value 리턴 */
	private PersistentTable getFromCache(int oid) {
		Integer key = new Integer(oid);
		return (PersistentTable) cache.get(key);
	}
	/** 테이블번호가 동일한 PersistentTable 얻기*/
	private PersistentTable getFromCacheByNumber(int tno) {
		PersistentTable t = null;
		Enumeration<PersistentTable> e = cache.elements();
		while (t == null & e.hasMoreElements()) {
			PersistentTable tmp = (PersistentTable) e.nextElement();
			if (tmp.getNumber() == tno) {
				t = tmp;
			}
		}
		return t;
	}


	/** 테이블번호를 갖는 PersistentTable가 해시테이블 cache에 없다면 DB에서 찾고 있다면 캐쉬에 add*/ 
	public PersistentTable getTable(int tno) {
		PersistentTable t = getFromCacheByNumber(tno);
		if (t == null) {
			t = getTable("SELECT * FROM `Table` WHERE number='" + tno + "'");
			if (t != null) {
				addToCache(t);
			}
		}
		return t;
	}
	/** oid를 갖는 PersistentTable가 해시테이블 cache에 없다면 DB에서 찾고 있다면 캐쉬에 add*/ 
	PersistentTable getTableForOid(int oid) {
		PersistentTable t = getFromCache(oid);
		if (t == null) {
			t = getTable("SELECT * FROM `Table` WHERE oid ='" + oid + "'");
			if (t != null) {
				addToCache(t);
			}
		}
		return t;
	}
	//여기서부터 DB와 직접적관련 메소드
	private PersistentTable getTable(String sql) {
		PersistentTable t = null;
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			while (rset.next()) {
				int oid = rset.getInt(1);
				int number = rset.getInt(2);
				int places = rset.getInt(3);
				t = new PersistentTable(oid, number, places);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	public Vector getTableNumbers() {
		Vector v = new Vector();
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM `Table` ORDER BY number");
			while (rset.next()) {
				v.addElement(new Integer(rset.getInt(2)));
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

}
