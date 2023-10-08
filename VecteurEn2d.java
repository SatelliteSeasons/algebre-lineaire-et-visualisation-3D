/**
 * ATL_2201
 * 
 * @author HO Richard & PHAN Theo
 * @version 22/09/2023
 */
public class VecteurEn2d
{
    private double aX;
    private double aY;

    /**
     * Constructeur d'objets de classe VecteurEn2d
     */
    public VecteurEn2d( final double pX, final double pY)
    {
        this.aX = pX;
        this.aY = pY;
    }

    public VecteurEn2d( final VecteurEn2d pVecteur)
    {
        this.aX = pVecteur.getX();
        this.aY = pVecteur.getY();
    }
    
    public double getX()
    {
        return this.aX;
    }
    
    public double getY()
    {
        return this.aY;
    }
    
    public double norme()
    {
        return Math.sqrt(this.getX()*this.getX() + this.getY()*this.getY());
    }
    
    public void multiplicationScalaire( final double pScalaire )
    {
        this.aX = this.getX()*pScalaire;
        this.aY = this.getY()*pScalaire;
    }
    
    public void sommeVectorielle( final VecteurEn2d pVecteur)
    {
        this.aX += pVecteur.getX();
        this.aY += pVecteur.getY();
    }
    
    public double produitScalaire( final VecteurEn2d pVecteur )
    {
        return this.getX()*pVecteur.getX() + this.getY()*pVecteur.getY();
    }
    
    public boolean estOrthogonal( final VecteurEn2d pVecteur )
    {
        double vProdSca = this.produitScalaire( pVecteur );
        if ( approche( 0.0, vProdSca, 1E-9, 1E-5 ) )
            return true;
        else
            return false;
    }
    
    public VecteurEn2d obtenirVectOrthogonal()
    {
        //On resout l'equation x = -(yy')/x'
        //Comme les solutions sont infinies, on fixe arbitrairement Y a 1.0
        if ( approche( 0.0, this.getX(), 1E-9, 1E-5 ) )
        {//x' nul donc on resout y = -(xx')/y' = 0
            return new VecteurEn2d( 1.0, 0.0 );
        }
        else //sinon x = -y'/x' car y=1.0
            return new VecteurEn2d( -this.getY()/this.getX(), 1.0 );
    }
    
    public static boolean approche( final double pA, final double pB, final double pEpsilon_rel, final double pEpsilon_abs )
    {
         return (Math.abs(pA-pB) <= Math.max( Math.max(Math.abs(pA), Math.abs(pB) ) * pEpsilon_rel, pEpsilon_abs) );
    }
    
    public boolean estColineaire( final VecteurEn2d pVecteur)
    {
        return ( approche(0.0, this.getX()*pVecteur.getY()-this.getY()*pVecteur.getX(), 1E-9, 1E-5) );
    }
    
    public double distanceA( final VecteurEn2d pVecteur )
    {
        VecteurEn2d vVecteur = new VecteurEn2d( this );
        vVecteur.multiplicationScalaire( -1.0 );
        vVecteur.sommeVectorielle(pVecteur);
        return vVecteur.norme();
    }
    
    public boolean segmentsSontOrthogonaux( final VecteurEn2d pVecteurA, final VecteurEn2d pVecteurC )
    {
        VecteurEn2d vAprime = new VecteurEn2d( pVecteurA );       
        VecteurEn2d vCprime = new VecteurEn2d( pVecteurC );
        VecteurEn2d vB = new VecteurEn2d( this );
        vB.multiplicationScalaire( -1.0 );
        
        vAprime.sommeVectorielle( vB );
        vCprime.sommeVectorielle( vB );
        
        return (vAprime.estOrthogonal( vCprime ));
    }
}//VecteurEn2d



/* Reponse 8.6 :
 * Resultat obtenu : true donc conforme meme en verifiant numeriquement
 * et on avait mis un encadrement entre -0.01 et 0.01.
 * En multipliant v par 0.1, on trouvait qu'ils etaient toujours orthogonaux car 
 * la norme et l'angle d'un vecteur par rapport a l'origine par exemple, sont 
 * independants l'un de l'autre.
 * 
 * 
 * Reponse 13.2 :
 * distance AB = 7.6157
 * distance BC = 7.8262
 * distance CD = 7.6157
 * distance DA = 7.8262
 * Aucune orthogonalite entre les vecteurs.
 * Il s'agit donc d'un simple parallelepipede.
 */