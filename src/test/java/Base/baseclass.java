package Base;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.common.value.qual.StaticallyExecutable;

import io.restassured.response.Response;
import utilities.Excelreader;

public class baseclass {
	public static final Logger logger = LogManager.getLogger(baseclass.class.getName());
	Excelreader ed = new Excelreader();

	public static String randomestring()

	{
		String generateinvalidID = RandomStringUtils.randomNumeric(3);
		return (generateinvalidID);
	}

	public static String excelDataValue(String sheetName, String testCase) throws IOException {
		Excelreader ed = new Excelreader();
		ArrayList<String> data;
		data = ed.dataDriven(sheetName, testCase);
		// array data data driven method return column name[index0] and value[index1]
		System.out.println(" data.get(0) " + data.get(0) + "data.get(1) " + data.get(1));
		//read column value from index1
		String value = data.get(1);
		return value;
	}
	
		

}
	
