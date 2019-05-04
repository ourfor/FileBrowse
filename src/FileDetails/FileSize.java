package FileDetails;

public class FileSize {
    public static String format(long size){
        if(size < 1024) return Long.toString(size)+"B";
        else if(size<1024 * 1024) return Long.toString(size / 1024) + "K";
        else if(size<1024 * 1024 * 1024) return Long.toString(size / 1024 / 1024) + "M";
        else if(size < 1024 * 1024 * 1024 * 1024) return Long.toString(size / 1024 / 1024 / 1024) + "G";
        else return Long.toString(size / (long)Math.pow(1024,4)) + "T";
    }
}
