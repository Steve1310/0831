package DAO_test;

import java.sql.Array;
import java.util.ArrayList;
import pkg0831.*;

public class T01 {

    public static void main(String[] args) {
    // Order o = new Order(1, "abc", "6565656565656");

//     OrderDetail orderDetail=new OrderDetail(1,"Cake",10);
//     OrderDetail orderDetail2=new OrderDetail(2,"Cake",5);
//     OrderDetail orderDetail3=new OrderDetail(3,"Cake",100);
//     o.Detail.add(orderDetail);
//          o.Detail.add(orderDetail2);
//           o.Detail.add(orderDetail3);
        OrderDAO dao = new OrderDAOImpl();
   // dao.add(o);
//       dao.edit(o);
        //   dao.remove(5);
       
   
//   ArrayList<Order> arrayList = new ArrayList();
//      
//   
          ArrayList<Order> arrayList = dao.search("abc");
             for (Order s : arrayList){
      System.out.println( "編號:"+s.id+"  姓名:"+s.CustomerName+"  電話"+s.CustomerTel);
       }
        
//         ArrayList<Order> arrayList = dao.getall();
//       for (Order s : arrayList){
//           System.out.println( "編號:"+s.id+"  姓名:"+s.CustomerName+"  電話"+s.CustomerTel);
        
        
       
        
                
    }
       }

    


