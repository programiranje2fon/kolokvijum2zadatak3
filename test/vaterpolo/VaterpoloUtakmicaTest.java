package vaterpolo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.TestUtil;

public class VaterpoloUtakmicaTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final InputStream originalIn = System.in;

	private VaterpoloUtakmica instance;

	@Before
	public void setUp() throws Exception {
		instance = new VaterpoloUtakmica();
		
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
		
		System.setOut(originalOut);
		System.setIn(originalIn);
	}
	
	@Test
	public void atribut_vaterpolisti() {
		assertTrue("U klasi nije definisan atribut vaterpolisti", TestUtil.doesFieldExist(VaterpoloUtakmica.class, "vaterpolisti"));
	}
	
	@Test
	public void atribut_vaterpolisti_vidljivost() {
		assertTrue("Atribut vaterpolisti nije privatan", TestUtil.hasFieldModifier(VaterpoloUtakmica.class, "vaterpolisti", Modifier.PRIVATE));
	}
	
	@Test
	public void metoda_napraviIzvestaj() throws Exception {
		Vaterpolista v1 = new Vaterpolista();
		v1.setImePrezime("Filip Filipovic");
		v1.setKlub("Pro Recco");
		v1.setPoeni(10);
		
		Vaterpolista v2 = new Vaterpolista();
		v2.setImePrezime("Andrija Prlainovic");
		v2.setKlub("Szolnoki Dozsa");
		v2.setPoeni(1);
		
		Vaterpolista v3 = new Vaterpolista();
		v3.setImePrezime("Dusan Mandic");
		v3.setKlub("Pro Recco");
		v3.setPoeni(7);
		
		@SuppressWarnings("unchecked")
		List<Vaterpolista> vaterpolisti = (List<Vaterpolista>) TestUtil.getFieldValue(instance, "vaterpolisti");
		
		vaterpolisti.add(v1);
		vaterpolisti.add(v2);
		vaterpolisti.add(v3);
		
		instance.napraviIzvestaj();
		
		String sadrzajIzvestaja = ucitajIVratiTekst("izvestaj.txt");
				
		String ocekivaniSadrzaj = 
			"Filip Filipovic#Pro Recco#10\n" + 
			"Dusan Mandic#Pro Recco#7\n";
	
		assertEquals("Kada se izgenerise izvestaj za utakmicu na kojoj su nastupili vaterpolisti Filip Filipovic (Pro Recco) - 10 poena, Andrija Prlainovic (Szolnoki Dozsa) - 1 poen i Dusan Mandic (Pro Recco) - 7 poena, u izvestaju se ne nalaze prvi i treci igrac.",
				ocekivaniSadrzaj,
				sadrzajIzvestaja);
		
		// brisemo kreirani fajl
		new File("izvestaj.txt").delete();
	}
	
	@Test
	public void metoda_dodajVaterpolistu() {
		instance.dodajVaterpolistu("Filip Filipovic", "Pro Recco", 10);
		instance.dodajVaterpolistu("Andrija Prlainovic", "Szolnoki Dozsa", 1);
		
		@SuppressWarnings("unchecked")
		List<Vaterpolista> vaterpolisti = (List<Vaterpolista>) TestUtil.getFieldValue(instance, "vaterpolisti");
		
		assertEquals("Kada se u listu dodaju vaterpolisti Filip Filipovic (Pro Recco) - 10 poena, Andrija Prlainovic (Szolnoki Dozsa) - 1 poen, prvi u listi nije Filip Filipovic", "Filip Filipovic", vaterpolisti.get(0).getImePrezime());
		assertEquals("Kada se u listu dodaju vaterpolisti Filip Filipovic (Pro Recco) - 10 poena, Andrija Prlainovic (Szolnoki Dozsa) - 1 poen, prvi u listi nije Andrija Prlainovic", "Andrija Prlainovic", vaterpolisti.get(1).getImePrezime());
	}
	
	@Test
	public void metoda_dodajVaterpolistu_dvaIsta() throws IOException {
		instance.dodajVaterpolistu("Filip Filipovic", "Pro Recco", 10);
		instance.dodajVaterpolistu("Filip Filipovic", "Pro Recco", 10);
		
		@SuppressWarnings("unchecked")
		List<Vaterpolista> vaterpolisti = (List<Vaterpolista>) TestUtil.getFieldValue(instance, "vaterpolisti");
		
		Vaterpolista v1 = new Vaterpolista();
		v1.setImePrezime("Filip Filipovic");
		v1.setKlub("Pro Recco");
		v1.setPoeni(10);
		
		assertEquals("Kada se u listu dva puta doda vaterpolista Filip Filipovic (Pro Recco) - 10 poena, prvi u listi nije Filip Filipovic", "Filip Filipovic", vaterpolisti.get(0).getImePrezime());
		
		String sadrzajFajlaSaGreskama = ucitajIVratiTekst("greske.txt");
		String[] redovi = sadrzajFajlaSaGreskama.split("\n");
		
		assertEquals("Kada se u listu dva puta doda vaterpolista Filip Filipovic (Pro Recco) - 10 poena, u fajl \"greske.txt\" u prvom redu nije upisan sadrzaj toString() metode nad tim vaterpolistom.", v1.toString(), redovi[0]);
		assertTrue("Kada se u listu dva puta doda vaterpolista Filip Filipovic (Pro Recco) - 10 poena, u fajl \"greske.txt\" u drugom redu nije upisan tekst \"Vec postoji u listi\"", redovi[1].contains("Vec postoji u listi"));
	
		// brisemo kreirani fajl
		new File("greske.txt").delete();
	}
	
	@Test
	public void metoda_ucitajVaterpoliste() {
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < 22; i++) {
			buffer.append("Filip Filipovic\nPro Recco\n10\n");
		}
		
		ByteArrayInputStream in = new ByteArrayInputStream(buffer.toString().getBytes());
		System.setIn(in);
				
		instance.ucitajVaterpoliste();
		
		@SuppressWarnings("unchecked")
		List<Vaterpolista> vaterpolisti = (List<Vaterpolista>) TestUtil.getFieldValue(instance, "vaterpolisti");
		
		assertEquals("Kada se 22 puta na tastaturi ispravno unesu podaci o vaterpolistima, lista vaterpolisti nema 22 elemenata.", 22, vaterpolisti.size());
	}
	
	@Test
	public void metoda_ucitajVaterpoliste_saJednomGreskom() {
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < 21; i++) {
			buffer.append("Filip Filipovic\nPro Recco\n10\n");
		}
		
		// pogresan unos
		buffer.append("Filip Filipovic\nPro Recco\nbla\n");
		
		ByteArrayInputStream in = new ByteArrayInputStream(buffer.toString().getBytes());
		System.setIn(in);
		
		instance.ucitajVaterpoliste();
		
		@SuppressWarnings("unchecked")
		List<Vaterpolista> vaterpolisti = (List<Vaterpolista>) TestUtil.getFieldValue(instance, "vaterpolisti");
		
		assertEquals("Kada se 21 puta na tastaturi ispravno unesu podaci o vaterpolistima, a jednom pogresan unos, lista vaterpolisti nema 21 element.", 21, vaterpolisti.size());
	}
	
	public static String ucitajIVratiTekst(String imeFajla) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(imeFajla));
		boolean kraj = false;
		String s = "";
		
		while (!kraj) {
			String pom = in.readLine();
			
			if (pom == null)
				kraj = true;
			else
				s = s + pom + "\n";
		}
		in.close();

		return s;
	}
	
}
