#### Integráiós és ellenőrzési technikák
#### Villamosmérnöki és Informatikai Kar
#### Budapesti Műszaki és Gazdaságtudományi Egyetem

## UI Teszt
# Beszámoló
### Erős Pál - BTMLYV

### Alkalmazás
Utánajárás után a User Interface teszteléséhez a QF-Test alkalmazást találtam a legjobbank. Ez ugyan egy fizetős software, 
de az ingyenes próbaidőszak alatt ki tudtam próbálni és a teszteket is meg tudtam valósítani benne. 
Az alkalmazásban különböző teszteseteket és inputokat lehet definiálni.

### Linux sajátosságok
Az alkalmazást és a teszteket Linux operációs rendszeren futtattam. 
Ennek következtében elképzelhetőek különböző design eltérések más OS-ek esetében. Ennek következtében eléképzelhető, hogy a tesztek egy része eltérő eredményt ad.

### Tesztek
Hat fő tesztcsoportot hoztam létre, az elsők egyszerűbbek, a vége felé vannak komplexebbek is:
* *Start window*: A kezdőablakot teszteli, a megjelenő képet, illetve a szövegeket.
* *Story window*: A story gomb működését, illetve az utána megjelenő ablak szövegeit teszteli.
* *Settings window*: A settings gomb működését teszteli. Majd a megjelenő ablak képét és szövegeit ellenőrzi. Ezt követően a beállítás értékeit. Utána módosítja az értékeket és megnézi, hogy ténylegesen is változtak-e a kiírt értékek.
* *Game window*: Először módósítja a beállításokat, majd a Start gomb menyomása után ellenőrzi a megjelenő ablakot. Checkolja a megjelenő szövegeket illetve ellenőrzi a beállított értékek helyességét is. Ezt követően az első játékossal elvégez pár műveletet (Move, Make sticky). A műveletek után ellenőrzi a megjelenő képet. Következő lépésként a második és harmadik játékos lépését is megcsinálja a program (Move, Make sticky, Move, Destroy pipe). A lépés utáén újabb ellenőrzés, hogy megjelennek-e a változtatások. A negyedik játékos esetében (Move, Repair pipe) akciókat hajt végre a program. A megjelenő kép ellenőrzése mellett a szöveg válzoztatásait is ellenőrzi.
* *Game round*: A teszt egy teljes kört lejátszik, majd megnézi, hogy ténylegesen megtörténtek-e a változások, illetve csökkent-e a számláló.
* *Game end*: A játék végét teszteli. Először beállítja, hogy csak egy kör legyen, majd ezt az egy kört lejátsza. Az utolsó játékos után megjelenő záróképernyő szövegeit és gombjait teszteli a program.

### Eredmények
A tesztek mindegyike sikeresen lefut és nem jelez hibát, egy kivételével. A Settings window teszt esetében a kezdeti megjelenéskor egy hibát talál a program. 
A "Rounds: 10" kíírásnál kezdetben nem stimmel a szöveg, ez viszont helyesen változik, ha módosítjuk a beállítás értékét. A probláma esztétikai, a szövegben van egy plusz szóköz feleslegesen. 
Ezt érdemes javítani. Ezen kívül a program által tesztelt elemek helyesen mőködnek.
A tesztek összesített eredményeit külön fájlban meg lehet tekinteni, lásd később.

### Fájlok
* src
    * test
        * qf-test
            * resources 
                * A megjelenés tesztelásáhez szükséges képek
            * testReport
                * report.html   - A teszt eredményei egy html fájlban 
            * iet-hf.qft        - A QF-Test projekt, amiben a tesztek vannak definiálva
