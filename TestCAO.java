/**
 * Fonction de test 
 * 
 * @author Votre_Nom
 * @version 0.1
 */
public class TestCAO
{
    /**
     * Constructor for objects of class Test
     */
    private TestCAO() {}

    /**
     * CrÃ©e les sommets d'un parallÃ©lÃ©pipÃ¨de rectangle centrÃ© sur l'origine en coordonnÃ©es homogÃ¨nes et les mets das un tableau.
     * @return Tableau de Vecteur
     */
    private static Vecteur[] creerSommets() throws Exception
    {
        Vecteur p1=new Vecteur(4);   // sommet arriÃ¨re gauche bas
        p1.setCoordonnee(0,-2);
        p1.setCoordonnee(1,-1);
        p1.setCoordonnee(2,-2.5); 
        Vecteur p2=new Vecteur(4);   // sommet arriÃ¨re gauche haut 
        p2.setCoordonnee(0,-2);
        p2.setCoordonnee(1,1);
        p2.setCoordonnee(2,-2.5); 
        Vecteur p3=new Vecteur(4);   // sommet arriÃ¨re droite haute
        p3.setCoordonnee(0,2);
        p3.setCoordonnee(1,1);
        p3.setCoordonnee(2,-2.5); 
        Vecteur p4=new Vecteur(4);   //  sommet arriÃ¨re droite bas
        p4.setCoordonnee(0,2);
        p4.setCoordonnee(1,-1);
        p4.setCoordonnee(2,-2.5); 
        Vecteur p5=new Vecteur(4);   // sommet avant gauche bas
        p5.setCoordonnee(0,-2);
        p5.setCoordonnee(1,-1);
        p5.setCoordonnee(2,2.5); 
        Vecteur p6=new Vecteur(4);   // sommet avant gauche haut 
        p6.setCoordonnee(0,-2); 
        p6.setCoordonnee(1,1);
        p6.setCoordonnee(2,2.5); 
        Vecteur p7=new Vecteur(4);   // sommet avant droite haute
        p7.setCoordonnee(0,2);
        p7.setCoordonnee(1,1);
        p7.setCoordonnee(2,2.5); 
        Vecteur p8=new Vecteur(4);   // sommet avant droite bas
        p8.setCoordonnee(0,2);
        p8.setCoordonnee(1,-1);
        p8.setCoordonnee(2,2.5); 
        
        Vecteur [] para = new Vecteur[]{p1,p2,p3,p4,p5,p6,p7,p8};
        for(int i=0;i<para.length;i++)
        {
            para[i].setCoordonnee(3,1);
        }
        return para;
    }
    
    /**
     * Dessine le Parallelepipede dont les sommets sont dans le tableau de Vecteur sur le Plan
     * @param pPlan : plan ou effectuer le dessin
     * @param pSommets : sommets du Parallelepipede
     */
    private static void dessinerParallelepipede(final Plan pPlan, final Vecteur[] pSommets)
    {
        //(p1,p2,p3,p4); // face arriere
        //(p5,p6,p7,p8); // face avant
        //(p1,p2,p6,p5); // face gauche
        //(p3,p4,p8,p7); // face droite
        //(p2,p3,p7,p6); // face haut
        //(p1,p4,p8,p5); // face bas
        
        // face arriÃ¨re
        pPlan.dessinerSegmentEn2d(pSommets[0],pSommets[1]);
        pPlan.dessinerSegmentEn2d(pSommets[1],pSommets[2]);
        pPlan.dessinerSegmentEn2d(pSommets[2],pSommets[3]);
        pPlan.dessinerSegmentEn2d(pSommets[3],pSommets[0]);
        
        // face avant 
        pPlan.dessinerSegmentEn2d(pSommets[4],pSommets[5]);
        pPlan.dessinerSegmentEn2d(pSommets[5],pSommets[6]);
        pPlan.dessinerSegmentEn2d(pSommets[6],pSommets[7]);
        pPlan.dessinerSegmentEn2d(pSommets[7],pSommets[4]);
        
        // on relie la face avant Ã  la face arriÃ¨re
        pPlan.dessinerSegmentEn2d(pSommets[0],pSommets[4]);
        pPlan.dessinerSegmentEn2d(pSommets[1],pSommets[5]);
        pPlan.dessinerSegmentEn2d(pSommets[2],pSommets[6]);
        pPlan.dessinerSegmentEn2d(pSommets[3],pSommets[7]);
    }
    
    /**
     * Applique la transformation linÃ©aire Ã  l'ensemble des vecteurs du tableau 
     * et met le rÃ©sultat dans un nouveau tableau de vecteurs
     * @param pVecteurs : tableau de Vecteur Ã  transformer
     * @param pTransformation : transformation linÃ©aire Ã  appliquer
     * @return tableau de vecteurs contenant les vecteurs transformÃ©s
     */
    private static Vecteur [] getTransformee(final Vecteur[] pVecteurs, final Matrice pTransformation) throws Exception{
        Vecteur [] vVecteursRes = new Vecteur[ pVecteurs.length ];
        //
        //  A completer !
        //
        for (int i=0; i<pVecteurs.length; i++) {
            vVecteursRes[i] = new Vecteur( 4 );
            vVecteursRes[i] = pTransformation.multiplicationVectorielle( pVecteurs[i] );
        }
        return vVecteursRes;
    }
    
    /**
     * Fonction de testDessinOxy
     */
    public static void testDessinOxy() throws Exception {
        // On crÃ©e un objet plan
        Plan p = new Plan();
        // On crÃ©e les sommets du parallelepipede
        Vecteur [] vCube3d = creerSommets();
        
        // Projection sur Oxy
        Matrice vMatProj = new Matrice( 3, 4 );
        vMatProj.setProjectionOrthoOxyHomogene3d();
        
        // Translation pour le placer dans le coin inferieur gauche
        Vecteur vVect = new Vecteur( 4 );
        vVect.setCoordonnee( 0, -3.0 );
        vVect.setCoordonnee( 1, -3.0 );
        vVect.setCoordonnee( 2, 0.0 );
        vVect.setCoordonnee( 3, 1.0 );
        
        Matrice vMatTrans = new Matrice( 4, 4 );
        vMatTrans.setTranslationHomogene3d( vVect );
        
        // Matrice correspondant à projection + translation
        Matrice vMatProd = vMatProj.produitMatriciel( vMatTrans );
        vCube3d = getTransformee( vCube3d, vMatProd );
        
        // On affiche le parallelepipede dans le plan
        dessinerParallelepipede(p, vCube3d);
    }
    
    /**
     * Fonction de testDessinOxz
     */
    public static void testDessinOxz() throws Exception {
        // On crÃ©e un objet plan
        Plan p = new Plan();
        // On crÃ©e les sommets du parallelepipede
        Vecteur [] vCube3d = creerSommets();
        
        // Projection sur Oxz
        Matrice vMatProj = new Matrice( 3, 4 );
        vMatProj.setProjectionOrthoOxzHomogene3d();
        
        // Translation pour le placer dans le coin superieur gauche
        Vecteur vVect = new Vecteur( 4 );
        vVect.setCoordonnee( 0, -3.0 );
        vVect.setCoordonnee( 1, 0.0 );
        vVect.setCoordonnee( 2, 3.0 );
        vVect.setCoordonnee( 3, 1.0 );
        
        Matrice vMatTrans = new Matrice( 4, 4 );
        vMatTrans.setTranslationHomogene3d( vVect );
        
        // Matrice correspondant à projection + translation
        Matrice vMatProd = vMatProj.produitMatriciel( vMatTrans );
        vCube3d = getTransformee( vCube3d, vMatProd );
        
        // On affiche le parallelepipede dans le plan
        dessinerParallelepipede(p, vCube3d);
    }
    
    /**
     * Fonction de testDessinOxz
     */
    public static void testDessinOyz() throws Exception {
        // On crÃ©e un objet plan
        Plan p = new Plan();
        // On crÃ©e les sommets du parallelepipede
        Vecteur [] vCube3d = creerSommets();
        
        // Projection sur Oyz
        Matrice vMatProj = new Matrice( 3, 4 );
        vMatProj.setProjectionOrthoOyzHomogene3d();
        
        // Translation pour le placer dans le coin superieur droit
        Vecteur vVect = new Vecteur( 4 );
        vVect.setCoordonnee( 0, 0.0 );
        vVect.setCoordonnee( 1, 3.0 );
        vVect.setCoordonnee( 2, 3.0 );
        vVect.setCoordonnee( 3, 1.0 );
        
        Matrice vMatTrans = new Matrice( 4, 4 );
        vMatTrans.setTranslationHomogene3d( vVect );
        
        // Matrice correspondant à projection + translation
        Matrice vMatProd = vMatProj.produitMatriciel( vMatTrans );
        vCube3d = getTransformee( vCube3d, vMatProd );
        
        // On affiche le parallelepipede dans le plan
        dessinerParallelepipede(p, vCube3d);
    }
    
    /**
     * Fonction de testDessinPerspective0xy
     */
    public static void testDessinPerspective0xy() throws Exception {
        // On crÃ©e un objet plan
        Plan p = new Plan();
        // On crÃ©e les sommets du parallelepipede
        Vecteur [] vCube3d = creerSommets();
        
        // Rotation
        Matrice vMatRot = new Matrice( 4, 4 );
        vCube3d = getTransformee( vCube3d, vMatRot.getRotationHomogene3d( Math.PI/60, -Math.PI/5, 0 ) );
        
        // Translation selon (0,0,12) + coin inférieur droit
        Vecteur vVect1 = new Vecteur(3);
        vVect1.setCoordonnee( 0, 4.0 );
        vVect1.setCoordonnee( 1, -4.0 );
        vVect1.setCoordonnee( 2, 12.0 );
        Matrice vMatTrans1 = new Matrice( 4, 4 );
        vMatTrans1.setTranslationHomogene3d( vVect1 );
        vCube3d = getTransformee( vCube3d, vMatTrans1 );
        
        // Projection perspective
        Matrice vMatProj = new Matrice( 3, 4 );
        vMatProj.setProjectionPerspectiveOxyHomogene3d( 8.0 );
        vCube3d = getTransformee( vCube3d, vMatProj );
        
        // On normalise le résultat
        for (int i=0; i<vCube3d.length; i++) {
            vCube3d[i].normaliseHomogene();
        }
        
        // On affiche le parallelepipede dans le plan
        dessinerParallelepipede(p, vCube3d);
    }
    
    /*
     * 5.7
     * On obtient un parallépipède vu de l'avant avec les diagonales
     * de la face avant qui se croisent à l'origine. Il s'agit d'une
     * perspective sur le plan parallèle au plan 0xy à une distance 8
     * de l'origine. En prenant le sommet superieur gauche comme par exemple, qui
     * avait (-2, 1, 2.5) comme coordonnees, est ensuite passé à (-1/4, 1/8, 2.5/8)
     * après la projection perspective, et enfin à (-1/32, 1/64, 2.5/8) ce qui 
     * est très proche de l'origine. Cet exemple s'applique ainsi à tous les autres
     * points du parallépipède. On les retrouve alors tous très proches de l'origine.
     */
    
    /*
     * 5.10
     * Pour voir le parallélépipède sous un autre angle, on applique
     * juste une rotation homogène avant les translations et la
     * projection perspective. (La projection doit se faire en dernier
     * elle retourne une matrice non carrée donc on ne pourra plus 
     * appliquer une translation ou une rotation sur le cube après.
     */
    
    /**
     * Fonction de testDessinFianl
     */
    public static void testDessinFianl() throws Exception {
        // On crÃ©e un objet plan
        Plan p = new Plan();
        // On crÃ©e les sommets du parallelepipede
        Vecteur [] vCube3d = creerSommets();
        
        // Projection sur Oxy
        Matrice vMatProjOxy = new Matrice( 3, 4 );
        vMatProjOxy.setProjectionOrthoOxyHomogene3d();
        
        // Translation pour le placer dans le coin inferieur gauche
        Vecteur vVect1 = new Vecteur( 4 );
        vVect1.setCoordonnee( 0, -3.0 );
        vVect1.setCoordonnee( 1, -3.0 );
        vVect1.setCoordonnee( 2, 0.0 );
        vVect1.setCoordonnee( 3, 1.0 );
        
        Matrice vMatTrans1 = new Matrice( 4, 4 );
        vMatTrans1.setTranslationHomogene3d( vVect1 );
        
        // Matrice correspondant à projection + translation
        Matrice vMatProd1 = vMatProjOxy.produitMatriciel( vMatTrans1 );
        Vecteur[] vCube3d1 = getTransformee( vCube3d, vMatProd1 );
        
        // On affiche le parallelepipede dans le plan
        dessinerParallelepipede(p, vCube3d1);
        
        
        
        // Projection sur Oxz //
        
        Matrice vMatProjOxz = new Matrice( 3, 4 );
        vMatProjOxz.setProjectionOrthoOxzHomogene3d();
        
        // Translation pour le placer dans le coin superieur gauche
        Vecteur vVect2 = new Vecteur( 4 );
        vVect2.setCoordonnee( 0, -3.0 );
        vVect2.setCoordonnee( 1, 0.0 );
        vVect2.setCoordonnee( 2, 3.0 );
        vVect2.setCoordonnee( 3, 1.0 );
        
        Matrice vMatTrans2 = new Matrice( 4, 4 );
        vMatTrans2.setTranslationHomogene3d( vVect2 );
        
        // Matrice correspondant à projection + translation
        Matrice vMatProd2 = vMatProjOxz.produitMatriciel( vMatTrans2 );
        Vecteur[] vCube3d2 = getTransformee( vCube3d, vMatProd2 );
        
        // On affiche le parallelepipede dans le plan
        dessinerParallelepipede(p, vCube3d2);
        
        
        
        // Projection sur Oyz //
        
        Matrice vMatProjOyz = new Matrice( 3, 4 );
        vMatProjOyz.setProjectionOrthoOyzHomogene3d();
        
        // Translation pour le placer dans le coin superieur droit
        Vecteur vVect3 = new Vecteur( 4 );
        vVect3.setCoordonnee( 0, 0.0 );
        vVect3.setCoordonnee( 1, 3.0 );
        vVect3.setCoordonnee( 2, 3.0 );
        vVect3.setCoordonnee( 3, 1.0 );
        
        Matrice vMatTrans3 = new Matrice( 4, 4 );
        vMatTrans3.setTranslationHomogene3d( vVect3 );
        
        // Matrice correspondant à projection + translation
        Matrice vMatProd3 = vMatProjOyz.produitMatriciel( vMatTrans3 );
        Vecteur[] vCube3d3 = getTransformee( vCube3d, vMatProd3 );
        
        // On affiche le parallelepipede dans le plan
        dessinerParallelepipede(p, vCube3d3);
        
        
        // Projection perspective Oxy //
        
        // Rotation
        Matrice vMatRot = new Matrice( 4, 4 );
        Vecteur[] vCube3d4 = getTransformee( vCube3d, vMatRot.getRotationHomogene3d( Math.PI/60, -Math.PI/5, 0 ) );
        
        // Translation selon (0,0,12) + coin inférieur droit
        Vecteur vVect4 = new Vecteur(3);
        vVect4.setCoordonnee( 0, 4.0 );
        vVect4.setCoordonnee( 1, -4.0 );
        vVect4.setCoordonnee( 2, 12.0 );
        Matrice vMatTrans = new Matrice( 4, 4 );
        vMatTrans.setTranslationHomogene3d( vVect4 );
        vCube3d4 = getTransformee( vCube3d4, vMatTrans );
        
        // Projection perspective
        Matrice vMatProjPers = new Matrice( 3, 4 );
        vMatProjPers.setProjectionPerspectiveOxyHomogene3d( 8.0 );
        vCube3d4 = getTransformee( vCube3d4, vMatProjPers );
        
        // On normalise le résultat
        for (int i=0; i<vCube3d4.length; i++) {
            vCube3d4[i].normaliseHomogene();
        }
        
        // On affiche le parallelepipede dans le plan
        dessinerParallelepipede(p, vCube3d4);
    }
}