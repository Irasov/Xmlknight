package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.entity.*;
import com.epam.irasov.xmlknight.util.PropertyManager;
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

public class SAXParserKnight implements Parsers {

    /*
        public void SchemaParser(InputStream inputStream) {
            Schema schema = null;
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
            try {
                schema = schemaFactory.newSchema();//(new File()(SAXParserKnight.class.getClassLoader().getResourceAsStream("knight.xsd")));
                SAXParserFactory spf = SAXParserFactory.newInstance();
                spf.setSchema(schema);
                SAXParser parser = spf.newSAXParser();
                parser.parse(inputStream, new KnightErrorHandler());
            } catch (SAXException | ParserConfigurationException |IOException e) {
                e.getMessage();
            }
        }
    */
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
        private Map<String, Ammunition> ammunitions = new HashMap<>();
        private Knight knight;
        private List<Ammunition> ammunitionList;
        private int numberAmmunition = -1;
        private StringBuilder sb = new StringBuilder();
        private String tagBody;
        private String nameAmmunition = "";

        public Knight getKnight() {
            return knight;
        }

        @Override
        public void startDocument() throws SAXException {
            PropertyManager propertyManager = new PropertyManager();
            propertyManager.loadProperty();

            try {
                ammunitions = propertyManager.getPropertyAmmunition();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace(); // remake in Logger
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
                case "knight":
                    knight = new Knight();
                    break;
            }
            for (Map.Entry<String, Ammunition> entry : ammunitions.entrySet()) {
                if (qName.equals(entry.getKey())) {
                    ammunitionList.add(entry.getValue());
                    nameAmmunition = qName;
                    numberAmmunition++;
                }
                tagBody = qName;
                sb.setLength(0);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (nameAmmunition) {
                case "armor":
                    initializerAmmunition(numberAmmunition, nameAmmunition, sb.toString());
                    break;
                case "helmet":
                    initializerAmmunition(numberAmmunition, nameAmmunition, sb.toString());
                    break;
                case "meleeWeapon":
                    initializerAmmunition(numberAmmunition, nameAmmunition, sb.toString());
                    break;
                case "rangedWeapon":
                    initializerAmmunition(numberAmmunition, nameAmmunition, sb.toString());
                    break;
                case "shield":
                    initializerAmmunition(numberAmmunition, nameAmmunition, sb.toString());
                    break;
            }

            switch (qName) {
                case "id":
                    knight.setId(Long.valueOf(sb.toString()));
                    knight.setUuid();
                    break;
                case "knightName":
                    knight.setKnightName(sb.toString());
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String str = new String(ch, start, length).trim();
            sb.append(str);
        }

        private void initializerAmmunition(int numberAmmunition, String nameAmmunition, String s) {
            switch (tagBody) {
                case "id":
                    ammunitionList.get(numberAmmunition).setId(Long.valueOf(sb.toString()));
                    ammunitionList.get(numberAmmunition).setUuid();
                    break;
                case "name":
                    ammunitionList.get(numberAmmunition).setName(s);
                    break;
                case "weight":
                    ammunitionList.get(numberAmmunition).setWeight(Integer.parseInt(s));
                    break;
                case "price":
                    ammunitionList.get(numberAmmunition).setPrice(Integer.parseInt(s));
                    break;
            }
            switch (nameAmmunition) {
                case "armor":
                    initializerArmor(s);
                    break;
                case "helmet":
                    initializerHelmet(s);
                    break;
                case "meleeWeapon":
                    initializerMeleeWeapon(s);
                    break;
                case "rangedWeapon":
                    initializerRangedWeapon(s);
                    break;
                case "shield":
                    initializerShield(s);
                    break;
            }

        }

        private void initializerArmor(String s) {
            switch (tagBody) {
                case "type":
                    Armor.Type type = new Armor.Type();
                    type.setName(s);
                    ammunitionList.get(numberAmmunition).setType(type);
                    break;
                case "protection":
                    Armor.Protection protection = new Armor.Protection();
                    protection.setName(s);
                    ((Armor) ammunitionList.get(numberAmmunition)).setProtection(protection);
                    break;
            }
        }

        private void initializerHelmet(String s) {
            switch (tagBody) {
                case "type":
                    Helmet.Type type = new Helmet.Type();
                    type.setName(s);
                    ammunitionList.get(numberAmmunition).setType(type);
                    break;
                case "balaclava":
                    ((Helmet) ammunitionList.get(numberAmmunition)).setBalaclava(Boolean.getBoolean(s));
                    break;
            }
        }

        private void initializerMeleeWeapon(String s) {
            switch (tagBody) {
                case "captured":
                    ((MeleeWeapon) ammunitionList.get(numberAmmunition)).setCaptured(Boolean.valueOf(s));
                    break;
                case "type":
                    MeleeWeapon.Type type = new MeleeWeapon.Type();
                    type.setName(s);
                    ammunitionList.get(numberAmmunition).setType(type);
                    break;
                case "length":
                    ((MeleeWeapon) ammunitionList.get(numberAmmunition)).setLengthWeapon(Integer.parseInt(s));
                    break;
            }
        }

        private void initializerRangedWeapon(String s) {
            switch (tagBody) {
                case "captured":
                    ((RangedWeapon) ammunitionList.get(numberAmmunition)).setCaptured(Boolean.valueOf(s));
                    break;
                case "type":
                    RangedWeapon.Type type = new RangedWeapon.Type();
                    type.setName(s);
                    ammunitionList.get(numberAmmunition).setType(type);
                    break;
                case "numberOfShells":
                    ((RangedWeapon) ammunitionList.get(numberAmmunition)).setNumberOfShells(Integer.parseInt(s));
                    break;
            }
        }

        private void initializerShield(String s) {
            switch (tagBody) {
                case "type":
                    Shield.Type type = new Shield.Type();
                    type.setName(s);
                    ammunitionList.get(numberAmmunition).setType(type);
                    break;
                case "material":
                    Shield.Material material = new Shield.Material();
                    material.setName(s);
                    ((Shield) ammunitionList.get(numberAmmunition)).setMaterial(material);
                    break;
            }
        }
    }
}


