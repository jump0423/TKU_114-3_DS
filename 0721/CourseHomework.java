public class CourseHomework {
    private String code;
    private String name;
    private int capacity;
    private int currentCount;

    public CourseHomework(String code, String name, int capacity) {
        this.code = code;
        this.name = name;
        this.capacity = capacity;
        this.currentCount = 0;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public boolean isFull() {
        return currentCount >= capacity;
    }

    public boolean enroll() {
        if (isFull()) {
            return false;
        }
        currentCount++;
        return true;
    }

    public boolean withdraw() {
        if (currentCount <= 0) {
            return false;
        }
        currentCount--;
        return true;
    }

    @Override
    public String toString() {
        return code + " | " + name + " | " + currentCount + "/" + capacity;
    }
}
