package com.prapro;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

public class UI {
  private static enum MenuOption {undefined, findAll, findById, saveAsPDF, saveAsJSON, saveAsXML, exit};
  private static MenuOption option = MenuOption.undefined;

  private static List<String> optionsDescription = new ArrayList<String>() {{
    add("1. Find all");
    add("2. Find by ID");
    add("3. Save as PDF");
    add("4. Save as JSON");
    add("5. Save as XML");
    add("0. Exit");
  }};

  private static Scanner scanner = new Scanner(System.in);

  public static void start(){
    do
    {
      printMenu();
      collectAndValidateOption();
      System.out.println("Processed...");
      runOption();
    }
    while(!option.equals(MenuOption.exit));

    scanner.close();
  }

  private static void printMenu(){
    clearScreen();
    
    optionsDescription.stream().forEach(System.out::println);
    System.out.println();
  }

  private static void clearScreen() {  
    System.out.print("\033[H\033[2J");
    System.out.flush();
   }

  private static void collectAndValidateOption(){
    System.out.print("Option: ");

    try{
    switch(scanner.nextInt()){
      case 1: option = MenuOption.findAll; break;
      case 2: option = MenuOption.findById; break;
      case 3: option = MenuOption.saveAsPDF; break;
      case 4: option = MenuOption.saveAsJSON; break;
      case 5: option = MenuOption.saveAsXML; break;
      case 0: option = MenuOption.exit; break;
      default: option = MenuOption.undefined;
    }
  }
    catch(InputMismatchException e){
      System.err.println("Invalid input");
      option = MenuOption.undefined;
      pressAnyKeyToContinue();
    }
  }

  private static void runOption(){
    try
    {

    switch(option){
      case findAll: 
      {
        RequestAPI.findAll(); 
        printPrettyString();
      }
      break;
      case findById:
      { 
        int stationId = inputIntegerWrapper("Station id");
        RequestAPI.findByID(stationId); 
        printPrettyString();
      }
      break;
      case saveAsPDF: Store.saveAsPDF(); break;
      case saveAsJSON: Store.saveAsJSON(); break;
      case saveAsXML: Store.saveAsXML(); break;
      default: return;
    }

    }catch(UnknownHostException e){
      System.err.println("Connection fault: " + e.getMessage());
    }
    catch(IOException e){
      System.err.println(e.getMessage());
    }
    catch(DocumentException e){
      System.err.println(e.getMessage());
    }

    pressAnyKeyToContinue();
  }

  private static void pressAnyKeyToContinue(){
    System.out.println("Press Enter key to continue...");
    try
    {
        System.in.read();
        scanner.nextLine();
    }  
    catch(Exception e)
    {}    
  }

  private static void printPrettyString(){
    String prettyString = Store.readFromStore().toPrettyString();
    System.out.println(prettyString);
  }

  private static int inputIntegerWrapper(String prefix){
    System.out.print(prefix + ": ");
    return scanner.nextInt();
  }
}
