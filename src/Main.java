import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
         Noeud n = new Noeud(1,2,'f', Noeud.TypeNoeud.passante);
         System.out.println(n.toString());
         Grille g=new Grille(10,14);

        List<String> theme= Arrays.asList("food", "tech");
        List<String> dic = ChargerDict.chargerDictionnaire("themes/"+theme.get(1)+".txt");
        System.out.println("Dictionnaire chargé avec le theme " + theme.get(1)+ " avec "+((List<?>) dic).size() + " mots.");
        System.out.println(dic);

        g.creerGrille(dic);
        g.afficherGrille();
        g.afficherGraphe();



    }
}