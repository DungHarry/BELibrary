package harry.bel.servlets;

import harry.bel.objects.BookItem;
import harry.bel.objects.BookTitle;
import harry.bel.objects.MySQLConnector;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class BookItemManager
 */
@WebServlet("/BookItemController")
public class BookItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Declare for some variables in this class
	
	MySQLConnector connector = new MySQLConnector();
	BookItem bookItem = new BookItem(connector.getConnector());
	/**
     * @see HttpServlet#HttpServlet()
     */
    public BookItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Set charset encoding
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//Declare for some variable as session and dispatcher
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		//Get value to sure type of request
		
		String requestGetInformation = request.getParameter("submitTypeGetEditBookManager");
		String requestFromLendingBook = (String) session.getAttribute("requestFromLending");
		
		if(requestGetInformation == null) {
			requestGetInformation = "";
		} 
		
		if(requestFromLendingBook == null) {
			requestFromLendingBook = "";
		}
		
		switch(requestGetInformation) {
			case "submitSearchBook": {
				//Call to searchBook function
			
				this.searchBook(request, response, session, dispatcher);
			}	break;

			default: {
		
			}	break;
		}
		
		switch(requestFromLendingBook) {
			case "placingBookFromLendingController": {
				//Handle request for set name of book placing
		
				this.handleRequestPlacingBook(request, response, session, dispatcher);
			}	break;
	
			case "getBookItemIdFromPlacingBook": {
				//Get book item id for placing book
		
				this.getBookItemIdForPlacingBook(request, response, session, dispatcher);
			}	break;
	
			case "getBookItemIdRegisterBefore": {
				//Call to function get book item id
		
				this.getBookItemCodeForCheckPlacingBook(request, response, session, dispatcher);
			}	break;
	
				case "checkExistenceBookFromUser": {
				//Call function check existence of book item
		
				this.checkExistenceBookItem(request, response, session, dispatcher);
			}	break;
	
			case "getCodeBookFromUserForPayBook": {
				//Call to function to get book code
		
				this.getCodeBookForPayBook(request, response, session, dispatcher);
			} 	break;

			default: {
		
			}	break;
		}
		
		doPost(request, response);
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Set character encoding
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		//Get some variable to check to sure type of request
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		String requestGetInformation = request.getParameter("submitTypeGetEditBookManager");
		//Check for sure type of post method
		
		if(requestGetInformation == null) {
			requestGetInformation = "";
			this.insertBookItem(request, response, session, dispatcher);
		}
		
		switch(requestGetInformation) {
			case "Kiểm tra": {
				//Get informations for check book by code book
		
				this.checkBookByCodeBook(request, response, session, dispatcher);
			}	break;
		
			case " Xem ": {
				//Get book informations
				
				this.getBookItemInformation(request, response, session, dispatcher);
			}	break;
			
			case "Cập nhật": {
				//Update book information for manager
				
				this.updateBookItem(request, response, session, dispatcher);
			}	break;
			
			default: {
				
			}	break;
		}
		
		return;
	}
	//Declare for method to insert informations to database
	
	private void insertBookItem(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Declare for some variables in this function
		
		File file;
		int maxFileSize = 50000 * 1024;
		int maxDiskSize = 50000 * 1024;
		String[] valueInformations = new String[BookItem.BOOK_ITEM_SIZE_PROPERTES];
		String[] bookTitleInformations = new String[BookTitle.BOOK_TITLE_SIZE_PROPERTIES];
		String imageName = "";
		String fieldName;
		String fileName;
		
		for(int i = 0; i < BookItem.BOOK_ITEM_SIZE_PROPERTES; i ++) {
			valueInformations[i] = "";
		}
		
		for(int i = 0; i < BookTitle.BOOK_TITLE_SIZE_PROPERTIES; i ++) {
			bookTitleInformations[i] = "";
		}
		
		//Get content type of request
		
		String contentType = request.getContentType();
		//Check for entype have format of multipart/form-data
		
		if((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
			//Create new disk file system factory
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//Set maximum file size wont stored in system
			
			factory.setSizeThreshold(maxDiskSize);
			//Set directory to store file has size is larger maxDiskSize
			
			factory.setRepository(new File(BookItem.DIRECTORY_FILE_TEMPLE));
			//Create file upload handler
			
			ServletFileUpload fileUploader = new ServletFileUpload(factory);
			//Set maximum size of file to upload
			
			fileUploader.setSizeMax(maxFileSize);
			//Get and handle to upload file
			
			try {
				//Parse request to get file item
				
				List<FileItem> fileItems = fileUploader.parseRequest(request);
				//Processed file uploaded item
				
				Iterator<FileItem> looper = fileItems.iterator();
				//Get file to upload
				while(looper.hasNext()) {
					//Create FileItem

					FileItem fileItem = (FileItem) looper.next();
					//Check to make sure that item isn't file field
					
					if(fileItem.isFormField()) {
						fieldName = fileItem.getFieldName();
						
						switch(fieldName) {
							case "n_titleBookContentManager": {
								valueInformations[BookItem.BOOK_ITEM_TITLE] = fileItem.getString("UTF-8").trim().replace("\"", "\\\""); 
							}	break;
							
							case "n_authorBookContentManager": {
								valueInformations[BookItem.BOOK_ITEM_AUTHOR] = fileItem.getString("UTF-8").trim().replace("\"", "\\\"");
							}	break;
							
							case "n_symbolBookContentManager": {
								valueInformations[BookItem.BOOK_ITEM_TYPE_SYMBOL] = fileItem.getString("UTF-8").trim().replace("\"", "\\\"");
							}	break;
							
							case "n_explainBookContentManager": {
								valueInformations[BookItem.BOOK_ITEM_TYPE_EXPLAIN] = fileItem.getString("UTF-8").trim().replace("\"", "\\\"");
							}	break;

							case "n_imageNameEntireBookManager": {
								imageName = fileItem.getString("UTF-8").trim().replace("\"", "\\\"");
							}	break;
							
							case "n_summaryBookContentManager": {
								valueInformations[BookItem.BOOK_ITEM_SUMMARY] = fileItem.getString("UTF-8").trim().replace("\"", "\\\"");
							}	break;
							
							case "n_yearPublicationBookContentManager": {
								valueInformations[BookItem.BOOK_ITEM_DAY_PUBPLICATION] = fileItem.getString("UTF-8").trim().replace("\"", "\\\"");
							}	break;
							
							case "n_monthPublicationBookContentManager": {
								valueInformations[BookItem.BOOK_ITEM_DAY_PUBPLICATION] += "-" + fileItem.getString("UTF-8").trim().replace("\"", "\\\"");
							}	break;
							
							case "n_datePublicationBookContentManager": {
								valueInformations[BookItem.BOOK_ITEM_DAY_PUBPLICATION] += "-" + fileItem.getString("UTF-8").trim().replace("\"", "\\\"");
							}	break;
							
							case "n_publisherBookManager": {
								valueInformations[BookItem.BOOK_ITEM_PUBLISHER] = fileItem.getString("UTF-8").trim().replace("\"", "\\\"");
							}	break;
							
							case "n_sumBookItemBookManager": {
								bookTitleInformations[BookTitle.BOOK_TITLE_COUNT] = fileItem.getString("UTF-8").trim().replace("\"", "\\\"");
							}	break;
							
							default: {
								
							}	break;
						}
					} else{
						//Get file name
						
						fileName = fileItem.getName();
						//Create file to directory needed with name as parameter
						
						file = new File(BookItem.DIRECTORY_FILE_UPLOAD + imageName + fileName.substring(fileName.lastIndexOf(".")));
						
						valueInformations[BookItem.BOOK_ITEM_IMAGE] = "./BELibrary/Images/" + imageName + fileName.substring(fileName.lastIndexOf("."));
						
						fileItem.write(file);
					}
				}
				
				valueInformations[BookItem.BOOK_ITEM_CODE] = "temple";
				//Insert data to database
				
				this.bookItem.insertBookItem(valueInformations);
				//Get information after insert book item into database
				
				this.bookItem.getValueInformations(valueInformations);
				
				this.bookItem.updateBookItem(this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_TYPE_SYMBOL] + this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_ID], BookItem.BOOK_ITEM_CODE);
				//Set some information for bookTitleInformations
				
				bookTitleInformations[BookTitle.BOOK_TITLE_BI_ID] = this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_ID];
				//Set properties for session
				
				session.setAttribute("bookTitleInformations", bookTitleInformations);
				session.setAttribute("requestFromBookItem", "insertEntry");
				//Forward to main.jsp
				
				response.sendRedirect("BookTitleController");
				
				return;
			}	catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return;
	}
	
	private void getBookItemInformation(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get some information from request variable
		
		String idBookItem = request.getParameter("bookItemIdGetBookManager");
		String url = "/main.jsp";
		
		if(this.bookItem.getValueInformations(idBookItem, BookItem.BOOK_ITEM_CODE)) {
			//Set value for properties of edit book part
			
			session.setAttribute("imageBookResultLinkEditBookManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_IMAGE]);
			session.setAttribute("nameEditBookUpdateManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_TITLE]);
			session.setAttribute("codeEditBookUpdateManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_CODE]);
			session.setAttribute("authorEditBookUpdateManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_AUTHOR]);
			session.setAttribute("dayPublisherEditBookUpdateManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_DAY_PUBPLICATION]);
			session.setAttribute("publisherEditUpdateBookManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_PUBLISHER]);
			session.setAttribute("summaryEditUpdateBookManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_SUMMARY]);
			//Set style for display part edit book
			
			session.setAttribute("styleResultNotExistBookManager", "display: none;");
			session.setAttribute("styleResultExistBookManager", "display: block;");
			//Redirect to BookTitleController
			
			session.setAttribute("requestFromBookItem", "getBookTitleInformation");
			session.setAttribute("bookItemId", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_ID]);
			
			response.sendRedirect("BookTitleController");
			
			return;
		}
		else {
			//Set style for display part error
			
			session.setAttribute("styleResultNotExistBookManager", "display: block;");
			session.setAttribute("styleResultExistBookManager", "display: none;");
			
			dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
			return;
		}
	}
	
	private void updateBookItem(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Declare for some variables in this function
		
		String[] dataInput = new String[BookItem.BOOK_ITEM_SIZE_PROPERTES];
		//Initialize value for dataInput array
		
		for(int i = 0; i < BookItem.BOOK_ITEM_SIZE_PROPERTES; i ++) {
			dataInput[i] = new String("");
		}
		//Get value of parameters from client
		
		dataInput[BookItem.BOOK_ITEM_TITLE] = request.getParameter("n_titleUpdateBookManager");
		dataInput[BookItem.BOOK_ITEM_AUTHOR] = request.getParameter("n_authorEditBookUpdateManager");
		dataInput[BookItem.BOOK_ITEM_DAY_PUBPLICATION] = request.getParameter("n_dayPublicationDateUpdateManager");
		dataInput[BookItem.BOOK_ITEM_PUBLISHER] = request.getParameter("n_newPublisherEditBookManager");
		dataInput[BookItem.BOOK_ITEM_SUMMARY] = request.getParameter("n_summaryEditPartBookManager");
		//Update information of book item
		
		this.bookItem.updateBookItem(dataInput);
		//Update display again
		
		this.bookItem.getValueInformations(this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_ID], BookItem.BOOK_ITEM_ID);
		
		session.setAttribute("imageBookResultLinkEditBookManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_IMAGE]);
		session.setAttribute("nameEditBookUpdateManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_TITLE]);
		session.setAttribute("codeEditBookUpdateManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_CODE]);
		session.setAttribute("authorEditBookUpdateManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_AUTHOR]);
		session.setAttribute("dayPublisherEditBookUpdateManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_DAY_PUBPLICATION]);
		session.setAttribute("publisherEditUpdateBookManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_PUBLISHER]);
		session.setAttribute("summaryEditUpdateBookManager", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_SUMMARY]);
		
		session.setAttribute("bookItemIdFromBookItemController", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_ID]);
		session.setAttribute("requestFromBookItem", "updateCountBookTitleInformation");
		session.setAttribute("n_numItemEditBookManager", request.getParameter("n_numItemEditBookManager"));
		//Create redirect to BookTitleController
		
		response.sendRedirect("BookTitleController");
		
		return;
	}
	
	private void checkBookByCodeBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws IOException, ServletException {
		//Get code book from client
		
		String codeBookItem = request.getParameter("n_textBookCodeCheckBook");
		//Reset all value of bookItem
		
		for(int i = 0; i < BookItem.BOOK_ITEM_SIZE_PROPERTES; i ++) {
			this.bookItem.getValueProperties()[i] = "";
		}
		//Get book item information by code book
		
		this.bookItem.getValueInformations(codeBookItem, BookItem.BOOK_ITEM_CODE);
		
		if(this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_ID].equals("")) {
			//Set attributes of session variable to show error
			
			session.setAttribute("styleNotFoundCheckBook", "display: block;");
			session.setAttribute("styleResultFromCheckBook", "display: none;");
			//Forward to main.jsp
			
			dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
			dispatcher.forward(request, response);
			
			return;
		}
		else {
			//Set attribute to display informations to client
			
			session.setAttribute("srcImageCheckBookPart", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_IMAGE]);
			session.setAttribute("nameBookCheckBookPart", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_TITLE]);
			session.setAttribute("codeBookCheckBookPart", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_CODE]);
			session.setAttribute("typeExplainBookCheckBookPart", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_TYPE_EXPLAIN]);
			session.setAttribute("authorBookCheckBookPart", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_AUTHOR]);
			session.setAttribute("publisherBookCheckBookPart", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_PUBLISHER]);
			session.setAttribute("publicationDateBookCheckBookPart", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_DAY_PUBPLICATION]);
			session.setAttribute("summaryBookCheckBookPart", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_SUMMARY]);
			//Set value of some variables to display result
			
			session.setAttribute("styleNotFoundCheckBook", "display: none;");
			session.setAttribute("styleResultFromCheckBook", "display: block;");
			//Set bookItemId to get when sendRedirect to BookTitleController
			
			session.setAttribute("bookItemIdFromBookItemController", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_ID]);
			session.setAttribute("requestFromBookItem", "chekBookByCodeBook");
			//Redirect to BookTitleController
			
			response.sendRedirect("BookTitleController");
			
			return;
		}
	}
	
	private void searchBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get key words from client
		
		String keysWord = request.getParameter("searchBookForm");
		//Call to search function from bookItem
		
		String[][] resultSearchArray = this.bookItem.searchBook(keysWord);
		String innerHTML = "";
		int numResult = this.bookItem.getNumResultsSearch();
		
		if(resultSearchArray == null) {
			//Make innnerHTML
			
			innerHTML = "<p style='color: blue; font-style: italic; font-weight: bold; font-size: 20px;'>Không tìm thấy kết quả</p>";
		}
		else {
			//Make innerHTML string
			
			innerHTML = "<p style='color: blue; font-style: italic; font-weight: bold; font-size: 20px;'>Tìm thấy " + Integer.toString(numResult) + " kết quả</p>";
			
			innerHTML += "<hr />";
			
			for(int i = 0; i < numResult; i ++) {
				innerHTML += "<div style='margin-bottom: 5px; border-bottom: rgb(156, 176, 130) solid 2px;'>";
				innerHTML += "<table>";
				innerHTML += "<tbody>";
				innerHTML += "<tr>";
				innerHTML += "<td>";
				innerHTML += "<div style='margin: 0 5px; width: 285px; height: 370px; border-radius: 5px; border-right: rgb(156, 176, 130) solid 1px;'>";
				innerHTML += "<a>";
				innerHTML += "<img src='" + resultSearchArray[i][BookItem.BOOK_ITEM_IMAGE] + "' alt='" + resultSearchArray[i][BookItem.BOOK_ITEM_TITLE] + "' title='" + resultSearchArray[i][BookItem.BOOK_ITEM_TITLE] + "' width='285px' height='370px' />";
				innerHTML += "</a>";
				innerHTML += "</div>";
				innerHTML += "</td>";
				innerHTML += "<td>";
				innerHTML += "<div>";
				innerHTML += "<div style='margin-bottom: 10px;'>";
				innerHTML += "<a>Mã sách: " + resultSearchArray[i][BookItem.BOOK_ITEM_CODE] + "</a>";
				innerHTML += "</div>";
				innerHTML += "<div style='margin-bottom: 10px;'>";
				innerHTML += "<a>Tên sách: " + resultSearchArray[i][BookItem.BOOK_ITEM_TITLE] + "</a>";
				innerHTML += "</div>";
				innerHTML += "<div style='margin-bottom: 10px;'>";
				innerHTML += "<a>Tác giả: " + resultSearchArray[i][BookItem.BOOK_ITEM_AUTHOR] + "</a>";
				innerHTML += "</div>";
				innerHTML += "<div style='margin-bottom: 10px;'>";
				innerHTML += "<a>Nhà xuất bản: " + resultSearchArray[i][BookItem.BOOK_ITEM_PUBLISHER] + "</a>";
				innerHTML += "</div>";
				innerHTML += "<div style='margin-bottom: 10px;'>";
				innerHTML += "<a>Ngày xuất bản: " + resultSearchArray[i][BookItem.BOOK_ITEM_DAY_PUBPLICATION] + "</a>";
				innerHTML += "</div>";
				innerHTML += "<div style='margin-bottom: 10px;'>";
				innerHTML += "<a>Thể loại: " + resultSearchArray[i][BookItem.BOOK_ITEM_TYPE_EXPLAIN] + "</a>";
				innerHTML += "</div>";
				innerHTML += "</div>";
				innerHTML += "</td>";
				innerHTML += "</tr>";
				innerHTML += "</tbody>";
				innerHTML += "</table>";
				innerHTML += "</div>";
			}
		}
		//Set attributes in this function
		
		session.setAttribute("resultFromSearchBook", innerHTML);
		//Create dispatcher and forward to main.jsp
		
		dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
		
		return;
	}
	
	private void handleRequestPlacingBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get book item id from LendingBookController
		
		String bookItemId = (String) session.getAttribute("valueBookItemIdStore");
		//Get value of book item
		
		this.bookItem.getValueInformations(bookItemId, BookItem.BOOK_ITEM_ID);
		//Set some attribute to session
		
		session.setAttribute("nameBookResultPlaceBook", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_TITLE]);
		//Create dispatcher and forward to main.jsp
		
		dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
		
		return;
	}
	
	private void getBookItemIdForPlacingBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get book item code
		
		String bookItemCode = (String) session.getAttribute("bookItemCodeFromPlacingBook");
		//Get value properties from bookItem
		
		this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_ID] = "";
		
		this.bookItem.getValueInformations(bookItemCode, BookItem.BOOK_ITEM_CODE);
		
		if(this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_ID].equals("")) {
			//Display error 
			
			session.setAttribute("styleErrorPlaceBook", "display: block;");
			session.setAttribute("styleSuccessResutPlaceBook", "display: none;");
			session.setAttribute("errorOver15Days", "display: none;");
			session.setAttribute("errorOver10BooksLending", "display: none;");
			session.setAttribute("errorSameBook", "display: none;");
			session.setAttribute("errorNotExistBookItem", "display: block;");
			session.setAttribute("errorNotAvaiablePlaceBook", "display: none;");
			//Create dispatcher and forward to main.jsp
			
			dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
			dispatcher.forward(request, response);
			
			return;
		} else {
			//Set some attributes
			
			session.setAttribute("bookItemIdFromController", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_ID]);
			session.setAttribute("typeRequestToLendingController", "returnGetBookItemFromBookItemController");
			//Send direct to LendingBookController
			
			response.sendRedirect("LendingController");
			
			return;
		}
	}
	
	private void getBookItemCodeForCheckPlacingBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws ServletException, IOException {
		//Get book item Id from LendingController
		
		String bookItemId = (String) session.getAttribute("bookItemIdFromCheckPlacingInformation");
		
		this.bookItem.getValueInformations(bookItemId, BookItem.BOOK_ITEM_ID);
		
		session.setAttribute("codeBookCheckAcceptRegisterBook", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_CODE]);
		session.setAttribute("bookItemIdAcceptRegisterBeforeBook", bookItemId);
		//Create dispatcher and forward to main.jsp
		
		dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
		
		return;
	}
	
	private void getCodeBookForPayBook(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws IOException {
		//Get bookItemId from session variable
		
		String bookItemId = (String) session.getAttribute("bookItemIdForPayBookPart");
		//Get value properties from bookItem variable
		
		this.bookItem.getValueInformations(bookItemId, BookItem.BOOK_ITEM_ID);
		//Set attribute to display to user
		
		session.setAttribute("codeBookPayBookPart", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_CODE]);
		session.setAttribute("requestFromBookItem", "getBookTitleIdForPayBookController");
		//Send redirect to BookTitleController
		
		response.sendRedirect("BookTitleController");
		
		return;
	}
	
	private void checkExistenceBookItem(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestDispatcher dispatcher) throws IOException, ServletException {
		//Get attributes from UserController
		
		String bookItemCode = (String) session.getAttribute("bookCodeFromUserNoRegisterBefore");
		//Set name of book to "" to check existence
		
		this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_TITLE] = "";
		//Get information about book has Id is bookItemId
		
		this.bookItem.getValueInformations(bookItemCode, BookItem.BOOK_ITEM_CODE);
		
		if(!this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_TITLE].equals("")) {
			//Set attributes to display to user
			
			session.setAttribute("codeLendBookNoRegisterLendingBook", bookItemCode);
			session.setAttribute("requestFromBookItem", "checkAvaibleBookItemNoRegisterBefore");
			session.setAttribute("bookItemIdFromBookItemController", this.bookItem.getValueProperties()[BookItem.BOOK_ITEM_ID]);
			//Send redirect to BookTitleController
			
			response.sendRedirect("BookTitleController");
			
			return;
		} else {
			//Set attributes to display errors to user
			
			session.setAttribute("styleSuccessLendingBookNotRegister", "display: none;");
			session.setAttribute("styleResultNoRegisterLendingBookContent", "display: block;");
			session.setAttribute("styleErrorUserNoRegisterLendingBookContent", "display: none;");
			session.setAttribute("styleNotFoundBookItemIdNoRegisterBeforeLendingBook", "display: block;");
			session.setAttribute("styleErrorNotAvaiableToLendingNoRegister", "display: none;");
			session.setAttribute("styleErrorBookItemNoRegisterLendingBook", "display: block;");
			session.setAttribute("styleInfringeRolesNotRegisterLendingBook", "display: none;");
			//Create dispatcher and forward to main.jsp
			
			dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
			dispatcher.forward(request, response);
			
			return;
		}
	}
}
