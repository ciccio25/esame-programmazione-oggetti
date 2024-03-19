package oggetti;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Pianificazione{

    /*
     * Classe AstraFa Pianificazione: 
     * Classe astratta dalla quale vengono ereditate le classi Progetto e Task. 
     * Attributi: nome, descrizione, data di inizio, data di scadenza, stato. 
     * Contiene i metodi astratti per la gestione dei progetti e dei task. 
     * 
     * Nota dal sottoscritto: 
     * Gli attributi di questa classe NON seguono la traccia (purtroppo). 
     * Ho perso il progetto più recente con le giuste modifiche che seguono la traccia.  
     * 
     */

    private ArrayList <Progetto> listaProgetti; 
    private ArrayList <Task> listaTask;

    public Pianificazione(ArrayList <Progetto> listaProgetti, ArrayList <Task> listaTask){
        this.listaProgetti = listaProgetti;
        this.listaTask = listaTask;
        
    }

    public abstract ArrayList <Progetto> getListaProgetti();
    public abstract ArrayList <Task> getListaTask();

    
}
    
