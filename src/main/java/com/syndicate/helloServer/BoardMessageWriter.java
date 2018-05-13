package com.syndicate.helloServer;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BoardMessageWriter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JsonFactory jsonFactory = new JsonFactory();

    private final OutputStream outputStream;
    private final JsonGenerator jsonGenerator;

    public BoardMessageWriter(String file) {
        try {
            outputStream = Files.newOutputStream(Paths.get(file)
                    , StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            jsonGenerator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void write(String topic, Message message){
        final BoardMessage toWrite = new BoardMessage(message, topic);
        try {

            objectMapper.writeValue(jsonGenerator, toWrite);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
