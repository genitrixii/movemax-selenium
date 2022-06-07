package tc002_addDistributionCenter;

import org.openqa.selenium.NoSuchElementException;

public class Driver {
	public static String Alert1 = "";
	public static String Alert2 = "";
	private Utility util = new Utility();

	public Utility getUtility() {
		return util;
	}

	public String keyword_executor(String vKeyword, String vIP1, String vIP2, String vIP3, int k) throws Exception {
		String flag = "false";
		try {
			if (vKeyword.equals("browser_open")) {
				util.browser_open(getIP(vIP1));
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("maximizewin")) {
				util.maximizewin();
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("login")) {
				util.login();
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("list_select")) {
				util.list_select(getIP(vIP1), getIP(vIP2));
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("edit_input")) {
				util.edit_input(getIP(vIP1), getIP(vIP2));
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("file_input")) {
				util.file_input(getIP(vIP1), getIP(vIP2));
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("button_click")) {
				util.button_click(getIP(vIP1));
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("link_click")) {
				util.link_click(getIP(vIP1));
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("get_text")) {
				StartUp.vActualResult = util.get_text(getIP(vIP1), getIP(vIP3));
				StartUp.xTDdata[k][23] = StartUp.vActualResult;
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("browser_close")) {
				util.browser_close();
				flag = "True";
				return "Pass";
			}
			if (flag.equals("false")) {
				StartUp.vError = "Error";
				return "False-Keyword Missing";
			}
		} catch (NoSuchElementException e) {
			System.out.println("Error is " + e);
			StartUp.vError = String.valueOf(e);
			return "Fail";
		}
		return "Unknown Keyword";
	}

	public String getIP(String vIP) {
		if (vIP.equals("vUrl")) {
			vIP = StartUp.vUrl;
		}
		if (vIP.equals("vDCid")) {
			vIP = StartUp.vDCid;
		}
		if (vIP.equals("vDCth")) {
			vIP = StartUp.vDCth;
		}
		if (vIP.equals("vDCen")) {
			vIP = StartUp.vDCen;
		}
		if (vIP.equals("vOrganization")) {
			vIP = StartUp.vOrganization;
		}
		if (vIP.equals("vDCdetail")) {
			vIP = StartUp.vDCdetail;
		}
		if (vIP.equals("vAddress")) {
			vIP = StartUp.vAddress;
		}
		if (vIP.equals("vAddress2")) {
			vIP = StartUp.vAddress2;
		}
		if (vIP.equals("vLatitude")) {
			vIP = StartUp.vLatitude;
		}
		if (vIP.equals("vLongitude")) {
			vIP = StartUp.vLongitude;
		}
		if (vIP.equals("vOfficeTel")) {
			vIP = StartUp.vOfficeTel;
		}
		if (vIP.equals("vTel")) {
			vIP = StartUp.vTel;
		}
		if (vIP.equals("vFax")) {
			vIP = StartUp.vFax;
		}
		if (vIP.equals("vEmail")) {
			vIP = StartUp.vEmail;
		}
		if (vIP.equals("vLine")) {
			vIP = StartUp.vLine;
		}
		if (vIP.equals("vFacebook")) {
			vIP = StartUp.vFacebook;
		}
		if (vIP.equals("vWebsite")) {
			vIP = StartUp.vWebsite;
		}
		if (vIP.equals("vContact")) {
			vIP = StartUp.vContact;
		}
		if (vIP.equals("vAlert")) {
			vIP = StartUp.vAlert;
		}
		if (vIP.equals("vExpectedResult")) {
			vIP = StartUp.vExpectedResult;
		}
		return vIP;
	}

	public void getData(int k) {
		StartUp.vUrl = StartUp.xTDdata[k][2];
		StartUp.vDCid = StartUp.xTDdata[k][4];
		StartUp.vDCth = StartUp.xTDdata[k][5];
		StartUp.vDCen = StartUp.xTDdata[k][6];
		StartUp.vOrganization = StartUp.xTDdata[k][7];
		StartUp.vDCdetail = StartUp.xTDdata[k][8];
		StartUp.vAddress = StartUp.xTDdata[k][9];
		StartUp.vAddress2 = StartUp.xTDdata[k][10];
		StartUp.vLatitude = StartUp.xTDdata[k][11];
		StartUp.vLongitude = StartUp.xTDdata[k][12];
		StartUp.vOfficeTel = StartUp.xTDdata[k][13];
		StartUp.vTel = StartUp.xTDdata[k][14];
		StartUp.vFax = StartUp.xTDdata[k][15];
		StartUp.vEmail = StartUp.xTDdata[k][16];
		StartUp.vLine = StartUp.xTDdata[k][17];
		StartUp.vFacebook = StartUp.xTDdata[k][18];
		StartUp.vWebsite = StartUp.xTDdata[k][19];
		StartUp.vContact = StartUp.xTDdata[k][20];
		StartUp.vAlert = StartUp.xTDdata[k][21];
		StartUp.vExpectedResult = StartUp.xTDdata[k][22];
	}
}