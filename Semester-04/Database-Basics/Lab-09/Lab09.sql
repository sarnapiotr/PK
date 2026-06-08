SELECT MAX(kwota) FROM lokata;

SELECT nrLokaty FROM lokata WHERE kwota = (SELECT MAX(kwota) FROM lokata);

SELECT COUNT(dataZamkniecia) AS zamkniete, COUNT(*) - COUNT(dataZamkniecia) AS otwarte FROM lokata;

SELECT rl.nazwaLokaty, COUNT(l.nrLokaty) AS liczbaOtwartych FROM rodzajlokaty rl LEFT JOIN lokata l ON rl.nazwaLokaty = l.nazwaLokaty AND l.dataZamkniecia IS NULL GROUP BY rl.nazwaLokaty;

SELECT idKlienta, COUNT(*) AS liczbaLokat FROM lokata GROUP BY(idKlienta) ORDER BY liczbaLokat DESC;

SELECT k.idKlienta, COUNT(l.nrLokaty) AS liczbaLokat FROM klient k LEFT JOIN lokata l ON k.idKlienta = l.idKlienta GROUP BY k.idKlienta ORDER BY liczbaLokat DESC;

SELECT idKlienta, COUNT(*) AS liczbaLokat, GROUP_CONCAT(nrLokaty) AS numeryLokat FROM lokata GROUP BY idKlienta;

SELECT idKlienta, nazwaLokaty, COUNT(*) AS liczbaLokat FROM lokata GROUP BY idKlienta, nazwaLokaty;

SELECT k.idKlienta, k.imie, k.nazwisko, k.idOpiekuna FROM klient k INNER JOIN lokata l ON k.idKlienta = l.idKlienta GROUP BY k.idKlienta HAVING COUNT(l.nrLokaty) > 1;

SELECT idKlienta FROM lokata WHERE kwota BETWEEN 10000 and 50000 GROUP BY idKlienta HAVING COUNT(*) > 1;

SELECT idKlienta, MAX(kwota) as maksymalnaKwota FROM lokata GROUP BY idKlienta ORDER BY maksymalnaKwota;

SELECT idKlienta, SUM(kwota) as sumaKwot FROM lokata WHERE dataZamkniecia IS NOT NULL GROUP BY idKlienta;

SELECT idKlienta, dataZamkniecia IS NULL AS czyOtwarta, SUM(kwota) AS sumaKwot FROM lokata GROUP BY idKlienta, dataZamkniecia IS NULL;

SELECT * FROM lokata WHERE kwota > (SELECT AVG(kwota) FROM lokata WHERE idKlienta = 22);

SELECT nazwaLokaty, dataZamkniecia IS NULL AS czyOtwarta, COUNT(*) as liczbaLokat FROM lokata GROUP BY nazwaLokaty, dataZamkniecia IS NULL;

SELECT COUNT(*) as liczbaPracownikow FROM pracownik;

SELECT stanowisko, COUNT(*) AS liczbaPracownikow FROM pracownik GROUP by stanowisko;

SELECT p.idPracownika, p.imie, p.nazwisko, p.stanowisko, COUNT(k.idOpiekuna) AS liczbaKlientow FROM pracownik p LEFT JOIN klient k ON p.idPracownika = k.idOpiekuna GROUP BY idPracownika ORDER BY liczbaKlientow DESC;

SELECT nrKonta, COUNT(*) AS liczbaOperacji FROM operacja GROUP BY nrKonta;

SELECT k.nrKonta, COUNT(o.nrKonta) AS liczbaOperacji FROM konto k LEFT JOIN operacja o ON k.nrKonta = o.nrKonta GROUP BY k.nrKonta;

SELECT ko.nazwaOddzialu, GROUP_CONCAT(kl.imie, kl.nazwisko) AS klienci FROM konto ko LEFT JOIN konto_klienta kk ON ko.nrKonta = kk.nrKonta LEFT JOIN klient kl ON kk.idKlienta = kl.idKlienta GROUP BY ko.nazwaOddzialu;

SELECT k.nrKonta, COUNT(o.nrOperacji) liczbaOperacji, o.kwota > 0 AS czyWplata FROM konto k LEFT JOIN operacja o ON k.nrKonta = o.nrKonta GROUP BY k.nrKonta, czyWplata;

SELECT kl.idKlienta, kl.imie, kl.nazwisko FROM klient kl JOIN konto_klienta kk ON kl.idKlienta = kk.idKlienta WHERE kk.dataRejestracji = (SELECT MIN(dataRejestracji) FROM konto_klienta);

SELECT YEAR(dataRejestracji) AS rokZalozenia, GROUP_CONCAT(nrKonta, idKlienta) AS kontoWlasciciel FROM konto_klienta GROUP BY YEAR(dataRejestracji);