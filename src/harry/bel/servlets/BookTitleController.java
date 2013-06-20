package harry.bel.servlets;

import harry.bel.objects.BookItem;
import harry.bel.objects.BookTitle;
import harry.bel.objects.MySQLConnector;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BookTitleManager
 */
@WebServlet("/BookTitleController")
public class BookTitleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	MySQLConnector connection = new MySQLConnector();
	BookTitle bookTitle = new BookTitle(this.connection.getConnector());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookTitleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Set character encoding
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//Declare for some variables in this function
				
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		String requestChecker = "";
		requestChecker = (String) session.getAttribute("requestFromBookItem");
		//Check to sure type of request
		
		switch (requestChecker) {
			case "insertEntry": {
				//Insert a record to database
				
				this.insertEntry(request, response, session, dispatcher);
			}	break;
			
			case "getBookTitleInformation": {
				//Get number of book in database
				
				this.getSumBookItem(request, response, session, dispatcher);
			}	break;
			
			case "updateCountBookTitleInformation": {
				//Update number of book in database
				
				this.updateInformations(request, response, session, dispatcher);
			}	break;
			
			case "chekBookByCodeBook": {
				//Get informations for check book part
				
				this.getForCheckBook(request, response, session, dispatcher);
			}	break;
			
			case "requestCheckForPlacingBook": {
				//Check for placing book
				
				this.handleRequestPlaceBook(request, response, session, dispatcher);
			}	break;
			
			case "checkAvaibleBookItemNoRegisterBefore": {
				//Call to function to check avaiable of book 
				
				this.checkAvaiableBookItem(request, response, session, dispatcher);
			}	break;
			
			case "changeLendingInformationBookTitle": {
				//Call to function to change lending book information
				
				this.changePlacingInformation(request, response, session, dispatcher);
			}	break;
			
			case "setNumLendingBookTitle": {
				this.setNumLendingRegisterBefore(request, response, session, dispatcher);
			}	break;
			
			case "getBookTitleIdForPayBookController": {
				this.getBookTitleCodeForPayBook(request, response, session, dispatcher);
			}	break;
			
			case "requestUpdateBookTitlePayBook": {
				this.updateForPayBook(request, response, session, dispatcher);
			} 	break;

			default: {
				
			}	break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	private void updateInformations(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get attributes from session
		
		String bookItemId = (String) session.getAttribute("bookItemIdFromBookItemController");
		//Get book title informations through bookItemId
		
		this.bookTitle.getValueProperties(bookItemId, BookTitle.BOOK_TITLE_BI_ID);
		//Get value number book item from user
		
		this.bookTitle.getValueProperties()[BookTitle.BOOK_TITLE_COUNT] = (String) session.getAttribute("n_numItemEditBookManager");
		//Check and control to GUI
		
		if(this.bookTitle.isUpdateCountBook()) {
			this.bookTitle.updateValueProperties(this.bookTitle.getValueProperties()[BookTitle.BOOK_TITLE_COUNT], BookTitle.BOOK_TITLE_COUNT);
			//Update display number of book again
			
			session.setAttribute("numberItemEditUpdateBookManager", this.bookTitle.getValueProperties()[BookTitle.BOOK_TITLE_COUNT]);
			session.setAttribute("styleUpdateNumberBookItemEditUpdateBookManager", "display: none;");
		}
		else {
			session.setAttribute("styleUpdateNumberBookItemEditUpdateBookManager", "display: block;");
		}
		//Create dispatcher and forward to main.jsp
		
		dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertEntry(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get attributes from BookItemController
		
		String[] bookTitleInformations = (String[]) session.getAttribute("bookTitleInformations");
		this.bookTitle.insertBookTitle(bookTitleInformations);
		
		dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
	}
	
	private void getSumBookItem(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		String bookItemId = (String) session.getAttribute("bookItemId");
		
		this.bookTitle.getValueProperties(bookItemId, BookTitle.BOOK_TITLE_BI_ID);
		//Set attributes
		
		session.setAttribute("numberItemEditUpdateBookManager", this.bookTitle.getValueProperties()[BookTitle.BOOK_TITLE_COUNT]);
		//Create dispatcher
		
		dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
	}
	
	private void getForCheckBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get value properties by BookItemId
		
		String bookItemId = (String) session.getAttribute("bookItemIdFromBookItemController");
		this.bookTitle.getValueProperties(bookItemId, BookTitle.BOOK_TITLE_BI_ID);
		//Set attributes to display informations to screen
		
		if(this.bookTitle.isAvaiable(bookItemId) == BookTitle.BOOK_ITEM_EXIST) {
			session.setAttribute("statusBookCheckBookPart", "Có sẵn");
		}
		else {
			session.setAttribute("statusBookCheckBookPart", "Không có sẵn");
		}
		
		session.setAttribute("countBookCheckBookPart", this.bookTitle.getValueProperties()[BookTitle.BOOK_TITLE_COUNT]);
		session.setAttribute("numPlacingBookCheckBookPart", this.bookTitle.getValueProperties()[BookTitle.BOOK_TITLE_NUM_PLACING]);
		session.setAttribute("numLendingBookCheckBookPart", this.bookTitle.getValueProperties()[BookTitle.BOOK_TITLE_NUM_LENDING]);
		//Forward to main.jsp
		
		dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
	}
	
	private void handleRequestPlaceBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get book item id from session
		
		String bookItemId = (String) session.getAttribute("valueBookItemIdStore");
		int isAvaiable = this.bookTitle.isAvaiable(bookItemId);
		//Check and control display to client 
		
		if(isAvaiable == BookTitle.BOOK_ITEM_EXIST) {
			//Set attribute
			
			session.setAttribute("typeRequestToLendingController", "isAvaiableFromBookTitle");
			//Change information placing book
			
			this.bookTitle.placingBook(bookItemId);
			//Send redirect to LendingBookController
			
			response.sendRedirect("LendingController");
		} else {
			//Set some attributes here
			
			session.setAttribute("styleErrorPlaceBook", "display: block;");
			session.setAttribute("styleSuccessResutPlaceBook", "display: none;");
			session.setAttribute("errorOver15Days", "display: none;");
			session.setAttribute("errorOver10BooksLending", "display: none;");
			session.setAttribute("errorSameBook", "display: none;");
			
			if(isAvaiable == BookTitle.ERROR_NOT_AVAIABLE) {
				session.setAttribute("errorNotExistBookItem", "display: none;");
				session.setAttribute("errorNotAvaiablePlaceBook", "display: block;");
			} else if(isAvaiable == BookTitle.ERROR_NOT_EXIST) {
				session.setAttribute("errorNotExistBookItem", "display: block;");
				session.setAttribute("errorNotAvaiablePlaceBook", "display: none;");
			}
			
			//Create dispatcher and forward to main.jsp
			
			dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private void checkAvaiableBookItem(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get attributes from UserController
		
		String bookItemId = (String) session.getAttribute("bookItemIdFromBookItemController");
		//Check for bookItemId
		
		if(this.bookTitle.isAvaiable(bookItemId) == BookTitle.ERROR_NOT_AVAIABLE) {
			//Set attributes to display error to user
			
			session.setAttribute("styleSuccessLendingBookNotRegister", "display: none;");
			session.setAttribute("styleResultNoRegisterLendingBookContent", "display: block;");
			session.setAttribute("styleErrorUserNoRegisterLendingBookContent", "display: none;");
			session.setAttribute("styleNotFoundBookItemIdNoRegisterBeforeLendingBook", "display: none;");
			session.setAttribute("styleErrorNotAvaiableToLendingNoRegister", "display: block;");
			session.setAttribute("styleErrorBookItemNoRegisterLendingBook", "display: block;");
			session.setAttribute("styleInfringeRolesNotRegisterLendingBook", "display: none;");
			//Send create dispatcher and forward to main.jsp
			
			dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
			dispatcher.forward(request, response);
		} else {
			//Set attribute to session variable
			
			session.setAttribute("typeRequestToLendingController", "requestToCreateLendingBookFromBookTitleController");
			//Send redirect to LendingController
			
			response.sendRedirect("LendingController");
		}
	}
	
	private void changePlacingInformation(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get bookItemId from session
		
		String bookItemId = (String) session.getAttribute("bookItemIdFromBookItemController");
		//Call to function and change lending information
		
		this.bookTitle.lendingBook(bookItemId, BookTitle.LENDING_NO_REGISTER);
		//Create dispatcher and forward to main.jsp
		
		dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
	}
	
	private void setNumLendingRegisterBefore(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get bookItemId
		
		String bookItemId = (String) session.getAttribute("setBookItemIdFromLendingController");
		//Set number of lending book
		
		this.bookTitle.lendingBook(bookItemId, BookTitle.LENDING_REGISTER);
		//Create dispatcher and forward to main.jsp
		
		dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
	}
	
	private void getBookTitleCodeForPayBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get bookItemId from session variable
		
		String bookItemId = (String) session.getAttribute("bookItemIdForPayBookPart");
		//Get value properties through bookItemId
		
		this.bookTitle.getValueProperties(bookItemId, BookTitle.BOOK_TITLE_BI_ID);
		//Set attribute for something
		
		session.setAttribute("bookTitleIdPayBookPart", this.bookTitle.getValueProperties()[BookTitle.BOOK_TITLE_ID]);
		
		session.setAttribute("styleErrorPayBookPart", "display: none;");
		session.setAttribute("styleSuccessPayBookPart", "display: block;");
		session.setAttribute("styleResponseFromPayBookPart", "display: none;");
		//Create dispatcher and forward to main.jsp
		
		dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
	}
	
	private void updateForPayBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get bookTitleId from session variable
		
		String bookTitleId = (String) session.getAttribute("bookTitleIdFromReservation");
		//Get valueProperties for bookTitle
		
		this.bookTitle.getValueProperties(bookTitleId, BookTitle.BOOK_TITLE_ID);
		//Update numLending and numPlacing book again
		
		this.bookTitle.payingBook(this.bookTitle.getValueProperties()[BookTitle.BOOK_TITLE_BI_ID]);
		//Set attribute to display result to user
		
		session.setAttribute("styleErrorPayBookPart", "display: none;");
		session.setAttribute("styleSuccessPayBookPart", "display: none;");
		session.setAttribute("styleResponseFromPayBookPart", "display: block;");
		//Create dispatcher and forward to main.jsp
		
		dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
	}
}
