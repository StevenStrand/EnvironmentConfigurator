package com.jamfsoftware.configuration;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jamfsoftware.event.FindEnvironment;
import com.jamfsoftware.event.FindInstalledJSS;
import com.jamfsoftware.event.ReadDirectory;
import com.jamfsoftware.event.ReadXML;
import com.jamfsoftware.object.JSSObject;
import com.jamfsoftware.object.TomcatObject;

@Configuration
public class WebAppConfiguration {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(Configuration.class);

	@PostConstruct
	public void contextInitialized() {
		FindEnvironment.tomcatInstances.addAll(FindEnvironment.findEnvironment());
		PrintWriter writer;
		try {
			writer = new PrintWriter("/filepath.txt", "UTF-8");
			for (String instance : ReadDirectory.getTomcatInstances()) {
				writer.println(ReadDirectory.getTomcatContainer().concat(instance).concat("/conf/server.xml"));
				Document doc = ReadXML.loadXMLDocument(ReadDirectory.getTomcatContainer().concat(instance).concat("/conf/server.xml"));
				NodeList nList = ReadXML.getNodeList(doc, "Connector");
				writer.println(nList.toString());
				Node nNode = ReadXML.getXMLNode(nList, 1);
				writer.println(ReadXML.getAttribute(nNode, "port"));
			}
			for (TomcatObject tomcat : FindEnvironment.tomcatInstances) {
				ArrayList<JSSObject> jssList =  FindInstalledJSS.buildList(tomcat.getPath());
				writer.println(tomcat.getPath());
				tomcat.setJssList(jssList);
				for (JSSObject jss : tomcat.getJssList()) {
					writer.println("jss: " + jss.getName() +" Port: " + tomcat.getPort());
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		logger.debug("Context Initialized");
	}

	@PreDestroy
	public void contextDestroyed() {
		logger.debug("Context Destroyed");
	}

}
