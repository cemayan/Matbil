package cemayan.com.matbil;

public class AkademikPersonel {


    private String id;
    private String adi;
    private String soyadi;
    public AkademikPersonel(String id,String adi,String soyadi){
        super();
        this.id =id;this.adi=adi;this.soyadi=soyadi;
    }

    public String getAdi(){
        return adi;
    }
    public void setAdi(String adi){
        this.adi=adi;
    }


    public String getSoyadi(){
        return soyadi;
    }
    public void setSoyadi(String soyadi){
        this.soyadi=soyadi;
    }


    public String getId(){return id;}
    public  void setId(String id) {this.id = id;}
}
