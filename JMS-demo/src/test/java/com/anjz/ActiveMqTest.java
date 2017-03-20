package com.anjz;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;

/**
 * 测试
 * http://120.25.210.34:8161/admin/topics.jsp
 * @author shuai.ding
 * @date 2017年3月20日下午3:59:59
 */
public class ActiveMqTest {

	/**
	 * 生产
	 */
	@Test
	public void pruducterText(){
		 //连接broker，就是我们刚刚防火墙里面打开的那个端口  
        ConnectionFactory cf=  new ActiveMQConnectionFactory("tcp://120.25.210.34:61616");  
          //创建了一个队列名称为 "user.queue"  
//        Destination  destination=new ActiveMQQueue("user.queuer"); 
        
        //创建一个topic
        Destination  destination =new ActiveMQTopic("test.topic");
        Connection conn=null;  
         
        try {  
          //从连接工程里面获取一个新连接  
          conn=cf.createConnection();  
          //自动确认消息的发送  
          Session session=conn.createSession(false, Session.AUTO_ACKNOWLEDGE);  
          //创建一个消息生产者，并且配置发送消息的地址  
          MessageProducer producer=session.createProducer(destination);  
          //创建一个map类型的消息  
          MapMessage message=session.createMapMessage();  
          message.setString("userId", "123456");  
          message.setString("userName", "李四");  
          message.setInt("age", 18);  
          //发送  
          producer.send(message);  
          //关闭  
          session.close();  
      } catch (Exception e) {  
          throw new RuntimeException(e);  
      }finally{  
          if(conn!=null){  
              try {  
                  conn.close();  
              } catch (Exception e2) {  
                  // TODO: handle exception  
              }  
          }  
      }  
	}
	
	/**
	 * 消费
	 */
	@Test
	public void consumerTest(){
		//连接broker，就是我们刚刚防火墙里面打开的那个端口  
        ConnectionFactory cf=  new ActiveMQConnectionFactory("tcp://120.25.210.34:61616");  
        //创建了一个队列名称为 "user.queue"  
//        Destination  destination=new ActiveMQQueue("user.queuer");  
        
        //创建一个topic
        Destination  destination =new ActiveMQTopic("test.topic");
        
        Connection conn=null;  
         
         
        try {  
          //从连接工程里面获取一个新连接  
          conn=cf.createConnection();  
          //自动确认消息的接受  
          Session session=conn.createSession(false, Session.AUTO_ACKNOWLEDGE);  
          //创建一个消费者  
          MessageConsumer consumer=session.createConsumer(destination);  
          conn.start();  
          //同步阻塞等待接受消息  
          MapMessage message=(MapMessage)consumer.receive();  
           
          
          System.out.println(message.getInt("age") +" "+ message.getString("userId")+" "+message.getString("userName"));  
          session.close();  
      } catch (Exception e) {  
          throw new RuntimeException(e);  
      }finally{  
          if(conn!=null){  
              try {  
                  conn.close();  
              } catch (Exception e2) {  
                  // TODO: handle exception  
              }  
          }  
      }  
	}
}
