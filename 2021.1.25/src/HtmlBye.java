import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: 2021.1.25
 * @description: ${description}
 * @author: spdz
 * @create: 2021-01-24 20:58
 **/
public class HtmlBye extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<hl>输出 html</hl>");
        writer.println("<p>再见 " + name + "年龄" + age + "</p>");
    }
}
