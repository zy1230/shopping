package bean;

public class Order {
    private String drug;     // 药品名称
    private int state;       //是否发货（值为0或1）；0表示未发货
    private int receive;     //是否收货（值为0或1）；0表示未收货
    private String time;     // 用户下订单时间
    private int evaluate;    // 是否评价（值为0或1）；0表示未评价
    private String uname;    // 用户姓名
    private String address;  // 用户地址
    private String username;  // 用户登录名
    private String phone;     // 用户手机号码
    private int piece;        // 用户购买数量
    private int id;
    public Order() {
    }
    public Order(String drug, int state, int receive, String time, int evaluate) {
        this.drug = drug;
        this.state = state;
        this.receive = receive;
        this.time = time;
        this.evaluate = evaluate;
    }
    public String getDrug() {
        return drug;
    }
    public int getState() {
        return state;
    }
    public int getReceive() {
        return receive;
    }
    public void setDrug(String drug) {
        this.drug = drug;
    }
    public void setState(int state) {
        this.state = state;
    }
    public void setReceive(int receive) {
        this.receive = receive;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getEvaluate() {
        return evaluate;
    }
    public void setEvaluate(int evaluate) {
        this.evaluate = evaluate;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getPiece() {
        return piece;
    }
    public void setPiece(int piece) {
        this.piece = piece;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
