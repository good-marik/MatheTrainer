package de.marik.mypackage;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimitiveCommentsDatabase implements ICommentsDatabase {
	final ArrayList<String> positiveFeedbackList = new ArrayList<String>(
			Arrays.asList("Richtig!", "Well done!", "Ура!", "Sehr gut!", "Prima!", "Super!", "Правильно!", "Отлично!",
					"Großartig!", "Rechenprofi!", "Toll!", "Bravo!", "Exellent!", "Correct!", "Молодец!"));

	@Override
	public ArrayList<String> getPositiveFeedback() {
		return positiveFeedbackList;
	}
}
