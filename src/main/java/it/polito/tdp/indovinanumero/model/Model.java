package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	private Set<Integer> tentativi;

	public Model() {
		this.inGioco=false;
		this.tentativiFatti=0;
		this.tentativi=new HashSet<Integer>();
	}
	
	public void nuovaPartita() {
		//gestione inizio nuova partita
    	this.segreto = (int)(Math.random()*NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco=true;
	}
	
	public int tentativo(int tentativo) {
		//controllo se partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è già terminata");
		}
		
		//controllo input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire numero tra 0 e 100");
		}
		
		//il tentativo valido, possiamo provarlo
		this.tentativiFatti++;
		
		this.tentativi.add(tentativo);
		
		if(this.tentativiFatti==TMAX) {
			this.inGioco=false;
		}
		
		if(tentativo==this.segreto) {
			this.inGioco=false;
			return 0;
		}
		
		if(tentativo<segreto) {
			return -1;
		}else {
			return 1;
		}
	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo>NMAX) {
			return false;
		}else {
			if(this.tentativi.contains(tentativo)) {
				return false;
			}
			return true;
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public void setSegreto(int segreto) {
		this.segreto = segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public void setTentativiFatti(int tentativiFatti) {
		this.tentativiFatti = tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
}
