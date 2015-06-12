package com.epam.taskthree.run;

import com.epam.taskthree.entity.Gift;
import com.epam.taskthree.exception.LogicException;
import com.epam.taskthree.exception.TechnicalException;
import com.epam.taskthree.implementation.AbstractParser;
import com.epam.taskthree.implementation.StAXParser;
import com.epam.taskthree.report.Reporter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.ResourceBundle;

public class Runner {

    public static ResourceBundle urlBundle = ResourceBundle.getBundle("URLBundle");

    static {
        new DOMConfigurator().doConfigure(urlBundle.getString("log4jURL"),
                LogManager.getLoggerRepository());
    }

    public static final Logger LOGGER = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        AbstractParser parser = new StAXParser();
//        AbstractParser parser = new SaxParser();
//        AbstractParser parser = new DOMParser();
        Gift gift = null;
        try {
            gift = parser.receiveGift(urlBundle.getString("xmlURL"));
        } catch (TechnicalException | LogicException e) {
            LOGGER.error(e);
        }
        Reporter.writeToFile(gift.toString(), urlBundle.getString("reportTextFileURL"));
    }
}
