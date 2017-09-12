<html>  
  <head> 			
        <title>freemarker测试</title>    <#-- 注释 -->  	
    </head>  
    <body>  
    	<#-- 插值由${}作为占位符。 -->
        <h1>${message},${name}</h1> 
        <#assign s="hello ${name}">
        ${s} 
        <#assign b = "Hello " + name + "!">
        ${b}
        
        <#--宏是有一个变量名的模板片段。可以在模板中使用宏作为自定义指令， 这样就能进行重复性的工作。 --> 
        <#macro greet>
		<font size="+2">Hello Joe!</font>
		</#macro>
		<@greet/>
		
		<#-- 带有参数的宏 -->
		<#macro greet2 person>
		  <font size="+2">Hello ${person}!</font>
		</#macro>
		<@greet2 person="测试 freemarker"/>
		
		<#-- 嵌套内容  <#nested> 指令执行位于开始和结束标记指令之间的模板代码段 -->
		<#macro border>
		  <table border=4 cellspacing=0 cellpadding=4><tr><td>
		    <#nested>
		  </tr></td></table>
		</#macro>		
		<@border>The bordered text</@border>
    </body>  
</html>
