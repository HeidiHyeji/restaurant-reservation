/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.storage ;

import java.io.* ;
import java.sql.* ;
import java.util.* ;

class Connectivity
{
  /**
   * getConnection() takes the name of a property file and returns
   * a connection.  A null return indicates failure to make a connection.
   * Any exceptions raised are printed, for ease of tracing.
   *
   * We assume the property file contains:
   *
   *   jdbc.driver: the name of the driver to be used
   *   jdbc.url:    the URL of the database to connect to
   */
  
  static Connection getConnection(String propFile)
  {
    Properties props = new Properties() ;
    InputStream pfile = null ;
    Connection con = null ;

    /**property file을 로드하기*/
    
    try {
      pfile = new FileInputStream(propFile) ;
      props.load(pfile) ;
      pfile.close() ;
    }
    catch (IOException e) {
      e.printStackTrace() ;
      return con ;
    }
    finally {
      if (pfile != null) {
	try {
	  pfile.close() ;
	}
	catch (IOException ignored) {}
      }
    }

    /**드라이버 로드하기*/
    
    String driver = props.getProperty("jdbc.driver") ;
    try {
      Class.forName(driver) ;
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace() ;
      return con ;
    }

    /**드라이버 연결하기 & properties 파일로부터 DB user와 password 가져오기*/
    
    String dbURL = props.getProperty("jdbc.url") ;
    String dbUser= props.getProperty("jdbc.user");
    String dbPass= props.getProperty("jdbc.pass");
    try{
      con = DriverManager.getConnection(dbURL,dbUser,dbPass) ;
    }
    catch (SQLException e) {
      e.printStackTrace() ;
    }
    return con ;
  }

}
