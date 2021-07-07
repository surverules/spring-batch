package com.ms.batch.process;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class Reader implements ItemReader<String> {

    private String[] messages = {"Hello", "World", "example"};

    int count = 0;
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        while (count < messages.length) {
            return messages[count++];
        }
        return null;
    }
}
