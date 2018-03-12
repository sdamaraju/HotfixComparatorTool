/**
 * @author damas
 *
 * 
 */
package HotfixCompareTool.HotfixComparator;

import java.io.Serializable;

	public class Paths implements Serializable{
		String path1;
		String path2;
		String version;
		public String getPath1(){
			return path1;
		}
		public String getPath2(){
			return path2;
		}
		public String getVersion(){
			return version;
		}
	
}
