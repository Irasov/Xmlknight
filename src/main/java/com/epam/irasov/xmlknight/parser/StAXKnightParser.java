package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.entity.*;
import com.epam.irasov.xmlknight.exception.ParseException;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.*;

public class StAXKnightParser implements Parser {

    private static final String TAG_AMMUNITION = "ammunition";
    private static final String TAG_NAME = "name";
    private static final String TAG_ID = "id";
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

    @Override
    public Knight knightParser(InputStream inputStream) {
        try {
            StAXParser stAXParser = new StAXParser();
            return stAXParser.parse(inputStream);
        } catch (XMLStreamException e) {
            throw new ParseException(e);
        }
    }

    private static class StAXParser {
        private Knight knight;
        private List<Ammunition> ammunitionList = new ArrayList<>();;
        private Ammunition.Type type;
        private Armor.Protection protection;
        private Shield.Material material;
        private String rootTagBody;
        private XMLStreamReader streamReader;
        private String tagBody;
        private Ammunition ammunition;

        public Knight getKnight() {
            return knight;
        }

        public Knight parse(InputStream inputStream) throws XMLStreamException {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            streamReader = inputFactory.createXMLStreamReader(inputStream);
            return startParse(streamReader);
        }

        private Knight startParse(XMLStreamReader streamReader) throws XMLStreamException {
            while (streamReader.hasNext()) {
                int streamType = streamReader.next();
                switch (streamType) {
                    case XMLStreamConstants.START_ELEMENT:
                        tagBody = streamReader.getLocalName();
                        switch (tagBody) {
                            case TAG_KNIGHT:
                                knight = new Knight();
                                rootTagBody = tagBody;
                                break;
                            case TAG_AMMUNITION:
                                rootTagBody = tagBody;
                                knight.setAmmunitionList((ArrayList<Ammunition>) parseAmmunition(streamReader));
                        }


                        if (TAG_KNIGHT.equals(rootTagBody)) {
                            switch (tagBody) {
                                case TAG_ID:
                                    knight.setId(Long.valueOf(getXMLText(streamReader)));
                                    knight.setUuid();
                                    break;
                                case TAG_NAME:
                                    knight.setName(getXMLText(streamReader));
                                    break;
                            }
                        }

                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        String tag = streamReader.getLocalName();
                        switch (tag) {
                            case TAG_KNIGHT:
                                return getKnight();
                        }
                        break;
                }
            }
            throw new ParseException("Not Knight");
        }

        public List<Ammunition> parseAmmunition(XMLStreamReader streamReader) throws XMLStreamException {
            while (streamReader.hasNext()) {
                int streamType = streamReader.next();
                switch (streamType) {
                    case XMLStreamConstants.START_ELEMENT:
                        tagBody = streamReader.getLocalName();
                        switch (tagBody) {
                            case TAG_ARMOR:
                                type = new Ammunition.Type();
                                protection = new Armor.Protection();
                                ammunition = new Armor();
                                ammunitionList.add(parseAmmunitionElement(streamReader));
                                break;
                            case TAG_HELMET:
                                ammunition = new Helmet();
                                type = new Ammunition.Type();
                                ammunitionList.add(parseAmmunitionElement(streamReader));
                                break;
                            case TAG_MELEE_WEAPON:
                                ammunition = new MeleeWeapon();
                                type = new Ammunition.Type();
                                ammunitionList.add(parseAmmunitionElement(streamReader));
                                break;
                            case TAG_RANGED_WEAPON:
                                ammunition = new RangedWeapon();
                                type = new Ammunition.Type();
                                ammunitionList.add(parseAmmunitionElement(streamReader));
                                break;
                            case TAG_SHIELD:
                                ammunition = new Shield();
                                type = new Ammunition.Type();
                                material = new Shield.Material();
                                ammunitionList.add(parseAmmunitionElement(streamReader));
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        tagBody = streamReader.getLocalName();
                        switch (tagBody) {
                            case TAG_AMMUNITION:
                                return ammunitionList;
                        }
                        break;
                }
            }

            throw new ParseException("Not Ammunition");
        }

        private Ammunition parseAmmunitionElement(XMLStreamReader streamReader) throws XMLStreamException {
            while (streamReader.hasNext()) {
                int streamType = streamReader.next();
                switch (streamType) {
                    case XMLStreamConstants.START_ELEMENT:
                        tagBody = streamReader.getLocalName();
                        switch (tagBody) {
                            case TAG_ID:
                                ammunition.setId(Long.parseLong(getXMLText(streamReader)));
                                ammunition.setUuid();
                                break;
                            case TAG_NAME:
                                ammunition.setName(getXMLText(streamReader));
                                break;
                            case TAG_WEIGHT:
                                ammunition.setWeight(Integer.parseInt(getXMLText(streamReader)));
                                break;
                            case TAG_PRICE:
                                ammunition.setPrice(Integer.parseInt(getXMLText(streamReader)));
                                break;
                            case TAG_TYPE:
                                ammunition.setType(ammunitionType(streamReader));
                                break;
                            case TAG_PROTECTION:
                                ((Armor) ammunition).setProtection(armorProtection(streamReader));
                                break;
                            case TAG_BALACLAVA:
                                ((Helmet) ammunition).setBalaclava(Boolean.parseBoolean(getXMLText(streamReader)));
                                break;
                            case TAG_CAPTURED:
                                ((Weapon) ammunition).setCaptured(Boolean.parseBoolean(getXMLText(streamReader)));
                                break;
                            case TAG_LENGTH:
                                ((MeleeWeapon) ammunition).setLengthWeapon(Integer.parseInt(getXMLText(streamReader)));
                                break;
                            case TAG_NUMBER_OF_SHELLS:
                                ((RangedWeapon) ammunition).setNumberOfShells(Integer.parseInt(getXMLText(streamReader)));
                                break;
                            case TAG_MATERIAL:
                                ((Shield)ammunition).setMaterial(shieldMaterial(streamReader));

                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        tagBody = streamReader.getLocalName();
                        switch (tagBody) {
                            case TAG_ARMOR:
                                return ammunition;
                            case TAG_HELMET:
                                return ammunition;
                            case TAG_MELEE_WEAPON:
                                return ammunition;
                            case TAG_RANGED_WEAPON:
                                return ammunition;
                            case TAG_SHIELD:
                                return ammunition;
                        }
                        break;
                }
            }
            throw new ParseException("Not Ammunition");
        }

        private Ammunition.Type ammunitionType(XMLStreamReader streamReader) throws XMLStreamException {
            while (streamReader.hasNext()) {
                int streamType = streamReader.next();
                switch (streamType) {
                    case XMLStreamConstants.START_ELEMENT:
                        tagBody = streamReader.getLocalName();
                        switch (tagBody) {
                            case TAG_ID:
                                type.setId(Long.parseLong(getXMLText(streamReader)));
                                type.setUuid();
                                break;
                            case TAG_NAME:
                                type.setName(getXMLText(streamReader));
                                break;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        tagBody = streamReader.getLocalName();
                        switch (tagBody) {
                            case TAG_TYPE:
                                return type;
                        }
                        break;
                }
            }
            throw new ParseException("not Type");
        }

        private Armor.Protection armorProtection(XMLStreamReader streamReader) throws XMLStreamException {
            while (streamReader.hasNext()) {
                int streamType = streamReader.next();
                switch (streamType) {
                    case XMLStreamConstants.START_ELEMENT:
                        tagBody = streamReader.getLocalName();
                        switch (tagBody) {
                            case TAG_ID:
                                protection.setId(Long.parseLong(getXMLText(streamReader)));
                                protection.setUuid();
                                break;
                            case TAG_NAME:
                                protection.setName(getXMLText(streamReader));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        tagBody = streamReader.getLocalName();
                        switch (tagBody) {
                            case TAG_PROTECTION:
                                return protection;
                        }
                        break;
                }
            }
            throw new ParseException("not Protection");
        }

        private Shield.Material shieldMaterial(XMLStreamReader streamReader) throws XMLStreamException {
            while (streamReader.hasNext()) {
                int streamType = streamReader.next();
                switch (streamType) {
                    case XMLStreamConstants.START_ELEMENT:
                        tagBody = streamReader.getLocalName();
                        switch (tagBody) {
                            case TAG_ID:
                                material.setId(Long.parseLong(getXMLText(streamReader)));
                                material.setUuid();
                                break;
                            case TAG_NAME:
                                material.setName(getXMLText(streamReader));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        tagBody = streamReader.getLocalName();
                        switch (tagBody) {
                            case TAG_MATERIAL:
                                return material;
                        }
                        break;
                }
            }
            throw new ParseException("not Material");
        }

        private String getXMLText(XMLStreamReader streamReader) throws XMLStreamException {
            String text = null;
            if (streamReader.hasNext()) {
                streamReader.next();
                text = streamReader.getText();
            }
            return text;
        }
    }
}
