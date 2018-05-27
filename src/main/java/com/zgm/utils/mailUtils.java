package com.zgm.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class mailUtils {
  /**
   * 
   * @param to
   * @param code
   */
	public static void sendMail(String to,String code){
		
		//�������Ӷ���
	Properties props=new Properties();	
	//props.setProperty("host", value);
	 Session session=Session.getInstance(props, new Authenticator() {
		 @Override
		protected PasswordAuthentication getPasswordAuthentication() {
			// TODO Auto-generated method stub
			return new PasswordAuthentication("service@zgm.com", "123456");
		}
	});
	 //�����ʼ�����
	 Message message=new MimeMessage(session);
	 try {
		message.setFrom(new InternetAddress("service@zgm.com"));
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		message.setSubject("hello world!!");
		message.setContent("<h1>�����˻���������������</h1><h3><a href='http://localhost:8080/mail/jihuo?code="+code+"'>http://localhost:8080/mail/jihuo?code="+code+"</a></h3>", "text/html;charset=UTF-8");
		
		Transport.send(message);
	} catch (AddressException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	  
  }
    // �����˵� ���� �� ���루�滻Ϊ�Լ�����������룩
    // PS: ĳЩ���������Ϊ���������䱾������İ�ȫ�ԣ��� SMTP �ͻ��������˶������루�е������Ϊ����Ȩ�롱��, 
    //     ���ڿ����˶������������, ����������������ʹ������������루��Ȩ�룩��
    public static String myEmailAccount = "1107782568@qq.com";
    public static String myEmailPassword = "wrswswfhvtykjbcj";

    // ����������� SMTP ��������ַ, ����׼ȷ, ��ͬ�ʼ���������ַ��ͬ, һ��(ֻ��һ��, ��Ǿ��)��ʽΪ: smtp.xxx.com
    // ����163����� SMTP ��������ַΪ: smtp.163.com
    public static String myEmailSMTPHost = "smtp.qq.com";

    // �ռ������䣨�滻Ϊ�Լ�֪������Ч���䣩
  //  public static String receiveMailAccount = "1107782568@qq.com";
  
	public static void sendMailNow (String to,String code) throws Exception{
		  // 1. ������������, ���������ʼ��������Ĳ�������
        Properties props = new Properties();                    // ��������
        props.setProperty("mail.transport.protocol", "smtp");   // ʹ�õ�Э�飨JavaMail�淶Ҫ��
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // �����˵������ SMTP ��������ַ
        props.setProperty("mail.smtp.auth", "true");            // ��Ҫ������֤

        // PS: ĳЩ���������Ҫ�� SMTP ������Ҫʹ�� SSL ��ȫ��֤ (Ϊ����߰�ȫ��, ����֧��SSL����, Ҳ�����Լ�����),
        //     ����޷������ʼ�������, ��ϸ�鿴����̨��ӡ�� log, ����������� ������ʧ��, Ҫ�� SSL ��ȫ���ӡ� �ȴ���,
        //     ������ /* ... */ ֮���ע�ʹ���, ���� SSL ��ȫ���ӡ�
      
        // SMTP �������Ķ˿� (�� SSL ���ӵĶ˿�һ��Ĭ��Ϊ 25, ���Բ����, ������� SSL ����,
        //                  ��Ҫ��Ϊ��Ӧ����� SMTP �������Ķ˿�, ����ɲ鿴��Ӧ�������İ���,
        //                  QQ�����SMTP(SLL)�˿�Ϊ465��587, ������������ȥ�鿴)
        final String smtpPort = "587";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
      

        // 2. ������ô����Ự����, ���ں��ʼ�����������
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                 // ����Ϊdebugģʽ, ���Բ鿴��ϸ�ķ��� log

        // 3. ����һ���ʼ�
        MimeMessage message = createMimeMessage(session, myEmailAccount, to,code);

        // 4. ��� Session ��ȡ�ʼ��������
        Transport transport = session.getTransport();

        // 5. ʹ�� �����˺� �� ���� �����ʼ�������, ������֤����������� message �еķ���������һ��, ���򱨴�
        // 
        //    PS_01: �ɰܵ��жϹؼ��ڴ�һ��, ������ӷ�����ʧ��, �����ڿ���̨�����Ӧʧ��ԭ��� log,
        //           ��ϸ�鿴ʧ��ԭ��, ��Щ����������᷵�ش������鿴�������͵�����, ��ݸ���Ĵ��� 
        //           ���͵���Ӧ�ʼ��������İ�����վ�ϲ鿴����ʧ��ԭ��
        //
        //    PS_02: ����ʧ�ܵ�ԭ��ͨ��Ϊ���¼���, ��ϸ������:
        //           (1) ����û�п��� SMTP ����;
        //           (2) �����������, ����ĳЩ���俪���˶�������;
        //           (3) ���������Ҫ�����Ҫʹ�� SSL ��ȫ����;
        //           (4) �������Ƶ��������ԭ��, ���ʼ��������ܾ����;
        //           (5) ������ϼ��㶼ȷ������, ���ʼ���������վ���Ұ���
        //
        //    PS_03: ��ϸ��log, ���濴log, ����log, ����ԭ����log��˵����
        transport.connect(myEmailAccount, myEmailPassword);

        // 6. �����ʼ�, �������е��ռ���ַ, message.getAllRecipients() ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���, ������, ������
        transport.sendMessage(message, message.getAllRecipients());

        // 7. �ر�����
        transport.close();
		
	}
	
	 public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String code) throws Exception {
	        // 1. ����һ���ʼ�
	        MimeMessage message = new MimeMessage(session);

	        // 2. From: ������
	        message.setFrom(new InternetAddress(sendMail, "ZGM", "UTF-8"));

	        // 3. To: �ռ��ˣ��������Ӷ���ռ��ˡ����͡����ͣ�
	        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX�û�", "UTF-8"));

	        // 4. Subject: �ʼ�����
	        message.setSubject("�����ʼ�", "UTF-8");

	        // 5. Content: �ʼ����ģ�����ʹ��html��ǩ��
	        message.setContent("<h1>�����˻���������������</h1><h3><a href='http://localhost:8080/mail/jihuo?code="+code+"'>http://localhost:8080/mail/jihuo?code="+code+"</a></h3>", "text/html;charset=UTF-8");

	        // 6. ���÷���ʱ��
	        message.setSentDate(new Date());

	        // 7. ��������
	        message.saveChanges();

	        return message;
	    }
}
