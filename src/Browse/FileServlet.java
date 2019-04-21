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
        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response)
                throws IOException,ServletException{

                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");

                StringBuffer url = request.getRequestURL();
                String dir = "";
                String server = "";
                int i =0;
                for(int j=0;j<4&&i<url.length()-2;i++) {
                    if(url.charAt(i)=='/') j++;
                }
                dir = url.substring(i-1);
                server = url.substring(0,i);


                PrintWriter out = response.getWriter();

                List<String> list = new ArrayList<String>();
                list = getChildren(dir);

                out.println("<!DOCTYPE HTML><head><title>"+"目录浏览"+"</title></head><body>");
                out.println("目录:"+dir+"<br>");
                out.println("服务器:"+server+"<br>");

                for(String item : list){
                    String fileName = item.substring(item.lastIndexOf("/")+1);
                    out.println("<a href="+server+item.substring(1)+">");
                    out.println(fileName);
                    out.println("</a><br>");
                }

                out.println("</body></html>");

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
}

