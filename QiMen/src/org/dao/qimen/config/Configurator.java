package org.dao.qimen.config;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Configurator {
	//public static boolean ALL = false;  //全部显示隐藏
	private boolean SJ = false;   //十二神将
	private boolean JXGE = false;
	private boolean SGKY = false;
	private boolean JTXMS = false;
	private boolean WSXQ = false;
	private boolean XCHM = false;
	private boolean HUO = false;	
	private boolean MA = true;	
	//private boolean HEAD = true;  //是否显示隐藏头部日期信息
	//private boolean CALENDAR = true;  //是否显示时间面板
	
	private boolean YANG = true;    //阴盘还是阳盘，默认是阳盘
	private static boolean ZF = true;    //转盘还是飞盘，默认是转
	private static boolean RB = false;		//置闰还是拆补，默认是拆补法
	private static boolean TD = true;		//小值符随天还是地，默认随天
	private static boolean KG = true;		//永寄坤宫还是艮，默认是坤宫
//	private boolean TIP = true;		//关闭显示提示信息，默认关闭
	private int XMHW = 0;  //星门换位，0是复位，100是72变
	
//	private boolean TOOL = true;  //是否显示工具栏，默认显示
//	private boolean INPUT = false;  //是否显示高级面板，默认显示
	private boolean IO = true;  //显示内置还是自定义的提示信息、规则引擎、格局定制。默认为内置	

	private String[][] GUA = new String[10][2];  //九宫意象
	private String[][] WH = new String[10][2];   //五行意象
	private String[][] SHEN = new String[10][2]; //八神意象
	private String[][] XING = new String[10][2]; //九星意象
	private String[][] MEN = new String[10][2];	 //八门意象
	private String[][] GAN = new String[11][2];  //十天干意象
	private String[][] ZI = new String[13][2];   //十二地支意象
	private String[][] JXZS = new String[10][13];	//九星值时
	private String[][] SHENJ = new String[13][3];	//十二神将

	private final Logger logger = Logger.getLogger(Configurator.class);

	public Configurator () {
		
		logger.info("Loading Configurator...");

		Configurator configuration = new Configurator();
		
		configuration.init();
		configuration.loadConfiguration();
	}

	private void loadConfiguration () {
		logger.info("Initializing QiMen Configuration");

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = factory.newDocumentBuilder();

			if(!(new File("properties/chart.ini").exists())) {
				logger.error("Configuration Not Found!");
				return;				
			}

			logger.info("load qimen configuration");

			Document doc = parser.parse(new File("properties/chart.ini"));	
			Node element = doc.getDocumentElement();			
			NodeList child = element.getChildNodes();  
			for (int i = 0; i < child.getLength(); i++) {			
				Node node2 = child.item(i);
				String text = node2.getTextContent();
				if(text.trim().equals("")) continue;
				String key = node2.getNodeName();
				int val = Integer.valueOf(text); 								
				System.out.println(key+"="+val);
				if(key.equals("ma")) this.MA = val==1;				
				else if(key.equals("zf")) this.ZF = val==1;
				else if(key.equals("jxge")) this.JXGE = val==1;
				else if(key.equals("rb")) this.RB = val==0;
				else if(key.equals("sgky")) this.SGKY = val==1;
				else if(key.equals("td")) this.TD = val==1;
				else if(key.equals("wsxq")) this.WSXQ = val==1;
				else if(key.equals("kg")) this.KG = val==1;
				else if(key.equals("xchm")) this.XCHM = val==1;
//				else if(key.equals("tip")) this.TIP = val==1;
				else if(key.equals("jtxms")) this.JTXMS = val==1;
				else if(key.equals("xmhw")) this.XMHW = val==1? 0 : 1;
				else if(key.equals("huo")) this.HUO = val==1;
//				else if(key.equals("tool")) this.TOOL = val==1;
				else if(key.equals("sj")) this.SJ = val==1;
//				else if(key.equals("input")) this.INPUT = val==1;
//				else if(key.equals("head")) this..HEAD = val==1;
				else if(key.equals("io")) this.IO = val==1;
//				else if(key.equals("left")) this.LEFT = val==1 ? QiMen2.LEFTMAX : QiMen2.LEFTMIN;
//				else if(key.equals("calendar")) this.CALENDAR = val==1;
				else if(key.equals("yang")) this.YANG = val==1;
			}
		} catch (Exception e) {
			logger.error("Error Loading Configuration", e);
		}
	}
	
	private void init () {
		
		logger.info("Initialization...");
		
		GUA[1] = new String[]{"Kan",""};
		GUA[2] = new String[]{"Kun",""};
		GUA[3] = new String[]{"Zhen",""};
		GUA[4] = new String[]{"Xun",""};
		GUA[6] = new String[]{"Qian",""};
		GUA[7] = new String[]{"Dui",""};
		GUA[8] = new String[]{"Geng",""};
		GUA[9] = new String[]{"Li",""};
		
		SHEN[1] = new String[]{"Commander",""};
		SHEN[2] = new String[]{"Snake",""};
		SHEN[3] = new String[]{"Supreme Yin",""};
		SHEN[4] = new String[]{"Harmonies",""};
		SHEN[5] = new String[]{"Tiger",""};
		SHEN[6] = new String[]{"Warrior",""};
		SHEN[7] = new String[]{"Earth",""};
		SHEN[8] = new String[]{"Heaven",""};
		
		MEN[1] = new String[]{"Rest",""};
		MEN[2] = new String[]{"Death",""};
		MEN[3] = new String[]{"Injury",""};
		MEN[4] = new String[]{"Clumsy",""};
		MEN[5] = new String[]{"Center",""};
		MEN[6] = new String[]{"Open",""};
		MEN[7] = new String[]{"Shocking",""};
		MEN[8] = new String[]{"Birth",""};
		MEN[9] = new String[]{"Prospect",""};
		
		XING[1] = new String[]{"TianPeng",""};
		XING[2] = new String[]{"TianRui",""};
		XING[3] = new String[]{"TianChong",""};
		XING[4] = new String[]{"TianFu",""};
		XING[5] = new String[]{"TianQin",""};
		XING[6] = new String[]{"TianXin",""};
		XING[7] = new String[]{"TianZhu",""};
		XING[8] = new String[]{"TianRen",""};
		XING[9] = new String[]{"TianYin",""};
		
		GAN[1] = new String[]{"Jia",""};
		GAN[2] = new String[]{"Yi",""};
		GAN[3] = new String[]{"Bing",""};
		GAN[4] = new String[]{"Ding",""};
		GAN[5] = new String[]{"Wu",""};
		GAN[6] = new String[]{"Ji",""};
		GAN[7] = new String[]{"Geng",""};
		GAN[8] = new String[]{"Xin",""};
		GAN[9] = new String[]{"Ren",""};
		GAN[10] = new String[]{"Gui",""};
		
		ZI[1] = new String[]{"Zi",""};
		ZI[2] = new String[]{"Chou",""};
		ZI[3] = new String[]{"Yin",""};
		ZI[4] = new String[]{"Mou",""};
		ZI[5] = new String[]{"Chen",""};
		ZI[6] = new String[]{"Si",""};
		ZI[7] = new String[]{"Wu",""};
		ZI[8] = new String[]{"Wei",""};
		ZI[9] = new String[]{"Shen",""};
		ZI[10] = new String[]{"You",""};
		ZI[11] = new String[]{"Xu",""};
		ZI[12] = new String[]{"Hai",""};
	}

	public static boolean zf() {
		return ZF;
	}
	
	public static boolean td() {
		return TD;
	}
	
	public static boolean rb() {
		return RB;
	}
	
	public static boolean kg() {
		return KG;
	}
}
