README
------------

Sustav je zamišljen kao dva jednostavna "mirkoservisa".
- WebApplication servis u je u principu  web aplikacija kojoj se korisnik obraća kada traži bilo što, tu može napraviti
sve CRUD operacije nad jelima i sastojcima, te zatražiti izračun dostave

- DeliveryCalculatorApplication je drugi mirkoservis koji je zadužen za računanje cijene dostave.
Unutar njega implementirana su dva servisa (@Service SpringBoot) jedan s fixom cijenom i drugi koji računa postotak od naručene.
Odabir jednog od servisa izvodi se aktiviranjem profila "fix" za fixnu cijenu i "percentage" za postotnu.
Sve izmjene logike računanja nalaze se unutar navedenog servisa te prilikom svake izmjene koda računja, potrebno je
buildati samo ovaj servis, te ga nakon builda ponovno pokrenuti

- Servisi komuniciraju putem HTTP requesta

Pokretanje:
Buid cijelog sustava pokreće se tako da se pokrene komdana mvn clean install u parent modulu
Nakon builda u modulu web u targetu nalaziti će se jar WebApplication servisa, a u modulu
delivery-calculator u targetu će se nalaziti jar DeliveryCalculatorApplication

Servise je potrebno pokrenutu komendaom java -jar
Defaultno se servis WebApplication diže na portu 8080, a DeliveryCalculatorApplication na portu 8090

Konfiguracije profila i iznosa fixne cijene kao i iznosa postotka računanja naleze se u application.properties fileu
u modulu delivery-calculator

Buduće nadogradnje:
--------------------

- Pokriti kod testovima, trenutno su testirani samo mapperi i određeni servisi
End 2 ent test je napravljen korištenjem postmana i ručnim generiranjem requestova

- Bolje odhendlati greške i validacije u kontrolerima


- Riješiti problem in memory baze:
Ideja je bila da se iskoristi h2 baza te da obadvije aplikacije čitaju iz iste baze.
No ispostavilo se je da svaka baza digne svoju instancu i onda zapravu vide svaka svoju.
Iz tog razloga WebApplication servis iz jsona kad zaprimi order, dovuče sve podatke i onda njih pošalje DeliveryCalculatorApplication 
(jer ih on ne vidi već vidi svoju instancu baze koja je prazna :)). Aplikacija nije bila inicijalno tako zamišljena, ideja je bila
da WebApplication servis proslijedi json narudžbe DeliveryCalculatorApplication servisu te da se on spoji na bazu i dovuče sve potrebno,
no on je vidio svoju instancu in memory baze pa je WebApplication morao napraviti korak više.
Detalji oko prolema su opisani u komentarima u kodu, kao i sugestije za unaprijeđenje koda :)

Napomena:
U danom zipu nisam maknu target foldera, kako bi iz njih mogli izvući već buildane jarove.
