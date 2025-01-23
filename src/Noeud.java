import java.util.List;

public class Noeud {

    public int x,y;
    public char lettre;
    public enum TypeNoeud{
        passante,
        bloquante,
        depart,
        arrivee,
    }
    public TypeNoeud typeNoeud;
    public List<Noeud> voisins;
   public Noeud(int x, int y, char lettre, TypeNoeud typeNoeud) {
       this.x = x;
       this.y = y;
       this.lettre = lettre;
       this.typeNoeud = typeNoeud;
   }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getLettre() {
        return lettre;
    }

    public void setLettre(char lettre) {
        this.lettre = lettre;
    }

    public TypeNoeud getTypeNoeud() {
        return typeNoeud;
    }

    public void setTypeNoeud(TypeNoeud typeNoeud) {
        this.typeNoeud = typeNoeud;
    }

    public List<Noeud> getVoisins() {
        return voisins;
    }

    public void setVoisins(List<Noeud> voisins) {
        this.voisins = voisins;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ",'" + lettre + "', " + typeNoeud.toString() + ")";
    }
}

