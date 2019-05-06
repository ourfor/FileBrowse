package Download;
import FileDetails.Details;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;


/**
 * 传入文件的路径后像客户端写入文件
 */

public class DownloadFile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String FileType;
    private String BaseUrl;
    private String ProjectName;

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public DownloadFile(String fileType, String baseUrl, String projectName) {
        FileType = fileType;
        BaseUrl = baseUrl;
        ProjectName = projectName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public String getBaseUrl() {
        return BaseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        BaseUrl = baseUrl;
    }

    public void Download(String dir, HttpServletResponse response) throws IOException {
        dir = dir.replaceFirst(ProjectName,"");
        dir = dir.replaceAll(" ","\\ ");

        String filename = dir.substring(dir.lastIndexOf("/")+1);
        dir = BaseUrl + dir;
        System.out.println("请求的文件路径为:"+dir);
        response.setContentType(Details.getType(filename));
        System.out.println(Details.getType(filename));
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(filename,"UTF-8"));

        //查看文件名
        System.out.println("下载的文件名为:"+filename);

        OutputStream os = response.getOutputStream();
        InputStream is = new FileInputStream(new File(dir));
        byte[] bytearrays = new byte[1024];
        int byteread = 0;
        while((byteread=is.read(bytearrays))!=-1){
            os.write(bytearrays,0,byteread);
        }
        os.flush();
        is.close();

    }

    private String ContentType(String fileType){
       String type = "";

       return type;
    }
}
