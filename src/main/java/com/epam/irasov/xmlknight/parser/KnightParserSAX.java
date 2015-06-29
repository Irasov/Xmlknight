package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.entity.*;
import com.epam.irasov.xmlknight.exception.KnightErrorHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.epam.irasov.xmlknight.exception.ParseException;

public class KnightParserSAX extends AbstractParser {
    /*
        public void SchemaParser(InputStream inputStream) {
            Schema schema = null;
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
            try {
                schema = schemaFactory.newSchema();//(new File()(KnightParserSAX.class.getClassLoader().getResourceAsStream("knight.xsd")));
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
        private Knight knight = null;
        String nameTag;
        String ammunitionName;
        private Armor armor = null;
        private Helmet helmet;
        private MelleWeapon melleWeapon;
        private RangedWeapon rangedWeapon;
        private Shield shield;
        String knightName;

        public Knight getKnight() {
            return knight;
        }

        @Override
        public void startDocument() throws SAXException {
            System.out.println("Start");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("end");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("knightName".equals(qName)) {
                ammunitionName = qName;
            }
            if ("armor".equals(qName)) {
                armor = new Armor();
                ammunitionName = qName;
            }
            if ("helmet".equals(qName)) {
                helmet = new Helmet();
                ammunitionName = qName;
            }
            if ("mellewWapon".equals(qName)) {
                melleWeapon = new MelleWeapon();
                ammunitionName = qName;
            }
            if ("rangedWeapon".equals(qName)) {
                rangedWeapon = new RangedWeapon();
                ammunitionName = qName;
            }
            if ("shield".equals(qName)) {
                shield = new Shield();
                ammunitionName = qName;
            }
            nameTag = qName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("knightName".equals(qName)) {
                knight = new Knight(knightName);
            }
            if ("armor".equals(qName)) {
                knight.addAmmunition(armor);
            }
            if ("helmet".equals(qName)) {
                knight.addAmmunition(helmet);
            }
            if ("mellewWapon".equals(qName)) {
                knight.addAmmunition(melleWeapon);
            }
            if ("rangedWeapon".equals(qName)) {
                knight.addAmmunition(rangedWeapon);
            }
            if ("shield".equals(qName)) {
                knight.addAmmunition(shield);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String str = new String(ch, start, length).trim();
            if ("knightName".equals(ammunitionName)) {
                knightName = str;
            }
            if ("armor".equals(ammunitionName)) {
                armorInitialization(str);
            }
            if ("helmet".equals(ammunitionName)) {
                helmetInitialization(str);
            }
            if ("mellewWapon".equals(ammunitionName)) {
                melleWeaponInitialization(str);
            }
            if ("rangedWeapon".equals(ammunitionName)) {
                rangedWeaponInitialization(str);
            }
            if ("shield".equals(ammunitionName)) {
                shieldInitialization(str);
            }

        }

        private void shieldInitialization(String str) {
            if ("SHIELD".equals(str)) {
                shield.setName(Ammunition.ConstAmmunition.SHIELD);
            }
            if ("weight".equals(nameTag)) {
                shield.setWeight(5/*Integer.parseInt(str)*/);
            }
            if ("price".equals(nameTag)) {
                shield.setPrice(1700/*Integer.parseInt(str)*/);
            }
            if ("CROWS".equals(str)) {
                shield.setType(Shield.ShieldType.CROWS);
            }
            if ("NORMAN".equals(str)) {
                shield.setType(Shield.ShieldType.NORMAN);
            }
            if ("BUCKLER".equals(str)) {
                shield.setType(Shield.ShieldType.BUCKLER);
            }
            if ("TORCH".equals(str)) {
                shield.setType(Shield.ShieldType.TORCH);
            }
            if ("PAVESE".equals(str)) {
                shield.setType(Shield.ShieldType.PAVESE);
            }
            if ("METAL".equals(str)) {
                shield.setMaterial(Shield.ShieldMaterial.METAL);
            }
            if ("TREE".equals(str)) {
                shield.setMaterial(Shield.ShieldMaterial.TREE);
            }
        }

        private void rangedWeaponInitialization(String str) {
            if ("RANGED_WEAPON".equals(str)) {
                rangedWeapon.setName(Ammunition.ConstAmmunition.RANGED_WEAPON);
            }
            if ("weight".equals(nameTag)) {
                rangedWeapon.setWeight(1/*Integer.parseInt(str)*/);
            }
            if ("price".equals(nameTag)) {
                rangedWeapon.setPrice(800/*Integer.parseInt(str)*/);
            }
            if ("CAPTURED".equals(str)) {
                rangedWeapon.setCaptured(Weapon.Captured.CAPTURED);
            }
            if ("NOT_CAPTURED".equals(str)) {
                rangedWeapon.setCaptured(Weapon.Captured.NOT_CAPTURED);
            }
            if ("BOW".equals(str)) {
                rangedWeapon.setType(RangedWeapon.RangedWeaponType.BOW);
            }
            if ("CROSS_BOW".equals(str)) {
                rangedWeapon.setType(RangedWeapon.RangedWeaponType.CROSS_BOW);
            }
            if ("JAVELIN".equals(str)) {
                rangedWeapon.setType(RangedWeapon.RangedWeaponType.JAVELIN);
            }
            if ("numberOfShells".equals(nameTag)) {
                rangedWeapon.setNumberOfShells(15);
            }
        }

        private void melleWeaponInitialization(String str) {
            if ("MELLE_WEAPON".equals(str)) {
                melleWeapon.setName(Ammunition.ConstAmmunition.MELLE_WEAPON);
            }
            if ("weight".equals(nameTag)) {
                melleWeapon.setWeight(2/*Integer.parseInt(str)*/);
            }
            if ("price".equals(nameTag)) {
                melleWeapon.setPrice(1900/*Integer.parseInt(str)*/);
            }
            if ("CAPTURED".equals(str)) {
                melleWeapon.setCaptured(Weapon.Captured.CAPTURED);
            }
            if ("NOT_CAPTURED".equals(str)) {
                melleWeapon.setCaptured(Weapon.Captured.NOT_CAPTURED);
            }
            if ("SWORD".equals(str)) {
                melleWeapon.setType(MelleWeapon.MelleWeaponType.SWORD);
            }
            if ("DAGER".equals(str)) {
                melleWeapon.setType(MelleWeapon.MelleWeaponType.DAGER);
            }
            if ("MACE".equals(str)) {
                melleWeapon.setType(MelleWeapon.MelleWeaponType.MACE);
            }
            if ("SPEAR".equals(str)) {
                melleWeapon.setType(MelleWeapon.MelleWeaponType.SPEAR);
            }
            if ("lengthWeapon".equals(nameTag)) {
                melleWeapon.setLength_weapon(70);
            }
        }

        private void helmetInitialization(String str) {
            if ("HELMET".equals(str)) {
                helmet.setName(Ammunition.ConstAmmunition.HELMET);
            }
            if ("weight".equals(nameTag)) {
                helmet.setWeight(10/*Integer.parseInt(str)*/);
            }
            if ("price".equals(nameTag)) {
                helmet.setPrice(1500/*Integer.parseInt(str)*/);
            }
            if ("NORMAN".equals(str)) {
                helmet.setType(Helmet.HelmetType.NORMAN);
            }
            if ("DORIE".equals(str)) {
                helmet.setType(Helmet.HelmetType.DORIE);
            }
            if ("DEAF".equals(str)) {
                helmet.setType(Helmet.HelmetType.DEAF);
            }
            if ("THERE_BALACLAVA".equals(str)) {
                helmet.setBalaclava(Helmet.ConstBalaclava.THERE_BALACLAVA);
            }
            if ("NO_BALACLAVA".equals(str)) {
                helmet.setBalaclava(Helmet.ConstBalaclava.NO_BALACLAVA);
            }
        }

        public void armorInitialization(String str) {
            if ("ARMOR".equals(str)) {
                armor.setName(Ammunition.ConstAmmunition.ARMOR);
            }
            if ("weight".equals(nameTag)) {
                armor.setWeight(20/*Integer.parseInt(str)*/);
            }
            if ("price".equals(nameTag)) {
                armor.setPrice(2000/*Integer.parseInt(str)*/);
            }
            if ("CHAIN_ARMOR".equals(str)) {
                armor.setType(Armor.ArmorType.CHAIN_ARMOR);
            }
            if ("ARMOR".equals(str)) {
                armor.setType(Armor.ArmorType.ARMOR);
            }
            if ("FULLY".equals(str)) {
                armor.setProtection(Armor.ConstProtection.FULLY);
            }
            if ("BREAST".equals(str)) {
                armor.setProtection(Armor.ConstProtection.BREAST);
            }
            if ("CHEST_AND_ARMS".equals(str)) {
                armor.setProtection(Armor.ConstProtection.CHEST_AND_ARMS);
            }
            if ("CHEST_AND_BACK".equals(str)) {
                armor.setProtection(Armor.ConstProtection.CHEST_AND_BACK);
            }
            if ("CHEST_AND_LEGS".equals(str)) {
                armor.setProtection(Armor.ConstProtection.CHEST_AND_LEGS);
            }
        }
    }
}


