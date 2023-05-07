import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
public class ExcelReader {

    private final XSSFSheet sheet;

    ExcelReader(String file) throws IOException{
        FileInputStream fileInput = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
        this.sheet = workbook.getSheetAt(0);
    }

    void readExcel(){
        Iterator<Row> iterator = this.sheet.iterator();

        Main.model.setRowCount(0);

        while(iterator.hasNext()){
            XSSFRow row = (XSSFRow) iterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            String[] rowArr = new String[13];
            int i = 0;

            while(cellIterator.hasNext()){
                XSSFCell cell = (XSSFCell) cellIterator.next();

                switch(cell.getCellType()){
                    case STRING -> rowArr[i] = cell.getStringCellValue();
                }
                i++;
            }
            Main.model.addRow(rowArr);
        }
    }
}
