package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.exception.ParseException;
import com.epam.irasov.xmlknight.util.FileOperation;
import com.epam.irasov.xmlknight.util.PropertyManagerTag;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.epam.irasov.xmlknight.parser.SAXKnightParser.*;

public class DOMCreatorXML {
    public static void creator() {
        PropertyManagerTag propertyManagerTag = new PropertyManagerTag();
        propertyManagerTag.loadProperty(FileOperation.load("tagValue.properties"));
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParseException(e);
        }
        Document document = documentBuilder.newDocument();
        Element rootElement = document.createElement(TAG_KNIGHT);
        document.appendChild(rootElement);
        Element elementId = document.createElement(TAG_ID);
        elementId.appendChild(document.createTextNode(propertyManagerTag.getProperties("knight.id.value")));
        Element elementName = document.createElement(TAG_NAME);
        elementName.appendChild(document.createTextNode(propertyManagerTag.getProperties("knight.name.value")));
        rootElement.appendChild(elementId);
        rootElement.appendChild(elementName);
        Element elementAmmunition = document.createElement(TAG_AMMUNITION);
        Element elementArmor = document.createElement(TAG_ARMOR);
        elementId = document.createElement(TAG_ID);
        elementId.appendChild(document.createTextNode(propertyManagerTag.getProperties("armor.id.value")));
        elementName = document.createElement(TAG_NAME);
        elementName.appendChild(document.createTextNode(propertyManagerTag.getProperties("armor.name.value")));
        Element elementWeight = document.createElement(TAG_WEIGHT);
        elementWeight.appendChild(document.createTextNode(propertyManagerTag.getProperties("armor.weight.value")));
        Element elementPrice = document.createElement(TAG_PRICE);
        elementPrice.appendChild(document.createTextNode(propertyManagerTag.getProperties("armor.price.value")));
        elementArmor.appendChild(elementId);
        elementArmor.appendChild(elementName);
        elementArmor.appendChild(elementWeight);
        elementArmor.appendChild(elementPrice);
        Element elementType = document.createElement(TAG_TYPE);
        elementId = document.createElement(TAG_ID);
        elementId.appendChild(document.createTextNode(propertyManagerTag.getProperties("armor.type.id.value")));
        elementName =document.createElement(TAG_NAME);
        elementName.appendChild(document.createTextNode(propertyManagerTag.getProperties("armor.type.name.value")));
        elementType.appendChild(elementId);
        elementType.appendChild(elementName);
        elementArmor.appendChild(elementType);
        Element elementProtection = document.createElement(TAG_PROTECTION);
        elementId = document.createElement(TAG_ID);
        elementId.appendChild(document.createTextNode(propertyManagerTag.getProperties("armor.protection.id.value")));
        elementName =document.createElement(TAG_NAME);
        elementName.appendChild(document.createTextNode(propertyManagerTag.getProperties("armor.protection.name.value")));
        elementProtection.appendChild(elementId);
        elementProtection.appendChild(elementName);
        elementArmor.appendChild(elementProtection);
        elementAmmunition.appendChild(elementArmor);
        rootElement.appendChild(elementAmmunition);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result;
            result = new StreamResult(new FileWriter("knight.xml"));
            transformer.transform(source, result);
        } catch (IOException | TransformerException e) {
            throw new ParseException(e);
        }
    }
}
