package FileDetails;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class FileList {
    private Map<String,Details> list = new HashMap<String,Details>();

    public Map<String, Details> getList() {
        return list;
    }

    public void setList(Map<String, Details> list) {
        this.list = list;
    }

    public void add(String filename, Details details){
        list.put(filename,details);
    }

    public Details get(String filename){
        return list.get(filename);
    }
}
