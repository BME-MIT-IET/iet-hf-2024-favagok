#### Integráiós és ellenőrzési technikák
#### Villamosmérnöki és Informatikai Kar
#### Budapesti Műszaki és Gazdaságtudományi Egyetem

## BDD Teszt
# Beszámoló
### Körösladányi Gergő -- FZZJFT
### Ködöböcz Mátyás -- PDYOJN

### Alkalmazás
A teszteléshez a Cucumber alaklamazást használtam. A pom.xml fájlba be kellett írni a megfelelő függőségeket.
A Cucumberben írt BDD tesztek csak akkor működtek ha maven a projekt ezért első lépésként a maven projektet kellett létrehozni.

### Tesztek
Hat fő tesztcsoportot hoztam létre, az elsők egyszerűbbek, a vége felé vannak komplexebbek is:
* *BDDChangePipeIN.feature*: Leteszteli hogy amikor megnyomjuk a megfelelő gombokat ténylegesen megváltozik-e a pumpába bemenő cső.
* *BDDChangePipeOut.feature*: Leteszteli hogy amikor megnyomjuk a megfelelő gombokat ténylegesen megváltozik-e a pumpába kimenő cső.
* *BDDDestroyPipe.feature*: A megfelelő gombok lenyomása után tönkreteszi a játékos a csövet amin átment.
* *BDDMakePipeSticky.feature*: A megfelelő gombok lenyomása után ragadóssá válik a cső amin előzőleg áthaladt.
* *BDDNomadMakesPipeSlippery.feature*: A megfelelő gombok lenyomása után a nomád csúszóssá teszi a csövet amin áthaladt.
* *BDDPickUpNewPipe.feature*: A megfelelő gombok lenyomása után felvessz a szerelő a kiindulási helyén 1 új csövet.
* *BDDPickUpPipe.feature*: A megfelelő gombok lenyomása után felvesszük a pumpához kapcsolódó kiválasztott csövet.
* *BDDPickUpPump.feature*: A megfelelő gombok lenyomása után a szerelő felvesz egy pumpát a kiindulási helyén.
* *BDDPlumberMoves.feature*: A megfelelő gombok lenyomása után a szerelő átlép egy másik pumpára.
* *BDDRepairBrokePipe.feature*: A megfelelő gombok lenyomása után a törött cső megjavítódik.
* *BDDStart.feature*: Elindul a program és rá kell nyomni a start gombra.

### Eredmények
A tesztek mindegyike sikeresen lefut és nem jelez hibát. Ez arra utal, hogy korábban nagyon jó kódot írtunk.

### Fájlok
* src
    * test
        * java
            * com.stepDefinition 
              itt vannak a konkrét függvények

* src
    * test
        * resources
            * Features
              itt vannak a tesztek
