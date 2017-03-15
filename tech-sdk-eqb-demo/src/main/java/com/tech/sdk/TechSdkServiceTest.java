package com.tech.sdk;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.tech.service.TechSdkService;
import com.tech.service.pdf.PdfExportService;
import com.tech.util.HttpUrlConnectionUtil;
import com.tech.util.PropertiesUtil;
import com.tech.util.sftp.SFTPChannel;
import com.tech.util.sftp.SftpUtil;
import com.timevale.esign.sdk.tech.bean.PersonBean;
import com.timevale.esign.sdk.tech.bean.PosBean;
import com.timevale.esign.sdk.tech.bean.SignPDFStreamBean;
import com.timevale.esign.sdk.tech.bean.result.AddAccountResult;
import com.timevale.esign.sdk.tech.bean.result.AddSealResult;
import com.timevale.esign.sdk.tech.bean.result.FileDigestSignResult;
import com.timevale.esign.sdk.tech.bean.result.GetSignedFileResult;
import com.timevale.esign.sdk.tech.bean.seal.PersonTemplateType;
import com.timevale.esign.sdk.tech.bean.seal.SealColor;
import com.timevale.esign.sdk.tech.impl.constants.SignType;

import net.sf.json.JSONObject;

/**
 * 
 * @author shuai.ding
 *
 * @date 2017年3月15日下午2:30:31
 */
public class TechSdkServiceTest extends BaseTest{
	
	@Resource
	private TechSdkService techSdkService;
	
	@Resource
	private PdfExportService pdfExportService;

	/**
	 * pdf盖章服务
	 * @throws IOException
	 * @throws SftpException 
	 * 
	 */
	@Test
	public void signTest() throws IOException, SftpException{
		//1.初始化参数
		techSdkService.init();
		
		//2.创建账户
		PersonBean psn = new PersonBean();
		//邮箱地址
		String mobile="18256915945";
		//手机号码
		String email="";
		//姓名
		String name = "丁帅";
		//身份证号/护照号
		String idno="341224199105103332";
		//个人归属地：0-大陆，1-香港，2-澳门，3-台湾，4-外籍，默认0
		Integer area = 0;
				
		psn.setMobile(mobile).setEmail(email).setName(name).setIdNo(idno)
        .setPersonArea(area);
		AddAccountResult addAccountResult = techSdkService.createPersonAccount(psn);
		
		//3.创建印章
		//账户ID
		String accountId=addAccountResult.getAccountId();
		//模板类型
		PersonTemplateType personTemplateType=PersonTemplateType.SQUARE;
		//生成印章的颜色
		SealColor sealColor=SealColor.RED;
		AddSealResult addSealResult = techSdkService.createPersonSeal(accountId, personTemplateType, sealColor);
		
		
		//4.签署文件
		String sealData=addSealResult.getSealData();
		//签署PDF文档信息
		SignPDFStreamBean signPDFStreamBean = new SignPDFStreamBean();
		
		SFTPChannel sftpChannel = new SFTPChannel();
		PropertiesUtil propertiesUtil = PropertiesUtil.getInstance("config.properties");
		String host = propertiesUtil.getValue("sftp.host");
		String port = propertiesUtil.getValue("sftp.port");
		String username = propertiesUtil.getValue("sftp.username");
		String password = propertiesUtil.getValue("sftp.password");

		ChannelSftp channelSftp = sftpChannel.getChannel(host, Integer.valueOf(port), username, password);
		
		String distDir=propertiesUtil.getValue("sftp.directory")+"attach/";
		SftpUtil.createDir(distDir,channelSftp);
		String fileName = "admin"+".pdf";
		OutputStream out = channelSftp.put(distDir+fileName, ChannelSftp.OVERWRITE); // 使用OVERWRITE模式
		pdfExportService.exportProtocolPdf(out);
		
		byte[] stream = HttpUrlConnectionUtil.doGet(propertiesUtil.getValue("sftp.httpBaseUrl")+"attach/"+fileName, null, 5000);
		logger.info(stream.toString());
		
				
		signPDFStreamBean.setStream(stream);
		
		//签章位置信息
        PosBean pos = new PosBean();
        //定位类型，0-坐标定位，1-关键字定位，默认0，若选择关键字定位，签署类型(signType)必须指定为关键字签署才会生效
        pos.setPosType(0);
        pos.setPosX(400);
        pos.setPosY(450);
        //签署页码，若为多页签章，支持页码格式“1-3,5,8“，若为坐标定位时，不可空
        pos.setPosPage("4");
        //关键字，仅限关键字签章时有效，若为关键字定位时，不可空
        pos.setKey("");
        //印章展现宽度，将以此宽度对印章图片做同比缩放。
        pos.setWidth(100);
        
        //签章类型
        SignType signType = SignType.Single;
        
        FileDigestSignResult userResult = techSdkService.userSignByStream(accountId, sealData, pos, signPDFStreamBean, signType);
		
        //5.平台签署
        
        signPDFStreamBean.setStream(userResult.getStream());
        
        //签署印章的标识，为0表示用默认印章签署
        Integer sealId = 0;
        
        //位置
        pos.setPosX(200);
        pos.setPosY(450);
        FileDigestSignResult selfResult = techSdkService.selfSignBySteam(signPDFStreamBean, pos, sealId, signType);
		
		//6.文档保全
        //文档对应的所有签署记录集合
      	List<String> signServiceIds = new ArrayList<String>();
      	signServiceIds.add(userResult.getSignServiceId());
      	signServiceIds.add(selfResult.getSignServiceId());
      	
      	String docName=fileName;
      	GetSignedFileResult result = techSdkService.saveByStream(selfResult.getStream(), signServiceIds, docName);
      	
      	logger.info(JSONObject.fromObject(result).toString());
	}
}
