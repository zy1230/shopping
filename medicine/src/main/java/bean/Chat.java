package bean;

public class Chat {
    private String user; // 用户登录名
    private String chat; // 会话内容
    public Chat(){
    }
    public Chat(String user, String chat) {
        this.user = user;
        this.chat = chat;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getChat() {
        return chat;
    }
    public void setChat(String chat) {
        this.chat = chat;
    }
}
