import java.util.*;

public class Grille {
    private Noeud[][] grille;
    private int ligne;
    private int colonne;

    // Constructeur
    Grille(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
        grille = new Noeud[ligne][colonne];
    }

    public void creerGrille(List<String> dic) {
        Random rand = new Random();

        // Initialiser la grille avec des nœuds et des murs
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                char lettre = rand.nextDouble() < 0.2 ? '#' : '\0'; // 20% de murs
                Noeud.TypeNoeud typeNoeud = (lettre == '#') ? Noeud.TypeNoeud.bloquante : Noeud.TypeNoeud.passante;
                grille[i][j] = new Noeud(i, j, lettre, typeNoeud);
            }
        }

        // Prendre quelques mots aléatoirement du dictionnaire
        Collections.shuffle(dic);
        List<String> mots = new ArrayList<>();
        for (int i = 0; i < Math.min(Math.max(ligne, colonne), dic.size()); i++) {
            mots.add(dic.get(i));
        }

        // Placer les mots dans les cases passantes
        for (String mot : mots) {
            boolean place = false;
            int tentatives = 0;
            while (!place && tentatives < 100) { // Limiter les tentatives à 100
                int x = rand.nextInt(ligne);
                int y = rand.nextInt(colonne);
                int direction = rand.nextInt(3); // 0: horizontal, 1: vertical, 2: diagonal
                place = PeutEtrePlacer(mot, x, y, direction);
                tentatives++;
            }
            if (!place) {
                System.out.println("Impossible de placer le mot: " + mot);
            }
        }

        // Remplir les cases vides restantes avec des lettres aléatoires
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                if (grille[i][j].getLettre() == '\0') {
                    grille[i][j].setLettre((char) ('A' + rand.nextInt(26)));
                }
            }
        }

        // Créer les graphes des nœuds (ajouter les voisins)
        //creerGraphGrille();
    }

    // Essayer de placer un mot à la position (x, y) dans la direction spécifiée
    private boolean PeutEtrePlacer(String mot, int x, int y, int direction) {
        int len = mot.length();
        // Vérifier si le mot peut être placé dans la grille sans sortir des limites
        if (direction == 0) { // horizontale
            if (y + len > colonne) return false;
            for (int i = 0; i < len; i++) {
                if (grille[x][y + i].getLettre() != '\0' && grille[x][y + i].getLettre() != mot.charAt(i)) {
                    return false;
                }
            }
            // Placer le mot
            for (int i = 0; i < len; i++) {
                grille[x][y + i].setLettre(mot.charAt(i));
            }
        } else if (direction == 1) { // verticale
            if (x + len > ligne) return false;
            for (int i = 0; i < len; i++) {
                if (grille[x + i][y].getLettre() != '\0' && grille[x + i][y].getLettre() != mot.charAt(i)) {
                    return false;
                }
            }
            // Placer le mot
            for (int i = 0; i < len; i++) {
                grille[x + i][y].setLettre(mot.charAt(i));
            }
        } else if (direction == 2) { // diagonale
            if (x + len > ligne || y + len > colonne) return false;
            for (int i = 0; i < len; i++) {
                if (grille[x + i][y + i].getLettre() != '\0' && grille[x + i][y + i].getLettre() != mot.charAt(i)) {
                    return false;
                }
            }
            // Placer le mot
            for (int i = 0; i < len; i++) {
                grille[x + i][y + i].setLettre(mot.charAt(i));
            }
        }
        return true;
    }



    // Afficher la grille en termes de lettres
    public void afficherGrille() {
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                System.out.print(grille[i][j].getLettre() + " ");
            }
            System.out.println();
        }
    }



}
