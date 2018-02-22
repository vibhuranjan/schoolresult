package com.vibhu.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLCreation {

	public static void main(String[] args) {
		
		try {

		    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		    Document doc = docBuilder.newDocument();
		    Element rootElement = doc.createElement("CONFIGURATION");
		    doc.appendChild(rootElement);
		    Element browser = doc.createElement("BROWSER");
		    browser.appendChild(doc.createTextNode("chrome"));
		    rootElement.appendChild(browser);
		    Element base = doc.createElement("BASE");
		    base.appendChild(doc.createTextNode("http:fut"));
		    rootElement.appendChild(base);
		    Element employee = doc.createElement("EMPLOYEE");
		    rootElement.appendChild(employee);
		    Element empName = doc.createElement("EMP_NAME");
		    empName.appendChild(doc.createTextNode("Anhorn, Irene"));
		    employee.appendChild(empName);
		    Element actDate = doc.createElement("ACT_DATE");
		    actDate.appendChild(doc.createTextNode("20131201"));
		    employee.appendChild(actDate);
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    DOMSource source = new DOMSource(doc);
		    StreamResult result = new StreamResult(new File("C:\\fuNke\\code\\schoolresult\\ScoreDetail.xml"));
		    transformer.transform(source, result);
		    System.out.println("File saved!");
		  } catch (ParserConfigurationException pce) {
		    pce.printStackTrace();
		  } catch (TransformerException tfe) {
		    tfe.printStackTrace();}
		}

}
