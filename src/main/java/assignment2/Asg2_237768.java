package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import assignment2.jKeyword;
import assignment2.jLOC;
import assignment2.ExcelFile;
import assignment2.jComment;

public class Asg2_237768 {
	final static File FILE = new File("C:\\Users\\Lim\\Documents\\UUM\\Sem 5\\Real Time Programming\\Real Time Example\\src\\Asg2\\MyThread1.java");
    final static String FILE_NAME = "Result.xlsx";
            
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        //scan details from comment
        jComment scan = new jComment();
        String sem = scan.getSem();
        String course = scan.getCourse();
        String grp = scan.getGroup();
        String task = scan.getTask();
        String name = scan.getName();
        String matric = scan.getMatric();
        
        //count lines of codes
        jLOC count = new jLOC();
        int LOC = count.countLineNumber();
        int blankLines = count.countBlankLineNumber();
        int commentLines = count.countCommentLineNumber();
        int actualLOC = LOC - blankLines - commentLines;
        String ttlLines = Integer.toString(LOC);
        String blank = Integer.toString(blankLines);
        String comment = Integer.toString(commentLines);
        String act = Integer.toString(actualLOC);
        
        //count number of keywords
        jKeyword key = new jKeyword();
        Map<String,Integer> map = key.countKeywords();
        
        //count total values
        int counter = key.noKeywords();
        int totalVal = actualLOC + counter;
        String ttlVal = Integer.toString(totalVal);
        
        //display output in Excel
        ExcelFile excel = new ExcelFile(FILE_NAME);
        excel.Details(sem, course, grp, task);
        excel.Data(map, name, matric, ttlLines, blank, comment, act, ttlVal);
        excel.Keyword(map);
        excel.processDataObject().create().write();
    }    
}
