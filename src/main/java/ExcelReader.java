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

            String[] rowArr = new String[10];
            int i = 0;

            while(cellIterator.hasNext()){
                XSSFCell cell = (XSSFCell) cellIterator.next();

                switch(cell.getCellType()){
                    case STRING -> rowArr[i] = cell.getStringCellValue();
                    case NUMERIC -> rowArr[i] = String.valueOf(cell.getNumericCellValue()).replace(".", ",");
                }
                i++;
            }
            Main.model.addRow(rowArr);
        }

        Main.model.removeRow(0);

        for(int j = 4; j <= 9; j++){
            for(int i = 1; i <= 71; i++){
                switch (j) {
                    case 4 -> {
                        switch (sheet.getRow(i).getCell(3).getStringCellValue().strip()) {
                            case "Pereiro" -> Main.somatAreas[0] += sheet.getRow(i).getCell(4).getNumericCellValue();
                            case "Irauçuba" -> Main.somatAreas[1] += sheet.getRow(i).getCell(4).getNumericCellValue();
                            case "Mauriti" -> Main.somatAreas[2] += sheet.getRow(i).getCell(4).getNumericCellValue();
                            case "Caucaia" -> Main.somatAreas[3] += sheet.getRow(i).getCell(4).getNumericCellValue();
                        }
                    }
                    case 5 -> {
                        if (sheet.getRow(i).getCell(5).getStringCellValue().strip().equals("Sim")) {
                            Main.qtdAreaArborizadaMaior50++;
                        } else {
                            Main.qtdAreaArborizadaMenor50++;
                        }
                    }
                    case 6 -> {
                        if (sheet.getRow(i).getCell(6).getStringCellValue().strip().equals("Sim")) {
                            Main.qtdAreaVegetPerturbadaMaior50++;
                        } else {
                            Main.qtdAreaVegetPerturbadaMenor50++;
                        }
                    }
                    case 7 -> {
                        if (sheet.getRow(i).getCell(7).getStringCellValue().strip().equals("Sim")) {
                            Main.qtdAreaVegetConservadaMaior50++;
                        } else {
                            Main.qtdAreaVegetConservadaMenor50++;
                        }
                    }
                    case 8 -> {
                        if (sheet.getRow(i).getCell(8).getStringCellValue().strip().equals("Sim")) {
                            Main.presencaFaunaNativa++;
                        } else {
                            Main.semPresencaFaunaNativa++;
                        }
                    }
                    case 9 -> {
                        if (sheet.getRow(i).getCell(9).getStringCellValue().strip().equals("Sim")) {
                            Main.mitigaIlhaCalor++;
                        } else {
                            Main.naoMitigaIlhaCalor++;
                        }
                    }
                }
            }
        }
    }
}
