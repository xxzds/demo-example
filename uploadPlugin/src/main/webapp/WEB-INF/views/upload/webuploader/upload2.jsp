<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<!--dom结构部分-->
	<div id="uploader-demo">
	    <!--用来存放item-->
	    <div id="fileList" class="uploader-list"></div>
	    <div id="filePicker">选择图片</div>
	</div>
</body>

<script type="text/javascript">
   var $list=$("#fileList");
   var thumbnailWidth = 100;
   var thumbnailHeight = 100;
   
   var uploader = WebUploader.create({
    
		// 选完文件后，是否自动上传。
	    auto: true,
	    // swf文件路径
	    swf: '${pageContext.request.contextPath}/static/webuploader-0.1.5/Uploader.swf',
	
	    // 文件接收服务端。
	    server: '${pageContext.request.contextPath}/ajaxUpload',
	
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#filePicker',
	
	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false,
		
	    // 只允许选择图片文件。
// 	    accept: {
// 	        title: 'Images',
// 	        extensions: 'gif,jpg,jpeg,bmp,png',
// 	        mimeTypes: 'image/*'
// 	    }
	});

/// 当有文件添加进来的时候
uploader.on( 'fileQueued', function( file ) {
    var $li = $(
            '<div id="' + file.id + '" class="file-item thumbnail">' +
                '<img>' +
                '<div class="info">' + file.name + '</div>' +
            '</div>'
            ),
        $img = $li.find('img');


    // $list为容器jQuery实例
    $list.append( $li );

    // 创建缩略图
    // 如果为非图片文件，可以不用调用此方法。
    // thumbnailWidth x thumbnailHeight 为 100 x 100
    uploader.makeThumb( file, function( error, src ) {
        if ( error ) {
            $img.replaceWith('<span>不能预览</span>');
            return;
        }

        $img.attr( 'src', src );
    }, thumbnailWidth, thumbnailHeight );
});

//文件上传过程中创建进度条实时显示。
uploader.on( 'uploadProgress', function( file, percentage ) {
    var $li = $( '#'+file.id ),
        $percent = $li.find('.progress span');

    // 避免重复创建
    if ( !$percent.length ) {
        $percent = $('<p class="progress"><span></span></p>')
                .appendTo( $li )
                .find('span');
    }

    $percent.css( 'width', percentage * 100 + '%' );
});

// 文件上传成功，给item添加成功class, 用样式标记上传成功。
uploader.on( 'uploadSuccess', function( file ) {
    $( '#'+file.id ).addClass('upload-state-done');
});

// 文件上传失败，显示上传出错。
uploader.on( 'uploadError', function( file ) {
    var $li = $( '#'+file.id ),
        $error = $li.find('div.error');

    // 避免重复创建
    if ( !$error.length ) {
        $error = $('<div class="error"></div>').appendTo( $li );
    }

    $error.text('上传失败');
});

// 完成上传完了，成功或者失败，先删除进度条。
uploader.on( 'uploadComplete', function( file ) {
    $( '#'+file.id ).find('.progress').remove();
});
</script>
</html>