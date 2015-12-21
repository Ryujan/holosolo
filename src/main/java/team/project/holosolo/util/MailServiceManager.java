package team.project.holosolo.util;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import team.project.holosolo.model.dto.MemberDTO;

public class MailServiceManager {
	private Properties prop;

	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
	public void sendEmail(MemberDTO memberDTO){
		String senderEmail = "jkjkjk1006@naver.com";
		String receiverEmail = memberDTO.getEmail();
		String content = "[WEB 발신] 안녕하세요. HANAUS입니다. 요청하신 비밀번호에 대한 답변입니다.\n"+
						 "귀하의 비밀번호는 " + memberDTO.getPassword() + "입니다.\n" + 
						 "이용해주셔서 감사합니다.";
		Authenticator auth = new SMTPAuthenticator();
		
		Session emailSession = Session.getInstance(prop, auth);
		
		// 아래는 상황을 출력하기 위해 필요한 메서드.
		emailSession.setDebug(true);
		
		// 메일의 내용을 담기 위한 객체이고, mime 타입이다.
		MimeMessage msg = new MimeMessage(emailSession);
		try{
			// 제목 입력
			msg.setSubject("[HANAUS] 요청하신 비밀번호입니다.");
			
			// 우리 메일 주소 입력
			Address fromAddress = new InternetAddress(senderEmail);
			msg.setFrom(fromAddress);
			
			// 받는 사람 메일 주소 입력
			Address toAddress = new InternetAddress(receiverEmail);
			msg.addRecipient(Message.RecipientType.TO, toAddress); // 이 부분을 잘 모름.
			
			// 메일의 본문 및 내용, 형식, charset 입력
			msg.setContent(content, "text/html;charset=utf-8");
			
			// 발송하기
			Transport.send(msg);
		}catch(Exception mailException){
			mailException.printStackTrace();
		}
	}
}
