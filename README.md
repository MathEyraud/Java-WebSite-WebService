# akdemia-WebService

## Description
Le projet Akdemia est une application de gestion de formation qui permet à l'utilisateur de planifier, gérer et suivre des programmes de formation.
Ce projet fonctionne en collaboration avec les deux autres projets "Java-WebSite-CoucheBusiness" et "Angular-WebSite-CoucheWeb".
Cette couche est la couche de communication entre le back et le front (là, où on retrouve les controlleurs).

## Démarrage
1. Clonez ce dépôt sur votre machine locale. 
2. Assurez-vous d'avoir JDK (version 17 ou supérieure) et Maven (Maven) installé.
3. Exécutez la commande mvn install pour installer les dépendances et construire le projet. 
4. Configurez les informations de connexion à la base de données, dans le fichier `application.yml`.
5. Lancer le projet : cliquez avec le bouton droit de la souris sur le fichier "AkdemiaWsGp1eApplication.java", puis sélectionnez l'option pour l'exécuter en tant qu'application Java.
6. Testez via PostMan les ajouts d’un thème et d’un sous-thème

## Faire fonctionner le projet complet
1. Suivre les indications de chaque ReadMe de chaque projet.
2. Démarrer WampServer64
3. Démarrer le client MySQL (phpMyAdmin)
4. Créer les nouvelles bases de données «akdemia-gp1e-db» et «akdemia-gp1e-db-test » pour cela ...
5. ... Importer les scripts SQL « akdemia-db.sql » et « akdemiae-db-test.sql » respectivement dans les bases «akdemia-gp1e-db» et «akdemia-gp1e-db-test»
6. Lancer le projet "Java-WebSite-CoucheWebService" : cliquez avec le bouton droit de la souris sur le fichier "AkdemiaWsGp1eApplication.java", puis sélectionnez l'option pour l'exécuter en tant qu'application Java (après avoir fait le ReadMe du projet).
7. Lancer le projet "Angular-WebSite-CoucheWeb" : "ng serve" dans l'invite de commande à la racine du projet (après avoir fait le ReadMe du projet).
8. Vous pouvez maintenant jouer avec l'interface web que Angular a créé.
   
## Liens externes
1. https://github.com/MathEyraud/Java-WebSite-CoucheBusiness
2. https://github.com/MathEyraud/Java-WebSite-CoucheWebService
3. https://github.com/MathEyraud/Angular-WebSite-CoucheWeb
