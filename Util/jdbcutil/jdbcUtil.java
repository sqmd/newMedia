import java.sql.*;

public class JdbcUtil {//EmployeeDaoImpl类  save方法
	final static String driverClassName="com.mysql.jdbc.Driver";
	//此处数据库名：Demo
	final static String url="jdbc:mysql://localhost:3306/Demo?useUnicode=true&characterEncoding=UTF-8&useSSL=true";
	final static String user="root";
	final static String password="SQ12345678";
	
	private static JdbcUtil util;
	static {	  	
		util=new JdbcUtil();
	  	try {
	  		Class.forName(driverClassName);
	  	} catch (ClassNotFoundException e) {
	  		System.out.println("创建连接错误");
	  		e.printStackTrace();
	  	}
	  }
	private JdbcUtil(){}
		
	public static Connection getconn(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("建立连接错误");
			e.printStackTrace();
		}
		return conn;
	}
	
//--------------------------------------关闭连接，释放资源--------------------------------------------------------
	//一次性关闭所有资源   
	//可用null---------------------
	//例如：没有结果集,就用null。JdbcUtil.closeCRS(conn,null,ps);
	public static void closeCRS(Connection conn, Statement ps,ResultSet rs) {//掘金上博客的close方法
	  	if (rs != null) {
	  		try {
	  			rs.close();
	  		} catch (SQLException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		} finally {
	  			if (ps != null) {
	  				try {
	  					ps.close();
	  				} catch (SQLException e) {
	  					// TODO Auto-generated catch block
	  					e.printStackTrace();
	  				} finally {
	  					if (conn != null) {
	  						try {
	  							conn.close();
	  						} catch (SQLException e) {
	  							// TODO Auto-generated catch block
	  							e.printStackTrace();
	  						}
	  					}
	  				}
	  			}
	  		}
	  	}
	}	
		

}
/*
	Class.forName("com.mysql.jdbc.Driver");		
	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/javabook","root","");//��̬����	
	Statement statement=connection.createStatement();
	
	//statement.executeUpdate("create table Temp(col1 char(5),col2 char(5)");//ִ�ж����������
	ResultSet resultSet=statement.executeQuery("select firstName,mi,lastname from Student where lastName='Smith'");//ִ�в�ѯ���
	
	while(resultSet.next()) {//
		System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3));//	resultSet.getString("firstName")Ҳ����	
	}
			
	connection.close();
*/
/*
	public static Statement closeStmt(Statement stmt){//学长代码里三个分散的close方法
		try {
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}
	
 */

/*--------------getStmt----getps---------------------------------------------
public static Statement getStmt(Connection conn){
		Statement stmt=null;
		try {
			stmt=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}
			
	public static PreparedStatement getps(Connection conn,String sql){
		PreparedStatement ps=null;
		try{
			ps=conn.prepareStatement(sql);//此处sql：带"?"
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
 */


/*
//--------------------------增删改查---------------------------------------------------------------------	
	//查
	  public static ResultSet executeQuery(Statement stmt,String sql){
			ResultSet rs=null;
			try {
				rs=stmt.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rs;
		}
	  
	  //增删改
	  //statement.executeUpdate("create table Temp(col1 char(5),col2 char(5)");//ִ�ж����������
		public static void executeUpdate(Statement stmt,String sql) {
			try {
				stmt.executeUpdate(sql);//返回值为其他类型
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		public static ResultSet executeQuery(Connection conn,String sql){
			ResultSet rs=null;
			try {
				rs=conn.createStatement().executeQuery(sql);//---------------
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
		}
		
		
 */




