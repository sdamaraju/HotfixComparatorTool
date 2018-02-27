/**
 * @author damas
 *
 * 
 */
package HotfixCompareTool.HotfixComparator;

import java.util.Comparator;

public class HotfixComparator implements Comparator<Hotfix> {

	@Override
	public int compare(Hotfix hf1, Hotfix hf2) {
		return hf1.hfixKey-hf2.hfixKey;
	}
	

}
