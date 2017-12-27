import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpUtil {
/*
	1.获取url地址
	2.获取连接
	3.写入请求头：设置请求方法，设置请求的接受的参数，编码方式
	4.建立连接
		写入内容----post请求特有
	5.读取返回值
		获取输入流并放入reader中
		创建BufferedReader读取数据
 */
/*
 	post：
	6.发送数据
	7.接收数据
 */
	public static void get(String url) throws IOException {
		URL realurl=new URL(url);
		HttpURLConnection conn=(HttpURLConnection) realurl.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "*/*");
		conn.setRequestProperty("content-type", "charset=utf-8");		
//		conn.setDoInput(true);//默认为true，有没有输入流   不用管
//		conn.setDoOutput(true);//默认为false，post请求需要往外写数据   用
		conn.connect();
		
		InputStreamReader input=new InputStreamReader(conn.getInputStream(),"utf-8");//防止中文乱码
		BufferedReader in=new BufferedReader(input);
		
		StringBuffer result=new StringBuffer();
		String line="";
		while ((line = in.readLine()) != null) {  			
			result.append(line);
		}  
		System.out.println(result.toString());
	}
	
	public static void post(String url,String content) throws IOException {
		URL realurl=new URL(url);
		HttpURLConnection conn=(HttpURLConnection) realurl.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "*/*");
		//post
		conn.setRequestProperty("authorization","9FI1s6FU5q8oFR0O2ocCi59rSURhPTEwMDA5NjMzJms9QUtJRGpYQmR1ek9hNlF1SlpmNUpxQk5PSzdqOVBhZFhqbDhKJmU9MTUxNDAzMTQzNSZ0PTE1MTM5NDUwMzUmcj0xMjE3OTI0NDQxJnU9MzI1NTIwNTg3Mg==");
		conn.setRequestProperty("content-type", "charset=utf-8");		
//		conn.setDoInput(true);//默认为true，有没有输入流   不用管
		conn.setDoOutput(true);//默认为false，post请求需要往外写数据   用
		//写入内容----post请求特有
		conn.connect();
		
		//6.post：发送数据
		DataOutputStream out=new DataOutputStream(conn.getOutputStream());
		out.write(content.getBytes("utf8"));
		out.flush();
		out.close();
		
		//7.接收数据
		InputStreamReader input=new InputStreamReader(conn.getInputStream(),"utf-8");//防止中文乱码
		BufferedReader in=new BufferedReader(input);
		
		StringBuffer result=new StringBuffer();
		String line="";
		while ((line = in.readLine()) != null) {  			
			result.append(line);
		}  
		System.out.println(result.toString());		
	}
}
