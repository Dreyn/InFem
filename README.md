# Projet de Web des données, web sémantique, en M1 ATAL.

- apache-jena-3.5.0/ et bin/tarql-1.1/ contiennent les outils pour construire notre graph et réaliser nos requêtes.

- command.sh contient deux commandes bash basiques, pour utiliser rapidement les deux outils cités ci-dessus.

- Nous avons deux documents .csv ;   
 Le premier est le dataset original à partir duquel tout a été fait :   
fr-esr-initiatives-pour-la-lutte-contre-les-violences-sexistes-et-sexuelles.csv   
 Le second est le résultat de toutes nos retouches :   
fr-esr-initiatives-pour-la-lutte-contre-les-violences-sexistes-et-sexuelles_bis.csv   

- Nous construisons notre graphe à partir du fichier de requête suivant :   
construct.sparql

- Nous utilisons deux graphes :   
result.ttl : le résultat de notre construction de graphe  
autregroupe.ttl : le graphe de l'autre groupe avec lequel nous nous sommes liés   

- Nos requêtes sparql sont dans le fichier suivant :   
etapes.md

- Nous avons fait nos inferences dans le dossier si bien nommé :   
inferences/

- Enfin, le fichier contenant notre vocabulaire VoiD :   
void.ttl
