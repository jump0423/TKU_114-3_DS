public class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addUrgent(String taskCode, String description) {
        TaskNode newNode = new TaskNode(taskCode, description);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addNormal(String taskCode, String description) {
        TaskNode newNode = new TaskNode(taskCode, description);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        TaskNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }

    public boolean complete(String taskCode) {
        TaskNode current = head;
        while (current != null) {
            if (current.taskCode.equals(taskCode)) {
                current.isDone = true;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean remove(String taskCode) {
        if (head == null) {
            return false;
        }
        if (head.taskCode.equals(taskCode)) {
            head = head.next;
            size--;
            return true;
        }
        TaskNode previous = head;
        TaskNode current = head.next;
        while (current != null) {
            if (current.taskCode.equals(taskCode)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public void printUnfinished() {
        TaskNode current = head;
        while (current != null) {
            if (!current.isDone) {
                System.out.println(current.taskCode + "：" + current.description);
            }
            current = current.next;
        }
    }

    public int countUnfinished() {
        int count = 0;
        TaskNode current = head;
        while (current != null) {
            if (!current.isDone) {
                count++;
            }
            current = current.next;
        }
        return count;
    }
}
