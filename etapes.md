#Etape 1

##donne le nombre d'actions par ville
SELECT ?ville (count(?ville) as ?nb)
FROM<result.ttl>
  WHERE {
    ?x dbo:Place ?y .
    ?y dbf:Ville ?ville .
  }
  GROUP BY ?ville
  ORDER BY DESC(?nb)

  -----------------------------
  | ville                | nb |
  =============================
  | "Rennes"             | 7  |
  | "Paris"              | 6  |
  | "Lyon"               | 5  |
  | "Lille"              | 3  |
  | "Rouen"              | 3  |
  | "Aix-Marseille"      | 2  |
  | "Bordeaux"           | 2  |
  | "Créteil"            | 2  |
  | "Grenoble"           | 2  |
  | "Montpellier"        | 2  |
  | "Nantes"             | 2  |
  | "Orléans-Tours"      | 2  |
  | "Poitiers"           | 2  |
  | "Strasbourg"         | 2  |
  | "Versailles"         | 2  |
  | "Besançon"           | 1  |
  | "Caen"               | 1  |
  | "Dijon"              | 1  |
  | "Nancy-Metz"         | 1  |
  | "Nice"               | 1  |
  | "Nouvelle-Calédonie" | 1  |
  | "Toulouse"           | 1  |
  -----------------------------

## sélectionne les villes qui ont un événement en cours

SELECT DISTINCT ?Ville
FROM<result.ttl>
  WHERE {
    ?x rdf:type dbo:Event ;
    dbo:status ?Etat ;
    dbo:Place ?y .
    ?y dbf:Ville ?Ville .
  FILTER (?Etat="En cours")
  } ORDER BY ?Ville

  ------------------------
  | Ville                |
  ========================
  | "Dijon"              |
  | "Grenoble"           |
  | "Lille"              |
  | "Lyon"               |
  | "Montpellier"        |
  | "Nancy-Metz"         |
  | "Nantes"             |
  | "Nice"               |
  | "Nouvelle-Calédonie" |
  | "Poitiers"           |
  | "Rennes"             |
  | "Rouen"              |
  | "Strasbourg"         |
  | "Versailles"         |
  ------------------------

# Etape 2

## sélectionne les institutions en commun entre notre dataset et celui de l'autre groupe
SELECT DISTINCT ?institution
FROM<result.ttl>
FROM<graph-2.ttl>
WHERE {
	?x dbo:Place ?y .
	?y foaf:name ?institution .
	?a ?b ?c .
	?c rdf:type dbo:EducationalInstitution ;
	   foaf:name ?institution .
}
ORDER BY ?institution
------------------------------------------------------------------------------------
| institution                                                              | count |
====================================================================================
| "Université de Strasbourg"                                               | 135   |
| "Université de Lorraine"                                                 | 119   |
| "Université de Poitiers"                                                 | 112   |
| "Université de Rouen"                                                    | 100   |
| "Université d'Orléans"                                                   | 80    |
| "Université Lille 1 - Sciences technologies"                             | 78    |
| "École normale supérieure de Lyon"                                       | 58    |
| "Université d'Artois"                                                    | 57    |
| "Université du Havre"                                                    | 55    |
| "École normale supérieure de Cachan"                                     | 54    |
| "Institut national des sciences appliquées de Lyon"                      | 45    |
| "Université Lille 3 - Charles-de-Gaulle"                                 | 41    |
| "Institut national des sciences appliquées de Rennes"                    | 37    |
| "Institut national des sciences appliquées de Rouen"                     | 37    |
| "Université de technologie de Belfort-Montbéliard"                       | 35    |
| "École nationale supérieure d'ingénieurs de Caen"                        | 31    |
| "École nationale supérieure de mécanique et d'aérotechnique de Poitiers" | 31    |
| "École nationale supérieure de chimie de Rennes"                         | 28    |
| "Université de la Nouvelle-Calédonie"                                    | 12    |
| "Université de Bordeaux"                                                 | 10    |
------------------------------------------------------------------------------------

##sélectionne les institutions communes avec l'autre groupe et donne le nombre de prix attribués à des hommes.
SELECT ?institution (count(?institution) as ?count)
FROM<result.ttl>
FROM<graph-2.ttl>
WHERE {
	?x dbo:Place ?y .
	?y foaf:name ?institution .
	?a ?b ?c ;
        foaf:gender  "Hommes" .
	?c rdf:type dbo:EducationalInstitution ;
	   foaf:name ?institution .
}
GROUP BY ?count ?institution
ORDER BY DESC(?count)

------------------------------------------------------------------------------------
| institution                                                              | count |
====================================================================================
| "Université de Strasbourg"                                               | 97    |
| "Université de Lorraine"                                                 | 87    |
| "Université de Poitiers"                                                 | 74    |
| "Université de Rouen"                                                    | 66    |
| "Université Lille 1 - Sciences technologies"                             | 53    |
| "Université d'Orléans"                                                   | 51    |
| "Institut national des sciences appliquées de Lyon"                      | 35    |
| "École normale supérieure de Lyon"                                       | 33    |
| "Université Lille 3 - Charles-de-Gaulle"                                 | 27    |
| "Université d'Artois"                                                    | 19    |
| "École normale supérieure de Cachan"                                     | 18    |
| "Institut national des sciences appliquées de Rennes"                    | 13    |
| "Université de technologie de Belfort-Montbéliard"                       | 12    |
| "Université du Havre"                                                    | 12    |
| "École nationale supérieure de mécanique et d'aérotechnique de Poitiers" | 9     |
| "École nationale supérieure d'ingénieurs de Caen"                        | 6     |
| "École nationale supérieure de chimie de Rennes"                         | 5     |
| "Institut national des sciences appliquées de Rouen"                     | 4     |
| "Université de Bordeaux"                                                 | 4     |
------------------------------------------------------------------------------------

##sélectionne les institutions communes avec l'autre groupe et donne le nombre de prix attribués à des femmes.
SELECT ?institution (count(?institution) as ?count)
FROM<result.ttl>
FROM<graph-2.ttl>
WHERE {
	?x dbo:Place ?y .
	?y foaf:name ?institution .
	?a ?b ?c ;
        foaf:gender  "Femmes" .
	?c rdf:type dbo:EducationalInstitution ;
	   foaf:name ?institution .
}
GROUP BY ?count ?institution
ORDER BY DESC(?count)
