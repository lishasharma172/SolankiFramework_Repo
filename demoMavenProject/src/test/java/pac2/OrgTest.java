package pac2;

import org.testng.annotations.Test;

import demoMavenProject.BaseClass;

public class OrgTest extends BaseClass {
@Test(groups="regression")
public void createOrgTest() {
	System.out.println("execute createORgTest");
}
@Test(groups="smoke")
public void modifyOrgTest() {
	System.out.println("execute modifyOrgTest");
}
}
