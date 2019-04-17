package sample;


import java.io.IOException;

public class VeryficationFileName {

    public static String VerifyFileName (String DestinationFileName) throws IOException
    {
        return DestinationFileName.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
}
