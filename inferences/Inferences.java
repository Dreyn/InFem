package inferences;

import org.apache.jena.util.FileManager;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;  

public class Inferences {
	public static void main(String[] args){
		Model schema = FileManager.get().loadModel("file:/home/mica/Downloads/sparql/ontology.ttl");
		Model data = FileManager.get().loadModel("file:/home/mica/Downloads/sparql/final.ttl");
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		reasoner = reasoner.bindSchema(schema);
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data);

		Resource educInt = infmodel.getResource("http://dbpedia.org/ontology/EducationalInstitution");
		System.out.println("dbo:EducationalInstitution *:");
		printStatements(infmodel, educInt, null, null);
		
		Resource event = infmodel.getResource("http://dbpedia.org/ontology/Event");
		System.out.println("dbo:Event *:");
		printStatements(infmodel, event, null, null);		
		
		Resource hasLoc = infmodel.getResource("http://sw-portal.deri.org/ontologies/swportal#hasLocation");
		System.out.println("swpo:hasLocation *:");
		printStatements(infmodel, hasLoc, null, null);
		
		Resource person = infmodel.getResource("http://xmlns.com/foaf/0.1/Person");
		System.out.println("foaf:Person *:");
		printStatements(infmodel, person, null, null);
		
		Resource place = infmodel.getResource("http://dbpedia.org/ontology/Place");
		System.out.println("dbo:Place *:");
		printStatements(infmodel, place, null, null);
		
		
}
	public static void printStatements(Model m, Resource s, Property p, Resource o) {
	    for (StmtIterator i = m.listStatements(s,p,o); i.hasNext(); ) {
	        Statement stmt = i.nextStatement();
	        System.out.println(" - " + PrintUtil.print(stmt));
	    }
	}

}
