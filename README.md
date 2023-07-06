# file-upload-spring-boot
Ce projet est une démonstration de la gestion des fichiers en utilisant Spring Boot. Il permet d'enregistrer les fichiers côté back-end tout en sauvegardant le nom du fichier dans la base de données.

## Entité Article
L'entité utilisée dans ce projet est appelée "Article". Elle possède les attributs suivants :

- `designation` : de type String, représente la désignation de l'article.
- `description` : de type `String`, représente la description de l'article.
- `prixUnitaire` : de type `double`, représente le prix unitaire de l'article.
- `image` : de type `String`, représente le nom du fichier image associé à l'article.

## Endpoints disponibles
Ce projet expose les endpoints suivants pour la gestion des articles :

Voici une description des endpoints disponibles dans le code fourni :

1. **GET /articles**\
   **Description** : Récupère la liste de tous les articles.\
   **Méthode** : GET\
   **Retour** : Liste d'objets Article.


2. **GET /article/{id}**\
   **Description** : Récupère les informations d'un article spécifique en fonction de son ID.\
   **Méthode** : GET\
   **Paramètres** : `id` (chemin variable) - ID de l'article\
   **Retour** : Objet Article.


3. **POST /ajout/article**\
   **Description** : Ajoute un nouvel article avec les informations fournies et télécharge une image associée.\
   **Méthode** : POST\
   **Paramètres** :
    - `image` (multipart) - Fichier image à télécharger.
    - `article` (corps de la requête) - JSON représentant l'objet Article.\
    **Retour** : Objet Article nouvellement ajouté.


4. **GET /image/article/{id}**\
   **Description** : Récupère l'image associée à un article spécifique en fonction de son ID.\
   **Méthode** : GET\
   **Paramètres** : `id` (chemin variable) - ID de l'article\
   **Retour** : Fichier image.

Ces endpoints permettent de gérer la récupération des articles, l'ajout d'un nouvel article avec une image associée, ainsi que la récupération de l'image d'un article spécifique.

## Comment exécuter le projet ?
Pour exécuter ce projet, assurez-vous d'avoir installé les éléments suivants :

- **Postman**
- **Editeur de code**
- **Java Development Kit (JDK)**
- **Maven**
- Un SGBD (**MySQL**)

Suivez les étapes ci-dessous :

- Clonez ce projet sur votre machine.
- Crée la base de données **file_upload**
- Ou soit vous pouvez rentrer dans le fichier **application.properties** qui se trouve dans le repertoire **resources** du projet cloné pour configurer l'accès à votre base de données.
- Naviguez vers le répertoire du projet à l'aide d'un terminal.
- Exécutez la commande **mvn spring-boot:run** pour lancer l'application.
- L'application sera accessible à l'URL **http://localhost:2020**.
- Lancez ensuite votre **postman** pour tester nos **endpoints**.
   1. **POST /ajout/article**
  2. **GET /articles**
  3. **GET /article/{id}**
  4. **GET /image/article/{id}**

Assurez-vous d'apporter les modifications nécessaires à la configuration de la base de données, si nécessaire, dans le fichier application.properties.

N'hésitez pas à explorer les différents endpoints à l'aide d'un client HTTP tel que Postman pour interagir avec l'application.

Note : Assurez-vous d'avoir les permissions appropriées pour lire, écrire et supprimer des fichiers sur le système de fichiers où les images seront enregistrées.

C'est tout ! Vous êtes maintenant prêt à utiliser ce projet pour gérer les fichiers en utilisant Spring Boot.