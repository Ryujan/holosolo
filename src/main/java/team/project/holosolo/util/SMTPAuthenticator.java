package team.project.holosolo.util;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator{
	@Override
	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication("jkjkjk1006", "qkrwjdrl1");
	}
}
