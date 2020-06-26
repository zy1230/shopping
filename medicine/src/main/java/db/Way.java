package db;

import bean.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.http.Part;
import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Way {
    private User find(String name, String password) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet re = null;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select * from user where username= ? and upass= ?");
            stat.setString(1, name);
            stat.setString(2, password);
            re = stat.executeQuery();
            if (re.next()) {
                User user = new User();
                user.setUname(re.getString("username"));
                user.setUpass(re.getString("upass"));
                user.setIndentity(re.getString("identity"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.release(re, stat, conn);
        }
        return null;
    }
    public static String isLogin(String name, String password) {
        Way p = new Way();
        User user = p.find(name, password);
        if (user == null) {
            return "";
        }
        return user.getIndentity();
    }
    private User find1(String phone, String password) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet re = null;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select * from user where phone= ? and upass= ?");
            stat.setString(1, phone);
            stat.setString(2, password);
            re = stat.executeQuery();
            if (re.next()) {
                User user = new User();
                user.setIndentity(re.getString("identity"));
                user.setUname(re.getString("username"));
                user.setUpass(re.getString("upass"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.release(re, stat, conn);
        }
        return null;
    }
    public static String isLogin1(String phone, String password) {
        Way p = new Way();
        User user = p.find1(phone, password);
        if (user == null) {
            return "";
        }
        return user.getIndentity();
    }
    public  String select_username(String phone){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet re = null;
        String username;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select username from user where phone = ?");
            stat.setString(1,phone);
            re = stat.executeQuery();
            if(re.next()) {
                username = re.getString("username");
                return username;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.release(re, stat, conn);
        }
        return "";
    }
    public int insert_user(User user) {
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("insert into user (uname, username, upass, phone, address,identity) values (?,?,?,?,?,?)");
            stat.setString(1, user.getUname());
            stat.setString(2, user.getUsername());
            stat.setString(3, user.getUpass());
            stat.setString(4, user.getPhone());
            stat.setString(5, user.getAddress());
            stat.setString(6, user.getIndentity());
            result = stat.executeUpdate();
            if (result > 0) {
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.release(null, stat, conn);
        }
        return result;
    }
    public void insert_chat(String chat,String name) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("insert into chat (chat,user) values (?,?)");
            stat.setString(1, chat);
            stat.setString(2,name);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.release(null, stat, conn);
        }
    }
    public int insert_manager(User user){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("insert into user(username,upass,identity) values (?,?,'管理员')");
            stat.setString(1,user.getUsername());
            stat.setString(2,user.getUpass());
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null, stat, conn);
        }
        return result;
    }

    public int modify(String username,String password){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("update user set upass = ? where username = ?");
            stat.setString(1,password);
            stat.setString(2,username);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }

    public int delete(String username){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("delete from user where username = ?");
            stat.setString(1,username);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }

    public Chat chat(int i){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        Chat chat = new Chat();
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select chat,user from chat where id = ?");
            stat.setInt(1, i);
            set = stat.executeQuery();
            while (set.next()) {
                chat.setChat(set.getString("chat"));
                chat.setUser(set.getString("user"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return chat;
    }
    public  int chat_id(){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        int id = 0;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select id from chat limit 1");
            set = stat.executeQuery();
            while (set.next()){
                id = set.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return id;
    }
    public int charId_max(){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        int id_max = 0;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select count(id) from chat");
            set = stat.executeQuery();
            while (set.next()){
                id_max = set.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return id_max;
    }
    public void insert_answer(String answer,String user){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("insert into answer (answer,user) values (?,?)");
            stat.setString(1,answer);
            stat.setString(2,user);
            stat.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
    }
    public String answer(String user){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        String answer = null;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select answer from answer where user = ?");
            stat.setString(1,user);
            set = stat.executeQuery();
            while (set.next()){
                answer = set.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return answer;
    }
    public void delete_chat(String user){
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("delete from chat where user = ?");
            stat.setString(1,user);
            stat.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
    }
    public void delete_answer(String user){
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("delete from answer where user = ?");
            stat.setString(1,user);
            stat.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
    }
    public String select_user(String user){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        String username = null;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select user from chat where user = ?");
            stat.setString(1,user);
            set = stat.executeQuery();
            while (set.next()) {
                username=set.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return username;
    }
    public int insert_heal(String health,int show){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("insert into health(health, h_show) values (?,?)");
            stat.setString(1,health);
            stat.setInt(2,show);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public List<Integer> select_healId(){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        List<Integer> list = new ArrayList<Integer>();
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("select id from health");
            set = stat.executeQuery();
            while (set.next()){
                list.add(set.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return list;
    }
    public Health select_heal(int id){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        Health health = new Health();
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("select health,h_show,id from health where id = ?");
            stat.setInt(1,id);
            set = stat.executeQuery();
            while (set.next()){
                health.setHealth(set.getString(1));
                health.setShow(set.getInt(2));
                health.setHeal_id(set.getInt(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return health;
    }
    public int delete_heal(int id){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("delete from health where id = ?");
            stat.setInt(1,id);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public int update_heal(int heal_id){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("update health set h_show = ? where id = ?");
            stat.setInt(1,1);
            stat.setInt(2,heal_id);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public int update_noShow(int id){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("update health set h_show = ? where id = ?");
            stat.setInt(1,0);
            stat.setInt(2,id);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public List<String> select_show(){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        List<String> list = new ArrayList<String>();
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("select health from health where h_show = ?");
            stat.setInt(1,1);
            set = stat.executeQuery();
            while (set.next()){
                list.add(set.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return list;
    }
    public List<Integer> select_drugId(String classification){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        List<Integer> list = new ArrayList<Integer>();
        int id;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select id from drug where classification = ?");
            stat.setString(1,classification);
            set = stat.executeQuery();
            while (set.next()){
               id = set.getInt(1);
               list.add(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return list;
    }
    public Drug select_drugOne(int id){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        Drug drug = new Drug();
        try {
            conn = DB.getConn();
                stat = conn.prepareStatement("select name,price,picture from drug where id = ?");
                stat.setInt(1,id);
                set = stat.executeQuery();
                while (set.next()){
                    drug.setM_name(set.getString(1));
                    drug.setPrice(set.getDouble(2));
                    drug.setPicture(set.getString(3));
                }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return drug;
    }
    public Drug select_detail(String name){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        Drug drug = new Drug();
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select name,price,picture,classification,effect,m_use,remarks,taboo from drug where name = ?");
            stat.setString(1,name);
            set = stat.executeQuery();
            while (set.next()){
                drug.setM_name(set.getString(1));
                drug.setPrice(set.getDouble(2));
                drug.setPicture(set.getString(3));
                drug.setClassification(set.getString(4));
                drug.setEffect(set.getString(5));
                drug.setM_use(set.getString(6));
                drug.setRemark(set.getString(7));
                drug.setTaboo(set.getString(8));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return drug;
    }
    public List<Integer> comment_drugID(String drug){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        List<Integer> list = new ArrayList<Integer>();
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select id from comment where drug = ?");
            stat.setString(1,drug);
            set = stat.executeQuery();
            while (set.next()){
                list.add(set.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return list;
    }
    public Comment comment_drug(int id){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        Comment comment = new Comment();
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select username,comment,m_time from comment where id = ?");
            stat.setInt(1,id);
            set = stat.executeQuery();
            while (set.next()){
                comment.setUsername(set.getString(1));
                comment.setComment(set.getString(2));
                comment.setTime(set.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
            return comment;
        }
        public int insert_shop(Shop shop){
            Connection conn = null;
            PreparedStatement stat = null;
            int result = 0;
            try {
                conn = DB.getConn();
                stat = conn.prepareStatement("insert into shop(username, drug, piece,price) values (?,?,?,?)");
                stat.setString(1,shop.getUsername());
                stat.setString(2,shop.getDrug());
                stat.setInt(3,shop.getPiece());
                stat.setDouble(4,shop.getPrice());
                result = stat.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DB.release(null,stat,conn);
            }
            return result;
        }
        public List<Integer> select_shopId(String username){
            Connection conn = null;
            PreparedStatement stat = null;
            ResultSet set = null;
            List<Integer> list = new ArrayList<Integer>();
            try {
                conn = DB.getConn();
                stat = conn.prepareStatement("select id from shop where username = ?");
                stat.setString(1,username);
                set = stat.executeQuery();
                while (set.next()){
                    list.add(set.getInt(1));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DB.release(set,stat,conn);
            }
            return list;
        }
        public Shop select_shopAll(int id){
            Connection conn = null;
            PreparedStatement stat = null;
            ResultSet set = null;
            Shop shop = new Shop();
            try {
                conn = DB.getConn();
                stat = conn.prepareStatement("select username,drug,price,piece,id from shop where id = ?");
                stat.setInt(1,id);
                set = stat.executeQuery();
                while (set.next()){
                    shop.setUsername(set.getString(1));
                    shop.setDrug(set.getString(2));
                    shop.setPrice(set.getDouble(3));
                    shop.setPiece(set.getInt(4));
                    shop.setId(set.getInt(5));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DB.release(set,stat,conn);
            }
            return shop;
        }
        public User user_shop(String username){
            Connection conn = null;
            PreparedStatement stat = null;
            ResultSet set = null;
            User user = new User();
            try {
                conn = DB.getConn();
                stat = conn.prepareStatement("select address from user where username = ?");
                stat.setString(1,username);
                set = stat.executeQuery();
                while (set.next()){
                    user.setAddress(set.getString(1));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DB.release(set,stat,conn);
            }
            return user;
        }
        public int shop_delete(int id){
            Connection conn = null;
            PreparedStatement stat = null;
            int result = 0;
            try {
                conn = DB.getConn();
                stat = conn.prepareStatement("delete from shop where id = ?");
                stat.setInt(1,id);
                result = stat.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DB.release(null,stat,conn);
            }
            return result;
        }
        public User select_order(String username){
            Connection conn = null;
            PreparedStatement stat = null;
            ResultSet set = null;
            User user = new User();
            try {
                conn = DB.getConn();
                stat = conn.prepareStatement("select address,uname,phone,username from user where username = ?");
                stat.setString(1,username);
                set = stat.executeQuery();
                while (set.next()){
                    user.setAddress(set.getString(1));
                    user.setUname(set.getString(2));
                    user.setPhone(set.getString(3));
                    user.setUsername(set.getString(4));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DB.release(set,stat,conn);
            }
            return user;
         }
         public int insert_orderOne(String drug,int piece,String time,User user){
             Connection conn = null;
             PreparedStatement stat = null;
             int res = 0;
             try {
                 conn = DB.getConn();
                 stat = conn.prepareStatement("insert into m_order(drug,piece,uname,address,phone,username,order_time,state,receive,evaluate) values (?,?,?,?,?,?,?,0,0,0)");
                 stat.setString(1,drug);
                 stat.setInt(2,piece);
                 stat.setString(3,user.getUname());
                 stat.setString(4,user.getAddress());
                 stat.setString(5,user.getPhone());
                 stat.setString(6,user.getUsername());
                 stat.setString(7,time);
                 res = stat.executeUpdate();
             }catch (SQLException e){
                 e.printStackTrace();
             }finally {
                 DB.release(null,stat,conn);
             }
             return res;
         }
    public int insert_orderTwo(String drug,int piece,String address,String time,User user){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("insert into m_order(drug,piece,uname,address,phone,username,order_time,state,receive,evaluate) values (?,?,?,?,?,?,?,?,?,?)");
            stat.setString(1,drug);
            stat.setInt(2,piece);
            stat.setString(3,user.getUname());
            stat.setString(4,address);
            stat.setString(5,user.getPhone());
            stat.setString(6,user.getUsername());
            stat.setString(7,time);
            stat.setInt(8,0);
            stat.setInt(9,0);
            stat.setInt(10,0);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public List<Integer> select_orderId(String username){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        List<Integer> list = new ArrayList<Integer>();
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select id from m_order where username = ?");
            stat.setString(1,username);
            set = stat.executeQuery();
            while (set.next()){
                list.add(set.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return list;
    }
    public Order select_orderAll(int id){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        Order order = new Order();
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select drug,state,receive,order_time,evaluate from m_order where id = ?");
            stat.setInt(1,id);
            set = stat.executeQuery();
            while (set.next()){
               order.setDrug(set.getString(1));
               order.setState(set.getInt(2));
               order.setReceive(set.getInt(3));
               order.setTime(set.getString(4));
               order.setEvaluate(set.getInt(5));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return order;
    }
    public int update_receive(String username,String time){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("update m_order set receive = 1 where username = ? and order_time = ?");
            stat.setString(1,username);
            stat.setString(2,time);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public int update_evaluate(String username,String date){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("update m_order set evaluate = 1 where username = ? and order_time = ?");
            stat.setString(1,username);
            stat.setString(2,date);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public Order select_notEval(String username){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        Order order = new Order();
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select drug,state,receive,evaluate from m_order where username = ? and evaluate ==0");
            stat.setString(1,username);
            set = stat.executeQuery();
            while (set.next()){
                order.setDrug(set.getString(1));
                order.setState(set.getInt(2));
                order.setReceive(set.getInt(3));
                order.setEvaluate(set.getInt(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return order;
    }
    public int insert_comm(Comment comment){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("insert into comment(comment,username,drug,m_time) values (?,?,?,?)");
            stat.setString(1, comment.getComment());
            stat.setString(2,comment.getUsername());
            stat.setString(3,comment.getDrug());
            stat.setString(4,comment.getTime());
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public List<Integer> man_orderId(int state){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        List<Integer> list = new ArrayList<Integer>();
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select id from m_order where state = ?");
            stat.setInt(1,state);
            set = stat.executeQuery();
            while (set.next()){
                list.add(set.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return list;
    }
    public Order man_orderAll(int id){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        Order order = new Order();
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select uname,username,address,phone,drug,piece,state,order_time,id from m_order where id = ?");
            stat.setInt(1,id);
            set = stat.executeQuery();
            while (set.next()){
                order.setUname(set.getString(1));
                order.setUsername(set.getString(2));
                order.setAddress(set.getString(3));
                order.setPhone(set.getString(4));
                order.setDrug(set.getString(5));
                order.setPiece(set.getInt(6));
                order.setState(set.getInt(7));
                order.setTime(set.getString(8));
                order.setId(set.getInt(9));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return order;
    }
    public int manUpState(int id){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("update m_order set state = 1 where id = ?");
            stat.setInt(1,id);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public List<Integer> man_commId(){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        List<Integer> list = new ArrayList<Integer>();
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select id from comment");
            set = stat.executeQuery();
            while(set.next()){
                list.add(set.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return list;
    }
    public Comment man_CommAll(int id){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        Comment comment = new Comment();
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select username,drug,comment,m_time,id from comment where id = ?");
            stat.setInt(1, id);
            set = stat.executeQuery();
            while (set.next()){
                comment.setUsername(set.getString(1));
                comment.setDrug(set.getString(2));
                comment.setComment(set.getString(3));
                comment.setTime(set.getString(4));
                comment.setId(set.getInt(5));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return comment;
    }
    public int dele_comm(int id){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("delete from comment where id = ?");
            stat.setInt(1, id);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public int man_deleteDrug(String drugName){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("delete from drug where name = ?");
            stat.setString(1,drugName);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public int man_updatePrice(String drugName,double price){
           Connection conn = null;
           PreparedStatement stat = null;
           int result = 0;
           try{
               conn = DB.getConn();
               stat = conn.prepareStatement("update drug set price = ? where name = ?");
               stat.setDouble(1,price);
               stat.setString(2,drugName);
               result = stat.executeUpdate();
           }catch (SQLException e){
               e.printStackTrace();
           }finally {
               DB.release(null,stat,conn);
           }
           return result;
    }
    public int man_insertDrug(Drug drug){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("insert into drug(name, price, effect, m_use, taboo, classification, remarks, picture) values (?,?,?,?,?,?,?,?)");
            stat.setString(1,drug.getM_name());
            stat.setDouble(2,drug.getPrice());
            stat.setString(3,drug.getEffect());
            stat.setString(4,drug.getM_use());
            stat.setString(5,drug.getTaboo());
            stat.setString(6,drug.getClassification());
            stat.setString(7,drug.getRemark());
            stat.setString(8,drug.getPicture());
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public int manMaxDrugId(){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set  = null;
        int id = 0;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("select Max(id) from drug");
            set = stat.executeQuery();
            while (set.next()){
                id = set.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return id;
    }
    public int manUpPicture(int id,String url){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("update drug set picture = ? where  id = ?");
            stat.setString(1,url);
            stat.setInt(2,id);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public List<Integer> start_DrugId(){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        List<Integer> list = new ArrayList<Integer>();
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("select id from drug where supreme = ?");
            stat.setInt(1,1);
            set = stat.executeQuery();
            while (set.next()){
               list.add(set.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return list;
    }
    public Drug start_drug(int id){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        Drug drug = new Drug();
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("select name,picture,price from drug where id = ?");
            stat.setInt(1,id);
            set = stat.executeQuery();
            while (set.next()){
               drug.setM_name(set.getString(1));
               drug.setPicture(set.getString(2));
               drug.setPrice(set.getDouble(3));
            }
       }catch (SQLException e){
                e.printStackTrace();
       }finally {
        DB.release(set,stat,conn);
    }
        return drug;
    }
    public List<Integer> userLikeDrug(String name) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        List<Integer> list = new ArrayList<Integer>();
        String nameAll = '%' + name + '%';
        try {
            conn = DB.getConn();
            stat = conn.prepareStatement("select id from drug where name like ?");
            stat.setString(1, nameAll);
            set = stat.executeQuery();
            while (set.next()) {
                list.add(set.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.release(set, stat, conn);
        }
        return list;
    }
    public List<String> select_userName(){
        Connection conn = null;
        PreparedStatement stat = null;
        List<String> list = new ArrayList<String>();
        ResultSet set = null;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("select username from user where identity  = ?");
            stat.setString(1,"用户");
            set = stat.executeQuery();
            while (set.next()){
                list.add(set.getString(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return list;
    }
    public List<String> select_userName_login(){
        Connection conn = null;
        PreparedStatement stat = null;
        List<String> list = new ArrayList<String>();
        ResultSet set = null;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("select username from user");
            set = stat.executeQuery();
            while (set.next()){
                list.add(set.getString(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return list;
    }
    public int addRemarks(String drugName,String remarks){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("update drug set remarks = ? where name = ?");
            stat.setString(1,remarks);
            stat.setString(2,drugName);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public List<String> goodDrugName(){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        List<String> list = new ArrayList<String>();
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("select name from drug where supreme = ?");
            stat.setInt(1,1);
            set = stat.executeQuery();
            while (set.next()){
                list.add(set.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return list;
    }
    public int updateGood(String name){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("update drug set supreme = ? where name = ?");
            stat.setInt(1,0);
            stat.setString(2,name);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public int updateGood2(String name){
        Connection conn = null;
        PreparedStatement stat = null;
        int result = 0;
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("update drug set supreme = ? where name = ?");
            stat.setInt(1,1);
            stat.setString(2,name);
            result = stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(null,stat,conn);
        }
        return result;
    }
    public List<String> select_phone(){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        List<String> list = new ArrayList<String>();
        try{
            conn = DB.getConn();
            stat = conn.prepareStatement("select phone from user");
            set = stat.executeQuery();
            while (set.next()){
                list.add(set.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.release(set,stat,conn);
        }
        return list;
    }
}