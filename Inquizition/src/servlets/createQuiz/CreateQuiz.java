package servlets.createQuiz;

import helpers.Quiz;
import helpers.User;

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
    	//User creator = (User) request.getSession().getAttribute("user");	   
	   	User creator = new User("John");
	   	String name = request.getParameter("name");
		String descript = request.getParameter("descript");
		boolean one_page = request.getParameter("paging") == "one";
		boolean shuffle = request.getParameter("shuffle") == "yes";
		String type = request.getParameter("type");
		
		Quiz quiz = new Quiz(name, descript, one_page, creator.getID(), shuffle);
		quiz.addToDB();
		request.getSession().setAttribute("quiz", quiz);

		RequestDispatcher dispatch = request.getRequestDispatcher(getServletByType(type));
		dispatch.forward(request, response);
	}

	private String getServletByType(String type) {
		if(type.equals("qr")) return "/CreateQuestionResponse";
		if(type.equals("blank")) return "/CreateFillInBlank";
		if(type.equals("mcq")) return "/CreateMultipleChoice";
		if(type.equals("prq")) return "/CreatePictureResponse";
		return null;
	}

}
