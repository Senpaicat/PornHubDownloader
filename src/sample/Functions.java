package sample;

public class Functions {

    public static String getValue(String source, String Start, String End) throws Exception {
        int start, end;

        try{
            if(source.contains(Start) && source.contains(End)){
                start = source.indexOf(Start, 0) + Start.length();
                end = source.indexOf(End, start);

                return source.substring(start, end);
            }
            else
            {
                return "";
            }
        }catch (Exception ex){
            System.err.print(ex);

            return "";
        }

    }

}
