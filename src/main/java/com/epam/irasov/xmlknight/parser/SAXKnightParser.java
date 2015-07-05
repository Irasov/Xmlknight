package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.entity.*;
import com.epam.irasov.xmlknight.util.FileOperation;
import com.epam.irasov.xmlknight.util.PropertyManagerAmmunition;
import com.epam.irasov.xmlknight.util.PropertyManagerBodyTag;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.irasov.xmlknight.exception.ParseException;

public class SAXKnightParser implements Parser {
    private static Logger LOGGER = Logger.getLogger(SAXKnightParser.class);

    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_PRICE = "price";
    private static final String TAG_WEIGHT = "weight";
    private static final String TAG_KNIGHT = "knight";
    private static final String TAG_ARMOR = "armor";
    private static final String TAG_HELMET = "helmet";
    private static final String TAG_MELEE_WEAPON = "meleeWeapon";
    private static final String TAG_RANGED_WEAPON = "rangedWeapon";
    private static final String TAG_SHIELD = "shield";
    private static final String TAG_PROTECTION = "protection";
    private static final String TAG_BALACLAVA = "balaclava";
    private static final String TAG_MATERIAL = "material";
    private static final String TAG_TYPE = "type";
    private static final String TAG_CAPTURED = "captured";
    private static final String TAG_LENGTH = "length";
    private static final String TAG_NUMBER_OF_SHELLS = "numberOfShells";


    private static final String AMMUNITION_PROPERTIES = "ammunition.properties";
    private static final String SECOND_BODY_TAG_PROPERTIES = "BodyTagAdditions.properties";
    private static final String ROOT_BODY_TAG_PROPERTIES = "rootTagBody.properties";

    @Override
    public Knight knightParser(InputStream inputStream) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        Knight knight;
        try {
            saxParser = factory.newSAXParser();
            KnightHandler handler = new KnightHandler();
            saxParser.parse(inputStream, handler);
            knight = handler.getKnight();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParseException(e);
        }
        return knight;
    }

    private static class KnightHandler extends DefaultHandler {
        private Map<String, String> ammunition = new HashMap<>();
        private Knight knight;
        private List<Ammunition> ammunitionList;
        private List<String> secondTagBodyList;
        private List<String> rootTagBodyList;
        private Ammunition.Type type;
        private Armor.Protection protection;
        private Shield.Material material;
        private int numberAmmunition = -1;
        private StringBuilder sb = new StringBuilder();
        private String firstTagBody;
        private String rootTagBody;
        private String secondTagBody = "";
        Class cl;

        public Knight getKnight() {
            return knight;
        }

        @Override
        public void startDocument() throws SAXException {
            PropertyManagerBodyTag propertyBodyTag = new PropertyManagerBodyTag();
            PropertyManagerAmmunition propertyAmmunition = new PropertyManagerAmmunition();
            propertyBodyTag.loadProperty(FileOperation.LoadProperty(SECOND_BODY_TAG_PROPERTIES));
            try {
                secondTagBodyList = propertyBodyTag.getPropertyBodyTag();
                propertyBodyTag.loadProperty(FileOperation.LoadProperty(ROOT_BODY_TAG_PROPERTIES));
                rootTagBodyList = propertyBodyTag.getPropertyBodyTag();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                LOGGER.info(e);
            }

            propertyAmmunition.loadProperty(FileOperation.LoadProperty(AMMUNITION_PROPERTIES));
            try {
                ammunition = propertyAmmunition.getPropertyAmmunition();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                LOGGER.info(e);
            }
            ammunitionList = new ArrayList<>();
        }

        @Override
        public void endDocument() throws SAXException {
            knight.setAmmunitionList((ArrayList<Ammunition>) ammunitionList);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName) {
                case TAG_KNIGHT:
                    knight = new Knight();
                    rootTagBody = qName;
                    break;
            }
            for (String TagBody : secondTagBodyList) {
                if (qName.equals(TagBody))
                    secondTagBody = qName;
            }

            for (Map.Entry<String, String> entry : ammunition.entrySet()) {
                if (qName.equals(entry.getKey())) {
                    try {
                        cl = Class.forName(entry.getValue());
                    } catch (ClassNotFoundException e) {
                        LOGGER.info(e);
                    }
                    try {
                        ammunitionList.add((Ammunition) cl.newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        LOGGER.info(e);
                    }
                    rootTagBody = qName;
                    numberAmmunition++;
                    type = new Ammunition.Type();
                    if (TAG_ARMOR.equals(qName)) protection = new Armor.Protection();
                    else if (TAG_SHIELD.equals(qName)) material = new Shield.Material();
                }
                firstTagBody = qName;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

            if (TAG_KNIGHT.equals(rootTagBody)) {
                switch (qName) {
                    case TAG_ID:
                        knight.setId(Long.valueOf(sb.toString()));
                        knight.setUuid();
                        break;
                    case TAG_NAME:
                        knight.setKnightName(sb.toString());
                        break;
                }
            }

            for (String TagBody : rootTagBodyList) {
                if (rootTagBody.equals(TagBody))
                    initializerAmmunition(numberAmmunition, sb.toString());
            }

            switch (qName) {
                case TAG_TYPE:
                    type = new Ammunition.Type();
                    secondTagBody = "";
                    break;
                case TAG_PROTECTION:
                    secondTagBody = "";
                    break;
                case TAG_MATERIAL:
                    secondTagBody = "";
            }
            sb.setLength(0);
            firstTagBody = "";
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String str = new String(ch, start, length).trim();
            sb.append(str);
        }

        private void initializerAmmunition(int numberAmmunition, String s) {
            switch (firstTagBody) {
                case TAG_ID:
                    if ((!TAG_TYPE.equals(secondTagBody)) && (!TAG_PROTECTION.equals(secondTagBody)) && (!TAG_MATERIAL.equals(secondTagBody))) {
                        ammunitionList.get(numberAmmunition).setId(Long.valueOf(sb.toString()));
                        ammunitionList.get(numberAmmunition).setUuid();
                    }
                    break;
                case TAG_NAME:
                    if ((!TAG_TYPE.equals(secondTagBody)) && (!TAG_PROTECTION.equals(secondTagBody)) && (!TAG_MATERIAL.equals(secondTagBody))) {
                        ammunitionList.get(numberAmmunition).setName(s);
                    }
                    break;
                case TAG_WEIGHT:
                    ammunitionList.get(numberAmmunition).setWeight(Integer.parseInt(s));
                    break;
                case TAG_PRICE:
                    ammunitionList.get(numberAmmunition).setPrice(Integer.parseInt(s));
                    break;
            }

            if (TAG_TYPE.equals(secondTagBody)) initializerAmmunitionType(s);

            switch (rootTagBody) {
                case TAG_ARMOR:
                    if (TAG_PROTECTION.equals(secondTagBody)) initializerArmorAdditions(s);
                    break;
                case TAG_HELMET:
                    if (TAG_BALACLAVA.equals(firstTagBody)) initializerHelmetAdditions(s);
                    break;
                case TAG_MELEE_WEAPON:
                    if ((TAG_CAPTURED.equals(firstTagBody)) || (TAG_LENGTH.equals(firstTagBody)))
                        initializerMeleeWeaponAdditions(s);
                    break;
                case TAG_RANGED_WEAPON:
                    if ((TAG_CAPTURED.equals(firstTagBody)) || (TAG_NUMBER_OF_SHELLS.equals(firstTagBody)))
                        initializerRangedWeaponAdditions(s);
                    break;
                case TAG_SHIELD:
                    if (TAG_MATERIAL.equals(secondTagBody)) initializerShieldAdditions(s);
                    break;
            }

        }

        private void initializerAmmunitionType(String s) {
            switch (firstTagBody) {
                case TAG_ID:
                    type.setId(Long.valueOf(s));
                    type.setUuid();
                    break;
                case TAG_NAME:
                    type.setName(s);
                    ammunitionList.get(numberAmmunition).setType(type);
                    break;
            }
        }

        private void initializerArmorAdditions(String s) {
            switch (firstTagBody) {
                case TAG_ID:
                    protection.setId(Long.valueOf(s));
                    protection.setUuid();
                    break;
                case TAG_NAME:
                    protection.setName(s);
                    ((Armor) ammunitionList.get(numberAmmunition)).setProtection(protection);
                    break;
            }
        }

        private void initializerHelmetAdditions(String s) {
            switch (firstTagBody) {
                case TAG_BALACLAVA:
                    System.out.println(s);
                    ((Helmet) ammunitionList.get(numberAmmunition)).setBalaclava(Boolean.parseBoolean(s));
                    break;
            }
        }

        private void initializerMeleeWeaponAdditions(String s) {
            switch (firstTagBody) {
                case TAG_CAPTURED:
                    ((MeleeWeapon) ammunitionList.get(numberAmmunition)).setCaptured(Boolean.parseBoolean(s));
                    break;
                case TAG_LENGTH:
                    ((MeleeWeapon) ammunitionList.get(numberAmmunition)).setLengthWeapon(Integer.parseInt(s));
                    break;
            }
        }

        private void initializerRangedWeaponAdditions(String s) {
            switch (firstTagBody) {
                case TAG_CAPTURED:
                    ((RangedWeapon) ammunitionList.get(numberAmmunition)).setCaptured(Boolean.parseBoolean(s));
                    break;
                case TAG_NUMBER_OF_SHELLS:
                    ((RangedWeapon) ammunitionList.get(numberAmmunition)).setNumberOfShells(Integer.parseInt(s));
                    break;
            }
        }

        private void initializerShieldAdditions(String s) {
            switch (firstTagBody) {
                case TAG_ID:
                    material.setId(Long.valueOf(s));
                    material.setUuid();
                    break;
                case TAG_NAME:
                    material.setName(s);
                    ((Shield) ammunitionList.get(numberAmmunition)).setMaterial(material);
                    break;
            }
        }
    }
}


