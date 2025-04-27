Projet Pvz - BackEnd

Julien Thepaut - Groupe MIN 2

Arborescence du projet : 

projet_pvz/
│
├── .idea/                        
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/epf/
│   │   │       ├── config/         # Configurations techniques Spring 
│   │   │       │   ├── DatabaseConfig.java		#BDD
│   │   │       │   └── WebConfig.java			#Communication Front-Back
│   │   │       ├── controller/     # Gestion des routes API (CRUD Maps, Zombies, Plantes)
│   │   │       │   ├── MapController.java
│   │   │       │   ├── PlanteController.java
│   │   │       │   └── ZombieController.java
│   │   │       ├── dao/            # Interfaces d'accès aux données 
│   │   │       │   ├── MapDAO.java
│   │   │       │   ├── PlanteDAO.java
│   │   │       │   ├── ZombieDAO.java
│   │   │       │   └── impl/       # Implémentations concrètes des DAO (JdbcTemplate)
│   │   │       │       ├── MapDAOImpl.java
│   │   │       │       ├── PlanteDAOImpl.java
│   │   │       │       └── ZombieDAOImpl.java
│   │   │       ├── dto/            # Objets utilisés pour transporter des données API (j'aurai laissé de mon côté model au vu de la similarité mais dans le soucis du détail...)
│   │   │       │   ├── MapDTO.java
│   │   │       │   ├── PlanteDTO.java
│   │   │       │   └── ZombieDTO.java
│   │   │       ├── exception/      # Gestion centralisée des erreurs
│   │   │       │   ├── GlobalExceptionHandler.java
│   │   │       │   └── ResourceNotFoundException.java
│   │   │       ├── model/          # Entités métiers
│   │   │       │   ├── Map.java
│   │   │       │   ├── Plante.java
│   │   │       │   └── Zombie.java
│   │   │       └── service/        # Service
│   │   │       |   ├── MapService.java
│   │   │       |   ├── PlanteService.java
│   │   │       |   ├── ZombieService.java
│   │   │       |   └── impl/       # Implémentations concrètes des services (lien DTO <-> Models)
│   │   │       |      ├── MapServiceImpl.java
│   │   │       |      ├── PlanteServiceImpl.java
│   │   │       |      └── ZombieServiceImpl.java
│   │   │       └── Main							#Permet d'utiliser SringBootApp
│   │   │       └── ServletInitializer       # Permet d'utiliser TomCat
│   │   └── resources/
│   │       ├── application.properties  # Paramètres de configuration de l'application (BDD, serveur, etc.)
│   │       └── mockito-extensions/
│   │           └── org.mockito.plugins.MockMaker   # Activation de la mockabilité Mockito pour @InjectMock sur final classes
│   └── test/
│       └── java/
│       |    └── com/epf/
│       |       ├── controller/         # Tests unitaires pour les Controllers
│       |       ├── dao/impl/           # Tests unitaires pour les DAO implémentés
│       |       └── service/impl/       # Tests unitaires pour les Services implémentés
|	├──resources/
│         └── mockito-extensions/
│              └── org.mockito.plugins.MockMaker   
│
├── target/                             # Dossier généré automatiquement après compilation/maven build
│
├── init.sql                        # Script de création des tables
└── values.sql                      # Script d'insertion de données initiales
│
└── pom.xml                             # Dépendances Maven et configuration du projet



Ajouts/Modifications de fonctionnalités du projet pour le lancer au front

Le projet s'articule autour SpringBootApp plutôt que TomCat. Les difficultés à lancer TomCat avec la BDD et devoir changer à chaque fois le fichier war m'a fait tourner sur cette solution, où seul le fichier Main suffit à rafraichir la BDD. Tomcat peut toujours être utilisé cependant, même si la gestion des images ne fonctionne pas (et donc pas de grand intérêt à l'utiliser)
-> Classe Zombie :
J'ai décidé de faire en sorte que l'id_map puisse être null pour assurer l'intégrité des données dans le cas d'une suppression d'une map avec des zombies assignés. Ainsi, les zombies ne sont pas perdus, et il suffit de les update sur une nouvelle map pour les changer. J'aurai pu supprimer les zombies où les réassigner à une map par défaut préexistante.

-> Création d'un dossier exception qui doit renvoyer lorsqu'une ressource n'est pas trouver un message d'erreur et sa cause


