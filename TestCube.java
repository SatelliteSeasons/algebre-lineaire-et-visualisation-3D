
/**
 * Write a description of class TestA here.
 * 
 * @author Richard Ho & Théo Phan
 * @version 0.1
 */
public class TestCube
{

    /**
     * Constructor for objects of class Test
     */
    private TestCube() {}
    
    /**
     * Crée les sommets d'un cube centré sur l'origine et les mets das un tableau.
     * @return Tableau de Vecteur
     */
    private static Vecteur[] creerSommets() throws Exception
    {
        Vecteur p1=new Vecteur(3);   // sommet arrière gauche bas
        p1.setCoordonnee(0,-1);
        p1.setCoordonnee(1,-1);
        p1.setCoordonnee(2,-1); 
        Vecteur p2=new Vecteur(3);   // sommet arrière gauche haut 
        p2.setCoordonnee(0,-1);
        p2.setCoordonnee(1,1);
        p2.setCoordonnee(2,-1); 
        Vecteur p3=new Vecteur(3);   // sommet arrière droite haute
        p3.setCoordonnee(0,1);
        p3.setCoordonnee(1,1);
        p3.setCoordonnee(2,-1); 
        Vecteur p4=new Vecteur(3);   //  sommet arrière droite bas
        p4.setCoordonnee(0,1);
        p4.setCoordonnee(1,-1);
        p4.setCoordonnee(2,-1); 
        Vecteur p5=new Vecteur(3);   // sommet avant gauche bas
        p5.setCoordonnee(0,-1);
        p5.setCoordonnee(1,-1);
        p5.setCoordonnee(2,1); 
        Vecteur p6=new Vecteur(3);   // sommet avant gauche haut 
        p6.setCoordonnee(0,-1); 
        p6.setCoordonnee(1,1);
        p6.setCoordonnee(2,1); 
        Vecteur p7=new Vecteur(3);   // sommet avant droite haute
        p7.setCoordonnee(0,1);
        p7.setCoordonnee(1,1);
        p7.setCoordonnee(2,1); 
        Vecteur p8=new Vecteur(3);   // sommet avant droite bas
        p8.setCoordonnee(0,1);
        p8.setCoordonnee(1,-1);
        p8.setCoordonnee(2,1); 
        
        return new Vecteur[]{p1,p2,p3,p4,p5,p6,p7,p8};
    }
    
        /**
     * Dessine sur le Plan les arêtes du cube dont les sommets sont dans le tableau de Vecteur;
     * @param pPlan : plan ou effectuer le dessin
     * @param pSommets : sommets du cube
     */
    private static void dessinerCube(final Plan pPlan, final Vecteur[] pSommets)
    {
        pPlan.effacer();
        //(p1,p2,p3,p4); // face arriere
        //(p5,p6,p7,p8); // face avant
        //(p1,p2,p6,p5); // face gauche
        //(p3,p4,p8,p7); // face droite
        //(p2,p3,p7,p6); // face haut
        //(p1,p4,p8,p5); // face bas
        
        // face arrière
        pPlan.dessinerSegmentEn2d(pSommets[0],pSommets[1]);
        pPlan.dessinerSegmentEn2d(pSommets[1],pSommets[2]);
        pPlan.dessinerSegmentEn2d(pSommets[2],pSommets[3]);
        pPlan.dessinerSegmentEn2d(pSommets[3],pSommets[0]);
        
        // face avant 
        pPlan.dessinerSegmentEn2d(pSommets[4],pSommets[5]);
        pPlan.dessinerSegmentEn2d(pSommets[5],pSommets[6]);
        pPlan.dessinerSegmentEn2d(pSommets[6],pSommets[7]);
        pPlan.dessinerSegmentEn2d(pSommets[7],pSommets[4]);
        
        // on relie la face avant à la face arrière
        pPlan.dessinerSegmentEn2d(pSommets[0],pSommets[4]);
        pPlan.dessinerSegmentEn2d(pSommets[1],pSommets[5]);
        pPlan.dessinerSegmentEn2d(pSommets[2],pSommets[6]);
        pPlan.dessinerSegmentEn2d(pSommets[3],pSommets[7]);
    }
    
     /**
     * Applique la transformation linéaire à l'ensemble des vecteurs du tableau
     * @param pVecteurs : tableau de Vecteur à transformer
     * @param pTransformation : transformation linéaire à appliquer
     */
    public static void appliquer(final Vecteur[] pVecteurs, final Matrice pTransformation) throws Exception
    {
        for(int i=0; i< pVecteurs.length; i++){
            pVecteurs[i] = pTransformation.multiplicationVectorielle( pVecteurs[i] );
        }
    }
        
    /**
     * Procédure de test
     */
    public static void test()  throws Exception
    {
        // On crée un objet plan
        Plan p = new Plan();
        // On crée les sommets du cube
        Vecteur [] cube= creerSommets();
        
        //Matrice vMat = new Matrice( 3, 3 );
        // Rotation Ox
        //vMat.setRotation3dOz( Math.PI/4 ); <- Q3.4
        
        //Homotétie
        //vMat.setHomothetie(4); <- Q3.5
        
        //rotation autour des 3 axes <- Q3.7
        /*Matrice vMat = getRotation( Math.PI/4, Math.PI/4, Math.PI/4 );
        appliquer( cube, vMat );
        vMat.setHomothetie(3);
        appliquer( cube, vMat );
        
        // On affiche le cube
        dessinerCube( p,cube );
        */
        
        //Q.3.8
        for (int i=0; i<=3140/4; i++) //PI~3.14 => PI*100~314 et 
        //est le déplacement élémentaire est de 0.001 radian
        {
            //Pour les rotations
            Matrice vMat = getRotation( 0.001, 0.001, 0.001 );
            appliquer( cube, vMat );
            dessinerCube( p,cube );
            
            //Pour l'homothétie
            vMat.setHomothetie(1.001);
            appliquer( cube, vMat );
            dessinerCube( p,cube );
        }
        
        
    }
    
    /*
     * Cette procédure est censée d'abord créer un plan en 2D pour afficher le cube,
     * puis de créer le cube sous forme d'un tableau de Vecteur, et enfin le dessine
     * sur le plan.
     * Après exécution, on observe juste un carré centré à l'origine du repère,
     * ce qui est normal car le représentation est en 2D (et un cube vu de face 
     * est un carré en 2D).
     */
    
    /*
     * Q3.6
     */
    public static Matrice getRotation( final double alpha1, final double alpha2, final double alpha3 ){
        Matrice vMat1 = new Matrice(3,3);
        Matrice vMat2 = new Matrice(3,3);
        Matrice vMat3 = new Matrice(3,3);
        vMat1.setRotation3dOx(alpha1);
        vMat2.setRotation3dOy(alpha2);
        vMat3.setRotation3dOz(alpha3);
        Matrice vMatProd = ( vMat1.produitMatriciel( vMat2 ) ).produitMatriciel( vMat3 );
        return vMatProd;
    }
}
