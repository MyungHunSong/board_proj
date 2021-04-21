package board_proj_erp;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class JdbcUtilTest {

	
	@Test
	public void testConnection() {
		Connection con = JdbcUtil.getConnection();
		Assert.assertNotNull(con);
		
		System.out.println("con >> " + con);
	}

}
