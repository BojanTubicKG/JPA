
package assignmentperzistencija;

import java.io.Serializable;

public class Zaposleni implements Serializable{
    
    private int id;
    private String ime;
    private int godine;
    private String adresa;
    private String zarada;

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public int getGodine() {
        return godine;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getZarada() {
        return zarada;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setGodine(int godine) {
        this.godine = godine;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setZarada(String zarada) {
        this.zarada = zarada;
    }

    public Zaposleni() {
    }

    public Zaposleni(int id, String ime, int godine, String adresa, String zarada) {
        this.id = id;
        this.ime = ime;
        this.godine = godine;
        this.adresa = adresa;
        this.zarada = zarada;
    }
    
    public Zaposleni(String ime, int godine, String adresa, String zarada) {
        this.ime = ime;
        this.godine = godine;
        this.adresa = adresa;
        this.zarada = zarada;
    }
    
       @Override
    public String toString() {
        return id + " ; " + ime + " ; " + godine +" ; " + adresa + " ; " + zarada;
    }
    
   
    
}
