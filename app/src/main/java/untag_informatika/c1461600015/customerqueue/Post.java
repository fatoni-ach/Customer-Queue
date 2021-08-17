package untag_informatika.c1461600015.customerqueue;

public class Post {
    private String nomor, nama, status, keperluan, loket, datetime;
    public Post(){

    }

    public Post(String nomor, String nama, String status, String keperluan, String loket, String datetime){
        this.nomor = nomor;
        this.nama = nama;
        this.status = status;
        this.keperluan = keperluan;
        this.loket = loket;
        this.datetime=datetime;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getNomor() {
        return nomor;
    }

    public String getNama() {
        return nama;
    }

    public String getStatus() {
        return status;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public String getLoket() {
        return loket;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public void setLoket(String loket) {
        this.loket = loket;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
