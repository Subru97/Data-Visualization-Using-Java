package fr.epita.titanic.launcher;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;

public class Chart5 implements ExampleChart<PieChart> {
	static Integer peopleWithRelatives = 0;
	static Integer peopleWithOutRelatives = 0;

	public void display(List<ArrayList<Double>> matrix) {


	    matrix.forEach(row -> {
	    	if (row.get(4) != 0 || row.get(5) != 0){
	    		peopleWithRelatives += 1;
	    	}
		});
	    
	    peopleWithOutRelatives = matrix.size() - peopleWithRelatives;
	    	    
	    ExampleChart<PieChart> eChart5 = new Chart5();
	    PieChart pchart5 = eChart5.getChart();
	    new SwingWrapper<PieChart>(pchart5).displayChart();
	}
	
	@Override
	public PieChart getChart() {
		// Create Chart
	    PieChart chart = new PieChartBuilder().width(800).height(600).title("Total percentage of people w & w/o relatives").build();

	    // Customize Chart
	    Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(236, 143, 110), new Color(246, 199, 182) };
	    chart.getStyler().setSeriesColors(sliceColors);

	    // Series
	    chart.addSeries("People with relatives", peopleWithRelatives);
	    chart.addSeries("People without relatives", peopleWithOutRelatives);
	
	    return chart;
	}

}
