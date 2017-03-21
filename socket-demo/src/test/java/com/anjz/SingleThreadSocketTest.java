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
 * 单线程socket通信测试
 * 
 * @author shuai.ding
 * @date 2017年3月21日上午9:18:39
 */
public class SingleThreadSocketTest {

	/**
	 * 1、用指定的端口实例化一个SeverSocket对象。服务器就可以用这个端口监听从客户端发来的连接请求。
     * 2、调用ServerSocket的accept()方法，以在等待连接期间造成阻塞，监听连接从端口上发来的连接请求。
     * 3、利用accept方法返回的客户端的Socket对象，进行读写IO的操作
     * 4、关闭打开的流和Socket对象
	 * @throws IOException
	 */
	@Test
	public void serverTest() throws IOException {
		// 1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
		ServerSocket serverSocket = new ServerSocket(10086);// 1024-65535的某个端口
		// 2、调用accept()方法开始监听，等待客户端的连接
		Socket socket = serverSocket.accept();
		// 3、获取输入流，并读取客户端信息
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String info = null;
		while ((info = br.readLine()) != null) {
			System.out.println("Hello,我是服务器，客户端说：" + info);
		}
		socket.shutdownInput();// 关闭输入流
		// 4、获取输出流，响应客户端的请求
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.write("Hello World！");
		pw.flush();

		// 5、关闭资源
		pw.close();
		os.close();
		br.close();
		isr.close();
		is.close();
		socket.close();
		serverSocket.close();
	}
	
	
	/**
	 *1、用服务器的IP地址和端口号实例化Socket对象。
     *2、调用connect方法，连接到服务器上。
     *3、获得Socket上的流，把流封装进BufferedReader/PrintWriter的实例，以进行读写
     *4、利用Socket提供的getInputStream和getOutputStream方法，通过IO流对象，向服务器发送数据流
     *5、关闭打开的流和Socket。
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@Test
	public void clientTest() throws UnknownHostException, IOException{
		//1、创建客户端Socket，指定服务器地址和端口
		Socket socket =new Socket("127.0.0.1",10086);
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
}
