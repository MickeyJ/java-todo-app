
public class Todo {

  private int id;
  private String task;
  private boolean complete;

  Todo(){
    id = 0;
    task = "";
    complete = false;
  }

  public void setTodo(int id, String task){
    this.id = id;
    this.task = task;
    this.complete = false;
  }

  public String getTodo(){
    return (
      "  {" +
        "\n    id: " + this.id +"," +
        "\n    task: " + this.task +
        "\n    complete: " + this.complete +
        "\n  },"
    );
  }

}
