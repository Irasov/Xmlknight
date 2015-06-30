package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.entity.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;

public class StAXParseKnightr implements Parsers {
    private XMLInputFactory factory;

    public StAXParseKnightr() {
        factory = XMLInputFactory.newInstance();
    }

    @Override
    public Knight knightParser(InputStream inputStream) {
        XMLStreamReader reader = null;
        String name;
        Knight knight = null;
        // factory = XMLInputFactory.newInstance();
        try {
            reader = factory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if ("knight".equals(name)) {
                        knight = new Knight(reader.getText());
                    }
                }
            }

        } catch (XMLStreamException ex) {
            ex.getMessage();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }

        return knight;
    }
/*
    private Knight createKnight(XMLStreamReader reader) throws XMLStreamException {
        String name;
        Armor armor;
        Helmet helmet;
        MeleeWeapon melleWeapon;
        RangedWeapon rangedWeapon;
        Shield shield;
        Knight knight = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    if ("knightName".equals(name)) {
                        knight = new Knight(reader.getText());
                    }
                 /*   if ("armor".equals(name)) {
                        armor = armorInitialization(reader);
                    }*/

         /*  }
        }
        return knight;

    }*/

    /*

    public Armor armorInitialization(XMLStreamReader reader)  throws XMLStreamException{
        Armor armor = null;
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
            }
            if ("ARMOR".equals(str)) {
                armor.setName(Ammunition.ConstAmmunition.ARMOR);
            }
            if ("weight".equals(nameTag)) {
                armor.setWeight(20/*Integer.parseInt(str)*//*);/*
            }
            if ("price".equals(nameTag)) {
                armor.setPrice(2000/*Integer.parseInt(str)*//*);/*
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
        return armor;
    }*/
}
