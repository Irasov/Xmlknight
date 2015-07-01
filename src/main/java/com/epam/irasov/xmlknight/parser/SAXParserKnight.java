package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

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
        private Knight knight;
        private Armor armor;
        private Helmet helmet;
        private MeleeWeapon meleeWeapon;
        private RangedWeapon rangedWeapon;
        private Shield shield;
        private StringBuilder sb = new StringBuilder();
        private String tagBody;
        private String nameAmmunition = "";

        public Knight getKnight() {
            return knight;
        }

       /* @Override
        public void startDocument() throws SAXException {
            System.out.println("Start");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("end");
        }*/

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName) {
                case "knight":
                    knight = new Knight();
                    break;
                case "armor":
                    armor = new Armor();
                    nameAmmunition = qName;
                    break;
                case "helmet":
                    helmet = new Helmet();
                    nameAmmunition = qName;
                    break;
                case "meleeWeapon":
                    meleeWeapon = new MeleeWeapon();
                    nameAmmunition = qName;
                    break;
                case "rangedWeapon":
                    rangedWeapon = new RangedWeapon();
                    nameAmmunition = qName;
                    break;
                case "shield":
                    shield = new Shield();
                    nameAmmunition = qName;
                default:
                    sb.setLength(0);
                    tagBody = qName;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

            switch (nameAmmunition) {
                case "armor":
                    initializerArmor(sb.toString());
                    break;
                case "helmet":
                    initializerHelmet(sb.toString());
                    break;
                case "meleeWeapon":
                    initializerMeleeWeapon(sb.toString());
                    break;
                case "rangedWeapon":
                    initializerRangedWeapon(sb.toString());
                    break;
                case "shield":
                    initializerShield(sb.toString());
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
                case "armor":
                    knight.addAmmunition(armor);
                    break;
                case "helmet":
                    knight.addAmmunition(helmet);
                    break;
                case "meleeWeapon":
                    knight.addAmmunition(meleeWeapon);
                    break;
                case "rangedWeapon":
                    knight.addAmmunition(rangedWeapon);
                    break;
                case "shield":
                    knight.addAmmunition(shield);
                    break;
            }

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String str = new String(ch, start, length).trim();
            sb.append(str);
        }

        private void initializerArmor(String s) {
            switch (tagBody) {
                case "id":
                    armor.setId(Long.valueOf(sb.toString()));
                    armor.setUuid();
                    break;
                case "name":
                    armor.setName(s);
                    break;
                case "weight":
                    armor.setWeight(Integer.parseInt(s));
                    break;
                case "price":
                    armor.setPrice(Integer.parseInt(s));
                    break;
                case "type":
                    Armor.Type type = new Armor.Type();
                    type.setName(s);
                    armor.setType(type);
                    break;
                case "protection":
                    Armor.Protection protection = new Armor.Protection();
                    protection.setName(s);
                    armor.setProtection(protection);
                    break;
            }
        }

        private void initializerHelmet(String s) {
            switch (tagBody) {
                case "id":
                    helmet.setId(Long.valueOf(sb.toString()));
                    helmet.setUuid();
                    break;
                case "name":
                    helmet.setName(s);
                    break;
                case "weight":
                    helmet.setWeight(Integer.parseInt(s));
                    break;
                case "price":
                    helmet.setPrice(Integer.parseInt(s));
                    break;
                case "type":
                    Helmet.Type type = new Helmet.Type();
                    type.setName(s);
                    helmet.setType(type);
                    break;
                case "balaclava":
                    helmet.setBalaclava(Boolean.getBoolean(s));
                    break;
            }
        }

        private void initializerMeleeWeapon(String s) {
            switch (tagBody) {
                case "id":
                    meleeWeapon.setId(Long.valueOf(sb.toString()));
                    meleeWeapon.setUuid();
                    break;
                case "name":
                    meleeWeapon.setName(s);
                    break;
                case "weight":
                    meleeWeapon.setWeight(Integer.parseInt(s));
                    break;
                case "price":
                    meleeWeapon.setPrice(Integer.parseInt(s));
                    break;
                case "captured":
                    meleeWeapon.setCaptured(Boolean.valueOf(s));
                    break;
                case "type":
                    MeleeWeapon.Type type = new MeleeWeapon.Type();
                    type.setName(s);
                    meleeWeapon.setType(type);
                    break;
                case "length":
                    meleeWeapon.setLengthWeapon(Integer.parseInt(s));
                    break;
            }
        }

        private void initializerRangedWeapon(String s) {
            switch (tagBody) {
                case "id":
                    rangedWeapon.setId(Long.valueOf(sb.toString()));
                    rangedWeapon.setUuid();
                    break;
                case "name":
                    rangedWeapon.setName(s);
                    break;
                case "weight":
                    rangedWeapon.setWeight(Integer.parseInt(s));
                    break;
                case "price":
                    rangedWeapon.setPrice(Integer.parseInt(s));
                    break;
                case "captured":
                    rangedWeapon.setCaptured(Boolean.valueOf(s));
                    break;
                case "type":
                    RangedWeapon.Type type = new RangedWeapon.Type();
                    type.setName(s);
                    rangedWeapon.setType(type);
                    break;
                case "numberOfShells":
                    rangedWeapon.setNumberOfShells(Integer.parseInt(s));
                    break;
            }
        }

        private void initializerShield(String s) {
            switch (tagBody) {
                case "id":
                    shield.setId(Long.valueOf(sb.toString()));
                    shield.setUuid();
                    break;
                case "name":
                    shield.setName(s);
                    break;
                case "weight":
                    shield.setWeight(Integer.parseInt(s));
                    break;
                case "price":
                    shield.setPrice(Integer.parseInt(s));
                    break;
                case "type":
                    Shield.Type type = new Shield.Type();
                    type.setName(s);
                    shield.setType(type);
                    break;
                case "material":
                    Shield.Material material = new Shield.Material();
                    material.setName(s);
                    shield.setMaterial(material);
                    break;
            }
        }


    }
}


