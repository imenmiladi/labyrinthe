import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static <Noued> void main(String[] args) {
        System.out.println("Hello world!");
         Noeud n = new Noeud(1,2,'f', Noeud.TypeNoeud.passante);
         System.out.println(n.toString());
         Grille g=new Grille(10,14);
        Set<String> dic = new HashSet<>(Arrays.asList(
                "APPLE", "BANANA", "ORANGE", "GRAPE", "PEACH",
                "CHERRY", "MANGO", "LEMON", "LIME", "PLUM",
                "KIWI", "PINEAPPLE", "COCONUT", "STRAWBERRY",
                "BLUEBERRY", "RASPBERRY", "WATERMELON", "PAPAYA",
                "APRICOT", "FIG"
        ));

        g.creerGrille(dic);
        g.afficherGrille();


    }
}