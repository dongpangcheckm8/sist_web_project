package com.sist;

import java.sql.SQLException;

public class TestUserService extends GscPanServiceImpl {
	private String id;
	
	public TestUserService(){}
	
	//Exception 발생 ID지정
	public TestUserService(String id){
		this.id = id;
	}

	@Override
	protected void upgradeLevel(GscPanVO vo) {
		//지정된 ID가 발견 되면 Exception 발생.
		if(vo.getCmId().equals(id)){
			throw new TestUserServiceException(id+"Tx GscPanVO 익셉션!");
		}
		
		
		super.upgradeLevel(vo);
	}


	
	
	
}
