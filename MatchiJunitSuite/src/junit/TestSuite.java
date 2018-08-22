package junit;

import selenium.MatchiSelenium;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSuite {

	MatchiSelenium s = new MatchiSelenium();

	//@Test
	void bookingAFreeCourt() {
		s.login("mjukvarutestare1@mailinator.com", "mjukvarutestare");
		assertEquals(s.checkLogin().contains("Mjuk"), true);
		s.bookCourt("Gnesta TK");
		assertEquals(true, s.checkIfBooked().contains("Tack f�r din bokning!"));
		s.quitSelenium();
		
	}
	
	@Test
	void bookingACourt() {
		s.login("mjukvarutestare1@mailinator.com", "mjukvarutestare");
		assertEquals(s.checkLogin().contains("Mjuk"), true);
		s.bookCourt("H�n� Tenniss�llskap");
		s.paymentCard();
		System.out.println(s.checkIfBooked());
		assertEquals(true, s.checkIfBooked().contains("H�n�"));
		s.unbook();
		s.quitSelenium();
	}

}
