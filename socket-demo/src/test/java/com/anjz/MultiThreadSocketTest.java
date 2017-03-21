package com.anjz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * 多线程socket通信测试
 * @author shuai.ding
 *
 * @date 2017年3月21日上午9:31:24
 */
public class MultiThreadSocketTest {

	/**
	 * 服务端，每个socket连接对应一个线程
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	@Test
	public void serverTest() throws IOException{
		  ServerSocket server = new ServerSocket(5678);  
	        while (true) {  
	            // transfer location change Single User or Multi User  	  
	            MultiClient mc = new MultiClient(server.accept());      //无连接，阻塞态
	            mc.start();  
	        } 
	}
	
	
	@Test
    public void clientTest() throws UnknownHostException, IOException{
	   //1、创建客户端Socket，指定服务器地址和端口
		Socket socket =new Socket("127.0.0.1",5678);
		//2、获取输出流，向服务器端发送信息
		OutputStream os = socket.getOutputStream();//字节输出流
		PrintWriter pw =new PrintWriter(os);//将输出流包装成打印流
		pw.write("用户名：admin；密码：admin");
		pw.flush();
		socket.shutdownOutput();
		//3、获取输入流，并读取服务器端的响应信息
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String info = null;
		while((info=br.readLine())!=null){
		 System.out.println("Hello,我是客户端，服务器说："+info);
		}
		  
		//4、关闭资源
		br.close();
		is.close();
		pw.close();
		os.close();
		socket.close();
	   
	   
	   
	   
   }
	
	
	public class MultiClient extends Thread {  
	    private Socket client;  
	  
	    public MultiClient(Socket c) {  
	        this.client = c;  
	    }  
	  
	    public void run() {  
	        try {  
	            BufferedReader in = new BufferedReader(new InputStreamReader(  
	                    client.getInputStream()));  
	            PrintWriter out = new PrintWriter(client.getOutputStream());  
	            // Mutil User but can't parallel  
	  
	            while (true) {  
	                String str = in.readLine();
	                if(str!=null){
	                	 System.out.println("hello,我是服务器，客户端说："+str);  
	 	                out.println("has receive....");  
	 	                out.flush();  
	 	                if ("end".equals(str))  
	 	                    break; 
	                }
//	                System.out.println("当前线程："+Thread.currentThread().getName());
	            }  
	            client.close();   
	        } catch (IOException ex) {  
	        } finally {  
	        }  
	    }  
	  
	   
	}  
}
