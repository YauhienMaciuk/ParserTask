package com.epam.taskthree.implementation;

import com.epam.taskthree.entity.Gift;
import com.epam.taskthree.exception.LogicException;
import com.epam.taskthree.exception.TechnicalException;

public abstract class AbstractParser {
    public abstract Gift receiveGift(String url) throws TechnicalException, LogicException;
}
