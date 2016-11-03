package com.anjz.test;

import javax.xml.namespace.QName;  
import org.apache.axis2.AxisFault;  
import org.apache.axis2.addressing.EndpointReference;  
import org.apache.axis2.client.Options;  
import org.apache.axis2.rpc.client.RPCServiceClient; 

public class CalculateClient {

	public static void main(String[] args){  
        
        String url="http://localhost:9999/myaxis/services/AxisService?wsdl";  
        String method="sum";  
        //返回值  
          
        RPCServiceClient serviceClient;  
        try {  
            serviceClient = new RPCServiceClient();  
            Options options = serviceClient.getOptions();  
            EndpointReference targetEPR = new EndpointReference(url);  
            options.setTo(targetEPR);  
            // 在创建QName对象时，QName类的构造方法的第一个参数表示WSDL文件的命名空间名，也就是<wsdl:definitions>元素的targetNamespace属性值  
             QName opAddEntry = new QName("http://service.Axis2Service",method);   
              
             Object[] opAddEntryArgs = new Object[] {"1","5"};  
             Class[] classes = new Class[] {String.class };  
            // 返回参数类型，这个和axis1有点区别  
             // invokeBlocking方法有三个参数，其中第一个参数的类型是QName对象，表示要调用的方法名；  
             // 第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；  
             // 第三个参数表示WebService方法的返回值类型的Class对象，参数类型为Class[]。  
             // 当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}  
             // 如果被调用的WebService方法没有返回值，应使用RPCServiceClient类的invokeRobust方法，  
             // 该方法只有两个参数，它们的含义与invokeBlocking方法的前两个参数的含义相同  
             Object[] result=serviceClient.invokeBlocking(opAddEntry,opAddEntryArgs, classes);  
             System.out.println(result[0].toString());  
               
        } catch (AxisFault e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
    }  
      
}
