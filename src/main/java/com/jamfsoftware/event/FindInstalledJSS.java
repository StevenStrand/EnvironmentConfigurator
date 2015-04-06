package com.jamfsoftware.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jamfsoftware.object.JSSObject;

public class FindInstalledJSS {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(FindInstalledJSS.class);
	public static ArrayList<JSSObject> listJSS;
	public static List<String> ids = new ArrayList<String>();
	public static List<String> names = new ArrayList<String>();
	public static List<String> versions = new ArrayList<String>();
	public static List<String> urls = new ArrayList<String>();
	
	public static ArrayList<JSSObject> buildList(String tomcatDirectory) {
		ArrayList<JSSObject> listJSS = new ArrayList<JSSObject>();
		ArrayList<String> tomcatFiles = ReadDirectory.getDirectories(tomcatDirectory);
		Integer i = 0;
		for(String name : tomcatFiles) {
			String url = tomcatDirectory.concat(name.toString());
			String version = "";
			String path = "";
			if(ReadDirectory.checkWebInf(url) && ReadDirectory.checkIsJSS(url)) {
				JSSObject jss = new JSSObject();
				jss.setId(i);
				ids.add(i.toString());
				i++;
				jss.setName(name);
				names.add(name);
				jss.setUrl(url = "/".concat(name.toString()).concat("/?username=admin&password=jamf1234"));
				urls.add(url);
				path = ReadDirectory.getTomcatWebappRoot().toString().concat(name);
				jss.setPath(path);
				jss.setVersion(version = getVersion(path));
				versions.add(version);
				listJSS.add(jss);
			}
		}
		
		Collections.sort(listJSS);
		logger.debug("JSS list generated.");
		return listJSS;
	}
	
	public static String getVersion(String path) {
		String version = "";
		Document doc = ReadXML.loadXMLDocument(path.concat("/WEB-INF/xml/version.xml"));
		NodeList nList = ReadXML.getNodeList(doc, "app");
		if (nList.getLength() == 0) {
			nList = ReadXML.getNodeList(doc, "jamfWebApplication");
			Node nNode = ReadXML.getXMLNode(nList, 0);
			version = ReadXML.getXMLElement(nNode, "version", 0);
		} else {
			Node nNode = ReadXML.getXMLNode(nList, 0);
			version = ReadXML.getXMLElement(nNode, "version", 0);
		}

		return version;
	}
	
	public JSSObject getJSS(int index) {
		return listJSS.get(index);
	}

	public static List<String> getIds() {
		
		return ids;
	}

	public static List<String> getNames() {
		return names;
	}

	public static List<String> getVersions() {
		return versions;
	}

	public static List<String> getUrls() {
		return urls;
	}
}
