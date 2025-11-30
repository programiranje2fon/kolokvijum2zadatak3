package vaterpolo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class VaterpoloUtakmica {

	private List<Vaterpolista> vaterpolisti = new LinkedList<>();

	public void napraviIzvestaj() {
		try (PrintWriter out = new PrintWriter(
									new BufferedWriter(
											new FileWriter("izvestaj.txt")))) {
			
			for (Vaterpolista vaterpolista : vaterpolisti) {
				if (vaterpolista.getPoeni() >= 2) {
					out.print(vaterpolista.getImePrezime());
					out.print("#");
					out.print(vaterpolista.getKlub());
					out.print("#");
					out.print(vaterpolista.getPoeni());
					out.println();
				}
			}
		} catch (Exception e) {
			System.out.println("Greska: " + e);
		}
	}

	public void dodajVaterpolistu(String imePrezime, String klub, int poeni) {
		Vaterpolista v = new Vaterpolista();
		v.setImePrezime(imePrezime);
		v.setKlub(klub);
		v.setPoeni(poeni);

		if (!vaterpolisti.contains(v)) {
			vaterpolisti.add(v);
		} else {
			try (PrintWriter out = new PrintWriter(
										new BufferedWriter(
												new FileWriter("greske.txt")))) {
				
				out.println(v);
				out.println("Vec postoji u listi");
			} catch (Exception e) {
				System.out.println("Greska: " + e.getMessage());
			}
		}
	}

	public void ucitajVaterpoliste() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		for (int i = 0; i < 22; i++) {
			// Posto u slucaju greske, metoda treba da omoguci korisniku da nastavi 
			// unosenje ostalih vaterpolista, try/catch blok stavljamo unutar for petlje. 
			// U slucaju greske, ona ce biti uhvacena u catch bloku i petlja ce nastaviti. 
			try {
				System.out.print("Unesite ime i prezime: ");
				String imePrezime = br.readLine();

				System.out.print("Unesite klub: ");
				String klub = br.readLine();

				System.out.print("Unesite broj poena: ");
				int poeni = Integer.parseInt(br.readLine());

				Vaterpolista v = new Vaterpolista();
				v.setImePrezime(imePrezime);
				v.setKlub(klub);
				v.setPoeni(poeni);
				
				vaterpolisti.add(v);
			} catch (Exception e) {
				System.out.println("Greska: " + e);
			}
		}
	}
}
