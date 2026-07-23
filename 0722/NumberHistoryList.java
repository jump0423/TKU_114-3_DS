public class NumberHistoryList {

    static class NumberNode {
        int data;
        NumberNode next;

        NumberNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private NumberNode head;
    private int size;

    public NumberHistoryList() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(int value) {
        NumberNode newNode = new NumberNode(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(int value) {
        NumberNode newNode = new NumberNode(value);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        NumberNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }

    public boolean contains(int target) {
        NumberNode current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean remove(int target) {
        if (head == null) {
            return false;
        }
        if (head.data == target) {
            head = head.next;
            size--;
            return true;
        }
        NumberNode previous = head;
        NumberNode current = head.next;
        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public Integer sum() {
        if (head == null) {
            return null;
        }
        int total = 0;
        NumberNode current = head;
        while (current != null) {
            total += current.data;
            current = current.next;
        }
        return total;
    }

    public Integer max() {
        if (head == null) {
            return null;
        }
        int maxValue = head.data;
        NumberNode current = head.next;
        while (current != null) {
            if (current.data > maxValue) {
                maxValue = current.data;
            }
            current = current.next;
        }
        return maxValue;
    }

    public Integer min() {
        if (head == null) {
            return null;
        }
        int minValue = head.data;
        NumberNode current = head.next;
        while (current != null) {
            if (current.data < minValue) {
                minValue = current.data;
            }
            current = current.next;
        }
        return minValue;
    }

    public void print() {
        NumberNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();

        System.out.println("空串列 size：" + list.size());
        System.out.println("空串列 sum：" + list.sum());
        System.out.println("空串列 max：" + list.max());
        System.out.println("空串列 min：" + list.min());

        list.addLast(15);
        list.addLast(8);
        list.addFirst(20);
        list.addLast(3);
        list.addFirst(42);
        list.addLast(9);
        list.print();

        System.out.println("包含 8：" + list.contains(8));
        System.out.println("包含 100：" + list.contains(100));

        System.out.println("刪除 20：" + list.remove(20));
        list.print();

        System.out.println("刪除 100：" + list.remove(100));

        System.out.println("size：" + list.size());
        System.out.println("總和：" + list.sum());
        System.out.println("最大值：" + list.max());
        System.out.println("最小值：" + list.min());
    }
}
