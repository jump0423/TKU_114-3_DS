public class PlaylistLinkedList {
    private PlaylistNode head;
    private int size;

    public PlaylistLinkedList() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean contains(String songCode) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.songCode.equals(songCode)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean addLast(String songCode, String songName) {
        if (contains(songCode)) {
            return false;
        }
        PlaylistNode newNode = new PlaylistNode(songCode, songName);
        if (head == null) {
            head = newNode;
            size++;
            return true;
        }
        PlaylistNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
        return true;
    }

    public PlaylistNode search(String songCode) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.songCode.equals(songCode)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public boolean remove(String songCode) {
        if (head == null) {
            return false;
        }
        if (head.songCode.equals(songCode)) {
            head = head.next;
            size--;
            return true;
        }
        PlaylistNode previous = head;
        PlaylistNode current = head.next;
        while (current != null) {
            if (current.songCode.equals(songCode)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public void printAll() {
        PlaylistNode current = head;
        while (current != null) {
            System.out.print(current.songCode + ":" + current.songName + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
