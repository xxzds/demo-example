package com.anjz.itext.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfExport {

	public static void exportProtocolPdf(OutputStream out) {
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);// 其余4个参数，设置了页面的4个边距
		try {

			PdfWriter.getInstance(document, out);

			// 打开PDF文档
			document.open();
			
			//设置标题
			Paragraph title = new PdfParagraph("易借条服务协议",30,true);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);			
			
			// 换行
            document.add(new Chunk("\n"));

			document.add(new PdfParagraph("    申请使用“易借条服务”，您需要同意本协议中的所有内容："));
			document.add(new PdfParagraph(
					"    您使用“易借条服务”，须与安徽慧通互联科技有限公司（以下简称“慧通互联”）签订本协议，并授权慧通互联查询您的信用信息，用于评估慧通互联与您的交易条件和控制服务中的风险，慧通互联查询您的信用信息的有效期至慧通互联对您使用易借条服务申请审核不通过日止（易借条服务未获批的情形下），或者您使用的易借条服务终止日止（易借条服务获批的情形下）。"));
			document.add(new PdfParagraph("    本协议约定了慧通互联向您提供易借条服务的前提、双方权利义务及其他事项。您在慧通互联提供的网络页面上点击以合理的理解表明您希望与慧通互联签订本协议的按钮，即表明本协议在您与慧通互联之间成立并生效。"));
			document.add(new PdfParagraph("第一条 声明与承诺",10,true));
			
			
			List<RecordModel> contentList = new ArrayList<RecordModel>();
			
			contentList.add(new RecordModel(new String[]{"(一)","慧通互联一贯重视用户权益保护，故本协议已对慧通互联认为的与您的权益有或可能有重大关系的条款及对慧通互联具有或可能具有免责或限制责任的条款用粗体字予以标注，请您务必注意。您理解并承诺，在您申请成为易借条用户以接受本服务，或您以慧通互联允许的其他方式实际使用本服务前，您已充分阅读、理解并接受本协议的全部内容，您使用本服务，即表示您同意遵循本协议之所有约定。"}));
			contentList.add(new RecordModel(new String[]{"(二)","作为本协议服务的提供方，慧通互联有权根据情况的需要对本协议内容进行变更，变更的原因包括但不限于国家法律、法规及其他规范性文件的规定变化，增加、变更了新的服务类型或服务方式，进一步明确协议双方的权利义务。因本协议所涉服务的性质独特及服务用户数量众多，故慧通互联在对本协议内容进行变更时，不会另行单独通知您，该等变更只会以在本站或其他适当的地方公告的方式予以公布；您在本协议内容公告变更后继续使用本服务，表示您已充分阅读、理解并接受变更后的协议内容，并遵循变更后的协议内容而使用本服务；若您不同意修改后的协议内容，您应停止使用本服务。"}));
			contentList.add(new RecordModel(new String[]{"(三)","您在接受本协议前应明确对于安徽慧通互联科技有限公司及慧易通网站进行如下授权：授权安徽慧通互联科技有限公司、慧易通网站、或由该网站及该公司委托的第三方通过合法渠道了解、咨询、审查用户的资信状况、财务状况和其他与评定用户信用付款额度及付款能力有关的信息，并可以在易借条服务整个过程中及在追偿违约欠款时索取、留存和使用该等信息以及用户在慧易通网站所提供的全部资料。您的账号、密码是易借条识别用户身份及指令的唯一标志，所有使用用户账号、密码的操作即为用户的（授权）操作行为。"},true));
			contentList.add(new RecordModel(new String[]{"(四)","您保证，作为自然人，在您同意接受本协议并申请成为易借条用户时，您具有中华人民共和国法律规定的完全民事权利和民事行为能力，能够独立承担民事责任。不具备前述条件的，您应立即终止申请或停止使用本服务。"}));			
			addTable(document,contentList,2);
			
			document.add(new PdfParagraph("第二条 定义",10,true));
			document.add(new PdfParagraph("    除非本协议正文另有明确所指，在本协议中所用定义如下："));
			
			contentList.clear();
			contentList.add(new RecordModel(new String[]{"(一)","慧通互联：安徽慧通互联科技有限公司"}));
			contentList.add(new RecordModel(new String[]{"(二)","用户：【实名认证的用户姓名（用户身份证号）】"}));
			contentList.add(new RecordModel(new String[]{"(三)","易借条：系慧通互联为您提供的安徽交通卡货车卡高速通行费的信用消费服务，用户可依据易借条规则及申请，享受由慧通互联提供的相应的信用消费付款方式。易借条包括通行费预存，及慧易通账户代扣等服务。慧通互联根据您的申请，进行审查、业务办理。审核通过后，用户即可享受易借条服务。用户每次高速通行消费使用易借条服务，按日产生相应的服务费，用户须按照本协议的约定，并遵照慧通互联的还款指示将欠还款项还清。"}));
			contentList.add(new RecordModel(new String[]{"(四)","慧易通账户：系用户开设在慧通互联的安徽交通卡（货车专用）资金帐户。"}));
			contentList.add(new RecordModel(new String[]{"(五)","易借条账户：系用户开设在慧通互联的安徽交通卡（货车专用）信用消费资金帐户。"}));
			contentList.add(new RecordModel(new String[]{"(六)","易借条额度：系慧通互联为用户核定的可循环使用的易借条信用消费额度,慧通互联可根据用户的使用情况，动态调整。"}));
			contentList.add(new RecordModel(new String[]{"(七)","欠还款项：系用户使用易借条进行的包含但不限于慧易通账户代扣消费金额、消费产生的日计服务费、逾期未能及时履行的日计违约金等款项之和。"}));
			contentList.add(new RecordModel(new String[]{"(八)","到期还款日：系用户使用易借条进行信用消费的第30天。"}));			
			addTable(document,contentList,2);
			
			document.add(new PdfParagraph("第三条 代扣与还款",10,true));
			contentList.clear();
			contentList.add(new RecordModel(new String[]{"(一)","您设定慧易通账户代扣服务，当慧易通账户余额到达零时，慧通互联自您的易借条账户自动划扣相应金额进行消费。"}));
			contentList.add(new RecordModel(new String[]{"(二)","您设定易借条账户与您现有的慧易通账户【xxx1、xxx2】绑定，自动代扣，由此产生的欠还款项，您须在到期还款日之前完成还款。"},true));
			addTable(document,contentList,2);
			
			document.add(new PdfParagraph("第四条 慧通互联之权利义务",10,true));
			contentList.clear();
			contentList.add(new RecordModel(new String[]{"(一)","您超过到期还款日仍未还清欠还款项，慧通互联有权督促您还款，并停止对您的易借条服务。"},true));
			contentList.add(new RecordModel(new String[]{"(二)","慧通互联有权根据高速通行消费等数据调整您的易借条额度。"},true));
			contentList.add(new RecordModel(new String[]{"(三)","慧通互联有权在您累计使用易借条款项达到易借条额度时，停止对您的易借条服务。"},true));
			contentList.add(new RecordModel(new String[]{"(四)","慧通互联向您收取易借条服务费，在到期还款日之前，按消费金额的（万分之五/日）计收服务费。超过到期还款日未还款，视为您违约，慧通互联按欠还款项的（万分之拾/日）计收违约金。"},true));
			contentList.add(new RecordModel(new String[]{"(五)","慧通互联有权根据自身业务的发展，增加、减少或撤销相关服务项目，甚至终止本服务的提供，调整自动代扣的内容及方式或者对本服务功能进行升级、维护和改造的权利。慧通互联亦有权调整本产品的服务费率、违约金率等，具体以届时页面提示的收费规则及计算方式为准。如您不接受上述变更的，您应立即停止使用本服务并终止本协议。一旦您继续使用本服务的，即视为您同意接受相应的变更或调整。"},true));
			contentList.add(new RecordModel(new String[]{"(六)","慧通互联建立呼叫中心，由专业客服人员向用户提供 7×14小时服务，通过包括但不限于服务电话、网络、现场进行等方式为用户提供业务咨询服务。具体详见i.ahggwl.com，或者致电0551-66775656咨询相关信息。"}));
			contentList.add(new RecordModel(new String[]{"(七)","您不遵守有关法律法规、本协议等规定（包括但不限于用户未在到期还款日之前支付欠还款项的）或者慧通互联有理由认为您不再符合易借条的使用条件时，慧通互联有权基于合理怀疑拒绝您继续使用易借条服务，如您在合理期限内未向慧通互联提供充分证据以消除该等合理怀疑的，慧通互联有权提前暂停易借条服务而不必事先通知您。您对慧通互联停止其易借条费服务的使用有异议的，需要按照慧通互联的要求，向慧通互联提供充分证据以消除该等合理怀疑。"},true));
			contentList.add(new RecordModel(new String[]{"(八)","慧通互联有权通过信函、短信、电子邮件、电话、上门或司法途径等方式向用户催收应还款项。您同意慧通互联有权授权第三方通过手机短信、站内信、电话提醒、上门外访和寄送信函等方式，督促您按时支付所有欠还款项，其中手机短信、站内信在发出时即视为送达。"},true));
			contentList.add(new RecordModel(new String[]{"(九)","慧通互联有权根据相关适用法律，将在易借条服务关系建立和存续期间获得的用户的个人基本信息、债务信息、信用信息（即适用法律规定的对您信用状况的客观状态）等有关信息提供给相关政府管理部门、行业监督管理机构或其他合法"},true));
			contentList.add(new RecordModel(new String[]{"(十)","慧通互联有义务向您通过网站（公告方式）、电子邮件、手机短信等公示易借条产品和服务、重要提示、服务协议、计费标准等信息。"},true));
			contentList.add(new RecordModel(new String[]{"(十一)","为了充分保护您的账户安全，当您访问慧易通网站及其相关网站或使用慧通互联提供的服务时，系统会记录您操作的相关信息，这些信息可帮助慧通互联更好地识别您的身份以及保护您的账户安全。"},true));
			addTable(document,contentList,2);
			
			document.add(new PdfParagraph("第五条 用户之权利义务",10,true));			
			contentList.clear();
			contentList.add(new RecordModel(new String[]{"(一)","您同意并按照慧通互联要求提供办理易借条所需各项资料，并保证所提供资料的真实性、合法性、准确性、完整性。"}));
			contentList.add(new RecordModel(new String[]{"(二)","您有权知悉易借条服务的功能、使用方法、收费项目、收费标准、费率及相关的计算规则。"}));
			contentList.add(new RecordModel(new String[]{"(三)","您依据本协议享受易借条服务, 由于易借条是后付费服务，有可能导致欠还款项大于易借条额度，您应按实际的欠还款项还款。"},true));
			contentList.add(new RecordModel(new String[]{"(四)","您可以申请慧易通账户代扣业务，享受易借条代扣服务。受您易借条账户易借条额度的限制及其他制约代扣服务成功的因素的影响，易借条可能无法正常向您提供代扣服务，您应消除前述影响因素，否则由此给您造成的任何损失将由您自行承担。请您根据易借条代扣相关服务提示的使用规则设置相关自动代扣内容并履行相关约定，因您未遵循本协议及相关规则或未尽到合理义务导致代扣服务失败的，慧通互联不承担任何责任。"},true));
			contentList.add(new RecordModel(new String[]{"(五)","您对易借条服务数据有异议的，应在交易数据产生后叁拾日内向慧通互联提出书面申请，并提供相关证明。"},true));
			contentList.add(new RecordModel(new String[]{"(六)","如您信息资料发生变更，包括但不限于您的慧易通账户信息、代扣信息、联系方式等，您变更后须及时到慧通互联客户网点做变更申请，以便慧通互联对您的易借条账户风险做出重新评估。因您未及时通知所产生的损失和责任等，均由您自行承担。"},true));
			contentList.add(new RecordModel(new String[]{"(七)","(您仔细阅读并充分理解本协议、重要提示及其他所有相关资料和文件，并持续关注慧通互联通过网站等方式向用户公示的协议修订，以及对上述资料和文件的修改和变更。"}));
			contentList.add(new RecordModel(new String[]{"(八)","用户承诺妥善保管其账号密码，不将个人账号信息泄露给其他任何人。因用户对其账号、密码保管不善而引发的包括但不限于损害赔偿等直接法律责任，概由用户自行承担。如您发现有他人冒用或盗用本人账号及密码或任何其他未经合法授权之情形时，应立即以有效方式（包括但不限于客服电话、现场等）通知慧通互联，要求暂停其账号相关服务。"},true));
			contentList.add(new RecordModel(new String[]{"(九)","您不得以出租、转借或以其他方式将使用易借条服务过程中的相关账号、密码等信息交由任何其他第三方使用，否则由此导致的损失及相关后果由您承担。"}));
			contentList.add(new RecordModel(new String[]{"(十)","您不得以非法套现、非法获取不正当利益为目的，违反易借条服务规则及各项管理规定，否则由此导致的损失及相关后果由您承担。"}));
			contentList.add(new RecordModel(new String[]{"(十一)","您应按时支付易借条服务的欠还款项（包括但不限于本金、服务费等），且不得以服务纠纷为由拒绝支付易借条服务欠应还款项。"},true));
			contentList.add(new RecordModel(new String[]{"(十二)","您在使用易借条服务过程中遇到相关问题，均可通过本站公布的联系方式与慧通互联沟通协商。"}));
			addTable(document,contentList,2);
			
			document.add(new PdfParagraph("第六条 免责条款",10,true));
			contentList.clear();
			contentList.add(new RecordModel(new String[]{"(一)","易借条相关平台的信息系统出现下列任一状况而无法正常运作，致使无法向您提供本协议项下的各项服务，慧通互联不承担任何违约或赔偿责任，该状况包括但不限于："}));
			contentList.add(new RecordModel(new String[]{"1.","在服务网站维护期间；"}));
			contentList.add(new RecordModel(new String[]{"2.","电信设备出现故障不能进行数据传输的；"}));
			contentList.add(new RecordModel(new String[]{"3.","因台风、地震、海啸、洪水、停电、战争、恐怖袭击等不可抗力之因素，造成系统障碍不能执行业务的；"}));
			contentList.add(new RecordModel(new String[]{"4.","由于黑客攻击、电信部门和其他慧通互联有信息技术依赖的相关部门、企事业单位技术调整或故障、网站升级、银行方面的问题等原因而造成的服务中断或者延迟。"}));
			contentList.add(new RecordModel(new String[]{"5.","在服务网站维护期间；"}));
			contentList.add(new RecordModel(new String[]{"(二)","本协议有效期内，因国家相关主管部门颁布、变更的法令、政策导致慧通互联相关平台不能提供约定服务的，不视为其违约，各方可根据相关的法令、政策变更协议内容或提前终止本协议，在此情况下，您已经使用易借条服务而尚未到期或偿还的任何款项视为立即到期，您应立即归还，否则慧通互联仍有权按照本协议的约定采取合理的救济措施。"}));
			addTable(document,contentList,2);
			
			document.add(new PdfParagraph("第七条 逾期及违约",10,true));
			contentList.clear();
			contentList.add(new RecordModel(new String[]{"(一)","您到到期还款日仍未还清欠还款项，视为您逾期并违约，慧通互联将按如下催收程序对您进行催收："},true));
			contentList.add(new RecordModel(new String[]{"1.","慧通互联自到期还款日之次日起按欠还款项的【万分之拾/日】计收违约金，并停止对您的易借条服务，直至您还清欠还款项。"},true));
			contentList.add(new RecordModel(new String[]{"2.","慧通互联自到期还款日之次日起有权冻结您名下所有开设在慧通互联的安徽交通卡（货车专用）资金帐户，并暂停您名下所有安徽交通卡（货车专用）的使用，限制车辆行驶高速公路、一级公路等，由此产生的损失由您自行承担；慧通互联有权将全部或者部分债权转让给任何第三方（包括但不限于专业的商账追收公司等）；慧通互联有权解除本协议，并有权以法律途径追偿所有损失。"},true));
			contentList.add(new RecordModel(new String[]{"(二)","如有下列任一情形，视为您严重性违约事件，慧通互联有权追究您的法律责任，有权在慧通互联公司网站及其他媒体公告您的失信违约行为情况，并有权向交通、公安、工商等部门共享您失信违约行为信息，由此产生的损失由您自行承担。"},true));			
			contentList.add(new RecordModel(new String[]{"1.","您提供的证件、资料虚假不属实。"},true));
			contentList.add(new RecordModel(new String[]{"2.","您逾期天数超过【十五】日。"},true));
			contentList.add(new RecordModel(new String[]{"3.","您违反本协议之相关约定，或未经慧通互联同意，擅自转让本协议债务。"},true));
			addTable(document,contentList,2);
			
			document.add(new PdfParagraph("第八条 通知与送达",10,true));
			contentList.clear();
			contentList.add(new RecordModel(new String[]{"(一)","本协议各方地址与联系方式如下："}));
			addTable(document,contentList,2);
			
			
			/**************************************公司信息 start******************************************/
			PdfPTable table = new PdfPTable(2);
			int[] tableWidths = { 8, 92 };// 按百分比分配单元格宽带
			table.setWidths(tableWidths);
			table.setTotalWidth(PageSize.A4.getWidth() - 100);
			table.setLockedWidth(true);
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER); 
			
			PdfPCell cell = null;
			cell = new PdfPCell(new PdfParagraph("1."));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
						
			List<String> contentStringList=new ArrayList<String>();
			contentStringList.add("公司信息");
			contentStringList.add("慧通互联：安徽慧通互联科技有限公司");
			contentStringList.add("地址：合肥市高新区留学生园二号楼212、214、216室");
			contentStringList.add("电话：0551-66775656");
			contentStringList.add("电子邮箱：service@ustcsoft.com");
			table.addCell(getOneColumeTable(contentStringList));			
			document.add(table);
			/**************************************公司信息 end******************************************/
			
			/**************************************用户信息 start*****************************************/
			PdfPTable table1 = new PdfPTable(2);
			int[] tableWidths1 = { 8, 92 };// 按百分比分配单元格宽带
			table1.setWidths(tableWidths1);
			table1.setTotalWidth(PageSize.A4.getWidth() - 100);
			table1.setLockedWidth(true);
			table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER); 
			
			PdfPCell cell1 = null;
			cell1 = new PdfPCell(new PdfParagraph("2."));
			cell1.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell1);
						
			contentStringList.clear();
			contentStringList.add("用户信息");
			contentStringList.add("您:【系慧易通网站实名认证的用户姓名】");
			contentStringList.add("电话：【系慧易通网站注册的用户手机号】");
			contentStringList.add("电子邮箱：【系易借条服务申请的用户电子邮箱】");
			contentStringList.add("通讯地址：【系易借条服务申请的用户地址】");
			contentStringList.add("直系亲属联系人：【系易借条服务申请时的直系亲属联系人姓名（关系）】");
			contentStringList.add("直系亲属联系人电话：【系易借条服务申请时的直系亲属联系人电话】");
			contentStringList.add("紧急联系人：【系易借条服务申请时的紧急联系人姓名（关系）】");
			contentStringList.add("紧急联系人电话：【系易借条服务申请时的紧急联系人电话】");
			table1.addCell(getOneColumeTable(contentStringList));			
			document.add(table1);
			/**************************************用户信息 end*****************************************/
			
			contentList.clear();
			contentList.add(new RecordModel(new String[]{"(二)","除法律法规或监管规定另有强制性规定外，本合同履行过程中，慧通互联传递给您的通知包括但不限于慧易通网站公告、向您发送电子邮件、手机短信等电子方式，在采用电子方式进行通知的情况下发送当日即视为送达。"}));
			addTable(document,contentList,2);
			
			
			document.add(new PdfParagraph("第九条 协议生效、期限、解除及续期",10,true));
			contentList.clear();
			contentList.add(new RecordModel(new String[]{"(一)","本协议由本协议条款与本网站公示的各项规则组成，相关名词可互相引用参照，如有不同理解，以本协议条款为准。您对本协议理解和认同，您即对本协议所有组成部分的内容理解并认同，一旦您使用本服务，您和慧通互联即受本协议所有组成部分的约束。本协议部分内容被有管辖权的法院认定为违法或无效的，不因此影响其他内容的效力。"}));
			contentList.add(new RecordModel(new String[]{"(二)","本协议履行过程中，如发生下列所述情形之一，慧通互联有权单方解除本协议。通知到达您之日，本协议解除。您应在接到慧通互联通知后拾日内，结清其所有欠还款项："},true));
			contentList.add(new RecordModel(new String[]{"1.","您生产经营困难、财务状况恶化，可能影响其履行本协议。"},true));
			contentList.add(new RecordModel(new String[]{"2.","您发生严重性违约。"},true));
			contentList.add(new RecordModel(new String[]{"3.","您依法申请或被第三方申请破产、重组、托管或其他类似法律程序。"},true));
			contentList.add(new RecordModel(new String[]{"4.","您出现其他可能严重影响慧通互联及时、足额收回欠还款项的情形。"},true));
			contentList.add(new RecordModel(new String[]{"(三)","您在使用易借条服务的过程中，如果有下列情形发生，您同意慧通互联有权终止提供服务，应立即支付所有欠还款项："},true));
			contentList.add(new RecordModel(new String[]{"1.","您账户注销。"},true));
			contentList.add(new RecordModel(new String[]{"2.","您违反本协议约定，导致本协议约定的违约情形出现的。"},true));
			contentList.add(new RecordModel(new String[]{"3.","您从事司法机构、监管机构等认为违法违规的行为，或慧通互联认定的不适当行为。"},true));
			contentList.add(new RecordModel(new String[]{"4.","除上述原因外，慧通互联有权根据风险及自身业务运营情况需要随时中止（终止）向您提供易借条服务，因此导致您无法使用服务或服务受到限制的，不视为易借条服务提供方违约，在此情况下，您已经使用易借条服务而尚未到期或偿还的任何款项，视为立即到期，您应立即归还。"},true));
			contentList.add(new RecordModel(new String[]{"(四)","本协议不论因任何事由而终止，均不影响其终止时慧通互联根据本协议已经取得的欠还款项下的权利与义务。在行使及履行此等权利与义务之必要范围内，本协议继续有效。并且协议各方应理清根据本协议各自应付款项。"}));
			contentList.add(new RecordModel(new String[]{"(五)","本协议有效期壹年，期满后十日内如双方均未对本协议履行提出异议，则本协议自动续期壹年，以此类推。"},true));
			addTable(document,contentList,2);
			
			document.add(new PdfParagraph("第十条 商标、知识产权、专利的保护",10,true));
			contentList.clear();
			contentList.add(new RecordModel(new String[]{"(一)","慧通互联及关联公司所有系统及本网站上所有内容，包括但不限于著作、图片、档案、资讯、资料、网站架构、网站画面的安排、网页设计，均由慧通互联或慧通互联关联公司依法拥有其知识产权，包括但不限于商标权、专利权、著作权、商业秘密权利等。"}));
			contentList.add(new RecordModel(new String[]{"(二)","非经慧通互联或慧通互联关联企业书面同意，任何人不得擅自使用、修改、复制、公开传播、改变、散布、发行或公开发表本网站程序或内容。"}));
			contentList.add(new RecordModel(new String[]{"(三)","尊重知识产权是您应尽的义务，如有违反， 您应承担损害赔偿责任。"}));
			addTable(document,contentList,2);
						
			document.add(new PdfParagraph("第十一条 法律适用与管辖",10,true));
			contentList.clear();
			contentList.add(new RecordModel(new String[]{"(一)","用法律。本协议的全部事项，包括但不限于本协议的效力、解释、履行以及争议的解决均受中国法律管辖；本协议项下任一条款如与中国法律中的强制性规范相抵触，应在该等强制性规范所不禁止的最大限度内进行解释和执行，且任何该等与强制性规范相抵触的约定不应影响本协议其他条款的效力。"}));
			contentList.add(new RecordModel(new String[]{"(二)","凡因本协议发生的任何争议，协议各方应通过友好协商解决；友好协商不成的，提交慧通互联公司所在地有管辖权法院进行诉讼解决。诉讼费、合理的律师费以及诉讼过程中产生的其他费用（其包括但不限于财产保全费、差旅费、公证认证费、翻译费、评估拍卖费、执行费等）均由败诉方承担。"}));
			contentList.add(new RecordModel(new String[]{"(三)","在仲裁或诉讼期间，本协议中不涉及争议的条款仍须履行，各方均不得以解决争议为由拒不履行其在本协议项下的任何义务。"}));
			addTable(document,contentList,2);
		} catch (DocumentException e) {

		} finally {
			try {
				document.close();
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {

			}
		}
	}
	
	
	
	/**
	 * 增加表格
	 * @param document       文档
	 * @param contentList    表格内容
	 * @param column         列数
	 * @throws DocumentException
	 */
	public static void addTable(Document document,List<RecordModel> contentList,int column) throws DocumentException{
		PdfPTable table = new PdfPTable(column);
		int[] tableWidths = { 8, 92 };// 按百分比分配单元格宽带
		table.setWidths(tableWidths);
		table.setTotalWidth(PageSize.A4.getWidth() - 100);
		table.setLockedWidth(true);
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

		PdfPCell cell = null;
		for (RecordModel recordModel : contentList) {
			String[] contents = recordModel.getStrs();
			for (String content : contents) {
				if (recordModel.getIsBold()) {
					cell = new PdfPCell(new PdfParagraph(content, 10, true));
				} else {
					cell = new PdfPCell(new PdfParagraph(content));
				}

				cell.setHorizontalAlignment(Cell.ALIGN_TOP);
				cell.setVerticalAlignment(Cell.ALIGN_LEFT);
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
			}
		}
		document.add(table);
	}
	
	
	/**
	 * 一列的表格
	 * @param contentList    每列显示的内容
	 * @return
	 */
	public static PdfPTable getOneColumeTable(List<String> contentList){
		PdfPTable table = new PdfPTable(1);
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);  
		
		PdfPCell cell = null;		
		for(String content:contentList){
			cell = new PdfPCell(new PdfParagraph(content));
			cell.setHorizontalAlignment(Cell.ALIGN_TOP);
			cell.setVerticalAlignment(Cell.ALIGN_LEFT);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
		}		
		return table;
	}
	

	public static void main(String[] args) throws DocumentException, IOException {
		exportProtocolPdf(new FileOutputStream(new File("C:/Users/Administrator/Desktop/x.pdf")));
	}
}
