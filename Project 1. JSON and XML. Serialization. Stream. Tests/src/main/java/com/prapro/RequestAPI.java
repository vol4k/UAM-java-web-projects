package com.prapro;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestAPI {
  
  public static void findAll() throws IOException {
    String request = "https://api.gios.gov.pl/pjp-api/rest/station/findAll";
    Store.writeToStore(makeRequest(request));
  }

  public static void findByID(int stationId) throws IOException{
    String request = "https://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/" + stationId;
    Store.writeToStore(makeRequest(request));
  }

  private static JsonNode makeRequest(String req) throws IOException{
    URL res = new URL(req);
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readTree(res);
  }

}
