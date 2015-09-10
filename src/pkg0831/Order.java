package pkg0831;

import java.util.ArrayList;
//order 包含orderdetail

public class Order {

    public int id;
    public String CustomerName;
    public String CustomerTel;
    public ArrayList<OrderDetail> Detail;

    public Order(int id, String CustomerName, String CustomerTel) {
        this.id = id;
        this.CustomerName = CustomerName;
        this.CustomerTel = CustomerTel;
        Detail = new ArrayList<>();
    }

}
