package karmaka;

import java.util.*;


public class Joueur {
	    // attributs d'un joueur
		private String nom;
		public Joueur jeu1;
		public Joueur jeu2;
		private int points;
		private Echelle echelle;
		private LinkedList<Carte> main;
		private LinkedList<Carte> pile;
		private LinkedList<Carte> oeuvres;
		private Carte derniereCarteJouee;
		
		
		// constructeur构造函数(玩家的初始化）
		public Joueur(String nom){
			// initialise le nom du joueur
		    this.nom=nom;
			// instancie la main du joueur
		    //盲猜这三行可以通过继承的方式实现
			this.main=new LinkedList<Carte>();
			this.pile=new LinkedList<Carte>();
			this.oeuvres=new LinkedList<Carte>();
			this.echelle=Echelle.Bousier;
			this.points=0;
		}
		
		// le joueur ramasse la carte et l'ajoute en dessous des cartes déjà existantes dans la main
		public void ramasserCarteMain(Carte carte){
			this.main.add(carte);
		}

		public void ramasserCartePile(Carte carte){
			this.pile.add(carte);
		}
		
		// le joueur retire la premiere carte de sa main.  
		public Carte jouerCarte(){
			derniereCarteJouee = main.getFirst();
			System.out.println(this.nom + " joue " + this.derniereCarteJouee);
			return derniereCarteJouee;
		}
		// getter de derniereCarteJouee;
		public Carte getDerniereCarteJouee() {
			return derniereCarteJouee;
		}
		
		// le joueur gagne s'il a toutes les cartes dans sa main.
		// JeuCartes.nbrCartes représente toutes les cartes
		public boolean isWinner(){
			boolean jaigagne = false;
			if (main.size()==32)
				jaigagne=true;
			return jaigagne;
		}
		
		public LinkedList<Carte> getMain(){
			return main;
		}
		
		public int getNumberMain() {
			return main.size();
		}
		
		public LinkedList<Carte> getPile(){
			return pile;
		}
		
		public int getNumberPile() {
			return pile.size();
		}
		
		public String getNom(){
			return nom;
		}
		
		// Description d'un joueur avec sa main
		public String toString(){
			StringBuffer sb = new StringBuffer();
			sb.append("\n ******************************************* \n");		
			sb.append(nom + " a  " +  main.size() + " cartes dans sa main \n");
			sb.append(main);
			sb.append("\n ******************************************* \n");
			return sb.toString();
		}
}
