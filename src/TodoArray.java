import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class TodoArray {
  Scanner input = new Scanner(System.in);
  Todo[] todos;

  public static void main(String[] args){
    TodoArray todoArray = new TodoArray();

    todoArray.pushMany();

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

      FileWriter file = new FileWriter("assets/todos.txt");
      for (Todo x : todos) file.write(x.getTodo());
      file.flush();
      file.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
