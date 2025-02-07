import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Menu extends JFrame {
    private JComboBox<String> comboThemes;
    private JComboBox<String> comboDifficulte;
    private JButton btnCommencer;

    private List<String> themes = Arrays.asList("food", "tech");
    private List<String> niveaux = Arrays.asList("Facile", "Moyen", "Difficile");

    public Menu() {
        setTitle("Choisissez un Thème et un Niveau");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));
        setLocationRelativeTo(null);

        // Labels
        add(new JLabel("Thème :"));
        comboThemes = new JComboBox<>(themes.toArray(new String[0]));
        add(comboThemes);

        add(new JLabel("Niveau de difficulté :"));
        comboDifficulte = new JComboBox<>(niveaux.toArray(new String[0]));
        add(comboDifficulte);

        // Bouton Commencer
        btnCommencer = new JButton("Commencer");
        add(new JLabel()); // Espace vide pour alignement
        add(btnCommencer);

        btnCommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String theme = (String) comboThemes.getSelectedItem();
                String niveau = (String) comboDifficulte.getSelectedItem();

                try {
                    lancerJeu(theme, niveau);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur lors du chargement du jeu.");
                }
            }
        });

        setVisible(true);
    }

    private void lancerJeu(String theme, String niveau) throws IOException {
        // Charger le dictionnaire en fonction du thème
        List<String> dic = ChargerDict.chargerDictionnaire("themes/" + theme + ".txt");
        System.out.println("Dictionnaire chargé avec le thème " + theme + " (" + dic.size() + " mots).");

        // Définir la taille de la grille en fonction du niveau
        int tailleX = 10, tailleY = 14;
        switch (niveau) {
            case "Moyen":
                tailleX = 15; tailleY = 18;
                break;
            case "Difficile":
                tailleX = 20; tailleY = 24;
                break;
        }

        // Générer la grille
        Grille g = new Grille(tailleX, tailleY);

        g.creerGrille(dic);
        g.afficherGrille();
        g.afficherGraphe();
        g.nDepart(g);
        g.nArrivee(g);

        // Lancer la fenêtre du jeu
        SwingUtilities.invokeLater(() -> new fenetreDuLabyrinthe(g));

        // Fermer la fenêtre de sélection
        dispose();
    }

    public static void main(String[] args) {
        new Menu();
    }
}
