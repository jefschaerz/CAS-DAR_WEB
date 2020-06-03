package ch.eiafr.rdf;

import java.io.File;
import org.eclipse.rdf4j.common.iteration.Iterations;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.model.vocabulary.XMLSchema;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
// TODO Check from where
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;

import org.eclipse.rdf4j.sail.memory.MemoryStore;

//----------------------------------------------------------------------------------------
// CAS-DAR / WEB 
// Mini-projet
// Schärz Jean-François
//----------------------------------------------------------------------------------------

public class OntoITSystem{
	
	public static String namespace = "http://jefschaerz.ch/casdar/web/";
	public static ValueFactory f;
	
	// Class
	public static IRI it_equipment, person, department, server, pc,
		printer, software, pcenduser, it_admin ;
	
	//Object property
	public static IRI isUserOf, isMemberOf, isInstalledOn, isTheSuperiorOf, isManagerOf,
		printsOn, isEndUserOf, isITContactOF, canInstall, needsSW ;
	
	// Data Property
	public static IRI hasBuyDate, hasInvNb, OSName, isVirtual, hasProcType, hasModelNb,
		hasCartridgeNb, isColor, hasSWName, isVersion, hasLastName, hasFirstName,
		hasOfficeID, hasLaptop, hasDepName, hasKeyNb, hasDirectPhoneNb, hasUserNb  ;
	
	public static IRI DCFileSrv, vmDC2Print, vmLinux1, PC1, PC2, IMP1, IMP2,
		Office, CAD, JohnDoe, BillyBond, JeanSairien, MichelBlanc, 
		SM, Dev;
	
	static void buildOntology(Repository rep) {
		
		System.out.println("** Buildontology ! **********************");
		
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();

		// Create Class		
		it_equipment = f.createIRI(namespace, "it_equipment");
		person = f.createIRI(namespace, "person");
		department = f.createIRI(namespace, "department");
		server = f.createIRI(namespace, "server");
		pc = f.createIRI(namespace, "pc");
		printer = f.createIRI(namespace, "printer");
		software = f.createIRI(namespace, "software");
		pcenduser = f.createIRI(namespace, "pcenduser");
		it_admin = f.createIRI(namespace, "it_admin");

		// Create Data Properties
		//	IT_Equipement
		hasBuyDate = f.createIRI(namespace, "hasBuyDate");
		hasInvNb = f.createIRI(namespace, "hasInvNb");
		// 		Server
		OSName = f.createIRI(namespace, "OSName");
		isVirtual = f.createIRI(namespace, "isVirtual");
		// 		PC
		hasProcType = f.createIRI(namespace, "hasProcType");
		hasModelNb = f.createIRI(namespace, "hasModelNb");
		// 		Printer
		hasCartridgeNb = f.createIRI(namespace, "hasCartridgeNb");
		isColor = f.createIRI(namespace, "isColor");
		isInstalledOn = f.createIRI(namespace, "isInstalledOn");
		// 		Software
		hasSWName = f.createIRI(namespace, "hasSWName");
		isVersion = f.createIRI(namespace, "isVersion");
		
		// Person
		hasLastName = f.createIRI(namespace, "hasLastName");
		hasFirstName = f.createIRI(namespace, "hasFirstName");
		isTheSuperiorOf = f.createIRI(namespace, "isTheSuperiorOf");
		isUserOf = f.createIRI(namespace, "isUserOf");
		isMemberOf = f.createIRI(namespace, "isMemberOf");
		// 		PCEndUser
		hasOfficeID = f.createIRI(namespace, "hasOfficeID");
		hasLaptop = f.createIRI(namespace, "hasLaptop");
		printsOn = f.createIRI(namespace, "printsOn");
		isEndUserOf = f.createIRI(namespace, "isEndUserOf");
		needsSW = f.createIRI(namespace, "needsSW");
		//		IT_Admin
		hasKeyNb = f.createIRI(namespace, "hasKeyNb");
		hasDirectPhoneNb = f.createIRI(namespace, "hasDirectPhoneNb");
		isITContactOF = f.createIRI(namespace, "isITContactOF");
		canInstall = f.createIRI(namespace, "canInstall");
		isManagerOf = f.createIRI(namespace, "isManagerOf");
		
		// Department
		hasDepName = f.createIRI(namespace, "hasDepName");
		hasUserNb = f.createIRI(namespace, "hasUserNb");
				
		try {
					
			conn.add(it_equipment, RDF.TYPE, RDFS.CLASS);
			conn.add(hasInvNb, RDF.TYPE, RDF.PROPERTY);
			conn.add(hasBuyDate, RDF.TYPE, RDF.PROPERTY);
			
			conn.add(department, RDF.TYPE, RDFS.CLASS);
			conn.add(hasDepName, RDF.TYPE, RDF.PROPERTY);
			conn.add(hasUserNb, RDF.TYPE, RDF.PROPERTY);
			
			conn.add(person, RDF.TYPE, RDFS.CLASS);
			conn.add(person, isUserOf, it_equipment);
			conn.add(person, isMemberOf, department);
			conn.add(person, isTheSuperiorOf, department);
			conn.add(hasLastName, RDF.TYPE, RDF.PROPERTY);
			conn.add(hasFirstName, RDF.TYPE, RDF.PROPERTY);
						
			conn.add(server, RDFS.SUBCLASSOF, it_equipment);
			conn.add(OSName, RDF.TYPE, RDF.PROPERTY);
			conn.add(isVirtual, RDF.TYPE, RDF.PROPERTY);
			
			conn.add(pc, RDFS.SUBCLASSOF, it_equipment );
			conn.add(hasProcType, RDF.TYPE, RDF.PROPERTY);
			conn.add(hasModelNb, RDF.TYPE, RDF.PROPERTY);
			
			conn.add(printer, RDFS.SUBCLASSOF, it_equipment );
			conn.add(isColor, RDF.TYPE, RDF.PROPERTY);
			conn.add(hasCartridgeNb, RDF.TYPE, RDF.PROPERTY);
			conn.add(printer, isInstalledOn, server);
			
			conn.add(software, RDFS.SUBCLASSOF, it_equipment );
			conn.add(hasSWName, RDF.TYPE, RDF.PROPERTY);
			conn.add(isVersion, RDF.TYPE, RDF.PROPERTY);
			
			conn.add(pcenduser, RDFS.SUBCLASSOF, person);
			conn.add(hasLaptop, RDF.TYPE, RDF.PROPERTY);
			conn.add(hasOfficeID, RDF.TYPE, RDF.PROPERTY);
			conn.add(pcenduser, printsOn, printer);
			conn.add(pcenduser, isEndUserOf, pc);
			conn.add(pcenduser, needsSW, pc);
			
			conn.add(it_admin, RDFS.SUBCLASSOF, person );
			conn.add(hasKeyNb, RDF.TYPE, RDF.PROPERTY);
			conn.add(hasDirectPhoneNb, RDF.TYPE, RDF.PROPERTY);
			conn.add(isManagerOf, RDFS.SUBPROPERTYOF, isUserOf);
			conn.add(it_admin, isManagerOf, server);
			conn.add(it_admin, canInstall, software);
			conn.add(isITContactOF, RDFS.SUBPROPERTYOF, isMemberOf);
			conn.add(it_admin, isITContactOF, department);
							
		}
		finally {
			conn.close();
		}

	} 
	
	static void createIndividualsPerson(Repository rep, IRI irilink, String hasLastName, String hasFirstName) {
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		
		try {
			conn.add(irilink, RDF.TYPE, person);
			conn.add(irilink, OntoITSystem.hasLastName, f.createLiteral(hasLastName, XMLSchema.STRING));
			conn.add(irilink, OntoITSystem.hasFirstName, f.createLiteral(hasFirstName, XMLSchema.STRING));	
		}
		finally {
			conn.close();
		}
	}
	
	static void createIndividualsITEquipment(Repository rep, IRI irilink, String hasInvNb, String hasBuyDate) {
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		
		try {
			conn.add(irilink, RDF.TYPE, it_equipment);
			conn.add(irilink, OntoITSystem.hasInvNb, f.createLiteral(hasInvNb, XMLSchema.STRING));
			conn.add(irilink, OntoITSystem.hasBuyDate, f.createLiteral(hasBuyDate, XMLSchema.DATETIME));	
		}
		finally {
			conn.close();
		}
	}
	
	static void createIndividualsDepartment(Repository rep, IRI irilink, String hasDepName, Integer hasUserNb) {
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		
		try {
			conn.add(irilink, RDF.TYPE, department);
			conn.add(irilink, OntoITSystem.hasDepName, f.createLiteral(hasDepName, XMLSchema.STRING));
			conn.add(irilink, OntoITSystem.hasUserNb, f.createLiteral(hasUserNb.toString(), XMLSchema.INTEGER));	
		}
		finally {
			conn.close();
		}
	}
	
	static void createIndividualsServer(Repository rep, IRI irilink, String OSName, Boolean isVirtual, String hasBuyDate, String hasInvNb ) {
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		
		try {
			conn.add(irilink, RDF.TYPE, server);
			conn.add(irilink, OntoITSystem.OSName, f.createLiteral(OSName, XMLSchema.STRING));	
			conn.add(irilink, OntoITSystem.isVirtual, f.createLiteral(isVirtual.toString(), XMLSchema.BOOLEAN));
			// Create also Data property of IT_Equipment Object
			conn.add(irilink, OntoITSystem.hasBuyDate, f.createLiteral(hasBuyDate, XMLSchema.DATETIME));	
			conn.add(irilink, OntoITSystem.hasInvNb, f.createLiteral(hasInvNb, XMLSchema.STRING));
			
		}
		finally {
			conn.close();
		}
	}
	
	static void createIndividualsPC(Repository rep, IRI irilink, String hasProcType, String hasModelNb, String hasBuyDate, String hasInvNb ) {
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		
		try {
			conn.add(irilink, RDF.TYPE, pc);
			conn.add(irilink, OntoITSystem.hasProcType, f.createLiteral(hasProcType, XMLSchema.STRING));	
			conn.add(irilink, OntoITSystem.hasModelNb, f.createLiteral(hasModelNb, XMLSchema.STRING));
			// Create also Data property of IT_Equipment Object
			conn.add(irilink, OntoITSystem.hasBuyDate, f.createLiteral(hasBuyDate, XMLSchema.DATETIME));	
			conn.add(irilink, OntoITSystem.hasInvNb, f.createLiteral(hasInvNb, XMLSchema.STRING));
			
		}
		finally {
			conn.close();
		}
	}
	
	static void createIndividualsPrinter(Repository rep, IRI irilink, String hasCartridgeNb, Boolean isColor, String hasBuyDate, String hasInvNb ) {
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		
		try {
			conn.add(irilink, RDF.TYPE, printer);
			conn.add(irilink, OntoITSystem.hasCartridgeNb, f.createLiteral(hasCartridgeNb, XMLSchema.STRING));	
			conn.add(irilink, OntoITSystem.isColor, f.createLiteral(isColor.toString(), XMLSchema.BOOLEAN));
			// Create also Data property of IT_Equipment Object
			conn.add(irilink, OntoITSystem.hasBuyDate, f.createLiteral(hasBuyDate, XMLSchema.DATETIME));	
			conn.add(irilink, OntoITSystem.hasInvNb, f.createLiteral(hasInvNb, XMLSchema.STRING));

		}
		finally {
			conn.close();
		}
	}
	
	static void createIndividualsSoftware(Repository rep, IRI irilink, String hasSWName , String isVersion, String hasBuyDate, String hasInvNb ) {
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		
		try {
			conn.add(irilink, RDF.TYPE, software);
			conn.add(irilink, OntoITSystem.hasSWName, f.createLiteral(hasSWName, XMLSchema.STRING));	
			conn.add(irilink, OntoITSystem.isVersion, f.createLiteral(isVersion, XMLSchema.STRING));
			// Create also Data property of IT_Equipment Object
			conn.add(irilink, OntoITSystem.hasBuyDate, f.createLiteral(hasBuyDate, XMLSchema.DATETIME));	
			conn.add(irilink, OntoITSystem.hasInvNb, f.createLiteral(hasInvNb, XMLSchema.STRING));
		}
		finally {
			conn.close();
		}
	}
	
	static void createIndividualsPCEndUser(Repository rep, IRI irilink, String hasOfficeID , Boolean hasLaptop, String hasLastName, String hasFirstName) {
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		
		try {
			conn.add(irilink, RDF.TYPE, pcenduser);
			conn.add(irilink, OntoITSystem.hasOfficeID, f.createLiteral(hasOfficeID, XMLSchema.STRING));	
			conn.add(irilink, OntoITSystem.hasLaptop, f.createLiteral(hasLaptop.toString(), XMLSchema.BOOLEAN));
			
			// Data property of parent Person
			createIndividualsPerson(rep, irilink, hasLastName, hasFirstName);

		}
		finally {
			conn.close();
		}
	}
	
	static void createIndividualsITAdmin(Repository rep, IRI irilink, String hasDirectPhone , String hasKeyNb, String hasLastName, String hasFirstName) {
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		
		try {
			conn.add(irilink, RDF.TYPE,it_admin);
			conn.add(irilink, OntoITSystem.hasDirectPhoneNb, f.createLiteral(hasDirectPhone, XMLSchema.STRING));	
			conn.add(irilink, OntoITSystem.hasKeyNb, f.createLiteral(hasKeyNb, XMLSchema.STRING));
			
			// Data property of parent Person
			createIndividualsPerson(rep, irilink, hasLastName, hasFirstName);
		}
		finally {
			conn.close();
		}
	}
	
	static void createIndividuals(Repository rep) {
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		
		System.out.println("** CreateIndividuals ! **********************");
		
		// Create individuals IRI
		DCFileSrv = f.createIRI(namespace, "DCFileSrv");
		vmDC2Print = f.createIRI(namespace, "vmDC2Print");
		vmLinux1 = f.createIRI(namespace, "vmLinux1");
		PC1 = f.createIRI(namespace, "PC1");
		PC2 = f.createIRI(namespace, "PC2");
		IMP1 = f.createIRI(namespace, "IMP1");
		IMP2 = f.createIRI(namespace, "IMP2");
		Office = f.createIRI(namespace, "Office");
		CAD = f.createIRI(namespace, "CAD");
		JohnDoe = f.createIRI(namespace, "JohnDoe");
		BillyBond = f.createIRI(namespace, "BillyBond");
		JeanSairien = f.createIRI(namespace, "JeanSairien");
		MichelBlanc= f.createIRI(namespace, "MichelBlanc");
		SM = f.createIRI(namespace, "SM");
		Dev = f.createIRI(namespace, "Dev");
		
		createIndividualsDepartment(rep, SM, "Sales", 5);
		createIndividualsDepartment(rep, Dev, "Dev", 3);
		createIndividualsServer(rep, DCFileSrv, "WindowsServer2016", false, "2019-05-20T10:00:00", "Server_001" );
		createIndividualsServer(rep, vmDC2Print, "WindowsServer2016", true, "2018-05-26T12:00:00", "Server_002");
		createIndividualsServer(rep, vmLinux1, "Linux", true, "2019-05-07T12:00:00", "Server_003");
		createIndividualsPC(rep, PC1, "i5-5700", "HPElite8300", "2018-02-07T10:00:00", "PC_001");
		createIndividualsPC(rep, PC2, "i7-7200", "HPEliteBook850", "2019-08-01T10:00:00", "PC_002");
		createIndividualsPrinter(rep, IMP1, "CE375", false, "2019-08-12T15:00:00", "IMP_001");
		createIndividualsPrinter(rep, IMP2, "CF410x", true, "2020-01-05T15:00:00", "IMP_002");
		createIndividualsSoftware(rep, CAD, "SolidWorks", "2020.1", "2019-06-05T14:30:00", "Soft_001");
		createIndividualsSoftware(rep, Office, "MSOffice", "2016", "2019-06-05T14:30:00", "Soft_002");
		createIndividualsPCEndUser(rep, JohnDoe, "C4-3", false, "Doe", "John");
		createIndividualsPCEndUser(rep, BillyBond, "A1-12", true, "Bond", "Billy");
		createIndividualsITAdmin(rep, JeanSairien, "360", "1A", "Sairien", "Jean");
		createIndividualsITAdmin(rep, MichelBlanc, "383", "2A", "Blanc", "Michel");
		
		// Add object properties between objects
		try {
			conn.add(MichelBlanc, canInstall, Office);
			conn.add(MichelBlanc, isManagerOf, DCFileSrv);
			conn.add(MichelBlanc, isManagerOf, vmLinux1);
			conn.add(MichelBlanc, isITContactOF, Dev);
			conn.add(JeanSairien, isManagerOf, vmDC2Print);
			conn.add(JeanSairien, canInstall, CAD);
			conn.add(JeanSairien, isITContactOF, SM);
			conn.add(JohnDoe, needsSW, CAD);
			conn.add(JohnDoe, isEndUserOf, PC1);
			conn.add(JohnDoe, printsOn, IMP1);
			conn.add(BillyBond, needsSW, Office);
			conn.add(BillyBond, printsOn, IMP2);
			conn.add(BillyBond, isEndUserOf, PC2);
			conn.add(IMP1, isInstalledOn, vmDC2Print);
			conn.add(IMP2, isInstalledOn, vmDC2Print);
		}
		finally {
			conn.close();
		}
	}
	
	static void execQueryGetServersWindows(Repository rep) {
		RepositoryConnection conn = rep.getConnection();
		
		try {
			String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
								 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
								 "PREFIX ns: <" + namespace + ">" +
								 "SELECT DISTINCT (?serversWin as ?WindowsServer) where { " +
								  	"?serversWin rdf:type ns:server ." +
								  	"?serversWin ns:OSName ?x ." +
								  	"FILTER regex (?x, \"^Windows\")" + 
								 "}";
								
			System.out.println(queryString);
			TupleQuery query = conn.prepareTupleQuery(queryString);
			System.out.println("");
			System.out.println("** Request 1 : Search for Windows servers by SPARQL ************** ");

			try (TupleQueryResult result = query.evaluate()) {
				while (result.hasNext()) {
					BindingSet solution = result.next();
					String line = "Serveurs Windows = " + solution.getValue("WindowsServer") ;
					System.out.println(line);
				}
			}
		} finally {
			conn.close();
		}
		
	}
	
	static void execQueryGetPCFrom2019(Repository rep) {
		RepositoryConnection conn = rep.getConnection();
		
		try {
			String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
								 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
								 "PREFIX ns: <" + namespace + ">" +
								 "SELECT DISTINCT ?pcFrom2019 ?buyDate where { " +
								 	"?x rdf:type ns:pc ." +
								  	"?x ns:hasBuyDate ?buyDate ." +
								 	"?x ns:hasInvNb ?pcFrom2019 ." + 
								  	"FILTER(?buyDate > \"2019-01-01T00:00:00Z\"^^xsd:dateTime)" +
								 "}";
								
			TupleQuery query = conn.prepareTupleQuery(queryString);
			System.out.println("");
			System.out.println("** Request 2 : Search for PC younger than 2019 by SPARQL ************** ");

			try (TupleQueryResult result = query.evaluate()) {
				while (result.hasNext()) {
					BindingSet solution = result.next();
					String line = "PCs : " + solution.getValue("pcFrom2019") + " BuyDate = "+ solution.getValue("buyDate");
					System.out.println(line);
				}
			}
		} finally {
			conn.close();
		}
		
	}
	
	static void execQueryGetUsers(Repository rep) {
		RepositoryConnection conn = rep.getConnection();
		
		try {
			String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
								 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
								 "PREFIX ns: <" + namespace + ">" +
								 "SELECT DISTINCT ?FirstName ?LastName where { " +
								 	"{?x rdf:type ns:pcenduser .} " +
								 	"UNION" +
								 	"{?x rdf:type ns:it_admin .} " +
								 	"?x ns:hasLastName ?LastName ." +
								 	"?x ns:hasFirstName ?FirstName ." + 
								 "}";
								
			TupleQuery query = conn.prepareTupleQuery(queryString);
			System.out.println("");
			System.out.println("** Request 3 : Search for Users (PCEndUser of IT_admin) by SPARQL ************** ");

			try (TupleQueryResult result = query.evaluate()) {
				while (result.hasNext()) {
					BindingSet solution = result.next();
					String line = "Lastname: " + solution.getValue("LastName") + " FirstName: "+ solution.getValue("FirstName");
					System.out.println(line);
				}
			}
		} finally {
			conn.close();
		}
		
	}
	
	static void execQueryGetSWListInstalledByMichelBlanc(Repository rep) {
		RepositoryConnection conn = rep.getConnection();
		
		try {
			String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
								 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
								 "PREFIX ns: <" + namespace + ">" +
								 "SELECT DISTINCT ?SWName where { " +
								 	"?x rdf:type ns:software ." +
								 	"?x ns:hasSWName ?SWName ." +
								 	"ns:MichelBlanc ns:canInstall ?x . " +
								 "}";
										
			TupleQuery query = conn.prepareTupleQuery(queryString);
			System.out.println("");
			System.out.println("** Request 4 : Search for SW installed by MichelBlanc by SPARQL ************** ");

			try (TupleQueryResult result = query.evaluate()) {
				while (result.hasNext()) {
					BindingSet solution = result.next();
					String line = "SWName: " + solution.getValue("SWName") ;
					System.out.println(line);
				}
			}
		} finally {
			conn.close();
		}
		
	}
	
	static void execQueryGetServersWithPrinters(Repository rep) {
		RepositoryConnection conn = rep.getConnection();
		
		try {
			String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
								 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
								 "PREFIX ns: <" + namespace + ">" +
								 "SELECT DISTINCT ?ServerInv ?Printers where { " +
								 	"?x rdf:type ns:server ." +
								 	"?x ns:hasInvNb ?ServerInv ." +
								 	"OPTIONAL {" +
								 		" ?Printers ns:isInstalledOn ?x . }" + 
								 "}";
										
			TupleQuery query = conn.prepareTupleQuery(queryString);
			System.out.println("");
			System.out.println("** Request 5 : Search for servers and display printers if any installed by SPARQL ************** ");

			try (TupleQueryResult result = query.evaluate()) {
				while (result.hasNext()) {
					BindingSet solution = result.next();
					String line = "ServerInv: " + solution.getValue("ServerInv") + " Printers: "+ solution.getValue("Printers");
					System.out.println(line);
				}
			}
		} finally {
			conn.close();
		}
		
	}
	
	static void execQueryGetDepartmentNames(Repository rep) {
		RepositoryConnection conn = rep.getConnection();
		
		try {
			String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
								 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
								 "PREFIX ns: <" + namespace + ">" +
								 "SELECT DISTINCT ?DepName where { " +
								 	"?x rdf:type ns:department ." +
								 	"?x ns:hasDepName ?DepName ." +
								 "}" + 
								 "ORDER BY (?DepName)";
										
			TupleQuery query = conn.prepareTupleQuery(queryString);
			System.out.println("");
			System.out.println("** Request 6 : Search for department names order by names by SPARQL ************** ");

			try (TupleQueryResult result = query.evaluate()) {
				while (result.hasNext()) {
					BindingSet solution = result.next();
					String line = "DepName: " + solution.getValue("DepName");
					System.out.println(line);
				}
			}
		} finally {
			conn.close();
		}
		
	}
	
	static void displayOntology(Repository rep) {
		System.out.println("** Display Ontology *********************");
		
		RepositoryConnection conn = rep.getConnection();
		
		// For FOFA : http://xmlns.com/foaf/spec/
		// https://rdf4j.org/documentation/getting-started/
		
		try {
						
		RepositoryResult<Statement> comptes = conn.getStatements (null, null, null, true);
		Model model = Iterations.addAll(comptes, new LinkedHashModel());
		model.setNamespace("rdf", RDF.NAMESPACE);
		model.setNamespace("rdfs", RDFS.NAMESPACE);
		model.setNamespace("xsd", XMLSchema.NAMESPACE);
		model.setNamespace("foaf", FOAF.NAMESPACE);
		model.setNamespace("ns", namespace);
		Rio.write(model, System.out, RDFFormat.TURTLE);
		
		//Question 
				
		}finally {
		conn.close();
		}
	}


	public static void main(String[] args) {

		// Local non persistant :
		Repository rep = new SailRepository(new MemoryStore());
		ValueFactory f = rep.getValueFactory();
			
		buildOntology(rep);
		createIndividuals(rep);
		
		// For debugging
		// displayOntology(rep);
		
		// Requête 1
		execQueryGetServersWindows(rep);
		
		// Requêtes 2
		execQueryGetPCFrom2019(rep);
		
		// Requêtes 3
		execQueryGetUsers(rep);
		
		// Requêtes 4 : 
		execQueryGetSWListInstalledByMichelBlanc(rep);
		
		// Requêtes 5: 
		execQueryGetServersWithPrinters(rep);

		// Requêtes 6: 
		execQueryGetDepartmentNames(rep);		
	}

}
