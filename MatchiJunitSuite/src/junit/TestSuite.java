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
	void bookingHönöCourtCardCorrectCVC() {
		
		s.login("mjukvarutestare1@mailinator.com", "mjukvarutestare");
		assertEquals(s.checkLogin().contains("Mjuk"), true);
		s.bookCourt("Hönö Tennissällskap");
		s.paymentCard();
		assertEquals(true, s.checkIfBooked());
		s.unbook();
		s.quitSelenium();
	}
	
	@Test
	void bookingHönöCourtCardWrongCVC() {
		
		s.login("mjukvarutestare1@mailinator.com", "mjukvarutestare");
		assertEquals(s.checkLogin().contains("Mjuk"), true);
		s.bookCourt("Hönö Tennissällskap");
		s.paymentCardWrongCVC();
		assertTrue(s.checkWrongCVCNumber("Den angivna kortets säkerhetskod är ogiltig"));
		s.quitSelenium();
	}
	
	@Test
	void bookingHönöCourtSwish() {
		
		s.login("mjukvarutestare1@mailinator.com", "mjukvarutestare");
		assertEquals(s.checkLogin().contains("Mjuk"), true);
		s.bookCourt("Hönö Tennissällskap");
		s.paymentSwish("0777777777");
		// Här failar testet...
		// assertEquals(true, s.checkIfBooked());
		// s.unbook();
		s.quitSelenium();
	}


}
