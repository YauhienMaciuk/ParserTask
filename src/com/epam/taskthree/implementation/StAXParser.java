package com.epam.taskthree.implementation;

import com.epam.taskthree.entity.*;
import com.epam.taskthree.exception.LogicException;
import com.epam.taskthree.exception.TechnicalException;
import com.epam.taskthree.implementation.AbstractParser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StAXParser extends AbstractParser {

    @Override
    public Gift receiveGift(String url) throws LogicException, TechnicalException {
        Gift gift;
        Confection confection = null;
        String tagContent = null;
        List<Confection> listConfections = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader;
        try {
            reader = factory.createXMLStreamReader(new FileInputStream(url));
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new TechnicalException("Exception in method receiveGift", e);
        }
        if (reader != null) {
            try {
                while (reader.hasNext()) {
                    int event = reader.next();
                    switch (event) {
                        case XMLStreamConstants.START_ELEMENT:
                            String currentElement = reader.getLocalName();
                            if (doStartElement(currentElement) != null) {
                                confection = doStartElement(currentElement);
                            }
                            break;
                        case XMLStreamConstants.CHARACTERS:
                            tagContent = reader.getText().trim();
                            break;
                        case XMLStreamConstants.END_ELEMENT:
                            if ("candies".equals(reader.getLocalName())) {
                                listConfections.add(confection);
                            }
                            if ("chocolate".equals(reader.getLocalName())) {
                                listConfections.add(confection);
                            }
                            if ("wafers".equals(reader.getLocalName())) {
                                listConfections.add(confection);
                            }
                            if ("name".equals(reader.getLocalName())) {
                                confection.setName(tagContent);
                            }
                            if ("weight".equals(reader.getLocalName())) {
                                confection.setWeight(Double.parseDouble(tagContent));
                            }
                            if ("sugar".equals(reader.getLocalName())) {
                                confection.setQuantitySugar(Double.parseDouble(tagContent));
                            }
                            if ("type".equals(reader.getLocalName())) {
                                ((Candies) confection).setType(tagContent);
                            }
                            if ("color".equals(reader.getLocalName())) {
                                ((Chocolate) confection).setColor(tagContent);
                            }
                            if ("stuffed".equals(reader.getLocalName())) {
                                ((Wafers) confection).setStuffed(Boolean.parseBoolean(tagContent));
                            }
                            break;
                        case XMLStreamConstants.START_DOCUMENT:
                            listConfections = new ArrayList<>();
                            break;
                    }
                }
            } catch (NumberFormatException | XMLStreamException e) {
                throw new TechnicalException("Exception in method receiveGift", e);
            }
        } else {
            throw new TechnicalException("Exception associated with the parameter reader");
        }
        gift = new Gift(listConfections);
        return gift;
    }

    private Confection doStartElement(String currentElement) {
        Confection confection;
        switch (currentElement) {
            case "candies":
                confection = new Candies();
                break;
            case "chocolate":
                confection = new Chocolate();
                break;
            case "wafers":
                confection = new Wafers();
                break;
            default:
                confection = null;
        }
        return confection;
    }
}
