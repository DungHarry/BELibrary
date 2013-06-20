package harry.bel.servlets;

import harry.bel.objects.BookTitle;
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
 * Servlet implementation class PlacingController
 */
@WebServlet("/ReservationController")
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MySQLConnector connection = new MySQLConnector();
    Reservation reservation = new Reservation(this.connection.getConnector());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Set character encoding for request and response
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//Get parameter from client
		
		String requestToReservation = request.getParameter("n_submitToReservationController");
		//Create some variable in this function
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		switch(requestToReservation) {
			case "submitAcceptPayBookPart": {
				this.insertReservationRecords(request, response, session, dispatcher);
			}	break;
			
			default: {
				
			}	break;
		}
	}
	
	private void getUsers(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) {
		
	}
	
	private void getBooks(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) {
		
	}
	
	private void insertReservationRecords(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws IOException {
		//Get some parameters in this function
		
		String codeLendingBook = request.getParameter("n_codeLendingAcceptPayBookPart");
		String userIdLendingBook = request.getParameter("n_userIdAcceptPayBookPart");
		String bookTitleId = request.getParameter("n_bookTitleIdAcceptPayBookPart");
		String timeBeginLendingBook = request.getParameter("n_timeLendingAcceptPayBookPart");
		//Create array to insert reservation record
		
		String[] valueProperties = new String[Reservation.RESERVATION_SIZE_PROPERTIES];
		//Initialize for valueProperties
		
		for(int i = 0; i < Reservation.RESERVATION_SIZE_PROPERTIES; i ++) {
			valueProperties[i] = new String("");
		}
		//Set value of some elements in array valueProperties
		
		valueProperties[Reservation.RESERVATION_BOOK_TITLE_ID] = bookTitleId;
		valueProperties[Reservation.RESERVATION_TIME_BEGIN] = timeBeginLendingBook;
		valueProperties[Reservation.RESERVATION_USER_ID] = userIdLendingBook;
		valueProperties[Reservation.RESERVATION_TIME_FINISH] = "current_timestamp";
		//Create a record to Reservation table in database
		
		this.reservation.insertReservation(valueProperties);
		//Set value of reservation time finish to get valueProperties for reservation object
		
		valueProperties[Reservation.RESERVATION_TIME_FINISH] = "";
		this.reservation.getValueProperties(valueProperties);
		//Check for infringement and update note again
		
		if(this.reservation.isInfringement()) {
			//Update note again
			
			this.reservation.setEachProperty("Trả sách đúng thời hạn", Reservation.RESERVATION_NOTE);
		} else {
			//Update note again
			
			this.reservation.setEachProperty("Trả sách muộn theo quy định", Reservation.RESERVATION_NOTE);
		}
		
		this.reservation.setEachProperty(timeBeginLendingBook, Reservation.RESERVATION_TIME_BEGIN);
		//Set some attribute to session variable
		
		session.setAttribute("codeLendingBookFromReservation", codeLendingBook);
		session.setAttribute("bookTitleIdFromReservation", bookTitleId);
		session.setAttribute("typeRequestToLendingController", "deleteRequestFromPayBook");
		//Send redirect to LendingController
		
		response.sendRedirect("LendingController");
	}
}
