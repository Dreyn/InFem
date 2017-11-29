#Etape 1

##donne le nombre d'actions par ville
SELECT ?region ?ville (count(?ville) as ?nbActions)
FROM<result.ttl>
WHERE {
  ?x swpo:hasLocation ?y.
  ?y swpo:hasLocation ?z.
  ?z db:City ?ville;
	dbf:Region ?region.
}
GROUP BY ?region ?ville
ORDER BY DESC(?nbActions)

-------------------------------------------------------------------------
| region                       | ville                      | nbActions |
=========================================================================
| "Île-de-France"              | "Paris"                    | 6         |
| "Bretagne"                   | "Rennes"                   | 5         |
| "Auvergne-Rhône-Alpes"       | "Lyon"                     | 3         |
| "Hauts-de-France"            | "Villeneuve-d'Ascq"        | 2         |
| "Auvergne-Rhône-Alpes"       | "Grenoble"                 | 1         |
| "Auvergne-Rhône-Alpes"       | "Saint-Martin-d'Hères"     | 1         |
| "Auvergne-Rhône-Alpes"       | "Saint-Étienne"            | 1         |
| "Auvergne-Rhône-Alpes"       | "Villeurbanne"             | 1         |
| "Bourgogne-Franche-Comté"    | "Dijon"                    | 1         |
| "Bourgogne-Franche-Comté"    | "Sevenans"                 | 1         |
| "Bretagne"                   | "Brest"                    | 1         |
| "Bretagne"                   | "Bruz"                     | 1         |
| "Collectivités d'outre-mer"  | "Nouméa"                   | 1         |
| "Grand Est"                  | "Mulhouse"                 | 1         |
| "Grand Est"                  | "Nancy"                    | 1         |
| "Grand Est"                  | "Strasbourg"               | 1         |
| "Hauts-de-France"            | "Arras"                    | 1         |
| "Normandie"                  | "Caen"                     | 1         |
| "Normandie"                  | "Le Havre"                 | 1         |
| "Normandie"                  | "Mont-Saint-Aignan"        | 1         |
| "Normandie"                  | "Saint-Étienne-du-Rouvray" | 1         |
| "Nouvelle-Aquitaine"         | "Bordeaux"                 | 1         |
| "Nouvelle-Aquitaine"         | "Chasseneuil-du-Poitou"    | 1         |
| "Nouvelle-Aquitaine"         | "Pessac"                   | 1         |
| "Nouvelle-Aquitaine"         | "Poitiers"                 | 1         |
| "Occitanie"                  | "Montpellier"              | 1         |
| "Occitanie"                  | "Perpignan"                | 1         |
| "Occitanie"                  | "Toulouse"                 | 1         |
| "Pays de la Loire"           | "Nantes"                   | 1         |
| "Provence-Alpes-Côte d'Azur" | "Avignon"                  | 1         |
| "Provence-Alpes-Côte d'Azur" | "Marseille"                | 1         |
| "Provence-Alpes-Côte d'Azur" | "Nice"                     | 1         |
| "Île-de-France"              | "Cachan"                   | 1         |
| "Île-de-France"              | "Champs-sur-Marne"         | 1         |
| "Île-de-France"              | "Versailles"               | 1         |
| "Île-de-France"              | "Évry"                     | 1         |
-------------------------------------------------------------------------


##donne le nombre de villes et d'action par région
SELECT ?region (count(distinct ?ville) as ?nbVilles) (count(?ville) as ?nbActions)
FROM<result.ttl>
WHERE {
  ?x swpo:hasLocation ?y.
  ?y swpo:hasLocation ?z.
  ?z db:City ?ville;
	dbf:Region ?region;
}
GROUP BY ?region
ORDER BY DESC(?nbVilles)

-------------------------------------------------------
| region                       | nbVilles | nbActions |
=======================================================
| "Île-de-France"              | 5        | 10        |
| "Auvergne-Rhône-Alpes"       | 5        | 7         |
| "Normandie"                  | 4        | 4         |
| "Nouvelle-Aquitaine"         | 4        | 4         |
| "Grand Est"                  | 3        | 3         |
| "Occitanie"                  | 3        | 3         |
| "Provence-Alpes-Côte d'Azur" | 3        | 3         |
| "Bretagne"                   | 3        | 7         |
| "Bourgogne-Franche-Comté"    | 2        | 2         |
| "Hauts-de-France"            | 2        | 3         |
| "Collectivités d'outre-mer"  | 1        | 1         |
| "Pays de la Loire"           | 1        | 1         |
-------------------------------------------------------


## Donne les événements en cours, avec sa ville et son (ou ses) contacte.

SELECT DISTINCT ?action ?Ville ?Contacte ?AutreContacte
FROM<result.ttl>
WHERE {
  ?action rdf:type dbo:Event ;
		dbo:status ?Etat ;
		swpo:hasLocation ?y;
		foaf:Person ?p.
		
  ?y swpo:hasLocation ?z.
  ?z db:City ?Ville .
  
  ?p foaf:name ?Contacte;
	foaf:name ?Contacte2.

  OPTIONAL {?p foaf:name ?Contacte2}
  FILTER (?Etat="En cours")
} ORDER BY ?Ville


--------------------------------------------------------------------------
| action     | Ville        | Contacte                   | AutreContacte |
==========================================================================
| <0381912X> | "Grenoble"   | "Isabelle Schanen"         |               |
| <0762762P> | "Le Havre"   | "Clare Ramsbottom"         |               |
| <0692437Z> | "Lyon"       | "Marion Girer"             |               |
| <0681166Y> | "Mulhouse"   | "Evelyne Aubry"            |               |
| <0542493S> | "Nancy"      | "Frédéric Mouzaoui"        |               |
| <0440984F> | "Nantes"     | "Charlotte Truchet"        |               |
| <0060931E> | "Nice"       | "Emilie Souyri"            |               |
| <0860856N> | "Poitiers"   | "Stéphane Bikialo"         |               |
| <0350077U> | "Rennes"     | "Audrey Deniccurt-Nowicki" |               |
| <0350095N> | "Rennes"     | "Nicoleta Bakhos"          |               |
| <0673021V> | "Strasbourg" | "Isabelle Kraus"           |               |
| <0781944P> | "Versailles" | "Armel Dubois-Nayt"        |               |
--------------------------------------------------------------------------


# Etape 2

## sélectionne les institutions en commun entre notre dataset et celui de l'autre groupe
SELECT DISTINCT ?institution
FROM<result.ttl>
FROM<graph-2.ttl>
WHERE {
	?x swpo:hasLocation ?y.
	?y rdf:type dbo:EducationalInstitution ;
		foaf:name ?institution .
	?a ?b ?c .
	?c rdf:type dbo:EducationalInstitution ;
	   foaf:name ?institution .
}
ORDER BY ?institution

## Remarque: toutes les institutions de notre graphe sont dans le leur
----------------------------------------------------------------------------------------------------
| institution                                                                                      |
====================================================================================================
| "Aix-Marseille université"                                                                       |
| "Ecole centrale de Nantes"                                                                       |
| "Grenoble INP"                                                                                   |
| "Institut d'études politiques de Lyon"                                                           |
| "Institut d'études politiques de Paris"                                                          |
| "Institut français des sciences et technologies des transports, de l'aménagement et des réseaux" |
| "Institut national des sciences appliquées de Lyon"                                              |
| "Institut national des sciences appliquées de Rennes"                                            |
| "Institut national des sciences appliquées de Rouen"                                             |
| "Université Bordeaux-Montaigne"                                                                  |
| "Université François-Rabelais"                                                                   |
| "Université Jean Monnet"                                                                         |
| "Université Jean Moulin - Lyon 3"                                                                |
| "Université Lille 1 - Sciences technologies"                                                     |
| "Université Lille 3 - Charles-de-Gaulle"                                                         |
| "Université Nice - Sophia-Antipolis"                                                             |
| "Université Paris Descartes"                                                                     |
| "Université Paris Diderot"                                                                       |
| "Université Pierre et Marie Curie"                                                               |
| "Université Rennes 2"                                                                            |
| "Université Sorbonne Nouvelle - Paris 3"                                                         |
| "Université Sorbonne Paris Cité"                                                                 |
| "Université d'Artois"                                                                            |
| "Université d'Avignon et des Pays de Vaucluse"                                                   |
| "Université d'Orléans"                                                                           |
| "Université d'Évry-Val d'Essonne"                                                                |
| "Université de Bordeaux"                                                                         |
| "Université de Bourgogne"                                                                        |
| "Université de Bretagne Occidentale"                                                             |
| "Université de Grenoble Alpes"                                                                   |
| "Université de Haute-Alsace"                                                                     |
| "Université de Lorraine"                                                                         |
| "Université de Montpellier"                                                                      |
| "Université de Nantes"                                                                           |
| "Université de Perpignan - Via Domitia"                                                          |
| "Université de Poitiers"                                                                         |
| "Université de Rennes 1"                                                                         |
| "Université de Rouen"                                                                            |
| "Université de Strasbourg"                                                                       |
| "Université de Toulouse 3 - Paul Sabatier"                                                       |
| "Université de Versailles Saint-Quentin-en-Yvelines"                                             |
| "Université de la Nouvelle-Calédonie"                                                            |
| "Université de technologie de Belfort-Montbéliard"                                               |
| "Université du Havre"                                                                            |
| "École des hautes études en santé publique"                                                      |
| "École nationale supérieure d'ingénieurs de Caen"                                                |
| "École nationale supérieure de chimie de Rennes"                                                 |
| "École nationale supérieure de mécanique et d'aérotechnique de Poitiers"                         |
| "École normale supérieure de Cachan"                                                             |
| "École normale supérieure de Lyon"                                                               |
| "École normale supérieure de Rennes"                                                             |
----------------------------------------------------------------------------------------------------

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
