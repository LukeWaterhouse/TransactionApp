package csc1035.project3;

public interface Update_Delete {

    public void deleteStock(int id, int n) ;

    public void addStock(int id, int n);

    public void updatewTrans(int id, int n);

    public void updatewoutTrans(int id, boolean add_del, int n);


}
