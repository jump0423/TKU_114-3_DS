public class TaskNode {
    String taskCode;
    String description;
    boolean isDone;
    TaskNode next;

    public TaskNode(String taskCode, String description) {
        this.taskCode = taskCode;
        this.description = description;
        this.isDone = false;
        this.next = null;
    }
}
