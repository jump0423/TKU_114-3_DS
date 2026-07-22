public class Equipment {
    private String code;
    private String name;
    private boolean available;

    public Equipment(String code, String name) {
        this.code = code;
        this.name = name;
        this.available = true;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() {
        available = false;
    }

    public void returnEquipment() {
        available = true;
    }

    @Override
    public String toString() {
        return code + " | " + name + " | available=" + available;
    }
}
