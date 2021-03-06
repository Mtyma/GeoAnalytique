package geoanalytique.model;

import geoanalytique.util.GeoObjectVisitor;
import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.exception.VisiteurException;
 
/**
 * Classe représentant les points
 *
 */
// TODO: compléter les commentaires javadoc, pour toutes les fonctions publiques
public class Point extends GeoObject {
    private double x;
    private double y;
    
    // En informatique il est difficile de savoir si deux flottants sont
    // egaux à cause des imprecisions dans les calculs du co-processeur. 
    // Donc pour simplifier nous disons qu'ils sont egaux s'il existe un 
    // delta minuscule (epsilon) entre deux flottants.
    // la valeur choisis a ete prise au hasard
    public static final double DELTA_PRECISION = 0.3;
    
    public Point(double x, double y,GeoAnalytiqueControleur controleur) {
        super(controleur);
        this.x = x;
        this.y = y;
    }
    
    public Point(String name, double x, double y,GeoAnalytiqueControleur controleur) {
        super(name,controleur);
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setY(double y) {
        this.y = y;
        modifie();
    }
    
    public void setX(double x) {
        this.x = x;
        modifie();
    }
    
    public double calculPente(Point a) {
        // TODO: a completer
    	return ( this.max(this.y, a.getY()) - this.min(this.y, a.getY()) )/( this.max(this.x, a.getX()) - this.min(this.x, a.getX()) );
        //return 0.0;
    }
    
    public boolean equals(Point o) {
        // TODO: a completer
    	boolean t=false;
    	if(this.x- o.getX()<=DELTA_PRECISION && this.x-o.getX()>=-1*DELTA_PRECISION)
    		if(this.y-o.getY()<=DELTA_PRECISION && this.y-o.getY()>=-1*DELTA_PRECISION)
    			t=true;
        return t;
    }
   
    public double calculerDistance(Point b) {
        // TODO: a completer
    	return Math.sqrt((x-b.x)*(x-b.x)+(y-b.y)*(y-b.y));
       // return 0.0;
    }
    
    public void deplacer(double dx, double dy) {
        // TODO: a completer
    	this.x+=dx;
    	this.y+=dy;
    }

	@Override
	public <T> T visitor(GeoObjectVisitor<T> obj) throws VisiteurException {
		return obj.visitPoint(this);
	}

	@Override
	public boolean contient(Point p) {
		return equals(p);
	}
	
	private double max(double a,double b){
		return (a>=b)?a:b;
	}
	private double min(double a,double b){
		return (a<=b)?a:b;
	}
}