import java.util.*;

public class Grille {
   private char[][] grille;
   private int ligne;
   private int colonne;

   Grille(int ligne, int colonne) {
       this.ligne = ligne;
       this.colonne = colonne;
       grille = new char[ligne][colonne];
   }

   public void creerGrille(Set<String> dic) {

   for (int i = 0; i < ligne; i++) {
       for (int j = 0; j < colonne; j++) {
           grille[i][j] = '0';
   }
  }
   }

   public void afficherGrille() {
       for (int i = 0; i < ligne; i++) {
           for (int j = 0; j < colonne; j++) {
               System.out.print(grille[i][j] + " ");

           }
           System.out.println();
       }
   }


}
