package fr.epita.titanic.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.titanic.datamodel.PassengerEntry;

public class PassengerEntryCSVDAO {
	public List<PassengerEntry> readAll() throws IOException, ParseException{
		List<String> lines = Files.readAllLines(new File("data/input.csv").toPath());
		//because of the header:
		lines.remove(0);
		List<PassengerEntry> resultList = new ArrayList<>();
		for (String line : lines) {
			String[] parts = line.split(",");
			PassengerEntry pe = new PassengerEntry();
			pe.setSurvived(Integer.valueOf(parts[0]));
			pe.setPclass(Integer.valueOf(parts[1]));
			pe.setName(String.valueOf(parts[2]));
			pe.setSex(String.valueOf(parts[3]));
			pe.setAge(Double.valueOf(parts[4]));
			pe.setSiblingsOrSpouse(Integer.valueOf(parts[5]));
			pe.setParentsOrChildrens(Integer.valueOf(parts[6]));
			pe.setFare(Double.valueOf(parts[7]));
	
			resultList.add(pe);
		}
		
		return resultList;
		
	}
}