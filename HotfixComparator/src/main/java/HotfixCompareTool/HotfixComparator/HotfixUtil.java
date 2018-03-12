/**
 * @author damas
 *
 * 
 */
package HotfixCompareTool.HotfixComparator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class HotfixUtil {
	
	public static ArrayList createNecessaryHotfixObjects(File aEnv1, File aEnv2,ArrayList listofHotfixSet,String prpcVersion) throws IOException {
		
		fileReaderAndHfixObjectCreator(aEnv1,listofHotfixSet,prpcVersion);
		fileReaderAndHfixObjectCreator(aEnv2,listofHotfixSet,prpcVersion);
		return listofHotfixSet;
	}

	private static ArrayList fileReaderAndHfixObjectCreator(File env,ArrayList listofHotfixSet,String prpcVersion) throws IOException{
		FileReader fr = new FileReader(env);
		BufferedReader br = new BufferedReader(fr);
		TreeSet<Hotfix> hotfixSet = new TreeSet<Hotfix>(new HotfixComparator());
		String temp[] = getFromPropertiesFile(prpcVersion); // get the column level details from the properties file.
		String line = br.readLine();
		line=br.readLine();// to omit the header.
		while(line !=null){
			String[] strArray = line.split(",",-1);
			int indices [] = evaluateTheIndices(strArray,temp);
			Hotfix newHotfixObj = null;
			if(indices[5]>0){ // indices[5] has the descriptionDelta, (if "," exists in Hotfix-description), handling that case here.
				String desc = strArray[indices[1]-1];
				for(int i=1;i<=indices[5];i++){
					desc = desc + strArray[indices[1]-1+i];
				}
				newHotfixObj = new Hotfix(strArray[indices[0]-1],desc,strArray[indices[2]-1],strArray[indices[3]-1],strArray[indices[4]-1]);
			}else{
				 newHotfixObj = new Hotfix(strArray[indices[0]-1],strArray[indices[1]-1],strArray[indices[2]-1],strArray[indices[3]-1],strArray[indices[4]-1]);
			}
			hotfixSet.add(newHotfixObj);
			line=br.readLine();
		}
		listofHotfixSet.add(hotfixSet);
		br.close();
		fr.close();
		return listofHotfixSet;
	}
	
	/*
	 * evaluateTheIndices is needed when the Description of hotfix has "," in it then 
	 * there is always a danger for losing important data like the installation status
	 * , hot-fix state, etc, as the "," will be counted in for the split(",") logic we 
	 *  used to split the csv.
	 *  temp[0] has Total Columns , 
	 *	temp[1] has Index of Hotfix ID , 
	 *	temp[2] has Index of Hotfix Description ,
	 *	temp[3] has Index of HotfixState , 
	 *	temp[4] has Index of HotfixStatus ,
	 *	temp[5] has Index of Installation status 
	 */
	private static int[] evaluateTheIndices(String strArray[],String temp[]) throws IOException{ 
		int indices [] = new int[10];
		indices[0] = Integer.parseInt(temp[1]);
		indices[1] = Integer.parseInt(temp[2]);
		if(strArray.length == Integer.parseInt(temp[0])){
			indices[2] = Integer.parseInt(temp[3]);
			indices[3] = Integer.parseInt(temp[4]);
			indices[4] = Integer.parseInt(temp[5]);
		}else{
			int descriptionDelta = strArray.length - Integer.parseInt(temp[0]);
			indices[2] = Integer.parseInt(temp[3])+descriptionDelta;
			indices[3] = Integer.parseInt(temp[4])+descriptionDelta;
			indices[4] = Integer.parseInt(temp[5])+descriptionDelta;
			indices[5] = descriptionDelta;
		}
		return indices ;
	}
	
	/*
	 * getFromPropertiesFile accepts the prpcVersion from the user and looks 
	 * for column related information present in the PRPCVersion.properties file.
	*/
	private static String[] getFromPropertiesFile(String prpcVersion) throws IOException{
		InputStream is = HotfixUtil.class.getResourceAsStream("PRPCVersion.properties"); // Move this out of every iteration.
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		br.readLine(); // omit the first line.
		String line = br.readLine();
		String[] temp = null;
		while(line!=null){
			temp = line.split("=");
			if(temp[0].equals(prpcVersion)){
				break;
			}
			line = br.readLine(); // go to next line..
		}
		temp[1]=temp[1].trim();
		temp = temp[1].split(",");
		br.close();
		is.close();
		return temp;
	}
	
	
}
