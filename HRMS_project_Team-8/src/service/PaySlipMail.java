package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.itextpdf.html2pdf.HtmlConverter;

public class PaySlipMail {
	public static void sendEmail(HttpServletRequest request, HttpServletResponse response,
			models.input.output.EmployeePayRollOutputModel payRollOutput) throws Exception {

		String viewName = "payslip";
		request.setAttribute("pay", payRollOutput);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/" + viewName + ".jsp");

		StringWriter stringWriter = new StringWriter();

		HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
			@Override
			public PrintWriter getWriter() throws IOException {
				return new PrintWriter(stringWriter);
			}
		};

		requestDispatcher.include(request, responseWrapper);
		// Render the jsp content into string
		String renderedHtml = stringWriter.toString();
		// Convert the jsp file into pdf
		OutputStream file = new FileOutputStream(new File("Payslip.pdf"));
		HtmlConverter.convertToPdf(renderedHtml, file);
		file.close();

		// Set up the mail properties to mail the payslip pdf
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.auth", true);
		p.put("mail.smtp.starttls.enable", true);
		p.put("mail.smtp.port", "587");
		Session s = Session.getInstance(p, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sambangichandrasekhar340@gmail.com", "edgg kndd kwgs vqcb");
			}
		});
		try {
			MimeMessage mm = new MimeMessage(s);
			mm.setFrom(new InternetAddress("sambangichandrasekhar340@gmail.com"));
			mm.setRecipient(Message.RecipientType.TO, new InternetAddress("bhaskar.p@pennanttech.com"));
			mm.setSubject("Payslip Details");
			// mm.setContent(renderedHtml, "text/html");
			mm.setContent("This is payslip email...........\n", "text/html");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Payslip details");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			String filename = "Payslip.pdf";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			mm.setContent(multipart);

			Transport.send(mm);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}