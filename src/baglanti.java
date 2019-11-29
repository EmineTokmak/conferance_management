/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eminetokmak
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class baglanti {
    
    private String kullanici_adi = "root";
    private String parola = "";
    
    private String db_ismi = "kys";
    
    private String host =  "localhost";
    
    private int port = 3306;
    
    private Connection con = null;
    
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    
       
       
        
        
  
    
    
    public void yazarlariGetir() {
        
        String sorgu = "Select * From yazar";
      
        try {
            statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery(sorgu);
            
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String eposta = rs.getString("eposta");
                String sifre=rs.getString("sifre");
                
                System.out.println("Id : " + id + "Ad: " + ad + "Soyad : " + soyad + " Email : " + eposta+"Şifre:"+sifre);
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    public baglanti() {
        
        // "jbdc:mysql://localhost:3306/demo" 
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_ismi+ "?useUnicode=true&characterEncoding=utf8";
        
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }
        
        
        try {
            con = DriverManager.getConnection(url, kullanici_adi, parola);
            System.out.println("Bağlantı Başarılı...");
            
            
        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
            //ex.printStackTrace();
        }
        
    }
    //public static void main(String[] args) {
        
        
        
       // baglanti baglanti = new baglanti();
        //baglanti.yazarlariGetir();
        
        /*System.out.println("Silinmeden Önce........");
        baglanti.calisanlariGetir();
        System.out.println("********************************************");
        System.out.println("Silindikten Sonra........");
        //baglanti.calisanGuncelle();
        baglanti.calisanSil();*/
        
       // baglanti.calisanlariGetir();
        
        //baglanti.calisanEkle();
        //baglanti.calisanlariGetir();

        
        
    //}
    
    
}
