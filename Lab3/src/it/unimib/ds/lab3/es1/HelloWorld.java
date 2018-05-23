package it.unimib.ds.lab3.es1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/es1/helloworld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter()
		.append("Served at: ")
		.append(request.getContextPath())
		.append("\nServer name :" + request.getServerName())
		.append("\nServer Port : " + request.getServerPort())
		.append("\nMethod : " + request.getMethod())
		.append("\nRequest Uri : " + request.getRequestURI());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter()
		.append("Served at: ")
		.append(request.getContextPath())
		.append("\nServer name :" + request.getServerName())
		.append("\nServer Port : " + request.getServerPort())
		.append("\nMethod : " + request.getMethod())
		.append("\nRequest Uri : " + request.getRequestURI());
	}

}
