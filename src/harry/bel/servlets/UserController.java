package harry.bel.servlets;

import harry.bel.objects.MySQLConnector;
import harry.bel.objects.User;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	//Declare for MySQL connection and User object
	
	private MySQLConnector connection = new MySQLConnector();
	
	private User user = new User(connection.getConnector());
	
    public UserController() {
        super();
        //Do something on constructor
        
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Set encoding for request and response informations
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//Declare some variables in this function
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		//Confirm for GET request: create user
		
		String typeRequest = request.getParameter("requestInsertUser");
		String requestToUserController = (String) session.getAttribute("requestToUserController");
		
		if(typeRequest == null) {
			typeRequest = "";
		} else {
			requestToUserController = "";
		}
		
		if(typeRequest.equals("insertUser")) {
			this.insertUserToDatabase(request, response, session, dispatcher);
		}
		
		switch(requestToUserController) {
			case "requestGetUserNameFromLendingController": {
				this.getUserNamePayBook(request, response, session, dispatcher);
			}	break;
			
			default: {
				
			}	break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Set encoding for request and response
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//Declare some variables in this function
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		//Get value of type request to UserController
		
		String typeUser = request.getParameter("typeUser");
		
		switch (typeUser) {
			case "login": {
				this.handleLogin(request, response, session, dispatcher);
			} 	break;
			
			case "updateUser": {
				this.updateUserInformations(request, response, session, dispatcher);
			}	break;
			
			case "submitCheckForLendingBookNoRegister": {
				this.checkExistenceUser(request, response, session, dispatcher);
			}	break;

			default: {
				
			}	break;
		}
	}
	//Declare function to handle for login
	
	private void handleLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get userName and userPassword from request variables
		
		String userName = request.getParameter("nameLog");
		String userPassword = request.getParameter("passLog");
		String url = "";
		String[] valuePropertiesUser;
		//Check for User
		
		if(this.user.checkUser(userName, userPassword)) {
			
			if(!this.user.getFlagAdmin()) {
				//Set value for attribute when user is Librarian

				session.setAttribute("styleManager", "display: none;");
			}
			else {
				session.setAttribute("styleManager", "");
			}
			//Get value of user properties here
			
			user.getInformations(userName, userPassword);
			valuePropertiesUser = user.getValueProperties();
			//Set value for attributes
			
			this.setAttributesUser(session, valuePropertiesUser);
			//Assign value for url
			
			url = "/main.jsp";
		}
		else {
			//Set attribute to display error
			
			session.setAttribute("styleNotify", "errorLogin");
			session.setAttribute("contentNotify", "Đăng nhập thất bại");
			session.setAttribute("namePredefine", userName);
			
			url = "/index.jsp";
		}
		//Set attributes to request parameter
		
		request.setAttribute("user", this.user);
		request.setAttribute("connection", this.connection);
		//Forward for request
		
		dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private void updateUserInformations(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get type update string
		
		String typeUpdateUser = request.getParameter("infsUserUpdateType");
		String temple = "";
		String url = "/main.jsp";
		//Switch thought update request corresponding and handle
		
		switch(typeUpdateUser) {
			case "updateUserName": {
				//Get and update user name
				
				temple = request.getParameter("infsUserName");
				this.user.updateInformations(temple, User.USER_NAME);
			} 	break;
					
			case "updateUserBirthday": {
				//Get and update user birthday
				
				temple = request.getParameter("infsYearBirthdayUser") + "-" + request.getParameter("infsMonthBirthdayUser") + "-" + request.getParameter("infsDateBirthdayUser");
				this.user.updateInformations(temple, User.USER_BIRTHDAY);
			}	break;
			
			case "updateUserGender": {
				//Get and update user gender
				
				temple = request.getParameter("infsUserGender");
				this.user.updateInformations(temple, User.USER_GENDER);
			}	break;
			
			case "updateUserJob": {
				//Get and update user job
				
				temple = request.getParameter("infsUserJob");
				this.user.updateInformations(temple, User.USER_JOB);
			}	break;
			
			case "updateUserAddress": {
				//Get and update user address
				
				temple = request.getParameter("infsUserAddress");
				this.user.updateInformations(temple, User.USER_ADDRESS);
			}	break;
			
			case "updateUserPhone": {
				//Get and update user phone
				
				temple = request.getParameter("infsUserPhone");
				this.user.updateInformations(temple, User.USER_TELEPHONE);
			}	break;
			
			case "updateUserMail": {
				//Get and update user email
				
				temple = request.getParameter("infsUserEmail");
				this.user.updateInformations(temple, User.USER_EMAIL);
			} break;
			
			case "updateAdditionalLibrarian": {
				//Get and update librarian additional information
				
				temple = request.getParameter("infsAdminAdditional");
				this.user.updateInformations(temple, User.ADMIN_ADD_INFS);
			}	break;
			
			case "updateUserPassword": {
				//Get and update password
				
				temple = request.getParameter("infsUserPasswordNew");
				this.user.updateInformations(temple, User.USER_PASS);
			}	break;
			
			default: {
						
			}	break;
		}
		//Get and update user information to interface
		
		this.user.getInformations(this.user.getValueProperties()[User.USER_ID]);
		String[] valuePropertiesUser = this.user.getValueProperties();
		
		this.setAttributesUser(session, valuePropertiesUser);
		
		dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private void setAttributesUser(HttpSession session, String[] valuePropertiesUser) {
		session.setAttribute("userName", valuePropertiesUser[User.USER_NAME]);
		session.setAttribute("userDateCreated", valuePropertiesUser[User.USER_TIME_BEGIN]);
		session.setAttribute("userBirthday", valuePropertiesUser[User.USER_BIRTHDAY]);
		session.setAttribute("userGender", valuePropertiesUser[User.USER_GENDER]);
		session.setAttribute("userJob", valuePropertiesUser[User.USER_JOB]);
		session.setAttribute("userAddress", valuePropertiesUser[User.USER_ADDRESS]);
		session.setAttribute("userPhone", valuePropertiesUser[User.USER_TELEPHONE]);
		session.setAttribute("userEmail", valuePropertiesUser[User.USER_EMAIL]);
		session.setAttribute("userId", valuePropertiesUser[User.USER_ID]);
		session.setAttribute("adminAdditionInfs", valuePropertiesUser[User.ADMIN_ADD_INFS]);
		session.setAttribute("userPassword", valuePropertiesUser[User.USER_PASS]);
		session.setAttribute("namePredefine", "");
	}
	
	private void insertUserToDatabase(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Report some variables in this function
		
		String[] informations = new String[User.SIZE_USER_PROPERTIES];
		String url = "/main.jsp";
		//Get informations from request parameter
		
		informations[User.USER_NAME] = request.getParameter("userNameInsertion");
		informations[User.USER_PASS] = request.getParameter("userPasswordInsertion");
		informations[User.USER_EMAIL] = request.getParameter("userEmailInsertion");
		informations[User.USER_ADDRESS] = request.getParameter("userAddressInsertion");
		informations[User.USER_TELEPHONE] = request.getParameter("userPhoneInsertion");
		informations[User.USER_GENDER] = request.getParameter("userGenderInsertion");
		informations[User.USER_BIRTHDAY] = request.getParameter("userYearBirthdayInsertion") + "-" + request.getParameter("userMonthBirthdayInsertion") + "-" + request.getParameter("userDateBirthdayInsertion");
		informations[User.USER_JOB] = request.getParameter("userJobInsertion");
		//Check for name have exist in database
		
		if(!this.user.existAccount(informations[User.USER_NAME])) {
			//Insert to database
		
			this.user.insertUser(informations);
			session.setAttribute("styleErrorNameExistCreateAccount", "display: none;");
		} else {
			//Display error to Librarian
			
			session.setAttribute("styleErrorNameExistCreateAccount", "display: block;");
		}
		//Set type of Get request
		
		session.setAttribute("typeGetUserInsert", "");
		//Forward webpage to main.jsp
		
		dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private void checkExistenceUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws IOException, ServletException {
		//Get parameters from client
		
		String userId = request.getParameter("n_userIdNoRegisterLendingBookContent");
		String bookItemCode = request.getParameter("n_bookItemCodeNoRegisterLendingBookContent");
		//Clear value of user name to check
		
		this.user.getValueProperties()[User.USER_NAME] = "";
		//Get value by userId
		
		this.user.getInformations(userId);
		
		if(!this.user.getValueProperties()[User.USER_NAME].equals("")) {
			session.setAttribute("userIdNoRegisterLendingBook", userId);
			session.setAttribute("userNameNoRegisterLendingBook", this.user.getValueProperties()[User.USER_NAME]);
			session.setAttribute("bookCodeFromUserNoRegisterBefore",bookItemCode);
			session.setAttribute("requestFromLending", "checkExistenceBookFromUser");
			//Send redirect to BookTitleController
			
			response.sendRedirect("BookItemController");
		} else {
			//Display error to user
			
			session.setAttribute("styleSuccessLendingBookNotRegister", "display: none;");
			session.setAttribute("styleResultNoRegisterLendingBookContent", "display: block;");
			session.setAttribute("styleErrorUserNoRegisterLendingBookContent", "display: block;");
			session.setAttribute("styleNotFoundBookItemIdNoRegisterBeforeLendingBook", "display: none;");
			session.setAttribute("styleErrorNotAvaiableToLendingNoRegister", "display: none;");
			session.setAttribute("styleErrorBookItemNoRegisterLendingBook", "display: none;");
			session.setAttribute("styleInfringeRolesNotRegisterLendingBook", "display: none;");
			//Create dispatcher and forward to main.jsp
			
			dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private void getUserNamePayBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws IOException {
		//Get user id from LendingController
		
		String userId = (String) session.getAttribute("userIdPayBookPart");
		//Get value properties
		
		this.user.getInformations(userId);
		//Set userId to display to user
		
		session.setAttribute("userNamePayBookPart", this.user.getValueProperties()[User.USER_NAME]);
		session.setAttribute("requestFromLending", "getCodeBookFromUserForPayBook");
		//Send redirect to BookItemController
		
		response.sendRedirect("BookItemController");
	}
}
