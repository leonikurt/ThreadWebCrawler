import java.util.Collections;
import java.util.List;

public class Buffer {
    private List<String> images;

    public Buffer(List<String> images) {
        this.images = images;
    }

    public String readImage() {
        synchronized (this) {
            if (!images.isEmpty()) {
                return images.remove(images.size() - 1);
            }
            return null;
        }
    }

    public void addImage(String urlImage){
        this.images.add(urlImage);
    }

    public List<String> getImages(){
        return this.images;
    }
}
