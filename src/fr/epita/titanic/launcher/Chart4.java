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

public class Chart4 implements ExampleChart<PieChart> {
	static Map<Double, Double> fareCollectionOfClasses = new LinkedHashMap<>();

	public void display(List<ArrayList<Double>> matrix) {


	    matrix.forEach(row -> {
	    	if (!fareCollectionOfClasses.containsKey(row.get(1))){
    			fareCollectionOfClasses.put(row.get(1) , row.get(6));
			} else {
				fareCollectionOfClasses.put(row.get(1) , fareCollectionOfClasses.get(row.get(1)) + row.get(6));
			}
		});
	    	    
	    ExampleChart<PieChart> eChart4 = new Chart4();
	    PieChart pchart4 = eChart4.getChart();
	    new SwingWrapper<PieChart>(pchart4).displayChart();
	}
	
	@Override
	public PieChart getChart() {
		// Create Chart
	    PieChart chart = new PieChartBuilder().width(800).height(600).title("Total percentage of fare from each class").build();

	    // Customize Chart
	    Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(236, 143, 110), new Color(246, 199, 182) };
	    chart.getStyler().setSeriesColors(sliceColors);

	    // Series
	    
	    fareCollectionOfClasses.forEach((key, value) -> {
	    	chart.addSeries("Fare collected in class " + String.valueOf(key.intValue()), value);
	    });
	
	    return chart;
	}

}
