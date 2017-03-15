package com.tech.sdk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tech.util.FileUtil;
import com.timevale.esign.sdk.tech.bean.OrganizeBean;
import com.timevale.esign.sdk.tech.bean.PersonBean;
import com.timevale.esign.sdk.tech.bean.PosBean;
import com.timevale.esign.sdk.tech.bean.SignPDFFileBean;
import com.timevale.esign.sdk.tech.bean.SignPDFStreamBean;
import com.timevale.esign.sdk.tech.bean.result.AddAccountResult;
import com.timevale.esign.sdk.tech.bean.result.AddSealResult;
import com.timevale.esign.sdk.tech.bean.result.FileDigestSignResult;
import com.timevale.esign.sdk.tech.bean.result.GetSignedFileResult;
import com.timevale.esign.sdk.tech.bean.result.Result;
import com.timevale.esign.sdk.tech.bean.result.SignDataResult;
import com.timevale.esign.sdk.tech.bean.result.SignedFileResult;
import com.timevale.esign.sdk.tech.bean.seal.PersonTemplateType;
import com.timevale.esign.sdk.tech.bean.seal.SealColor;
import com.timevale.esign.sdk.tech.impl.constants.OrganRegType;
import com.timevale.esign.sdk.tech.impl.constants.SignType;
import com.timevale.esign.sdk.tech.service.AccountService;
import com.timevale.esign.sdk.tech.service.EsignsdkService;
import com.timevale.esign.sdk.tech.service.SealService;
import com.timevale.esign.sdk.tech.service.SelfSignService;
import com.timevale.esign.sdk.tech.service.SignService;
import com.timevale.esign.sdk.tech.service.UserSignService;
import com.timevale.esign.sdk.tech.service.factory.AccountServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.EsignsdkServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.SealServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.SelfSignServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.SignServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.UserSignServiceFactory;
import com.timevale.tech.sdk.bean.HttpConnectionConfig;
import com.timevale.tech.sdk.bean.ProjectConfig;
import com.timevale.tech.sdk.bean.SignatureConfig;

import net.sf.json.JSONObject;

/**
 * e签宝 接口测试
 * @author shuai.ding
 *
 * @date 2017年3月13日下午4:25:09
 */
public class TechSdkTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TechSdkTest.class);
	
	/**
	 * 初始化
	 */
	@Before
	public void initTest(){
		EsignsdkService SDK = EsignsdkServiceFactory.instance();
		ProjectConfig projectConfig = new ProjectConfig();
		projectConfig.setProjectId("1111563517");
		projectConfig.setProjectSecret("95439b0863c241c63a861b87d1e647b7");
		projectConfig.setItsmApiUrl("http://121.40.164.61:8080/tgmonitor/rest/app!getAPIInfo2");
		
		
		HttpConnectionConfig httpConfig = new HttpConnectionConfig();
		SignatureConfig signConfig = new SignatureConfig();
		Result result = SDK.init(projectConfig, httpConfig, signConfig);
		logger.info(JSONObject.fromObject(result).toString());		
	}
	
	/**
	 * 创建个人账户
	 */
	@Test
	public void createPersonAccount(){
		AccountService SERVICE = AccountServiceFactory.instance();
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
		AddAccountResult r = SERVICE.addAccount(psn);
		logger.info(JSONObject.fromObject(r).toString());	
	}
	
	/**
	 * 创建企业账户
	 */
	@Test
	public void createOrganizeAccount(){
		AccountService SERVICE = AccountServiceFactory.instance();
		OrganizeBean org = new OrganizeBean();
		
		String mobile="";
		String email ="";
		//机构名称
		String name ="";
		//单位类型，0-普通企业，1-社会团体，2-事业单位，3-民办非企业单位，4-党政及国家机构，默认0
		Integer organType=0;
		
		//企业注册类型，含组织机构代码号、多证合一或工商注册码，默认组织机构代码号
		OrganRegType organRegType=OrganRegType.NORMAL;
		
		//组织机构代码号、社会信用代码号或工商注册号
		String organCode ="";
		
		//注册类型，1-代理人注册，2-法人注册，默认1
		Integer userType=1;
		
		//代理人姓名，当注册类型为1时必填
		String agentName="";
		
		//代理人身份证号，当注册类型为1时必填
		String agentIdNo="";
		
		//法定代表姓名，当注册类型为2时必填
		String legalName="";
		
		//法定代表身份证号/护照号，当注册类型为2时必填
		String legalIdNo="";
		
		//法定代表人归属地,0-大陆，1-香港，2-澳门，3-台湾，4-外籍，默认0
		Integer legalArea=0;
			
        org.setMobile(mobile).setEmail(email).setName(name)
                .setOrganType(organType).setRegType(organRegType)
                .setOrganCode(organCode).setUserType(userType)
                .setAgentName(agentName).setAgentIdNo(agentIdNo)
                .setLegalName(legalName).setLegalIdNo(legalIdNo)
                .setLegalArea(legalArea);
		AddAccountResult r = SERVICE.addAccount(org);
		logger.info(JSONObject.fromObject(r).toString());	
	}
	
	/**
	 * 创建个人模板印章
	 */
	@Test
	public void createPersonSeal(){
		SealService SEAL = SealServiceFactory.instance();
		//账户ID
		String accountId="9ED8B4D7D289484299E8FDA3BFD3FFE8";
		//模板类型
		PersonTemplateType personTemplateType=PersonTemplateType.SQUARE;
		//生成印章的颜色
		SealColor sealColor=SealColor.RED;
		
		AddSealResult addSealResult = SEAL.addTemplateSeal(accountId, personTemplateType, sealColor);
		logger.info(JSONObject.fromObject(addSealResult).toString());	
		logger.info("印章图片Base64:"+addSealResult.getSealData());
	}
	
	/**
	 * 平台用户PDF摘要签署
	 */
	@Test
	public void userSignServiceTest(){
		UserSignService userSign = UserSignServiceFactory.instance();
		
		//签署者账号标识，以此获取账户的证书进行签署
		String accountId = "A2F9C63FAD74412199F7EE4F8A2CFB63";
		
		//印章图片Base64，若为空最终签署后将没有直观图片展现
		String sealData="iVBORw0KGgoAAAANSUhEUgAAAP8AAAD/CAMAAAAJ1vD4AAAADFBMVEX/////AAAAAP8AAABvxgj3AAAAAXRSTlMAQObYZgAABQtJREFUeNrtnYly3DgMRKe1/P9f7iS7ro3HnhEvkCaAZlVcKR8SHxoAjwEl4JG6XQ/xiz9vK3++MCc7pL/4xS9+8Ytf/OIXv/jFL37x51r/jq4emVh/fPzLys9HCANMxj/y8v8X/DjdApUOTuhPDy6AigUuk1ucjH/fwxl+Hm8AVHuYZP6DJfynOwDqBvg3OXD6DjTtMFeIztdTOBv+NV22bXx1s8v4mlnXf1ik0bnrv0VO2h6PJrnnHxgJv38MgMUFtP+Tu5XVHsok+vNNgCKt/8PD+vgy0p3v0zP8xj+GJyZeNsZKfTbSxHP3c9Ct/9MyE6Qb/xyskWr8vyOAU3rTc/yP9d8Rvub/a9dlzMfvCt+e3xf+0vhnwviHL3xrfm/4xvzu8DX+55bflN8hviW/R/w18c+M8Q+P+Hb8PvEXfP7r6yPxKze+Eb9bfOv8764e5DKV3185zJUb37T+1WMx1JUbf57fN/40v3N8s/MfXgshr9z4c/z+8af4A+DP8EfAn+APgT9e/4EI9OP6B8Ef5Y+CP8gfBn+MPw7+UP5DGPoR/kDij/h/LPxu/RGKvlv/aPh9+gfz/V7+cOJ38QcUv4c/ovjt/DHFb+XHp/8xHf//no+AFigdnh/RApX5D54Dn1++G5wf3/b4+Ahmgasp7f11eAazwNUg/nO88xHJAqWq/bf2fCiUMfl5U83K1w8WYqsd6YD/yQBtP2wLB/rQ/xMjX1KMBf8EPSzu35P/7/tK7sV/m5zn2u3zb1DrMzbj22YT1Pib+LBXfcNBt85v2jUuuQ/P4d/XbOqN/T7/iWY+5fT8G8fHoO79j7M9IKf+u/Y/xC9+8Ytf/OIXv/jFL37xi1/84he/+MUvfvGLX/ziF7/4xS9+8Ytf/OIXv/jFL/5tbaQodqb+CRurudvu1d+jMtOjfa+2wioTXFP4u19t1ebhPXFwzXYEu2PctiZ77vk3P2CApsal/F8kwFaglprXjgRQprT/+A7rWcdqrKCp+v364+3JJ9ROkhxZaV+6VMOtod8Phvj4Sl/8eIJC1cveGQB1C52p/6c+oyXE0HixDYndLv+BZiduzvOAW37im6q8/U2zue62lUWxdT5WwoIdVuBJ/DadYZ8f0MqbODn+cyc+m9IpelY58Ln/wfl1Xm2ZevnFbxpva7/p9fzbHRY6Bt7Ls/zzpkq//1u8A/C7wnylOoPyT8aSPv8Qv/jFL36N/93zKSbW/9gd7T38aFt/RY9/JOVHFANMf/6LhPyIEwJlPsyR8/1fWz0AJ/MzvP+He+y95v/iF7/4xS9+8Ytf/C4apb/4E/JD+otf/OIXv3GbrP+pviHl8OnPDP/qbd+3J08O4MdaVY72/zDsI/x4uKKHKf8eeGxMfwPvf96lPNtFnEjMZeSS3Ah/RPz3af81Qe5+Sxo7BCv2yqyqDmDfL8M4/pv7QFvs0Wux6dRd6bkzJ1xwUVCzwQAcXP98PfDc2X9yPSkb/oKD/j+f82kxV8RcONIm/jkqGoy8nwNdarjl8voPLjrObHTBpve/zo/Icy8pXTghKsulO3uRVA7v/mL5q/t/7OpmsPX/z7vuavkP3/9dju9i/3ulG14O5H8k5V/v/Ufz78A/mH8L/vH5j1njv3/ujUj8ux6CdeXGP/T5FzMfq/PH+Dfs95n/WTkRffxyDBL/Y+wD20yR6n9GvEb1X+IXv/jFL37xiz9bA6S/+MWftf0CnuQRr8v/n3AAAAAASUVORK5CYII=";		
		//签署PDF文档信息
		SignPDFFileBean fileBean = new SignPDFFileBean();
        fileBean.setSrcPdfFile("C:/Users/Administrator/Desktop/test.pdf");
        fileBean.setDstPdfFile("C:/Users/Administrator/Desktop/test-dst.pdf");
        fileBean.setFileName("test");
        
        //签章位置信息
        PosBean pos = new PosBean();
        //定位类型，0-坐标定位，1-关键字定位，默认0，若选择关键字定位，签署类型(signType)必须指定为关键字签署才会生效
        pos.setPosType(0);
        pos.setPosX(400);
        pos.setPosY(100);
        //签署页码，若为多页签章，支持页码格式“1-3,5,8“，若为坐标定位时，不可空
        pos.setPosPage("1");
        //关键字，仅限关键字签章时有效，若为关键字定位时，不可空
        pos.setKey("");
        //印章展现宽度，将以此宽度对印章图片做同比缩放。
        pos.setWidth(100);
        
        //签章类型
        SignType signType = SignType.Single;
		
		FileDigestSignResult r = userSign.localSignPDF(accountId, sealData, fileBean, pos, signType);
		logger.info(JSONObject.fromObject(r).toString());
		logger.info("签署记录id："+r.getSignServiceId());
	}
	
	
	/**
	 * 平台用户PDF摘要签署(文件流)
	 * @throws IOException
	 */
	@Test
	public void userSignServiceByStreamTest() throws IOException{
		UserSignService userSign = UserSignServiceFactory.instance();
		
		//签署者账号标识，以此获取账户的证书进行签署
		String accountId = "A2F9C63FAD74412199F7EE4F8A2CFB63";
		
		//印章图片Base64，若为空最终签署后将没有直观图片展现
		String sealData="iVBORw0KGgoAAAANSUhEUgAAAP8AAAD/CAMAAAAJ1vD4AAAADFBMVEX/////AAAAAP8AAABvxgj3AAAAAXRSTlMAQObYZgAABQtJREFUeNrtnYly3DgMRKe1/P9f7iS7ro3HnhEvkCaAZlVcKR8SHxoAjwEl4JG6XQ/xiz9vK3++MCc7pL/4xS9+8Ytf/OIXv/jFL37x51r/jq4emVh/fPzLys9HCANMxj/y8v8X/DjdApUOTuhPDy6AigUuk1ucjH/fwxl+Hm8AVHuYZP6DJfynOwDqBvg3OXD6DjTtMFeIztdTOBv+NV22bXx1s8v4mlnXf1ik0bnrv0VO2h6PJrnnHxgJv38MgMUFtP+Tu5XVHsok+vNNgCKt/8PD+vgy0p3v0zP8xj+GJyZeNsZKfTbSxHP3c9Ct/9MyE6Qb/xyskWr8vyOAU3rTc/yP9d8Rvub/a9dlzMfvCt+e3xf+0vhnwviHL3xrfm/4xvzu8DX+55bflN8hviW/R/w18c+M8Q+P+Hb8PvEXfP7r6yPxKze+Eb9bfOv8764e5DKV3185zJUb37T+1WMx1JUbf57fN/40v3N8s/MfXgshr9z4c/z+8af4A+DP8EfAn+APgT9e/4EI9OP6B8Ef5Y+CP8gfBn+MPw7+UP5DGPoR/kDij/h/LPxu/RGKvlv/aPh9+gfz/V7+cOJ38QcUv4c/ovjt/DHFb+XHp/8xHf//no+AFigdnh/RApX5D54Dn1++G5wf3/b4+Ahmgasp7f11eAazwNUg/nO88xHJAqWq/bf2fCiUMfl5U83K1w8WYqsd6YD/yQBtP2wLB/rQ/xMjX1KMBf8EPSzu35P/7/tK7sV/m5zn2u3zb1DrMzbj22YT1Pib+LBXfcNBt85v2jUuuQ/P4d/XbOqN/T7/iWY+5fT8G8fHoO79j7M9IKf+u/Y/xC9+8Ytf/OIXv/jFL37xi1/84he/+MUvfvGLX/ziF7/4xS9+8Ytf/OIXv/jFL/5tbaQodqb+CRurudvu1d+jMtOjfa+2wioTXFP4u19t1ebhPXFwzXYEu2PctiZ77vk3P2CApsal/F8kwFaglprXjgRQprT/+A7rWcdqrKCp+v364+3JJ9ROkhxZaV+6VMOtod8Phvj4Sl/8eIJC1cveGQB1C52p/6c+oyXE0HixDYndLv+BZiduzvOAW37im6q8/U2zue62lUWxdT5WwoIdVuBJ/DadYZ8f0MqbODn+cyc+m9IpelY58Ln/wfl1Xm2ZevnFbxpva7/p9fzbHRY6Bt7Ls/zzpkq//1u8A/C7wnylOoPyT8aSPv8Qv/jFL36N/93zKSbW/9gd7T38aFt/RY9/JOVHFANMf/6LhPyIEwJlPsyR8/1fWz0AJ/MzvP+He+y95v/iF7/4xS9+8Ytf/C4apb/4E/JD+otf/OIXv3GbrP+pviHl8OnPDP/qbd+3J08O4MdaVY72/zDsI/x4uKKHKf8eeGxMfwPvf96lPNtFnEjMZeSS3Ah/RPz3af81Qe5+Sxo7BCv2yqyqDmDfL8M4/pv7QFvs0Wux6dRd6bkzJ1xwUVCzwQAcXP98PfDc2X9yPSkb/oKD/j+f82kxV8RcONIm/jkqGoy8nwNdarjl8voPLjrObHTBpve/zo/Icy8pXTghKsulO3uRVA7v/mL5q/t/7OpmsPX/z7vuavkP3/9dju9i/3ulG14O5H8k5V/v/Ufz78A/mH8L/vH5j1njv3/ujUj8ux6CdeXGP/T5FzMfq/PH+Dfs95n/WTkRffxyDBL/Y+wD20yR6n9GvEb1X+IXv/jFL37xiz9bA6S/+MWftf0CnuQRr8v/n3AAAAAASUVORK5CYII=";		
		//签署PDF文档信息
		SignPDFStreamBean signPDFStreamBean = new SignPDFStreamBean();
		signPDFStreamBean.setStream(FileUtil.toByteArray("C:/Users/Administrator/Desktop/x.pdf"));
        
        //签章位置信息
        PosBean pos = new PosBean();
        //定位类型，0-坐标定位，1-关键字定位，默认0，若选择关键字定位，签署类型(signType)必须指定为关键字签署才会生效
        pos.setPosType(0);
        pos.setPosX(400);
        pos.setPosY(100);
        //签署页码，若为多页签章，支持页码格式“1-3,5,8“，若为坐标定位时，不可空
        pos.setPosPage("1");
        //关键字，仅限关键字签章时有效，若为关键字定位时，不可空
        pos.setKey("");
        //印章展现宽度，将以此宽度对印章图片做同比缩放。
        pos.setWidth(100);
        
        //签章类型
        SignType signType = SignType.Single;
		
		FileDigestSignResult r = userSign.localSignPDF(accountId, sealData, signPDFStreamBean, pos, signType);
		logger.info(JSONObject.fromObject(r).toString());
		logger.info("签署记录id："+r.getSignServiceId());
		logger.info("stream:"+r.getStream());
	}
	
	
	
	/**
	 * 平台自身PDF摘要签署
	 */
	@Test
	public void selfSignServiceTest() {
		SelfSignService selfSign = SelfSignServiceFactory.instance();

		SignPDFFileBean fileBean = new SignPDFFileBean();		
		fileBean.setSrcPdfFile("C:/Users/Administrator/Desktop/test-dst.pdf");
		fileBean.setDstPdfFile("C:/Users/Administrator/Desktop/test-final.pdf");
		fileBean.setFileName("test1");
		
		PosBean pos = new PosBean();
		//定位类型，0-坐标定位，1-关键字定位，默认0，若选择关键字定位，签署类型(signType)必须指定为关键字签署才会生效
        pos.setPosType(0);
        pos.setPosX(200);
        pos.setPosY(100);
        //签署页码，若为多页签章，支持页码格式“1-3,5,8“，若为坐标定位时，不可空
        pos.setPosPage("1");
        //关键字，仅限关键字签章时有效，若为关键字定位时，不可空
        pos.setKey("");
        //印章展现宽度，将以此宽度对印章图片做同比缩放。
        pos.setWidth(159);

        //签署印章的标识，为0表示用默认印章签署
        Integer sealId = 0;
        
        //签章类型
        SignType signType = SignType.Single;
        
		FileDigestSignResult r = selfSign.localSignPdf(fileBean, pos, sealId, signType);
		logger.info(JSONObject.fromObject(r).toString());
		logger.info("签署记录id："+r.getSignServiceId());
	}
	
	
	/**
	 * 平台自身PDF摘要签署(文件流)
	 * @throws IOException 
	 */
	@Test
	public void SelfSignServiceByStreamTest() throws IOException{
		SelfSignService selfSign = SelfSignServiceFactory.instance();
		
		SignPDFStreamBean signPDFStreamBean = new SignPDFStreamBean();
		signPDFStreamBean.setStream(FileUtil.toByteArray("C:/Users/Administrator/Desktop/x.pdf"));
//		signPDFStreamBean.setFileName("");
		
		
		PosBean pos = new PosBean();
		//定位类型，0-坐标定位，1-关键字定位，默认0，若选择关键字定位，签署类型(signType)必须指定为关键字签署才会生效
        pos.setPosType(0);
        pos.setPosX(200);
        pos.setPosY(100);
        //签署页码，若为多页签章，支持页码格式“1-3,5,8“，若为坐标定位时，不可空
        pos.setPosPage("1");
        //关键字，仅限关键字签章时有效，若为关键字定位时，不可空
        pos.setKey("");
        //印章展现宽度，将以此宽度对印章图片做同比缩放。
        pos.setWidth(159);

        //签署印章的标识，为0表示用默认印章签署
        Integer sealId = 0;
        
        //签章类型
        SignType signType = SignType.Single;
        
        FileDigestSignResult r = selfSign.localSignPdf(signPDFStreamBean, pos, sealId, signType);
		logger.info(JSONObject.fromObject(r).toString());
		logger.info("签署记录id："+r.getSignServiceId());
	}
	
	/**
	 * 文档保全
	 * {"errCode":0,"errShow":false,"msg":"成功","url":"https://esignoss.oss-cn-hangzhou.aliyuncs.com/1111563517/67254e78-6582-4eca-b0fd-ff74602b5a05/test-new.pdf?Expires=1489461495&OSSAccessKeyId=STS.GN2evrWJdEq2TfJqBGEtCrzT5&Signature=DFbWHiphW12FKo6dTaZAgMbSL%2BY%3D&security-token=CAIS%2BAF1q6Ft5B2yfSjIpo2HLszGupVF8rPZVkD7lUISSftvnb//1zz2IHtKdXRvBu8Xs/4wnmxX7f4YlqB6T55OSAmcNZEoFGz0T8fiMeT7oMWQweEurv/MQBqyaXPS2MvVfJ%2BOLrf0ceusbFbpjzJ6xaCAGxypQ12iN%2B/m6/Ngdc9FHHPPD1x8CcxROxFppeIDKHLVLozNCBPxhXfKB0ca0WgVy0EHsPnvm5DNs0uH1AKjkbRM9r6ceMb0M5NeW75kSMqw0eBMca7M7TVd8RAi9t0t1/IVpGiY4YDAWQYLv0rda7DOltFiMkpla7MmXqlft%2BhzcgeQY0pc/RqAAbNFea3X1HLukSD8VtUDNo5wCehpa%2BrWa4QIZBiZjlfKJyVYPko7OPRvr%2BaA4Pjsoz/fcfVcUkkh0/N64Y%2BAE3LsDEItHlUJrqwDJO7bD96hQ7bPN3GkbW%2BUojknFNAM/YMO4KYiNK3UvSprCCd0WU3ANQXVn14KWEf0qEwlB%2B%2BR"}
		{"errCode":0,"errShow":false,"msg":"成功","url":"https://esignoss.oss-cn-hangzhou.aliyuncs.com/1111563517/cb0772b4-68b9-4007-836e-7a90dca538b8/test-new.pdf?Expires=1489552278&OSSAccessKeyId=STS.K57uzm64ztjowA2q7frbGMsqr&Signature=FG7mzsle%2BIsndYNTx7oy2WRDVFs%3D&security-token=CAIS%2BAF1q6Ft5B2yfSjIqvaCPsDZ2%2Btbw6iEdWeDlTczfu1rorbakDz2IHtKdXRvBu8Xs/4wnmxX7f4YlqB6T55OSAmcNZEoQ3vPGMbiMeT7oMWQweEurv/MQBqyaXPS2MvVfJ%2BOLrf0ceusbFbpjzJ6xaCAGxypQ12iN%2B/m6/Ngdc9FHHPPD1x8CcxROxFppeIDKHLVLozNCBPxhXfKB0ca0WgVy0EHsPnvm5DNs0uH1AKjkbRM9r6ceMb0M5NeW75kSMqw0eBMca7M7TVd8RAi9t0t1/IVpGiY4YDAWQYLv0rda7DOltFiMkpla7MmXqlft%2BhzcgeQY0pc/RqAAYABauuf/QWqchUx4HilxvJfDpuJl3cz3aoTZq2%2B4Hn5JNYSZfOc4nBSBOduaRJlNlvSuALyQOeg4CdOIIPT92j8aPqEy8BLPErcuyvqW6Q5zWUUf3%2B6uwWLuPkOIW/dxlllOQvJjUn4bnl%2BD6Cy3%2BaogQLc%2B%2B8BLavg5mnP1WdL"}

	 */
	@Test
	public void saveTest() {
		SignService signService = SignServiceFactory.instance();
		// 签署后文档路径文档本地路径，含文档名
		String docFilePath = "C:/Users/Administrator/Desktop/test-final.pdf";
		//文档名称，将作为存证名称
		String docName = "test-new.pdf";		
		//文档对应的所有签署记录集合
		List<String> signServiceIds = new ArrayList<String>();
        signServiceIds.add("841854225804726279");
        signServiceIds.add("841853029996072964");
		SignedFileResult saveFileResult = signService.saveSignedFile(docFilePath, docName, signServiceIds);
		
		GetSignedFileResult getSignedFileResult = signService.getSignedFile(saveFileResult.getDocId());
		
		logger.info(JSONObject.fromObject(getSignedFileResult).toString());
	}
	
	/**
	 * 文档保全(文件流)
	 * {"errCode":0,"errShow":false,"msg":"成功","url":"https://esignoss.oss-cn-hangzhou.aliyuncs.com/1111563517/d49600bd-9f10-4a18-b20a-2148a38c99a3/test-new.pdf?Expires=1489552697&OSSAccessKeyId=STS.JBwvsy1JD7pwPixzvAMrXvnd1&Signature=sY0Ravpvwjhj2dGesDkVQONhomM%3D&security-token=CAIS%2BAF1q6Ft5B2yfSjIq4HCPcnN3JVlgLKcUk/JnnYUQf10mavP0zz2IHtKdXRvBu8Xs/4wnmxX7f4YlqB6T55OSAmcNZEoYzK2G8biMeT7oMWQweEurv/MQBqyaXPS2MvVfJ%2BOLrf0ceusbFbpjzJ6xaCAGxypQ12iN%2B/m6/Ngdc9FHHPPD1x8CcxROxFppeIDKHLVLozNCBPxhXfKB0ca0WgVy0EHsPnvm5DNs0uH1AKjkbRM9r6ceMb0M5NeW75kSMqw0eBMca7M7TVd8RAi9t0t1/IVpGiY4YDAWQYLv0rda7DOltFiMkpla7MmXqlft%2BhzcgeQY0pc/RqAAaAFol/wcYlN6p%2BBXzHyg2t64QzVbyN9o7NBJMmobMbwmsQgSpF4V/vwhEyLEYZmysoeMwgL3x0B8tot4mehxQZDNdB%2B2UUgzuKKntjhkiZiVzDXMyW8Gx9OZofdD7gDCTJJ5lQHTRZCAk9DPrhcfsBP4ejK/7YFKaALhZ/bntP%2B"}

	 * @throws IOException 
	 */
	@Test
	public void saveBySteamTest() throws IOException {
		SignService signService = SignServiceFactory.instance();
		// 待签署文档本地二进制数据
		byte[] stream = FileUtil.toByteArray("C:/Users/Administrator/Desktop/x.pdf");
		//文档名称，将作为存证名称
		String docName = "test-new.pdf";		
		//文档对应的所有签署记录集合
		List<String> signServiceIds = new ArrayList<String>();
        signServiceIds.add("841854225804726279");
        signServiceIds.add("841853029996072964");
        SignedFileResult saveFileResult = signService.saveSignedFile(stream, docName, signServiceIds);
		
		GetSignedFileResult getSignedFileResult = signService.getSignedFile(saveFileResult.getDocId());
		
		logger.info(JSONObject.fromObject(getSignedFileResult).toString());
	}
	
	@Test
	public void a(){
		SignService SERVICE = SignServiceFactory.instance();
		String srcData ="asdwsqd.....";//待签名文本数据 
		SignDataResult result =  SERVICE.localTextSign(srcData);
		logger.info(JSONObject.fromObject(result).toString());
	}
}
