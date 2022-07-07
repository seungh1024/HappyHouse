package com.ssafy.happyhouse.model.service;

import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	JavaMailSender emailSender;

	public static final String ePw = createKey();

	private MimeMessage createMessage(String to) throws Exception {
		System.out.println("보내는 대상 : " + to);
		System.out.println("인증 번호 : " + ePw);
		MimeMessage message = emailSender.createMimeMessage();

		message.addRecipients(RecipientType.TO, to);// 보내는 대상
		message.setSubject("Happy House 인증번호가 도착했습니다.");// 제목

		String msgg = "";
		msgg += "<div>";
		msgg += "<h1 style='background-color: #ead1dc';>Welcome!</h1>";
		msgg += "<img src='https://demo.stripocdn.email/content/guids/17653316-4f41-40aa-8517-f15031e828b0/images/house.jpeg'; alt style=\"display: block;\" width=\"600\">";
		msgg += "<p style='line-height: 150%;'>안녕하세요? Happy House입니다.<br><br>인증번호 확인 후&nbsp;이메일 인증을&nbsp;완료해 주세요.</p>";
		msgg += "<button style='font-color: \"white\"; border-width: 0px 5px; display: block; background: #6667ab; border-color: #6667ab; border-radius: 0px; font-size: 36px;'>";
		msgg += ePw + "</button>";
		msgg += "</div>";
		message.setText(msgg, "utf-8", "html");// 내용
		message.setFrom(new InternetAddress("sukyungkim99@gmail.com", "Happy House"));// 보내는 사람

		return message;
	}

//		인증코드 만들기
	public static String createKey() {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();

		for (int i = 0; i < 8; i++) { // 인증코드 8자리
			int index = rnd.nextInt(3); // 0~2 까지 랜덤

			switch (index) {
			case 0:
				key.append((char) ((int) (rnd.nextInt(26)) + 97));
				// a~z (ex. 1+97=98 => (char)98 = 'b')
				break;
			case 1:
				key.append((char) ((int) (rnd.nextInt(26)) + 65));
				// A~Z
				break;
			case 2:
				key.append((rnd.nextInt(10)));
				// 0~9
				break;
			}
		}

		return key.toString();
	}

	public void sendSimpleMessage(String to) throws Exception {
		// TODO Auto-generated method stub
		MimeMessage message = createMessage(to);
		try {// 예외처리
			emailSender.send(message);
		} catch (MailException es) {
			es.printStackTrace();
			throw new IllegalArgumentException();
		}

	}

}
