package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import thread.Run;
import utils.PropertiesUtils;

public class StartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		String path = getServletContext().getRealPath("/");
		try {
			PropertiesUtils.loadProperties(path);
		} catch (IOException e) {
			System.out.println("读取配置文件出错!!");
			return;
		}
		Run.main(new String[]{});
	}

}
