package pri.computerstore.model;

/**
 * @author 33133
 */
public class Hardware {
    private int id;
    private String name;
    private double price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
