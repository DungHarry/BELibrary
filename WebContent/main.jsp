<%@page import="org.apache.catalina.mbeans.ConnectorMBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>BELibrary</title>
	<link type="text/css" href="CSS/styleFormat.css" rel="stylesheet" />
	<link rel="shortcut icon" href="Images/Library.ico" />
	<script type="text/javascript" src="javascript/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="javascript/jquery.cycle.all.js"></script>
	<script type="text/javascript" src="javascript/script.js"></script>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
	%>
	<div class="tabTop shadown">
		<div class="topInner1">
			<a>
				<form method="get" action="BookItemController">
					<input type="hidden" name="submitTypeGetEditBookManager" value="submitSearchBook" />
					<input tabindex="1" type="text" placeholder="Tìm kiếm sách ..." name="searchBookForm" size="35" />
				</form>
			</a>
		</div>
		
		<div class="topInner5">
			<a><img src="Images/openSearch.jpg" id="idShowSearchTapTop" title="Hiển thị mục tìm kiếm" /></a>
		</div>
		
		<div class="topInner4">
			<a href="/BELibrary">Thoát</a>
		</div>
		
		<div class="topInner3">
			<a id="userDisplay">${userName}</a>
		</div>
		
		<div class="topInner2" style="${styleManager}">	
			<a>Quản lý</a>
		</div>	
	</div>
	
	<div id="divSelections" class="shadown">
		<div id="managerChooserBar1"><a>Thêm người sử dụng</a></div>
		<div id="managerChooserBar2"><a>Cập nhật sách</a></div>
		<div id="managerChooserBar3"><a>Cho mượn sách</a></div>
		<div id="managerChooserBar4"><a>Nhận trả sách </a></div>
		<div id="managerChooserBar5"><a>Kiểm tra đặt sách</a></div>
		<div id="managerChooserBar6"><a>Kiểm tra mượn sách</a></div>
	</div>
	
	<div class="wrapper">
		<div class="slideTop shadown">
			<img alt="Picture 1" src="Images/Cover0.jpg" />
			<img alt="Picture 2" src="Images/Cover1.jpg" />
			<img alt="Picture 3" src="Images/Cover2.jpg" />
			<img alt="Picture 4" src="Images/Cover3.jpg" />
			<img alt="Picture 5" src="Images/Cover4.jpg" />
		</div>
		
		<div class="menuChooser shadown">
			<div class="chooserInner">
				<div id="introductChooser"><a>Giới thiệu</a></div>
				<div id="checkBookChooser"><a>Kiểm tra sách</a></div>
				<div id="placeBookChooser"><a>Đặt sách</a></div>
				<div id="historyChooser"><a>Lịch sử</a></div>
			</div>
		</div>
		
		<div class="mainPart">
			<div class="slideLeft">
				<div id="bookSlide1">
					<img alt="Image 1.1" src="Images/1.1.jpg" />
					<img alt="Image 1.2" src="Images/1.2.jpg" />
					<img alt="Image 1.3" src="Images/1.3.jpg" />
					<img alt="Image 1.4" src="Images/1.4.jpg" />
					<img alt="Image 1.5" src="Images/1.5.jpg" />
					<img alt="Image 1.6" src="Images/1.6.jpg" />
				</div>
				
				<div id="bookSlide2">
					<img alt="Image 2.1" src="Images/2.1.jpg" />
					<img alt="Image 2.2" src="Images/2.2.jpg" />
					<img alt="Image 2.3" src="Images/2.3.jpg" />
					<img alt="Image 2.4" src="Images/2.4.jpg" />
					<img alt="Image 2.5" src="Images/2.5.jpg" />
					<img alt="Image 2.6" src="Images/2.6.jpg" />
				</div>
				
				<div id="bookSlide3">
					<img alt="Image 3.1" src="Images/3.1.jpg" />
					<img alt="Image 3.2" src="Images/3.2.jpg" />
					<img alt="Image 3.3" src="Images/3.3.jpg" />
					<img alt="Image 3.4" src="Images/3.4.jpg" />
					<img alt="Image 3.5" src="Images/3.5.jpg" />
					<img alt="Image 3.6" src="Images/3.6.jpeg" />
				</div>
				
				<div id="bookSlide4">
					<img alt="Image 4.1" src="Images/4.1.jpg" />
					<img alt="Image 4.2" src="Images/4.2.jpg" />
					<img alt="Image 4.3" src="Images/4.3.jpg" />
					<img alt="Image 4.4" src="Images/4.4.jpg" />
					<img alt="Image 4.5" src="Images/4.5.jpg" />
					<img alt="Image 4.6" src="Images/4.6.jpg" />
				</div>
			</div>
			
			<div class="content shadown">
				<div id="introductWrapper">
					<div class="introChooser">
						<div id="introSlide1" onclick="focusChooser('introSlide1');"><a>Tổng quan</a></div>
						<div id="introSlide2" onclick="focusChooser('introSlide2');"><a>Lịch sử</a></div>
						<div id="introSlide3" onclick="focusChooser('introSlide3');"><a>Quy định</a></div>
						<div id="introSlide4" onclick="focusChooser('introSlide4');"><a>Hỗ trợ</a></div>
						<div id="introSlide5" onclick="focusChooser('introSlide5');"><a>Bản quyền</a></div>
					</div>
					
					<div id="introContent">
						<div id="introContent1">
							<div style="margin: 0 10px auto; min-height: 1000px;">
								<p align="center" style="font-size: 40px;">Tổng quan</p>
								<p style="padding-left: 20px;">Chào mừng bạn tới với BELibrary!</p>
								<p><a style="padding-left: 20px;">BELibrary là thư viện mở</a>, cho phép bạn có thể đăng ký và sử dụng một cách miễn phí
									mà không phải tốn bất cứ một chi phí nào. Bạn có thể đăng ký mượn sách trực tiếp với thủ thư hoặc bạn cũng có thể đăng 
									và mượn sách thông qua hệ thống web của thư viện tại <a href="http://localhost:8080/BELibrary/" style="text-decoration: none;">đây.</a>
								</p>
								<p style="padding-left: 20px;">Kính gửi</p>
								<p style="padding-left: 20px;">Admin: Dung Harry</p> 
							</div>
						</div>
						
						<div id="introContent2" class="hide">
							<div style="margin: 0 10px auto; min-height: 1000px;">
								<p align="center" style="font-size: 40px;">Lịch sử</p>
								<p><a style="padding-left: 20px;">BELibrary được thành</a> lập từ ngày 28, tháng 5, năm 2013 bởi Dung Harry. 
									Tuy được thành lập chưa lâu nhưng thư viện cũng có một số lượng sách khổng lồ. Các loại sách trong thư viện 
									bao gồm đầy đủ các thể loại khác nhau, như: kỹ thuật, toán học, văn học,... Chúng tôi tin rằng bạn có thể 
									tìm thấy các quyển sách mình mong muốn cùng với BELibrary. Chúng tôi hân hạnh khi được đón tiếp và phục vụ 
									bạn. Chúng tôi mong rằng bạn sẽ luôn ủng hộ và sử dụng dịch vụ do BELibrary cung cấp.
								</p>
							</div>
						</div>
						
						<div id="introContent3" class="hide">
							<div style="margin: 0 10px auto; min-height: 1000px;">
								<p align="center" style="font-size: 40px;">Quy định</p>
								<p><a style="padding-left: 20px;">BELibrary có những</a> quy định bắt buộc các thành viên của thư viện phải tuân 
									theo nhằm đảo bảo cho chúng tôi có thể phục vụ bạn một cách tốt nhất. Dưới đây là các quy định của BELibrary: 
								</p>
								
									<ol>
										<li>
											BELibrary mở cửa từ 9:00 sáng tới 18:00 tối các ngày trong tuần từ thứ hai tới thứ 7
										</li>
										
										<li> 
											Thời gian mượn sách tối đa là 15 ngày đối với mỗi quyển sách cụ thể. Điều này có nghĩa là sách bạn mượn 
											phải được trả trong vòng 15 ngày kể từ thời điểm bắt đầu mượn. Nếu bạn có nhu cầu mượn tiếp thì bạn được 
											ưu tiên mượn tiếp nhưng phải đăng ký mượn lại với thủ thư. Trong trường hợp bạn mượn sách quá 15 ngày chưa 
											giả thì bạn không thể tiếp tục mượn sách trong thư viện trừ khi bạn trả quyển sách đó.
										</li>
										
										<li>
											Trong cùng một thời điểm, bạn chỉ có thể mượn tối đa 10 quyển sách và các quyển sách này không được trùng nhau. 
											Bạn không thể cố tình đăng ký mượn hai quyển sách giống nhau cùng một lúc, hệ thống và thủ thư của chúng tôi 
											sẽ ngăn chặn cho hành vi này.
										</li>
										
										<li>
											Khi mượn sách bạn cần bảo vệ, bảo vệ quyển sách cận thận. Nếu quyển sách bạn mượn bị hỏng không thể sử dụng nữa 
											sau đó bạn sẽ phải chịu trách nhiệm với chúng tôi bằng cách phải đền bù số tiền tương đương với quyển sách bị hỏng 
											hoặc bạn cũng có thể mua quyển sách tương tự thay thế cho nó để đền bù cho nó
										</li>
									</ol>
									
								<p>
									<a style="padding-left: 20px;">Trên đây,</a> là những quy định mà bạn <strong>bắt buộc</strong> phải tuân theo trong quá
									trình sử dụng thư viện BELibrary.
								</p>
							</div>
						</div>
						
						<div id="introContent4" class="hide">
							<div style="margin: 0 10px auto; min-height: 1000px;">
								<p align="center" style="font-size: 40px;">Hỗ trợ</p>
								<p>
									<a style="padding-left: 20px;">BELibrary là hệ thống vô cùng đơn</a> giản để sử dụng. Tuy nhiên, chúng tôi vẫn muốn có một hướng dẫn cụ thể
									cho bạn để có thể theo dõi trong trường hợp bạn gặp khó khăn trong quá trình sử dụng. Dưới đây là các hướng dẫn sử dụng hệ thống của chúng tôi. 
									Đó là:
								</p>
								 
									<ol>
										<li>
											Giới thiệu: Bạn có thể đọc để tìm hiểu về chúng tôi tại sự lựa chọn này. Khi chọn sự lựa chọn này bạn có thể chọn 5 lựa chọn để đọc, đó 
											là: Tổng quan, Lịch sử, Quy định, Hướng dẫn và Bản quyền
										</li>
										
										<li>
											Ô tìm kiếm ở trên thanh menu, chúng tôi cung cấp cho bạn một cách để tìm kiếm các cuốn sách mong muốn trong thư viện bằng mục này, bạn chỉ 
											cần gõ tên sách vào chỉ ô này, tiếp theo hệ thống sẽ xử lý và trả lại cho bạn các kết quả có trong thư viện cho bạn có thể tham khảo và lựa 
											chọn
										</li>
										
										<li>
											Kiểm tra sách, mục này cung cho bạn một cách để có thể kiểm tra trạng thái sách trong thư viện. Trạng thái sách là thứ tương tự như có còn sách 
											cho bạn mượn hay không, để sử dụng mục này bạn cần biết chính xác mã số quyển sách bạn quan tâm vì nó sẽ yêu cầu mã số của sách để sử dụng 
										</li>
										
										<li>
											Đặt sách, mục này cho phép bạn có thể đặt trực tiếp một cuốn sách có sẵn trong thư viện để đặt sách bạn cần có mã của cuốn sách mà bạn muốn mượn. 
											Bạn cũng có thể đặt sách bằng các sự lựa chọn trong kết quả tìm kiếm hoặc với mục kiểm tra sách
										</li>
										
										<li>
											Lịch sử, mục này cho phép bạn có thể xem lại các thông tin về các hoạt động đặt sách và mượn sách trong thư viện. Bạn cũng có thể xóa các thông tin này nếu
											bạn muốn
										</li>
									</ol>
									
									<p><a style="padding-left: 20px;">Trên đây, </a>chúng tôi đã trình bày các thông tin để bạn có thể tham khảo cho cách sử dụng thư viện BELibrary</p>
							</div>
						</div>
						
						<div id="introContent5" class="hide">
							<div style="margin: 0 10px auto; min-height: 1000px;">
								<p align="center" style="font-size: 40px; border: none;">Bản quyền</p>
								
								<div class="introductCopyright">
									<div>Tác giả: Dung Harry</div>
									<div>Điện thoại: (+84)973 675 143</div>
									<div>Email: belibrarycenter@gmail.com</div>
									<div>Logo và bản quyền của BE 2013</div>
									<div>© 2013, The Copyright BE of the Vietnam. All rights reserved</div>									
								</div>
							</div>
						</div>
					</div>
				</div>
			
			
				<div class="hide" id="userInforsPart" >
					<p style="font-size: 40px; font-weight: bold;">Thông tin người sử dụng</p>
					
					<div class="userInforsWrapper">
						<form method="post" action="UserController">
							<input type="hidden" value="updateUser" name="typeUser" />
							<input type="hidden" name="infsUserId" value="${userId}" />
							<input type="hidden" id="infsUserPassword" name="infsUserPassword" value="${userPassword}" />
							<input type="hidden" id="infsTypeUpdateChange" name="infsUserUpdateType" value="${userUpdateType}" />
							
							<div class="topicInfors">
								<div id="infsNameDefine">
									<table>
										<tr>
											<td width="130px">Tên:</td>
											<td width="320px">${userName}</td>
											<td><input class="edit hide" id="editInfsUserName" title="Edit" type="button" /></td>
										</tr>
									</table>
								</div>
								
								<div id="infsNameChange" class="hide">
									<table>
										<tr>
											<td width="130px">Tên:</td>
											<td width="320px"><input name="infsUserName" type="text" size="20" title="Name" placeholder="Enter new name..." value="${userName}" /></td>
											<td><input class="apply hide" id="applyInfsUserName" type="submit" title="Apply" value="" /></td>
										</tr>
										
										<tr>	
											<td align="right"><input id="cancelInfsName" type="button" title="Cancel" value="Cancel" /></td>
										</tr>
									</table>
								</div>
							</div>
							
							<div class="topicInfors">
								<div>Là thành viên từ: <a>${userDateCreated}</a></div>
							</div>
							
							<div class="topicInfors">
								<div id="infsBirthdayDefine">
									<table>
										<tr>
											<td width="130px">Ngày sinh:</td> 
											<td width="320px">${userBirthday}</td>
											<td><input class="edit hide" id="editInfsBirthday" type="button" /></td>
										</tr>
									</table>
								</div>
								
								<div id="infsBirthdayChange" class="hide">
									<input type="hidden" name="infsDateBirthdayUser" id="infsHiddenDateBirthday" value="${dateBirthday}" />
									<input type="hidden" name="infsMonthBirthdayUser" id="infsHiddenMonthBirthday" value="${monthBirthday}" />
									<input type="hidden" name="infsYearBirthdayUser" id="infsHiddenYearBirthday" value="${yearBirthday}" />
									
									<table>
										<tr>
											<td width="130px">
												Ngày sinh: 
											</td>
											
											<td width="320px">
												<select id="selectYearBirthdayChange" title="Năm">
													<%
														for(int i = 1950; i <= 2013; i ++) {
													%>
													<option><%= Integer.toString(i) %></option>
													<%
														}
													%>
												</select>
												-
												<select id="selectMonthBirthdayChange" title="Tháng">
													<%
														for(int i = 1; i <= 12; i ++) {
													%>
													<option><%= Integer.toString(i) %></option>
													<%
														}
													%>
												</select>
												-
												<select id="selectDateBirthdayChange" title="Ngày">
													<%
														for(int i = 1; i <= 31; i ++) {
													%>
													<option><%= Integer.toString(i) %></option>
													<%
														}
													%>
												</select>
											</td>
											<td>
												<input class="apply hide" id="applyInfsBirthday" type="submit" value="" title="Apply" />
											</td>
										</tr>
										<tr>
											<td align="right">
												<input id="cancelInfsBirthday" value="Cancel" type="button" title="Cancel" />
											</td>
										</tr>
									</table>
								</div>
							</div>
							
							<div class="topicInfors">
								<div id="infsGenderDefine">
									<table>
										<tr>
											<td width="130px">Giới tính: </td>
											<td width="320px">${userGender}</td>
											<td><input class="edit hide" id="editInfsGender" title="Edit" type="button" /></td>
										</tr>
									</table>
								</div>
								
								<div id="infsGenderChange" class="hide">
									<input type="hidden" id="infsGenderHidden" name="infsUserGender" value="${userGender}" />
									<table>
										<tr>
											<td width="130px">Giới tính:</td>
											<td width="320px"> 
												<select id="selectUserGenderChange">
													<option>Nam</option>
													<option>Nữ</option>
												</select>
											</td>
									
											<td><input class="apply hide" id="applyInfsGender" type="submit" value="" title="Apply" /></td>
										</tr>
										<tr>
											<td align="right">
												<input id="cancelInfsGender" value="Cancel" type="button" title="Cancel" />
											</td>
										</tr>
									</table>
								</div>
							</div>
							
							<div class="topicInfors">
								<div id="infsJobDefine">
									<table>
										<tr>
											<td width="130px">Công việc: </td> 
											<td width="320px">${userJob}</td>
											<td><input class="edit hide" id="editInfsJob" title="Edit" type="button" /></td>
										</tr>
									</table>
								</div>
								
								<div id="infsJobChange" class="hide">
									<table>
										<tr>
											<td width="130px">Công việc:</td>
											<td width="320px"><input type="text" name="infsUserJob" size="20" value="${userJob}" title="Công việc" placeholder="Nhập công việc mới ..." /></td>
											<td><input class="apply hide" id="applyInfsJob" type="submit" title="Apply" value="" /></td>
										</tr>
										<tr>
											<td align="right"><input type="button" value="Cancel" title="Cancel" id="cancelInfsJob" /></td>
										</tr>
									</table>
								</div>
							</div>
							
							<div class="topicInfors">
								<div id="infsAddressDefine">
									<table>
										<tr>
											<td width="130px">Địa chỉ:</td>
											<td width="320px">${userAddress}</td>
											<td><input class="edit hide" id="editInfsAddress" title="Edit" type="button" /></td>
										</tr>
									</table>
								</div>
								
								<div id="infsAddressChange" class="hide">
									<table>
										<tr>
											<td width="130px">Địa chỉ:</td>
											<td width="320px"><input type="text" name="infsUserAddress" size="20" value="${userJob}" title="Công việc" placeholder="Nhập công việc mới ..." /></td>
											<td><input class="apply hide" id="applyInfsAdress" type="submit" title="Apply" value="" /></td>
										</tr>
										
										<tr>
											<td align="right"><input type="button" value="Cancel" title="Cancel" id="cancelInfsAddress" /></td>
										</tr>
									</table>
								</div>
							</div>
							
							<div class="topicInfors">
								<div id="infsPhoneDefine">
									<table>
										<tr>
											<td width="130px">Số điện thoại:</td>
											<td width="320px">${userPhone}</td>
											<td><input class="edit hide" id="editInfsPhone" title="Edit" type="button" /></td>
										</tr>
									</table>
								</div>
								
								<div id="infsPhoneChange" class="hide">
									<table>
										<tr>
											<td width="130px">Số điện thoại:</td>
											<td width="320px"><input type="text" name="infsUserPhone" size="20" value="${userPhone}" title="Số điện thoại" placeholder="Nhập số điện thoại mới ..." /></td>
											<td><input class="apply hide" id="applyInfsPhone" type="submit" value="" title="Apply" /></td>
										</tr>
										<tr>
											<td align="right"><input type="button" value="Cancel" title="Cancel" id="cancelInfsPhone" /></td>
										</tr>
									</table>
								</div>
							</div>

							<div class="topicInfors">
								<div id="infsEmailDefine">
									<table>
										<tr>
											<td width="130px">Email:</td>
											<td width="320px">${userEmail}</td>
											<td><input class="edit hide" id="editInfsEmail" title="Edit" type="button" /></td>
										</tr>
									</table>
								</div>
								
								<div id="infsEmailChange" class="hide">
									<table>
										<tr>
											<td width="130px">Email:</td>
											<td width="320px"><input type="text" name="infsUserEmail" size="20" value="${userEmail}" title="Email" placeholder="Nhập địa chỉ email mới ..." /></td>
											<td><input class="apply hide" id="applyInfsEmail" type="submit" title="Apply" value="" /></td>
										</tr>
										
										<tr>
											<td align="right"><input value="Cancel" title="Cancel" id="cancelInfsEmail" type="button" /></td>
										</tr>
									</table>
								</div>
							</div>
							
							<div class="topicInfors" style="${styleManager}">
								<div id="infsAdditionDefine">
									<table>
										<tr>
											<td width="130px">Thông tin thêm:</td>
											<td width="320px">${adminAdditionInfs}</td>
											<td><input class="edit hide" id="editInfsAddition" title="Edit" type="button" /></td>
										</tr>
									</table>
								</div>
								
								<div id="infsAdditionChange" class="hide">
									<table>
										<tr>
											<td width="130px">Thông tin thêm: </td>
											<td width="320px"><input type="text" name="infsAdminAdditional" size="20" value="${adminAdditionInfs}" title="Thông tin bổ sung cho thủ thư" placeholder="Nhập thông tin bổ sung mới ..." /></td>
											<td><input class="apply hide" id="applyInfsAddition" type="submit" title="Apply" value="" /></td>
										</tr>
										
										<tr>
											<td align="right"><input id="cancelInfsAddition" value="Cancel" type="button" title="Cancel" /></td>
										</tr>
									</table>
								</div>
							</div>
							
							<div class="topicInfors">
								<div id="infsPasswordDefine">
									<table>
										<tr>
											<td width="130px">*Mật khẩu </td>
											<td width="320px"><a>Vì lý do bảo mật nên mật khẩu không được hiện ở đây</a></td>
											<td><input class="edit hide" id="editInfsPassword" title="Edit" type="button" /></td>
										</tr>
									</table>
								</div>
								
								<div id="infsPasswordChange" class="hide">
									<table>
										<tr>
											<td width="130px">*Mật khẩu: </td>
											<td width="320px">
												<input type="password" id="infsValueOldPassword" size="25" title="Mật khẩu cũ" placeholder="Nhập mật khẩu cũ ..." />
												<p class="hide" style="color: red; font-size: 15px; margin: 5px 10px 0 0;" id="infsWarringOldPassword"></p>
												<input type="password" name="infsUserPasswordNew" id="infsValueNewPassword" size="25" title="Mật khẩu mới" placeholder="Nhập mật khẩu mới ..." />
												<input type="password" id="infsValueReNewPassword" size="25" title="Nhắc lại mật khẩu" placeholder="Nhập lại mật khẩu lần nữa ..." />
												<p class="hide" style="color: red; font-size: 15px; margin: 5px 10px 0 0;" id="infsWarringNewPassword"></p>
											</td>
											
											<td><input onclick="return checkPasswordInformations('infsValueOldPassword', 'infsValueNewPassword', 'infsValueReNewPassword', 'infsUserPassword', 'infsWarringOldPassword', 'infsWarringNewPassword');" class="apply hide" id="applyInfsPassword" type="submit" title="Apply" value="" /></td>
										</tr>
										
										<tr>
											<td align="right"><input id="cancelInfsPassword" value="Cancel" type="button" title="Cancel" /></td>
										</tr>
									</table>
								</div>
							</div>
							
						</form>
					</div>			
				</div>
				
				<div id="managerWrapper" class="hide">
					<div title="Danh mục lựa chọn" id="dropdownManagerChooser">
						<img alt="Manager Chooser Dropdown Menu" src="Images/ScrollDown.jpg">
					</div>
				
					<div id="divManagerChooserWrapper" class="hide">
						<div class="chooserManagerInner">
							<div id="managerChooser1">
								<a>Thêm người sử dụng</a>
							</div>
							
							<div id="managerChooser2">
								<a>Cập nhật sách</a>
							</div>
							
							<div id="managerChooser3">
								<a>Cho mượn sách</a>
							</div>
							
							<div id="managerChooser4">
								<a>Nhận trả sách</a>
							</div>
							
							<div id="managerChooser5">
								<a>Kiểm tra đặt sách</a>
							</div>
							
							<div id="managerChooser6">
								<a>Kiểm tra mượn sách</a>
							</div>
						</div>
					</div>
					
					<div class="divManagerContentWrapper">
						<div>
							<div id="managerContent1" class="hide" style="${styleManagerContent1}">
								<p align="center" style="font-size: 40px; font-weight: bold;">Thêm người sử dụng</p>
								
								<div class="addUserWrapper">
									<form method="get" action="UserController">
										<input type="hidden" name="requestInsertUser" id="idRequestInsertUser" value="${typeGetUserInsert}" />
										<div>
											<table>
												<tr>
													<td style="width: 150px;">Tên: </td>
													<td>
														<input type="text" size="25" name="userNameInsertion" id="idUserNameInsertion" title="Tên người sử dụng" placeholder="Nhập tên người dùng..." />
														<p class="hide" id="errorNameInsertion">*Chưa nhập tên người dùng</p>
													</td>
												</tr>
											</table>
										</div>
										
										<div>
											<table>		
												<tr>
													<td style="width: 150px;">Mật khẩu: </td>
													<td>
														<input type="password" size="25" name="userPasswordInsertion" id="passwordInsertion" placeholder="Nhập mật khẩu người dùng..." title="Mật khẩu" />
														<input type="password" size="25" id="repasswordInsertion" placeholder="Nhập lại mật khẩu..." title="Xác nhận mật khẩu" />
														<p id="errorPasswordInsertion" class="hide"></p>
													</td>
												</tr>
											</table>
										</div>
												
										<div>
											<table>
												<tr>
													<td style="width: 150px;">Email: </td>
													<td>
														<input type="text" size="25" name="userEmailInsertion" id="idUserEmailInsertion" title="Email" placeholder="Nhập địa chỉ email..." />
														<p class="hide" id="errorEmailInsertion">*Chưa nhập vào địa chỉ Email</p>
													</td>
												</tr>
											</table>
										</div>
										
										<div>
											<table>
												<tr>
													<td style="width: 150px;">Địa chỉ: </td>
													<td>
														<textarea cols="25" rows="5" name="userAddressInsertion" id="idUserAddressInsertion" title="Địa chỉ" placeholder="Nhập địa chỉ..."></textarea>
														<p class="hide" id="errorAddressInsertion">*Chưa nhập vào địa chỉ</p>
													</td>		
												</tr>
											</table>
										</div>
												
										<div>
											<table>
												<tr>
													<td style="width: 150px;">Số điện thoại: </td>
													<td>
														<input size="25" type="text" name="userPhoneInsertion" id="idUserPhoneInsertion" title="Số điện thoại" placeholder="Nhập số điện thoại..." />
														<p class="hide" id="errorPhoneInsertion">*Chưa nhập vào số điện thoại</p>
													</td>
												</tr>
											</table>
										</div>
												
										<div>
											<table>
												<tr>
													<td style="width: 150px;">Giới tính: </td>
													<td>
														<input type="hidden" name="userGenderInsertion" id="idUserGenderInsertion" value="Nam" />
														<select title="Giới tính" id="userGenderInsertSelection">
															<option>Nam</option>
															<option>Nữ</option>
														</select>
													</td>
												</tr>
											</table>
										</div>
												
										<div>
											<table>
												<tr>
													<td style="width: 150px;">Ngày sinh: </td>
													<td>
														<input type="hidden" name="userDateBirthdayInsertion" id="idDateBirthdayInsertion" value="01" />
														<input type="hidden" name="userMonthBirthdayInsertion" id="idMonthBirthdayInsertion" value="01" />
														<input type="hidden" name="userYearBirthdayInsertion" id="idYearBirthdayInsertion" value="1950" />
														
														<select title="Năm sinh" id="userYearBirthdaySelection">
															<%
																for(int i = 1950; i <= 2013; i ++) {
															%>
															<option><%= i %></option>
															<%
																}
															%>
														</select>
														<a> - </a>
														<select title="Tháng sinh" id="userMonthBirthdaySelection">
															<%
																for(int i = 1; i <= 12; i ++) {
															%>
															<option><%= i %></option>
															<%
																}
															%>
														</select>
														<a> - </a>
														<select title="Ngày sinh" id="userDateBirthdaySelection">
															<%
																for(int i = 1; i <= 31; i ++) {
															%>
															<option><%= i %></option>
															<%
																}
															%>
														</select>
													</td>
												</tr>
											</table>
										</div>
											
										<div>	
											<table>
												<tr>
													<td style="width: 150px;">Công việc: </td>
													<td>
														<textarea cols="25" rows="5" name="userJobInsertion" id="idUserJobInsertion" title="Công việc" placeholder="Nhập vào công việc..."></textarea>
														<p class="hide" id="errorJobInsertion">*Chưa nhập vào công việc</p>	
													</td>
												</tr>
											</table>
										</div>
										
										<div>
											<table>
												<tr>
													<td><p style="padding-left: 150px;"><input type="submit" id="submitUserInsertion" value="Thêm người dùng" /></p></td>
												</tr>
											</table>
										</div>
										
										<p class="hide" style="color: red; font-size: 20px; font-weight: bold; ${styleErrorNameExistCreateAccount}">*Tạo tài khoản thất bại: tên người dùng đã tồn tại</p>
									</form>
								</div>
							</div>
							
							<div id="managerContent2" class="hide" style="${styleManagerContent2}">
								<div>
									<div class="bookUpdateChooserManager">
										<div>
											<div id="updateChooserManager1"><a>Thêm sách</a></div>
											<div id="updateChooserManager2"><a>Chỉnh sửa</a></div>
										</div>
									</div>
									
									<div class="bookUpdateContentManager">
										<div>
											<div id="updateContentChooser1">
												<p align="center" style="font-size: 40px;">Thêm sách</p>
												
												<div>
													<form method="post" action="BookItemController" enctype="multipart/form-data">
														<div class="wrapperEachElement">
															<table>
																<tbody>
																	<tr>
																		<td width="120">Tên sách: </td>
																		<td>
																			<input type="text" id="titleBookContentManager" size="20" name="n_titleBookContentManager" title="Tên sách" placeholder="Nhập vào tên sách..." />
																			<p class="hide" id="titleBookManagerError">*Chưa nhập tên sách</p>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
														
														<div class="wrapperEachElement">
															<table>
																<tbody>
																	<tr>
																		<td width="120">Tác giả: </td>
																		<td>
																			<input type="text" id="authorBookContentManager" size="20" name="n_authorBookContentManager" title="Tên tác giả" placeholder="Nhập vào tên tác giả..." />
																			<p class="hide" id="authorBookContentManagerError">*Chưa nhập tên tác giả</p>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
														
														<div class="wrapperEachElement">
															<table>
																<tbody>
																	<tr>
																		<td width="120">Biểu tượng: </td>
																		<td>
																			<input type="text" id="symbolBookContentManager" size="20" name="n_symbolBookContentManager" title="Biểu tượng" placeholder="Nhập vào biểu tượng..." />
																			<p class="hide" id="symbolBookContentManagerError">*Chưa nhập vào biểu tượng</p>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
														
														<div class="wrapperEachElement">
															<table>
																<tbody>
																	<tr>
																		<td width="120">Chú thích: </td>
																		<td>
																			<textarea rows="5" cols="20" id="explainBookContentManager" name="n_explainBookContentManager" title="Chú thích" placeholder="Nhập vào chú thích cho biểu tượng..."></textarea>
																			<p class="hide" id="explainBookContentManagerError">*Chưa nhập vào chú thích</p>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
														
														<div class="wrapperEachElement">
															<table>
																<tbody>
																	<tr>
																		<td width="120">Tên ảnh: </td>
																		<td>
																			<input size="20" name="n_imageNameEntireBookManager" id="imageNameEntireBookManager" title="Tên ảnh" placeholder="Nhập tên ảnh..." />
																			<p class="hide" id="imageNameEntireBookManagerError">*Chưa nhập vào tên ảnh</p>
																		</td>
																	</tr>
																
																	<tr>
																		<td width="120">Ảnh: </td>
																		<td>
																			<input size="10" type="file" id="imageEntireBookManager" name="n_imageEntireBookManager" title="Ảnh" />
																			<p class="hide" id="imageEntireBookManagerError">*Chưa chọn ảnh</p>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
														
														<div class="wrapperEachElement">
															<table>
																<tbody>
																	<tr>
																		<td width="120">Tóm tắt: </td>
																		<td>
																			<textarea cols="20" rows="5" id="summaryBookContentManager" name="n_summaryBookContentManager" title="Tóm tắt" placeholder="Nhập vào tóm tắt nội dung..."></textarea>
																			<p class="hide" id="summaryBookContentManagerError">*Chưa nhập tóm tắt</p>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
														
														<div class="wrapperEachElement">
															<table>
																<tr>
																	<td width="120">Ngày xuất bản</td>
																	<td>
																		<input type="hidden" id="yearPublicationBookContentManager" name="n_yearPublicationBookContentManager" value="1950" />
																		<input type="hidden" id="monthPublicationBookContentManager" name="n_monthPublicationBookContentManager" value="1" />
																		<input type="hidden" id="datePublicationBookContentManager" name="n_datePublicationBookContentManager" value="1" />
																		
																		<select id="yearPublicationSelection">
																			<%
																				for(int i = 1950; i <= 2013; i ++) {
																			%>
																				<option><%= Integer.toString(i) %></option>
																			<%
																				}
																			%>
																		</select>
																		<a> - </a>
																		<select id="monthPublicationSelection">
																			<%
																				for(int i = 1; i <= 12; i ++) {
																			%>
																				<option><%= Integer.toString(i) %></option>
																			<%
																				}
																			%>
																		</select>
																		<a> - </a>
																		<select id="datePublicationSelection">
																			<%
																				for(int i = 1; i <= 31; i ++) {
																			%>
																			<option><%= Integer.toString(i) %></option>
																			<%
																				}
																			%>
																		</select>
																	</td>
																</tr>
															</table>
														</div>
														
														<div  class="wrapperEachElement">
															<table>
																<tbody>
																	<tr>
																		<td width="120">Nhà xuất bản: </td>
																		<td>
																			<input type="text" name="n_publisherBookManager" id="publisherBookManager" placeholder="Nhập vào nhà xuất bản..." title="Nhà xuất bản" />
																			<p class="hide" id="publisherBookManagerError">*Chưa nhập vào nhà xuất bản</p>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
														
														<div  class="wrapperEachElement">
															<table>
																<tbody>
																	<tr>
																		<td width="120">Số lượng sách: </td>
																		<td>
																			<input type="hidden" id="hiddenSumBookItemBookManager" value="1" name="n_sumBookItemBookManager" />
																			
																			<select id="sumBookItemBookManager">
																				<%
																					for(int i = 1; i <= 1000; i ++) {
																				%>
																				
																				<option><%= Integer.toString(i) %></option>
																				
																				<%
																					}
																				%>
																			</select>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
														
														<div  class="wrapperEachElement" align="center"><input type="submit" id="submitInsertBookManager" title="Thêm sách vào trong cơ sở dữ liệu" value="Thêm sách" /></div>		
													</form>
												</div>
											</div>
											
											<div id="updateContentChooser2" class="hide">
												<p align="center" style="font-size: 40px; font-weight: bold;">Cập nhật thông tin về sách</p>
												
												<div align="center">
													<form action="BookItemController" method="get">
														<p>
															<a>Mã số sách: </a>
															<a><input name="bookItemIdGetBookManager" type="text" title="Mã số sách" placeholder="Nhập mã số sách..." size="20" /></a>
															<a><input type="submit" id="updateEditBookManager" name="submitTypeGetEditBookManager" value=" Xem " /></a>
														</p>
													</form>
												</div>
												
												<hr />
												
												<div style="margin: 10px 0 0 5px; ${styleResultNotExistBookManager}" class="hide">
													<p style="font-size: 25px; font-weight: bold;">Cuốn sách không tồn tại</p>
												</div>
												
												<form method="get" action="BookItemController">
												<div id="updateEditContent" class="hide" style="${styleResultExistBookManager}">
													<div class="updateEditContentWrapperResult">
														
														<div class="firstEditContentBookManager">
															<table>
																<tr>
																	<td>
																		<div class="leftHandUpdateEditContentWrapper">
																			<img alt="Ảnh chỉnh sửa" src="${imageBookResultLinkEditBookManager}" title="${nameEditBookUpdateManager}">
																		</div>
																	</td>
																	
																	<td>
																		<div class="rightHandUpdateEditContentWrapper">
																			<p>Mã sách: ${codeEditBookUpdateManager}</p>
																		</div>
																	</td>
																</tr>
															</table>
														</div>
														
														<div>
															<table>
																<tr>
																	<td>
																		<div class="leftHandUpdateEditContentWrapper" id="nameBeginEditPartBookManager">
																			<a>Tên: </a>
																		</div>
																	</td>
																	
																	<td>
																		<div class="rightHandUpdateEditContentWrapper">
																			<div id="nameMainEditPartBookManager">
																				<div class="alignMainEditBookManager">
																					<a>${nameEditBookUpdateManager}</a>
																				</div>
																
																				<div align="center" class="iconShowEditPart hide" id="idNameIconShowEditPartBookManager">
																					<img src="Images/Forward.jpg" alt="Shower icon" title="Hiển thị" />
																				</div>
																			</div>
																			
																			<div id="idNameEditPartBookManager" class="hide">
																				<div class="newFieldUpdateBookManager alignMainEditBookManager">
																					<input size="20" name="n_titleUpdateBookManager" id="titleUpdateBookManager" value="${nameEditBookUpdateManager}" title="Chỉnh sửa tên sách" />
																				</div>
																
																				<div align="center" class="applyFieldUpdateBookManager hide" id="idNameIconApplyEditPartBookManager">
																					<img src="Images/ForwardUp.jpg" alt="Hider Icon" title="Thiết lập" />
																				</div>
																			</div>
																		</div>
																	</td>
																</tr>
															</table>
														</div>
														
														<div>
															<table>
																<tr>
																	<td>
																		<div class="leftHandUpdateEditContentWrapper" id="authorBeginEditPartBookManager">
																			<a>Tác giả </a>
																		</div>
																	</td>
																		
																	<td>	
																		<div class="rightHandUpdateEditContentWrapper">
																			<div id="authorMainEditPartBookManager">
																				<div class="alignMainEditBookManager">
																					<a>${authorEditBookUpdateManager}</a>
																				</div>
																			
																				<div align="center" class="iconShowEditPart hide" id="idAuthorIconShowEditPartBookManager">
																					<img src="Images/Forward.jpg" alt="Shower icon" title="Hiển thị" />
																				</div>
																			</div>
																			
																			<div id="idAuthorEditPartBookManager" class="hide">
																				<div class="newFieldUpdateBookManager alignMainEditBookManager">
																					<input type="text" size="20" name="n_authorEditBookUpdateManager" id="authorEditBookUpdateManager" value="${authorEditBookUpdateManager}" title="Chỉnh sửa tác giả" />
																				</div>
																
																				<div align="center" class="applyFieldUpdateBookManager hide" id="idAuthorIconApplyEditPartBookManager">
																					<img src="Images/ForwardUp.jpg" alt="Hider Icon" title="Thiết lập" />
																				</div>
																			</div>
																		</div>
																	</td>
																</tr>
															</table>
														</div>
														
														<div>
															<table>
																<tr>
																	<td>
																		<div class="leftHandUpdateEditContentWrapper" id="datePublicationBeginEditPartBookManager">
																			<a>Ngày xuất bản: </a>
																		</div>
																	</td>
																	
																	<td>
																		<div class="rightHandUpdateEditContentWrapper">
																			<div id="datePublicationEditPartBookManager">
																				<div class="alignMainEditBookManager">
																					<a>${dayPublisherEditBookUpdateManager}</a>
																				</div>
																
																				<div align="center" class="iconShowEditPart hide" id="idDatePublicationIconShowEditPartBookManager">
																					<img src="Images/Forward.jpg" alt="Shower icon" title="Hiển thị" />
																				</div>
																			</div>
																			
																			<div id="idDatePublicationEditPartBookManager" class="hide">
																				<div class="newFieldUpdateBookManager alignMainEditBookManager">
																					<input type="hidden" id="dayPublicationDateUpdateManager" name="n_dayPublicationDateUpdateManager" value="${dayPublisherEditBookUpdateManager}" />
																	
																					<select id="yearEditBookUpdateManager" title="Chỉnh sửa năm">
																						<%
																							for(int i = 1950; i <= 2013; i ++) {
																						%>
																						<option><%= Integer.toString(i) %></option>
																						<%
																							}
																						%>
																					</select>
																					<a> - </a>
																					<select id="monthEditBookUpdateManager" title="Chỉnh sửa tháng">
																						<%
																							for(int i = 1; i <= 12; i ++) {
																						%>
																						<option><%= Integer.toString(i) %></option>
																						<%
																							}
																						%>
																					</select>
																					<a> - </a>
																					<select id="dateEditBookUpdateManager" title="Chỉnh sửa ngày">
																						<%
																							for(int i = 1; i <= 31; i ++) {
																						%>
																						<option><%= Integer.toString(i) %></option>
																						<%
																							}
																						%>
																					</select>
																				</div>
																
																				<div align="center" class="applyFieldUpdateBookManager hide" id="idDatePublicationIconApplyEditPartBookManager">
																					<img src="Images/ForwardUp.jpg" alt="Hider Icon" title="Thiết lập" />
																				</div>
																			</div>
																		</div>
																	</td>
																</tr>
															</table>
														</div>
														
														<div>
															<table>
																<tr>
																	<td>
																		<div class="leftHandUpdateEditContentWrapper" id="publisherBeginEditPartBookManager">
																			<a>Nhà xuất bản</a>
																		</div>
																	</td>
															
																	<td>
																		<div class="rightHandUpdateEditContentWrapper">
																			<div id="publisherMainEditPartBookManager">
																				<div class="alignMainEditBookManager">
																					<a>${publisherEditUpdateBookManager}</a>
																				</div>
																
																				<div align="center" class="iconShowEditPart hide" id="idPublisherIconShowEditPartBookManager">
																					<img src="Images/Forward.jpg" alt="Shower Icon" title="Hiển thị" />
																				</div>
																			</div>
																
																			<div id="idPublisherEditPartBookManager" class="hide">
																				<div class="newFieldUpdateBookManager alignMainEditBookManager">
																					<input size="20" type="text" id="newPublisherEditBookManager" name="n_newPublisherEditBookManager" value="${publisherEditUpdateBookManager}" title="Chỉnh sửa nhà xuất bản" />
																				</div>
																
																				<div align="center" class="applyFieldUpdateBookManager hide" id="idPublisherIconApplyEditPartBookManager">
																					<img src="Images/ForwardUp.jpg" alt="Hider Icon" title="Thiết lập" />
																				</div>
																			</div>
																		</div>
																	</td>
																</tr>
															</table>
														</div>
														
														<div>
															<table>
																<tr>
																	<td>
																		<div class="leftHandUpdateEditContentWrapper" id="summaryBeginEditPartBookManager">
																			<a>Số lượng </a>
																		</div>
																	</td>
															
																	<td>
																		<div class="rightHandUpdateEditContentWrapper">
																			<div id="numberItemMainEditPartBookManager">
																				<div class="alignMainEditBookManager">
																					<a>${numberItemEditUpdateBookManager}</a>
																					<p class="hide" style="color: red; ${styleUpdateNumberBookItemEditUpdateBookManager}">Cập nhật số lượng sách không thành công</p>
																				</div>
																
																				<div align="center" class="iconShowEditPart hide" id="idNumberItemIconShowEditPartBookManager">
																					<img src="Images/Forward.jpg" alt="Shower Icon" title="Hiển thị" />
																				</div>
																			</div>
																			
																			<div id="idNumItemEditPartBookManager" class="hide">
																				<input type="hidden" name="n_numItemEditBookManager" id="idHiddenNumItemEditBookManager" value="${numberItemEditUpdateBookManager}" />
																				<div class="newFieldUpdateBookManager alignMainEditBookManager">
																					<select id="numberItemEditPartBookManager" title="Chỉnh sửa số lượng sách">
																						<%
																							for(int i = 1; i <= 1000; i ++) {
																						%>
																						
																						<option><%= Integer.toString(i) %></option>
																						
																						<%
																							}
																						%>
																					</select>
																				</div>
																
																				<div align="center" class="applyFieldUpdateBookManager hide" id="idNumberItemIconApplyEditPartBookManager">
																					<img src="Images/ForwardUp.jpg" alt="Hider Icon" title="Thiết lập" />
																				</div>
																			</div>
																		</div>
																	</td>
																</tr>
															</table>
														</div>
														
														<div>
															<table>
																<tr>
																	<td>
																		<div class="leftHandUpdateEditContentWrapper" id="summaryBeginEditPartBookManager">
																			<a>Tóm tắt </a>
																		</div>
																	</td>
															
																	<td>
																		<div class="rightHandUpdateEditContentWrapper">
																			<div id="summaryMainEditPartBookManager">
																				<div class="alignMainEditBookManager">
																					<a>${summaryEditUpdateBookManager}</a>
																				</div>
																
																				<div align="center" class="iconShowEditPart hide" id="idSummaryIconShowEditPartBookManager">
																					<img src="Images/Forward.jpg" alt="Shower Icon" title="Hiển thị" />
																				</div>
																			</div>
																			
																			<div id="idSummaryEditPartBookManager" class="hide">
																				<div class="newFieldUpdateBookManager alignMainEditBookManager">
																					<textarea id="summaryEditPartBookManager" name="n_summaryEditPartBookManager" rows="5" cols="20" placeholder="Nhập tóm tắt mới cho sách..." title="Chỉnh sửa tóm tắt của sách">${summaryEditUpdateBookManager}</textarea>
																				</div>
																
																				<div align="center" class="applyFieldUpdateBookManager hide" id="idSummaryIconApplyEditPartBookManager">
																					<img src="Images/ForwardUp.jpg" alt="Hider Icon" title="Thiết lập" />
																				</div>
																			</div>
																		</div>
																	</td>
																</tr>
															</table>
														</div>
														
														<div align="center" style="padding: 10px 0;">
															<input type="submit" name="submitTypeGetEditBookManager" value="Cập nhật" id="submitEditPartBookManager" />
														</div>
														
													</div>
												</div>
											</form>
											
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div id="managerContent3" class="hide">
							<div>
								<div class="acceptLendingBookTitle">
									<p align="center">
										<a style="font-size: 40px; font-weight: bold;">Cho mượn sách</a>
									</p>
								</div>
								
								<div class="acceptLendingBookContent">
									<div class="menuAcceptLendingBookContent">
										<div>
											<div id="menuBeforeRegisterLendingBookContent">
												<a>Có đặt trước</a>
											</div>
											
											<div id="menuNoRegisterLendingBookContent">
												<a>Không đặt trước</a>
											</div>
										</div>
									</div>
									
									<div class="mainAcceptLendingBookContent">
										<div id="beforeRegisterLendingBookContent">
											<div class="formCheckAcceptLendingBookContent">
												<form method="get" action="LendingController">
													<p align="center">
														<a><input type="text" name="n_textCheckAcceptRegisterBookContent" id="textCheckAcceptRegisterBookContent" title="Mã đăng ký sách" placeholder="Nhập mã số đặt sách vào đây..." size="30" /></a>
														<a>  <input type="submit" title="Xem thông tin đăng ký sách" value="Xem" id="submitCheckRegisterBookContent" /></a>
														<input type="hidden" name="n_submitToLendingBookController" value="getBookPlacingInformations" />
													</p>
													
													<p align="center" class="hide" id="errorEmptyLendingCodeBook" style="color: red; font-size: 15px;">*Chưa nhập mã số đặt sách</p>
												</form>
											</div>
											
											<div class="resultCheckAcceptLendingBookContent">
												<div class="hide" style="${styleNotFoundCheckAcceptRegisterBook}">
													<hr />
													
													<p style="color: red; font-size: 30px">Không tìm thấy thông tin đặt sách</p>
												</div>
												
												<div class="hide" style="${styleResultFoundCheckAcceptRegisterBook}">
													<hr />
													
													<form method="post" action="LendingController">
														<table cellpadding="5px">
															<tbody>
																<tr>
																	<td width="30%">Mã đặt sách </td>
																	<td width="70%">${codeCheckAcceptRegisterBook}</td>
																</tr>
																
																<tr>
																	<td width="30%">Mã người đặt </td>
																	<td width="70%">${userIdCheckAcceptRegisterBook}</td>
																</tr>
																
																<tr>
																	<td width="30%">Mã số sách </td>
																	<td width="70%">${codeBookCheckAcceptRegisterBook}</td>
																</tr>
																
																<tr>
																	<td width="30%">Thời gian đặt </td>
																	<td width="70%">${timePlacingCheckAcceptRegisterBook}</td>
																</tr>
															</tbody>
														</table>
														
														<p align="center">
															<input type="hidden" name="n_bookItemIdAcceptRegisterBeforeBook" value="${bookItemIdAcceptRegisterBeforeBook}" />
															<input type="hidden" name="n_codeCheckAcceptRegisterBook" value="${codeCheckAcceptRegisterBook}" />
															<input type="submit" id="allowAcceptLendingThisBook" value="Đồng ý cho mượn" name="n_submitToLendingBookController" title="Đồng ý cho mượn" />
														</p>
														
														<p align="center" class="hide" style="${styleNotifyResultAcceptLendingBook}">
															<a style="font-size: 30px; color: blue;">Cho mượn sách thành công</a>
														</p>
													</form>
												</div>
											</div>
										</div>
										
										<div class="hide" id="noRegisterLendingBookContent">
											<div class="formNoRegisterLendingBookContent">
												<form method="post" action="UserController">
													<div style="margin: 5px 0 0 10px;">
														<p align="center">
															<a><input type="text" name="n_userIdNoRegisterLendingBookContent" id="userIdNoRegisterLendingBookContent" placeholder="Nhập mã số người sử dụng..." title="Mã số người sử dụng" /></a>
															<a class="hide" style="color: red; font-size: 15px;" id="errorUserIdNoRegisterLendingBookContent">Chưa nhập mã người dùng</a>
														</p>
													</div>
													
													<div style="margin: 5px 0 0 10px;">
														<p align="center">
															<input type="text" name="n_bookItemCodeNoRegisterLendingBookContent" id="bookItemCodeNoRegisterLendingBookContent" placeholder="Nhập mã số sách..." title="Mã số sách" />
															<a class="hide" style="color: red; font-size: 15px;" id="errorBookItemCodeNoRegisterLendingBookContent">Chưa nhập mã sách</a>
														</p>		
													</div>	
													
													<div>
														<p align="center">
															<input type="hidden" value="submitCheckForLendingBookNoRegister" name="typeUser" />
															<input type="submit" title="Đồng ý" value="Đồng ý" id="submitFormNotRegisterBeforeLendingBook" />
														</p>
													</div>	
												</form>
											</div>
											
											<div class="resultNoRegisterLendingBookContent hide" style="${styleResultNoRegisterLendingBookContent}">
												<div class="errorUserNoRegisterLendingBook hide" style="${styleErrorUserNoRegisterLendingBookContent}">
													<hr />
													
													<p align="center">
														<a style="color: red; font-size: 30px">Người sử dụng không tồn tại</a>
													</p>
												</div>
												
												<div class="errorBookItemNoRegisterLendingBook hide" style="${styleErrorBookItemNoRegisterLendingBook}">
													<hr />
													
													<div class="hide" style="${styleNotFoundBookItemIdNoRegisterBeforeLendingBook}">
														<p align="center">
															<a style="color: red; font-size: 30px;">Cuốn sách không tồn tại</a>
														</p>
													</div>
													
													<div class="hide" style="${styleErrorNotAvaiableToLendingNoRegister}">
														<p align="center">
															<a style="color: red; font-size: 30px;">Cuốn sách không có sẵn để đặt</a>
														</p>
													</div>
												</div>
												
												<div class="hide" style="${styleInfringeRolesNotRegisterLendingBook}">
													<hr />
													
													<p align="center" style="font-size: 30px;">Người sử dụng này không thể mượn sách do: </p>
													
													<div class="hide" style="${errorOver15DaysNotRegisterLendingBook}">
														<a> - Có sách mượn quá 30 ngày chưa trả</a>
													</div>
													
													<div class="hide" style="${errorOver10BooksNotRegisterLendingBook}">
														<a> - Đã mượn hoặc đặt 10 cuốn sách</a>
													</div>
													
													<div class="hide" style="${errorSameBookNotRegisterLendingBook}">
														<a> - Cuốn sách này đã được mượn</a>
													</div>
												</div>
												
												<div class="successNoRegisterLendingBook hide" style="${styleSuccessLendingBookNotRegister}">
													<hr />
													
													<div>
														<p align="center" style="font-size: 30px;">Thông tin mượn sách</p>
														<table cellpadding="10px">
															<tbody>
																<tr>
																	<td width="40%">
																		Mã mượn sách: 
																	</td>
																	
																	<td>
																		${codeLendBookNoRegisterLendingBook}
																	</td>
																</tr>
																
																<tr>
																	<td width="40%">
																		Mã người dùng:  
																	</td>
																	
																	<td>
																		${userIdNoRegisterLendingBook}
																	</td>
																</tr>
																
																<tr>
																	<td width="40%">
																		Tên người dùng:  
																	</td>
																	
																	<td>
																		${userNameNoRegisterLendingBook}
																	</td>
																</tr>
																
																<tr>
																	<td width="40%">
																		Mã sách:  
																	</td>
																	
																	<td>
																		${codeBookNoRegisterLendingBook}
																	</td>
																</tr>
																
																<tr>
																	<td width="40%">
																		Thời gian mượn sách:  
																	</td>
																	
																	<td>
																		${timeLendBookNoRegisterLendingBook}
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
										
									</div>
								</div>
							</div>
						</div>
							
						<div id="managerContent4" class="hide">
							<div class="titlePayBookPart">
								<p align="center">
									<a style="font-size: 40px; font-weight: bold;">Nhận trả sách</a>
								</p>
							</div>
							
							<div class="contentPayBookPart">
								<div class="formContentPayBook">
									<form action="LendingController" method="get">
										<p align="center">
											<input type="hidden" name="n_submitToLendingBookController" value="submitGetInfsPayBookPart" />
											<a><input type="text" placeholder="Nhập mã số mượn sách..." title="Mã số mượn sách" id="textCodeLendingPayBookPart" name="n_textCodeLendingPayBookPart" size="30" /></a>
											<a>   <input type="submit" id="submitPayBookPart" value="Kiểm tra" /></a>
										</p>
										
										<p id="errorEmptyContentPayBook" align="center" class="hide" style="color: red; font-size: 15px;">*Chưa nhập mã số mượn sách</p>
									</form>
								</div>
								
								<div class="resultContentPayBook">
									
									
									<div class="hide" style="${styleErrorPayBookPart}">
										<hr />
										<a style="color: red; font-size: 30px;">Không tìm thấy mã số mượn sách</a>
									</div>
									
									<div class="hide" style="${styleSuccessPayBookPart}">
										<hr />
										<div style="margin: 10px;">
											<table cellpadding="5px">
												<tbody>
													<tr>
														<td>
															Mã số mượn sách: 
														</td>
														
														<td>
															${codeLendingBookPayBookPart}
														</td>
													</tr>
													
													<tr>
														<td>
															Mã người dùng: 
														</td>
														
														<td>
															${userIdPayBookPart}
														</td>
													</tr>
													
													<tr>
														<td>
															Tên người dùng: 
														</td>
														
														<td>
															${userNamePayBookPart}
														</td>
													</tr>
													
													<tr>
														<td>
															Mã số sách: 
														</td>
														
														<td>
															${codeBookPayBookPart}
														</td>
													</tr>
													
													<tr>
														<td>
															Thời gian mượn sách: 
														</td>
														
														<td>
															${timeLendingPayBookPart}
														</td>
													</tr>
												</tbody>
											</table>
											
											
											<form action="ReservationController" method="post">
												<p align="center">
													<input type="hidden" name="n_codeLendingAcceptPayBookPart" value="${codeLendingBookPayBookPart}" />
													<input type="hidden" name="n_userIdAcceptPayBookPart" value="${userIdPayBookPart}" />
													<input type="hidden" name="n_bookTitleIdAcceptPayBookPart" value="${bookTitleIdPayBookPart}" />
													<input type="hidden" name="n_timeLendingAcceptPayBookPart" value="${timeLendingPayBookPart}" />
													<input type="submit" value="Đồng ý" id="submitToLendingBookController" title="Đồng ý" />
													<input type="hidden" name="n_submitToReservationController" value="submitAcceptPayBookPart" />
												</p>
											</form>
										</div>
									</div>
									
									<div class="hide" style="${styleResponseFromPayBookPart}">
										<p align="center">
											<a style="color: green; font-size: 30px;">Trả sách thành công</a>
										</p>
									</div>
								</div>
							</div>
						</div>
							
						<div id="managerContent5" class="hide">
							
						</div>
							
						<div id="managerContent6" class="hide">
							
						</div>
						
						</div>
					</div>
				</div>
				
				<div class="hide" id="checkBookWrapper">
					<div class="innerWrapperCheckBook">
						<div class="titleAreaCheckBook">
							<p align="center">
								<a><img src="Images/checkBook.jpg" alt="Check book image" title="Kiểm tra sách" />  </a>
								<a style="font-size: 50px; font-weight: bold;">Kiểm tra sách</a>
							</p>
						</div>
						
						<div class="formAreaCheckBook">
							<form action="BookItemController" method="post">
								<p align="center">
									<a><input type="text" id="textBookCodeCheckBook" name="n_textBookCodeCheckBook" size="30" placeholder="Nhập mã số sách muốn kiểm tra..." title="Nhập mã số sách" /></a>
									<a>   <input type="submit" title="Kiểm tra" name="submitTypeGetEditBookManager" value="Kiểm tra" id="submitButtonCheckBook" /></a>
								</p>
							</form>
							
							<p style="color: red; font-size: 20px;" class="hide" align="center" id="errorTextBookCodeCheckBook">*Chưa nhập mã số sách</p>
						</div>
						
						<hr />
						
						<div class="resultAreaCheckBook">
							<div>
								<div class="hide" style="${styleNotFoundCheckBook}">
									<p style="color: red; font-size: 25px; font-weight: bold;">Mã sách không đúng. Xin hãy kiểm tra lại</p>
								</div>
								
								<div class="hide wrapperRealResultCheckBook" style="${styleResultFromCheckBook}">
									<div>
										<div>
											<table>
												<tr>
													<td>
														<div class="eachElementCheckBook">
															<img src="${srcImageCheckBookPart}" alt="${nameBookCheckBookPart}" title="${nameBookCheckBookPart}" />
														</div>
													</td>
													
													<td>
														<div>
															<p>	Mã sách: ${codeBookCheckBookPart}</p>
														</div>
														
														<div>
															<p>	Tên sách: ${nameBookCheckBookPart}</p>
														</div>
														
														<div>
															<p>	Trạng thái: ${statusBookCheckBookPart}</p>
														</div>
													</td>
												</tr>
											</table>
										</div>
										
										<div>
											<table>
												<tr>
													<td>
														<div class="eachElementCheckBook">
															<p>Số lượng sách</p>
														</div>
													</td>
													
													<td>
														<div>
															<p title="Tổng số sách">  Tổng số sách: ${countBookCheckBookPart}</p>
															<p title="Số sách được đặt">  Số lượng sách được đặt: ${numPlacingBookCheckBookPart}</p>
															<p title="Số sách được mượn">  Số lượng sách được mượn: ${numLendingBookCheckBookPart}</p>
														</div>
													</td>
												</tr>
											</table>
										</div>
										
										<div>
											<table>
												<tr>
													<td>
														<div class="eachElementCheckBook">
															<p>Thể loại</p>
														</div>
													</td>
													
													<td>
														<div>
															<p> ${typeExplainBookCheckBookPart}</p>
														</div>
													</td>
												</tr>
											</table>
										</div>
										
										<div>
											<table>
												<tr>
													<td>
														<div class="eachElementCheckBook">
															<p>Tác giả</p>
														</div>
													</td>
													
													<td>
														<div>
															<p> ${authorBookCheckBookPart}</p>
														</div>
													</td>
												</tr>
											</table>
										</div>
										
										<div>
											<table>
												<tr>
													<td>
														<div class="eachElementCheckBook">
															<p>Nhà xuất bản</p>
														</div>
													</td>
													
													<td>
														<div>
															<p> ${publisherBookCheckBookPart}</p>
														</div>
													</td>
												</tr>
											</table>
										</div>
										
										<div>
											<table>
												<tr>
													<td>
														<div class="eachElementCheckBook">
															<p>Ngày xuất bản</p>
														</div>
													</td>
													
													<td>
														<div>
															<p> ${publicationDateBookCheckBookPart}</p>
														</div>
													</td>
												</tr>
											</table>
										</div>
										
										<div>
											<table>
												<tr>
													<td>
														<div class="eachElementCheckBook">
															<p>Tóm tắt</p>
														</div>
													</td>
													
													<td>
														<div>
															<p>${summaryBookCheckBookPart}</p>
														</div>
													</td>
												</tr>
											</table>
										</div>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="hide" id="seachBookWrapper" style="font-size: 15px;">
					<div class="titleResultSearchBookPart">
						<p align="center">
							<a><img src="Images/searchTool.jpg" alt="Kết quả tìm kiếm" title="Kết quả tìm kiếm" />  </a>
							<a style="font-size: 50px; font-weight: bold;">Kết quả tìm kiếm</a>
						</p>
					</div>
					
					<div class="innerWrapperSearchBook">
						<div>
							${resultFromSearchBook}
							<!--
							<p style='color: blue; font-style: italic; font-weight: bold; font-size: 20px;'>Tìm thấy 5 kết quả</p>
							
							<hr />
							
							<div style='margin-bottom: 5px; border-bottom: rgb(156, 176, 130) solid 2px;'>
								<table>
									<tbody>
										<tr>
											<td>
												<div style='margin: 0 5px; width: 285px; height: 370px; border-radius: 5px; border-right: rgb(156, 176, 130) solid 1px;'>
													<a>
														<img src='' alt='' title='' width="285px" height='370px' />
													</a>
												</div>
											</td>
											
											<td>
												<div>
													<div style='margin-bottom: 10px;'>
														<a>Mã sách: </a>
													</div>
													
													<div style='margin-bottom: 10px;'>
														<a>Tên sách: </a>
													</div>
													
													<div style='margin-bottom: 10px;'>
														<a>Tác giả: </a>
													</div>
													
													<div style='margin-bottom: 10px;'>
														<a>Nhà xuất bản: </a>
													</div>
													
													<div style='margin-bottom: 10px;'>
														<a>Ngày xuất bản: </a>
													</div>
													
													<div style='margin-bottom: 10px;'>
														<a>Thể loại: </a>
													</div>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							-->
						</div>
						
					</div>
				</div>
			
				<div class="hide" id="placeBookWrapper">
					<div class="innerWrapperPlaceBookPart">
						<div class="titlePlaceBookPart">
							<p align="center">
								<a><img src="Images/placeBook.jpg" alt="Icon đặt sách" title="Đặt sách" /></a>
								<a style="font-size: 60px; font-weight: bold;">  Đặt sách</a>
							</p>
						</div>
						
						<div class="formPlaceBookPart">
							<form action="LendingController" method="get">
								<input type="hidden" name="userIdPlaceBookPart" value="${userId}" />
								<p align="center">
									<a>
										<input type="text" name="n_textPlaceBookPart" id="textPlaceBookPart" size="30" placeholder="Nhập mã số sách muốn đặt..." title="Nhập mã số sách" />
									</a>
									
									<a>
										   <input type="submit" id="submitPlaceBookPart" name="n_submitToLendingBookController" value="Đặt sách" title="Đặt sách" />
									</a>
									
									<p align="center" id="errorEmptyTextboxPlaceBookPart" class="hide" style="font-size: 15px; color: red;">*Chưa nhập mã số sách</p>
								</p>
							</form>
						</div>
						
						<hr />
						
						<div class="mainPlaceBookPart">
							<div>
								<div class="resultSuccessPlaceBook hide" style="${styleSuccessResutPlaceBook}">
									<p align="center" style="font-size: 30px; color: blue;">Đặt sách thành công</p>
								
									<div>
										<p>Thông tin sách vừa mượn của bạn</p>
										
										<div>	
											<table>
												<tr>
													<td>
														<div class="styleLeftSideResultPlaceBook">
															Mã đặt sách
														</div> 
													</td>
													<td>
														<div style="text-align: center;">
															${codePlacingResultPlaceBook}
														</div>
													</td>
												</tr>
											</table>
										</div>
										
										<div>	
											<table>
												<tr>
													<td>
														<div class="styleLeftSideResultPlaceBook">
															Mã sách
														</div> 
													</td>
													<td>
														<div style="text-align: center;">
															${codeBookResultPlaceBook}
														</div>
													</td>
												</tr>
											</table>
										</div>
										
										<div>	
											<table>
												<tr>
													<td>
														<div class="styleLeftSideResultPlaceBook">
															Tên sách
														</div> 
													</td>
													<td>
														<div style="text-align: center;">
															${nameBookResultPlaceBook}
														</div>
													</td>
												</tr>
											</table>
										</div>
										
										<div>	
											<table>
												<tr>
													<td>
														<div class="styleLeftSideResultPlaceBook">
															Thời gian mượn
														</div> 
													</td>
													<td>
														<div style="text-align: center;">
															${timePlacingResultPlaceBook}
														</div>
													</td>
												</tr>
											</table>
										</div>
											
									</div>
								</div>
								
								<div class="hide" style="${styleErrorPlaceBook}">
									<p align="center" style="font-size: 30px; color: red;">Đặt sách thất bại</p>
								
									<div style="margin-top: 80px;">
										<p style="font-size: 25px;">Các vi phạm của bạn: </p>
										<p class="hide" style="${errorOver15Days}">- Bạn mượn sách quá 15 ngày chưa trả</p>
										<p class="hide" style="${errorOver10BooksLending}">- Bạn đã mượn 10 quyển sách. Bạn không thể đặt thêm sách</p>
										<p class="hide" style="${errorSameBook}">- Cuốn sách này bạn đã mượn hoặc đã đặt</p>
										<p class="hide" style="${errorNotAvaiablePlaceBook}">- Cuốn sách bạn muốn đặt không có sẵn</p>
										<p class="hide" style="${errorNotExistBookItem}">- Cuốn sách không tồn tại</p>
									</div>
									
									<p style="padding-top: 10px; font-size: 25px;">Làm ơn kiểm tra lại lịch sử và đọc nội quy để biết cách xử lý</p>
								</div>
								
							</div>
						</div>
						
					</div>
				</div>
				
				<div class="hide" id="historyWrapper"></div>
				
			</div>		
		</div>
		
		<div class="copyright shadown">
			<div class="logoFooter">
				<div class="logoContent">
					<div class="logoImage"><img src="Images/Library-icon.png" alt="Logo BELibrary" /></div>
					<div class="copyrightDescript">
						<div>Logo và bản quyền của BE 2013.</div>
						<div>© 2013, The Copyright BE of the Vietnam.  All rights reserved.</div>
					</div>
				</div>
			</div>
			
			<div class="contactFooter">
				<div class="contactContent">
					<div>Contact:</div>
					<div>Email: belibrarycenter@gmail.com</div>
					<div>Được thiết kế bởi Dung Harry</div>
					<div>Điện thoại: (+84)973 675 143</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>