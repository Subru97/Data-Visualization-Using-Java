package fr.epita.titanic.launcher;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.epita.titanic.datamodel.PassengerEntry;
import fr.epita.titanic.services.PassengerEntryCSVDAO;
import fr.epita.titanic.launcher.Chart1;
import fr.epita.titanic.launcher.Chart2;
import fr.epita.titanic.launcher.Chart3;
import fr.epita.titanic.launcher.Chart4;
import fr.epita.titanic.launcher.Chart5;

public class Launcher {
	static Map<String, Integer> sexIndexMapping = new LinkedHashMap<>(); 

	public static void main(String[] args) throws IOException, ParseException {
		PassengerEntryCSVDAO dao = new PassengerEntryCSVDAO();
		
		List<PassengerEntry> list = dao.readAll();
		
		list.forEach(p -> {
			if (!sexIndexMapping.containsKey(p.getSex())){
				sexIndexMapping.put(p.getSex() , sexIndexMapping.size() + 1);
			}
		});
				
		List<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();
		list.forEach(p -> {
			ArrayList<Double> row = new ArrayList<>();
			row.add((double) p.getSurvived());
	    	row.add((double) p.getPclass());
	    	row.add((double) sexIndexMapping.get(p.getSex()));
	    	row.add(p.getAge());
	    	row.add((double) p.getSiblingsOrSpouse());
	    	row.add((double) p.getParentsOrChildrens());
	    	row.add(p.getFare());
	    	matrix.add(row);
		});

	    Chart1 chart1 = new Chart1();
	    chart1.display(matrix);
	    
	    Chart2 chart2 = new Chart2();
	    chart2.display(matrix);
	    
	    Chart3 chart3 = new Chart3();
	    chart3.display(matrix);
	    
	    Chart4 chart4 = new Chart4();
	    chart4.display(matrix);
	    
	    Chart5 chart5 = new Chart5();
	    chart5.display(matrix);	    
	}

}
	