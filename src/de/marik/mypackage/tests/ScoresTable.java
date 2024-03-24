package de.marik.mypackage.tests;

import java.io.Serializable;

//obsolete
public class ScoresTable implements Serializable {
	private String[][] scoresTable;
	
	public ScoresTable(){
		scoresTable = new String[][] {
			{"1", "dummy", "12"},
			{"2", "dummy", "6"},
			{"3", "dummy", "3"},
			{"4", "dummy", "1"},
			};
	}
	
	public void setEntry(int position, String name, int score) {
		scoresTable[position-1][1] = name;
		scoresTable[position-1][2] = String.valueOf(score);
	}
}
