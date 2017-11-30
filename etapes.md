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


## Donne les événements en cours, avec sa ville et son (ou ses) contact.

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
FROM <result.ttl>
FROM <autregroupe.ttl>
WHERE {
	?x swpo:hasLocation ?y.
	?y rdf:type dbo:EducationalInstitution ;
		foaf:name ?institution.
	
	?a ?b ?c;
	   foaf:gender ?g.
	?c rdf:type dbo:EducationalInstitution ;
	   foaf:name ?institution .
}
ORDER BY ?institution

----------------------------------------------------------------------------
| institution                                                              |
============================================================================
| "Institut national des sciences appliquées de Lyon"                      |
| "Institut national des sciences appliquées de Rennes"                    |
| "Institut national des sciences appliquées de Rouen"                     |
| "Université Lille 1 - Sciences technologies"                             |
| "Université Lille 3 - Charles-de-Gaulle"                                 |
| "Université d'Artois"                                                    |
| "Université d'Orléans"                                                   |
| "Université de Bordeaux"                                                 |
| "Université de Lorraine"                                                 |
| "Université de Poitiers"                                                 |
| "Université de Rouen"                                                    |
| "Université de Strasbourg"                                               |
| "Université de la Nouvelle-Calédonie"                                    |
| "Université de technologie de Belfort-Montbéliard"                       |
| "Université du Havre"                                                    |
| "École nationale supérieure d'ingénieurs de Caen"                        |
| "École nationale supérieure de chimie de Rennes"                         |
| "École nationale supérieure de mécanique et d'aérotechnique de Poitiers" |
| "École normale supérieure de Cachan"                                     |
| "École normale supérieure de Lyon"                                       |
----------------------------------------------------------------------------


## Donne pour les établissements en commun, le nombre de prix donnés aux femmes, aux hommes, et le nombre d'actions

SELECT ?institution (count(?female) as ?prixF) (count(?male) as ?prixM) (count(distinct ?action) as ?nbActions)
FROM <result.ttl>
FROM <autregroupe.ttl>
WHERE {
	{
		?action swpo:hasLocation ?y.
		?y rdf:type dbo:EducationalInstitution;
		   foaf:name ?institution.
		
		?a foaf:gender ?male;
		   ?b ?c.
		?c rdf:type dbo:EducationalInstitution;
		   foaf:name ?institution.
		
		FILTER (?male = "Hommes").
	}
	UNION
	{
		?action swpo:hasLocation ?y.
		?y rdf:type dbo:EducationalInstitution;
		   foaf:name ?institution.
		
		?a foaf:gender ?female;
		   ?b ?c.
		?c rdf:type dbo:EducationalInstitution;
		   foaf:name ?institution.
		
		FILTER (?female = "Femmes").
	}
}
GROUP BY ?institution
ORDER BY DESC(?prixF)

--------------------------------------------------------------------------------------------------------
| institution                                                              | prixF | prixM | nbActions |
========================================================================================================
| "Université de Strasbourg"                                               | 97    | 135   | 1         |
| "Université de Lorraine"                                                 | 87    | 119   | 1         |
| "Université de Poitiers"                                                 | 74    | 112   | 1         |
| "Université de Rouen"                                                    | 66    | 100   | 1         |
| "Université Lille 1 - Sciences technologies"                             | 53    | 78    | 1         |
| "Université d'Orléans"                                                   | 51    | 80    | 1         |
| "Institut national des sciences appliquées de Lyon"                      | 35    | 45    | 1         |
| "École normale supérieure de Lyon"                                       | 33    | 58    | 1         |
| "Université Lille 3 - Charles-de-Gaulle"                                 | 27    | 41    | 1         |
| "Université d'Artois"                                                    | 19    | 57    | 1         |
| "École normale supérieure de Cachan"                                     | 18    | 54    | 1         |
| "Institut national des sciences appliquées de Rennes"                    | 13    | 37    | 1         |
| "Université de technologie de Belfort-Montbéliard"                       | 12    | 35    | 1         |
| "Université du Havre"                                                    | 12    | 55    | 1         |
| "École nationale supérieure de mécanique et d'aérotechnique de Poitiers" | 9     | 31    | 1         |
| "École nationale supérieure d'ingénieurs de Caen"                        | 6     | 31    | 1         |
| "École nationale supérieure de chimie de Rennes"                         | 5     | 28    | 1         |
| "Université de Bordeaux"                                                 | 4     | 10    | 1         |
| "Institut national des sciences appliquées de Rouen"                     | 4     | 37    | 1         |
| "Université de la Nouvelle-Calédonie"                                    | 0     | 12    | 1         |
--------------------------------------------------------------------------------------------------------


## Donne par région le ratio homme/femme des prix, avec le nombre d'actions de ces régions
SELECT ?region (count(?female)/count(?male) as ?ratio) (count(?female) as ?prixF) (count(?male) as ?prixM) (count(distinct ?action) as ?nbActions)
FROM <result.ttl>
FROM <autregroupe.ttl>
WHERE {
	{
		?action swpo:hasLocation ?x.
		?x rdf:type dbo:EducationalInstitution;
		   swpo:hasLocation ?y.
		?y dbf:Region ?region.
		
		?a foaf:gender ?male;
		   ?b ?c.
		?c rdf:type dbo:EducationalInstitution;
		   ?d ?e.
		?e rdf:type  dbo:Region;
		   foaf:name ?region.
		
		FILTER (?male = "Hommes").
	}
	UNION
	{
		?action swpo:hasLocation ?x.
		?x rdf:type dbo:EducationalInstitution;
		   swpo:hasLocation ?y.
		?y dbf:Region ?region.
		
		?a foaf:gender ?female;
		   ?b ?c.
		?c rdf:type dbo:EducationalInstitution;
		   ?d ?e.
		?e rdf:type  dbo:Region;
		   foaf:name ?region.
		
		FILTER (?female = "Femmes").
	}
}
GROUP BY ?region
ORDER BY DESC(?ratio)

##Remarque: notre dataset contient les nouvelles régions tandis que la leur contient les vieilles, d'où de nombreux manques.
-----------------------------------------------------------------------------------------
| region                       | ratio                      | prixF | prixM | nbActions |
=========================================================================================
| "Île-de-France"              | 0.716740576496674057649667 | 12930 | 18040 | 10        |
| "Provence-Alpes-Côte d'Azur" | 0.541226215644820295983086 | 768   | 1419  | 3         |
| "Bretagne"                   | 0.517441860465116279069767 | 1246  | 2408  | 7         |
| "Pays de la Loire"           | 0.479729729729729729729729 | 284   | 592   | 2         |
-----------------------------------------------------------------------------------------
