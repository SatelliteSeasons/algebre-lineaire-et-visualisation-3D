/**
 * Matrice
 *
 * @author Richard Ho & Théo Phan
 * @version 27/10/2023
 */
public class Matrice
{
    private double[][] aMatrice;
    private int aM; //nb lignes
    private int aN; //nb colonnes
    
    public Matrice( final int pM, final int pN ){
        this.aMatrice = new double[pM][pN];
        this.aM = pM;
        this.aN = pN;
    }
    
    public double getCoefficient( final int pI, final int pJ ){
        return this.aMatrice[pI][pJ]; // pI et pJ sont des indices
    }
    
    public int getNbColonnes(){
        return this.aN;
    }
    
    public int getNbLignes(){
        return this.aM;
    }
    
    public void setCoefficient( final int pI, final int pJ, final double pCoefficient ){
        this.aMatrice[pI][pJ] = pCoefficient;
    }
    
    public Vecteur getVecteurLigne( final int pI ){
        Vecteur vVect = new Vecteur( this.aN );
        for (int i=0; i<this.aN; i++){
            vVect.setCoordonnee( i, this.aMatrice[pI][i] );
        }
        return vVect;
    }
    
    public Vecteur multiplicationVectorielle( final Vecteur pVect ){
        Vecteur vVect;
        Vecteur vVectMulti = new Vecteur( this.aM );
        for(int i=0; i<this.aM; i++){
            vVect = this.getVecteurLigne( i );
            vVectMulti.setCoordonnee( i, pVect.produitScalaire( vVect ) );
        }
        return vVectMulti;
    }
    
    public void setIdentite(){
        for (int i=0; i<this.aM; i++){
            for (int j=0; j<this.aN; j++){
                if ( i==j ){
                    this.setCoefficient( i, j, 1.0 );
                }
                else{
                    this.setCoefficient( i, j, 0.0 );
                }
            }
        }
    }
    
    public void setHomothetie( final double pK ){
        for (int i=0; i<this.aM; i++){
            for (int j=0; j<this.aN; j++){
                if ( i==j ){
                    this.setCoefficient( i, j, pK );
                }
                else{
                    this.setCoefficient( i, j, 0.0 );
                }
            }
        }
    }
    
    public void setSymetrieCentrale(){
        for (int i=0; i<this.aM; i++){
            for (int j=0; j<this.aN; j++){
                if ( i==j ){
                    this.setCoefficient( i, j, -1.0 );
                }
                else{
                    this.setCoefficient( i, j, 0.0 );
                }
            }
        }
    }
    
    public void setReflexionOx(){ //par rapport à l'axe des x
        this.setIdentite();
        this.setCoefficient( 1, 1, -1.0 );
    }
    
    public void setReflexionOxOy(){ //par rapport au plan xOy
        this.setIdentite();
        this.setCoefficient( 2, 2, -1.0 );
    }
    
    public void setRotation2d( final double pAlpha ){
        this.setCoefficient( 0, 0, Math.cos(pAlpha) );
        this.setCoefficient( 0, 1, -Math.sin(pAlpha) );
        this.setCoefficient( 1, 0, Math.sin(pAlpha) );
        this.setCoefficient( 1, 1, Math.cos(pAlpha) );
    }
    
    public void setRotation3dOx( final double pAlpha ){ //autour de l'axe des x
        this.setIdentite();
        this.setCoefficient( 1, 1, Math.cos(pAlpha) );
        this.setCoefficient( 1, 2, -Math.sin(pAlpha) );
        this.setCoefficient( 2, 1, Math.sin(pAlpha) );
        this.setCoefficient( 2, 2, Math.cos(pAlpha) );
    }
    
    public void setRotation3dOy( final double pAlpha ){ //autour de l'axe des x
        this.setIdentite();
        this.setCoefficient( 0, 0, Math.cos(pAlpha) );
        this.setCoefficient( 2, 0, -Math.sin(pAlpha) );
        this.setCoefficient( 0, 2, Math.sin(pAlpha) );
        this.setCoefficient( 2, 2, Math.cos(pAlpha) );
    }
    
    public void setRotation3dOz( final double pAlpha ){ //autour de l'axe des x
        this.setIdentite();
        this.setCoefficient( 0, 0, Math.cos(pAlpha) );
        this.setCoefficient( 0, 1, -Math.sin(pAlpha) );
        this.setCoefficient( 1, 0, Math.sin(pAlpha) );
        this.setCoefficient( 1, 1, Math.cos(pAlpha) );
    }
    
    public void setRotation3dOxOyOz(final double alpha1, final double alpha2, final double alpha3){
        this.setIdentite();
        this.setCoefficient( 1, 1, Math.cos(alpha1) );
        this.setCoefficient( 1, 2, -Math.sin(alpha1) );
        this.setCoefficient( 2, 1, Math.sin(alpha1) );
        this.setCoefficient( 2, 2, Math.cos(alpha1) );
        this.setCoefficient( 0, 0, Math.cos(alpha2) );
        this.setCoefficient( 2, 0, -Math.sin(alpha2) );
        this.setCoefficient( 0, 2, Math.sin(alpha2) );
        this.setCoefficient( 2, 2, Math.cos(alpha2) );
        this.setCoefficient( 0, 0, Math.cos(alpha3) );
        this.setCoefficient( 0, 1, -Math.sin(alpha3) );
        this.setCoefficient( 1, 0, Math.sin(alpha3) );
        this.setCoefficient( 1, 1, Math.cos(alpha3) );
    }
    
    public static void tracerDroite( final Vecteur pU ){
        Plan vPlan = new Plan();
        Vecteur vVect = new Vecteur(2);
        
        int vTaille = 2; //Peut être modifié
        pU.multiplicationScalaire(vTaille);
        double vDiviseur = vTaille*0.001; //Peut être modifié
        
        vVect.setCoordonnee( 0, -pU.getX() );
        vVect.setCoordonnee( 1, -pU.getY() );
        
        Vecteur pasVect = new Vecteur(2);
        pasVect.setCoordonnee(0, Math.abs(vDiviseur*vVect.getX()) );
        pasVect.setCoordonnee(1, Math.abs(vDiviseur*vVect.getY()) );
        
        double vMaxX = Math.abs(pU.getX());
        double vMaxY = Math.abs(pU.getY());
        
        do{
            vPlan.dessinerPointEn2d(vVect);
            vVect.sommeVectorielle( pasVect );
        }
        while (Math.abs(vVect.getX()) <vMaxX && Math.abs(vVect.getY()) <vMaxY);  
    }
    
    public static void tracerCercle( final double pRayon ){
        Plan vPlan = new Plan();
        Vecteur vVect = new Vecteur(2);
        vVect.setCoordonnee(0, pRayon);
        vVect.setCoordonnee(1, 0);
        
        int vTaille = 150; //Peut être modifié
        double vDiviseur = 2*Math.PI/vTaille; //Peut être modifié
        
        Matrice vMat = new Matrice(2,2);
        vMat.setRotation2d( vDiviseur );

        int i=0;
        
        vPlan.dessinerPointEn2d(vVect);
        
        Vecteur pasVect = vMat.multiplicationVectorielle(vVect);
        
        vPlan.dessinerPointEn2d(pasVect);
        pasVect = vMat.multiplicationVectorielle(pasVect);
        while( vTaille != 0 ){
            vPlan.dessinerPointEn2d(pasVect);
            pasVect = vMat.multiplicationVectorielle(pasVect);
            vTaille -= 1; //Peut être modifié, mais dépend de la modification de vTaille
        }
    }
    
    /*
     * 2.8 Lorsqu'on invoque getCoefficient(3,4) sur les deux matrices, on a une erreur du type java.lang.ArrayIndexOutOfBoundsException,
     * ce qui est normal car les paramètres sont les indices de la matrice et les matrices vont de l'indice 0 jusqu'à m exclus et n exclus
     */
    
    /*
     * oui
     */
    public Vecteur getVecteurColonne(final int pIndice){
        int vDimension = this.getNbLignes();
        Vecteur vVecteur = new Vecteur(vDimension);
        for(int i=0; i< vDimension; i++){
            vVecteur.setCoordonnee(i, this.getCoefficient(i,pIndice));
        }
        return vVecteur;
    }
    
    /*
    public Matrice produitMatriciel(final Matrice pMat){
        int vNbColonneA = this.getNbColonnes();
        int vNbLigneP = pMat.getNbLignes();
        if(vNbColonneA == vNbLigneP){
            Matrice vMatrice = new Matrice( vNbLigneP, vNbColonneA);
            for(int i=0; i < vNbLigneP; i++){
                Vecteur vVectP = new Vecteur(pMat.getVecteurLigne(i) );
                for(int j=0; j < vNbColonneA; j++){
                    Vecteur vVectA = new Vecteur( this.getVecteurColonne(j) );
                    vMatrice.setCoefficient(i, j, vVectP.produitScalaire(vVectA) );
                }
            }
            return vMatrice;
        }else{
            return null;
        }
        
    }
    */
   
    public Matrice produitMatriciel(final Matrice pMat){
        int vNbColonneA = this.getNbColonnes();
        int vNbLigneP = pMat.getNbLignes();
        if(vNbColonneA == vNbLigneP){
            int vNbLigneA = this.getNbLignes();
            int vNbColonneP = pMat.getNbColonnes();
            Matrice vMatrice = new Matrice( vNbLigneA, vNbColonneP );
            for(int i=0; i<vNbLigneA  ;i++){
                Vecteur vVectA = this.getVecteurLigne(i);
                for(int j=0; j<vNbColonneP;j++){
                    Vecteur vVectP = pMat.getVecteurColonne(j);
                    vMatrice.setCoefficient(i , j, vVectA.produitScalaire(vVectP) );
                }
            }
            return vMatrice;
        }else{
            return null;
        }
        
    }
    
    
    
}
