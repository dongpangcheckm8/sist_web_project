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
	
	public GscPanVO(){}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cmId == null) ? 0 : cmId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((useYn == null) ? 0 : useYn.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GscPanVO other = (GscPanVO) obj;
		if (cmId == null) {
			if (other.cmId != null)
				return false;
		} else if (!cmId.equals(other.cmId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (useYn == null) {
			if (other.useYn != null)
				return false;
		} else if (!useYn.equals(other.useYn))
			return false;
		return true;
	}

	public GscPanVO(String cmId, String name, String password, String useYn) {
		super();
		this.cmId = cmId;
		this.name = name;
		this.password = password;
		this.useYn = useYn;
	}


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
