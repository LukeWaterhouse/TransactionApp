package csc1035.project3;

import java.util.List;

public interface Interface {

    public void addItem();

    public void deleteStock(int id);

    public void addStock(int id);

    public void printReceipt();

    public Stock getStockById();

    public void asciiOut(List<Stock> records);

    public List<Stock> getStock();

}
