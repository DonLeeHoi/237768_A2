package realtime.asg;

import realtime.asg.CountJavaFile;
import realtime.asg.CountJavaIssues;

public class Assignment1_237768 {

	static final String folder ="/Users/Lim/Documents/UUM/Sem 5/Real Time Programming/Real Time Example/src/Week_01";
	static final String file = folder+"/";
	 public static void main(String args[]) throws Exception {
		 CountJavaFile f = new CountJavaFile();
		 CountJavaIssues j = new CountJavaIssues();
		 f.Count(folder);
		 j.listIssues(file);
	 }
}
