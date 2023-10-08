
import java.util.Arrays;

/**
 * Vecteur
 *
 * @author Richard Ho & Theo Phan 
 * @version 06/10/2023
 */
public class Vecteur
{
    private double[] aN;
    
    public Vecteur( final int pN ){
        this.aN = new double[pN];
        Arrays.fill(this.aN, 0);
    }
    
    public Vecteur( final Vecteur pVecteur ){
        Arrays.copyOf(pVecteur.aN, pVecteur.aN.length);
        //Si avec getter, necessite boucle
    }
    
    public int getDimension(){
        return this.aN.length;
    }
    
    public double getCoordonnee( final int pI ){
        return this.aN[pI];
    }
    
    public void setCoordonnee( final int pI, final double pCoordonnee){
        this.aN[pI] = pCoordonnee;
    }
    
    public double getX(){
        return this.aN[0];
    }
    
    public double getY(){
        return this.aN[1];
    }
    
    public void multiplicationScalaire( final double pScalaire ){
        for( int i = 0; i<this.aN.length; i++){
            this.aN[i] *= pScalaire;
        }
    }
    
    public double norme(){
        double vLocal = 0.0;
        for(int i = 0; i<this.aN.length; i++){
            vLocal += this.getCoordonnee(i) * this.getCoordonnee(i);
        }
        return Math.sqrt(vLocal);
    }
    
    /*
     * Procedure ne marchant pas lorsque la norme du vecteur est egal a 0
     */
    public void normaliser(){
        this.multiplicationScalaire( (1.0/this.norme()) );
    }
    
    /*
     * Necessite un autre vecteur de meme dimension que l'instance.
     */
    public void sommeVectorielle( final Vecteur pVect ){
        if( this.getDimension() == pVect.getDimension() ){
            for(int i=0; i<this.aN.length; i++){
                this.aN[i] += pVect.getCoordonnee(i);
            }
        }else{
            System.out.print("Dimensions differentes entre les deux vecteurs.");
        }
    }
    
    public double produitScalaire( final Vecteur pVect ){
        double vLocal = 0.0;
        if( this.getDimension() == pVect.getDimension() ){
            for(int i=0; i<this.aN.length; i++){
                vLocal += this.aN[i]*pVect.getCoordonnee(i);
            }
        }else{
            System.out.print("Dimensions differentes entre les deux vecteurs.");
        }
        return vLocal;
    }
    
    public Vecteur produitVectoriel3d( final Vecteur pVect ){
        if( this.getDimension() == 3 && pVect.getDimension() == 3 ){
            Vecteur vVect = new Vecteur(3);
            vVect.setCoordonnee( 0,this.getCoordonnee(1)*pVect.getCoordonnee(2) - pVect.getCoordonnee(1)*this.getCoordonnee(2) );
            vVect.setCoordonnee( 1,this.getCoordonnee(2)*pVect.getCoordonnee(0) - pVect.getCoordonnee(2)*this.getCoordonnee(0) );
            vVect.setCoordonnee( 2,this.getCoordonnee(0)*pVect.getCoordonnee(1) - pVect.getCoordonnee(0)*this.getCoordonnee(1) );
            return vVect;
        }else{
            return null;
        }
    }
    
    
    public boolean estOrthogonal( final Vecteur pVect ){
        double vLocal = this.produitScalaire(pVect);
        if( approche( 0.0, vLocal, 1E-9, 1E-5 ) ){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean estColineaire( final Vecteur pVect ){
        if( this.getDimension() == pVect.getDimension() ){
            int vDim = this.getDimension();
            for(int i = 0; i<vDim; i++){
                for(int j=0; j<vDim; j++){
                    if( (j>i) && !( approche( this.getCoordonnee(i)*pVect.getCoordonnee(j), this.getCoordonnee(j)*pVect.getCoordonnee(i), 1E-9,  1E-5 )) ){
                        return false;
                    }
                }
            }
            return true;
        }else{
            System.out.print("Pas meme dimension");
            return false;
        }
    }
    
    public boolean estCoplanaire3d( final Vecteur pVect1, final Vecteur pVect2){
        double vLocal = this.produitScalaire( pVect1.produitVectoriel3d(pVect2) );
        return approche( 0.0, vLocal, 1E-9, 1E-5 );
    }
    
    public static boolean approche( final double pA, final double pB, final double pEpsilon_rel, final double pEpsilon_abs )
    {
        return (Math.abs(pA-pB) <= Math.max( Math.max(Math.abs(pA), Math.abs(pB) ) * pEpsilon_rel, pEpsilon_abs) );
    }
    
    /*
         2.1  En utilisant uniquement les types de donnes
         vus dans cette atelier jusqua present, est-il possible de declarer les attributs correspondant a un vecteur en dimension arbitraire ?
         - Oui il est possible de prendre en parametre n dimension, puis d'initialiser un tableau de n dimension sur Java dans le constructeur.
     */
    
    /*
     *  2.8 Lorsuqu'on invoque getCoordonne(4), on obtient un message d'erreur de type "ArrayIndexOutOfBoundsException".
     *  Ce qui est normal, le deuxieme vecteur est un tableau de 4 elements donc les indices sont de 0 a 3, notre methode affiche
     *  ainsi, l'indice du tableau et non la "4e" valeur. Meme explication pour le premier vecteur.
     */
    
    /*
     *  3.2 Il est normal de constater que le vecteur (0 ; 1.5 ; 0) est confondu avec le vecteur (0; 1.5; 2) car nous avons un plan en 2D,
     *  ce qui signifie que ce plan ne peux afficher que les deux premieres coordonnees d'un vecteur. Et ici, on observe que les deux coordonnees des deux vecteur
     *  sont identiques.
     */
    
    /*
     *  7.2 Lorsque qu'on fait une somme entre deux vecteur de dimension different, nous avons la decision d'afficher
     *  un message d'erreur.
     */
    
    /*
     * Meme raisonnement que question precedente.
     */
}
