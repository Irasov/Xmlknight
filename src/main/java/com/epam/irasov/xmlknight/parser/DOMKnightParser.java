package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.entity.*;
import com.epam.irasov.xmlknight.exception.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static com.epam.irasov.xmlknight.parser.SAXKnightParser.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class DOMKnightParser implements Parser {
    @Override
    public Knight knightParser(InputStream inputStream) {
        DOMParser domParser = new DOMParser();
        return domParser.parse(inputStream);
    }

    private static class DOMParser {
        private Knight knight;
        private Ammunition ammunition;
        private DocumentBuilder docBuilder;

        public Knight parse(InputStream inputStream) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                docBuilder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new ParseException(e);
            }
            return startParse(inputStream);
        }

        public Knight getKnight() {
            return knight;
        }

        public Knight startParse(InputStream inputStream) {
            Document document;
            try {
                document = docBuilder.parse(inputStream);
                Element root = document.getDocumentElement();
                knight = new Knight();
                knight.setId(Long.valueOf(getElementTextContent(root, TAG_ID)));
                knight.setUuid();
                knight.setName(getElementTextContent(root, TAG_NAME));
                NodeList childNodesKnight = root.getChildNodes();
                Element element;
                for (int i = 0; i < childNodesKnight.getLength(); i++) {
                    if (childNodesKnight.item(i).getNodeName().equals(TAG_AMMUNITION)) {
                        NodeList childNodesAmmunition = childNodesKnight.item(i).getChildNodes();
                        for (int j = 0; j < childNodesAmmunition.getLength(); j++) {
                            switch (childNodesAmmunition.item(j).getNodeName()) {
                                case TAG_ARMOR:
                                    element = (Element) childNodesAmmunition.item(j);
                                    ammunition = new Armor();
                                    knight.addAmmunition(ammunitionParse(element));
                                    break;
                                case TAG_HELMET:
                                    element = (Element) childNodesAmmunition.item(j);
                                    ammunition = new Helmet();
                                    knight.addAmmunition(ammunitionParse(element));
                                    break;
                                case TAG_MELEE_WEAPON:
                                    element = (Element) childNodesAmmunition.item(j);
                                    ammunition = new MeleeWeapon();
                                    knight.addAmmunition(ammunitionParse(element));
                                    break;
                                case TAG_RANGED_WEAPON:
                                    element = (Element) childNodesAmmunition.item(j);
                                    ammunition = new RangedWeapon();
                                    knight.addAmmunition(ammunitionParse(element));
                                    break;
                                case TAG_SHIELD:
                                    element = (Element) childNodesAmmunition.item(j);
                                    ammunition = new Shield();
                                    knight.addAmmunition(ammunitionParse(element));
                                    break;
                            }
                        }
                    }
                }

            } catch (SAXException | IOException e) {
                throw new ParseException(e);
            }
            return getKnight();
        }

        private Ammunition ammunitionParse(Element element) {
            ammunition.setId(Long.parseLong(getElementTextContent(element, TAG_ID)));
            ammunition.setUuid();
            ammunition.setName(getElementTextContent(element, TAG_NAME));
            ammunition.setWeight(Integer.parseInt(getElementTextContent(element, TAG_WEIGHT)));
            ammunition.setPrice(Integer.parseInt(getElementTextContent(element, TAG_PRICE)));
            NodeList childNodesElementAmmunition = element.getChildNodes();
            for (int i = 0; i < childNodesElementAmmunition.getLength(); i++) {
                switch (childNodesElementAmmunition.item(i).getNodeName()) {
                    case TAG_TYPE:
                        Element typeElement = (Element) childNodesElementAmmunition.item(i);
                        ammunition.setType(parseType(typeElement));
                        break;
                    case TAG_PROTECTION:
                        Element protectionElement = (Element) childNodesElementAmmunition.item(i);
                        ((Armor) ammunition).setProtection(parseProtection(protectionElement));
                        break;
                    case TAG_BALACLAVA:
                        ((Helmet) ammunition).setBalaclava(Boolean.parseBoolean(getElementTextContent(element, TAG_BALACLAVA)));
                        break;
                    case TAG_LENGTH:
                        ((MeleeWeapon) ammunition).setLengthWeapon(Integer.parseInt(getElementTextContent(element, TAG_LENGTH)));
                        break;
                    case TAG_NUMBER_OF_SHELLS:
                        ((RangedWeapon) ammunition).setNumberOfShells(Integer.parseInt(getElementTextContent(element, TAG_NUMBER_OF_SHELLS)));
                        break;
                    case TAG_CAPTURED:
                        ((Weapon) ammunition).setCaptured(Boolean.parseBoolean(getElementTextContent(element, TAG_CAPTURED)));
                        break;
                    case TAG_MATERIAL:
                        Element materialElement = (Element) childNodesElementAmmunition.item(i);
                        ((Shield)ammunition).setMaterial(parseMaterial(materialElement));
                }
            }
            return ammunition;
        }

        private Shield.Material parseMaterial(Element materialElement) {
            Shield.Material material = new Shield.Material();
            material.setId(Long.parseLong(getElementTextContent(materialElement,TAG_ID)));
            material.setUuid();
            material.setName(getElementTextContent(materialElement, TAG_NAME));
            return material;
        }

        private Armor.Protection parseProtection(Element protectionElement) {
            Armor.Protection protection = new Armor.Protection();
            protection.setId(Long.parseLong(getElementTextContent(protectionElement, TAG_ID)));
            protection.setUuid();
            protection.setName(getElementTextContent(protectionElement, TAG_NAME));
            return protection;
        }

        private Ammunition.Type parseType(Element typeElement) {
            Ammunition.Type type = new Ammunition.Type();
            type.setId(Long.parseLong(getElementTextContent(typeElement, TAG_ID)));
            type.setUuid();
            type.setName(getElementTextContent(typeElement, TAG_NAME));
            return type;
        }

        private static String getElementTextContent(Element element, String elementName) {
            NodeList nodeList = element.getElementsByTagName(elementName);
            Node node = nodeList.item(0);
            return node.getTextContent();
        }


    }
}
