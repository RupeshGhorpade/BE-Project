package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.GeneratePassword;
import bean.BusinessBean;
import bean.RegistrationBean;


public class DBQuires {

	public boolean insertRegistrationForm(RegistrationBean rb) {
		// TODO Auto-generated method stub
		int rows = 0;
		DAO dao = new DAO();

		Connection conn = dao.getConnection();
		String password = GeneratePassword.randomPasswordIs();
		rb.setPassword(password);
		String insertquery = "insert into registration values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		System.out.println(rb.getMailid());
		try {
			pstmt = conn.prepareStatement(insertquery);
			pstmt.setString(1, rb.getFname());
			pstmt.setString(2, rb.getLname());
			pstmt.setString(3, rb.getGender());
			pstmt.setString(4, rb.getDob());
			pstmt.setString(5, rb.getBranch());
			pstmt.setString(6, rb.getYear());
			pstmt.setString(7, rb.getMailid());
			pstmt.setString(8, rb.getPassword());
			pstmt.setString(9, rb.getMno());

			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();

				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (rows == 0)
			return false;

		return true;

	}

	public String checkLogin(RegistrationBean bean) {
		// TODO Auto-generated method stub
		DAO data = new DAO();
		Connection conn = data.getConnection();
		System.out.println("Trying to Login");
		String validateUser = "select fname from  registration where email=? and password = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, bean.getMailid());
			preparedStatement.setString(2, bean.getPassword());

			// execute insert SQL statement
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				System.out.println("Login Successfull");
				return result.getString(1);
			}
			

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub

		return null;
	}

	public RegistrationBean getForgetPassword(RegistrationBean bean) {
		// TODO Auto-generated method stub
		DAO data = new DAO();
		Connection conn = data.getConnection();
		String validateUser = "select password from  registration where email=? ";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, bean.getMailid());

			// execute insert SQL statement
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				bean.setPassword(result.getString(1));
				return bean;
			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub

		return null;
	}

	public int changePwd(RegistrationBean rb, String emailid) {

		DAO data = new DAO();
		Connection conn = data.getConnection();
		String validateUser = "select password from  registration where email=? ";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, emailid);

			System.out.println("Email id" + emailid + rb.getPassword());
			// execute insert SQL statement
			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				System.out.println(result.getString(1));
				if (result.getString(1).equals(rb.getPassword())) {
					validateUser = "update registration set password=? where email=?";
					preparedStatement = conn.prepareStatement(validateUser);
					preparedStatement.setString(1, rb.getChangepwd());
					preparedStatement.setString(2, emailid);
					return preparedStatement.executeUpdate();
				} else {
					return 0;
				}
			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return 0;

		// TODO Auto-generated method stub

	}

	public void insertQuery(BusinessBean bb) {
		// TODO Auto-generated method stub
		
		DAO dao = new DAO();

		Connection conn = dao.getConnection();
		
		
		String insertquery = "insert into postquery (fname,query) values(?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(insertquery);
			pstmt.setString(1, bb.getFname());
			pstmt.setString(2, bb.getQuery());
			 pstmt.executeUpdate();
			 System.out.println(pstmt.toString());

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();

				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
	}

	public ArrayList<BusinessBean> getQueries() {
		// TODO Auto-generated method stub
		DAO data = new DAO();
		Connection conn = data.getConnection();
		ArrayList<BusinessBean> queryList = new ArrayList<BusinessBean>();
		String validateUser = "select fname,query from  postQuery";
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = conn.prepareStatement(validateUser);

			// execute insert SQL statement
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				BusinessBean bb = new BusinessBean();
				bb.setFname(result.getString(1));
				bb.setQuery(result.getString(2));
				queryList.add(bb);
			}

	}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		try {
			preparedStatement.close();

			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		return queryList;
}

	public ArrayList<String> getComments(String query) {
		// TODO Auto-generated method stub
		DAO data = new DAO();
		Connection conn = data.getConnection();
		ArrayList<String> commentList = new ArrayList<String>();
		String validateUser = "select comment from  comments where query=?";
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, query);
			// execute insert SQL statement
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				
				commentList.add(result.getString(1));
			}

	}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		try {
			preparedStatement.close();

			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
		return commentList;
	}
	public void insertEvent(BusinessBean bb) {
		// TODO Auto-generated method stub
		
		DAO dao = new DAO();

		Connection conn = dao.getConnection();
		
		
		String insertquery = "insert into event (date,description) values(?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(insertquery);
			pstmt.setString(1, bb.getEventdate());
			pstmt.setString(2, bb.getDescription());
			 pstmt.executeUpdate();
			 System.out.println(pstmt.toString());

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();

				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}

	public ArrayList<BusinessBean> getEvents() {
		// TODO Auto-generated method stub
		DAO data = new DAO();
		Connection conn = data.getConnection();
		ArrayList<BusinessBean> event = new ArrayList<BusinessBean>();
		String validateUser = "select date,description from  event";
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = conn.prepareStatement(validateUser);

			// execute insert SQL statement
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				BusinessBean bb = new BusinessBean();
				bb.setEventdate(result.getString(1));
				bb.setDescription(result.getString(2));
				event.add(bb);
			}

	}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		try {
			preparedStatement.close();

			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		return event;
	}

	public void insertComments(BusinessBean bb) {
		// TODO Auto-generated method stub
		DAO dao = new DAO();

		Connection conn = dao.getConnection();
		
		
		String insertquery = "insert into comments (query,comment) values(?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(insertquery);
			pstmt.setString(1, bb.getQuery());
			pstmt.setString(2, bb.getComments());
			 pstmt.executeUpdate();
			 System.out.println(pstmt.toString());

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();

				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}
}
