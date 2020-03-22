package reserva.entidades;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectarBase {
	public static Connection getOracleConnection() throws Exception {
	    String driver = "oracle.jdbc.driver.OracleDriver";
	    String url = "jdbc:oracle:thin:@192.168.91.27:1521:buxis";
	    String username = "reservaViajes";
	    String password = "reservaViajes";

	    Class.forName(driver).newInstance();
	    Connection conn = DriverManager.getConnection(url, username, password);
	    conn.setAutoCommit(false);
	    return conn;
	  }

}
