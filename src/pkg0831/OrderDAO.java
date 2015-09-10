package pkg0831;
//這是DAO介面,沒有實作

import java.util.ArrayList;


public interface OrderDAO {

    public void add(Order o);

    public void edit(Order o);

    public void remove(int i);

    public ArrayList<Order> search(String str);

    public ArrayList<Order> getall();

}
