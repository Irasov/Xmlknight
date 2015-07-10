package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.entity.Ammunition;
import com.epam.irasov.xmlknight.exception.ParseException;
import com.epam.irasov.xmlknight.util.FileOperation;
import com.epam.irasov.xmlknight.util.PropertyManagerTag;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.epam.irasov.xmlknight.parser.SAXKnightParser.*;

public class DOMCreatorXML {

    public static final String KEY_ID = "tag.id.value";
    public static final String KEY_NAME = "tag.name.value";
    public static final String KEY_PRICE = "tag.price.value";
    public static final String KEY_WEIGHT = "tag.weight.value";
    public static final String KEY_PROTECTION_ID = "tag.protection.id.value";
    public static final String KEY_PROTECTION_NAME = "tag.protection.name.value";
    public static final String KEY_BALACLAVA = "tag.balaclava.value";
    public static final String KEY_TYPE_ID = "tag.type.id.value";
    public static final String KEY_TYPE_NAME = "tag.type.name.value";
    Document document;

    public void creator() {
        Element elementId;
        Element elementName;
        Element elementAmmunition;
        List<String> keys;
        List<String> tagsAmmunition;
        List<String> tagsArmor;
        PropertyManagerTag propertyManagerTag = new PropertyManagerTag();
        propertyManagerTag.loadProperty(FileOperation.load("knightValue.properties"));
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParseException(e);
        }
        document = documentBuilder.newDocument();
        Element rootElement = document.createElement(TAG_KNIGHT);
        document.appendChild(rootElement);
        elementId = document.createElement(TAG_ID);
        elementId.appendChild(document.createTextNode(propertyManagerTag.getProperties("knight.id.value")));
        elementName = document.createElement(TAG_NAME);
        elementName.appendChild(document.createTextNode(propertyManagerTag.getProperties("knight.name.value")));
        rootElement.appendChild(elementId);
        rootElement.appendChild(elementName);
        Element ammunition = document.createElement(TAG_AMMUNITION);
        propertyManagerTag.loadProperty(FileOperation.load("tagsAmmunition.properties"));
        try {
            tagsAmmunition = propertyManagerTag.getPropertyTag();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new ParseException(e);
        }

        for (String anAmmunition : tagsAmmunition) {
            switch (anAmmunition) {
                case TAG_ARMOR:
                    propertyManagerTag.loadProperty(FileOperation.load("armor.properties"));
                    try {
                        tagsArmor = propertyManagerTag.getPropertyTag();
                        keys = propertyManagerTag.getPropertyKey();
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        throw new ParseException(e);
                    }
                    elementAmmunition = document.createElement(TAG_ARMOR);
                    setTagsCommon(tagsArmor, keys, elementAmmunition);
                    ammunition.appendChild(elementAmmunition);
                    break;
                case TAG_HELMET:
                    propertyManagerTag.loadProperty(FileOperation.load("helmet.properties"));
                    try {
                        tagsArmor = propertyManagerTag.getPropertyTag();
                        keys = propertyManagerTag.getPropertyKey();
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        throw new ParseException(e);
                    }
                    elementAmmunition = document.createElement(TAG_HELMET);
                    setTagsCommon(tagsArmor, keys, elementAmmunition);
                    ammunition.appendChild(elementAmmunition);

            }


        }
        rootElement.appendChild(ammunition);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try
        {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result;
            result = new StreamResult(new FileWriter("knight.xml"));
            transformer.transform(source, result);
        } catch (IOException |
                TransformerException e
                )

        {
            throw new ParseException(e);
        }

    }

    public void setTagsCommon(List<String> ammunition, List<String> keys, Element elementAmmunition) {
        Element element;
        Element additionTags = null;
        String key;
        for (int i = 0; i < keys.size(); i++) {
            key = keys.get(i);
            switch (key) {
                case KEY_ID:
                    element = document.createElement(TAG_ID);
                    element.appendChild(document.createTextNode(ammunition.get(i)));
                    elementAmmunition.appendChild(element);
                    break;
                case KEY_NAME:
                    element = document.createElement(TAG_NAME);
                    element.appendChild(document.createTextNode(ammunition.get(i)));
                    elementAmmunition.appendChild(element);
                    break;
                case KEY_WEIGHT:
                    element = document.createElement(TAG_WEIGHT);
                    element.appendChild(document.createTextNode(ammunition.get(i)));
                    elementAmmunition.appendChild(element);
                    break;
                case KEY_PRICE:
                    element = document.createElement(TAG_PRICE);
                    element.appendChild(document.createTextNode(ammunition.get(i)));
                    elementAmmunition.appendChild(element);
                    break;
                case KEY_TYPE_ID:
                    additionTags = document.createElement(TAG_TYPE);
                    element = document.createElement(TAG_ID);
                    element.appendChild(document.createTextNode(ammunition.get(i)));
                    additionTags.appendChild(element);
                    break;
                case KEY_TYPE_NAME:
                    element = document.createElement(TAG_NAME);
                    element.appendChild(document.createTextNode(ammunition.get(i)));
                    assert additionTags != null;
                    additionTags.appendChild(element);
                    elementAmmunition.appendChild(additionTags);
                    break;
                case KEY_PROTECTION_ID:
                    element = document.createElement(TAG_ID);
                    element.appendChild(document.createTextNode(ammunition.get(i)));
                    assert additionTags != null;
                    additionTags.appendChild(element);
                    break;
                case KEY_PROTECTION_NAME:
                    additionTags = document.createElement(TAG_PROTECTION);
                    element = document.createElement(TAG_NAME);
                    element.appendChild(document.createTextNode(ammunition.get(i)));
                    additionTags.appendChild(element);
                    elementAmmunition.appendChild(additionTags);
                    break;
                case KEY_BALACLAVA:
                    element = document.createElement(TAG_BALACLAVA);
                    element.appendChild(document.createTextNode(ammunition.get(i)));
                    elementAmmunition.appendChild(element);
                    break;
            }
        }
    }

}
