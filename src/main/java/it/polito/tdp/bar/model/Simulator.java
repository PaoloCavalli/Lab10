package it.polito.tdp.bar.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	//Modello del mondo
	private List<Tavolo> tavoli;
	
	//parametri di simulazione
	private final int NUM_EVENTI = 2000;
	private int T_MIN_ARRIVO_MAX = 10;
	private int NUM_MAX_PERSONE = 10;
	private int DURATA_MIN = 60;
	private int DURATA_MAX = 120;
	private double TOLLERANZA_MAX = 0.0;
	private double OCCUPAZIONE_MIN = 0.5;
	
	//valori calcolati
	private Statistiche stat;
	
	//coda degli eventi
	private PriorityQueue<Event> queue;
	
	
	
	public void caricaTavoli() {
	
		this.tavoli= new ArrayList<Tavolo>();
		
		aggiungiTavolo (2,10);
		aggiungiTavolo (4, 8);
		aggiungiTavolo (4, 6);
		aggiungiTavolo (5, 4);
		
		Collections.sort(this.tavoli, new Comparator<Tavolo>() {
			@Override
			public int compare(Tavolo o1, Tavolo o2) {
				return o1.getnPosti()-o2.getnPosti();
			}
		});
		
		
	}
	
    public void caricaEventi() {
	 Duration arrivo = Duration.ofMinutes(0);
	
	 for(int i = 0; i<this.NUM_EVENTI; i++) {
		int numPersone = (int) (Math.random()*this.NUM_MAX_PERSONE+1);
		Duration durata = Duration.ofMinutes(this.DURATA_MIN + (int)Math.random()*(this.DURATA_MAX-this.DURATA_MIN));
		double tolleranza = Math.random()*this.TOLLERANZA_MAX;
		Event e = new Event(arrivo, EventType.ARRIVO_GRUPPO_CLIENTI, numPersone, durata, tolleranza, null);
	    
		this.queue.add(e);
		arrivo = arrivo.plusMinutes(1 + (int)(Math.random()*this.T_MIN_ARRIVO_MAX));
	}
  }
    
	private void aggiungiTavolo(int num, int nPosti) {
		for(int i =0; i<num; i++) {
			Tavolo t = new Tavolo (nPosti, false);
			this.tavoli.add(t);
			
		}
      }
    

    public void init() {
    	caricaTavoli();
    	this.queue = new PriorityQueue<Event>();
    	caricaEventi();
    	this.stat = new Statistiche();
    	
    }

    public void run() {
    	while (!queue.isEmpty()) {
    		Event e = queue.poll();
    		System.out.println(e);
    		processEvent(e);
    		
    	}
    }
    
    public void processEvent(Event e) {
    	switch(e.getType()) {
    	
    	case ARRIVO_GRUPPO_CLIENTI:
    	stat.addNumClientiTot(e.getNumPersone());
    	//cerca un tavolo
    	Tavolo trovato = null;
    	for(Tavolo t : this.tavoli) {
    		if(!t.isOccupato() && t.getnPosti() >= e.getNumPersone() && t.getnPosti()*this.OCCUPAZIONE_MIN <= e.getNumPersone()) {
    			trovato = t;
    			break;
    		}
    	}
    		
    	if(trovato != null) {
    		System.out.format("Sedute %d persone al tavolo da %d\n ", e.getNumPersone(), trovato.getnPosti());
            stat.addNumClientiSoddisfatti(e.getNumPersone());
            trovato.setOccupato(true);
            
            //un nuovo evento sarà generato
            
            queue.add(new Event(e.getTime().plus(e.getDurata()), EventType.TAVOLO_LIBERATO, e.getNumPersone(), e.getDurata(), e.getTolleranza(), trovato));
    	} else {
    		double bancone = Math.random();
    		
    		if(bancone <= e.getTolleranza()){
    		// Si, le persone accettano di andare al bancone
    		stat.addNumClientiSoddisfatti(e.getNumPersone());
    	}else {
    		//Le persone se ne vanno a casa 
    		stat.addNumClientiInsoddisfatti(e.getNumPersone());
    	}
    	 }
    	break;
    	
    	case TAVOLO_LIBERATO:
    		e.getTavolo().setOccupato(false);
    		
    		break;
    		
    	}
    }
    public Statistiche getStat() {
    	return stat;
    }
}