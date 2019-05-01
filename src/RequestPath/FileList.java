package RequestPath;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import ListFile.Files;


public class FileList {

    private Files files = new Files();
    private String BasePath = "/Users/sagit";

    public Files getFileLists(String path){
        File fp = new File(BasePath+path);

        File[] fileList = fp.listFiles();
        for(File item:fileList){
            files.getAllFiles().add(item.getName());
            System.out.println(item.getName());
        }
        return files;
    }

}
