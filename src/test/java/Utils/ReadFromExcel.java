package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromExcel {

    // Path to your Excel file
    private static final String filePath =
            System.getProperty("user.dir") + "/src/test/java/TestData/data.xlsx";

    private static Workbook workbook;

    // Load workbook ONCE
    static {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Excel file: " + filePath, e);
        }
    }

    /**
     * Reads a sheet and returns an Object[][] for TestNG.
     */
    public static Object[][] getSheetData(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null)
            throw new RuntimeException("Sheet not found: " + sheetName);

        List<Object[]> rows = new ArrayList<>();

        boolean headerSkipped = false;

        for (Row row : sheet) {
            if (!headerSkipped) {
                headerSkipped = true;
                continue;
            }

            List<String> cells = new ArrayList<>();

            for (int i = 0; i < row.getLastCellNum(); i++) {
                cells.add(cellValue(row.getCell(i)));
            }

            rows.add(cells.toArray());
        }

        return rows.toArray(new Object[0][]);
    }

    /**
     * Convert Excel cell → Java String (Java 8 compatible)
     */
    private static String cellValue(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                double d = cell.getNumericCellValue();
                if (d == Math.rint(d)) {
                    return String.valueOf((long) d); // integer
                }
                return String.valueOf(d); // decimal

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            default:
                return "";
        }
    }
}
