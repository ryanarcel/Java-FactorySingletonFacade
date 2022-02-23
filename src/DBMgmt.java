
import javax.swing.table.DefaultTableModel;

public interface DBMgmt {
    public Boolean add(String n, String p, String v); //method for adding data
    public DefaultTableModel getData();                          // method for fetching data
    public Boolean update(String id, String n, String p, String v); // method for updating a particular data
    public Boolean delete(String id);                           //method for deleting a particular data
}


