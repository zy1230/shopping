package bean;

public class Comment {
    private String username; // 用户登录名
    private String Comment;  // 用户评价内容
    private String time;     // 用户评论时间（年月日）
    private String drug;     // 用户评论的商品名称
    private int id;
    public Comment() {
    }
    public Comment(String username, String comment, String time, String drug) {
        this.username = username;
        Comment = comment;
        this.time = time;
        this.drug = drug;
    }
    public String getUsername() {
        return username;
    }
    public String getComment() {
        return Comment;
    }
    public String getTime() {
        return time;
    }
    public String getDrug() {
        return drug;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setComment(String comment) {
        Comment = comment;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setDrug(String drug) {
        this.drug = drug;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
