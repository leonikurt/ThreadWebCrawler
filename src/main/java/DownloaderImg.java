public class DownloaderImg {
    private Buffer buffer;

    public DownloaderImg(Buffer buffer){
        this.buffer = buffer;
    }

    public void downloadImg(){
        String link  = buffer.readImage();
        if(link != null) {
            MinhaThread thread = new MinhaThread(link, this.buffer, true);
        }
    }
}
