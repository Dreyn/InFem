./bin/tarql-1.1/bin/tarql -d semicolon construct.sparql > result.ttl

./apache-jena-3.5.0/bin/arq --query query.sparql --data result.ttl

