package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	public static void main(String[] args) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Asteroids");
		config.setWindowSizeLimits(1000,600,1000,600);

		//new Lwjgl3Application(new Game(), config);

		AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext();
		for (String bn : application.getBeanDefinitionNames()) {
			System.out.println(bn);
		}

		application.scan("dk.sdu.mmmi.cbse.main");
		application.refresh();

		new Lwjgl3Application((ApplicationListener) application.getBean("game"), config);
	}
	
}
