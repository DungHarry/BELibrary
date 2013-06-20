package harry.bel.servlets;

import harry.bel.objects.BookItem;
import harry.bel.objects.Lending;
import harry.bel.objects.MySQLConnector;
import harry.bel.objects.Reservation;
import harry.bel.objects.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

/**
 * Servlet implementation class LendingController
 */
@WebServlet("/LendingController")
public class LendingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MySQLConnector connection = new MySQLConnector();
	Lending lendingObject = new Lending(this.connection.getConnector());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LendingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse ddresponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Set character encoding
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//Declare for some variable in this function
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		//Get type of request
		
		String typeRequest = (String) session.getAttribute("typeRequestToLendingController");
		String placingBook = request.getParameter("n_submitToLendingBookController");
		
		if((placingBook != null)) {
			typeRequest = "";
		} else {
			placingBook = "";
		}
		
		switch(typeRequest) {
			case "isAvaiableFromBookTitle": {

				this.displayPlacingBook(request, response, session, dispatcher);
			}	break;
			
			case "returnGetBookItemFromBookItemController": {
	
				this.checkPlacingBook(request, response, session, dispatcher);
			}	break;
			
			case "requestToCreateLendingBookFromBookTitleController": {
				//Create lending record
				
				this.createLendingBook(request, response, session, dispatcher);
			}	break;
			
			case "deleteRequestFromPayBook": {
				//Delete record from Lending
				
				this.deleteLendinBookRecord(request, response, session, dispatcher);
			}	break;
			
			default: {
				
			}	break;
		}
		
		switch(placingBook) {
			case "getBookPlacingInformations": {
				//Call to checkPlacingInformation
			
				this.checkPlacingInformation(request, response, session, dispatcher);
			}	break;
			
			case "Đặt sách": {
				//Call to function placing book
				
				this.createPlacingBook(request, response, session, dispatcher);
			}	break;
			
			case "submitGetInfsPayBookPart": {
				this.getLendingInformations(request, response, session, dispatcher);
			}
			
			default: {
				
			}	break;
		}
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Set character encoding
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//Create some variables in this fucntion
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		String typeRequest = request.getParameter("n_submitToLendingBookController");
		
		switch(typeRequest) {
			case "Đồng ý cho mượn": {
				//Call to convert to lending
			
				this.convertToLendingBook(request, response, session, dispatcher);
			}	break;
			
			default: {
				
			}	break;
		}
		
		return;
	}
	
	private void createPlacingBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get parameter from client
		
		String userId = request.getParameter("userIdPlaceBookPart");
		String bookItemCode = request.getParameter("n_textPlaceBookPart");
		//Get attribute from session
		
		session.setAttribute("userIdPlaceBookControllerFirst", userId);

		session.setAttribute("bookItemCodeFromPlacingBook", bookItemCode);
		session.setAttribute("requestFromLending", "getBookItemIdFromPlacingBook");

		response.sendRedirect("BookItemController");
	}
	
	private void checkPlacingBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Check book and user
		
		String bookItemId = (String) session.getAttribute("bookItemIdFromController");
		String userId = (String) session.getAttribute("userIdPlaceBookControllerFirst");
		
		if(!this.lendingObject.checkForLending(userId, Lending.ERROR_OVER_15_DAYS, bookItemId) || !this.lendingObject.checkForLending(userId, Lending.ERROR_GREATER_THAN_10_BOOKS, bookItemId) || !this.lendingObject.checkForLending(userId, Lending.ERROR_PLACING_SAME_BOOK, bookItemId)) {
			//Set some attributes in this case
		
			session.setAttribute("styleErrorPlaceBook", "display: block;");
			session.setAttribute("styleSuccessResutPlaceBook", "display: none;");
		
			if(!this.lendingObject.checkForLending(userId, Lending.ERROR_OVER_15_DAYS, bookItemId)) {
				session.setAttribute("errorOver15Days", "display: block;");
			} else {
				session.setAttribute("errorOver15Days", "display: none;");
			}
		
			if(!this.lendingObject.checkForLending(userId, Lending.ERROR_GREATER_THAN_10_BOOKS, bookItemId)) {
				session.setAttribute("errorOver10BooksLending", "display: block;");
			} else {
				session.setAttribute("errorOver10BooksLending", "display: none;");
			}
		
			if(!this.lendingObject.checkForLending(userId, Lending.ERROR_PLACING_SAME_BOOK, bookItemId)) {
				session.setAttribute("errorSameBook", "display: block;");
			} else {
				session.setAttribute("errorSameBook", "display: none;");
			}
			//Create dispatcher and forward to main.jsp
		
			dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
			dispatcher.forward(request, response);
			
			return;
		}
		else {
			//Set some store data to session
		
			session.setAttribute("requestFromBookItem", "requestCheckForPlacingBook");
			session.setAttribute("valueUserIdStore", userId);
			session.setAttribute("valueBookItemIdStore", bookItemId);
			session.setAttribute("bookItemIdAcceptRegisterBeforeBook", bookItemId);
			//Send direct to check avaialbe for placing book
		
			response.sendRedirect("BookTitleController");
			
			return;
		}
	}
	
	private void displayPlacingBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws IOException {
		//Set display to show informations of placing book
		
		session.setAttribute("styleErrorPlaceBook", "display: none;");
		session.setAttribute("styleSuccessResutPlaceBook", "display: block;");
		session.setAttribute("errorOver15Days", "display: none;");
		session.setAttribute("errorOver10BooksLending", "display: none;");
		session.setAttribute("errorSameBook", "display: none;");
		session.setAttribute("errorNotAvaiablePlaceBook", "display: none;");
		session.setAttribute("errorNotExistBookItem", "display: none;");
		//Create array for value book
		
		String[] valueProperties = new String[Lending.LENDING_SIZE_PROPERTIES];
		//Initialize for valueProperties array
		
		for(int i = 0; i < Lending.LENDING_SIZE_PROPERTIES; i ++) {
			valueProperties[i] = new String("");
		}
		//Set some value for valueProperties array
		
		valueProperties[Lending.LENDING_USER_ID] = (String) session.getAttribute("valueUserIdStore");
		valueProperties[Lending.LENDING_BOOK_ITEM_ID] = (String) session.getAttribute("valueBookItemIdStore");
		
		this.lendingObject.insertLendingRecord(valueProperties);
		
		this.lendingObject.getValueProperties(valueProperties);
		//Set some attributes
		
		session.setAttribute("codePlacingResultPlaceBook", this.lendingObject.getValueProperties()[Lending.LENDING_CODE]);
		session.setAttribute("codeBookResultPlaceBook", this.lendingObject.getValueProperties()[Lending.LENDING_BOOK_ITEM_ID]);
		session.setAttribute("timePlacingResultPlaceBook", this.lendingObject.getValueProperties()[Lending.LENDING_TIME]);
		
		session.setAttribute("requestFromLending", "placingBookFromLendingController");
		//Redirect to BookItemController
		
		response.sendRedirect("BookItemController");
		
		return;
	}
	
	private void createLendingBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Declare some variables in this function
		
		String[] valueProperties = new String[Lending.LENDING_SIZE_PROPERTIES];
		//Initialize for valueProperties array
		
		for(int i = 0; i < valueProperties.length; i ++) {
			valueProperties[i] = new String("");
		}
		//Get some attributes needed from session
		
		String userId = (String) session.getAttribute("userIdNoRegisterLendingBook");
		String bookItemId = (String) session.getAttribute("bookItemIdFromBookItemController");
		//Check for rules

		if(!this.lendingObject.checkForLending(userId, Lending.ERROR_GREATER_THAN_10_BOOKS, bookItemId) || !this.lendingObject.checkForLending(userId, Lending.ERROR_OVER_15_DAYS, bookItemId) || !this.lendingObject.checkForLending(userId, Lending.ERROR_PLACING_SAME_BOOK, bookItemId)) {
			//Set some attributes to display error to user
			
			session.setAttribute("styleSuccessLendingBookNotRegister", "display: none;");
			session.setAttribute("styleResultNoRegisterLendingBookContent", "display: block;");
			session.setAttribute("styleErrorUserNoRegisterLendingBookContent", "display: none;");
			session.setAttribute("styleNotFoundBookItemIdNoRegisterBeforeLendingBook", "display: none;");
			session.setAttribute("styleErrorNotAvaiableToLendingNoRegister", "display: none;");
			session.setAttribute("styleErrorBookItemNoRegisterLendingBook", "display: none;");
			session.setAttribute("styleInfringeRolesNotRegisterLendingBook", "display: block;");
			session.setAttribute("errorOver15DaysNotRegisterLendingBook", "display: none;");
			session.setAttribute("errorOver10BooksNotRegisterLendingBook", "display: none;");
			session.setAttribute("errorSameBookNotRegisterLendingBook", "display: none;");
			
			if(!this.lendingObject.checkForLending(userId, Lending.ERROR_GREATER_THAN_10_BOOKS, bookItemId)) {
				session.setAttribute("errorOver10BooksNotRegisterLendingBook", "display: block;");
			}
			
			if(!this.lendingObject.checkForLending(userId, Lending.ERROR_OVER_15_DAYS, bookItemId)) {
				session.setAttribute("errorOver15DaysNotRegisterLendingBook", "display: block;");
			}
			
			if(!this.lendingObject.checkForLending(userId, Lending.ERROR_PLACING_SAME_BOOK, bookItemId)) {
				session.setAttribute("errorSameBookNotRegisterLendingBook", "display: block;");
			}
			//Create dispatcher and forward to main.jsp
			
			dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
			dispatcher.forward(request, response);
			
			return;
		} else {
			//Set some value of elements in value properties
			
			valueProperties[Lending.LENDING_BOOK_ITEM_ID] = bookItemId;
			valueProperties[Lending.LENDING_USER_ID] = userId;
			valueProperties[Lending.LENDING_STATUS] = "1";
			//Create Lending
			
			this.lendingObject.insertLendingRecord(valueProperties);
			
			this.lendingObject.getValueProperties(valueProperties);
			//Set attributes to display to user
			
			session.setAttribute("styleSuccessLendingBookNotRegister", "display: block;");
			session.setAttribute("styleResultNoRegisterLendingBookContent", "display: block;");
			session.setAttribute("styleErrorUserNoRegisterLendingBookContent", "display: none;");
			session.setAttribute("styleNotFoundBookItemIdNoRegisterBeforeLendingBook", "display: none;");
			session.setAttribute("styleErrorNotAvaiableToLendingNoRegister", "display: none;");
			session.setAttribute("styleErrorBookItemNoRegisterLendingBook", "display: none;");
			session.setAttribute("styleInfringeRolesNotRegisterLendingBook", "display: none;");
			session.setAttribute("codeLendBookNoRegisterLendingBook", this.lendingObject.getValueProperties()[Lending.LENDING_CODE]);
			session.setAttribute("timeLendBookNoRegisterLendingBook", this.lendingObject.getValueProperties()[Lending.LENDING_TIME]);
			session.setAttribute("requestFromBookItem", "changeLendingInformationBookTitle");
			session.setAttribute("codeBookNoRegisterLendingBook", (String) session.getAttribute("bookCodeFromUserNoRegisterBefore"));
			//Send redirect to BookTitleController
			
			response.sendRedirect("BookTitleController");
			
			return;
		}
	}
	
	private void convertToLendingBook(HttpServletRequest request, HttpServletResponse response,HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get parameter from client
		
		String codePlacingBook = request.getParameter("n_codeCheckAcceptRegisterBook");
		String bookItemId = request.getParameter("n_bookItemIdAcceptRegisterBeforeBook");
		
		this.lendingObject.getValueProperties()[Lending.LENDING_CODE] = codePlacingBook;
		//Update information of Lending book
		
		this.lendingObject.updateEachProperties("1", Lending.LENDING_STATUS);
		this.lendingObject.updateEachProperties("current_timestamp", Lending.LENDING_TIME);
		//Set attribute to display result to user
		
		session.setAttribute("styleNotifyResultAcceptLendingBook", "display: block;");
		session.setAttribute("requestFromBookItem", "setNumLendingBookTitle");
		session.setAttribute("setBookItemIdFromLendingController", bookItemId);
		//Set dispatcher and forward to main.jsp
		
		response.sendRedirect("BookTitleController");
	}
	
	private void checkLendingInformation(HttpServletRequest request, HttpServletResponse response,HttpSession session, RequestDispatcher dispatcher) {
		//Get parameter from client
		
	}
	
	private void checkPlacingInformation(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get parameter from client
		
		String codePlacingBook = request.getParameter("n_textCheckAcceptRegisterBookContent");
		
		this.lendingObject.getValueProperties()[Lending.LENDING_STATUS] = "";
		
		this.lendingObject.getValueProperties(codePlacingBook, Lending.LENDING_CODE);
		
		if(this.lendingObject.getValueProperties()[Lending.LENDING_STATUS].equals("") || this.lendingObject.getValueProperties()[Lending.LENDING_STATUS].equals("1")) {
			//Set some attributes in this function
			
			session.setAttribute("styleNotFoundCheckAcceptRegisterBook", "display: block;");
			session.setAttribute("styleResultFoundCheckAcceptRegisterBook", "display: none;");
			//Create dispatcher and forward to main.jsp
			
			dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
			dispatcher.forward(request, response);
			
			return;
		}
		else {
			//Set some attributes to display results to user
			
			session.setAttribute("styleNotFoundCheckAcceptRegisterBook", "display: none;");
			session.setAttribute("styleResultFoundCheckAcceptRegisterBook", "display: block;");
			session.setAttribute("styleNotifyResultAcceptLendingBook", "display: none;");
			session.setAttribute("codeCheckAcceptRegisterBook", this.lendingObject.getValueProperties()[Lending.LENDING_CODE]);
			session.setAttribute("userIdCheckAcceptRegisterBook", this.lendingObject.getValueProperties()[Lending.LENDING_USER_ID]);
			session.setAttribute("timePlacingCheckAcceptRegisterBook", this.lendingObject.getValueProperties()[Lending.LENDING_TIME]);
			session.setAttribute("bookItemIdFromCheckPlacingInformation", this.lendingObject.getValueProperties()[Lending.LENDING_BOOK_ITEM_ID]);
			session.setAttribute("requestFromLending", "getBookItemIdRegisterBefore");
			//Send redirect to BookItemController
			
			response.sendRedirect("BookItemController");
			
			return;
		}
	}
	
	private void getLendingInformations(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get code lending book from client
		
		String codeLedingBook = request.getParameter("n_textCodeLendingPayBookPart");
		this.lendingObject.getValueProperties()[Lending.LENDING_STATUS] = "";
		//Call function to get value properties
		
		this.lendingObject.getValueProperties(codeLedingBook, Lending.LENDING_CODE);
		
		if(this.lendingObject.getValueProperties()[Lending.LENDING_STATUS].equals("")) {
			//Set attribute to display error to user
			
			session.setAttribute("styleErrorPayBookPart", "display: block;");
			session.setAttribute("styleSuccessPayBookPart", "display: none;");
			session.setAttribute("styleResponseFromPayBookPart", "display: none;");
			//Create dispatcher and forward to main.jsp
			
			dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
			dispatcher.forward(request, response);
			
			return;
		} else if(this.lendingObject.getValueProperties()[Lending.LENDING_STATUS].equals("1")) {
			//Set attributes to display to user
			
			session.setAttribute("codeLendingBookPayBookPart", this.lendingObject.getValueProperties()[Lending.LENDING_CODE]);
			session.setAttribute("userIdPayBookPart", this.lendingObject.getValueProperties()[Lending.LENDING_USER_ID]);
			session.setAttribute("bookItemIdForPayBookPart", this.lendingObject.getValueProperties()[Lending.LENDING_BOOK_ITEM_ID]);
			session.setAttribute("timeLendingPayBookPart", this.lendingObject.getValueProperties()[Lending.LENDING_TIME]);
			
			session.setAttribute("requestToUserController", "requestGetUserNameFromLendingController");
			//Send redirect to UserController
		
			response.sendRedirect("UserController");
			
			return;
		}
	}
	
	private void deleteLendinBookRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws IOException {
		//Get attribute from session variable
		
		String codeLendingBook = (String) session.getAttribute("codeLendingBookFromReservation");
		//Delete record from Lending table in database
		
		this.lendingObject.deleteLendingRecord(codeLendingBook);
		//Set attribute for session
		
		session.setAttribute("requestFromBookItem", "requestUpdateBookTitlePayBook");
		//Send redirect to BookTitleController
		
		response.sendRedirect("BookTitleController");
	}
}
