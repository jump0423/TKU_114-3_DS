public class LinkedListSearchRemove {
    public static void main(String[] args) {
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        head.next.next.next = new IntNode(40);

        System.out.println("包含 30：" + contains(head, 30));
        System.out.println("包含 99：" + contains(head, 99));

        System.out.print("刪除 head(10) 前：");
        printList(head);
        head = removeValue(head, 10);
        System.out.print("刪除 head(10) 後：");
        printList(head);

        System.out.print("刪除中間(20) 前：");
        printList(head);
        head = removeValue(head, 20);
        System.out.print("刪除中間(20) 後：");
        printList(head);

        System.out.print("刪除最後(40) 前：");
        printList(head);
        head = removeValue(head, 40);
        System.out.print("刪除最後(40) 後：");
        printList(head);

        System.out.print("刪除找不到的值(99) 前：");
        printList(head);
        head = removeValue(head, 99);
        System.out.print("刪除找不到的值(99) 後：");
        printList(head);
    }

    public static boolean contains(IntNode head, int target) {
        IntNode current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static IntNode removeValue(IntNode head, int target) {
        if (head == null) {
            return null;
        }
        if (head.data == target) {
            return head.next;
        }
        IntNode previous = head;
        IntNode current = head.next;
        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                break;
            }
            previous = current;
            current = current.next;
        }
        return head;
    }

    public static void printList(IntNode head) {
        IntNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
