import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class DBHandler implements DBMgmt{

    String conString = "jdbc:mysql://localhost:3306/ninja_db";
    String username = "root";
    String password = "";

    private static DBHandler instance = new DBHandler();

    private DBHandler(){
    }

    public static DBHandler getInstance(){
        return instance;
    }

    @Override
    public Boolean add(String name, String pos, String village) {
        //SQL STATEMENT
        String sql = "INSERT INTO tbl_ninja (Name,Position,Village) VALUES('" + name + "','" + pos + "','" + village + "')";

        try {
            //GET CONNECTION
            Connection con = DriverManager.getConnection(conString, username, password);

            // PREPARED STMT
            Statement s = con.prepareStatement(sql);

            s.execute(sql);

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public DefaultTableModel getData() {
        //ADD COLUMNS TO TABLE MODEL
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("ID");
        dm.addColumn("Name");
        dm.addColumn("Position");
        dm.addColumn("Village");

        //SQL STATEMENT
        String sql = "SELECT * FROM tbl_ninja";

        try {
            Connection con = DriverManager.getConnection(conString, username, password);

            //PREPARED STMT
            Statement s = con.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            //LOOP THRU GETTING ALL VALUES
            while (rs.next()) {
                //GET VALUES
                String id = rs.getString(1);
                String name = rs.getString(2);
                String pos = rs.getString(3);
                String village = rs.getString(4);

                dm.addRow(new String[]{id, name, pos, village});
            }

            return dm;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;

    }

    @Override
    public Boolean update(String id, String name, String pos, String village) {
        //SQL STMT
        String sql = "UPDATE tbl_ninja SET Name ='" + name + "',Position='" + pos + "',Village='" + village + "' WHERE ID='" + id + "'";

        try {
            //GET COONECTION
            Connection con = DriverManager.getConnection(conString, username, password);

            //STATEMENT
            Statement s = con.prepareStatement(sql);

            //EXECUTE
            s.execute(sql);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(String id)
    {
        //SQL STMT
        String sql="DELETE FROM tbl_ninja WHERE ID ='"+id+"'";

        try
        {
            //GET COONECTION
            Connection con=DriverManager.getConnection(conString, username, password);

            //STATEMENT
            Statement s=con.prepareStatement(sql);

            //EXECUTE
            s.execute(sql);

            return true;

        }catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }



}