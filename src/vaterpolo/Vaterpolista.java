package vaterpolo;
import vaterpolo.exception.VaterpoloException;

public class Vaterpolista {

	private String imePrezime;
	private String klub;
	private int poeni;

	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		if (imePrezime == null || imePrezime.isEmpty()) {
			throw new VaterpoloException("Ime i prezime ne mogu biti null, niti prazan string.");
		}
		this.imePrezime = imePrezime;
	}

	public String getKlub() {
		return klub;
	}

	public void setKlub(String klub) {
		if (klub == null || klub.isEmpty()) {
			throw new VaterpoloException("Klub ne moze biti null, niti prazan string.");
		}
		this.klub = klub;
	}

	public int getPoeni() {
		return poeni;
	}

	public void setPoeni(int poeni) {
		if (poeni < 0) {
			throw new VaterpoloException("Poeni ne mogu biti manji od nule.");
		}
		this.poeni = poeni;
	}
	
	@Override
	public String toString() {
		return "Ime i prezime: " + imePrezime + ", klub: " + klub + ", poeni: " + poeni;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vaterpolista))
			return false;

		Vaterpolista v = (Vaterpolista) obj;

		return this.imePrezime.equals(v.imePrezime) && this.klub.equals(v.klub);
	}
}
