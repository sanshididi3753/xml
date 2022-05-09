package com.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;

public class XPathDemo {
    private static Document doc;
    private static XPath xpath;

    public static void main(String[] args) throws Exception {
        init();
        getAttrEles();
    }

    public static void init() throws Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(new FileInputStream(new File("src\\main\\java\\com\\xml\\FlightINFO.xml")));
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }


    public static void getAttrEles() throws XPathExpressionException {
        //"//Airlines/AirlinesTime[StartDrome='北京首都机场']/AirlineCode"
        //"//Airlines/AirlinesTime[number(translate(substring(StartTime, 1, 5), \":\", \".\")) <10>]/AirlineCode"
        NodeList nodeList = (NodeList) xpath.evaluate("//Airlines/AirlinesTime[StartDrome='北京首都机场']/AirlineCode", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent() + " ");
        }
        System.out.println();
    }
}