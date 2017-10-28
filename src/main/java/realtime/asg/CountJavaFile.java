package realtime.asg;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;


public class CountJavaFile {
	
	public LinkedList name= new LinkedList();
	
	public void Count (String path){
		
		File folder = new File(path);
		
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith(".java");
		    }
		};
		
		File[] listOfFiles = folder.listFiles(filter);

		for (int i = 0; i < listOfFiles.length; i++) {
		    File file = listOfFiles[i];
		    name.add(file.getAbsolutePath());
		}
		System.out.println("Number Of Java Files : "+ listOfFiles.length);
	}
}
