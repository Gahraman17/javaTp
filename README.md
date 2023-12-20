# javaTp
Bienvenue dans TPPoke, un projet de combat Pokémon textuel basé sur le terminal en Java. Suivez ces étapes pour configurer et lancer le projet avec succès.

Téléchargement des Fichiers
Téléchargez le fichier JSON Pokémon : Assurez-vous de télécharger le fichier JSON contenant les informations sur les Pokémon. Vous pouvez le trouver [lien vers le fichier JSON].

Téléchargez Gson : Le projet utilise la bibliothèque Gson pour lire le fichier JSON. Vous pouvez trouver la bibliothèque dans le référentiel du projet [lien vers le référentiel].

Configuration et Lancement du Projet
Exécutez la Classe de Test : Avant de commencer, assurez-vous que Gson est correctement configuré dans votre projet. Exécutez la classe de test pour obtenir des Pokémon et des bonbons pour eux.

Démarrage du Serveur : Pour lancer le serveur, exécutez la classe ServeurDeuxClients.

Ouverture des Consoles Client : Ouvrez deux consoles pour les deux clients. Pour chaque console, exécutez la classe ClientDeuxClients.

Configuration des Clients : Exécutez les clients l'un après l'autre. Attendez que chaque client s'initialise dans la console.

Identification et Choix des Pokémon : Chaque client sera invité à saisir un identifiant. Entrez un identifiant unique pour chaque client. Ensuite, choisissez un Pokémon parmi la liste proposée.

Combat Pokémon : Une fois que les deux clients sont connectés et ont choisi leurs Pokémon, le combat commence automatiquement. Le client avec le Pokémon le plus puissant remporte le combat.

Exemple d'Utilisation:
$ java ServeurDeuxClients
Le serveur est en attente de connexions...

$ java ClientDeuxClients
Connecté au serveur
Veuillez saisir votre identifiant :
1
Bienvenue, 1!
Choisissez votre Pokémon parmi [Pikachu, Bulbasaur, Charmander, Squirtle]:
Pikachu
Vous avez choisi Pikachu !

$ java ClientDeuxClients
Connecté au serveur
Veuillez saisir votre identifiant :
2
Bienvenue, 2!
Choisissez votre Pokémon parmi [Bulbasaur, Charmander, Squirtle]:
Squirtle
Vous avez choisi Squirtle !

Les deux joueurs sont connectés. Le combat commence !
...

Le vainqueur est 1 avec Pikachu !
