import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ChargerDict {


    public static List<String> chargerDictionnaire(String filename) throws IOException {
        List<String> mots = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                mots.add(ligne.trim().toUpperCase());
            }
        }
        return mots;
    }
}
