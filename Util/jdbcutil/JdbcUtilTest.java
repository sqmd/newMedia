import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import other.Database.teachedDemo.Employee;

public class JdbcUtilTest {
	public static void main(String[] args) throws FileNotFoundException {
		String createTable="Create table Employee(\r\n" + 
				"id int not null auto_increment,\r\n" + 
				"name char(20) not null,\r\n" + 
				"sex char(4) not null,\r\n" + 
				"gross double not null,\r\n" + 
				"primary key(id)\r\n" + 
				");";
		JdbcUtilTemplate.myExecute(createTable);


		JdbcUtilTemplate.insert("张三", true, 1.0);
		JdbcUtilTemplate.insert("李四", false, 1.5);
		JdbcUtilTemplate.insert("王五", true, 2.2);
		
		ArrayList<Employee> ems=null;
		ems=JdbcUtilTemplate.select("select * from Employee");
		System.out.println(ems);
		ems=JdbcUtilTemplate.selectById("select * from Employee where id=?", 1);	
		System.out.println(ems);
		System.out.println("--------------------");
		
		JdbcUtilTemplate.delete("delete from Employee where Name=?","张三");
		JdbcUtilTemplate.update("update Employee set Name=? where Gross=?","lisi",1.5);
		ems=JdbcUtilTemplate.select("select * from Employee");
		System.out.println(ems);
		System.out.println("--------------------");

		String deleteTable="drop table employee;";
		JdbcUtilTemplate.myExecute(deleteTable);		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		File file=new File("databaseOperation.txt");
//		PrintWriter out=new PrintWriter(file);
//		out.print(createTable);
//		out.close();
	}
}
