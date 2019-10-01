import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> imagens = new ArrayList<String>();

        Buffer buffer = new Buffer(imagens);

        BufferedReader buffRead = new BufferedReader(new FileReader("C:/Users/leoni/Documents/crawler/links.txt"));
        String linha = "";
        linha = buffRead.readLine();

        while (true) {
            if (linha != null && linha != "") {
                System.out.println("Link: " + linha);
                MinhaThread thread = new MinhaThread(linha, buffer, false);
            }else{
                break;
            }
            linha = buffRead.readLine();
        }
        buffRead.close();
    }
}