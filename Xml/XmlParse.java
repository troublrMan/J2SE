package com.fuxi.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlParse {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String path = "E:/temp/employees.xml";
		List<Employee> data = parseXml(path);
		System.out.println(data);

	}

	static List<Employee> parseXml(String path) throws Exception {
		List<Employee> data = new Vector<Employee>();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		// InputSource
		FileInputStream fin = new FileInputStream(path);
		InputStreamReader reader = new InputStreamReader(fin, "utf-8");
		InputSource input = new InputSource(reader);

		Document doc = db.parse(input);
		NodeList employees = selectByXpath("/employees/employee", doc);
		for (int i = 0; i < employees.getLength(); i++) {
			Node node = employees.item(i);
			Employee e = fromNode(node);
			data.add(e);
		}
		return data;
	}

	static Employee fromNode(Node n) {
		Employee emp = new Employee();
		try {
			String id = selectByXpathReturnString("@id", n);
			String name = selectByXpathReturnString("name", n);
			String birthday = selectByXpathReturnString("birthday", n);

			emp.setId(Long.parseLong(id));
			emp.setName(name);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			emp.setBirthday(sdf.parse(birthday));

		} catch (Exception e) {

		}
		return emp;
	}

	/**
	 * 
	 * @param path
	 * @param node
	 * @return
	 * @throws Exception
	 */
	static NodeList selectByXpath(String path, Node node) throws Exception {
		XPathFactory xf = XPathFactory.newInstance();
		XPath xpath = xf.newXPath();
		return (NodeList) xpath.evaluate(path, node, XPathConstants.NODESET);
	}

	static String selectByXpathReturnString(String path, Node node)
			throws Exception {
		XPathFactory xf = XPathFactory.newInstance();
		XPath xpath = xf.newXPath();
		return (String) xpath.evaluate(path, node, XPathConstants.STRING);
	}

}
