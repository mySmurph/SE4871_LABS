package src.Personel;

import src.Actions.Hazard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Worker extends Employee{
	public Worker(String name) {
		super(name);
	}

	/**
	 * Worker -    triggered the method [seeDanger] to report hazard to his overseer(supervisor)
	 * @param reporter
	 * @param hazard
	 */
	@Override
	public void seeDanger(IReporterHazard reporter, Hazard hazard)
	{
		overseer.seeDanger(reporter, hazard);
	}

	/**
	 * create a new hazard and report hazard to his overseer(supervisor)
	 */
	public void seeDanger(){
		Scanner observationDetails = new Scanner(System.in);

		List<String> types = new ArrayList<String>(){
			{
				add("Biological");
				add("Chemical");
				add("Physical");
				add("Safety");
				add("Ergonomic");
				add("Psychosocial");
			}
		};

		System.out.printf("Worker %s has observed something hazardous.\n", name);
		System.out.println("What type of hazard is this?");
		int i = 1;
		for (String type: types){
			System.out.printf("\t[%d] - %s\n", i++, type);
		}
		int typeSelection = observationDetails.nextInt()-1;
		if(typeSelection>=0 && typeSelection < types.size())
			typeSelection = 4;
		String type = types.get(typeSelection);

		System.out.println("On a scale from 1(low) - 10(high), how dangerous is this hazard?");
		int level = observationDetails.nextInt();

		System.out.println("Please provide a short description of this hazard:");
		String desp = observationDetails.nextLine();

		System.out.println("Triggering seeDanger(...) up the chain of responsibility.");
		seeDanger(this, new Hazard(type, desp, level)); // call internal method
	}

	/**
	 * Announce that this worker has evacuated.
	 */
	@Override
	public void evacuate() {
		System.out.println(name + " has been evacuated." );
	}

	/**
	 * Announce that this worker is fixing the hazard
	 * @param hazard the hazard being fixed
	 */
	public void fixIt(Hazard hazard){
		System.out.printf(" -> %s is fixing %s.\n", name, hazard.toString());
	}
}
