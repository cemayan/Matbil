package cemayan.com.matbil;

import java.util.Date;

public class Duyurular {

    private String  baslik;
    private String  icerik;
    private String  tarih;
    private String  ek;

    public Duyurular(String baslik, String icerik, String tarih, String ek) {
        super();
        this.baslik = baslik;
        this.icerik = icerik;
        this.tarih = tarih;
        this.ek = ek;
    }


    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }


    public String geticerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }



    public String gettarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }



    public String getEk() {
        return ek;
    }

    public void setEk(String ek) {
        this.ek = ek;
    }


}