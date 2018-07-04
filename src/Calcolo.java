import java.util.ArrayList;



public class Calcolo {
	static ArrayList<Integer> Problemi = new ArrayList<Integer>();
	static ArrayList<Integer> DimensioniGrafi = new ArrayList<Integer>();
	static ArrayList<Integer> Nodi = new ArrayList<Integer>();
	static ArrayList<Integer> Collegamenti = new ArrayList<Integer>();
	static ArrayList<Integer> Quantita = new ArrayList<Integer>();
	static ArrayList<Integer> grafoSP = new ArrayList<Integer>();
	static ArrayList<Integer> LinkSP = new ArrayList<Integer>();
	static ArrayList<Integer> quantitaSP = new ArrayList<Integer>();
	static int contaLink=0;
	static int dimensioneGrafo=0;
	static int puntatore=0;
	static int posizioneIniziale=0;

	public Calcolo() {
	}

	public static void scegli(String nome, String valore) {


		if(nome.equals("link"))
		{
			contaLink++;
		}
		else {
			if(contaLink!=0)
				Quantita.add(contaLink);
			contaLink=0;
		}


		switch(nome) {
		case "root":
			//System.out.println("");
			break;
		case "problem":
			//System.out.println(" id " + valore);
			Problemi.add(Integer.parseInt(valore));
			break;
		case "graph":
			//System.out.println(" size " + valore);
			DimensioniGrafi.add(Integer.parseInt(valore));
			break;
		case "link":
			//System.out.println(" to " + valore);
			Collegamenti.add(Integer.parseInt(valore));
			break;
		case "node":
			//System.out.println(" id " + valore);
			Nodi.add(Integer.parseInt(valore));
			break;
		}





	}

	public static void prova() {
		System.out.println("PROBLEMI");
		for(int puntatore: Problemi)
			System.out.println(puntatore);
		System.out.println("DIMENSIONI GRAFI");
		for(int puntatore: DimensioniGrafi)
			System.out.println(puntatore);
		System.out.println("NODI");
		for(int puntatore: Nodi)
			System.out.println(puntatore);
		System.out.println("COLLEGAMENTI");
		for(int puntatore: Collegamenti)
			System.out.println(puntatore);
		System.out.println("QUANTITA");
		for(int puntatore: Quantita)
			System.out.println(puntatore);
	}

	public static void Iterazioni() {
		for(int i=0; i<Problemi.size();i++) {
			int posizionePartenza=dimensioneGrafo;
			grafoSP.clear();
			LinkSP.clear();
			quantitaSP.clear();
			dimensioneGrafo=dimensioneGrafo + DimensioniGrafi.get(i);
			//System.out.println("il problema numero " + i + " ha i seguenti nodi:");
			for(int j=posizionePartenza;j<dimensioneGrafo;j++) {
				int puntatorePartenza = posizioneIniziale;
				posizioneIniziale= posizioneIniziale + Quantita.get(j);
				grafoSP.add(Nodi.get(j));
				quantitaSP.add(Quantita.get(j));
				//System.out.println(Nodi.get(j) + " che ha " +Quantita.get(j) + " Link, che sono " );
				for(int k=puntatorePartenza; k<posizioneIniziale;k++) {
					LinkSP.add(Collegamenti.get(k));
					//System.out.println(Collegamenti.get(k));
				}
			}

			Flooding(grafoSP,quantitaSP,LinkSP);
			//			System.out.println("Prova1");
			//			for(int puntatore: grafoSP)
			//				System.out.println(puntatore);
			//			System.out.println("Prova2");
			//			for(int puntatore: LinkSP)
			//				System.out.println(puntatore);
			//			System.out.println("Prova3");
			//			for(int puntatore: quantitaSP)
			//				System.out.println(puntatore);
		}
	}

	public static void risolviProblemaUltimoLink() {
		Quantita.add(contaLink);
	}

	public static void Flooding(ArrayList<Integer> nodi,ArrayList<Integer> quantitaLink,ArrayList<Integer> link) {
		int contaDa=0;
		int contaA=0;
		int contatore=1;
		int contatoreSecondario=1;
		ArrayList<ArrayList<Integer>> indirizzi = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<nodi.size();i++) {
			ArrayList<Integer> linkDelNodo = new ArrayList<Integer>();
			contaA=contaDa + quantitaLink.get(i);
			//			System.out.println("contaDa "+contaDa+" contaA "+contaA);
			//			System.out.println("link ");
			for(int j=contaDa;j<contaA;j++) {
				linkDelNodo.add(link.get(j));
			}
			indirizzi.add(linkDelNodo);
			contaDa = contaDa +quantitaLink.get(i);
		}
		for(int i=0;i<nodi.size();i++) {
			ArrayList<Integer> copia = new ArrayList<Integer>(nodi);
			copia.remove(i);
			ArrayList<Integer> linkDaPassare = new ArrayList<Integer>(indirizzi.get(i));
			ArrayList<Integer> linkAggiornati = new ArrayList<Integer>();
//			System.out.println("Nuovo problema "+copia);
			do {
				linkAggiornati.clear();
				for(int puntatore1 : linkDaPassare) {
					for(int k=0;k<copia.size();k++) {
						if(puntatore1==copia.get(k)) {
							copia.remove(k);
//							System.out.println("GG"+copia);
						}
					}
				}
				contatore++;
//				System.out.println("GG"+copia);
//				System.out.println("finito il primo for" + contatore);
				for(int puntatore1 : linkDaPassare) {
					for(int puntatore2:indirizzi.get(puntatore1)) {
						for(int j=0;j<copia.size();j++) {
							if(puntatore2==copia.get(j)) {
								linkAggiornati.add(puntatore2);
//								copia.remove(j);
//								System.out.println("GG"+copia + copia.isEmpty());
							}
						}
					}
				}
//				System.out.println("GG"+copia + copia.isEmpty());
//				System.out.println("finito il secondo for");
				linkDaPassare.clear();
				for(int puntatore3 : linkAggiornati) 
				{
					linkDaPassare.add(puntatore3);
				}
			}
			while(!copia.isEmpty());
			if(i==0) {
				contatoreSecondario=contatore;
			}
			if(contatoreSecondario>contatore) {
				contatoreSecondario=contatore;
			}
			contatore=1;
		}
		System.out.println("Soluzione " + contatoreSecondario);
	}


}