//控制整个游戏，判断游戏是否结束
package karmaka;
import java.util.*;


//partie类为单例模式singeton
public class Partie {
		// attributs d'une Partie
		private ArrayList<Joueur> listJ;
		private JeuCartes jeu;
		private boolean isover;
		
		
		//创建 SingleObject 的一个对象
		private static Partie instance = new Partie();
		
		
		// constructeur de Partie
		//让构造函数为 private，这样该类就不会被实例化
		private Partie(){
			// instanciez la liste de joueurs : listJ
			ArrayList<Joueur> listJ = new ArrayList<Joueur>();
			// instanciez le jeu de cartes : jeu
			//début du jeu
			isover=false;
		}
		
		//提供获得实例的方法
		public static Partie getInstance(){
		      return instance;
		   }

		// Entrer les noms des deux joueurs
		//可修改：Maintenant, il n’y a que 2 joueurs. Si vous avez plus de joueurs, vous pouvez remplacer cette étape par une boucle
		public void ajouterUnJoueur(){
			Scanner scanner = new Scanner(System.in);
			System.out.println("Le nom du premier joueur est:");
			String nom1=scanner.nextLine();
			Joueur jou1=new Joueur(nom1);
			listJ.add(jou1);
			System.out.println("Le nom du deuxième joueur est:");
			String nom2=scanner.nextLine();
			Joueur jou2=new Joueur(nom2);
			listJ.add(jou2);
			scanner.close();
			System.out.println(listJ);
		}

	    // On mélange le jeu puis on on distribue les cartes aux joueurs（洗牌+发牌） 
		public void distribuerCartes(){
			jeu.melanger();  
			int tour=0;
			while (jeu.estVide() == false){
				Iterator<Joueur> it =listJ.iterator();
				//总觉得这边的*2不是很好，但是不知道怎么修改
				while(it.hasNext()&&tour<6*2){
					//on sort une carte du jeu获取并移除牌堆中的第一张牌
					Carte premiere=jeu.distribuerUneCarte(); 
					//le joueur pointé par l'itérateur ramasse la carte被迭代器指向的玩家将这张牌拿走并放入手牌中
					Joueur pointe=it.next();
					if(pointe.getNumberMain()<4) {
						pointe.ramasserCarteMain(premiere);
					}else if(pointe.getNumberPile()<2) {
						pointe.ramasserCartePile(premiere);
					}
					tour++;
				}
			}		
		}
		
		
		public static void main(String[] args) {
			Partie partie = Partie.getInstance();
			partie.distribuerCartes();
			partie.ajouterUnJoueur();
		
			
		}
		
		/*
		// Chaque joueur jette une et une seule carte sur la table.
		// Les cartes jouées se retrouvent dans la liste cartesJouees
		// On ne teste pas le cas où les joueurs jettent une carte de même valeur.
		public ArrayList<Carte> jouerVosCartes(){
			ArrayList<Carte> cartesJouees = new ArrayList<Carte> ();
			Iterator<Joueur> it = listJ.iterator();
			while(it.hasNext()) {
				// ajoute à la liste carteJouees la carte jouée par le joueur pointé par l'itérateur.
				
			}
			return cartesJouees;
		}
		
		// Parmi les cartes jouées, laquelle est gagnante?
		public Carte carteGagnante(List<Carte> cartes) {
			Carte meilleureCarte = cartes.get(0);
			Iterator<Carte> it = cartes.iterator();
			while(it.hasNext()) {
				Carte c = it.next();
				if (c.getValeur().ordinal() > meilleureCarte.getValeur().ordinal()) {
					meilleureCarte = c;
				}
			}		
			return meilleureCarte;
		}
		
		// Quel joueur a joué la carte gagnante?
		// Par souci de simplicité, on parcourt la liste des joueurs et on prend le premier joueur qui a posé la carte gagnante
		// même si plusieurs joueurs ont posé une carte de même valeur.
		public Joueur joueurGagnant(Carte carte) {
			boolean leGagnantEst = false;
			Iterator<Joueur> it =listJ.iterator();
			Joueur gagnant = null;
			while (it.hasNext() && leGagnantEst == false) {
				gagnant=it.next();
				if ( carte.equals(gagnant.getDerniereCarteJouee()) ) 
						leGagnantEst = true;			
			}
			return gagnant;	
		}
		
		// Le gagnant ramasse toutes les cartes jouees 
	    public void recupererCartesJouees(Joueur gagantj, ArrayList<Carte> cartesJouees) {
	    	// le joueur gagnant ramasse toutes les cartes
			
	    }
	    	
		// la partie est terminée quand un vainqueur est trouvé
		public boolean partieTerminee() {
			boolean fin =false;
			Iterator<Joueur> it =listJ.iterator();
			while (it.hasNext() && fin == false) {
				Joueur jj = it.next();
				fin=jj.isWinner();
			}
			return fin;		
		}

		public String toString(){
			return listJ.toString();
		}
					
		public static void main(String[] args) {

			// création d'une partie de Bataille
			Partie bataille = new Partie();
			
			// création de deux joueurs 
			Joueur Karadoc = new Joueur("Karadoc");
			Joueur Perceval = new Joueur("Perceval");
			
			// on ajoute les 2 joueurs à la partie
			bataille.ajouterUnJoueur(Karadoc);
			bataille.ajouterUnJoueur(Perceval);
			
			// on affiche le jeu de cartes
			System.out.println(bataille.jeu);
			
			// on distribue les cartes à l'ensemble des joueurs
			bataille.distribuerCartes();

			// on affiche les 2 joueurs de cette partie
			System.out.println(bataille.listJ.get(0));
			System.out.println(bataille.listJ.get(1));
			
			// ces 2 lignes peuvent être omises: elles vont nous servir à dérouler la partie en mode pas à pas
			// vous pouvez les mettre en commentaire. si c'est le cas, mettre également  en commentaire  //saisieClavier=terminal.lireChaine();
			MaConsole terminal = new MaConsole();
			String saisieClavier = new String();
			
			while (bataille.partieTerminee()==false) {
				ArrayList<Carte> lesCartesJouees =  bataille.jouerVosCartes();
				Carte laCarteGagnante                 =  bataille.carteGagnante(lesCartesJouees);
				Joueur leGagnant                         =  bataille.joueurGagnant(laCarteGagnante);
				bataille.recupererCartesJouees(leGagnant, lesCartesJouees);		
				System.out.println(bataille);	
				saisieClavier=terminal.lireChaine();
			}		
		}
		*/
}
