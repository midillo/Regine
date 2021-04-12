package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {

	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	int N;
	private List<List<Integer>> soluzioni;	
	
	public List<List<Integer>> risolvi(int N){
		this.N=N;
		List<Integer> parziale = new ArrayList<Integer>();
		this.soluzioni= new ArrayList<>();
	
		cerca(parziale, 0);
		return this.soluzioni;
	}
	
	//cerca == true : trovato; cerca == false : cerca ancora
	private void cerca(List<Integer>parziale, int livello) {
		if(livello==N) {
		//	System.out.println(parziale);
			this.soluzioni.add(new ArrayList<>(parziale));
		} else {
			for(int colonna=0; colonna<N; colonna++) {
				//if la mossa nella cartella [livello][colonna] è valida
				//se si, aggiungi a parziale e fai ricorsione
				
				if(posValida(parziale, colonna)) {
					parziale.add(colonna);
					cerca(parziale, livello+1);
					
					parziale.remove(parziale.size()-1); //backtracking
				}
			}
		}
	}
	
	private boolean posValida(List<Integer> parziale, int colonna) {
		int livello = parziale.size();
		
		//controlla se viene mangiata in verticale
		if(parziale.contains(colonna))
		return false;
		
		//controlla le diagonali: contronta la posizione (livello, colonna) con (r,c) delle regine esistenti
		for(int r=0; r<livello; r++) {
			int c = parziale.get(r);
			
			if(r+c == livello+colonna || r-c == livello-colonna) 
				return false;
		}
		return true;
	}
	
	
	
}
