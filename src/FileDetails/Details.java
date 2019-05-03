package FileDetails;

public class Details {
    private String FileName;
    private String ModifyTime;
    private String FileSize;
    private String FileType;

    public Details(String fileName, String modifyTime, String fileSize,Boolean isDir) {
        FileName = fileName;
        ModifyTime = modifyTime;
        FileSize = fileSize;
        FileType = getFileType(fileName,isDir);
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getModifyTime() {
        return ModifyTime;
    }

    public void setModifyTime(String modifyTime) {
        ModifyTime = modifyTime;
    }

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String fileSize) {
        FileSize = fileSize;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    private static String getFileType(String fileName,Boolean dir){
        int index = fileName.lastIndexOf(".");
        Boolean hasExt = (index!=-1)? Boolean.TRUE:Boolean.FALSE;
        String extension = fileName.substring(index+1);

        if(dir) return "directory";

        //如果是文件而不是目录的话
        switch(extension){
            case "mkv":
            case "mp4":
                return "video";
            case "mp3":
                return "audio";
            case "txt":
                return "text";
            case "doc":
                return "doc";
            case "xls":
                return "excel";
            case "ppt":
                return "powerpoint";
            default:
                return "file";

        }
    }
}
