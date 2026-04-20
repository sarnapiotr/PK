















SELECT * FROM lokata INNER JOIN klient ON lokata.idKlienta = klient.idKlienta WHERE lokata.dataZamkniecia is NULL
 
SELECT * FROM lokata LEFT OUTER JOIN klient ON lokata.idKlienta = klient.idKlienta WHERE lokata.dataZamkniecia is NULL
 
SELECT klient.idKlienta, klient.imie, klient.nazwisko, lokata.nrLokaty, lokata.kwota FROM lokata INNER JOIN klient ON klient.idKlienta = lokata.idKlienta ORDER BY lokata.kwota DESC
 
SELECT klient.idKlienta, klient.imie, klient.nazwisko, lokata.nrLokaty, lokata.kwota FROM lokata NATURAL JOIN klient ORDER BY lokata.kwota DESC
 
SELECT klient.idKlienta, klient.imie, klient.nazwisko FROM lokata RIGHT OUTER JOIN klient ON klient.idKlienta = lokata.idKlienta WHERE nrLokaty IS NULL
 
SELECT klient.idKlienta, klient.imie, klient.nazwisko FROM klient NATURAL JOIN konto_klienta NATURAL JOIN konto WHERE konto.nazwaOddzialu = 'Krakow3'
 
SELECT p.imie, p.nazwisko, k.imie, k.nazwisko FROM pracownik p LEFT OUTER JOIN pracownik k ON p.idKierownika = k.idPracownika