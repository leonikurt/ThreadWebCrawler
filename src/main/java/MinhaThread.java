import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class MinhaThread extends Thread{
    private String link;
    private Buffer buffer;
    private Boolean flagDownload;

    public MinhaThread(String link, Buffer buffer, Boolean flagDownload){
        this.link = link;
        this.buffer = buffer;
        this.flagDownload = flagDownload;
        start();
    }

    public void run() {
        if(!flagDownload) {
            try {
                //Html
                Document document = Jsoup.connect(link).ignoreContentType(true).get();

                //Pegando as imagens do HTML
                Elements linksOnPage = document.select("img[src]");

                for (Element page : linksOnPage) {
                    String imgURL = page.attr("abs:src");
                    this.buffer.addImage(page.attr("abs:src"));
                }
            } catch (IOException e) {
                System.err.println("Para a URL '" + link + "': " + e.getMessage());
            }
            DownloaderImg downloader = new DownloaderImg(this.buffer);
            while(true){
                if(!buffer.getImages().isEmpty()){
                    downloader.downloadImg();
                }else {
                    break;
                }
            }
        }else{
            System.out.println(this.link);
            try {
                BufferedInputStream in = new BufferedInputStream(new URL(this.link).openStream());
                String[] link = this.link.split("/", 20);
                FileOutputStream fileOutputStream = new FileOutputStream(link[link.length - 1]);
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
