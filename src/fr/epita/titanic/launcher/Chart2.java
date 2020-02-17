package fr.epita.titanic.launcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.ChartTheme;

public class Chart2 implements ExampleChart<CategoryChart> {
	static ArrayList<Number> survivedMenAndWomen = new ArrayList<>(Arrays.asList(0, 0));
	static ArrayList<Number> deadMenAndWomen = new ArrayList<>(Arrays.asList(0, 0));

	public void display(List<ArrayList<Double>> matrix) {

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
	    
	    ExampleChart<CategoryChart> eChart2 = new Chart2();
	    CategoryChart cchart2 = eChart2.getChart();
	    new SwingWrapper<CategoryChart>(cchart2).displayChart();
	}
	
	@Override
	public CategoryChart getChart() {
		// Create Chart
	    CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Survivors/Dead based on gender").xAxisTitle("Gender").yAxisTitle("Number of people").theme(ChartTheme.GGPlot2).build();
	 
	    // Series
	    chart.addSeries("Dead", new ArrayList<String>(Arrays.asList(new String[] { "Men", "Women" })), deadMenAndWomen);
	    chart.addSeries("Survivors", new ArrayList<String>(Arrays.asList(new String[] { "Men", "Women" })), survivedMenAndWomen);
	    return chart;	
	}

}
