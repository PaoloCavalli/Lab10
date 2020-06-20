package it.polito.tdp.bar.model;

import java.time.Duration;

public class Event implements Comparable<Event> {

	public enum EventType{
		ARRIVO_GRUPPO_CLIENTI, // arriva un nuovo gruppo--> andrà al tavolo OPPURE --> al bancone 
		                  //abbandona
		TAVOLO_LIBERATO, // un gruppo precedentemente in un tavolo esce e libera il tavolo
		
		
	}
	private Duration time;
	private EventType type;
	private int numPersone;
	private Duration durata;
	private double tolleranza;
	private Tavolo tavolo;
	
	public Event(Duration time, EventType type, int numPersone, Duration durata, double tolleranza, Tavolo tavolo) {
		super();
		this.time = time;
		this.type = type;
		this.numPersone = numPersone;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.tavolo = tavolo;
	}

	public Duration getTime() {
		return time;
	}

	public EventType getType() {
		return type;
	}

	public int getNumPersone() {
		return numPersone;
	}

	public Duration getDurata() {
		return durata;
	}

	public double getTolleranza() {
		return tolleranza;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	@Override
	public String toString() {
		return type  +"["+  time+ "," + numPersone + ", durata=" + durata
				+ ", tolleranza=" + tolleranza + ", tavolo=" + tavolo + "]";
	}

	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.time);
	}
	
	
	
	
}
