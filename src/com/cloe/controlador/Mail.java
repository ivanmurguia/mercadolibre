package com.cloe.controlador;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cloe.modelo.Log;

public class Mail {
	public void sendMail(List<Log> logs) {
				String pedidosbien = "";
				String pedidosmal = "";
//				String encabezado = "" +
//							  "<html>\r\n" + 
//							  "<style>\r\n" + 
//							  "	table{\r\n" + 
//							  "		border-collapse: collapse;\r\n" + 
//							  "		border: 1px solid black;\r\n" + 
//							  "	}\r\n" + 
//							  "	table th{\r\n" + 
//							  "		  background-color: black;\r\n" + 
//							  "  		  color: white;\r\n" + 
//							  "	}\r\n" + 
//							  "	table tr:nth-child(even) {\r\n" + 
//							  "  		  background-color: rgb(255, 252, 35);\r\n" + 
//							  "	}\r\n" + 
//							  "</style>\r\n" + 
//							  "<font face=\"Century Gothic\">\r\n" + 
//							  "<h2 align=\"center\">Ordenes procesadas Mercado Libre</h2>\r\n" + 
//							  "<table style=\"width:100%\">\r\n" + 
//							  "	<tr>\r\n" + 
//							  "		<th>Pedido</th>\r\n" + 
//							  "		<th>Estatus</th>\r\n" + 
//							  "		<th>Mensaje</th>\r\n" + 
//							  "		<th>Fecha/Hora</th>\r\n" + 
//							  "	</tr>\r\n";
				String encabezado = "<html>\r\n" + 
						"<body style=\"margin: 0; padding: 0;\">\r\n" + 
						"	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">	\r\n" + 
						"		<tr>\r\n" + 
						"			<td style=\"padding: 10px 0 10px 0;\">\r\n" + 
						"				<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border: 1px solid #cccccc; border-collapse: collapse;\">\r\n" + 
						"					<tr>\r\n" + 
						"						<td align=\"center\" bgcolor=\"white\" style=\"padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;\">\r\n" + 
						"							<img src=\"https://cdn2.downdetector.com/static/uploads/c/300/d20f7/Mercado_libre_logo.png\" alt=\"Creating Email Magic\" width=\"300\" height=\"230\" style=\"display: block;\" />\r\n" + 
						"						</td>\r\n" + 
						"					</tr>\r\n" + 
						"					<tr>\r\n" + 
						"						<td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\r\n" + 
						"							<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
						"								<tr>\r\n" + 
						"									<td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 24px;\">\r\n" + 
						"										<b></b>\r\n" + 
						"									</td>\r\n" + 
						"								</tr>\r\n" + 
						"								<tr align = \"center\">\r\n" + 
						"									<td style=\"padding: 20px 0 30px 0; color: rgb(0,8,125); font-family: Arial, sans-serif; font-size: 25px; line-height: 20px; align: center;\">\r\n" + 
						"										Ordenes Procesadas\r\n" + 
						"									</td>\r\n" + 
						"								</tr>\r\n" + 
						"								<tr>\r\n" + 
						"									<td>\r\n" + 
						"										<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" width=\"100%\">\r\n" + 
						"											<tr>\r\n" + 
						"												<th>Pedido</th>\r\n" + 
						"												<th>Estatus</th>\r\n" + 
						"												<th>Mensaje</th>\r\n" + 
						"												<th>Fecha/Hora</th>\r\n" + 
						"											</tr>" ;
				String pie = "										</table>\r\n" + 
						"									</td>\r\n" + 
						"								</tr>\r\n" + 
						"							</table>\r\n" + 
						"						</td>\r\n" + 
						"					</tr>\r\n" + 
						"					<tr align=\"center\">\r\n" + 
						"						<td bgcolor=\"yellow\" style=\"padding: 30px 30px 30px 30px; align: center\">\r\n" + 
						"							<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
						"								<tr>\r\n" + 
						"									<td style=\"color: blue; font-family: Arial, sans-serif; font-size: 14px;\" width=\"75%\">\r\n" + 
						"										<br/>\r\n" + 
						"									</td>\r\n" + 
						"								</tr>\r\n" + 
						"							</table>\r\n" + 
						"						</td>\r\n" + 
						"					</tr>\r\n" + 
						"				</table>\r\n" + 
						"			</td>\r\n" + 
						"		</tr>\r\n" + 
						"	</table>\r\n" + 
						"</body>\r\n" + 
						"</html>";
//				String pie =  "</table>\r\n" + 
//						  "<p>Favor de corregir estos errores.</p>\r\n" + 
//						  "</html>";
				  for(int i = 0; i < logs.size(); i++) {
					  if(logs.get(i).getStatus().equals("Correcto")) {
						  pedidosbien = pedidosbien + 
								  "	<tr>\r\n" + 
								  "		<td>" + logs.get(i).getPedido()+"</td>\r\n" + 
								  "		<td>" + logs.get(i).getStatus()+"</td>\r\n" + 
								  "		<td>" + logs.get(i).getMensaje()+"</td>\r\n" + 
								  "		<td>" + logs.get(i).getFecha()+"</td>\r\n" + 
								  "	</tr>\r\n" ;
					  } else {
						  pedidosmal = pedidosmal + 
								  "	<tr>\r\n" + 
								  "		<td>" + logs.get(i).getPedido()+"</td>\r\n" + 
								  "		<td>" + logs.get(i).getStatus()+"</td>\r\n" + 
								  "		<td>" + logs.get(i).getMensaje()+"</td>\r\n" + 
								  "		<td>" + logs.get(i).getFecha()+"</td>\r\n" + 
								  "	</tr>\r\n" ;
					  }
				  }

				    String remitente = "CloeMercadoLibre@oemoda.com";
				    String clave = "Cloe.123";
				    
				    Properties props = System.getProperties();
				    props.put("mail.smtp.host", "outlook.oemoda.com");
				    props.put("mail.smtp.user", remitente);
				    props.put("mail.smtp.clave", clave);
				    props.put("mail.smtp.auth", "true");
				    props.put("mail.smtp.starttls.enable", "true");
				    props.put("mail.smtp.ehlo", "false");
				    props.put("mail.smtp.port", "25");

				    Session session = Session.getDefaultInstance(props);
//				    session.setDebug(true);
				    
			        try {
			        	if(!pedidosbien.equals("")) {
				        	MimeMessage message = new MimeMessage(session);
							message.setFrom(new InternetAddress(remitente));
//					        message.addRecipients(Message.RecipientType.TO, "andreaa@oemoda.com");
//					        message.addRecipients(Message.RecipientType.TO, "davids@oemoda.com");
//					        message.addRecipients(Message.RecipientType.TO, "rodrigoa@oemoda.com");
					        message.addRecipients(Message.RecipientType.CC, "ivanm@oemoda.com");
					        message.setSubject("Pedidos procesados");
					        message.setContent(encabezado+pedidosbien+pie,"text/html");
					        Transport transport = session.getTransport("smtp");
					        transport.connect("outlook.oemoda.com", remitente, clave);
					        transport.sendMessage(message, message.getAllRecipients());
					        transport.close();
			        	}
			        	if(!pedidosmal.equals("")) {
				        	MimeMessage message = new MimeMessage(session);
							message.setFrom(new InternetAddress(remitente));
					        message.addRecipients(Message.RecipientType.TO, "andreaa@oemoda.com");
					        message.addRecipients(Message.RecipientType.TO, "davids@oemoda.com");
					        message.addRecipients(Message.RecipientType.TO, "rodrigoa@oemoda.com");
					        message.addRecipients(Message.RecipientType.CC, "ivanm@oemoda.com");
					        message.setSubject("Pedidos procesados con errores");
					        message.setContent(encabezado+pedidosmal+pie,"text/html");
					        Transport transport = session.getTransport("smtp");
					        transport.connect("outlook.oemoda.com", remitente, clave);
					        transport.sendMessage(message, message.getAllRecipients());
					        transport.close();
			        	}
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        
	}
}