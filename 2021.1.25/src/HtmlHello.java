import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: 2021.1.25
 * @description: 动态资源练习二
 * @author: spdz
 * @create: 2021-01-24 19:34
 **/
public class HtmlHello extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("target");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<hl>输出 html</hl>");
        writer.println("<p>Hello " + target + "</p>");
    }
}
