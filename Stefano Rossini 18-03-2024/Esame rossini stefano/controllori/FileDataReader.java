package controllori;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import interfacce.DataReader;

import oggetti.Progetto;
import oggetti.Sviluppatore;
import oggetti.Task;

public class FileDataReader implements DataReader {

    /*
     * Classe FileDataReader (Implementazione di DataReader): 
     * 
     * Responsabile per la lettura e l'interpretazione dei dati dei progetti da un file di testo. 
     * Implementa i metodi definiti nell'interfaccia DataReader per leggere le informazioni 
     * sui progetti, gli sviluppatori e i task, convertendoli in oggetti utilizzabili dal sistema.
     * 
     */

    private ArrayList <Progetto> listaProgetti;
    private ArrayList <Sviluppatore> listaSviluppatori;
    private ArrayList <Task> listaTask;

    public FileDataReader(ArrayList <Progetto> listaProgetti, ArrayList <Sviluppatore> listaSviluppatori,  ArrayList <Task> listaTask) {
        this.listaProgetti = listaProgetti;
        this.listaTask = listaTask;
        this.listaSviluppatori = listaSviluppatori; 
    }
    
    
    public ArrayList <Progetto> getListaProgetti() {
        return listaProgetti;
    }
    public ArrayList <Sviluppatore> getListaSviluppatori() {
        return listaSviluppatori;
    }
    public ArrayList <Task> getListaTask() {
        return listaTask;
    }

    //Qui sotto si svolgono gli override dei metodi indicati nell'interfaccia DataReader 
    @Override
    public ArrayList<Progetto> lettura_progetti(String sezione) {
        

        ArrayList<String> listaProgetti_dal_file = new ArrayList<String>(Arrays.asList(sezione.split("\n")));

        ArrayList <Progetto> lista_progetti = new ArrayList<Progetto>(); 
        
         for (int i = 0; i < listaProgetti_dal_file.size(); i++) {

            String[] progetto_riga = listaProgetti_dal_file.get(i).replaceAll("\\s", "").split(",");

            /**
             * Si instanzia l'elemento Progetto p perchè la classe progetto è un extend di Pianificazione, 
             * quest'ultima è una classe astratta
             * 
            */
            Progetto p = new Progetto(progetto_riga[0], progetto_riga[1], LocalDate.parse(progetto_riga[2]), LocalDate.parse(progetto_riga[3]), progetto_riga[4]);

            lista_progetti.add(p);

            
        }   



        return lista_progetti; 
    }

    @Override
    public ArrayList<Sviluppatore> lettura_sviluppatori(String sezione) {

        ArrayList<String> listaSviluppatori_dal_file = new ArrayList<String>(Arrays.asList(sezione.split("\n")));
        
    

        ArrayList <Sviluppatore> team_sviluppatori = new ArrayList<Sviluppatore>();
        
        
        
        for (int i = 0; i < listaSviluppatori_dal_file.size(); i++) {
            
            String[] sviluppatore_riga = listaSviluppatori_dal_file.get(i).replaceAll("\\s", "").split(",");

            int task_assegnati = 0;
            
            
            //Trovare quante task per ogni sviluppatore ci sono 
            for(int j = 0; j < sviluppatore_riga.length; j++)
            {
                if (sviluppatore_riga[j].startsWith("Task_") ) {

                    task_assegnati++;

                }
            }

            
            ArrayList<String> task_sviluppatore = new ArrayList<String>();

            //Mettere quelle task nell'arraylist task_sviluppatore
            for(int j = 0; j < sviluppatore_riga.length; j++)
            {
                if (sviluppatore_riga[j].startsWith("Task_") ) {

                    task_sviluppatore.add(sviluppatore_riga[j]);
                
                }
            }

            ArrayList<String> competenze_sviluppatore = new ArrayList<String>();
    

            //Mettere quelle competemze nel vettore competenze_sviluppatore
            for(int j = 2; j < sviluppatore_riga.length-task_assegnati; j++)
            {
                competenze_sviluppatore.add(sviluppatore_riga[j]);
            } 
           
            

            team_sviluppatori.add(new Sviluppatore(sviluppatore_riga[0], sviluppatore_riga[1], competenze_sviluppatore, task_sviluppatore));

            
            
            

        }

        return team_sviluppatori; 
    }

    @Override
    public ArrayList<Task> lettura_tasks(String sezione) {
       
        ArrayList<String> listaTasks_da_file = new ArrayList<String>(Arrays.asList(sezione.split("\n")));
        
        ArrayList <Task> tasks = new ArrayList<Task>();


         for (int i = 0; i < listaTasks_da_file.size(); i++) {
            
            String[] task_riga = listaTasks_da_file.get(i).replaceAll("\\s", "").split(",");


            ArrayList <String> progetti_riga = new ArrayList<String>(); 
            
            for(int j = 0; j < task_riga.length; j++)
            {
                if (task_riga[j].startsWith("Progetto_") ) {

                    progetti_riga.add(task_riga[j]);
                
                }
            }
            
            /**
             * Si instanzia l'elemento Task p perchè la classe progetto è un extend di Pianificazione, 
             * quest'ultima è una classe astratta
             * 
            */

            Task t = new Task(task_riga[0], task_riga[1], LocalDate.parse(task_riga[2]), LocalDate.parse(task_riga[3]), task_riga[4], progetti_riga);
            tasks.add(t);
            
        }

        return tasks;
    }

    @Override
    public <T> void leggiPianificazione(String file_path) {
        
        //Dal percorso del file si ottiene gli elementi "usabili" dal software
        //Per maggior dettagli, vedi Interfaccia DataReader

        try {
            
            Scanner fileInput = new Scanner(new FileInputStream(file_path));
            fileInput.useDelimiter("###\n");
            String[] sezioniFile = new String[3];
            int i=0;
            
            while(fileInput.hasNext()) {
                sezioniFile[i] = fileInput.next();
                i++;
            }
            fileInput.close();

            this.listaProgetti = lettura_progetti(sezioniFile[0]); 
            this.listaSviluppatori = lettura_sviluppatori(sezioniFile[1]); 
            this.listaTask = lettura_tasks(sezioniFile[2]); 

          
            
        } catch (Exception e) {
            System.out.println("Errore nella lettura del file");
        }


      
    }


    
   
    
}
