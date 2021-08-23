package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AnonymousShoutListTest extends AcmeWorkPlansTest{
	
	/*
	 * En este test se va comprobar si cualquier anonimo puede 
	 * acceder a la lista de los shouts recientes
	 * 
	 * Lo esperado es que los shout del listado
	 * coincidan con los establecidos en el archivo csv.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void ListRecent(final int recordIndex,final String moment, final String author, final String text, final String info) {
		
		// Accedemos como anonimo
		super.clickOnMenu("Anonymous", "List recent shouts");
		// Comprobación de columna
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/dashboard-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAnonymousNegative(final String user, final String password) {

		super.signIn(user, password);

		this.driver.get("localhost:8080/Acme-Work-Plans/anonymous/shout/list-recent");
		super.checkPanicExists();

		super.signOut();
	}
	
	
}
