
import com.sun.corba.se.spi.orbutil.fsm.Guard;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.rsa.RSACore;

public class Bildiri {
     private Connection con = null;
    
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    private int bildiriID;
    private String baslik;
     private String email;
      private String yazarlar;
       private String kurum;
        private String ozet;
        private String keyword;
         private String yazar_id;
          private String bildiri_form;

    public Bildiri(int bildiriID, String baslik,String yazarlar,String keyword) {
        this.bildiriID = bildiriID;
        this.baslik = baslik;
        //this.email = email;
        this.yazarlar = yazarlar;
        //this.kurum = kurum;
        //this.ozet = ozet;
        this.keyword = keyword;
        //this.konu = konu;
        //this.bildiri_form = bildiri_form;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public int getBildiriID() {
        return bildiriID;
    }

    public void setBildiriID(int bildiriID) {
        this.bildiriID = bildiriID;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYazarlar() {
        return yazarlar;
    }

    public void setYazarlar(String yazarlar) {
        this.yazarlar = yazarlar;
    }

    public String getKurum() {
        return kurum;
    }

    public void setKurum(String kurum) {
        this.kurum = kurum;
    }

    public String getOzet() {
        return ozet;
    }

    public void setOzet(String ozet) {
        this.ozet = ozet;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getYazar_id() {
        return yazar_id;
    }

    public void setYazar_id(String konu) {
        this.yazar_id = yazar_id;
    }

    public String getBildiri_form() {
        return bildiri_form;
    }

    public void setBildiri_form(String bildiri_form) {
        this.bildiri_form = bildiri_form;
    }
          
    
    public void BildiriYukle(String baslik,String email,String yazarlar,String calistiklari_kurum,String ozet,String keyword,int yazar_id,String Bildiri_form){
     String sorgu="Insert Into bildiri (baslik,emailler,yazarlar,calistiklari_kurum,ozet,keyword,yazar_id,bildiriform) VALUES (?,?,?,?,?,?,?,?)";
     
           try {
               preparedStatement=con.prepareStatement(sorgu);
               
               preparedStatement.setString(1,baslik);
                preparedStatement.setString(2,email);
                preparedStatement.setString(3,yazarlar);
                 preparedStatement.setString(4,calistiklari_kurum);
                 preparedStatement.setString(5,ozet);
                 preparedStatement.setString(6, keyword);
                 preparedStatement.setInt(7, yazar_id);
                 preparedStatement.setString(8,Bildiri_form);
                 
                 
               preparedStatement.executeUpdate();
               
               
           } catch (SQLException ex) {
               Logger.getLogger(KullaniciOperation.class.getName()).log(Level.SEVERE, null, ex);
           }
    
        
              
           }
     public ArrayList<Bildiri> BildiriListele(){
         try {
             ArrayList<Bildiri> cikti = new ArrayList<Bildiri>();
             statement=con.createStatement();
             String sorgu="Select* From bildiri";
             ResultSet rs=statement.executeQuery(sorgu);
             while(rs.next()){
             int bildiriId=rs.getInt("bildiri_id");
             String baslik=rs.getString("baslik");
             String yazarlar= rs.getString("yazarlar");
             String AnahtarKelime=rs.getString("keyword");
             
             cikti.add(new Bildiri(bildiriId,baslik,yazarlar,AnahtarKelime));
              
             }
             return cikti;
             
         } catch (SQLException ex) {
             Logger.getLogger(Bildiri.class.getName()).log(Level.SEVERE, null, ex);
         }
                return null;
               
               }
  
    
    
     public Bildiri()
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
    

