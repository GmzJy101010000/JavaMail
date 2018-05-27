package com.zgm.service;

import com.zgm.dao.MailDao;
import com.zgm.dao.MailUserDao;
import com.zgm.utils.mailUtils;
import com.zgn.entity.mailUser;

public class sendMailServieImp implements sendMailServise {

	

	public void addMaillUser(mailUser a,String mail) throws Exception{
	  //insert data to DB
		MailUserDao ddaDao=new MailDao();
	  ddaDao.addMailUser(a);
		// send mail to USER
	  
	  mailUtils.sendMailNow(mail, a.getCode());
	}

	public void getData(String a) {
		// TODO Auto-generated method stub
		
	}
  
}
