package servlets;

import helpers.Quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateQuiz")
public class CreateQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public CreateQuiz() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String descript = request.getParameter("descript");
		boolean one_page = request.getParameter("paging") == "one";
		String type = request.getParameter("type");
		Quiz quiz = new Quiz(name, descript, one_page, type);
		request.getSession().setAttribute("quiz", quiz);
		RequestDispatcher dispatch = request.getRequestDispatcher("welcome.html");
		dispatch.forward(request, response);
	}

}
