package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.entity.Ammunition;
import com.epam.irasov.xmlknight.entity.Armor;
import com.epam.irasov.xmlknight.entity.Knight;
import com.epam.irasov.xmlknight.entity.Shield;
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
        private Knight knight;// = null;
       // private Ammunition.Type type;
       // private Armor.Protection protection;
        private Shield.Material material;
        private String rootTagBody;
        private String tagBody;
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
                NodeList aList = root.getChildNodes();
                for (int i = 0; i < aList.getLength(); i++) {
                    if (aList.item(i).getNodeName().equals(TAG_AMMUNITION)) {
                        NodeList aaList = aList.item(i).getChildNodes();
                        for (int j = 0; j < aaList.getLength(); j++) {
                            switch (aaList.item(j).getNodeName()) {
                                case TAG_ARMOR:
                                    Element element = (Element) aaList.item(j);
                                    ammunition = new Armor();
                                    knight.addAmmunition(ammunitionParse(element));
                                    break;
                                case TAG_HELMET:

                                    break;
                                case TAG_MELEE_WEAPON:

                                    break;
                                case TAG_RANGED_WEAPON:

                                    break;
                                case TAG_SHIELD:

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
            NodeList ammunitionList = element.getChildNodes();
            for (int i = 0; i < ammunitionList.getLength(); i++) {
                switch (ammunitionList.item(i).getNodeName()) {
                    case TAG_TYPE:
                        Element typeElement = (Element) ammunitionList.item(i);
                        ammunition.setType(parseType(typeElement));
                        break;
                    case TAG_PROTECTION:
                        Element protectionElement = (Element) ammunitionList.item(i);
                        ((Armor)ammunition).setProtection(parseProtection(protectionElement));
                }
            }
            return ammunition;
        }

        private Armor.Protection parseProtection(Element protectionElement) {
            Armor.Protection protection = new Armor.Protection();
            protection.setId(Long.parseLong(getElementTextContent(protectionElement,TAG_ID)));
            protection.setUuid();
            protection.setName(getElementTextContent(protectionElement,TAG_NAME));
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
