SELECT * FROM klient;

SELECT nazwisko, adres FROM klient WHERE idKlienta = 21;

SELECT * FROM konto WHERE YEAR(dataOtwarcia) = 2012;

SELECT * FROM pracownik WHERE idKierownika IS NOT NULL;

SELECT * FROM konto ORDER BY saldo DESC;

SELECT * FROM konto ORDER BY nazwaOddzialu ASC, saldo DESC;

SELECT nrKonta FROM konto_klienta WHERE idKlienta IN (21, 22, 24) ORDER BY nrKonta ASC;

SELECT * FROM klient ORDER BY idOpiekuna IS NULL ASC;

SELECT idKlienta, imie, nazwisko FROM klient WHERE idOpiekuna = 7;

SELECT idKlienta from konto_klienta WHERE nrKonta = 311113;

SELECT nazwaLokaty, oprocentowanie FROM rodzajlokaty;

SELECT DISTINCT nazwaLokaty FROM lokata;

SELECT DISTINCT idKlienta FROM lokata WHERE nazwaLokaty = 'stala12';

SELECT * FROM klient WHERE idOpiekuna IS NULL;

SELECT DISTINCT idKlienta FROM lokata WHERE nazwaLokaty = 'super';

SELECT DISTINCT idKlienta from lokata WHERE nazwaLokaty = 'super' AND dataZamkniecia IS NULL;

SELECT * FROM klient WHERE nazwisko LIKE 'A%i';

SELECT * FROM konto WHERE saldo <= 10000;

SELECT * FROM lokata WHERE dataZamkniecia IS NULL;

SELECT * FROM lokata WHERE kwota BETWEEN 5000 AND 15000;

SELECT * FROM lokata WHERE dataZamkniecia IS NULL ORDER BY kwota DESC;

SELECT DISTINCT idKlienta FROM lokata WHERE nazwaLokaty LIKE 'stala%';

SELECT nrKonta FROM konto WHERE nazwaOddzialu = 'Krakow3';

SELECT * FROM operacja WHERE idKlienta = 24;

SELECT * FROM operacja WHERE nrKonta BETWEEN 300000 AND 400000;

SELECT * FROM operacja WHERE nrKonta BETWEEN 300000 AND 400000 AND kwota > 0;

SELECT * FROM operacja ORDER BY kwota > 0 ASC;

SELECT nrLokaty from lokata WHERE idKlienta = 22;

SELECT * FROM pracownik WHERE idKierownika IS NULL;

SELECT * FROM lokata WHERE idKlienta = 91 AND dataZamkniecia IS NULL;

SELECT *, (1000 * oprocentowanie) AS zysk FROM roodzajLokaty;