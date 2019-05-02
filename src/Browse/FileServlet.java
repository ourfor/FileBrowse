package Browse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
        name="browse",
        urlPatterns={
                "/*"
        }
)
public class FileServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private static String BaseUrl = "/Users/sagit";
        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response)
                throws IOException,ServletException{

                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");

                StringBuffer url = request.getRequestURL();
                int i =0;
                for(int j=0;j<4&&i<url.length();i++) {
                    if(url.charAt(i)=='/') j++;
                }
                String dir = url.substring(i-1);
                dir = dir.replaceAll("%20","\\ ");
                String server = url.substring(0,i);

                File type = new File(BaseUrl+dir);
                if(type.isDirectory()){
                    Show(dir,server,response);
                }
                else{
                    Download(dir,response);
                }


        }

        private static List<String> getChildren(String path){
            File file = new File(path);
            if(file.isDirectory()){
                List<String> list = new ArrayList<String>();
                File[] fileList = file.listFiles();
                for(File item : fileList){
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

            out.println("<!DOCTYPE HTML><head><title>"+dir+"</title></head><body>");
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
            String filename = dir.substring(dir.lastIndexOf("/")+1);
            System.out.println(dir);
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition","attachment;filename="+filename);
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
}

