package Browse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

import Download.DownloadFile;
import FileDetails.Details;
import FileDetails.FileList;
import FileDetails.FileSize;


public class FileServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private static String BaseUrl = "/Users/sagit";
        private static String ProjectName = "FileBrowse/";
        private static FileList fl = null;
        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response)
                throws IOException,ServletException{

                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");

                StringBuffer url_ori = request.getRequestURL();
                System.out.println("原请求的URL为:"+url_ori);
                String url_dirty = url_ori.toString();
                String url = url_dirty.replaceFirst(ProjectName,"");

                System.out.println("处理以后的请求URL为:"+url);
                int i =0;
                for(int j=0;j<3&&i<url.length();i++) {
                    if(url.charAt(i)=='/') j++;
                }
                String dir = url.substring(i-1);
                //url转义
                dir = URLDecoder.decode(dir,"UTF-8");
                dir = dir.replaceAll(" ","\\ ");
                String server = url.substring(0,i);

                File type = new File(BaseUrl+dir);
                System.out.println("查看路径:"+BaseUrl+dir+"的文件类型");



                if(type.isDirectory()){
//                    Show(dir,server,response);
                    fl = getChildren(BaseUrl + dir);
                    List<Details> fd = fl.getList();
                    request.setAttribute("FileList",fd);
                }
                else{
                    DownloadFile df = new DownloadFile(type.getName(),BaseUrl,ProjectName);
                    df.Download(dir,response);
                }

                request.setAttribute("Title",dir);
                request.setAttribute("server",server);
                request.setAttribute("project",ProjectName);
                RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
                rd.forward(request,response);


        }

        private static FileList getChildren(String path){
            File file = new File(path);
            //实例化一个用于存储当前请求目录的文件的基本信息
            FileList fl = new FileList();

            if(file.isDirectory()){
                List<String> list = new ArrayList<String>();
                java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                File[] fileList = file.listFiles();
                for(File item : fileList){
                    String fullPath = item.toString();
                    String name = fullPath.substring(path.length());
                    if(name.startsWith("/")) name=name.substring(1);
                    fullPath = fullPath.substring(BaseUrl.length()+1);
                    System.out.println(name);
                    String change = sf.format(item.lastModified());
                    String size = FileSize.format(item.length());
                    Details dl = new Details(name,fullPath,change,size,item.isDirectory());
                    fl.add(dl);
                    list.add(item.toString());
                }

//                return list;
                return fl;
            }

            else return null;
        }



}

