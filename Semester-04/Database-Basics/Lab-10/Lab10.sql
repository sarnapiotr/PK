SELECT nrLokaty, nazwaLokaty FROM lokata WHERE kwota = (SELECT MIN(kwota) FROM lokata);

SELECT l.idKlienta, GROUP_CONCAT(l.nrLokaty) AS numeryLokat, l.kwota FROM lokata l WHERE kwota = (SELECT MAX(kwota) FROM lokata WHERE idKlienta = l.idKlienta) GROUP BY l.idKlienta;

SELECT MAX(liczbaLokat) AS najwiecejLokat FROM (SELECT COUNT(*) AS liczbaLokat FROM lokata GROUP BY idKlienta) AS lokatyKlientow;

SELECT idKlienta FROM lokata GROUP BY idKlienta HAVING COUNT(*) = (SELECT MAX(liczbaLokat) FROM (SELECT COUNT(*) AS liczbaLokat FROM lokata GROUP BY idKlienta) AS lokatyKlientow);

SELECT MAX(kwota), dataZamkniecia IS NULL AS czyZamkniete FROM lokata GROUP BY dataZamkniecia IS NULL;

SELECT k.imie, k.nazwisko FROM klient k LEFT JOIN lokata l ON k.idKlienta = l.idKlienta WHERE l.kwota = (SELECT MAX(kwota) FROM lokata WHERE dataZamkniecia IS NULL);

SELECT nrOperacji, kwota FROM operacja WHERE kwota = (SELECT MAX(kwota) FROM operacja);

SELECT k.idKlienta, k.imie, k.nazwisko FROM klient k JOIN operacja o ON k.idKlienta = o.idKlienta WHERE o.kwota = (SELECT MAX(kwota) FROM operacja);

SELECT p.idPracownika, p.imie, p.nazwisko FROM pracownik p JOIN klient k ON p.idPracownika = k.idOpiekuna GROUP BY p.idPracownika HAVING COUNT(k.idKlienta) = (SELECT MAX(liczbaPodopiecznych) FROM (SELECT COUNT(*) AS liczbaPodopiecznych FROM klient WHERE idOpiekuna IS NOT NULL GROUP BY idOpiekuna) AS iloscPodopiecznych);

SELECT nrKonta, dataOtwarcia FROM konto WHERE dataOtwarcia = (SELECT MIN(dataOtwarcia) FROM konto);

SELECT k.nrKonta, k.dataOtwarcia FROM konto k WHERE k.dataOtwarcia = (SELECT MIN(dataOtwarcia) FROM konto WHERE YEAR(dataOtwarcia) = YEAR(k.dataOtwarcia));

SELECT kl.idKlienta, COUNT(kk.nrKonta) AS liczbaKont, GROUP_CONCAT(kk.nrKonta) AS numeryKont FROM klient kl LEFT JOIN konto_klienta kk ON kl.idKlienta = kk.idKlienta GROUP BY kl.idKlienta;

SELECT k.idKlienta, (SELECT COUNT(*) FROM konto_klienta WHERE idKlienta = k.idKlienta) AS liczba_kont, (SELECT COUNT(*) FROM lokata WHERE idKlienta = k.idKlienta) AS liczba_lokat, (SELECT GROUP_CONCAT(nrKonta) FROM konto_klienta WHERE idKlienta = k.idKlienta) AS numery_kont, (SELECT GROUP_CONCAT(nrLokaty) FROM lokata WHERE idKlienta = k.idKlienta) AS numery_lokat FROM klient k;

SELECT (SELECT MIN(kwota) FROM lokata WHERE dataZamkniecia IS NOT NULL) AS najnizszaZamknieta, (SELECT MIN(kwota) FROM lokata WHERE dataZamkniecia IS NULL) AS najnizszaOtwarta;

SELECT rl.nazwaLokaty, rl.oprocentowanie, rl.czasTrwania FROM lokata l JOIN rodzajlokaty rl ON l.nazwaLokaty = rl.nazwaLokaty WHERE l.kwota = (SELECT MAX(kwota) FROM lokata);

SELECT rl.nazwaLokaty, COUNT(rl.nazwaLokaty) AS liczbaLokat FROM rodzajlokaty rl LEFT JOIN lokata l ON rl.nazwaLokaty = l.nazwaLokaty WHERE l.dataZamkniecia IS NOT NULL GROUP BY rl.nazwaLokaty;