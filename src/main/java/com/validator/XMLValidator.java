package com.validator;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLValidator implements Validator {

	private String xml;
	private String xsd;

	public XMLValidator(String xml, String xsd) {
		super();
		this.xml = xml;
		this.xsd = xsd;
	}
	
	public XMLValidator(String xml) {
		super();
		this.xml = xml;
	}

	public static class RaiseOnErrorHandler implements ErrorHandler {
		
		public void warning(SAXParseException e) throws SAXException {
			throw new RuntimeException(e);
		}

		public void error(SAXParseException e) throws SAXException {
			throw new RuntimeException(e);
		}
		
		public void fatalError(SAXParseException e) throws SAXException {
			throw new RuntimeException(e);
		}
	}

	public void validate() throws Exception {

		InputStream xmlStream = new FileInputStream(this.xml);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setErrorHandler(new RaiseOnErrorHandler());
		builder.parse(new InputSource(xmlStream));

		System.out.println("Xml valid");

		xmlStream.close();
	}

}
