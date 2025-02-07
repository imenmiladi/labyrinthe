import javax.swing.*;

public class fenetreDuLabyrinthe extends JFrame {
    public fenetreDuLabyrinthe(Grille grille) {
        setTitle("Jeu du Labyrinthe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        LabyrintheUI panel = new LabyrintheUI(grille);
        add(panel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
