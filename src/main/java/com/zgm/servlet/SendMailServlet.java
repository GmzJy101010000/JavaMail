package com.zgm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zgm.service.sendMailServieImp;
import com.zgm.service.sendMailServise;
import com.zgm.utils.uuidGet;
import com.zgn.entity.mailUser;

/**
 * Servlet implementation class SendMailServlet
 */
public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
	String pwd=request.getParameter("pwd");
	String name=request.getParameter("username");
	String email=request.getParameter("mail");
	mailUser user=new mailUser();
	user.setPwd(pwd);
	user.setName(name);
	user.setStatus("0");
	user.setCode(uuidGet.getCode());
	sendMailServise sd=new sendMailServieImp();
	try {
		sd.addMaillUser(user,email);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	}
	System.out.println(288);
  // response.getWriter().write("fgf");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
