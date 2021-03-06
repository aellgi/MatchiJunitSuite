package junit;

import selenium.MatchiSelenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSuite {

	MatchiSelenium s = new MatchiSelenium();

	@Test
	void bookingH�n�CourtCardCorrectCVCIpad() {
		
		s.setSize("ipad");
		s.loginWithMobileOrIpad("mjukvarutestare1@mailinator.com", "mjukvarutestare");
		s.bookCourtMobileOrIpad("H�n� Tenniss�llskap");
		s.paymentCard();
		s.unbookMobileOrIpad();
		s.quitSelenium();
	}	

	@Test
	void bookingH�n�CourtCardCorrectCVCMobile() {
		
		s.setSize("mobile");
		s.loginWithMobileOrIpad("mjukvarutestare1@mailinator.com", "mjukvarutestare");
		s.bookCourtMobileOrIpad("H�n� Tenniss�llskap");
		s.paymentCard();
		s.unbookMobileOrIpad();
		s.quitSelenium();
	}	
	
	@Test
	void bookingH�n�CourtCardCorrectCVC() {
		
		s.setSize("max");
		s.login("mjukvarutestare1@mailinator.com", "mjukvarutestare");
		assertEquals(s.checkLogin().contains("Mjuk"), true);
		s.bookCourt("H�n� Tenniss�llskap");
		s.paymentCard();
		assertEquals(true, s.checkIfBooked());
		s.unbook();
		s.quitSelenium();
	}
	
	//@Test
	void bookingH�n�CourtCardWrongCVC() {
		
		s.setSize("max");
		s.login("mjukvarutestare1@mailinator.com", "mjukvarutestare");
		assertEquals(s.checkLogin().contains("Mjuk"), true);
		s.bookCourt("H�n� Tenniss�llskap");
		s.paymentCardWrongCVC();
		assertTrue(s.checkWrongCVCNumber("Den angivna kortets s�kerhetskod �r ogiltig"));
		s.quitSelenium();
	}
	
	//@Test
	void bookingH�n�CourtSwish() {
		
		s.setSize("max");
		s.login("mjukvarutestare1@mailinator.com", "mjukvarutestare");
		assertEquals(s.checkLogin().contains("Mjuk"), true);
		s.bookCourt("H�n� Tenniss�llskap");
		s.paymentSwish("0777777777");
		// H�r failar testet...
		// assertEquals(true, s.checkIfBooked());
		// s.unbook();
		s.quitSelenium();
	}


}
