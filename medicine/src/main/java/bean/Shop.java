package bean;

public class Shop {
    private int id;
    private String username;   // 用户登录名
    private int piece;         // 用户购买数量
    private String drug;       // 商品名称
    private double price;      // 药品价格
    public Shop() {
    }
    public Shop(String username, int piece, String drug, double price) {
        this.username = username;
        this.piece = piece;
        this.drug = drug;
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getUsername() {
        return username;
    }
    public int getPiece() {
        return piece;
    }
    public String getDrug() {
        return drug;
    }
    public void setUsername(String username) { this.username = username; }
    public void setPiece(int piece) { this.piece = piece; }
    public void setDrug(String drug) { this.drug = drug; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}
