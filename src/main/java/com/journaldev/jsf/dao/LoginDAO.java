package com.journaldev.jsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.journaldev.jsf.util.DataConnect;

//+-----------+--------------+------+-----+---------+-------+
//username 
//password
//full_name
//role
//country
//enabled
//+-----------+--------------+------+-----+---------+-------+

public class LoginDAO {

	public static boolean validate(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
//			ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
			ps = con.prepareStatement("Select username, password from users where username = ? and password = ?");    //mysql:playground
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		return false;
	}

	public static boolean validate2(String user, String password) {
		LoginRestClient lrc = new LoginRestClient(user);
		if (lrc.getUsr().getUsername().equals(user) && lrc.getUsr().getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}