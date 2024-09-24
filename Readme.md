# Projet de Prix d'Action en Temps Réel

Ce projet démontre une application de suivi du prix d'une action en temps réel, utilisant Spring Boot pour le backend et React pour le frontend. Il utilise les Server-Sent Events (SSE) pour mettre à jour le prix en temps réel côté client.

## Partie I : Backend Spring Boot

### Structure du projet

Le backend est construit avec Spring Boot et contient les éléments suivants :

- `StockPriceApplication.java` : Point d'entrée de l'application Spring Boot
- `StockController.java` : Contrôleur REST gérant les endpoints pour le prix de l'action et les SSE

### Fonctionnalités principales

1. Endpoint GET pour récupérer le prix actuel de l'action
2. Endpoint POST pour mettre à jour le prix de l'action
3. Endpoint SSE pour diffuser les mises à jour du prix en temps réel

### Configuration

Le backend est configuré pour fonctionner sur le port 8080 et autorise les requêtes CORS depuis `http://localhost:3000`.

## Partie II : Frontend React

### Structure du projet

Le frontend est une application React simple contenant :

- `App.js` : Composant principal de l'application
- `StockPrice.js` : Composant affichant le prix de l'action en temps réel
- `index.js` : Point d'entrée de l'application React

### Fonctionnalités principales

1. Affichage du prix actuel de l'action
2. Mise à jour automatique du prix affiché lors de changements côté serveur

### Configuration

Le frontend est configuré pour communiquer avec le backend à l'adresse `http://localhost:8080`.

## Méthodes d'utilisation

### Méthode 1 : Lancement séparé du backend et du frontend

#### Lancement du backend

1. Naviguez vers le dossier du projet backend :
   ```
   cd stock-price
   ```
2. Lancez l'application Spring Boot avec Gradle :
   ```
   ./gradlew bootRun
   ```

#### Lancement du frontend

1. Dans un nouveau terminal, naviguez vers le dossier du projet frontend :
   ```
   cd stock-price-frontend
   ```
2. Installez les dépendances :
   ```
   npm install
   ```
3. Lancez l'application React :
   ```
   npm start
   ```

L'application sera accessible à l'adresse `http://localhost:3000`.

### Méthode 2 : Utilisation de Docker Compose

1. Assurez-vous que Docker et Docker Compose sont installés sur votre système.

2. À la racine du projet, où se trouve le fichier `docker-compose.yml`, exécutez :
   ```
   docker-compose up --build
   ```

Cette commande va construire et lancer à la fois le backend et le frontend. L'application sera accessible à l'adresse `http://localhost:80`.

## Utilisation de l'application

1. Ouvrez un navigateur et accédez à l'application (soit `http://localhost:3000` pour le lancement séparé, soit `http://localhost:80` pour Docker Compose).

2. Vous verrez le prix actuel de l'action affiché.

3. Pour mettre à jour le prix, vous pouvez utiliser un outil comme cURL ou Postman pour envoyer une requête POST à `http://localhost:8080/api/update-price?newPrice=150.0`.

4. Observez la mise à jour en temps réel du prix dans votre navigateur sans avoir à actualiser la page.

Ce projet démontre l'utilisation efficace des Server-Sent Events pour la mise à jour en temps réel des données entre un backend Java Spring Boot et un frontend React.