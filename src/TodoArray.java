import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class TodoArray {
  ArrayList list = new ArrayList();
  Scanner input = new Scanner(System.in);
  String todoFile = "assets/todos.json";
  Todo[] todos;

  public static void main(String[] args){
    TodoArray todoArray = new TodoArray();

    todoArray.initREPL();
  }

  public void initREPL(){
    System.out.print("\nType 'add', 'list', 'empty', or 'exit'\n\n");
    callAction(input.next());
    System.out.print("\n");
    initREPL();
  }

  public void callAction(String input){

    switch(input){
      case "add": pushMany();
        break;
      case "list": listTodos();
        break;
      case "empty": popAll();
        break;
      case "exit": System.exit(0);
        break;
      default:
        System.out.println("Command Does Not Exist");
    }

  }

  public void pushMany(){
    System.out.println("\nHow many new things to do?");
    todos = new Todo[input.nextInt()];

    for (int i = 0; i < todos.length; i++){
      System.out.println("\nEnter a task");
      todos[i] = new Todo();
      todos[i].setTodo(i, input.next());

      try { list.add(todos[i].getTodo()); }
      catch (JSONException e) { e.printStackTrace(); }
    }

    writeFile(String.valueOf(list));
    list.clear();
    listTodos();
  }

  public void popAll(){
    writeFile("[]");
    System.out.println("\nTodo List is Now Empty");
  }

  public void listTodos() {
    String jsonData = readFile(todoFile);
    System.out.println();

    try {
      JSONArray jsonArr = new JSONArray(jsonData);

      for(int i = 0; i < jsonArr.length(); i++) {
        int id = jsonArr.getJSONObject(i).getInt("id");
        String task = jsonArr.getJSONObject(i).getString("task");
        System.out.println(id +" | "+ task);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }

  }

  public static String readFile(String filename) {
    String result = "";
    try {
      BufferedReader file = new BufferedReader(new FileReader(filename));
      StringBuilder string = new StringBuilder();
      String line = file.readLine();
      while (line != null) {
        string.append(line);
        line = file.readLine();
      }
      result = string.toString();
    } catch(Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public void writeFile(String input){
    try {
      FileWriter writeFile = new FileWriter(todoFile, false);
      writeFile.write(input);
      writeFile.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
