
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OKBoperation {
    private Connection con = null;
    
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
        public boolean OKBGirisYap(String kullanici_id, String sifre){
        
    String sorgu="Select * From okb where okb_id=? and sifre=? ";
    
           try {
               preparedStatement =con.prepareStatement(sorgu);
               preparedStatement.setString(1, kullanici_id);
               preparedStatement.setString(2, sifre);
               
               ResultSet rs =preparedStatement.executeQuery();
               
               return rs.next();
           } catch (SQLException ex) {
               Logger.getLogger(KullaniciOperation.class.getName()).log(Level.SEVERE, null, ex);
           }
           
    return false;
    }
        
         public void AtamaYap(int bildiriId,int hakem1,int hakem2, int hakem3){
     String sorgu="Insert Into atama (bildiri_id,hakem1_id,hakem2_id,hakem3_id) VALUES (?,?,?,?)";
     
           try {
               preparedStatement=con.prepareStatement(sorgu);
               
               preparedStatement.setInt(1,bildiriId);
                preparedStatement.setInt(2,hakem1);
                preparedStatement.setInt(3,hakem2);
                 preparedStatement.setInt(4,hakem3);
                 
                 
               preparedStatement.executeUpdate();
               
               
           } catch (SQLException ex) {
               Logger.getLogger(KullaniciOperation.class.getName()).log(Level.SEVERE, null, ex);
           }
    
    
    }
    
    public OKBoperation()
    {
     // "jbdc:mysql://localhost:3306/demo" 
        String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.db_ismi+ "?useUnicode=true&characterEncoding=utf8";
        
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }
        
        
        try {
            con = DriverManager.getConnection(url, Database.kullanici_adi, Database.parola);
            System.out.println("Bağlantı Başarılı...");
            
            
        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
            //ex.printStackTrace();
        }
    }
    
}
