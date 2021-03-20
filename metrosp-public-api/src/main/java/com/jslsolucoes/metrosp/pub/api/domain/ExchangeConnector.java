package com.jslsolucoes.metrosp.pub.api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jslsolucoes.metrosp.pub.api.config.ExchangeEnviromentProperties;
import com.jslsolucoes.metrosp.pub.api.stereotype.Connector;

@Connector
public class ExchangeConnector {

	private ExchangeEnviromentProperties exchangeEnviromentProperties;
	private static Pattern fromPattern = Pattern.compile(".+<(.*?)>");
	private static final Logger logger = LoggerFactory.getLogger(ExchangeConnector.class);

	@Autowired
	public ExchangeConnector(ExchangeEnviromentProperties exchangeEnviromentProperties) {
		this.exchangeEnviromentProperties = exchangeEnviromentProperties;
	}

	public List<ExchangeEmail> messages() throws Exception {
		Session session = Session.getDefaultInstance(new Properties());
		Store store = session.getStore("imaps");
		List<ExchangeEmail> emails = new ArrayList<>();
		try {
			store.connect(exchangeEnviromentProperties.getHost(), exchangeEnviromentProperties.getPort(),
					exchangeEnviromentProperties.getUsername(), exchangeEnviromentProperties.getPassword());
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			SearchTerm searchTerm = new AndTerm(new SearchTerm[] { new FlagTerm(new Flags(Flag.SEEN), false),
					new FromTerm(new InternetAddress("jonatan@jslsolucoes.com")) });
			logger.info("Search for messages on inbox using term {}", searchTerm);
			Message[] messages = inbox.search(searchTerm);
			logger.info("Messages found {}", messages.length);
			for (Message message : messages) {
				MimeMultipart mimeMultipart = MimeMultipart.class.cast(message.getContent());
				for (int i = 0; i < mimeMultipart.getCount(); i++) {
					MimeBodyPart mimeBodyPart = (MimeBodyPart) mimeMultipart.getBodyPart(i);
					String contentType = mimeBodyPart.getContentType();
					if (contentType.matches("^text/plain;.+$")) {
						String content = mimeBodyPart.getContent().toString();
						Matcher matcher = fromPattern.matcher(message.getFrom()[0].toString());
						if (matcher.find()) {
							String from = matcher.group(1);
							emails.add(new ExchangeEmail(id(from, content), from, content));
						}
					}
				}
			}
		} finally {
			store.close();
		}
		return emails;
	}

	private String id(String from, String content) {
		return DigestUtils.sha256Hex(from + ":" + content);
	}

}
