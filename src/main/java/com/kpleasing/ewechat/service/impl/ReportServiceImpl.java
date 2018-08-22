package com.kpleasing.ewechat.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpleasing.ewechat.common.Configure;
import com.kpleasing.ewechat.dao.CrmBpMasterDao;
import com.kpleasing.ewechat.enums.APP_TYPE;
import com.kpleasing.ewechat.mongo.collections.BranchCompany;
import com.kpleasing.ewechat.mongo.collections.BusinessMember;
import com.kpleasing.ewechat.mongo.collections.BusinessTeam;
import com.kpleasing.ewechat.mongo.collections.KPBusinessReport;
import com.kpleasing.ewechat.mongo.dao.KPBusinessReportDao;
import com.kpleasing.ewechat.protocol.send_text.SendTextResData;
import com.kpleasing.ewechat.service.ReportService;
import com.kpleasing.ewechat.util.ConfigUtil;
import com.kpleasing.ewechat.util.DateUtil;
import com.kpleasing.ewechat.util.HttpHelper;
import com.kpleasing.ewechat.util.JsonUtil;
import com.kpleasing.ewechat.util.WeChatUtils;
import com.kpleasing.ewechat.vo.Item;
import com.kpleasing.ewechat.vo.Merchant;
import com.kpleasing.ewechat.vo.Platform;

@Service("ReportService")
public class ReportServiceImpl implements ReportService {
	
	private static Logger logger = Logger.getLogger(ReportServiceImpl.class);
	
	@Autowired
	CrmBpMasterDao crmBpMasterDao;
	
	
	@Autowired
	KPBusinessReportDao businessReportDao;

	
	private String getPushURL001() {
		ConfigUtil conf = ConfigUtil.getInstance();
		StringBuilder url = new StringBuilder();
		url.append(conf.getPropertyParam("push_download_uri"))
		.append("?access_key=").append(conf.getPropertyParam("push_access_key_001"))
		.append("&date=").append(DateUtil.getCurrentDate(DateUtil.yyyyMMdd));
		
		return url.toString();
	}
	
	
	private String getPushURL002() {
		ConfigUtil conf = ConfigUtil.getInstance();
		StringBuilder url = new StringBuilder();
		url.append(conf.getPropertyParam("push_download_uri"))
		.append("?access_key=").append(conf.getPropertyParam("push_access_key_002"))
		.append("&date=").append(DateUtil.getCurrentDate(DateUtil.yyyyMMdd));
		
		return url.toString();
	}
	
	private void moveFileToLocal(String file1, String file2) {
		String url = getPushURL001();
		logger.info("请求下载文件1-URI：" + url);
		Map<String, String> map = new HashMap<String, String>();
		map.put("save_file_addr", file1);
		HttpHelper.doHttpDownload(url, map);
		
		url = getPushURL002();
		logger.info("请求下载文件2-URI："+url);
		map.put("save_file_addr", file2);
		HttpHelper.doHttpDownload(url, map);
	}
	
	/**
	 * 
	 * @param file1
	 * @param pf
	 */
	private void parseFormatText(String file1, Platform pf) {
		StringBuilder txtContent = new StringBuilder();
		BufferedReader bufReader = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(file1));
			bufReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			
			String headLine = bufReader.readLine();
			logger.info("头信息："+headLine);
			String[] headers = headLine.split("\\|");
			pf.setPlatformName(headers[0]);
			pf.setAuthCounts(headers[1]);
			pf.setRentCounts(headers[2]);
			pf.setPurcCounts(headers[3]);
			pf.setCoopCounts(headers[4]);
			
			List<Merchant> merchants = new ArrayList<Merchant>();
			List<Item> items = null;
			Merchant merchant = null;
			String line;
			while ((line = bufReader.readLine()) != null) {
				if("********************".equals(line)) break;
				if("--------------------".equals(line)) {
					if(merchant!=null && items!=null) {
						merchant.setItems(items);
						merchant = null;
						items = null;
					}
					
					String head2Line = bufReader.readLine();
					merchant = new Merchant();
					items = new ArrayList<Item>();
					String[] hs = head2Line.split("\\|");
					merchant.setMerchantName(hs[0]);
					merchant.setAuthCounts(hs[1]);
					merchant.setRentCounts(hs[2]);
					merchant.setPurcCounts(hs[3]);
					merchant.setCoopCounts(hs[4]);
					merchants.add(merchant);
					continue;
				}
				
				String[] h = line.split("\\|");
				Item item = new Item();
				item.setCustName(h[0]);
				item.setAuthDate(h[1]);
				item.setRentDate(h[2]);
				item.setFinance(h[3]);
				item.setCarType(h[4]);
				item.setCarDesc(h[5]);
				items.add(item);
			}
			if(merchant!=null && items!=null) {
				merchant.setItems(items);
				merchant = null;
				items = null;
			}
			pf.setMerchants(merchants);
			
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			close(inputStream, bufReader);
		}
		logger.info(txtContent.toString());
	}
	
	
	/**
	 * 
	 * @param file2
	 * @param pf
	 */
	private void parseFormatText2(String file2, Platform pf) {
		StringBuilder txtContent = new StringBuilder();
		BufferedReader bufReader = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(file2));
			bufReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			
			String headLine = bufReader.readLine();
			logger.info("头信息："+headLine);
			String[] headers = headLine.split("\\|");
			pf.setPlatformName(headers[0]);
			pf.setPurcCounts(headers[1]);
			
			List<Merchant> merchants = new ArrayList<Merchant>();
			List<Item> items = null;
			Merchant merchant = null;
			String line;
			while ((line = bufReader.readLine()) != null) {
				if("********************".equals(line)) break;
				if("--------------------".equals(line)) {
					if(merchant!=null && items!=null) {
						merchant.setItems(items);
						merchant = null;
						items = null;
					}
					
					String head2Line = bufReader.readLine();
					merchant = new Merchant();
					items = new ArrayList<Item>();
					String[] hs = head2Line.split("\\|");
					merchant.setMerchantName(hs[0]);
					merchants.add(merchant);
					continue;
				}
				
				String[] h = line.split("\\|"); 
				Item item = new Item();
				item.setCustName(h[0]);
				item.setCarModel(h[1]);
				item.setCarType(h[2]);
				item.setPurcPrice(h[3]);
				item.setSpName(h[4]);
				items.add(item);
			}
			if(merchant!=null && items!=null) {
				merchant.setItems(items);
				merchant = null;
				items = null;
			}
			pf.setMerchants(merchants);
			
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			close(inputStream, bufReader);
		}
		logger.info(txtContent.toString());
	}
	
	private void pushText1(Platform platform, Platform platform2) {
		logger.info("start info ...... ");
		ConfigUtil conf = ConfigUtil.getInstance();
		StringBuilder abc = new StringBuilder();
		abc.append("新审批通过").append(platform.getAuthCounts()).append("单，")
		.append("30天内已放款起租").append(platform.getRentCounts()).append("单\n")
		.append("采购中车型").append(platform.getPurcCounts()).append("单，")
		.append("30天内已合作车型").append(platform.getCoopCounts()).append("单...");
		
		StringBuilder strRequest = new StringBuilder();
		strRequest.append("{")
		.append("\"touser\":\"").append(Configure.userid).append("\",")
		.append("\"toparty\":\"\",")
		.append("\"totag\":\"").append(conf.getPropertyParam("totag.report.label1")).append("\",")
		.append("\"msgtype\":\"news\",")
		.append("\"agentid\":\"").append(Configure.REPORT_AGENT_ID).append("\",")
		.append("\"news\":{")
		.append("     \"articles\": [")
		.append("          {")
		.append("               \"title\":\"携程-去哪儿 网约车项目日报\", ")
		.append("               \"description\":\"项目日报\", ")
		.append("               \"url\":\"http://kpxmc-uat.e-autofinance.net:7070/ewechat/report/ctrip30?date="+DateUtil.getCurrentDate(DateUtil.yyyyMMdd)+"\", ")
		.append("               \"picurl\":\"http://kpxmc-uat.e-autofinance.net:7070/ewechat/images/ctrip-title2.jpg\", ")
		.append("               \"btntxt\":\"更多\" ")
		.append("          },")
		.append("          {")
		.append("               \"title\":\"").append(abc.toString()).append("\", ")
		.append("               \"description\":\"").append("新审批通过：").append(platform.getAuthCounts()).append("单\n已放款起租：").append(platform.getRentCounts()).append("单").append("\", ")
		.append("               \"url\":\"http://kpxmc-uat.e-autofinance.net:7070/ewechat/report/ctrip30?date="+DateUtil.getCurrentDate(DateUtil.yyyyMMdd)+"\", ")
		//append("               \"picurl\":\"http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png\", ")
		.append("               \"btntxt\":\"更多\" ")
		.append("          },")
		.append("          {")
		.append("               \"title\":\"新采购的车型有"+platform2.getPurcCounts()+"款...\", ")
		.append("               \"description\":\"").append("新审批通过：").append(platform.getAuthCounts()).append("单\n已放款起租：").append(platform.getRentCounts()).append("单").append("\", ")
		.append("               \"url\":\"http://kpxmc-uat.e-autofinance.net:7070/ewechat/report/purchase?date="+DateUtil.getCurrentDate(DateUtil.yyyyMMdd)+"\", ")
		//append("               \"picurl\":\"http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png\", ")
		.append("               \"btntxt\":\"更多\" ")
		.append("          }")
		.append("      ]")
		.append("    }")
		.append("}");
		logger.info(strRequest.toString());
		
		try {
			String url = WeChatUtils.getSendNewsUrl(WeChatUtils.getAccessToken(APP_TYPE.REPORT));
			logger.info(url);
			
			String result = HttpHelper.doHttpPost(url, strRequest.toString());
			logger.info("响应结果："+result);
			
			SendTextResData resText = new SendTextResData();
			
			resText = (SendTextResData) JsonUtil.jsonToBean(result, resText.getClass());
			
			logger.info("响应结果："+resText);
			if(null != resText.getErrcode() && "42001".equals(resText.getErrcode())) {
				url = WeChatUtils.getSendNewsUrl(WeChatUtils.getAccessToken(APP_TYPE.REPORT, true));
				logger.info(url);
				
				result = HttpHelper.doHttpPost(url, strRequest.toString());
				logger.info("响应结果："+result);
			}
		} catch (Exception e) {
			logger.error("响应结果：" , e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param inputStream
	 * @param bufReader
	 */
	private void close(InputStream inputStream, BufferedReader bufReader) {
		try {
			if(null!=inputStream) inputStream.close();
			if(null!=bufReader)  bufReader.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}


	@Override
	public void pushNews(boolean mFlag) {
		String file1 = ConfigUtil.getInstance().getPropertyParam("push_file_addr") + DateUtil.getCurrentDate(DateUtil.yyyyMMdd) + "_PUSH_001.txt";
		String file2 = ConfigUtil.getInstance().getPropertyParam("push_file_addr") + DateUtil.getCurrentDate(DateUtil.yyyyMMdd) + "_PUSH_002.txt";
		if(mFlag) {
			moveFileToLocal(file1, file2);
		}
		
		Platform platform1 = new Platform();
		parseFormatText(file1, platform1);
		
		
		Platform platform2 = new Platform();
		parseFormatText2(file2, platform2);
		
		pushText1(platform1, platform2);
	}


	@Override
	public Platform getCtripR1(String strDate) {
		String file1 = ConfigUtil.getInstance().getPropertyParam("push_file_addr") + strDate + "_PUSH_001.txt";
		logger.info("查找文件"+file1);
		Platform platform = new Platform();
		parseFormatText(file1, platform);
		return platform;
	}


	@Override
	public Platform getCtripR2(String strDate) {
		String file2 = ConfigUtil.getInstance().getPropertyParam("push_file_addr") + strDate + "_PUSH_002.txt";
		logger.info("查找文件"+file2);
		Platform platform = new Platform();
		parseFormatText2(file2, platform);
		return platform;
	}


	@Override
	public void createWeeklyReport() {
		KPBusinessReport businessReport = new KPBusinessReport();
		businessReport.setReport_date(DateUtil.date2Str(DateUtil.getDate(), DateUtil.yyyyMMdd));
		businessReport.setBranchCompany(getBranchCompanyList());
		
		businessReportDao.createKPBusinessReport(businessReport);
	}
	
	
	/**
	 *  分公司列表
	 * @return
	 */
	private List<BranchCompany> getBranchCompanyList() {
		List<BranchCompany> branchCompanyList = new ArrayList<BranchCompany>();
		
		BranchCompany branchCompany = new BranchCompany();
		branchCompany.setBranchName("郑州分公司");
		branchCompany.setBusinessTeam(getBusinessTeamList("201"));
		branchCompanyList.add(branchCompany);
		
		return branchCompanyList;
	}
	
	
	/**
	 *  销售小组清单
	 * @return
	 */
	private List<BusinessTeam> getBusinessTeamList(String parentTeamId) {
		List<BusinessTeam> businessTeamList = crmBpMasterDao.findBusinessTeamByPositionId(parentTeamId);
		for(BusinessTeam businessTeam : businessTeamList) {
			List<BusinessMember> businessMemberList = crmBpMasterDao.findBusinessMemeberByPositionId(businessTeam.getTeamId());
			for(BusinessMember businessMember : businessMemberList ) {
				setBusinessMemberReport(businessMember);
			}
			businessTeam.setBusinessMember(businessMemberList);
			businessTeam.setMemberNum(businessMemberList.size());
		}
		return businessTeamList;
	}
	
	
	/**
	 * 
	 * @param businessMember
	 */
	private void setBusinessMemberReport(BusinessMember businessMember) {
		String userId = businessMember.getUserId();
		int fallowCustomers = crmBpMasterDao.getAllCrmBpMasterCount(userId);
		businessMember.setFallowCustomers(fallowCustomers);
		
		int lastWeekAddCustomers = crmBpMasterDao.getLastWeekCrmBpMasterCount(userId);
		businessMember.setLastWeekAddCustomers(lastWeekAddCustomers);
		
		int lastWeekRentCustomers = crmBpMasterDao.getLaskWeekRentCrmBpMasterCount(userId);
		businessMember.setLastWeekRentCustomers(lastWeekRentCustomers);
		
		int customerA = crmBpMasterDao.getTypeACrmBpMasterCount(userId);
		businessMember.setCustomerA(customerA);
		
		int customerB = crmBpMasterDao.getTypeBCrmBpMasterCount(userId);
		businessMember.setCustomerB(customerB);
		
		int lastWeekCallbackCustomers = crmBpMasterDao.getLastWeekCallBackCrmBpMasterCount(userId);
		businessMember.setLastWeekCallbackCustomers(lastWeekCallbackCustomers);
		
		int callbackCustomers = crmBpMasterDao.getAllCallBackCrmBpMasterCount(userId);
		businessMember.setCallbackCustomers(callbackCustomers);
		
		businessMember.setUncallback3Customers(0);
		businessMember.setUncallback7Customers(0);
		businessMember.setUncallback15Customers(0);
	}


	@Override
	public void pushBusinessTeamReport() {
		// TODO Auto-generated method stub
		
	}

 
	@Override
	public BusinessTeam findBusinessTeamReportMsg(String date, String branchCompanyName, String teamID) throws Exception {
		List<KPBusinessReport> kpBusinessList = businessReportDao.findReportByDate(date);
		for(KPBusinessReport kpBusinessCR : kpBusinessList) {
			System.out.println(kpBusinessCR.getBranchCompany());
		}
		
		KPBusinessReport kpBusiness = kpBusinessList.get(0);
		List<BranchCompany> branchCompanyList = kpBusiness.getBranchCompany();
		for(BranchCompany branchCompany : branchCompanyList) {
			if(branchCompany.getBranchName().equals(branchCompanyName)) {
				List<BusinessTeam> businessTeamList = branchCompany.getBusinessTeam();
				for(BusinessTeam businessTeam : businessTeamList) {
					if(businessTeam.getTeamId().equals(teamID)) {
						return businessTeam;
					}
				}
			}
		}
		
		return null;
	}


	@Override
	public void findBusinessPersonalReportMsg() {
		// TODO Auto-generated method stub
		
	}

}
