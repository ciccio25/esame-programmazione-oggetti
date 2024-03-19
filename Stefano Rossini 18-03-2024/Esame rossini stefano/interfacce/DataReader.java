package interfacce;

import java.util.ArrayList;


import oggetti.Progetto;
import oggetti.Sviluppatore;
import oggetti.Task;

public interface DataReader {

    /*
     * Interfaccia DataReader 
     * 
     * Definisce i metodi per la lettura dei dati dei progetti da un file di testo. 
     * Definisce un metodo generico leggiPianificazione() per la lettura dei dati dei progetti e dei task.
     * 
     */

    ArrayList<Progetto> lettura_progetti(String sezione);
    ArrayList<Sviluppatore> lettura_sviluppatori(String sezione);
    ArrayList<Task> lettura_tasks(String sezione);
    <T> void leggiPianificazione(String file_path); 
}
