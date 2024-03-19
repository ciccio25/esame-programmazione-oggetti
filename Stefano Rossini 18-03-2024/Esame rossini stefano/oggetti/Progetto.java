package oggetti;

import java.time.LocalDate;

public class Progetto {
    /*
     * Classe Sviluppatore: 
     * 
     * Rappresenta uno sviluppatore che lavora su uno o più progetti. 
     * Attributi: nome, cognome, competenze, task assegnati. 
     * Metodi per la gestione delle informazioni sullo sviluppatore
     * 
     */
    private String nome; 
    private String descrizione;
    private LocalDate data_di_inzio; 
    private LocalDate data_di_scadenza;
    private String stato;

    public Progetto(String nome, String descrizione, LocalDate data_di_inzio, LocalDate data_di_scadenza, String stato) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.data_di_inzio = data_di_inzio;
        this.data_di_scadenza = data_di_scadenza;
        this.stato = stato;
    }
    public String getNome() {
        return nome;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public LocalDate getData_di_inzio() {
        return data_di_inzio;
    }
    public LocalDate getData_di_scadenza() {
        return data_di_scadenza;
    }
    public String getStato() {
        return stato;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public void setData_di_inzio(LocalDate data_di_inzio) {
        this.data_di_inzio = data_di_inzio;
    }
    public void setData_di_scadenza(LocalDate data_di_scadenza) {
        this.data_di_scadenza = data_di_scadenza;
    }
    
    public void setStato(String stato) {
        this.stato = stato;
    }
    

}