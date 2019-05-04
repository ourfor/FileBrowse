package FileDetails;

public class Details {
    private String name;
    private String change;
    private String size;
    private String path;
    private String type;

    public Details(String name, String path, String change, String size,Boolean isDir) {
        this.name = name;
        this.change = change;
        this.size = isDir?"--":size;
        this.path = path;
        type = getFileType(name,isDir);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private static String getFileType(String fileName, Boolean dir){
        int index = fileName.lastIndexOf(".");
        Boolean hasExt = (index!=-1)? Boolean.TRUE:Boolean.FALSE;
        String extension = fileName.substring(index+1);

        if(dir) return "briefcase";

        //如果是文件而不是目录的话
        switch(extension){
            case "mkv":
            case "mp4":
                return "film";
            case "mp3":
                return "musical-notes";
            case "jpg":
            case "png":
            case "svg":
            case "ico":
                return "images";
            case "zip":
            case "tar":
            case "gz":
            case "7z":
                return "archive";
            case "doc":
                return "pricetags";
            case "xls":
                return "pricetags";
            case "ppt":
                return "pricetags";
            case "pdf":
                return "pricetags";
            case "exe":
            case "dmg":
            case "apk":
            case "rpm":
            case "deb":
                return "appstore";
            case "epub":
            case "txt":
                return "bookmarks";
            default:
                return "document";

        }
    }
}
