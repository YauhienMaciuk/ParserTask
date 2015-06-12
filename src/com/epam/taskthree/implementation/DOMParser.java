package com.epam.taskthree.implementation;

import com.epam.taskthree.entity.*;
import com.epam.taskthree.exception.LogicException;
import com.epam.taskthree.exception.TechnicalException;
import com.epam.taskthree.implementation.AbstractParser;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMParser extends AbstractParser {
    public static final Logger LOGGER = Logger.getLogger("DOMParser.class");

    @Override
    public Gift receiveGift(String url) throws TechnicalException {
        Gift gift;
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setIgnoringElementContentWhitespace(true);
        Document document = null;
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
            document = documentBuilder.parse(new File(url));
        } catch (ParserConfigurationException e) {
            LOGGER.error("Exception during configuration parser", e);
        } catch (SAXException e) {
            LOGGER.error("Exception occurred during parsing XML", e);
        } catch (IOException e) {
            LOGGER.error("File not found", e);
        }
        List<Confection> confections = doParsing(document);
        gift = new Gift(confections);
        return gift;
    }

    private List<Confection> doParsing(Document document) {
        List<Confection> listConfections = new ArrayList<Confection>();
        Element gift = document.getDocumentElement();
        NodeList childPlants = gift.getChildNodes();
        for (int i = 0; i < childPlants.getLength(); i++) {
            Node plantNode = childPlants.item(i);
            if (plantNode instanceof Element) {
                Element confectionElement = (Element) plantNode;
                Confection confection = null;
                try {
                    confection = createConfection(confectionElement);
                } catch (LogicException | TechnicalException e) {
                    LOGGER.error("Exception in DOMParser", e);
                }
                listConfections.add(confection);
            }
        }
        return listConfections;
    }

    private Confection createConfection(Element confectionElement) throws LogicException, TechnicalException {
        Confection confection;
        String currentSortConfection = confectionElement.getNodeName();
        switch (currentSortConfection) {
            case "candies":
                confection = createCandies(confectionElement);
                break;
            case "chocolate":
                confection = createChocolate(confectionElement);
                break;
            case "wafers":
                confection = createWafers(confectionElement);
                break;
            default:
                throw new TechnicalException("Exception in method createConfection. Incorrect param currentSortConfection.");
        }
        return confection;
    }

    private Candies createCandies(Element confectionElement) throws LogicException, TechnicalException {
        Candies candies = new Candies();
        NodeList childNodes = confectionElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childElement = childNodes.item(i);
            if (childElement instanceof Element) {
                Element paramConfection = (Element) childElement;
                switch (paramConfection.getTagName()) {
                    case "name":
                        candies.setName(paramConfection.getTextContent());
                        break;
                    case "weight":
                        candies.setWeight(Double.parseDouble(paramConfection.getTextContent()));
                        break;
                    case "sugar":
                        candies.setQuantitySugar(Double.parseDouble(paramConfection.getTextContent()));
                        break;
                    case "type":
                        candies.setType(paramConfection.getTextContent());
                        break;
                    default:
                        throw new TechnicalException("Exception in method createCandies. Incorrect paramConfection");
                }
            }
        }

        return candies;
    }

    private Chocolate createChocolate(Element confectionElement) throws LogicException, TechnicalException {
        Chocolate chocolate = new Chocolate();
        NodeList childNodes = confectionElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childElement = childNodes.item(i);
            if (childElement instanceof Element) {
                Element paramConfection = (Element) childElement;
                switch (paramConfection.getTagName()) {
                    case "name":
                        chocolate.setName(paramConfection.getTextContent());
                        break;
                    case "weight":
                        chocolate.setWeight(Double.parseDouble(paramConfection.getTextContent()));
                        break;
                    case "sugar":
                        chocolate.setQuantitySugar(Double.parseDouble(paramConfection.getTextContent()));
                        break;
                    case "color":
                        chocolate.setColor(paramConfection.getTextContent());
                        break;
                    default:
                        throw new TechnicalException("Exception in method createChocolate. Incorrect paramConfection");
                }
            }
        }

        return chocolate;
    }

    private Wafers createWafers(Element confectionElement) throws LogicException, TechnicalException {
        Wafers wafers = new Wafers();
        NodeList childNodes = confectionElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childElement = childNodes.item(i);
            if (childElement instanceof Element) {
                Element paramConfection = (Element) childElement;
                switch (paramConfection.getTagName()) {
                    case "name":
                        wafers.setName(paramConfection.getTextContent());
                        break;
                    case "weight":
                        wafers.setWeight(Double.parseDouble(paramConfection.getTextContent()));
                        break;
                    case "sugar":
                        wafers.setQuantitySugar(Double.parseDouble(paramConfection.getTextContent()));
                        break;
                    case "stuffed":
                        wafers.setStuffed(Boolean.parseBoolean(paramConfection.getTextContent()));
                        break;
                    default:
                        throw new TechnicalException("Exception in method createWafers. Incorrect paramConfection");
                }
            }
        }

        return wafers;
    }
}
