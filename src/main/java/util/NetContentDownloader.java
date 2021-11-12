package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class NetContentDownloader {

    private static final String FILES_PATH = "/home/sevo/IdeaProjects/homework_3/download/";

    public static void downloadContentByteByByte(String webpage) {
        String fileName = FILES_PATH + webpage.split("https://")[1] + System.currentTimeMillis() + ".html";

        try (InputStream inputStream = catchContent(webpage);
             OutputStream writer = new FileOutputStream(fileName)) {

            int result;
            while ((result = inputStream.read()) != -1) {
                writer.write((byte) result);
            }
            System.out.println("Successfully Downloaded via byte to byte mode");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void downloadContentBy8KbBuffer(String webpage) {
        String fileName = FILES_PATH + webpage.split("https://")[1] + System.currentTimeMillis() + ".html";

        try (InputStream inputStream = catchContent(webpage);
             OutputStream writer = new FileOutputStream(fileName)) {

            byte[] buffer = new byte[8192];
            int result;
            while ((result = inputStream.read(buffer)) != -1) {
                writer.write(buffer, 0, result);
            }
            System.out.println("Successfully Downloaded via 8kb buffer mode");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    private static InputStream catchContent(String target) {
        InputStream is = null;
        try {
            URL url = new URL(target);
            is = url.openStream();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return is;
    }

}
