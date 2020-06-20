package it.polito.tdp.bar.model;

public class Statistiche {

	private int numClientiTot;
	private int numClientiSoddisfatti;
	private int numClientiInsoddisfatti;
	/**
	 * @param numClientiTot
	 * @param numClientiSoddisfatti
	 * @param numClientiInsoddisfatti
	 */
	public Statistiche(int numClientiTot, int numClientiSoddisfatti, int numClientiInsoddisfatti) {
		super();
		this.numClientiTot = 0;
		this.numClientiSoddisfatti = 0;
		this.numClientiInsoddisfatti = 0;
	}
	
	
	public Statistiche() {
			}


	public void addNumClientiTot(int numClientiTot) {
		this.numClientiTot += numClientiTot;
	}
	public int getNumClientiSoddisfatti() {
		return numClientiSoddisfatti;
	}
	public void addNumClientiSoddisfatti(int numClientiSoddisfatti) {
		this.numClientiSoddisfatti += numClientiSoddisfatti;
	}
	public int getNumClientiInsoddisfatti() {
		return numClientiInsoddisfatti;
	}
	public void addNumClientiInsoddisfatti(int numClientiInsoddisfatti) {
		this.numClientiInsoddisfatti += numClientiInsoddisfatti;
	}
	
	
	
}
