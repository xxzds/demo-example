package com.anjz.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传控制器
 * @author shuai.ding
 * @date 2017年3月6日下午3:24:12
 */
@Controller
public class UploadController {
	
	private static final Logger logger =LoggerFactory.getLogger(UploadController.class);

	@RequestMapping("toUploadPage{index}")
	public String toUploadPage(@PathVariable("index") String index){
		return "/upload/upload"+index;
	}
	
	
	@RequestMapping("ajaxUpload")
	@ResponseBody
	public String ajaxUpload(MultipartFile file){
		
		if(file!=null){
			logger.info("originalFilename:{},contentType:{},name:{},size{}",file.getOriginalFilename(),
					file.getContentType(),file.getName(),file.getSize());
		}

		return "999";
	}
}
