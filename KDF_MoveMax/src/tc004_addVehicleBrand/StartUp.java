package tc004_addVehicleBrand;

import java.text.DecimalFormat;

import kdf.ManageExcel;

public class StartUp {
	public static String vUrl, vVbrandTh, vVbrandEn, vVbrandDetail, vAlert;
	public static String vResult, vError, vflag, vExpectedResult, vActualResult;
	public static String xTDdata[][];
	public static int passes, fails;
	public static double priority;

	public void main() throws Exception {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("0.00");

		String xTSdata[][];
		String xTCdata[][];
		String vKeyword, vIP1, vIP2, vIP3;
		String xlPath_tc = "D:/eclipse-workspace-tester/KDF_MoveMax/Excel/TCResult/TC004_Result.xlsx";
		String xlPath_ts = "D:/eclipse-workspace-tester/KDF_MoveMax/Excel/TSResult/TS004_Result.xlsx";
		String xlPath_td = "D:/eclipse-workspace-tester/KDF_MoveMax/Excel/TDResult/TD004_Result.xlsx";
		String xlPath = "D:/eclipse-workspace-tester/KDF_MoveMax/Excel/TC004_AddVehicleBrand.xlsx";

		Driver myDriver = new Driver();

		ManageExcel kdf = new ManageExcel();
		xTCdata = kdf.xlRead(xlPath, 0);
		xTSdata = kdf.xlRead(xlPath, 1);
		xTDdata = kdf.xlRead(xlPath, 2);

		for (int i = 1; i < xTCdata.length; i++) {
			if (xTCdata[i][3].equals("Y")) {
				vflag = "Pass";
				for (int k = 1; k < xTDdata.length; k++) {
					if (xTDdata[k][1].equals("Y")) {
						myDriver.getData(k);

						for (int j = 1; j < xTSdata.length; j++) {
							if (xTCdata[i][1].equals(xTSdata[j][0])) {
								vKeyword = xTSdata[j][4];
								vIP1 = xTSdata[j][5];
								vIP2 = xTSdata[j][6];
								vIP3 = xTSdata[j][8];
								System.out.println("---" + vKeyword + "````" + vIP1 + "````" + vIP2);
								vResult = "Pass";
								vError = "No Error";

								Thread.sleep(1000);
								vResult = myDriver.keyword_executor(vKeyword, vIP1, vIP2, vIP3, k);

								if (!vError.equals("No Error")) {
									vflag = "Fail";
									myDriver.getUtility().saveScreen(k);
									xTDdata[k][10] = vResult;
									xTDdata[k][11] = vError;
								}
								xTSdata[j][8] = vResult;
							}
							if (xTDdata[k][9].equals(xTDdata[k][8])) {
								vResult = "Pass";
								StartUp.xTDdata[k][11] = StartUp.vError;
							} else {
								vResult = "Fail";
								StartUp.xTDdata[k][11] = "Mismatch alert";
							}
							StartUp.xTDdata[k][10] = vResult;
						}
						System.out.println(StartUp.xTDdata[k][0]);
						System.out.println("Expected = " + StartUp.xTDdata[k][8]);
						System.out.println("Actual = " + StartUp.xTDdata[k][9]);
						System.out.println("Result = " + StartUp.xTDdata[k][10]);
					}
					if (xTDdata[k][10] == "Fail") {
						fails += 1;
					} else if (xTDdata[k][10] == "Pass") {
						passes += 1;
					}
				}
				if (fails != 0) {
					priority = 100.00 * (passes * 1.0 / (fails + passes));
					xTCdata[i][5] = passes + "";
					xTCdata[i][6] = fails + "";
					xTCdata[i][7] = (int) priority + "%";
					if (xTCdata[i][4].equals("High")) {
						if (priority < 95) {
							xTCdata[i][8] = "Fail";
						} else {
							xTCdata[i][8] = vflag;
						}
					} else if (xTCdata[i][4].equals("Medium")) {
						if (priority < 97) {
							xTCdata[i][8] = "Fail";
						} else {
							xTCdata[i][8] = vflag;
						}
					} else if (xTCdata[i][4].equals("Low")) {
						if (priority < 100) {
							xTCdata[i][8] = "Fail";
						} else {
							xTCdata[i][8] = vflag;
						}
					}
				} else if (passes == 0) {
					priority = 0;
					xTCdata[i][5] = passes + "";
					xTCdata[i][6] = fails + "";
					xTCdata[i][7] = (int) priority + "%";
					xTCdata[i][8] = "Fail";
				} else {
					priority = 100;
					xTCdata[i][5] = passes + "";
					xTCdata[i][6] = fails + "";
					xTCdata[i][7] = (int) priority + "%";
					xTCdata[i][8] = vflag;
				}
			}
			kdf.xlWrite(xlPath_tc, xTCdata, xTCdata.length, xTCdata[1].length);
			kdf.xlWrite(xlPath_ts, xTSdata, xTSdata.length, xTSdata[1].length);
			kdf.xlWrite(xlPath_td, xTDdata, xTDdata.length, xTDdata[1].length);
		}
		System.out.println("---done---");
	}
}
