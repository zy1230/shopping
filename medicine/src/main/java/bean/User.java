package bean;

public class User {
     private int uid;
     private String uname;        // 用户姓名
     private String username;     // 用户登录名
     private String upass;        // 用户密码
     private String phone;        // 用户手机号码
     private String address;      // 用户地址
     private String indentity;    // 用户身份（用户和管理员）

     public User() {
     }
     public User(String uname, String username, String upass, String phone, String address,String indentity) {
        this.uname = uname;
        this.username = username;
        this.upass = upass;
        this.phone = phone;
        this.address = address;
        this.indentity = indentity;
    }
    public User(String uname, String upass) {
        this.uname = uname;
        this.upass = upass;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
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
    public String getUpass() {
        return upass;
    }
    public void setUpass(String upass) {
        this.upass = upass;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getIndentity() {
        return indentity;
    }
    public void setIndentity(String indentity) {
        this.indentity = indentity;
    }
}
