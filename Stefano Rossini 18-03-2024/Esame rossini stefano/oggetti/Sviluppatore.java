package oggetti;

import java.util.ArrayList;

public class Sviluppatore {
    /*
     * Classe Sviluppatore: 
     * 
     * Rappresenta uno sviluppatore che lavora su uno o più progetti. 
     * Attributi: nome, cognome, competenze, task assegnati. 
     * Metodi per la gestione delle informazioni sullo sviluppatore.
     * 
     */

    private String nome; 
    private String cognome; 
    private ArrayList <String> competenze;
    private ArrayList <String> task_assegnati;
    
    public Sviluppatore(String nome, String cognome, ArrayList <String> competenze, ArrayList <String> task_assegnati) {
        this.nome = nome;
        this.cognome = cognome;
        this.competenze = competenze;
        this.task_assegnati = task_assegnati;
    }
    
    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }
    public ArrayList<String> getCompetenze() {
        return competenze;
    }
    public ArrayList<String> getTask_assegnati() {
        return task_assegnati;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public void setCompetenze(ArrayList<String> competenze) {
        this.competenze = competenze;
    }
    public void setTask_assegnati(ArrayList<String> task_assegnati) {
        this.task_assegnati = task_assegnati;
    }

}
