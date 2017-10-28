package realtime.asg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class CountJavaIssues {
	
	static CountJavaFile j = new CountJavaFile();
	static LinkedList n = j.name;
	
	public void listIssues(String path) throws FileNotFoundException, IOException {
        int countIssues = 0;
		for (int i=0; i<n.size(); i++){
            String dir = n.get(i).toString();
            BufferedReader br = new BufferedReader(new FileReader(dir));
            String line;
            while ((line = br.readLine()) != null){
                if (line.contains("public static void main")){
                    countIssues++;
                }
            }
        }
	System.out.println("Total Issues : "+countIssues);
	}
}
