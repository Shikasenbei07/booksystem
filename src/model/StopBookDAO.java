package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StopBookDAO {
	private Connection con = null;
	
	public StopBookDAO(Connection con) {
		this.con = con;
	}
	
	public boolean findStopBookByKey(String isbn) {
		int successflg = 0;
		boolean result = true;
		PreparedStatement pStmt = null;
		String sql = "update visibility set visible = 0 where isbn = ?";
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, isbn);
			
			successflg = pStmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(successflg == 1) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
	}
}	
