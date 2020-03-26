package EventSourcing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Main class for running event sourcing. Not needed in mango project
@SpringBootApplication
public class EventSourcingMain {

	public static void main(String[] args) {
		SpringApplication.run(EventSourcingMain.class, args);
	}

}

