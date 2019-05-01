package RequestPath;
import javax.servlet.http.HttpServletRequest;

public class RequestDirectory {
    public String getDirectory(HttpServletRequest request){
        String uri = request.getRequestURI();
        uri = uri.substring(uri.indexOf("/")+1);
        uri = uri.substring(uri.indexOf("/"));

        return uri;
    }
}
