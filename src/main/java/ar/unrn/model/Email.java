package ar.unrn.model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email implements ServicioEmail {
	private final String host;
	private final String port;
	private final String username;
	private final String password;

	public Email(String host, String port, String username, String password) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public void enviarEmail(String destinatarioEmail, String asunto, String tema) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatarioEmail));
			message.setSubject(asunto);
			message.setText(tema);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException("Error al enviar el correo electrónico: " + e.getMessage());
		}
	}
}