/**
 * @author damas
 *
 * 
 */
package HotfixCompareTool.HotfixComparator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class HotfixUtil {
	
	public static ArrayList createNecessaryHotfixObjects(File aEnv1, File aEnv2,ArrayList listofHotfixSet) throws IOException {
		
		fileReaderAndHfixObjectCreator(aEnv1,listofHotfixSet);
		fileReaderAndHfixObjectCreator(aEnv2,listofHotfixSet);
		return listofHotfixSet;
	}

	private static ArrayList fileReaderAndHfixObjectCreator(File env,ArrayList listofHotfixSet) throws IOException{
		FileReader fr = new FileReader(env);
		BufferedReader br = new BufferedReader(fr);
		TreeSet<Hotfix> hotfixSet = new TreeSet<Hotfix>(new HotfixComparator());
		String line = br.readLine();
		while(line !=null){
			String[] strArray = line.split(",");
			Hotfix newHotfixObj = new Hotfix(strArray[0],strArray[1],strArray[3],strArray[4]);
			//System.out.println(newHotfixObj);
			hotfixSet.add(newHotfixObj);
			line=br.readLine();
		}
		listofHotfixSet.add(hotfixSet);
		br.close();
		fr.close();
		return listofHotfixSet;
	}
	
	
}
