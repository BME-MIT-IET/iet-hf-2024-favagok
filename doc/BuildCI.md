#### Integráiós és ellenőrzési technikák
#### Villamosmérnöki és Informatikai Kar
#### Budapesti Műszaki és Gazdaságtudományi Egyetem

## Build + CI
# Beszámoló
### Sólyom Béla - OUYZY7

### Alkalmazás
A build és continuous integration (CI) feladatainak automatizálásához a Maven és a GitHub Actions eszközöket használtam. Az alábbiakban részletezem az egyes lépéseket és a megvalósított megoldásokat.

### Maven beállítása
A projekt build menedzsmentjéhez a Maven-t használtam. A pom.xml fájlban definiáltam a szükséges függőségeket és build konfigurációkat, beleértve az esetleges jövőbeni QF-Test integrációt.
### CI beállítása GitHub Actions segítségével
A GitHub Actions-t használtam a folyamatos integráció megvalósításához. Két különböző job-ot definiáltam, amelyek Ubuntu és Windows környezetben is futtatják a build és tesztelési folyamatokat.

### Eredmények
A beállított CI pipeline sikeresen végrehajtja a következő lépéseket mindkét operációs rendszeren:
- Kódbázis letöltése a GitHub repozitóriumból.
- Java fejlesztési környezet beállítása a megfelelő JDK verzióval.
- Projekt build és tesztelés a Maven segítségével.
- Függőségek naprakészen tartása és figyelése.



### Fájlok
pom.xml - maven build          
* .github
    * workflows
        * maven.yml - CI Github-on is fusson a build

