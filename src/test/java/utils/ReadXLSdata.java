package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadXLSdata {

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {

		ReadXLSdata red = new ReadXLSdata();
		red.getData("Sheet1");

	}

	public String[][] getData(String excelSheetName)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		File f = new File(
				System.getProperty("D:\\Automation\\BQAutomationfrmaework\\src\\test\\resources\\testdata\\c.xlsx"));

		FileInputStream fs = new FileInputStream(f);
		Workbook wb = WorkbookFactory.create(fs);
		Sheet sheetName = wb.getSheet(excelSheetName);

		int totalRows = sheetName.getLastRowNum();
		System.out.println(totalRows);
		Row rowCells = sheetName.getRow(0);
		int totalCols = rowCells.getLastCellNum();
		System.out.println(totalCols);

		DataFormatter format = new DataFormatter();
		String testData[][] = new String[totalRows][totalCols];

		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j <= totalCols; j++) {
				testData[i - 1][j] = format.formatCellValue(sheetName.getRow(i).getCell(j));
				System.out.println(testData[i - 1][j]);
			}

		}
		return testData;
	}
}
