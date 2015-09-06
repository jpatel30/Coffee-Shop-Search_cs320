package cs320.onlinetest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SaveAnswer
 */
@WebServlet("/onlinetest/SaveAnswer")
public class SaveAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
		HttpSession session = request.getSession();
		
		
		ArrayList<Test> list = (ArrayList<Test>) session.getAttribute("Questions");
		String qid = request.getParameter("qid");
		HashMap<String, String> hm = (HashMap<String, String>) session.getAttribute("Map");
		String redirectUrl = "../onlinetest/OnlineTest";
		if(hm == null){
			hm = new HashMap<>();
			
		}
		
		String end = request.getParameter("end");
			
		if( end!=null && end.equals("End Test")){
				redirectUrl = "../onlinetest/Result.jsp";
									
		} else{
			
			if(!hm.containsKey(qid)) {
			for(Test temp:list){
				String answerSaved = request.getParameter("option");
				hm.put(qid, "incorrect");
				if(temp.getAnswer().equals(answerSaved)){
					hm.put(qid, "correct");
					break;
					}
				}
			}
			session.setAttribute("Map", hm);
		}
		
		response.sendRedirect(redirectUrl);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
