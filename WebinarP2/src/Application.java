import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class Application {

    private final Path path = Paths.get("src", "resources");
    // private final String path = null;
    private String[] pathnames;

    public void init() throws IOException {
        // parcours le contenu d'un rep via la classe File
        File f = null;
        try {
            f = new File(this.path.toString());
            // System.out.println(this.path.toString());
            // throw new EmptyFileException("");
            // } catch (NullPointerException | EmptyFileException e) {
        } catch (NullPointerException e) {
            System.err.println("catch Exception caught : " + e);
        }
        this.pathnames = f.list();
        String fileToRead = chooseUrFile();
        if (fileToRead != null) {
            readFile(fileToRead);
        }

    }

    public String chooseUrFile() {
        System.out.println("Choisir un fichier à lire :");
        if (this.pathnames != null) {
            // IntStream.range(0, n) génère une suite de nombres de 0 à n−1
            IntStream.range(0, this.pathnames.length)
                    .forEach(i -> System.out.println("\t" + (i + 1) + "- " + this.pathnames[i]));
        } /*
           * else {
           * throw new NullPathException("");
           * }
           */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String reponse = null;
        try {
            reponse = reader.readLine();
        } catch (Exception e) {
            System.err.println("Catch de IOException");
        }
        Integer rInt = Integer.parseInt(reponse) - 1;
        if (rInt >= 0 && rInt <= this.pathnames.length + 1) {
            System.out.println("Vous avez choisis de lire : \"" + this.pathnames[rInt] + "\", please wait...");
            return this.pathnames[rInt];
        } else {
            /* throw new EmptyFileException(""); */
            return null;
        }
    }

    public void readFile(final String fileName) {
        // Remplacer le nom du fichier à lire ici
        String completePath = this.path + "\\" + fileName;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(completePath));
            String currLine;
            boolean firstLine = true;
            while ((currLine = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                }
                if (currLine.matches(".*\\d.*")) {
                    reader.close();
                    throw new EmptyFileException("Line = " + currLine);
                } else {
                    System.out.println(currLine);
                }
            }

            if (firstLine) {
                reader.close();
                throw new EmptyFileException("Fichier vide");
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Fichier introuvable : " + completePath);
        } catch (IOException e) {
            System.err.println("Erreur sur la lecture du fichier :" + e.getMessage());
        } catch (EmptyFileException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) throws IOException {
        Application application = new Application();

        application.init();

    }
}
