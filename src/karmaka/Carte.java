package karmaka;

public class Carte {
	
    private Couleur couleur;
    private Valeur valeur;
    private int nombre;
   

    //构造函数，输入为颜色和数值
    public Carte (Valeur valeur, Couleur couleur,int nombre) {
	this.setCouleur(couleur);
	this.setValeur(valeur);
	this.setNombre(nombre);
    }

    public Couleur getCouleur() {
    	return couleur;
    }

    public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

    public Valeur getValeur() {
    	return valeur;
    }

    public void setValeur(Valeur valeur) {
	    this.valeur = valeur;
    }

    public int getNombre() {
    	return nombre;
    }

    public void setNombre(int nombre) {
	    this.nombre=nombre;
    }
    
    //暂时不知道有什么用
    //用来重写sout
    public String toString() {
	StringBuffer sb = new StringBuffer();   //sb是一个需要被修改的字符串
	sb.append(this.valeur);
	sb.append(" de ");
	sb.append(this.couleur);
	return sb.toString();  //结果为卡片的valeur “de” couleur
    }   
	
    
    public static void main(String[]args){
    	Carte transmigrate=new Carte(Valeur.un, Couleur.Bleue,3);
  
    	System.out.println(transmigrate);

    	// la méthode ordinal() renvoie la position de l'élèment dans l'énumération
    	// ici la valeur DIX renvoie 3, la valeur ROI renvoie 6 et ainsi de suite
    	System.out.println(transmigrate.getValeur() );
    	System.out.println(transmigrate.getValeur().ordinal());
    	
    	/* Comment récupérer les éléments d'une énumération?
		Valeur[] v=Valeur.values();
		for(int i=0 ; i < v.length; i++){
			System.out.println(v[i]);
		}
		*/

    }
}
