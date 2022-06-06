package kdf;

import org.openqa.selenium.NoSuchElementException;

public class Driver {

	private Utility util = new Utility();

	public Utility getUtility() {
		return util;
	}

	public String keyword_executor(String vKeyword, String vIP1, String vIP2) throws Exception {

		String flag = "false";

		try {
			if (vKeyword.equals("browser_open")) {
				util.browser_open(getIP(vIP1));
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("radio_select")) {
				util.radio_select(getIP(vIP1), getIP(vIP2));
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("checkbox_set")) {
				util.checkbox_set(getIP(vIP1), getIP(vIP2));
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
			if (vKeyword.equals("button_click")) {
				util.button_click(getIP(vIP1));
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("click_link")) {
				util.click_link(getIP(vIP1));
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("dialog_click")) {
				boolean works = util.dialog_click();
				if (works) {
					flag = "True";
					return "Pass";
				}
			}
			if (vKeyword.equals("browser_close")) {
				util.browser_close();
				flag = "True";
				return "Pass";
			}
			if (vKeyword.equals("actual_result")) {
				util.browser_close();
				flag = "True";
				return "Pass";
			}

			if (flag.equals("false")) {
				System.out.println("Keyword is missing " + vKeyword);
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
		if (vIP.equals("vAge")) {
			vIP = StartUp.vAge;
		}
		if (vIP.equals("vGender")) {
			vIP = StartUp.vGender;
		}
		if (vIP.equals("vHeight")) {
			vIP = StartUp.vHeight;
		}
		if (vIP.equals("vWeight")) {
			vIP = StartUp.vWeight;
		}
		return vIP;
	}

	public void getData(int k) {
		StartUp.vUrl = StartUp.xTDdata[k][2];
		StartUp.vAge = StartUp.xTDdata[k][4];
		StartUp.vGender = StartUp.xTDdata[k][5];
		StartUp.vHeight = StartUp.xTDdata[k][6];
		StartUp.vWeight = StartUp.xTDdata[k][7];
	}
}