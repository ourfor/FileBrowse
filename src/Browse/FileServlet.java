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
import FileDetails.Details;
import FileDetails.FileList;


@WebServlet(
        name="browse",
        urlPatterns={
                "/*"
        }
)
public class FileServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private static String BaseUrl = "/var/www/file";
        private static String ProjectName = "FileBrowse/";
        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response)
                throws IOException,ServletException{

                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");

                StringBuffer url_ori = request.getRequestURL();
                System.out.println("原请求的URL为:"+url_ori);
                String url_dirty = url_ori.toString();
                String url = url_dirty.replaceFirst("FileBrowse/","");

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

                request.setAttribute("Title",dir);
                RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
                rd.forward(request,response);


                if(type.isDirectory()){
                    Show(dir,server,response);
                }
                else{
                    Download(dir,response);
                }


        }

        private static List<String> getChildren(String path){
            File file = new File(path);
            //实例化一个用于存储当前请求目录的文件的基本信息
            FileList fl = new FileList();

            if(file.isDirectory()){
                List<String> list = new ArrayList<String>();
                java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-MM-dd- HH:mm:ss");
                File[] fileList = file.listFiles();
                for(File item : fileList){
                    String filename = item.toString();
                    String changetime = sf.format(item.lastModified());
                    String filesize = Long.toString(item.length());
                    Details dl = new Details(filename,changetime,filesize,item.isDirectory());
                    fl.add(filename,dl);
                    list.add(item.toString());
                }
                return list;
            }

            else return null;
        }

        private static void Show(String dir,String server,HttpServletResponse response) throws IOException{
            PrintWriter out = response.getWriter();

            List<String> list = new ArrayList<String>();
            list = getChildren(BaseUrl+dir);
            System.out.println("当前请求文件为:"+BaseUrl+dir);

            response.setContentType("text/html;charset=UTF-8");

            out.println("<!DOCTYPE HTML><head><title>"+dir.replaceFirst(ProjectName,"")+"</title></head><body>");
//                out.println("目录:"+dir+"<br>");
//                out.println("服务器:"+server+"<br>");

            for(String item : list){
                item=item.substring(BaseUrl.length());
                System.out.println(item);
                String fileName = item.substring(item.lastIndexOf("/")+1);
                out.println("<a href='"+server+item.substring(1)+"' >");
                out.println(fileName);
                out.println("</a><br>");
            }

            out.println("</body></html>");

        }

        private static void Download(String dir,HttpServletResponse response) throws IOException {
            dir = dir.replaceFirst(ProjectName,"");
            dir = dir.replaceAll(" ","\\ ");

            String filename = dir.substring(dir.lastIndexOf("/")+1);
            dir = BaseUrl + dir;
            System.out.println("请求的文件路径为:"+dir);
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(filename,"UTF-8"));

            //查看文件名
            System.out.println("下载的文件名为:"+filename);

            OutputStream os = response.getOutputStream();
            InputStream is = new FileInputStream(new File(dir));
            byte[] bytearrays = new byte[8192];
            int byteread = 0;
            while((byteread=is.read(bytearrays))!=-1){
                os.write(bytearrays,0,byteread);
            }
            os.flush();
            is.close();


        }
}

