package com.sist;

import java.sql.SQLException;

/**
 * 위원회 검증
 * @author sist1
 * @version : 0.1
 * @since: 2017/11/14
 *
 */
public class GscMain {
    
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		GscPanDao dao =new GscPanDao();

		
		
		
		GscPanVO pan=new GscPanVO();
		pan.setCmId("james");
		pan.setName("다현아빠");
		pan.setPassword("1234");
		pan.setUseYn("0");
		
		try {
			//---------------------------------------//
			//전체삭제                                                                                         //
			//---------------------------------------//			
			int deleteFlag = dao.deleteAll();
			System.out.println("deleteFlag:"+deleteFlag);
			
			
			//---------------------------------------//
			//단건추가                                                                                          //
			//---------------------------------------//			
			dao.add(pan);
		
			
			//---------------------------------------//
			//1건 입력==1건 조회                                                                      //
			//---------------------------------------//			
			GscPanVO pan2=dao.get(pan.getCmId());
			if(!pan.getCmId().equals(pan2.getCmId())){
				System.out.println("데이터 등록 실패:CmId");
			}else if(!pan.getName().equals(pan2.getName())){
				System.out.println("데이터 등록 실패:getName");
			}else if(!pan.getPassword().equals(pan2.getPassword())){
				System.out.println("데이터 등록 실패:getPassword");
			}else if(!pan.getUseYn().equals(pan2.getUseYn())){
				System.out.println("데이터 등록 실패:getUseYn");
			}else{
				System.out.println("데이터 등록 성공"+pan2.toString());
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		

	}

}
