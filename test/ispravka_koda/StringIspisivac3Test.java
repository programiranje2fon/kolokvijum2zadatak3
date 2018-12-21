package ispravka_koda;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringIspisivac3Test {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));		
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	@Test
	public void metoda_ispisiKvadrat() {
		String[] niz = new String[] { "BELO", "CRNO", "ZUTO", "SIVO" };

		String ocekivaniIspis = 
				"*BELO*\n" +
				"C    Z\n" +
				"R    U\n" +
				"N    T\n" +
				"O    O\n" +
				"*SIVO*\n";
		
		StringIspisivac3.ispisiKvadrat(niz);
		
		assertEquals("Reci se ne ispisuju u kvadratu.", ocekivaniIspis, outContent.toString());
	}
}
