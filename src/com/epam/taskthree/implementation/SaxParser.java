package com.epam.taskthree.implementation;

import com.epam.taskthree.entity.Confection;
import com.epam.taskthree.entity.Gift;
import com.epam.taskthree.exception.TechnicalException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaxParser extends AbstractParser {

    @Override
    public Gift receiveGift(String url) throws TechnicalException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        Gift gift;
        List<Confection> listConfections;
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DefaultHandler handler = new SAXParserHandler();
            saxParser.parse(new File(url), handler);

            listConfections = ((SAXParserHandler) handler).getListConfections();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new TechnicalException("Exception in method receiveGift", e);
        }
        gift = new Gift(listConfections);
        return gift;
    }
}