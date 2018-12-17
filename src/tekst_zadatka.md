# Zadatak 1

Napraviti javnu klasu **VaterpoloException** u paketu **vaterpolo.exception** koja predstavlja neproveravani izuzetak i ima:
- Javni konstruktor koji kao parametar prima poruku greške i poziva odgovarajući konstruktor nadklase prosleđujući mu parametar.

Napraviti javnu klasu **Vaterpolista** u paketu **vaterpolo** koja ima:
- Privatni atribut **imePrezime**. Vrednost za ovaj atribut je uvek u formatu "IME PREZIME".
- Privatni atribut **klub** koji predstavlja ime kluba za koji vaterpolista igra.
- Privatni atribut **poeni** koji predstavlja ukupan broj golova koje je vaterpolista postigao na utakmici.
- Odgovarajuće **javne get i set metode** za ove atribute. Nedozvoljene vrednosti za atribute imePrezime i klub su null i prazni Stringovi, a broj poena može biti nula ili veći od nule. U slučaju unosa nedozvoljenih vrednosti, baciti izuzetak klase **VaterpolistaException** sa odgovarajućom porukom.
- Redefinisanu **toString** metodu klase Object koja vraća String sa svim podacima o vaterpolisti uz odgovarajuću poruku.
- Redefinisanu **equals** metodu klase Object. Metoda prvo proverava da li je uneti objekat klase Vaterpolista, pa ako nije baca izuzetak klase **VaterpoloException**. Metoda vraća true ako su ime, prezime i naziv kluba jednaki imenu, prezimenu i nazivu kluba unetog vaterpoliste, a u suprotnom false.

Napraviti javnu klasu **VaterpoloUtakmica** u paketu **vaterpolo** koja ima:
- Privatni atribut **vaterpolisti** koji je lista objekata klase **Vaterpolista**. Listu odmah inicijalizovati.
- Javnu metodu **napraviIzvestaj** koja u data fajl "izvestaj.txt" upisuje podatke o onim vaterpolistima iz liste koji su ostvarili makar dva gola i to u formatu: &lt;ime prezime&gt;#&lt;klub&gt;#&lt;broj poena&gt. Podatke o svakom vaterpolisti upisati u poseban red.
- Javnu metodu **dodajVaterpolistu** koja dobija tri ulazna parametra: ime i prezime, klub i broj poena koji predstavljaju podatke o vaterpolisti. Metoda unosi novog vaterpolistu u listu samo ako u listi već ne postoji isti vaterpolista. U slučaju da u listi već postoji taj vaterpolista, potrebno je u tekstualni fajl "greske.txt" uneti sve podatke o tom vaterpolisti i upisati u sledeći red fajla poruku "Vec postoji u listi".
- Javnu metodu **unesiVaterpolisteSaTastature** koja sa tastature učitava podatke o dvadeset dvojici vaterpolista i unosi ih u listu. U slučaju greške (nekog izuzetka) u toku unosa podataka za nekog vaterpolistu, metoda bi trebalo da preskoči unos za tog vaterpolistu, ispiše poruku izuzetka i nastavi sa unosom za preostale vaterpoliste.