package fr.epita.titanic.launcher;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToIntFunction;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;

import fr.epita.titanic.datamodel.PassengerEntry;
import fr.epita.titanic.services.PassengerEntryCSVDAO;

public class Launcher implements ExampleChart<CategoryChart> {
	static Map<String, Integer> sexIndexMapping = new LinkedHashMap<>(); 
	static ArrayList<Double> survivalCountOfClassesX = new ArrayList<>();
	static ArrayList<Double> survivalCountOfClassesY = new ArrayList<>();

	static ArrayList<Number> survivedMenAndWomen = new ArrayList<>(Arrays.asList(0, 0));
	static ArrayList<Number> deadMenAndWomen = new ArrayList<>(Arrays.asList(0, 0));
	
	static ArrayList<Number> survivedAgeGroup = new ArrayList<>(Arrays.asList(0, 0, 0));
	static ArrayList<Number> deadAgeGroup = new ArrayList<>(Arrays.asList(0, 0, 0));

	static String toggler = "chart1";

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
	    
	    System.out.println(matrix);
	    
//	    ArrayList<Double> survived = new ArrayList<>();
//	    ArrayList<Double> pclass = new ArrayList<>();
//	    ArrayList<Double> sex = new ArrayList<>();
//	    ArrayList<Double> age = new ArrayList<>();
//	    ArrayList<Double> siblingsOrSpouse = new ArrayList<>();
//	    ArrayList<Double> parentsOrChildrens = new ArrayList<>();
//	    ArrayList<Double> fare = new ArrayList<>();
//	    
//	    matrix.forEach(row -> {
//	    	survived.add(row.get(0));
//	    	pclass.add(row.get(1));
//	    	sex.add(row.get(2));
//	    	age.add(row.get(3));
//	    	siblingsOrSpouse.add(row.get(4));
//	    	parentsOrChildrens.add(row.get(5));
//	    	fare.add(row.get(6));
//	    });
	 	    
	    
//	    CHART 1
		Map<Double, Integer> survivalCountOfClasses = new LinkedHashMap<>();

	    matrix.forEach(row -> {
	    	if(row.get(0) == 1) {
	    		if (!survivalCountOfClasses.containsKey(row.get(1))){
	    			survivalCountOfClasses.put(row.get(1) , 1);
				} else {
					survivalCountOfClasses.put(row.get(1) , survivalCountOfClasses.get(row.get(1)) + 1);
				}
	    	}
		});
	    
		Set<Double> survivalCountOfClassesKeys = survivalCountOfClasses.keySet();
	    Collection<Integer> survivalCountOfClassesValues = survivalCountOfClasses.values();
	    	    
	    survivalCountOfClassesKeys.forEach(key -> {
	    	survivalCountOfClassesX.add(key);
	    });
	    survivalCountOfClassesValues.forEach(value -> {
	    	survivalCountOfClassesY.add((double) value);
	    });
	    
//	    System.out.println(survivalCountOfClasses);
	    
	    ExampleChart<CategoryChart> eChart1 = new Launcher();
	    CategoryChart chart1 = eChart1.getChart();
	    new SwingWrapper<CategoryChart>(chart1).displayChart();
	    
	    toggler = "chart2";

	    matrix.forEach(row -> {
	    	if(row.get(0) == 1) {
	    		if (row.get(2) == 1) {
	    			survivedMenAndWomen.set(0, (int) survivedMenAndWomen.get(0) + 1);
				} else {
					survivedMenAndWomen.set(1, (int) survivedMenAndWomen.get(1) + 1);
				}
	    	} else {
	    		if (row.get(2) == 1) {
	    			deadMenAndWomen.set(0, (int) deadMenAndWomen.get(0) + 1);
				} else {
					deadMenAndWomen.set(1, (int) deadMenAndWomen.get(1) + 1);
				}
	    	}
		});
	    
//	    System.out.println(survivedMenAndWomen);
	    
	    ExampleChart<CategoryChart> eChart2 = new Launcher();
	    CategoryChart chart2 = eChart2.getChart();
	    new SwingWrapper<CategoryChart>(chart2).displayChart();


	    toggler = "chart3";

	    matrix.forEach(row -> {
	    	if(row.get(0) == 1) {
	    		if (row.get(3) < 18) {
	    			survivedAgeGroup.set(0, (int) survivedAgeGroup.get(0) + 1);
				} else if (18 < row.get(3) && row.get(3) < 60) {
					survivedAgeGroup.set(1, (int) survivedAgeGroup.get(1) + 1);
				} else if (row.get(3) > 60) {
					survivedAgeGroup.set(2, (int) survivedAgeGroup.get(2) + 1);
				}
	    	} else {
	    		if (row.get(2) == 1) {
	    			deadAgeGroup.set(0, (int) deadAgeGroup.get(0) + 1);
				} else if (18 < row.get(3) && row.get(3) < 60) {
					deadAgeGroup.set(1, (int) deadAgeGroup.get(1) + 1);
				} else if (row.get(3) > 60) {
					deadAgeGroup.set(1, (int) deadAgeGroup.get(2) + 1);
				}
	    	}
		});
	    
//	    System.out.println(survivedMenAndWomen);
	    
	    ExampleChart<CategoryChart> eChart3 = new Launcher();
	    CategoryChart chart3 = eChart3.getChart();
	    new SwingWrapper<CategoryChart>(chart3).displayChart();

	}
	
	@Override
	public CategoryChart getChart() {

		if(toggler == "chart1") {
			// Create Chart
		    CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Survivors in each pclass").xAxisTitle("Pclass").yAxisTitle("Number of people").build();

		    // Customize Chart
		    chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		    chart.getStyler().setHasAnnotations(true);

		    // Series
		    chart.addSeries("Survivors", survivalCountOfClassesX, survivalCountOfClassesY);
		    return chart;
		    
		} else if(toggler == "chart2") {
			 // Create Chart
		    CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Survivors/Dead based on gender").xAxisTitle("Gender").yAxisTitle("Number of people").theme(ChartTheme.GGPlot2).build();
		 
		    // Customize Chart
		 
		    // Series
		    chart.addSeries("Dead", new ArrayList<String>(Arrays.asList(new String[] { "Men", "Women" })), deadMenAndWomen);
		    chart.addSeries("Survivors", new ArrayList<String>(Arrays.asList(new String[] { "Men", "Women" })), survivedMenAndWomen);
		    return chart;
		    
		} else if(toggler == "chart3") {
			 // Create Chart
		    CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Survivors/Dead based on age group").xAxisTitle("Gender").yAxisTitle("Number of people").theme(ChartTheme.GGPlot2).build();
		 
		    // Customize Chart
		 
		    // Series
		    chart.addSeries("Dead", new ArrayList<String>(Arrays.asList(new String[] { "Children", "Adults", "Elderly" })), deadAgeGroup);
		    chart.addSeries("Survivors", new ArrayList<String>(Arrays.asList(new String[] { "Children", "Adults", "Elderly" })), survivedAgeGroup);
		    return chart;
		    
		}
		return null;
	  
	}
	
}
