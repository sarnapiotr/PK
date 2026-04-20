SELECT k.imie, k.nazwisko, l.nrLokaty, l.kwota FROM klient k INNER JOIN lokata l ON k.idKlienta = l.idKlienta WHERE l.dataZamkniecia IS NULL ORDER BY l.kwota DESC;

SELECT idKlienta, imie, nazwisko FROM klient WHERE idKlienta NOT IN (SELECT idKlienta FROM lokata);

SELECT DISTINCT k.idKlienta, k.imie, k.nazwisko
FROM klient k
INNER JOIN konto_klienta kk ON k.idKlienta = kk.idKlienta
INNER JOIN konto ko ON kk.nrKonta = ko.nrKonta
WHERE ko.nazwaOddzialu = 'Krakow3';

SELECT p.nazwisko, IF(k.nazwisko IS NOT NULL, k.nazwisko, '-------------') FROM pracownik p LEFT OUTER JOIN pracownik k ON p.idKierownika = k.idPracownika;

SELECT k.idKlienta, k.imie, k.nazwisko, ko.nrKonta, ko.saldo
FROM klient k
INNER JOIN konto_klienta kk ON k.idKlienta = kk.idKlienta
INNER JOIN konto ko ON kk.nrKonta = ko.nrKonta
ORDER BY ko.nazwaOddzialu ASC, k.nazwisko ASC;

SELECT k.idKlienta, k.imie, k.nazwisko
FROM klient k
INNER JOIN pracownik p ON k.idOpiekuna = p.idPracownika
WHERE p.nazwaOddzialu = 'Krakow2';

SELECT k.idKlienta, k.imie, k.nazwisko, ko.dataOtwarcia, ko.nazwaOddzialu
FROM klient k
INNER JOIN konto_klienta kk ON k.idKlienta = kk.idKlienta
INNER JOIN konto ko ON kk.nrKonta = ko.nrKonta
WHERE DATEDIFF(CURDATE(), ko.dataOtwarcia) > (5 * 365)
ORDER BY ko.dataOtwarcia ASC, ko.nazwaOddzialu ASC;

SELECT o.nazwaOddzialu, p.imie, p.nazwisko FROM oddzialbanku o INNER JOIN pracownik p ON o.idKierownika = p.idPracownika;

SELECT * FROM konto ko
INNER JOIN konto_klienta kk ON ko.nrKonta = kk.nrKonta
INNER JOIN klient k ON kk.idKlienta = k.idKlienta
WHERE k.idOpiekuna IS NOT NULL;

SELECT * FROM konto ko
INNER JOIN konto_klienta kk ON ko.nrKonta = kk.nrKonta
INNER JOIN klient k ON kk.idKlienta = k.idKlienta
WHERE k.idOpiekuna IS NULL;

SELECT idKlienta, nazwisko
FROM klient WHERE idKlienta IN (
    SELECT idKlienta FROM konto_klienta kk
    INNER JOIN konto ko ON kk.nrKonta = ko.nrKonta
    WHERE ko.saldo < 1000
);

SELECT * FROM klient k
INNER JOIN konto_klienta kk ON k.idKlienta = kk.idKlienta
WHERE kk.nrKonta LIKE '%11'
OR kk.nrKonta LIKE '%13'
OR kk.nrKonta LIKE '%14';

SELECT * FROM klient k
INNER JOIN pracownik p ON k.idOpiekuna = p.idPracownika
WHERE p.imie = 'Ewa' AND p.nazwisko = 'Adamska'
ORDER BY k.nazwisko ASC;

SELECT k.* FROM klient k
INNER JOIN konto_klienta kk ON k.idKlienta = kk.idKlienta
INNER JOIN konto ko ON kk.nrKonta = ko.nrKonta
WHERE ko.saldo > 5000 and k.idOpiekuna IS NULL;

SELECT k.idKlienta, k.imie, k.nazwisko
FROM klient k
INNER JOIN lokata l ON k.idKlienta = l.idKlienta
WHERE l.dataZamkniecia IS NULL;