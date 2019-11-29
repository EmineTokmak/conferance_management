
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HakemOperation {
           private Connection con = null;
    
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    private int hakem_id;
    private String Hakem_ad;
    private String Hakem_soyad;
    private String hakem_ilgialani;

    public HakemOperation(int hakem_id, String Hakem_ad, String Hakem_soyad,String hakem_ilgialani) {
        this.hakem_id = hakem_id;
        this.Hakem_ad = Hakem_ad;
        this.Hakem_soyad = Hakem_soyad;
        this.hakem_ilgialani=hakem_ilgialani;
    }

    public int getHakem_id() {
        return hakem_id;
    }

    public void setHakem_id(int hakem_id) {
        this.hakem_id = hakem_id;
    }

    public String getHakem_ad() {
        return Hakem_ad;
    }

    public void setHakem_ad(String Hakem_ad) {
        this.Hakem_ad = Hakem_ad;
    }

    public String getHakem_soyad() {
        return Hakem_soyad;
    }

    public void setHakem_soyad(String Hakem_soyad) {
        this.Hakem_soyad = Hakem_soyad;
    }

    public String getHakem_ilgialani() {
        return hakem_ilgialani;
    }

    public void setHakem_ilgialani(String hakem_ilgialani) {
        this.hakem_ilgialani = hakem_ilgialani;
    }
public ArrayList<HakemOperation> HakemListele(){
         try {
             ArrayList<HakemOperation> cikti = new ArrayList<HakemOperation>();
             statement=con.createStatement();
             String sorgu="Select* From hakem";
             ResultSet rs=statement.executeQuery(sorgu);
             while(rs.next()){
             int HakemId=rs.getInt("hakem_id");
             String ad=rs.getString("ad");
             String soyad= rs.getString("soyad");
             String ilgiAlani=rs.getString("ilgi_alani");
             
             cikti.add(new HakemOperation(HakemId,ad,soyad,ilgiAlani));
              
             }
             return cikti;
             
         } catch (SQLException ex) {
             Logger.getLogger(Bildiri.class.getName()).log(Level.SEVERE, null, ex);
         }
                return null;
               
               }

    
    public boolean HakemGirisYap(String kullanici_id, String sifre){
        
    String sorgu="Select * From hakem where hakem_id=? and sifre=? ";
    
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
    
     public void HakemEkle(String ad,String soyad,String sifre,String email,String ilgi_alani){
     String sorgu="Insert Into hakem (ad,soyad,sifre,email,ilgi_alani) VALUES (?,?,?,?,?)";
     
           try {
               preparedStatement=con.prepareStatement(sorgu);
               
               preparedStatement.setString(1,ad);
                preparedStatement.setString(2,soyad);
                preparedStatement.setString(3,sifre);
                 preparedStatement.setString(4,email);
                 preparedStatement.setString(5,ilgi_alani);
                 
                 
               preparedStatement.executeUpdate();
               
               
           } catch (SQLException ex) {
               Logger.getLogger(KullaniciOperation.class.getName()).log(Level.SEVERE, null, ex);
           }
    
    
    }
    
    public HakemOperation()
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
    
    //public static void main(String[] args)
    {
    //KullaniciOperation islemler =new KullaniciOperation();
    }
}
    

