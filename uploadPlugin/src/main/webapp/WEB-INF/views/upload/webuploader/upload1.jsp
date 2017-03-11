<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 设置页面不缓存 -->
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">  
	<title>
	  Web Uploader
	</title>
	<!--引入CSS-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugin/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugin/webuploader-0.1.5/webuploader.css">

<!-- 	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> -->
	
	<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
	<!--引入JS-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/plugin/webuploader-0.1.5/webuploader.js"></script>	
</head>

<body>
	<div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
    <div id="thelist" class="uploader-list"></div>
    <div class="btns">
        <div id="picker">选择文件</div>
        <button id="ctlBtn" class="btn btn-default">开始上传</button>
    </div>
</div>
</body>

<script type="text/javascript">
   var $list=$("#thelist");   
   var $btn =$("#ctlBtn");     
   
	var uploader = WebUploader.create({
    
    // swf文件路径
    swf: '${pageContext.request.contextPath}/static/webuploader-0.1.5/Uploader.swf',

    // 文件接收服务端。
    server: '${pageContext.request.contextPath}/ajaxUpload',

    // 选择文件的按钮。可选。
    // 内部根据当前运行时创建，可能是input元素，也可能是flash.
    pick: '#picker',

    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
    resize: false,
	
	method:'POST'
});

// 当有文件被添加进队列的时候
uploader.on( 'fileQueued', function( file ) {
    $list.append( '<div id="' + file.id + '" class="item">' +
        '<h4 class="info">' + file.name + '</h4>' +
        '<p class="state">等待上传...</p>' +
    '</div>' );
});

// 文件上传过程中创建进度条实时显示。
uploader.on( 'uploadProgress', function( file, percentage ) {
    var $li = $( '#'+file.id ),
        $percent = $li.find('.progress .progress-bar');

    // 避免重复创建
    if ( !$percent.length ) {
        $percent = $('<div class="progress progress-striped active">' +
          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
          '</div>' +
        '</div>').appendTo( $li ).find('.progress-bar');
    }

    $li.find('p.state').text('上传中');

    $percent.css( 'width', percentage * 100 + '%' );
});

//文件成功、失败处理
uploader.on( 'uploadSuccess', function( file ) {
    $( '#'+file.id ).find('p.state').text('已上传');
});

uploader.on( 'uploadError', function( file ) {
    $( '#'+file.id ).find('p.state').text('上传出错');
});

uploader.on( 'uploadComplete', function( file ) {
    $( '#'+file.id ).find('.progress').fadeOut();
});

$btn.on( 'click', function() {  
        console.log("上传...");  
        uploader.upload();  
        console.log("上传成功");  
      });  
</script>
</html>