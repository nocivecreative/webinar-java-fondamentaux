public class EmptyFileException extends Exception {
    public EmptyFileException(String message) {
        /* System.out.println(message); */
        super("Fichier vide");
    }
}
