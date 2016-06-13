import org.json.JSONException;
import org.json.JSONObject;

public class Todo {
  JSONObject todo = new JSONObject();
  private int id;
  private String task;
  private boolean complete;

  public Todo(){
    id = 0;
    task = "";
    complete = false;
  }

  public void setTodo(int id, String task){
    this.id = id;
    this.task = task;
    this.complete = false;
  }

  public Object getTodo() throws JSONException {
    todo.put( "id", this.id );
    todo.put( "task", this.task );
    todo.put( "complete", this.complete );

    return todo;
  }

}
