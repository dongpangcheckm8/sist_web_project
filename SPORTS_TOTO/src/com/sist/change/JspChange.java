package com.sist.change;

public class JspChange {
   private static String[] jsp= {
		 "member_info.jsp",
		 "member_update.jsp",
         "mygame.jsp",
         "charge.jsp",
         "admin.jsp"
         
   };
   public static String change(int no) {
      return jsp[no];
   }
}