package oggetti;

import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    
    /**
     * Classe Task: 
     * Rappresenta un'attvità specifica da completare all'interno di un progetto. 
     * Attributi aggiuntivi: progetto associato. 
     * Metodi per la gestione delle informazioni sul task.
     *  
     */
    private String nome; 
    private String descrizione;
    private LocalDate data_di_inizio; 
    private LocalDate data_di_scadenza;
    private String stato;
    private ArrayList <String> progetti_associati; 
    
    public Task(String nome, String descrizione, LocalDate data_di_inizio, LocalDate data_di_scadenza, String stato, ArrayList <String> progetti_associati) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.data_di_inizio = data_di_inizio;
        this.data_di_scadenza = data_di_scadenza;
        this.stato = stato;
        this.progetti_associati = progetti_associati;
    }
    
    public String getNome() {
        return nome;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public LocalDate getData_di_inizio() {
        return data_di_inizio;
    }
    public LocalDate getData_di_scadenza() {
        return data_di_scadenza;
    }
    public String getStato() {
        return stato;
    }
    public ArrayList <String> getProgetti_associati() {
        return progetti_associati;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public void setData_di_inizio(LocalDate data_di_inizio) {
        this.data_di_inizio = data_di_inizio;
    }
    public void setData_di_scadenza(LocalDate data_di_scadenza) {
        this.data_di_scadenza = data_di_scadenza;
    }
    public void setStato(String stato) {
        this.stato = stato;
    }
    public void setProgetti_associati(ArrayList <String> progetti_associati) {
        this.progetti_associati = progetti_associati;
    }    
}
