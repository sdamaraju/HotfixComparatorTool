/**
 * @author damas
 *
 * 
 */
package HotfixCompareTool.HotfixComparator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class ResultGenerator {

	public String compareHotfixesAndGenerateResults(ArrayList listOfHotfixSet){
		//hard coding two read just 2 files. can iterate over the list for multiple files in Beta version.
		List uniqueHotfixList = new ArrayList<Hotfix>();
		ArrayList<Hotfix> HotfixInEnv1NotEnv2 = new ArrayList<Hotfix>();
		ArrayList<Hotfix> HotfixInEnv2NotEnv1 = new ArrayList<Hotfix>();
		Hotfix obj1 = null;
		Hotfix obj2 = null;
		TreeSet HotfixSet1 = (TreeSet) listOfHotfixSet.get(0);
		TreeSet HotfixSet2 = (TreeSet) listOfHotfixSet.get(1);
		if(HotfixSet1.size()==0 || HotfixSet2.size()==0){
			throw new RuntimeException("CSV empty");
		}
		Iterator<Hotfix> iter1 = HotfixSet1.iterator();
		Iterator<Hotfix> iter2 = HotfixSet2.iterator();
		obj1 = iter1.next();
		obj2 = iter2.next();
		while(iter1.hasNext() || iter2.hasNext() || (!iter1.hasNext() && !iter2.hasNext())){//
			 int a = obj1.getHotfixPK();
			 //System.out.print(a + " ");
			 int b = obj2.getHotfixPK();
			 //System.out.println(b);
			 if(a==b){
				 uniqueHotfixList.add(obj1);
				 if(iter1.hasNext()&&iter2.hasNext()){
					 obj1 = iter1.next();
					 obj2 = iter2.next();
					 continue;
				 }else if(!iter1.hasNext() && !iter2.hasNext()){
					 break;
				 }else if(!iter1.hasNext()){
					 //iter1null
					 while(iter2.hasNext()){
						HotfixInEnv2NotEnv1.add(iter2.next());
					 }
				 }else{
					 while(iter1.hasNext()){
						 HotfixInEnv1NotEnv2.add(iter1.next());
					 }
				 }
				 
			 }else if(a>b){
				 HotfixInEnv2NotEnv1.add(obj2);
				 if(iter2.hasNext()){
					 obj2=iter2.next();
				 }else{
					 HotfixInEnv1NotEnv2.add(obj1);
					 while(iter1.hasNext()){
						 HotfixInEnv1NotEnv2.add(iter1.next());
					 }
				 }
			 }if(b>a){
				 HotfixInEnv1NotEnv2.add(obj1);
				 if(iter1.hasNext()){
					 obj1=iter1.next();
				 }else{
					 HotfixInEnv2NotEnv1.add(obj2);
					 while(iter2.hasNext()){
						 HotfixInEnv2NotEnv1.add(iter2.next());
					 } 
				 }
			 }
		}
		
		StringBuffer finalbuf = new StringBuffer();
		// result generator
		finalbuf.append("Hotfixes in common in ENV-1 and ENV-2 \n");
		//System.out.println("Hotfixes in common in ENV-1 and ENV-2");
		for(int i=0;i<uniqueHotfixList.size();i++){
			finalbuf.append(i+1 + ") " +uniqueHotfixList.get(i)+"\n");
		//	System.out.println(i+1 + ") " +uniqueHotfixList.get(i));
		}
		//System.out.println("Hotfixes in Env-1 and not in Env-2");
		finalbuf.append("Hotfixes in Env-1 and not in Env-2 \n");
		for(int i=0;i<HotfixInEnv1NotEnv2.size();i++){
			finalbuf.append(i+1 + ") " +HotfixInEnv1NotEnv2.get(i)+"\n");
		//	System.out.println(i+1 + ") " +HotfixInEnv1NotEnv2.get(i));
		}
		finalbuf.append("Hotfixes in Env-2 and not in Env-1\n");
		//System.out.println("Hotfixes in Env-2 and not in Env-1");
		for(int i=0;i<HotfixInEnv2NotEnv1.size();i++){
			finalbuf.append(i+1 + ") " +HotfixInEnv2NotEnv1.get(i)+"\n");
			//System.out.println(i+1 + ") " +HotfixInEnv2NotEnv1.get(i));
		}
		System.out.println(finalbuf.toString());
		return finalbuf.toString();
	}	
}
