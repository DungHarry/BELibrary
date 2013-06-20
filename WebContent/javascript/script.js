function checkEmptyById(idElement, idNotify, message) {
	//Get needed elements by id
	
	var element = document.getElementById(idElement);
	var notify = document.getElementById(idNotify);
	
	if((element.value == '') || (element.value == null)) {
		//Insert notify
		
		notify.innerHTML = message;
		notify.style.display = 'block';
		
		return false;
	}
	else {
		notify.style.display = 'none';
		
		return true;
	}
}
//Declare function to check on startup Website

function checkOnStartup(idName, idError) {
	var eName = document.getElementById(idName);
	var eError = document.getElementById(idError);
	
	if((eName.value == '') || (eName.value == null)) {
		eError.style.display = 'none';
	}
	else {
		eError.style.display = 'block';
	}
}
/*Create slide show for top*/

function createSlide(element, type, speedTime, timeoutTime) {
	$(document).ready(function(){
		$(element).cycle({
			fx: type,
			speed: speedTime,
			timeout: timeoutTime
		});
	});
}
//Run slide effect

createSlide(".slideTop", 'fade', 1000, 5000);
createSlide("#bookSlide1", 'fadeZoom', 1000, 3000);
createSlide("#bookSlide2", 'slideY', 1000, 2000);
createSlide("#bookSlide3", 'cover', 1000, 6000);
createSlide("#bookSlide4", 'growX', 1000, 4000);
//Create effect to show an element by id

function showElement(idElement) {
	document.getElementById(idElement).style.display = 'block';
}
//Create function for hide an element by id

function hideElement(idElement) {
	document.getElementById(idElement).style.display = 'none';
}
//Create slide down & slide up effect

function slideDown(sourceElement, targetElement, duration, cssProperty, valueCss) {
	$(document).ready(function(){
		$(sourceElement).click(function(){
			$(targetElement).slideDown(duration, function() {
				//$(targetElement).css(cssProperty, valueCss);
			});
		});
	});
}

function slideUp(sourceElement, targetElement, duration, cssProperty, valueCss) {
	$(document).ready(function(){
		$(sourceElement).click(function(){
			$(targetElement).slideUp(duration, function() {
				//$(targetElement).css(cssProperty, valueCss);
			});
		});
	});
}

function slideToggle(sourceElement, targetElement, duration, cssProperty, valueCss) {
	$(document).ready(function(){
		$(sourceElement).click(function(){
			$(targetElement).slideToggle(duration, function() {
				//$(targetElement).css(cssProperty, valueCss);
			});
		});
	});
}

slideToggle('.topInner2 a', '#divSelections', 500, 'display', 'block');
slideUp('#divSelections div', '#divSelections', 500, 'display', 'none');
//Report functions for effect of introductions topic

function focusChooser(idElement) {
	document.getElementById("introSlide1").className = '';
	document.getElementById("introSlide2").className = '';
	document.getElementById("introSlide3").className = '';
	document.getElementById("introSlide4").className = '';
	document.getElementById("introSlide5").className = '';
	
	document.getElementById(idElement).className = 'chooserEffectClick';
}

function createEffectSlideIntroduction(idSource, idTarget, arrayElements, durationUp, durationDown, cssClass) {
	$(document).ready(function(){
		$('#' + idSource).click(function(){
			var i = 0;
			
			for(i = 0; i < arrayElements.length; i ++) {
				$('#' + arrayElements[i]).slideUp(durationUp, function() {
					//$(targetElement).css(cssProperty, valueCss);
				});
			}
			
			$('#' + idTarget).slideDown(durationDown, function() {
				//$('#' + idTarget).attr('class', cssClass);
			});
		});
	});
}

var arrayIntroductionSource = new Array("introSlide1", "introSlide2", "introSlide3", "introSlide4", "introSlide5");
var arrayIntroductionTarget = new Array("introContent1", "introContent2", "introContent3", "introContent4", "introContent5");

for(var i = 0; i < arrayIntroductionSource.length; i ++) {
	createEffectSlideIntroduction(arrayIntroductionSource[i], arrayIntroductionTarget[i], arrayIntroductionTarget, 1000, 3000, 'shadown');
}

function createEffectForwardContent(elementSource, elementTarget, arrayElements, durationUp, durationIn, cssClass) {
	$(document).ready(function(){
		$(elementSource).click(function(){
			var i = 0;
			
			for(i = 0; i < arrayElements.length; i ++) {
				
				$(arrayElements[i]).fadeOut(durationUp, function() {
					
				});
			}
			
			$(elementTarget).fadeIn(durationIn, function() {
				//$('#' + idTarget).attr('style', 'display: block;');
			});
		});
	});
}

var arraySourceContent = new Array("#userDisplay", "#introductChooser", "#checkBookChooser", "#placeBookChooser", "#historyChooser", "#divSelections div", "#idShowSearchTapTop");
var arrayTargetContent = new Array("#userInforsPart", "#introductWrapper", "#checkBookWrapper", "#placeBookWrapper", "#historyWrapper", "#managerWrapper", "#seachBookWrapper");

for(var i = 0; i < arrayTargetContent.length; i ++) {
	createEffectForwardContent(arraySourceContent[i], arrayTargetContent[i], arrayTargetContent, 1000, 3000, 'hide');
}
/*
Create javascript for user information viewer
*/

function handleMouseOverInfs(elementSource, elementTarget, timeDown, timeUp) {
	$(document).ready(function() {
		$(elementSource).mouseenter(function() {
			$(elementTarget).slideDown(timeDown);
		}).mouseleave(function(){
			$(elementTarget).slideUp(timeUp);
		});
	});
}

function handleMouseClickInfs(elementSource, elementHideTarget, elementShowTarget, timeDown, timeUp) {
	$(document).ready(function() {
		$(elementSource).click(function() {
			$(elementHideTarget).fadeOut(timeUp);
			$(elementShowTarget).fadeIn(timeDown);
		});
	});
}

var arrayOverElementsUser = new Array("infsNameDefine", "infsNameChange", "infsBirthdayDefine", "infsBirthdayChange", "infsGenderDefine", "infsGenderChange", "infsJobDefine", "infsJobChange", "infsAddressDefine", "infsAddressChange", "infsPhoneDefine", "infsPhoneChange", "infsEmailDefine", "infsEmailChange", "infsAdditionDefine", "infsAdditionChange", "infsPasswordDefine", "infsPasswordChange");
var arrayEditElementsUser = new Array("editInfsUserName", "applyInfsUserName", "editInfsBirthday", "applyInfsBirthday", "editInfsGender", "applyInfsGender", "editInfsJob", "applyInfsJob", "editInfsAddress", "applyInfsAdress", "editInfsPhone", "applyInfsPhone", "editInfsEmail", "applyInfsEmail", "editInfsAddition", "applyInfsAddition", "editInfsPassword", "applyInfsPassword");
var arrayCancelElements = new Array("cancelInfsName", "cancelInfsBirthday", "cancelInfsGender", "cancelInfsJob", "cancelInfsAddress", "cancelInfsPhone", "cancelInfsEmail", "cancelInfsAddition", "cancelInfsPassword");

for(var i = 0; i < arrayOverElementsUser.length; i ++) {
	handleMouseOverInfs('#' + arrayOverElementsUser[i], '#' + arrayEditElementsUser[i], 1000, 500);
}

for(var i = 0; i < arrayEditElementsUser.length; i ++) {
	if((i % 2) == 0) {
		handleMouseClickInfs('#' + arrayEditElementsUser[i], '#' + arrayOverElementsUser[i], '#' + arrayOverElementsUser[i + 1], 2000, 500);
	}
	else {
		handleMouseClickInfs('#' + arrayEditElementsUser[i], '#' + arrayOverElementsUser[i], '#' + arrayOverElementsUser[i - 1], 2000, 500);
	}
}

for(var i = 0; i < arrayCancelElements.length; i ++) {
	handleMouseClickInfs('#' + arrayCancelElements[i], '#' + arrayOverElementsUser[2*i + 1], '#' + arrayOverElementsUser[2*i], 2000, 2000);
}
//Create function allow we can set value of array elements by click event with index corresponding

function changeValueOfElementsByClick(elementClick, arraySource, arrayTarget) {
	$(document).ready(function() {
		$(elementClick).click(function() {
			for(var i = 0; i < arrayTarget.length; i ++) {
				$(arrayTarget[i]).attr("value", $(arraySource[i]).val());
			}
		});
	});
}

var timeArrayStore = new Array("#infsHiddenDateBirthday", "#infsHiddenMonthBirthday", "#infsHiddenYearBirthday");
var valueTimeArray = new Array("#selectDateBirthdayChange", "#selectMonthBirthdayChange", "#selectYearBirthdayChange");

var genderArrayStore = new Array("#infsGenderHidden");
var valueGenderArray = new Array("#selectUserGenderChange");

changeValueOfElementsByClick("#applyInfsBirthday", valueTimeArray, timeArrayStore);
changeValueOfElementsByClick("#applyInfsGender", valueGenderArray, genderArrayStore);

timeArrayStore = new Array("infsGenderHidden");
valueTimeArray = new Array("selectuUserGenderChange");

changeValueOfElementsByClick("#applyInfsGender", valueTimeArray, timeArrayStore);
//Report for function to check password within password of user informations

function checkPasswordInformations(idOldPassword, firstNewPassword, secondNewPassword, idEntire, firstWarring, secondWarring) {
	var elementOldPassword = document.getElementById(idOldPassword);
	var elementEntire = document.getElementById(idEntire);
	var elementFirstPassword = document.getElementById(firstNewPassword);
	var elementSecondPassword = document.getElementById(secondNewPassword);
	var elementFirstWarring = document.getElementById(firstWarring);
	var elementSecondWarring = document.getElementById(secondWarring);
	
	if((elementOldPassword.value != elementEntire.value) || (elementFirstPassword.value != elementSecondPassword.value)) {
		
		if(elementOldPassword.value != elementEntire.value) {
			elementFirstWarring.innerHTML = '*Mật khẩu hiện tại không chính xác';
			elementFirstWarring.style.display = 'block';
		}
		
		if(elementFirstPassword.value != elementSecondPassword.value) {
			elementSecondWarring.innerHTML = '*Nhập lại mật khẩu mới không chính xác';
			elementSecondWarring.style.display = 'block';
		}
		
		return false;
	}
	else if((elementFirstPassword.value == null) || (elementFirstPassword.value == '')) {
		elementSecondWarring.innerHTML = '*Mật khẩu mới không được trống';
		elementSecondWarring.style.display = 'block';
	} else {
		elementFirstWarring.style = 'display: none;';
		elementSecondWarring.style = 'display: none;';
			
		return true;
	}
}
//Create function to set value of hidden input when click into button

function setValueHiddenByClick(elementButtonClick, idElementSet, value) {
	$(document).ready(function() {
		$(elementButtonClick).click(function() {
			$(idElementSet).attr('value', value);
		});
	});
}

var elementArrayClickUpdateUser = new Array("#applyInfsUserName", "#applyInfsBirthday", "#applyInfsGender", "#applyInfsJob", "#applyInfsAdress", "#applyInfsPhone", "#applyInfsEmail", "#applyInfsAddition", "#infsValueNewPassword");
var valueArrayClickUpdateUser = new Array("updateUserName", "updateUserBirthday", "updateUserGender", "updateUserJob", "updateUserAddress", "updateUserPhone", "updateUserMail", "updateAdditionalLibrarian", "updateUserPassword");

for(var i = 0; i < elementArrayClickUpdateUser.length; i ++) {
	setValueHiddenByClick(elementArrayClickUpdateUser[i], "#infsTypeUpdateChange", valueArrayClickUpdateUser[i]);
}
/*
 * Create script for librarian manager
 */
//Create effect for slide down for manager choosers

slideToggle('#dropdownManagerChooser', '#divManagerChooserWrapper', 500, 'display', 'block');
//Create function for scroll content div

function createEffectSlideManager(elementSource, elementTarget, arrayElements, durationUp, durationDown, cssClass) {
	$(document).ready(function(){
		$(elementSource).click(function(){
			var i = 0;
			
			for(i = 0; i < arrayElements.length; i ++) {
				$(arrayElements[i]).slideUp(durationUp, function() {
					//$(targetElement).css(cssProperty, valueCss);
				});
			}
			
			$(elementTarget).slideDown(durationDown, function() {
				//$('#' + idTarget).attr('class', cssClass);
	
			});
		});
	});
}

var firstArrayManagerChooser = new Array("#managerChooser1", "#managerChooser2", "#managerChooser3", "#managerChooser4", "#managerChooser5", "#managerChooser6");
var secondArrayManagerChooser = new Array("#managerChooserBar1", "#managerChooserBar2", "#managerChooserBar3", "#managerChooserBar4", "#managerChooserBar5", "#managerChooserBar6");
var arrayDestinationContent = new Array("#managerContent1", "#managerContent2", "#managerContent3", "#managerContent4", "#managerContent5", "#managerContent6");

for(var i = 0; i < firstArrayManagerChooser.length; i ++) {
	createEffectSlideManager(firstArrayManagerChooser[i], arrayDestinationContent[i], arrayDestinationContent, 2000, 4000, 'hide');
	createEffectSlideManager(secondArrayManagerChooser[i], arrayDestinationContent[i], arrayDestinationContent, 2000, 4000, 'hide');
}
//Create function for change event within select element

function createEffectChangeSelect(elementSource, elementTarget) {
	$(document).ready(function() {
		$(elementSource).change(function() {
			var value = $(elementSource).val();
			$(elementTarget).val(value);
		});
	});
}

var arraySourceChangeSelect = new Array("#userDateBirthdaySelection", "#userMonthBirthdaySelection", "#userYearBirthdaySelection", "#userGenderInsertSelection");
var arrayDestinationChangeSelect = new Array("#idDateBirthdayInsertion", "#idMonthBirthdayInsertion", "#idYearBirthdayInsertion", "#idUserGenderInsertion");

for(var i = 0; i < arraySourceChangeSelect.length; i ++) {
	createEffectChangeSelect(arraySourceChangeSelect[i], arrayDestinationChangeSelect[i]);
}
//Create function to check when submit to insert user

function checkClickSubmitUserInsertion(idElementArray, idErrorArray) {
	var elementCheck;
	var elementError;
	var isValid = true;
	
	for(var i = 0; i < idElementArray.length; i ++) {
		elementCheck = document.getElementById(idElementArray[i]);
		elementError = document.getElementById(idErrorArray[i]);
	
		if((elementCheck.value == '') || (elementCheck.value == null)) {
			elementError.style.display = 'block';
		
			isValid = false;
		}
		else {
			elementError.style.display = 'none';
		}
	}
	
	return isValid;
}

var elementErrorToDisplay = new Array('errorNameInsertion', 'errorEmailInsertion', 'errorAddressInsertion', 'errorPhoneInsertion', 'errorJobInsertion');
var elementToCheck = new Array('idUserNameInsertion', 'idUserEmailInsertion', 'idUserAddressInsertion', 'idUserPhoneInsertion', 'idUserJobInsertion');

$(document).ready(function() {
	$('#submitUserInsertion').click(function() {
		return checkClickSubmitUserInsertion(elementToCheck, elementErrorToDisplay);
	});
});

function checkPasswordInsertion(idPasswordElement, idRepasswordElement, idError) {
	var elementPassword = document.getElementById(idPasswordElement);
	var elementRepassword = document.getElementById(idRepasswordElement);
	var elementError = document.getElementById(idError);
	
	if((elementPassword.value == '') || (elementRepassword.value == '') || (elementPassword.value == null) || (elementRepassword.value == null)) {
		elementError.innerHTML = "*Chưa nhập mật khẩu";
		elementError.style.display = 'block';
		
		return false;
	}
	else if(elementPassword.value != elementRepassword.value) {
		elementError.innerHTML = "*Mật khẩu nhập lại không chính xác";
		elementError.style.display = 'block';
		
		return false;
	}
	else {
		elementError.style.display = 'none';
		
		return true;
	}
}

$(document).ready(function() {
	$('#submitUserInsertion').click(function() {
		return checkPasswordInsertion("passwordInsertion", "repasswordInsertion", "errorPasswordInsertion");
	});
});
//Set value for type of Get Request

$(document).ready(function() {
	$('#submitUserInsertion').click(function() {
		$("#idRequestInsertUser").val("insertUser");
	});
});
/*
 * Create script for book manager with librarian
 */

var arrayElementsSource = new Array('imageEntireBookManager', 'imageNameEntireBookManager', 'titleBookContentManager', 'authorBookContentManager', 'symbolBookContentManager', 'explainBookContentManager', 'summaryBookContentManager', 'publisherBookManager');
var arrayElementsTarget = new Array('imageEntireBookManagerError', 'imageNameEntireBookManagerError', 'titleBookManagerError', 'authorBookContentManagerError', 'symbolBookContentManagerError', 'explainBookContentManagerError', 'summaryBookContentManagerError', 'publisherBookManagerError');

$(document).ready(function() {
	$('#submitInsertBookManager').click(function() {
		return checkClickSubmitUserInsertion(arrayElementsSource, arrayElementsTarget);
	});
});

var arraySources = new Array("#yearPublicationSelection", "#monthPublicationSelection", "#datePublicationSelection", "#sumBookItemBookManager");
var arrayTargets = new Array("#yearPublicationBookContentManager", "#monthPublicationBookContentManager", "#datePublicationBookContentManager", "#hiddenSumBookItemBookManager");

for(var i = 0; i < arraySources.length; i ++) {
	createEffectChangeSelect(arraySources[i], arrayTargets[i]);
}

$(document).ready(function() {
	$('#submitInsertBookManager').click(function() {
		$('#typeRequestInsertBookManager').val("insertionBookPostRequest");
	});
	
	$('#uploadImageEntireBookManager').click(function() {
		var nameImageBookItem = $('#nameImageEntireBookManager').val();
		
		$('#imageDirectoryInsertBookManager').val(nameImageBookItem);
	});
});
//Create effect for update book manager part

var arraySelectionsUpdateBookManager = new Array('#updateChooserManager1', '#updateChooserManager2');
var arrayContentsUpdateBookManager = new Array('#updateContentChooser1', '#updateContentChooser2');

for(var i = 0; i < arraySelectionsUpdateBookManager.length; i ++) {
	createEffectSlideManager(arraySelectionsUpdateBookManager[i], arrayContentsUpdateBookManager[i], arrayContentsUpdateBookManager, 1500, 3000, 'hide');
}

function createEffectChooser(elementHighline, arrayElements) {
	$(document).ready(function() {
		$(elementHighline).click(function() {
			for(var j = 0; j < arrayElements.length; j ++) {
				$(arrayElements[j]).attr('class', '');
			}
				
			$(elementHighline).attr('class', 'chooserEffectClickBook');
		});
	});
}

for(var i = 0; i < arraySelectionsUpdateBookManager.length; i ++) {
	createEffectChooser(arraySelectionsUpdateBookManager[i], arraySelectionsUpdateBookManager);
}
/*
 *	Create effect for update book informations 
*/

const SHOW_EDIT_PART = 1;
const APPLY_EDIT_PART = 2;

function chooseChangeBookInformation(elementClick, elementShow, elementHide) {
	$(document).ready(function() {
		$(elementShow).mouseover(function() {
			$(elementClick).fadeIn(2000);
		}).mouseout(function() {
			$(elementClick).fadeOut(2000);
		});
		
		$(elementClick).click(function() {
			$(elementClick).slideUp(2000);
			$(elementShow).slideUp(3000);
			$(elementHide).slideDown(2000);
		});
	});
}

var arrayElementsClickShowEditBook = new Array("#idNameIconShowEditPartBookManager", "#idAuthorIconShowEditPartBookManager", "#idDatePublicationIconShowEditPartBookManager", "#idPublisherIconShowEditPartBookManager", "#idSummaryIconShowEditPartBookManager", "#idNumberItemIconShowEditPartBookManager");
var arrayElementsClickHideEditBook = new Array("#idNameIconApplyEditPartBookManager", "#idAuthorIconApplyEditPartBookManager", "#idDatePublicationIconApplyEditPartBookManager", "#idPublisherIconApplyEditPartBookManager", "#idSummaryIconApplyEditPartBookManager", "#idNumberItemIconApplyEditPartBookManager");
var arrayElementFirstEditBook = new Array("#nameMainEditPartBookManager", "#authorMainEditPartBookManager", "#datePublicationEditPartBookManager", "#publisherMainEditPartBookManager", "#summaryMainEditPartBookManager", "#numberItemMainEditPartBookManager");
var arrayElementSecondEditBook = new Array("#idNameEditPartBookManager", "#idAuthorEditPartBookManager", "#idDatePublicationEditPartBookManager", "#idPublisherEditPartBookManager", "#idSummaryEditPartBookManager", "#idNumItemEditPartBookManager");

for(var i = 0; i < arrayElementsClickShowEditBook.length; i ++) {
	chooseChangeBookInformation(arrayElementsClickShowEditBook[i], arrayElementFirstEditBook[i], arrayElementSecondEditBook[i]);
	chooseChangeBookInformation(arrayElementsClickHideEditBook[i], arrayElementSecondEditBook[i], arrayElementFirstEditBook[i]);
}

//Declare for function to apply text to interface when click apply button

function applyTextChangeBookManager(elementClick, elementSource, elementTarget) {
	$(document).ready(function() {
		$(elementClick).click(function() {
			$(elementTarget).text($(elementSource).val());
		});
	});
}

function applyDatePublicationEditBookManager(elementClick, elementSourceArray, elementTargetArray) {
	$(document).ready(function() {
		$(elementClick).click(function() {
			$(elementTargetArray[0]).text($(elementSourceArray[0]).val() + "-" + $(elementSourceArray[1]).val() + "-" + $(elementSourceArray[2]).val());
			$(elementTargetArray[1]).val($(elementSourceArray[0]).val() + "-" + $(elementSourceArray[1]).val() + "-" + $(elementSourceArray[2]).val());
		});
	});
}

var arrayElementMainEditBook = new Array("#nameMainEditPartBookManager a", "#authorMainEditPartBookManager a", "#publisherMainEditPartBookManager a", "#summaryMainEditPartBookManager a", "#numberItemMainEditPartBookManager a");
var arrayElementSourceEditBook = new Array("#titleUpdateBookManager", "#authorEditBookUpdateManager", "#newPublisherEditBookManager", "#summaryEditPartBookManager", "#numberItemEditPartBookManager");
var arrayElementClickEditBook = new Array("#idNameIconApplyEditPartBookManager", "#idAuthorIconApplyEditPartBookManager", "#idPublisherIconApplyEditPartBookManager", "#idSummaryIconApplyEditPartBookManager", "#idNumberItemIconApplyEditPartBookManager");

for(var i = 0; i < arrayElementMainEditBook.length; i ++) {
	applyTextChangeBookManager(arrayElementClickEditBook[i], arrayElementSourceEditBook[i], arrayElementMainEditBook[i]);
}

var arrayElementSourceDateEditBook = new Array("#yearEditBookUpdateManager", "#monthEditBookUpdateManager", "#dateEditBookUpdateManager");
var arrayElementTargetDateEditBook = new Array("#datePublicationEditPartBookManager a", "#dayPublicationDateUpdateManager");

applyDatePublicationEditBookManager("#idDatePublicationIconApplyEditPartBookManager", arrayElementSourceDateEditBook, arrayElementTargetDateEditBook);

createEffectChangeSelect("#numberItemEditPartBookManager", "#idHiddenNumItemEditBookManager");
/*
 * Create script for check book and
 */

var arraySourceSubmitCheckBook = new Array("textBookCodeCheckBook");
var arrayErrorSubmitCheckBook = new Array("errorTextBookCodeCheckBook");

$(document).ready(function() {
	$("#submitButtonCheckBook").click(function() {
		return checkClickSubmitUserInsertion(arraySourceSubmitCheckBook, arrayErrorSubmitCheckBook);
	});
});

/*
 * Create script for place book 
 */

var arraySourceSubmitPlaceBook = new Array("textPlaceBookPart");
var arrayErrorSubmitPlaceBook = new Array("errorEmptyTextboxPlaceBookPart");

$(document).ready(function() {
	$("#submitPlaceBookPart").click(function() {
		return checkClickSubmitUserInsertion(arraySourceSubmitPlaceBook, arrayErrorSubmitPlaceBook);
	});
});
/*
 * Create effect for accept lending book part
 */

var arrayMenuChooserAcceptLendingBook = new Array("#menuBeforeRegisterLendingBookContent", "#menuNoRegisterLendingBookContent");
var arrayContentChooserAcceptLendingBook = new Array("#beforeRegisterLendingBookContent", "#noRegisterLendingBookContent");

slideDown(arrayMenuChooserAcceptLendingBook[0], arrayContentChooserAcceptLendingBook[0], 3000, "display", "block");
slideUp(arrayMenuChooserAcceptLendingBook[0], arrayContentChooserAcceptLendingBook[1], 2000, "display", "none");
slideDown(arrayMenuChooserAcceptLendingBook[1], arrayContentChooserAcceptLendingBook[1], 3000, "display", "block");
slideUp(arrayMenuChooserAcceptLendingBook[1], arrayContentChooserAcceptLendingBook[0], 2000, "display", "none");

for(var i = 0; i < arrayMenuChooserAcceptLendingBook.length; i ++) {
	createEffectChooser(arrayMenuChooserAcceptLendingBook[i], arrayMenuChooserAcceptLendingBook);
}

var arraySourceRegisterBeforeLendingBook = new Array("textCheckAcceptRegisterBookContent");
var arrayErrorRegisterBeforeLendingBook = new Array("errorEmptyLendingCodeBook");

$(document).ready(function() {
	$("#submitCheckRegisterBookContent").click(function() {
		return checkClickSubmitUserInsertion(arraySourceRegisterBeforeLendingBook, arrayErrorRegisterBeforeLendingBook);
	});
});

var arraySourceNoRegisterBeforeLendingBook = new Array("userIdNoRegisterLendingBookContent", "bookItemCodeNoRegisterLendingBookContent");
var arrayErrorNoRegisterBeforeLendingBook = new Array("errorUserIdNoRegisterLendingBookContent", "errorBookItemCodeNoRegisterLendingBookContent");

$(document).ready(function() {
	$("#submitFormNotRegisterBeforeLendingBook").click(function() {
		return checkClickSubmitUserInsertion(arraySourceNoRegisterBeforeLendingBook, arrayErrorNoRegisterBeforeLendingBook);
	});
});
/*
 * Create script for pay book part
 */

var arraySourcePayBook = new Array("textCodeLendingPayBookPart");
var arrayErrorRegisterPayBookPart = new Array("errorEmptyContentPayBook");

$(document).ready(function() {
	$("#submitPayBookPart").click(function() {
		return checkClickSubmitUserInsertion(arraySourcePayBook, arrayErrorRegisterPayBookPart);
	});
});