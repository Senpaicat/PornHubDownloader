package sample;

import java.io.*;
import java.util.regex.*;

public class Search {

    static String title;
    static String format;
    static String resolution;
    static String url;

    public static String Query(String Url) throws Exception{
        url = null;

        if(!Url.contains("pornhub")) return "";

        CharSequence sb = Parse.getDataFromUrl(Url);

        String ex = "720\",\"videoUrl\":\".*?\"";

        Pattern pat = Pattern.compile(ex, Pattern.DOTALL | Pattern.UNIX_LINES);

        Matcher match = pat.matcher(sb);

        while(match.find()){
            String TITLE = Functions.getValue(sb.toString(), "<title>", "</title>").replace(" - Pornhub.com", "");

            String URL = match.group().replace("\\", "").replace("720\",\"videoUrl\":\"", "");

            return TITLE + ";" + URL;

        }
        return "";
    }

}
