/**
 * @author damas
 *
 * 
 */
package HotfixCompareTool.HotfixComparator;
public class Hotfix {

	private String hotfix_id;
	private String hotfix_desc;
	private String hotfix_state;
	private String hotfix_install_status;
	private String hotfix_status;
	private int hfixKey;
	
	Hotfix(String hotfix_id,String hotfix_desc,String hotfix_state, String hotfix_install_status,String hotfix_status){
		this.hotfix_id = hotfix_id;
		this.hotfix_desc = hotfix_desc;
		this.hotfix_state = hotfix_state;
		this.hotfix_install_status = hotfix_install_status;
		this.hotfix_status = hotfix_status;
		this.hfixKey = Integer.parseInt(hotfix_id.substring(6,hotfix_id.length()-1));
	}
	
	public String getHotfixId(){
		return this.hotfix_id;
	}
	
	public String getHotDesc(){
		return this.hotfix_desc;
	}
	
	public String getHotfixState(){
		return this.hotfix_state;
	}
	
	public String getHotfixInstallStatus(){
		return this.hotfix_install_status;
	}
	
	public String getHotfixStatus(){
		return this.hotfix_status;
	}
	
	public int getHotfixPK(){
		return this.hfixKey;
	}
	
	@Override
	public String toString(){
		return this.hotfix_id + " " + this.hotfix_desc +  " " + this.hotfix_state +  " " + this.hotfix_status +  " " + this.hotfix_install_status;
	}
	
	public boolean equals(Object x){
		if(x instanceof Hotfix){
			if(this.hotfix_id.equals(((Hotfix) x).hotfix_id) && this.hotfix_state.equals(((Hotfix) x).hotfix_state)
					&& this.hotfix_install_status.equals(((Hotfix) x).hotfix_install_status) && this.hotfix_status.equals(((Hotfix) x).hotfix_status)){
				return true;
			}
		}
		return false;	
	}
}
