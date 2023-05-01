package com.prapro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Store {
  private static JsonNode store = null;

  public static void writeToStore(JsonNode node){
    store = node.deepCopy();
  }

  public static JsonNode readFromStore(){
    return store == null ? null : store.deepCopy();
  }

  public static void saveAsPDF() throws IOException, DocumentException{
    if(store == null) throw new IOException("Store is empty");
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("store.pdf"));
    document.open();
    document.add(new Paragraph(store.toPrettyString()));
    document.close();
    writer.close();
  }

  public static void saveAsJSON() throws StreamWriteException, DatabindException, IOException{
    if(store == null) throw new IOException("Store is empty");
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(new File("store.json"), store);
  }

  public static void saveAsXML() throws StreamWriteException, DatabindException, IOException{
    if(store == null) throw new IOException("Store is empty");
    XmlMapper objectMapper = new XmlMapper();
    objectMapper.writeValue(new File("store.xml"), store);
  }
}
