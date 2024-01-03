package pri.computerstore.model;

/**
 * @author 33133
 */
public class Hardware {
    private int id;
    private static String name;
    private static double price;
    public Hardware() {
        // 默认构造函数
    }

    public Hardware(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
