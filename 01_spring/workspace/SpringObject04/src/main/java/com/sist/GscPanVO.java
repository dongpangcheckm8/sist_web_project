package com.sist;

/**
 * 위원회 VO
 * @author sist1
 * @version : 0.1
 * @since: 2017/11/14
 *
 */
public class GscPanVO {
	private String cmId	    ;
	private String name	    ;
	private String password	;
	private String useYn	;
	

	@Override
	public String toString() {
		return "GscPanVO [cmId=" + cmId + ", name=" + name + ", password=" + password + ", useYn=" + useYn + "]";
	}
	
	
	public String getCmId() {
		return cmId;
	}
	public void setCmId(String cmId) {
		this.cmId = cmId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

}
