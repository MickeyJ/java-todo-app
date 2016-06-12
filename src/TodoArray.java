
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class TodoArray {
  ArrayList list = new ArrayList();
  Scanner input = new Scanner(System.in);
  String todoFile = "assets/todos.json";
  FileReader readFile;
  FileWriter writeFile;
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
      case "list": listAll();
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
      list.add(todos[i].getTodo());
    }

    System.out.print("\n\"todos\": ");
    System.out.println(list);

    try {
      writeFile = new FileWriter(todoFile, false);
      writeFile.write(String.valueOf(list));
      writeFile.flush();
      writeFile.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void popAll(){

    try {
      writeFile = new FileWriter(todoFile);
      writeFile.write("[]");
      writeFile.close();
      System.out.println("\nTodo List is Now Empty");

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void listAll(){

    System.out.println();

    try {
      readFile = new FileReader(todoFile);
      int ch;
      System.out.print("\"todos\": ");
      do {
        ch = readFile.read();
        if (ch != -1) System.out.print((char) ch);
      } while (ch != -1);
      readFile.close();
      System.out.println();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
