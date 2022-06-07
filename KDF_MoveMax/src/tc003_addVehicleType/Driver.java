package tc003_addVehicleType;

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
			if (vKeyword.equals("radio_select")) {
				util.radio_select(getIP(vIP1));
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
				StartUp.xTDdata[k][14] = StartUp.vActualResult;
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
		if (vIP.equals("vVtypeId")) {
			vIP = StartUp.vVtypeId;
		}
		if (vIP.equals("vVtypeTh")) {
			vIP = StartUp.vVtypeTh;
		}
		if (vIP.equals("vVtypeEn")) {
			vIP = StartUp.vVtypeEn;
		}
		if (vIP.equals("vVtypeDetail")) {
			vIP = StartUp.vVtypeDetail;
		}
		if (vIP.equals("vVtypeIcon")) {
			vIP = StartUp.vVtypeIcon;
		}
		if (vIP.equals("vMaxWeight")) {
			vIP = StartUp.vMaxWeight;
		}
		if (vIP.equals("vMaxCap")) {
			vIP = StartUp.vMaxCap;
		}
		if (vIP.equals("vMaxDistance")) {
			vIP = StartUp.vMaxDistance;
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
		StartUp.vVtypeId = StartUp.xTDdata[k][4];
		StartUp.vVtypeTh = StartUp.xTDdata[k][5];
		StartUp.vVtypeEn = StartUp.xTDdata[k][6];
		StartUp.vVtypeDetail = StartUp.xTDdata[k][7];
		StartUp.vVtypeIcon = StartUp.xTDdata[k][8];
		StartUp.vMaxWeight = StartUp.xTDdata[k][9];
		StartUp.vMaxCap = StartUp.xTDdata[k][10];
		StartUp.vMaxDistance = StartUp.xTDdata[k][11];
		StartUp.vAlert = StartUp.xTDdata[k][12];
		StartUp.vExpectedResult = StartUp.xTDdata[k][13];
	}
}