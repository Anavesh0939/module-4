package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.DBConnection;
import model.customer;

public class customerdao {
	public static void insertCustomer(customer c) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql ="insert into customer(fname,lname,email,mobile,gender,password) value (?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, c.getFname());
			pst.setString(2, c.getLname());
			pst.setString(3, c.getEmail());
			pst.setLong(4, c.getMobile());
			pst.setString(5, c.getGender());
			pst.setString(6, c.getPassword());
			pst.executeUpdate();
			System.out.println("data inserted");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static customer dologin(customer c) {
		customer c1 = null;
		try {
			Connection conn = DBConnection.createConnection();
			String sql ="select * from customer where email=? and password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, c.getEmail());
			pst.setString(2, c.getPassword());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				c1 = new customer();
				c1.setId(rs.getInt("id"));
				c1.setFname(rs.getString("fname"));
				c1.setLname(rs.getString("lname"));
				c1.setEmail(rs.getString("email"));
				c1.setMobile(rs.getLong("mobile"));
				c1.setGender(rs.getString("gender"));
				c1.setPassword(rs.getString("password"));
				
			}
			System.out.println("data inserted");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return c1;
	}
}
