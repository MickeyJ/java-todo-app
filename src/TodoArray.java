import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class TodoArray {
  Scanner input = new Scanner(System.in);
  String todoFile = "assets/todos.txt";
  Todo[] todos;

  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    TodoArray todoArray = new TodoArray();

    System.out.println("type 'add', 'list', 'empty'");
    todoArray.actions(input.next());
  }

  public void actions(String input){
    switch(input){
      case "add":
        pushMany();
        break;
      case "list":
        listAll();
        break;
      case "empty":
        popAll();
        break;
      default:
        System.out.println("Command Does Not Exist");
    }
  }

  public void pushMany(){
    System.out.println("How many new thing to do?");
    todos = new Todo[input.nextInt()];

    for (int i = 0; i < todos.length; i++){
      System.out.println("Enter a task");
      todos[i] = new Todo();
      todos[i].setTodo(i, input.next());
    }

    System.out.println("todos: [");
    for (Todo x : todos) System.out.println(x.getTodo());
    System.out.println("]");

    try {
      FileWriter file = new FileWriter(todoFile, true);
      for (Todo x : todos) file.write(x.getTodo());
      file.flush();
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void popAll(){
    try {
      FileWriter file = new FileWriter(todoFile, false);
      file.write("");
      file.close();
      System.out.println("Todo List is Now Empty");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void listAll(){
    try {
      FileReader file = new FileReader(todoFile);
      int ch;
      do {
        ch = file.read();
        if (ch != -1) System.out.print((char) ch);
      } while (ch != -1);
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
