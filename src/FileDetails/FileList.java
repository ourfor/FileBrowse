package FileDetails;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class FileList {
    private List<Details> list = new ArrayList<Details>();

    public List<Details> getList() {
        return list;
    }

    public void setList(List<Details> list) {
        this.list = list;
    }

    public void add(Details e){
        list.add(e);
    }
}
