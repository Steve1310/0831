package pkg0831;
//這裡實作DAO

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.AnySeqHelper;

public class OrderDAOImpl implements OrderDAO {

    final String DRIVER_NAME = "com.mysql.jdbc.Driver"; //SQL驅動程式
    final String CONN_STRING = "jdbc:mysql://localhost:3306/mydb?"
            + "user=root&password=123456";      //SQL網址與帳號密碼

    @Override
    public void add(Order o) { //新增資料到SQL
        try {                                 //要try catch block
            Class.forName(DRIVER_NAME);     //SQL驅動程式
            Connection conn = DriverManager.getConnection(CONN_STRING);//建立Connection物件才能建立PreparedStatement
            conn.setAutoCommit(false);//這個指令讓SQL語法不會馬上寫入資料庫
            //先設定Order的值進入資料庫
            PreparedStatement pstmt = conn.prepareStatement("Insert into `Order` (CustomerName, CustomerTel) values (?,?)", Statement.RETURN_GENERATED_KEYS);//這裡下SQL指令,Statement.RETURN_GENERATED_KEYS 是回傳auto_ID的指令
            pstmt.setString(1, o.CustomerName);//設定第一個問號的值
            pstmt.setString(2, o.CustomerTel);//設定第二個問號的值
            pstmt.executeUpdate();//執行
            ResultSet rs = pstmt.getGeneratedKeys();//游標放置在自動產生ID的資料上
            rs.next();//讀取資料行的下一行
            int auto_id = rs.getInt(1);//取鼠標的一個字 當作auto_id
            rs.close();
            //設定OrderDetail的值進資料庫
            for (OrderDetail od : o.Detail) {
                PreparedStatement pstmt2 = conn.prepareStatement("Insert into order_detail (order_id, ItemName, num) values (?, ?,?)");
                pstmt2.setInt(1, auto_id);
                pstmt2.setString(2, od.ItemName);
                pstmt2.setInt(3, od.num);
                pstmt2.executeUpdate();
            }
            conn.commit();//完整執行,開始執行SQL指令(對應conn.setAutoCommit(false);)
            conn.setAutoCommit(true);//完整執行,開始執行SQL指令(對應conn.setAutoCommit(false);)
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void edit(Order o) {
        try {
            Class.forName(DRIVER_NAME);     //SQL驅動程式
            Connection conn = DriverManager.getConnection(CONN_STRING);//SQL位置
            conn.setAutoCommit(false);//這個指令讓SQL語法不會馬上寫入資料庫
            PreparedStatement pstmt = conn.prepareStatement("update `Order` Set CustomerName=?, CustomerTel=? where id=? ");
            pstmt.setString(1, o.CustomerName);
            pstmt.setString(2, o.CustomerTel);
            pstmt.setInt(3, o.id);
            pstmt.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void remove(int i) {
        try {
            Class.forName(DRIVER_NAME);     //SQL驅動程式
            Connection conn = DriverManager.getConnection(CONN_STRING);//SQL位置
            conn.setAutoCommit(false);//這個指令讓SQL語法不會馬上寫入資料庫
            PreparedStatement pstmt = conn.prepareStatement("Delete From  `Order`  where id=? ");

            pstmt.setInt(1, i);
            pstmt.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
            System.out.println("編號" + i + "資料刪除成功!!");
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Order> search(String str) {
        try {
            Class.forName(DRIVER_NAME);     //SQL驅動程式
            Connection conn = DriverManager.getConnection(CONN_STRING);//SQL位置
            conn.setAutoCommit(false);//這個指令讓SQL語法不會馬上寫入資料庫
            PreparedStatement pstmt = conn.prepareStatement("select * from `Order` where CustomerName=?");
            pstmt.setString(1, str);
            ResultSet rs = pstmt.executeQuery();
 ArrayList<Order> arrayList = new ArrayList();
       
            while (rs.next()) {
              Order  o = new Order(rs.getInt(1), rs.getString(2), rs.getString(3));
    arrayList.add(o);
                  System.out.println("編號" + o.id + "姓名" + o.CustomerName + "電話" + o.CustomerTel);
               
            }
     

            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
               return arrayList;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Order> getall() {
        try {
            Class.forName(DRIVER_NAME);     //SQL驅動程式
            Connection conn = DriverManager.getConnection(CONN_STRING);//SQL位置
            conn.setAutoCommit(false);//這個指令讓SQL語法不會馬上寫入資料庫
            PreparedStatement pstmt = conn.prepareStatement("select* from `Order`");
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Order> arrayList = new ArrayList();
            while (rs.next()) {
                Order o = new Order(rs.getInt(1), rs.getString(2), rs.getString(3));
                arrayList.add(o);
                  System.out.println("編號" + o.id + "姓名" + o.CustomerName + "電話" + o.CustomerTel);
            }

          
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();

            return arrayList;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
