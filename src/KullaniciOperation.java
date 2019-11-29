
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KullaniciOperation {
    
       private Connection con = null;
    
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    
    public void yazarEkle(String ad,String soyad,String sifre,String email){
     String sorgu="Insert Into kullanici (ad,soyad,sifre,email) VALUES (?,?,?,?)";
     
           try {
               preparedStatement=con.prepareStatement(sorgu);
               
               preparedStatement.setString(1,ad);
                preparedStatement.setString(2,soyad);
                preparedStatement.setString(3,sifre);
                 preparedStatement.setString(4,email);
                 
                 
               preparedStatement.executeUpdate();
               
               
           } catch (SQLException ex) {
               Logger.getLogger(KullaniciOperation.class.getName()).log(Level.SEVERE, null, ex);
           }
    
    
    }
    
    public boolean GirisYap(String kullanici_id, String sifre){
        
    String sorgu="Select * From kullanici where kullanici_id=? and sifre=? ";
    
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
    
    public KullaniciOperation()
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
