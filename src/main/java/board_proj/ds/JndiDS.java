package board_proj.ds;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JndiDS {
	private static DataSource ds;
	
	private JndiDS() {}
	
	static {
		 try{
		      InitialContext init = new InitialContext(); // 1. JNDI 서버 객체 생성.
		      ds = (DataSource)init.lookup("java:comp/env/jdbc/board_proj"); // 리소스 의 이름을 가지고 요거를 가져오겟다. 
		      System.out.println("ds : " + ds);
		   }catch(Exception e){
		      e.printStackTrace();
		   }	
	}
	
	 public static Connection getConnection(){
		 try {
			 return ds.getConnection();
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
		 	
	 }
}
