package testcase;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFToExcel {
	
	
    public static void main(String[] args) {
        String pdfPath = "‪D:\\empb\\DraftQuote.pdf";
        String excelPath = "‪D:\\empb\\Book1.xlsx";
        String worksheetName = "Sheet1";
        int startRow = 1; // Assuming data starts from row 2
        int columnNumber = 1; // Column number (A=1, B=2, C=3, ...)

        try {
            // Extract data from PDF column
            List<String> data = extractDataFromPDF(pdfPath, columnNumber);

            // Update Excel with the extracted data
            updateExcelWithValues(excelPath, worksheetName, data, startRow, columnNumber);

            System.out.println("Data extracted and updated in Excel successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> extractDataFromPDF(String pdfPath, int columnNumber) throws IOException {
        PDDocument document = PDDocument.load(new File(pdfPath));
        PDFTextStripper textStripper = new PDFTextStripper();

        // Set the column region in PDF (adjust coordinates as needed)
        int left = 100; // Left x-coordinate of the column region
        int right = 200; // Right x-coordinate of the column region

        // Set the page(s) to extract data from (e.g., 1-5)
        textStripper.setStartPage(1);
        textStripper.setEndPage(5);

        // Extract the text from the specified column region
        String text = textStripper.getText(document);

        document.close();

        // Split the text into lines and extract the desired column
        String[] lines = text.split("\\r?\\n");
        List<String> data = new ArrayList<>();
        for (String line : lines) {
            String[] columns = line.split("\\s+"); // Split by whitespace
            if (columns.length >= columnNumber) {
                String value = columns[columnNumber - 1].trim();
                data.add(value);
            }
        }

        return data;
    }

    private static void updateExcelWithValues(String excelPath, String worksheetName, List<String> data,
                                              int startRow, int columnNumber) throws IOException {
        FileInputStream inputStream = new FileInputStream(excelPath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(worksheetName);

        int row = startRow;
        for (String value : data) {
            Row currentRow = sheet.getRow(row);
            if (currentRow == null) {
                currentRow = sheet.createRow(row);
            }

            Cell cell = currentRow.getCell(columnNumber - 1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellValue(value);

            row++;
        }

        inputStream.close();

        FileOutputStream outputStream = new FileOutputStream(excelPath);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
