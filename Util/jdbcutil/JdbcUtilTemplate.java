import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Driver;

import other.Database.teachedDemo.Employee;


public class JdbcUtilTemplate {
	//执行语句，如创建 删除  ，注入。。。
	public static int myExecute(String sql) {//
		Connection conn=null;
		PreparedStatement ps=null;
		int re=0;

	    try {
	    	conn = JdbcUtil.getconn();
			ps = conn.prepareStatement(sql);
	        re = ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally{
			//没有结果集,就用null
			JdbcUtil.closeCRS(conn,ps,null);
		}
	    return re;	    	 				
	}
	//增
	public static int insert(Employee emp){		
		Connection conn=null;
		PreparedStatement ps=null;
		int re=0;

		String sql="insert into Employee(name,sex,gross) values (?,?,?)";
//		String sql="insert into Employee(name,sex,gross) values ('"+name+"',"+sex+","+gross+");";
//		 rs=stmt.executeUpdate(sql);//-----			
		
		try {				
			conn = JdbcUtil.getconn();

			ps = conn.prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setBoolean(2, emp.isSex());
			ps.setDouble(3, emp.getGross());
			
			re=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//没有结果集,就用null
			JdbcUtil.closeCRS(conn,ps,null);
		}
		return re;	
	}
	public static int insert(String name,boolean sex,double gross){		
		Connection conn=null;
		PreparedStatement ps=null;
		int re=0;

		String sql="insert into Employee(name,sex,gross) values (?,?,?)";
//		String sql="insert into Employee(name,sex,gross) values ('"+name+"',"+sex+","+gross+");";
//		 rs=stmt.executeUpdate(sql);//-----			
		
		try {				
			conn = JdbcUtil.getconn();

			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setBoolean(2, sex);
			ps.setDouble(3, gross);
			
			re=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//没有结果集,就用null
			JdbcUtil.closeCRS(conn,ps,null);
		}
		return re;	
	}
	//删 
//	String sql = "delete from Employee where Name='" + name + "'";
//	String sql = "delete from Employee where Name=?";
	public static int delete(String sql,Object o) {//
		Connection conn=null;
		PreparedStatement ps=null;
		int re=0;

	    try {
	    	conn = JdbcUtil.getconn();
			ps = conn.prepareStatement(sql);
			ps.setObject(1, o);//修改
	        re = ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally{
			//没有结果集,就用null
			JdbcUtil.closeCRS(conn,ps,null);
		}
	    return re;	    	 				
	}
	//改
//	String sql ="update Employee set Age='" + student.getAge() + "' where Name='" + student.getName() + "'";
//	String sql ="update Employee set Name=? where Gross=?";
	public static int update(String sql,Object o1,Object o2) {//
		Connection conn=null;
		PreparedStatement ps=null;
		int re=0;

	    try {
	    	conn = JdbcUtil.getconn();
			ps = conn.prepareStatement(sql);
			ps.setObject(1, o1);//修改
			ps.setObject(2, o2);
	        re = ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally{
			//没有结果集,就用null
			JdbcUtil.closeCRS(conn,ps,null);
		}
	    return re;	    	 				
	}
	
	//查
	//String sql="select * from Employee";
	public static  ArrayList<Employee> select(String sql) {
		ArrayList<Employee> employees=new ArrayList<>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn = JdbcUtil.getconn();
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			//结果集解析
			employees=AnalystResultSet(rs);
//			System.out.println("select:"+employees);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			//没有结果集,就用null
			JdbcUtil.closeCRS(conn,ps,rs);
		}
		return employees;				
	}
	//connection PreparedStatement分离
	//PreparedStatement 不会被注入------------------------------
	//name="张三',true,0);delete from Employee where id=1; ";	
	//String sql="select * from Employee where id=?";
	public static  ArrayList<Employee> selectById(String sql,int id) {
		ArrayList<Employee> employees=new ArrayList<>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn = JdbcUtil.getconn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);//从1开始计数			
			rs=ps.executeQuery();
			//结果集解析
			employees=AnalystResultSet(rs);
//			System.out.println("selectById:"+employees);			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			//没有结果集,就用null
			JdbcUtil.closeCRS(conn,ps,rs);
		}
		return employees;
	}
	
	
//------------结果集解析-------------------------------------------
//	//name="张三',1,0);delete from table where id=1";
	private static ArrayList<Employee> AnalystResultSet(ResultSet rs) {
		ArrayList<Employee> employees=new ArrayList<>();	
		
		try {
			while(rs.next()) {
			int id=rs.getInt("id");
			String name=rs.getString("name");
			boolean sex=rs.getBoolean("sex");
			double gross=rs.getDouble(4);
			employees.add(new Employee(id,name,sex,gross));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}

}
