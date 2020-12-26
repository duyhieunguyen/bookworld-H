package pq.jdev.b001.bookstore.charts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pq.jdev.b001.bookstore.charts.model.ChartsResponse;
import pq.jdev.b001.bookstore.charts.model.Dataset;



@Service
public class ChartsService {

	public ChartsResponse getDummyData1(){
		ChartsResponse chartsResponse = new ChartsResponse();
		chartsResponse.setAppName("TOTAL BILL");
		
		List<String> lables = new ArrayList<>();
		lables.add("January");
		lables.add("February");
		lables.add("March");
		lables.add("April");
		lables.add("May");
		lables.add("June");
		lables.add("July");
		lables.add("August");
		lables.add("September");
		lables.add("October");
		lables.add("November");
		lables.add("December");
		chartsResponse.setLables(lables);
		
		List<Dataset> datasets = new ArrayList<>();
		Dataset dataset = new Dataset();
		List<Integer> value = new ArrayList<>();		
		value.add(65);
		value.add(59);
		value.add(80);
		value.add(81);
		value.add(56);
		value.add(55);
		value.add(40);
		dataset.setName("Total Bill Success");				
		dataset.setValue(value);
		datasets.add(dataset);
		
		dataset = new Dataset();
		value = new ArrayList<>();
		value.add(28);
		value.add(48);
		value.add(40);
		value.add(19);
		value.add(86);
		value.add(27);
		value.add(90);
		dataset.setName("Total Bill Fail");		
		dataset.setValue(value);
		datasets.add(dataset);
		
		chartsResponse.setDatasets(datasets);
		
		return chartsResponse;
	}

	public ChartsResponse getDummyData2(){
		ChartsResponse chartsResponse = new ChartsResponse();
		chartsResponse.setAppName("TOTAL PRICE");
		
		List<String> lables = new ArrayList<>();
		lables.add("January");
		lables.add("February");
		lables.add("March");
		lables.add("April");
		lables.add("May");
		lables.add("June");
		lables.add("July");
		lables.add("August");
		lables.add("September");
		lables.add("October");
		lables.add("November");
		lables.add("December");

		chartsResponse.setLables(lables);
		
		List<Dataset> datasets = new ArrayList<>();
		Dataset dataset = new Dataset();
		List<Integer> value = new ArrayList<>();		
		value.add(45);
		value.add(59);
		value.add(30);
		value.add(81);
		value.add(56);
		value.add(55);
		value.add(40);
		dataset.setName("Total Price Interest");				
		dataset.setValue(value);
		datasets.add(dataset);
		
		dataset = new Dataset();
		value = new ArrayList<>();
		value.add(28);
		value.add(70);
		value.add(40);
		value.add(19);
		value.add(20);
		value.add(27);
		value.add(90);
		dataset.setName("Total Price Losses");		
		dataset.setValue(value);
		datasets.add(dataset);
		
		chartsResponse.setDatasets(datasets);
		
		return chartsResponse;
	}

}
