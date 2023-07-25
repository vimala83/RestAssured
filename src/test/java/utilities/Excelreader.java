package utilities;

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Iterator;

	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.CellType;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.util.NumberToTextConverter;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class Excelreader {

		// Create a Array to save data
		ArrayList<String> arrData = new ArrayList<String>();

		static Config config = new Config();
		String DDexcelSheet = Config.EXCEL;

		// Java method to read file
		public ArrayList<String> dataDriven(String sheetName, String testCaseName) throws IOException {
			FileInputStream fis = new FileInputStream(DDexcelSheet);
            //System.out.println("Break 1");
			// Create a Excel Workbook
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int sheets = workbook.getNumberOfSheets();
			//System.out.println("Break 2 "+sheets);
			for (int i = 0; i < sheets; i++) {
				if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
					{
						//System.out.println("Break 3 "+sheetName);
						XSSFSheet sheet = workbook.getSheetAt(i);
						// identify Testcase by scanning 1st row
						Iterator<Row> rows = sheet.iterator();
						Row firstrow = rows.next();

						Iterator<Cell> ce = firstrow.cellIterator();
						int k = 0;
						int column = 0;
						while (ce.hasNext()) {
							Cell values = ce.next();

							if (values.getStringCellValue().equalsIgnoreCase("Testcase")) {
								column = k;
							}
							k++;
						}
						    //System.out.println("TestCase at column index :" + column);
						// once column is identify then scan entire column to Register testcase
					   while (rows.hasNext()) {
						  // System.out.println("Break 4 "+testCaseName);
							Row r = rows.next();
							if (r.getCell(column)==null) continue;
							if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
								// grab register testcase , pull all the data
								Iterator<Cell> cv = r.cellIterator();
								while (cv.hasNext()) {
									Cell c = cv.next();
									if (c.getCellType() == CellType.STRING) {
								
										arrData.add(c.getStringCellValue());
									} else 
									{
										
									arrData.add(NumberToTextConverter.toText(c.getNumericCellValue()));
									}
								}
							}
						}
					}
		     	}
			}
			workbook.close();
			return arrData;
		}

}
