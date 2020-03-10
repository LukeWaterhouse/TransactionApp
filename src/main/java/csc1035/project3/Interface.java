package csc1035.project3;

import java.util.List;

public interface Interface {

    public void addItem();

    public void deleteStock(int id, int n);

    public void addStock(int id, int n);

    public void printReceipt();

    public Stock getStockById();

    public void updateStock();

    public List<Stock> getStock();

    public void addTransaction();

}
