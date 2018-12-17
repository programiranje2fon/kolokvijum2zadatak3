package vaterpolo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.TestUtil;
import vaterpolo.exception.VaterpoloException;

public class VaterpolistaTest {

	private Vaterpolista instance;

	@Before
	public void setUp() throws Exception {
		instance = new Vaterpolista();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	@Test
	public void atribut_imePrezime() {
		assertTrue("U klasi nije definisan atribut imePrezime", TestUtil.doesFieldExist(Vaterpolista.class, "imePrezime"));
	}
	
	@Test
	public void atribut_imePrezime_vidljivost() {
		assertTrue("Atribut imePrezime nije privatan", TestUtil.hasFieldModifier(Vaterpolista.class, "imePrezime", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_klub() {
		assertTrue("U klasi nije definisan atribut klub", TestUtil.doesFieldExist(Vaterpolista.class, "klub"));
	}
	
	@Test
	public void atribut_klub_vidljivost() {
		assertTrue("Atribut klub nije privatan", TestUtil.hasFieldModifier(Vaterpolista.class, "klub", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_poeni() {
		assertTrue("U klasi nije definisan atribut poeni", TestUtil.doesFieldExist(Vaterpolista.class, "poeni"));
	}
	
	@Test
	public void atribut_poeni_vidljivost() {
		assertTrue("Atribut poeni nije privatan", TestUtil.hasFieldModifier(Vaterpolista.class, "poeni", Modifier.PRIVATE));
	}
	
	@Test
	public void metoda_setImePrezime() throws Exception {
		instance.setImePrezime("Filip Filipovic");
		String imePrezime = (String) TestUtil.getFieldValue(instance, "imePrezime");
		assertEquals("Nakon poziva metode setImePrezime(String) sa prosledjenim parametrom \"Filip Filipovic\", vrednost atributa imePrezime se nije promenila na tu vrednost", "Filip Filipovic", imePrezime);
	}
	
	@Test(expected = VaterpoloException.class)
	public void metoda_setImePrezime_null() throws Exception {
		instance.setImePrezime(null);
	}
	
	@Test(expected = VaterpoloException.class)
	public void metoda_setImePrezime_prazanString() throws Exception {
		instance.setImePrezime("");
	}
	
	@Test
	public void metoda_getImePrezime() {
		String imePrezime = (String) TestUtil.getFieldValue(instance, "imePrezime");

		assertEquals("Metoda getImePrezime ne vraca vrednost atributa imePrezime", imePrezime, instance.getImePrezime());
	}
	
	@Test
	public void metoda_setKlub() throws Exception {
		instance.setKlub("Pro Recco");
		String klub = (String) TestUtil.getFieldValue(instance, "klub");
		assertEquals("Nakon poziva metode setKlub(String) sa prosledjenim parametrom \"Pro Recco\", vrednost atributa klub se nije promenila na tu vrednost", "Pro Recco", klub);
	}
	
	@Test(expected = VaterpoloException.class)
	public void metoda_setKlub_null() throws Exception {
		instance.setKlub(null);
	}
	
	@Test(expected = VaterpoloException.class)
	public void metoda_setKlub_prazanString() throws Exception {
		instance.setKlub("");
	}
	
	@Test
	public void metoda_getKlub() {
		String klub = (String) TestUtil.getFieldValue(instance, "klub");
		
		assertEquals("Metoda getKlub() ne vraca vrednost atributa klub", klub, instance.getKlub());
	}
	
	@Test
	public void metoda_setPoeni() throws Exception {
		instance.setPoeni(10);
		int poeni = (int) TestUtil.getFieldValue(instance, "poeni");
		assertEquals("Nakon poziva metode setPoeni(int) sa prosledjenim argumentom \"10\", vrednost atributa poeni se nije promenila na tu vrednost", 10, poeni);
	}
	
	@Test(expected = VaterpoloException.class)
	public void metoda_setPobednik_manjeOdNule() throws Exception {
		instance.setPoeni(-1);
	}
	
	@Test
	public void metoda_getPoeni() {
		int poeni = (int) TestUtil.getFieldValue(instance, "poeni");
		
		assertEquals("Metoda getPoeni() ne vraca vrednost atributa poeni", poeni, instance.getPoeni());
	}
	
	@Test
	public void metoda_equals() {
		instance.setImePrezime("Filip Filipovic");
		instance.setKlub("Pro Recco");
		instance.setPoeni(10);
		
		Vaterpolista v1 = new Vaterpolista();
		v1.setImePrezime("Filip Filipovic");
		v1.setKlub("Pro Recco");
		v1.setPoeni(15);
		
		assertEquals("Metoda equals() ne vraca vrednost true za prosledjenog vaterpolistu sa istim imenom i prezimenom i klubom, a razlicitim brojem poena.", v1, instance);
	}
	
}
