<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>
	  Web Uploader
	</title>
	<!--引入CSS-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugin/webuploader/css/webuploader.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugin/webuploader/style.css">
</head>

<body>
	<div id="wrapper">
        <div id="container">
            <!--头部，相册选择和格式选择-->

            <div id="uploader">
                <div class="queueList">
                    <div id="dndArea" class="placeholder">
                        <div id="filePicker"></div>
                        <p>或将照片拖到这里，单次最多可选300张</p>
                    </div>
                </div>
                <div class="statusBar" style="display:none;">
                    <div class="progress">
                        <span class="text">0%</span>
                        <span class="percentage"></span>
                    </div><div class="info"></div>
                    <div class="btns">
                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/plugin/webuploader/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/plugin/webuploader/dist/webuploader.js"></script>
    <script type="text/javascript">
	var upload_config = {
			ctx : "${pageContext.request.contextPath}",
			baseUrl: "${baseUrl}",
			pre : "upload_",
			upload_action : "${pageContext.request.contextPath}/ajaxUpload",
			remove_upload_action : "${pageContext.request.contextPath}/do/certificate/remove",
			preview_action : "${pageContext.request.contextPath}/do/preview",
// 			刚开始加载的数据
// 			ready_action : "${pageContext.request.contextPath}/do/certificate/queryCertificate",
// 			ready_action_param : {businessId:'${businessId}'},
			
			
			relation_id : "${businessId}",
			relation_type : "credit_order",
			upload_success : function() {
				
			}
		}
    
    
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/plugin/webuploader/upload.js"></script>
</html>