package sample;

import java.io.*;
import java.net.*;

public class Parse {
    public static CharSequence getDataFromUrl(String Url) throws Exception{
        URL url = new URL(Url);

        URLConnection conn = url.openConnection();

        String enc = conn.getContentEncoding();

        if(enc == null) enc = "ISO-8859-1";

        BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream(), enc));

        StringBuilder sb = new StringBuilder(32768);

        try{
            String str;
            while((str=bf.readLine())!=null){
                sb.append(str);
            }
        }catch (Exception ex){
            System.err.print(ex);
        }

        bf.close();

        return sb;
    }
}
