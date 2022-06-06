package kdf;

public class StartUp {

	public static String vUrl, vAge, vGender, vHeight, vWeight;
	public static String vResult, vError, vflag;
	public static String xTDdata[][];

	public static void main(String[] args) throws Exception {

		String xTSdata[][];
		String xTCdata[][];
		String vKeyword, vIP1, vIP2;

		String xlPath_tc = "D:/eclipse-workspace-tester/KDF_BMI/TC_Res12.xlsx";
		String xlPath_ts = "D:/eclipse-workspace-tester/KDF_BMI/TS_Res12.xlsx";
		String xlPath_td = "D:/eclipse-workspace-tester/KDF_BMI/TD_Res12.xlsx";
		String xlPath = "D:/eclipse-workspace-tester/KDF_BMI/SeleniumData_BMI.xlsx";

		Driver myDriver = new Driver();

		ManageExcel kdf = new ManageExcel();
		xTCdata = kdf.xlRead(xlPath, 0);
		xTSdata = kdf.xlRead(xlPath, 1);
		xTDdata = kdf.xlRead(xlPath, 2);

		for (int i = 1; i < xTCdata.length; i++) {
			if (xTCdata[i][4].equals("Y")) {
				vflag = "Pass";

				for (int k = 1; k < xTDdata.length; k++) {
					if (xTDdata[k][1].equals("Y")) {
						myDriver.getData(k);

						for (int j = 1; j < xTSdata.length; j++) {
							if (xTCdata[i][1].equals(xTSdata[j][0])) {
								vKeyword = xTSdata[j][4];
								vIP1 = xTSdata[j][5];
								vIP2 = xTSdata[j][6];
								System.out.println("---" + vKeyword + "````" + vIP1 + "````" + vIP2);
								vResult = "Pass";
								vError = "No Error";

								vResult = myDriver.keyword_executor(vKeyword, vIP1, vIP2);

								if (!vError.equals("No Error")) {
									vflag = "Fail";
									myDriver.getUtility().saveScreen(i);
									xTSdata[j][8] = vResult;
									xTDdata[k][9] = vResult;
									xTDdata[k][10] = vError;
								}
							}
						}
						xTCdata[i][5] = vflag;
					}
				}
				kdf.xlWrite(xlPath_tc, xTCdata, xTCdata.length, xTCdata[1].length);
				kdf.xlWrite(xlPath_ts, xTSdata, xTSdata.length, xTSdata[1].length);
				kdf.xlWrite(xlPath_td, xTDdata, xTDdata.length, xTDdata[1].length);
			}
		}
	}
}
