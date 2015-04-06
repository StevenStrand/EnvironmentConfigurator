package com.jamfsoftware.event;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {
	public static Document loadXMLDocument(String filePath) {
		Document doc = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(new File(filePath));
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public static Node getXMLNode(NodeList nList, int index) {
		Node nNode = null;
		try {
			nNode = nList.item(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nNode;
	}
	
	public static String getAttribute(Node nNode, String attribute) {
		NamedNodeMap list = nNode.getAttributes();
		return list.getNamedItem(attribute).getNodeValue();
	}
	
	public static String getXMLElement(Node nNode, String element, int index) {
		Element eElement = (Element) nNode;
		String value = "";
		try {
			value = eElement.getElementsByTagName(element).item(index).getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static NodeList getNodeList(Document doc, String tagName) {
		NodeList nList = doc.getElementsByTagName(tagName);
		return nList;
	}
}
