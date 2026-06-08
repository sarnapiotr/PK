SELECT idKlienta, imie, nazwisko
FROM klient WHERE idKlienta IN (SELECT idKlienta FROM lokata);

SELECT idKlienta, imie, nazwisko FROM klient
WHERE idKlienta NOT IN (SELECT idKlienta FROM lokata);

SELECT * FROM konto
WHERE saldo > (SELECT saldo FROM konto WHERE nrKonta = 311113)
AND nazwaOddzialu = (SELECT nazwaOddzialu FROM konto WHERE nrKonta = 311113);

