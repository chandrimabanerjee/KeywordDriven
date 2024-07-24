package testScenario;

import org.testng.annotations.Test;

import KeywordEngine.KeywordEngine;

public class LoginScenario {
public KeywordEngine e;
	@Test
	public void login() {
	e=new KeywordEngine();
e.startExecution("login");
	}
	
	
}
