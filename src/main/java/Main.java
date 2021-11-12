import util.NetContentDownloader;

public class Main {

    public static void main(String[] args) {

        String url = "https://ok.ru";
        NetContentDownloader.downloadContentByteByByte(url);
        NetContentDownloader.downloadContentBy8KbBuffer(url);
    }

}
