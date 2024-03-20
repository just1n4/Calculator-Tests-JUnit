Internetinis skaičiuotuvas - WEB aplikacija skirta testavimui

WEB aplikacijos paleidimo žingsniai:
1. Parsisiųsti ir atsidaryti projektą. Projekto atidarymui rekomenduojama programinė įranga- IntelliJ studija. Parsisiuntimo url adresas: https://www.jetbrains.com/idea/download/#section=windows (naujausia versija)
2. Įsijungti duomenų bazę (MySQL). Atskirai serverio (Apache/ Tomcat) jungti nereikia- Tomcat serveris automatiškai paleidžiamas kartu su projektu. Rekomenduojama programinė įranga- XAMPP. Parsisiuntimo url adresas: https://www.apachefriends.org/download.html (naujausia versija)
3. Projekto viduje (testavimui -> src -> sql) yra aplankas pavadinimu 'sql', kuriame yra 5 lentelės su įrašais. Jas visas reikia suimportuoti į duomenų bazę, pavadinimu 'db'. Kitu atveju aplikacija tinkamai neveiks. Norint tai atlikti per GUI, reiktų laikinai įsijungti serverį (XAMPP'e tai būtų Apache). Tik paskui neužmiršti išjungti prieš paleidžiant projektą (!).
4. Paleisti aplikaciją studijoje. Projekto paleidimo klasė- CalculatorApplication.
5. Interneto naršyklėje suvesti url adresą 'localhost:8080/skaiciuotuvas'. Rekomenduojama naršyklė- Chrome. Parsisiuntimo url adresas: https://www.google.com/intl/lt_lt/chrome/ (naujausia versija)
6. Sėkmės testuojant aplikaciją :)
