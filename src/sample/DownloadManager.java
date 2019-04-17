package sample;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DownloadManager extends Thread {

    String Url;
    String DestinationFile;
    String Directory;
    public static void downloadFileAsync(String Url, String Directory, String DestinationFile) throws IOException {
        new DownloadManager(Url, Directory, VeryficationFileName.VerifyFileName(DestinationFile)).start();
    }

    public DownloadManager(String Url, String Directory, String DestinationFile) {
        this.Url = Url;
        this.DestinationFile = DestinationFile;
        this.Directory = Directory;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(Url);
            File file = new File(Directory + DestinationFile);

            long downloadedLength = 0;

            BufferedInputStream inputStream = null;
            BufferedOutputStream outputStream = null;

            URLConnection connection = url.openConnection();

            if (file.exists()) {
                downloadedLength = file.length();
                connection.setRequestProperty("Range", "bytes=" + downloadedLength + "-");
                outputStream = new BufferedOutputStream(new FileOutputStream(file, true));

            } else {
                outputStream = new BufferedOutputStream(new FileOutputStream(file));
            }

            connection.connect();

            inputStream = new BufferedInputStream(connection.getInputStream());

            byte[] buffer = new byte[1024 * 8];
            int byteCount;

            int cLength = connection.getContentLength();

            while ((byteCount = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, byteCount);

                downloadedLength += byteCount;

                downloadFileAsyncEventargs(cLength, downloadedLength);
            }

            inputStream.close();
            outputStream.flush();
            outputStream.close();

            downloadFileAsyncFinished();
        } catch (Exception e) {
            System.out.println("Download failed - " + e);
        }

    }

    public void downloadFileAsyncEventargs(int FileSize, long FileSizeDownloaded) {
        System.out.println(FileSizeDownloaded + " of " + FileSize + " " +
                (FileSizeDownloaded * 100) / FileSize + " %");
    }

    private void downloadFileAsyncFinished() {
        System.out.println("Download finished");
    }

}
