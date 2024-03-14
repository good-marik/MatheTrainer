package de.marik.mypackage.main;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimitiveCommentsDatabase implements ICommentsDatabase {
	final ArrayList<String> positiveFeedbackList = new ArrayList<String>(Arrays.asList("Richtig!", "Well done!", "Ура!",
			"Sehr gut!", "Prima!", "Super!", "Правильно!", "Отлично!", "Großartig!", "Rechenprofi!", "Toll!", "Bravo!",
			"Exellent!", "Correct!", "Молодец!", "Korrekt!", "Умница!", "Так держать!", "Good!", "Exactly!"));

	@Override
	public ArrayList<String> getPositiveFeedback() {
		return positiveFeedbackList;
	}
}
