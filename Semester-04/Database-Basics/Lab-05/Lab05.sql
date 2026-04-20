SELECT ABS(15.678), ABS(-15.678);

SELECT CEIL(15.678), CEIL(-15.678);

SELECT FLOOR(15.678), FLOOR(-15.678);

SELECT SIGN(15.678), SIGN(-15.678);

SELECT ROUND(15.678, 2);

SELECT TRUNCATE(15.678, 2);

SELECT 'Piotr' AS imie, CHAR_LENGTH('Piotr') AS dlugosc;

SELECT CONCAT('Piotr', ' ', 'Sarna') AS imieNazwisko;

SELECT
    SUBSTRING('Piotr Sarna', 1, LOCATE(' ', 'Piotr Sarna') - 1) AS imie,
    SUBSTRING('Piotr Sarna', LOCATE(' ', 'Piotr Sarna') + 1) AS nazwisko;

SELECT REPLACE('podstawy bazy danych', 'bazy', 'baz') AS tekst;

SELECT CURDATE();

SELECT DAY(CURDATE());

SELECT DAYOFYEAR(CURDATE());

SELECT DAYOFWEEK(CURDATE());

SELECT DAYNAME(CURDATE());

SELECT DATEDIFF('2026-11-07', CURDATE());

SELECT DATEDIFF('2003-04-18', '1923-08-19');

SELECT MAKEDATE(YEAR(CURDATE()), 171);

SELECT idKlienta, CONCAT(imie, ' ', nazwisko) AS "imię i nazwisko" FROM klient;

SELECT idKlienta, CONCAT(imie, ' ', nazwisko) AS "imię i nazwisko" FROM klient ORDER BY CHAR_LENGTH(CONCAT(imie, ' ', 'nazwisko')) ASC;

SELECT *, ROUND(saldo, 0) FROM konto;

SELECT *, ROUND(1000 * oprocentowanie, 2) FROM rodzajlokaty;

SELECT nrOperacji, kwota, dataWykonania, IF(kwota > 0, 'wplata', 'wyplata') AS rodzajOperacji FROM operacja;

SELECT idKlienta, imie, nazwisko, IF(idOpiekuna IS NOT NULL, idOpiekuna, 'brak opiekuna') AS opiekun FROM klient;

SELECT * FROM konto WHERE YEAR(dataOtwarcia) = 2012;

SELECT * FROM konto ORDER BY dataOtwarcia ASC;

SELECT nrKonta, saldo, dataOtwarcia FROM konto WHERE DATEDIFF(CURDATE(), dataOtwarcia) > 3650;

SELECT * FROM operacja ORDER BY YEAR(dataWykonania) DESC, kwota DESC;

SELECT *, IF(saldo > 9999, '***********', saldo) FROM konto;