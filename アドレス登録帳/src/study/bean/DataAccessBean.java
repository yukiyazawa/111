package study.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import study.erros.DuplicateEmailException;

public class DataAccessBean {

	private static DataSource ds = null;
	//lookupメソッドに渡すためのJNDI名を付ける
	private static final String JNDI_NAME = "java:comp/env/jdbc/ssjdb";

	private static DataSource getDataSource() throws NamingException {
		if(ds==null) {
			InitialContext ic= new InitialContext();
	//InitialContextオブジェクトのlookupメソッドにJNDI名を引数として渡し、JNDIリソースをもらう
			ds=(DataSource)ic.lookup(JNDI_NAME);
		}
		return ds;
	}

 public boolean authentication(String userName, String password)
		 	throws SQLException {
	        Connection conn = null;
	        PreparedStatement ps3 = null;
	        ResultSet rs = null;
	        try {
	            // 入力されたユーザ名,パスワードを取得する文
	            String sql = "SELECT username, password FROM users WHERE username=?";
	            DataSource datasource = getDataSource();
	            conn=datasource.getConnection();
	            ps3 = conn.prepareStatement(sql);
	            ps3.setString(1, userName);
	            //
	            rs = ps3.executeQuery();
	             if (rs.next()) {
	                // 検索結果から、パスワードを取得
	                String selectedPassword = rs.getString("password");
	                // 入力されたパスワードと、DBから取得したパスワードが一致しているかどうかを調べる
	                if (!password.equals(selectedPassword)) {
	                 // パスワードが一致しなかった場合　false
	                    return false;
	                }
	            } else {
	                // ユーザ名が一致しない場合)　false
	                return false;
	            }
	            //ユーザー名、パスワードともに一致した場合はtrue。
	             return true;
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        		throw new SQLException(e);
	        	} finally {
	            if (rs != null) {
	                rs.close();
	            }
	            if (ps3 != null) {
	                ps3.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        }
	    }
  
public Collection<UserInfo> findAllUserInfo() throws SQLException {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
       //DBから入力されたデータを持ってくるための文　ただしここで実行しているわけではない
		String sql = "SELECT name, yomi, zip, address,tel, email FROM addressbook";
       //userlistのなかにテーブル1行ずつのデータを格納したい
		Collection<UserInfo>userList= new ArrayList<UserInfo>();
		conn= getDataSource().getConnection();
      	ps= conn.prepareStatement(sql);
        //先ほど書いたSQL文を実行する
        rs=ps.executeQuery();
     while (rs.next()) {
        //userinfoに各値（名前、読み…それぞれを）を格納
        UserInfo userInfo =new UserInfo();
        userInfo.setName(rs.getString("name"));
      	userInfo.setYomi(rs.getString("yomi"));
      	userInfo.setZip(rs.getString("zip"));
      	userInfo.setAddress(rs.getString("address"));
       	userInfo.setTel(rs.getString("tel"));
       	userInfo.setEmail(rs.getString("email"));
       	//userInfoの値をuserlistに格納
       	userList.add(userInfo);
      }
      return userList;
   }catch (NamingException e) {
    	 e.printStackTrace();
    	 throw new SQLException(e);
   }finally {
	   if(rs!= null) {rs.close();}
       if(ps!= null) {ps.close();}
       if(conn!= null) {conn.close();}
    }
}


//登録用メソッド
public void registUserInfo(UserInfo userInfo) throws SQLException,DuplicateEmailException{
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
	try {
			//データを登録するための文
	    	String sql="INSERT INTO addressbook(name,yomi,zip,address,tel,email)VALUES(?,?,?,?,?,?)";

			//二重で登録していないかチェックするための文
			String sqlForcCheck ="SELECT email FROM addressbook WHERE email=?";

			conn = getDataSource().getConnection();
            ps1 = conn.prepareStatement(sqlForcCheck);
			ps1.setString(1,userInfo.getEmail());
			//二重登録チェック文を実行
			rs=ps1.executeQuery();
		if(rs.next()) {
			throw new DuplicateEmailException();
			}

			ps2=conn.prepareStatement(sql);
			ps2.setString(1,userInfo.getName());
			ps2.setString(2,userInfo.getYomi());
			ps2.setString(3,userInfo.getZip());
			ps2.setString(4,userInfo.getAddress());
			ps2.setString(5,userInfo.getTel());
			ps2.setString(6,userInfo.getEmail());


			//データ登録文を実行
			ps2.executeUpdate();
			} catch (NamingException e) {
				e.printStackTrace();
				throw new SQLException(e);
			} finally {
				if(rs!=null) {rs.close();}
				if(ps1!=null) {ps1.close();}
				if(ps2!=null) {ps2.close();}
				if(conn!=null) {conn.close();}
				}
		}


//データ消去用メソッド
public void deleteUserInfo(String email) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//データを消去する文
			String sql ="DELETE FROM addressbook WHERE email=?";
			conn = getDataSource().getConnection();
			ps= conn.prepareStatement(sql);
			ps.setString(1, email);
			//データ消去文を実行
			ps.executeUpdate();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			if(ps!=null) {ps.close();}
			if(conn!=null) {conn.close();
			}
		}
	}
	public static String getJndiName() {
		return JNDI_NAME;
	}
}
