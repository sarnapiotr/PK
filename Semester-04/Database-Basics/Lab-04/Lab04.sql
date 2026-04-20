SELECT * FROM klient
 
SELECT nazwisko, adres FROM klient WHERE idKlienta = 21
 
SELECT * FROM konto WHERE dataOtwarcia >= '2012-01-01' and dataOtwarcia <= '2012-12-31'
SELECT * FROM konto WHERE dataOtwarcia LIKE '2012%'
SELECT * FROM konto WHERE dataOtwarcia BETWEEN '2012-01-01' AND '2012-12-31'
 
SELECT * FROM pracownik WHERE idKierownika is NULL
 
SELECT * FROM konto ORDER BY saldo DESC
 
SELECT * FROM konto ORDER BY nazwaOddzialu, saldo DESC
 
SELECT nrKonta FROM konto_klienta WHERE idKlienta IN (21, 22, 24) ORDER BY nrKonta