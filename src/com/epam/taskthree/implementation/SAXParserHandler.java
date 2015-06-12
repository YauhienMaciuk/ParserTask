package com.epam.taskthree.implementation;

import com.epam.taskthree.entity.Candies;
import com.epam.taskthree.entity.Chocolate;
import com.epam.taskthree.entity.Confection;
import com.epam.taskthree.entity.Wafers;
import com.epam.taskthree.exception.LogicException;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXParserHandler extends DefaultHandler {
    public static final Logger LOGGER = Logger.getLogger("SAXParserHandler.class");
    private List<Confection> listConfections;
    private Confection confection;
    private boolean bName, bWeight, bSugar, bType, bColor, bStuffed;

    public List<Confection> getListConfections() {
        return listConfections;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "candies":
                confection = new Candies();
                if (listConfections == null) {
                    listConfections = new ArrayList<>();
                }
                break;
            case "chocolate":
                confection = new Chocolate();
                if (listConfections == null) {
                    listConfections = new ArrayList<>();
                }
                break;
            case "wafers":
                confection = new Wafers();
                if (listConfections == null) {
                    listConfections = new ArrayList<>();
                }
                break;
            case "name":
                bName = true;
                break;
            case "weight":
                bWeight = true;
                break;
            case "sugar":
                bSugar = true;
                break;
            case "type":
                bType = true;
                break;
            case "color":
                bColor = true;
                break;
            case "stuffed":
                bStuffed = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        try {
            if (bName) {
                confection.setName(value);
                bName = false;
            } else if (bWeight) {
                confection.setWeight(Double.parseDouble(value));
                bWeight = false;
            } else if (bSugar) {
                confection.setQuantitySugar(Double.parseDouble(value));
                bSugar = false;
            } else if (bType) {
                ((Candies) confection).setType(value);
                bType = false;
            } else if (bColor) {
                ((Chocolate) confection).setColor(value);
                bColor = false;
            } else if (bStuffed) {
                ((Wafers) confection).setStuffed(Boolean.parseBoolean(value));
                bStuffed = false;
            }
        } catch (LogicException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "candies":
                listConfections.add(confection);
                break;
            case "chocolate":
                listConfections.add(confection);
                break;
            case "wafers":
                listConfections.add(confection);
        }
    }
}