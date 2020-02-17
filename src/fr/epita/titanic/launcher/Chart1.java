package fr.epita.titanic.launcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.LegendPosition;

public class Chart1 implements ExampleChart<CategoryChart> {
	static Map<String, Integer> sexIndexMapping = new LinkedHashMap<>(); 
	static ArrayList<Double> survivalCountOfClassesX = new ArrayList<>();
	static ArrayList<Double> survivalCountOfClassesY = new ArrayList<>();

	public void display(List<ArrayList<Double>> matrix) {

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
	    
		ExampleChart<CategoryChart> eChart1 = new Chart1();
	    CategoryChart cchart1 = eChart1.getChart();
	    new SwingWrapper<CategoryChart>(cchart1).displayChart();
	}
	
	@Override
	public CategoryChart getChart() {
		// Create Chart
	    CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Survivors in each pclass").xAxisTitle("Pclass").yAxisTitle("Number of people").build();

	    // Customize Chart
	    chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
	    chart.getStyler().setHasAnnotations(true);

	    // Series
	    chart.addSeries("Survivors", survivalCountOfClassesX, survivalCountOfClassesY);
	    return chart;
	}

}
