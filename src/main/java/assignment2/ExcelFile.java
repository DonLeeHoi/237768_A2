package assignment2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import assignment2.ExcelFile;

public class ExcelFile {
	XSSFWorkbook workbook;
    XSSFSheet sheet;
    
    String fileName;
    String header[][];
    String [] key;
    String [] value;
    String keyword[][];
    String data[][];
    String[][] information;
    
    public ExcelFile(String file) {
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("Line of Codes");
        this.fileName = file;
    }
    
    public ExcelFile Details(String semester, String course, String group, String task) {

        String temporaryHeader[][] = {
            {"Semester", semester},
            {"Course", course},
            {"Group", group},
            {"Task", task},
            {}
        };
        
        header = temporaryHeader;
        return this;
    }
    
    public ExcelFile Keyword(Map<String, Integer> map) {
        key = new String [map.size()];
        int a = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            key[a] = entry.getKey();
            a++;
        }
        
        String keywordToDisplay[] = new String[key.length + 6];
        keywordToDisplay[0] = "Matrix";
        keywordToDisplay[1] = "LOC";
        keywordToDisplay[2] = "Blank";
        keywordToDisplay[3] = "Comment";
        keywordToDisplay[4] = "Actual LOC";

        for (int i=5; i<(keywordToDisplay.length-1); i++) {
            keywordToDisplay[i] = key[(i-5)];
        }
        keywordToDisplay[5 + key.length] = "Total";

        String keywords[][] = {
            {"", "", "", "", "", "Java keywords"},
            keywordToDisplay
        };

        this.keyword = keywords;
        return this;
    }
    
    public ExcelFile Data(Map<String, Integer> map, String name, String matric, 
            String loc, String blank, String comment, String actLoc, String ttl){
        value = new String [map.size()];
        int a = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            value[a] = Integer.toString(entry.getValue());
            a++;
        }
        
        String [] list = new String [value.length + 6];
        list[0]=matric;
        list[1]=loc;
        list[2]=blank;
        list[3]=comment;
        list[4]=actLoc;
        
        for (int i=5; i<(list.length-1); i++) {
            list[i] = value[(i-5)];
        }
        list[5 + value.length] = ttl;
        
        String datalist[][] = {list};
        
        this.data = datalist;
        return this;
    }
    
    public ExcelFile processDataObject() {

        information = new String[header.length + keyword.length + data.length][];
        int i = 0;
        for (; i < header.length; i++) {
            information[i] = new String[header[i].length];
            for (int b = 0; b < header[i].length; b++) {
                information[i][b] = header[i][b];
            }
        }

        for (int b = 0; b < keyword.length; b++) {
            information[i] = keyword[b];
            i++;
        }

        for (int b = 0; b < data.length; b++) {
            information[i] = data[b];
            i++;
        }

        this.information = information;
        return this;
    }
        
    public ExcelFile create(){
        int rowNum = 0;
        for (Object[] attribute : information) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : attribute) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
        return this;
    }

    public ExcelFile write(){
        try {
            sheet.setDefaultColumnWidth(10);
            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

}
