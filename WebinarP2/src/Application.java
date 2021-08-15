import java.io.*;
import java.util.Scanner;

public class Application {

    private final String path = ".\\WebinarP2\\src\\resources\\";

    public void readFile() {
        String fileToRead = "longText";
        String completePath = path + fileToRead;

        try {
            File file = new File("filename.txt");
            Scanner myReader = new Scanner(file);
            int count = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (count == 0 && data.equals("")) {
                    throw new EmptyFileException("Empty file");
                }
                if (data.matches(".*\\d.*")) {
                    throw new NotAllowedCharacter("Number present");
                }
                count++;
                System.out.println(data);
            }
            myReader.close();
        } catch (NotAllowedCharacter e) {
            System.out.println("Number error : " + e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EmptyFileException e) {
            System.out.println("Fichier vide" + e);
        }
    }

    public static void main(String[] args) {
        Application application = new Application();

        application.readFile();
    }
}
