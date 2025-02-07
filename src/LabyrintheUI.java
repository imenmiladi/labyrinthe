import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LabyrintheUI extends JPanel {
    private final Grille grille;
    private final int tailleCase = 40; // Taille de chaque case
    private int currentX, currentY;
    private final ArrayList<Noeud> cheminParcouru = new ArrayList<>(); // Chemin du joueur

    public LabyrintheUI(Grille grille) {
        this.grille = grille;

        // Récupérer le noeud de départ
        Noeud depart = grille.nDepart(grille);
        currentX = depart.x;
        currentY = depart.y;

        // Ajouter le départ au chemin parcouru
        cheminParcouru.add(depart);

        // Activer l'écoute des touches
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gererDeplacement(e.getKeyCode());
            }
        });

        setPreferredSize(new Dimension(grille.getGrille()[0].length * tailleCase, grille.getGrille().length * tailleCase));
    }

    private void gererDeplacement(int keyCode) {
        int newX = currentX;
        int newY = currentY;

        switch (keyCode) {
            case KeyEvent.VK_UP:    if (currentX > 0) newX--; break;
            case KeyEvent.VK_DOWN:  if (currentX < grille.getGrille().length - 1) newX++; break;
            case KeyEvent.VK_LEFT:  if (currentY > 0) newY--; break;
            case KeyEvent.VK_RIGHT: if (currentY < grille.getGrille()[0].length - 1) newY++; break;
            case KeyEvent.VK_Z: if (currentX > 0 && currentY > 0) { newX--; newY--; } break;
            case KeyEvent.VK_E: if (currentX > 0 && currentY < grille.getGrille()[0].length - 1) { newX--; newY++; } break;
            case KeyEvent.VK_C: if (currentX < grille.getGrille().length - 1 && currentY < grille.getGrille()[0].length - 1) { newX++; newY++; } break;
            case KeyEvent.VK_X: if (currentX < grille.getGrille().length - 1 && currentY > 0) { newX++; newY--; } break;
        }

        Noeud prochainNoeud = grille.getGrille()[newX][newY];

        // Vérifier si le déplacement est valide
        if (prochainNoeud.getLettre() != '#' && grille.getGrille()[currentX][currentY].voisins.contains(prochainNoeud)) {
            currentX = newX;
            currentY = newY;
            cheminParcouru.add(prochainNoeud); // Ajouter au chemin
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < grille.getGrille().length; i++) {
            for (int j = 0; j < grille.getGrille()[i].length; j++) {
                Noeud noeud = grille.getGrille()[i][j];
                char lettre = noeud.getLettre();
                int x = j * tailleCase;
                int y = i * tailleCase;

                // Définir la couleur de la case
                if (lettre == '#') {
                    g.setColor(Color.getHSBColor(30f / 360f, 1.0f, 0.6f)); // Murs
                } else if (cheminParcouru.contains(noeud)) {
                    g.setColor(Color.yellow);
                } else if (noeud.getTypeNoeud() == Noeud.TypeNoeud.arrivee) {
                    g.setColor(Color.blue);
                } else {
                    g.setColor(new Color(0, 255, 0, 150)); // Lettres normales
                }

                // Dessiner la case
                g.fillRect(x, y, tailleCase, tailleCase);
                g.setColor(Color.GRAY);
                g.drawRect(x, y, tailleCase, tailleCase); // Bordure

                // Afficher la lettre si ce n'est pas un mur
                if (lettre != '#') {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 20));
                    g.drawString(String.valueOf(lettre), x + tailleCase / 3, y + 2 * tailleCase / 3);
                }
            }
        }

        // Dessiner la position actuelle du joueur en rouge
        g.setColor(Color.RED);
        g.fillOval(currentY * tailleCase + 10, currentX * tailleCase + 10, 20, 20);

        // Afficher le chemin parcouru si le joueur atteint l'arrivée
        if (currentX == grille.getArrivee().x && currentY == grille.getArrivee().y) {
            System.out.println("Chemin parcouru : " + cheminParcouru);
        }
    }
}
