package fr.epita.titanic.launcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.ChartTheme;

public class Chart3 implements ExampleChart<CategoryChart> {
	static ArrayList<Number> survivedAgeGroup = new ArrayList<>(Arrays.asList(0, 0, 0));
	static ArrayList<Number> deadAgeGroup = new ArrayList<>(Arrays.asList(0, 0, 0));

	public void display(List<ArrayList<Double>> matrix) {

		matrix.forEach(row -> {
	    	if(row.get(0) == 1) {
	    		if (row.get(3) < 18) {
	    			survivedAgeGroup.set(0, (int) survivedAgeGroup.get(0) + 1);
				} else if (18 <= row.get(3) && row.get(3) < 60) {
					survivedAgeGroup.set(1, (int) survivedAgeGroup.get(1) + 1);
				} else if (row.get(3) >= 60) {
					survivedAgeGroup.set(2, (int) survivedAgeGroup.get(2) + 1);
				}
	    	} else {
	    		if (row.get(3) < 18) {
	    			deadAgeGroup.set(0, (int) deadAgeGroup.get(0) + 1);
				} else if (18 <= row.get(3) && row.get(3) < 60) {
					deadAgeGroup.set(1, (int) deadAgeGroup.get(1) + 1);
				} else if (row.get(3) >= 60) {
					deadAgeGroup.set(2, (int) deadAgeGroup.get(2) + 1);
				}
	    	}
		});
	    	    
	    ExampleChart<CategoryChart> eChart3 = new Chart3();
	    CategoryChart cchart3 = eChart3.getChart();
	    new SwingWrapper<CategoryChart>(cchart3).displayChart();
	}
	
	@Override
	public CategoryChart getChart() {
		 // Create Chart
	    CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Survivors/Dead based on age group").xAxisTitle("Age group").yAxisTitle("Number of people").theme(ChartTheme.GGPlot2).build();
	 
	    // Customize Chart
	 
	    // Series
	    chart.addSeries("Dead", new ArrayList<String>(Arrays.asList(new String[] { "Children", "Adults", "Elderly" })), deadAgeGroup);
	    chart.addSeries("Survivors", new ArrayList<String>(Arrays.asList(new String[] { "Children", "Adults", "Elderly" })), survivedAgeGroup);
	    return chart;
	}

}
