package com.spdz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: 2021.1.26
 * @description: ${description}
 * @author: spdz
 * @create: 2021-01-26 18:34
 **/
public class OnePieceRewardServlet extends HttpServlet {
//    private static Map<String,String> map = new HashMap<>();
//    static {
//        map.put("罗杰","5,564,800,000贝利");
//        map.put("白胡子","5,046,000,000贝利");
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("名号");

        String reward;
        // select 悬赏 from onepiecereward where 名号 = ?
        try(Connection c = DBUtil.getConnection()) {
            String sql = "select 悬赏 from onepiecereward where 名号 = ?";
            try(PreparedStatement s = c.prepareStatement(sql)) {
                s.setString(1,name);
                try(ResultSet r = s.executeQuery()) {
                    if (r.next()) {
                        reward = r.getString("悬赏");
                    }else {
                        reward = "无悬赏";
                    }
                }
            }
        }catch (SQLException e) {
            throw new ServletException(e);
        }

        //String reward = map.getOrDefault(name,"无悬赏");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>悬赏</h1>");
        writer.println("<p>" + name + " 悬赏 " + reward + "</p>");

    }
}
