import java.io.IOException;

public class b4 {
    public static void saveToFile() throws IOException {
        System.out.println("c");
        throw new IOException("Lỗi c");
    }
    public static void processUserData() throws IOException {
        System.out.println("b");
        saveToFile();
    }
    public static void main(String[] args) {
        try {
            processUserData();
        } catch (IOException e) {
            System.out.println("xảy ra lỗi: " + e.getMessage());
        }
    }
}