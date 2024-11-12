package pac1;

import org.testng.annotations.Test;

import demoMavenProject.BaseClass;

public class ContactTest extends BaseClass{
@Test(groups="regression")
public void createContactTest () {
	String URL=System.getProperty("url");
	String BROWSER=System.getProperty("browser");
	String USERNAME=System.getProperty("username");
	String PASSWORD=System.getProperty("password");
	System.out.println(URL);
	System.out.println(BROWSER);
	System.out.println(USERNAME);
	System.out.println(PASSWORD);
	
	System.out.println("execute createContactTestt");
}
@Test(groups="smoke")
public void modifyContactTest() {
	System.out.println("execute modifyContactTest");
}
}
