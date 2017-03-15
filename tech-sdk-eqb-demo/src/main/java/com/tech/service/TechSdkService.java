package com.tech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tech.util.PropertiesUtil;
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
import com.timevale.esign.sdk.tech.bean.result.SignedFileResult;
import com.timevale.esign.sdk.tech.bean.seal.PersonTemplateType;
import com.timevale.esign.sdk.tech.bean.seal.SealColor;
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

@Service
public class TechSdkService {
	
	/**
	 * 初始化
	 * @return
	 */
	public Result init(){
		EsignsdkService SDK = EsignsdkServiceFactory.instance();
		
		ProjectConfig projectConfig = new ProjectConfig();
		PropertiesUtil propertiesUtil = PropertiesUtil.getInstance("tech.properties");
		projectConfig.setProjectId(propertiesUtil.getValue("tech.projectId"));
		projectConfig.setProjectSecret(propertiesUtil.getValue("tech.projectSecret"));
		projectConfig.setItsmApiUrl(propertiesUtil.getValue("tech.itsmApiUrl"));
				
		HttpConnectionConfig httpConfig = new HttpConnectionConfig();
		SignatureConfig signConfig = new SignatureConfig();
		Result result = SDK.init(projectConfig, httpConfig, signConfig);
		return result;
	}
	
	/**
	 * 创建个人账户
	 * @param psn
	 * @return
	 */
	public AddAccountResult createPersonAccount(PersonBean psn){
		AccountService SERVICE = AccountServiceFactory.instance();
		AddAccountResult r = SERVICE.addAccount(psn);
		return r;
	}
	
	/**
	 * 创建企业账户
	 * @param org
	 * @return
	 */
	public AddAccountResult createOrganizaAccount(OrganizeBean org){
		AccountService SERVICE = AccountServiceFactory.instance();
		AddAccountResult r = SERVICE.addAccount(org);
		return r;
	}
	
	/**
	 * 创建个人模板印章
	 * @param accountId
	 * @param personTemplateType
	 * @param sealColor
	 * @return
	 */
	public AddSealResult createPersonSeal(String accountId,PersonTemplateType personTemplateType,SealColor sealColor){
		SealService SEAL = SealServiceFactory.instance();
		AddSealResult addSealResult = SEAL.addTemplateSeal(accountId, personTemplateType, sealColor);
		return addSealResult;
	}
	
	/**
	 * 平台用户PDF摘要签署
	 * @param accountId
	 * @param sealData
	 * @param pos
	 * @param signPDFStreamBean
	 * @param signType
	 * @return
	 */
	public FileDigestSignResult userSign(String accountId,String sealData,PosBean pos,SignPDFFileBean fileBean,SignType signType){
		UserSignService userSign = UserSignServiceFactory.instance();
		FileDigestSignResult r = userSign.localSignPDF(accountId, sealData, fileBean, pos, signType);
		return r;
	}
	
	/**
	 * 平台用户PDF摘要签署(文件流)
	 * @param accountId
	 * @param sealData
	 * @param pos
	 * @param signPDFStreamBean
	 * @param signType
	 * @return
	 */
	public FileDigestSignResult userSignByStream(String accountId,String sealData,PosBean pos,SignPDFStreamBean signPDFStreamBean,SignType signType){
		UserSignService userSign = UserSignServiceFactory.instance();
		FileDigestSignResult r = userSign.localSignPDF(accountId, sealData, signPDFStreamBean, pos, signType);
		return r;
	}
	
	/**
	 * 平台自身PDF摘要签署
	 * @param fileBean
	 * @param pos
	 * @param sealId
	 * @param signType
	 * @return
	 */
	public FileDigestSignResult selfSign(SignPDFFileBean fileBean,PosBean pos, Integer sealId,SignType signType){
		SelfSignService selfSign = SelfSignServiceFactory.instance();
		FileDigestSignResult r = selfSign.localSignPdf(fileBean, pos, sealId, signType);
		return r;
	}
	
	/**
	 * 平台自身PDF摘要签署(文件流)
	 * @param signPDFStreamBean
	 * @param pos
	 * @param sealId
	 * @param signType
	 * @return
	 */
	public FileDigestSignResult selfSignBySteam(SignPDFStreamBean signPDFStreamBean,PosBean pos, Integer sealId,SignType signType){
		SelfSignService selfSign = SelfSignServiceFactory.instance();
		FileDigestSignResult r = selfSign.localSignPdf(signPDFStreamBean, pos, sealId, signType);
		return r;
	}
	
	/**
	 * 文档保全
	 * @param docFilePath
	 * @param docName
	 * @param signServiceIds
	 * @return
	 */
	public GetSignedFileResult save(String docFilePath,String docName,List<String> signServiceIds){
		SignService signService = SignServiceFactory.instance();
        SignedFileResult saveFileResult = signService.saveSignedFile(docFilePath, docName, signServiceIds);		
		GetSignedFileResult getSignedFileResult = signService.getSignedFile(saveFileResult.getDocId());
		
		return getSignedFileResult;
	}
	
	/**
	 * 文档保全(文件流)
	 * @param stream
	 * @param signServiceIds
	 * @param docName
	 * @return
	 */
	public GetSignedFileResult saveByStream(byte[] stream,List<String> signServiceIds,String docName){
		SignService signService = SignServiceFactory.instance();
        SignedFileResult saveFileResult = signService.saveSignedFile(stream, docName, signServiceIds);
		GetSignedFileResult getSignedFileResult = signService.getSignedFile(saveFileResult.getDocId());
		
		return getSignedFileResult;
	}
}
