package com.prapro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Simple unit test for App.
 */
public class AppTest 
{
    /**
     * @throws IOException
     */
    @Test
    public void allTests() throws IOException
    {
        System.out.println("Store test:");
        storeWriteRead();

        System.out.println("Request API test:");
        request();
    }

    @Test
    public void storeWriteRead() throws JsonMappingException, JsonProcessingException{
        
        System.out.println("Read from empty Store return null");
        assertEquals(null, Store.readFromStore());

        System.out.println("Read after write return the same node");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree("{\"parametr\":\"value\"}");
        Store.writeToStore(node);
        assertEquals(node, Store.readFromStore());
    }

    @Test
    public void request() throws IOException{

        System.out.println("Sent request findAll write to Store not empty JsonNode");
        RequestAPI.findAll();
        assertNotEquals(null, Store.readFromStore());

        System.out.println("Sent request findByID write to Store not empty JsonNode");
        RequestAPI.findByID(14);
        assertNotEquals(null, Store.readFromStore());

        System.out.println("Sent request findByID with bad id parametr write to Store JsonNode with null fields");
        RequestAPI.findByID(0);
        JsonNode expected = Store.readFromStore();
        RequestAPI.findByID(100);
        assertEquals(expected, Store.readFromStore());
    }
}
