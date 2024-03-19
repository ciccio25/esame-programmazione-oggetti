/*
 * Obiettivo del Progetto: 
 * Sviluppare un sistema software per la gestione dei progetti alla Next Game. 
 * Il sistema dovrà consentire il monitoraggio dello stato dei progetti, l'allocazione delle risorse umane 
 * e materiali, la pianificazione delle attività e la valutazione delle prestazioni del team.
 * 
 * Software scritto da Stefano Rossini matricola sXXXXXXX UNIVPM 
 * per l'esame di Programmazione ad Oggetti 
 * 
 * Data 18/03/2024
 *
 */

package controllori;

import java.time.LocalDate;
import java.util.ArrayList;

import oggetti.Progetto;
import oggetti.Sviluppatore;
import oggetti.Task;

public class GestoreProgetti {

    /*
     * Classe GestoreProgetti: 
     * 
     * Coordina l'intera gestione dei progetti. 
     * Contiene la lista dei progetti attivi e dei membri del team. 
     * Metodi per il monitoraggio dello stato dei progetti, l'allocazione delle risorse 
     * e la valutazione delle prestazioni del team. 
     * 
     */

    private static FileDataReader fileDataReader = new FileDataReader(null, null, null);
    
    public static void riempiFileDataReader_da_file(String file_path) {
        fileDataReader.leggiPianificazione(file_path);
    }; 

    /**
     * Task 1: Report Progetto
     *  
     * Obiettivo:  Generare  un  report  che  presenti  le  informazioni  sui  progetti  della  Next  Game, 
     * inclusi  il  numero  di  sviluppatori  coinvolti,  il  numero  di  task  richiesti  e  l'elenco  degli 
     * sviluppatori associatti a ciascun task 
     * 
     * Output:  Nome_Progetto:  Numero_Sviluppatori_Coinvolti,  Numero_Task_Richiesti, [Nome_Sviluppatore1, Nome_Task1], [Nome_Sviluppatore2, Nome_Task2], ... 
     * 
     * Descrizione: Questo task fornisce un report dettagliato sui progetti, mostrando il numero di 
     * sviluppatori  coinvolti,  il  numero  di  task  richiesti  e  l'elenco  degli  sviluppatori  associati  a ciascun task. 
     * 
     * Per ogni progetto, il report include: 
     * Il nome del progetto. 
     * Il numero totale di sviluppatori coinvolti nel progetto. 
     * Il numero totale di task richiesti per il progetto. 
     * Per ciascun task del progetto, viene mostrato il nome dello sviluppatore associato e il nome del task. 
     * Se più sviluppatori sono associatti allo stesso task, vengono elencati insieme.
     * 
     */

    public static void task1_report_progetto(){
    
        System.out.println("REPORT_PROGETTO\n\n");
        //String file_path = "file_dati/traccia_b.txt";
        //fileDataReader.leggiPianificazione(file_path);
        //Si prendono gli elementi dall'elemento fileDataReader per poi lavorarci su 
        ArrayList<Progetto> progetti = fileDataReader.getListaProgetti(); 
        ArrayList<Sviluppatore> sviluppatori = fileDataReader.getListaSviluppatori();
        ArrayList<Task> tasks = fileDataReader.getListaTask();

        
        
        for (Progetto progetto : progetti) {

            System.out.println(progetto.getNome()+ ": ");
            
            int numero_task_progetto = 0;
            int numero_sviluppatori_progetto = 0;

            //Trova il numero di sviluppatori associati al singolo progetto
            for(Sviluppatore sviluppatore : sviluppatori){

                //Flag che serve se si trova un match tra sviluppatore, task e progetto
                boolean flag = false; 
                
                for (String sviluppatore_task: sviluppatore.getTask_assegnati()) {
                    
                    for (Task task : tasks) {

                        for (String task_progetto : task.getProgetti_associati()) {
                            
                            if ((sviluppatore_task.equals(task.getNome()))&&(task_progetto.equals(progetto.getNome()))) {
                                
                                flag = true;
                            }    
                        
                        }
                         
                    }
                      
                }

                if(flag == true){
                    numero_sviluppatori_progetto++;
                }
                
                
            }

            System.out.println("Numero sviluppatori per progetto: " + numero_sviluppatori_progetto);

            //Trova il numero di tasks associati al progetto 
            
            for (Task task : tasks) {
                for (String task_progetto : task.getProgetti_associati()){
                    if (task_progetto.equals(progetto.getNome())) {
                        numero_task_progetto++;
                    }
                }
                }

            
            
            System.out.println("Numero task per progetto: " + numero_task_progetto);

            //Trova e stampa a video l'elenco tra task e programmatore

            for(Sviluppatore sviluppatore : sviluppatori){
 
                
                for (String sviluppatore_task: sviluppatore.getTask_assegnati()) {
                    
                    for (Task task : tasks) {

                        for (String task_progetto : task.getProgetti_associati()) {
                            
                            if ((sviluppatore_task.equals(task.getNome()))&&(task_progetto.equals(progetto.getNome()))) {
                                
                                System.out.print(sviluppatore.getNome() + " --> ");
                                System.out.println(task.getNome());
                                
                            }    
                        
                        }
                         
                    }
                      
                }

               
                
                
            }

            System.out.println("\n\n");
            
        }


            
    } 
    /**
     * Task 2: 
     * Analisi delle Deadline dei Progetti  
     * 
     * Obiettivo: Analizzare le date di inizio e scadenza dei progetti e dei task per individuare il 
     * primo e l'ultimo task di ciascun progetto. 
     * 
     * Output: Nome_Progetto: Nome_Primo_Task, Nome_Ultimo_Task
     * 
     * Descrizione:  Questo  task  analizza  le  date  di  inizio  e  scadenza  dei  progetti  e  dei  task  per 
     * individuare il primo e l'ultimo task di ciascun progetto. 
     * 
     * Per ogni progetto, vengono identificati il nome del primo task (quello con la data di inizio più anticipata) 
     * e il nome dell'ultimo task (quello  con  la  data  di  scadenza  più  tardiva).  
     * Se  sono  presenti  Task  paralleli, creare  un ArrayList. 
     * 
     */            

    public static void task2_analisi_delle_deadline_dei_progetti(){
        
        System.out.println("ANALISI DELLE DEADLINE DEI PROGETTI\n\n");

        //Si prendono gli elementi dall'elemento fileDataReader per poi lavorarci su 
        ArrayList<Progetto> progetti = fileDataReader.getListaProgetti(); 
        ArrayList<Task> tasks = fileDataReader.getListaTask();


        for (Progetto progetto : progetti) {

            System.out.println(progetto.getNome() + ": ");

            //Inizializzano due date "fantocce", per poi essere rimpiazzate sotto
            LocalDate primo_task_data = LocalDate.of(0, 1, 1);
            LocalDate ultimo_task_data = LocalDate.of(0, 1, 1);

            //Trovare le date

            for (Task task : tasks) {

                if (task.getProgetti_associati().contains(progetto.getNome())) {

                    if ((task.getData_di_inizio().isBefore(primo_task_data))||(primo_task_data.equals(LocalDate.of(0, 1, 1)))) {

                        primo_task_data = task.getData_di_inizio();

                    }
                    if (task.getData_di_scadenza().isAfter(ultimo_task_data)||(primo_task_data.equals(LocalDate.of(0, 1, 1)))) {

                        ultimo_task_data = task.getData_di_scadenza();

                    } 

                } 
                    
                
            }


            //Trovare i nomi dei task  
            ArrayList<String> nomi_primi_task = new ArrayList<>();
            ArrayList<String> nomi_ultimi_task = new ArrayList<>();
            
            for (Task task : tasks) {
                if ((task.getProgetti_associati().contains(progetto.getNome()))&&(task.getData_di_inizio().equals(primo_task_data))) {
                    nomi_primi_task.add(task.getNome());
                }
                if ((task.getProgetti_associati().contains(progetto.getNome()))&&(task.getData_di_scadenza().equals(ultimo_task_data))) {
                    nomi_ultimi_task.add(task.getNome());
                }
            }

            if ((primo_task_data.equals(LocalDate.of(0, 1, 1)))&&(ultimo_task_data.equals(LocalDate.of(0, 1, 1)))) {
                System.out.println("Non ci sono task associati al progetto");

            } else {
            
            System.out.println("Nomi primi task: " + nomi_primi_task);   
            System.out.println("Data primi task: " + primo_task_data);

            System.out.println("Nomi ultimi task: " + nomi_ultimi_task);
            System.out.println("Ultimo task: " + ultimo_task_data);

            }

            System.out.println("\n");
        }

            

    }

    /**
     * Task 3: Monitoraggio dello Stato dei Task  
     * 
     * Obiettivo: Identificare i progetti attualmente in corso e tenere traccia dello stato di ciascun  
     * task all'interno di questo progetto.  
     * 
     * Output: Nome_Progetto_InCorso: [Nome_Task, Stato_Task] 
     * 
     * Descrizione:  Identificare  i  progetti  attualmente  in  corso  analizzando  lo  stato  di  ciascun progetto. 
     * 
     * Per ogni progetto in corso, individuare tutti i task associati e tenere traccia dello stato di ciascun task. 
     * Generare un output che mostri il nome del progetto in corso seguito da un elenco di task associato a tale progetto, 
     * insieme al loro stato attuale.
     * 
     */
         
    public static void task3_monitoraggio_dello_stato_dei_task(){
        
        //Si prendono gli elementi dall'elemento fileDataReader per poi lavorarci su 
        ArrayList<Progetto> progetti = fileDataReader.getListaProgetti(); 
        ArrayList<Task> tasks = fileDataReader.getListaTask();

        System.out.println("MONITORAGGIO DELLO STATO DEI TASK\n\n");
        
        for (Progetto progetto : progetti) {
            
            
            //Match tra progetto in corso e task associati al progetto in corso, 
            //per poi stampare a video il nome del progetto e il nome dei task associati al progetto in corso

            if (progetto.getStato().equals("InCorso")) {
                System.out.println(progetto.getNome());
                for (Task task : tasks) {
                    if (task.getProgetti_associati().contains(progetto.getNome())) {
                        System.out.println(task.getNome() + ": " + task.getStato());
                    }
                }

            } 
        System.out.println("\n");
        }
    }
    
    
    public static void main(String[] args) {
        
        /**
         * Requisiti Aggiuntivi: Il sistema dovrà gestire situazioni impreviste come modifiche dei requisiti del progetto, 
         * ritardi nello sviluppo e risorse umane limitate. 
         * Si richiede un'attenzione particolare alla progettazione di un codice robusto e flessibile, 
         * in grado di gestire efficacemente queste eventualità
         * 
         * Cambiare la stringa file_path se il file path e/o il nome del file da leggere sono diversi 
         * 
         */
        String file_path = "file_dati/traccia_B.txt"; 
        
        //Caricare gli elementi dal file all'elemtno fileDataReader
        riempiFileDataReader_da_file(file_path);
        
        //Esegue i task richiesti nella consegna della traccia_b
        task1_report_progetto();
        task2_analisi_delle_deadline_dei_progetti(); 
        task3_monitoraggio_dello_stato_dei_task(); 
        
        
    }
}

