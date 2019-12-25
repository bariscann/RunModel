/*
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.clusterers.XMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.instance.RemovePercentage;
import weka.filters.unsupervised.instance.RemoveWithValues;

public class BRADLEYHTCBIZIM {
	public static final int iterasyonSayisi=20;
	public static void main(String[] args) throws Exception {
		long time1=System.currentTimeMillis();
		long toplamsure=0;
		ArrayList<Long> sureler=new ArrayList<Long>();
		ArrayList<Long> timerlarim1=new ArrayList<Long>();
		ArrayList<Long> timerlarim2=new ArrayList<Long>();
		System.out.println(time1);
		Instances general_data=null;
		general_data=loadArff(general_data,"eskiall.arff");		
		System.out.println(general_data.size());
		Instances blank_data=new Instances(general_data);
		RemoveWithValues rwv=new RemoveWithValues();
		rwv.setInputFormat(general_data);
		try {
			blank_data=Filter.useFilter(blank_data, rwv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("BLANK DATA SİZE "+blank_data.size());
		
		Instances minibusData=new Instances(blank_data);
		Instances walkingData=new Instances(blank_data);
		Instances carData=new Instances(blank_data);
		Instances busData=new Instances(blank_data);
		Instances tramData=new Instances(blank_data);
		Instances metroKData=new Instances(blank_data);
		Instances metroHData=new Instances(blank_data);
		Instances marmarayData=new Instances(blank_data);
		Instances metrobusData=new Instances(blank_data);
		Instances ferryData=new Instances(blank_data);
		Instances stationaryData=new Instances(blank_data);
		Instances secmedenTrain=new Instances(blank_data);
		for (Instance instance : general_data) {
			double classIndex=instance.classValue();
			if(classIndex==1) {
				minibusData.add(instance);
			}else if(classIndex==0) {
				walkingData.add(instance);
			}else if(classIndex==2) {
				carData.add(instance);
			}else if(classIndex==3) {
				busData.add(instance);
			}else if(classIndex==4) {
				tramData.add(instance);
			}else if(classIndex==5) {
				metroKData.add(instance);
			}else if(classIndex==6) {
				metroHData.add(instance);
			}else if(classIndex==7) {
				marmarayData.add(instance);
			}else if(classIndex==8) {
				metrobusData.add(instance);
			}else if(classIndex==9) {
				ferryData.add(instance);
			}else if(classIndex==10) {
				stationaryData.add(instance);
			}
		}
		
		System.out.println("minibus Data size -> "+minibusData.size());
		System.out.println("walking Data size -> "+walkingData.size());
		System.out.println("car Data size -> "+carData.size());
		System.out.println("bus Data size -> "+busData.size());
		System.out.println("tram Data size -> "+tramData.size());
		System.out.println("metroK Data size -> "+metroKData.size());
		System.out.println("metroH Data size -> "+metroHData.size());
		System.out.println("marmaray Data size -> "+marmarayData.size());
		System.out.println("metrobus Data size -> "+metrobusData.size());
		System.out.println("ferry Data size -> "+ferryData.size());
		System.out.println("stationary Data size -> "+stationaryData.size());

		Instances minibus_gecici = splitData(minibusData, 70, false);
		Instances minibus_train = splitData(minibus_gecici, 33.33, true);
		Instances minibus_test = splitData(minibus_gecici, 33.33, false);
		Instances minibus_algoritmam = splitData(minibusData, 70, true);	
//		System.out.println("minibus_gecici size -> "+minibus_gecici.size());
		System.out.println("minibus_train size -> "+minibus_train.size());
		System.out.println("minibus_test size -> "+minibus_test.size());
		System.out.println("minibus_algoritmam size -> "+minibus_algoritmam.size());
		
		Instances walking_gecici = splitData(walkingData, 70, false);
		Instances walking_train = splitData(walking_gecici, 33.33, true);
		Instances walking_test = splitData(walking_gecici, 33.33, false);
		Instances walking_algoritmam = splitData(walkingData, 70, true);	
//		System.out.println("walking_gecici size -> "+walking_gecici.size());
		System.out.println("walking_train size -> "+walking_train.size());
		System.out.println("walking_test size -> "+walking_test.size());
		System.out.println("walking_algoritmam size -> "+walking_algoritmam.size());
		
		Instances car_gecici = splitData(carData, 70, false);
		Instances car_train = splitData(car_gecici, 33.33, true);
		Instances car_test = splitData(car_gecici, 33.33, false);
		Instances car_algoritmam = splitData(carData, 70, true);	
//		System.out.println("car_gecici size -> "+car_gecici.size());
		System.out.println("car_train size -> "+car_train.size());
		System.out.println("car_test size -> "+car_test.size());
		System.out.println("car_algoritmam size -> "+car_algoritmam.size());
		
		Instances bus_gecici = splitData(busData, 70, false);
		Instances bus_train = splitData(bus_gecici, 33.33, true);
		Instances bus_test = splitData(bus_gecici, 33.33, false);
		Instances bus_algoritmam = splitData(busData, 70, true);	
//		System.out.println("bus_gecici size -> "+bus_gecici.size());
		System.out.println("bus_train size -> "+bus_train.size());
		System.out.println("bus_test size -> "+bus_test.size());
		System.out.println("bus_algoritmam size -> "+bus_algoritmam.size());
		
		Instances tram_gecici = splitData(tramData, 70, false);
		Instances tram_train = splitData(tram_gecici, 33.33, true);
		Instances tram_test = splitData(tram_gecici, 33.33, false);
		Instances tram_algoritmam = splitData(tramData, 70, true);	
//		System.out.println("tram_gecici size -> "+tram_gecici.size());
		System.out.println("tram_train size -> "+tram_train.size());
		System.out.println("tram_test size -> "+tram_test.size());
		System.out.println("tram_algoritmam size -> "+tram_algoritmam.size());
		
		Instances metroK_gecici = splitData(metroKData, 70, false);
		Instances metroK_train = splitData(metroK_gecici, 33.33, true);
		Instances metroK_test = splitData(metroK_gecici, 33.33, false);
		Instances metroK_algoritmam = splitData(metroKData, 70, true);	
//		System.out.println("metroK_gecici size -> "+metroK_gecici.size());
		System.out.println("metroK_train size -> "+metroK_train.size());
		System.out.println("metroK_test size -> "+metroK_test.size());
		System.out.println("metroK_algoritmam size -> "+metroK_algoritmam.size());
		
		Instances metroH_gecici = splitData(metroHData, 70, false);
		Instances metroH_train = splitData(metroH_gecici, 33.33, true);
		Instances metroH_test = splitData(metroH_gecici, 33.33, false);
		Instances metroH_algoritmam = splitData(metroHData, 70, true);	
//		System.out.println("metroH_gecici size -> "+metroH_gecici.size());
		System.out.println("metroH_train size -> "+metroH_train.size());
		System.out.println("metroH_test size -> "+metroH_test.size());
		System.out.println("metroH_algoritmam size -> "+metroH_algoritmam.size());
		
		Instances marmaray_gecici = splitData(marmarayData, 70, false);
		Instances marmaray_train = splitData(marmaray_gecici, 33.33, true);
		Instances marmaray_test = splitData(marmaray_gecici, 33.33, false);
		Instances marmaray_algoritmam = splitData(marmarayData, 70, true);	
//		System.out.println("marmaray_gecici size -> "+marmaray_gecici.size());
		System.out.println("marmaray_train size -> "+marmaray_train.size());
		System.out.println("marmaray_test size -> "+marmaray_test.size());
		System.out.println("marmaray_algoritmam size -> "+marmaray_algoritmam.size());
		
		Instances metrobus_gecici = splitData(metrobusData, 70, false);
		Instances metrobus_train = splitData(metrobus_gecici, 33.33, true);
		Instances metrobus_test = splitData(metrobus_gecici, 33.33, false);
		Instances metrobus_algoritmam = splitData(metrobusData, 70, true);	
//		System.out.println("metrobus_gecici size -> "+metrobus_gecici.size());
		System.out.println("metrobus_train size -> "+metrobus_train.size());
		System.out.println("metrobus_test size -> "+metrobus_test.size());
		System.out.println("metrobus_algoritmam size -> "+metrobus_algoritmam.size());

		Instances ferry_gecici = splitData(ferryData, 70, false);
		Instances ferry_train = splitData(ferry_gecici, 33.33, true);
		Instances ferry_test = splitData(ferry_gecici, 33.33, false);
		Instances ferry_algoritmam = splitData(ferryData, 70, true);	
//		System.out.println("ferry_gecici size -> "+ferry_gecici.size());
		System.out.println("ferry_train size -> "+ferry_train.size());
		System.out.println("ferry_test size -> "+ferry_test.size());
		System.out.println("ferry_algoritmam size -> "+ferry_algoritmam.size());
		
		Instances stationary_gecici = splitData(stationaryData, 70, false);
		Instances stationary_train = splitData(stationary_gecici, 33.33, true);
		Instances stationary_test = splitData(stationary_gecici, 33.33, false);
		Instances stationary_algoritmam = splitData(stationaryData, 70, true);	
//		System.out.println("stationary_gecici size -> "+stationary_gecici.size());
		System.out.println("stationary_train size -> "+stationary_train.size());
		System.out.println("stationary_test size -> "+stationary_test.size());
		System.out.println("stationary_algoritmam size -> "+stationary_algoritmam.size());
		

		
		int minibus_artis=minibus_algoritmam.size()/iterasyonSayisi;
		int walking_artis=walking_algoritmam.size()/iterasyonSayisi;
		int car_artis=car_algoritmam.size()/iterasyonSayisi;
		int bus_artis=bus_algoritmam.size()/iterasyonSayisi;
		int tram_artis=tram_algoritmam.size()/iterasyonSayisi;
		int metroH_artis=metroH_algoritmam.size()/iterasyonSayisi;
		int marmaray_artis=marmaray_algoritmam.size()/iterasyonSayisi;
		int metrobus_artis=metrobus_algoritmam.size()/iterasyonSayisi;
		int metroK_artis=metrobus_algoritmam.size()/iterasyonSayisi;
		int ferry_artis=ferry_algoritmam.size()/iterasyonSayisi;
		int stationary_artis=stationary_algoritmam.size()/iterasyonSayisi;

		System.out.println("minibus_artis -> "+minibus_artis);
		System.out.println("walking_artis -> "+walking_artis);
		System.out.println("car_artis -> "+car_artis);
		System.out.println("bus_artis -> "+bus_artis);
		System.out.println("tram_artis -> "+tram_artis);
		System.out.println("metroH_artis -> "+metroH_artis);
		System.out.println("marmaray_artis -> "+marmaray_artis);
		System.out.println("metrobus_artis -> "+metrobus_artis);
		System.out.println("ferry_artis -> "+ferry_artis);
		System.out.println("stationary_artis -> "+stationary_artis);
		Instances training_data=new Instances(blank_data);
		training_data=addOn(training_data, minibus_train);
		training_data=addOn(training_data, walking_train);
		training_data=addOn(training_data, car_train);
		training_data=addOn(training_data, bus_train);
		training_data=addOn(training_data, tram_train);
		training_data=addOn(training_data, metroH_train);
		training_data=addOn(training_data, marmaray_train);
		training_data=addOn(training_data, metrobus_train);
		training_data=addOn(training_data, ferry_train);
		training_data=addOn(training_data, stationary_train);
		secmedenTrain=addOn(secmedenTrain, minibus_train);
		secmedenTrain=addOn(secmedenTrain, walking_train);
		secmedenTrain=addOn(secmedenTrain, car_train);
		secmedenTrain=addOn(secmedenTrain, bus_train);
		secmedenTrain=addOn(secmedenTrain, tram_train);
		secmedenTrain=addOn(secmedenTrain, metroH_train);
		secmedenTrain=addOn(secmedenTrain, marmaray_train);
		secmedenTrain=addOn(secmedenTrain, metrobus_train);
		secmedenTrain=addOn(secmedenTrain, ferry_train);
		secmedenTrain=addOn(secmedenTrain, stationary_train);
		ArrayList<Double> basarilar=new ArrayList<Double>();
		ArrayList<Double> basarilar2=new ArrayList<Double>();
		Instances test_data=new Instances(blank_data);
		test_data=addOn(test_data, minibus_test);
		test_data=addOn(test_data, walking_test);
		test_data=addOn(test_data, car_test);
		test_data=addOn(test_data, bus_test);
		test_data=addOn(test_data, tram_test);
		test_data=addOn(test_data, metroH_test);
		test_data=addOn(test_data, marmaray_test);
		test_data=addOn(test_data, metrobus_test);
		test_data=addOn(test_data, ferry_test);
		test_data=addOn(test_data, stationary_test);
		Instances walking_train_filtered=deleteClassAtt(walking_train);
		Instances minibus_train_filtered=deleteClassAtt(minibus_train);
		Instances car_train_filtered=deleteClassAtt(car_train);
		Instances bus_train_filtered=deleteClassAtt(bus_train);
		Instances tram_train_filtered=deleteClassAtt(tram_train);
		Instances metroK_train_filtered=deleteClassAtt(metroK_train);
		Instances metroH_train_filtered=deleteClassAtt(metroH_train);
		Instances marmaray_train_filtered=deleteClassAtt(marmaray_train);
		Instances metrobus_train_filtered=deleteClassAtt(metrobus_train);
		Instances ferry_train_filtered=deleteClassAtt(ferry_train);
		Instances stationary_train_filtered=deleteClassAtt(stationary_train);
		
		Instances walking_algoritmam_filtered=deleteClassAtt(walking_algoritmam);
		Instances minibus_algoritmam_filtered=deleteClassAtt(minibus_algoritmam);
		Instances car_algoritmam_filtered=deleteClassAtt(car_algoritmam);
		Instances bus_algoritmam_filtered=deleteClassAtt(bus_algoritmam);
		Instances tram_algoritmam_filtered=deleteClassAtt(tram_algoritmam);
		Instances metroK_algoritmam_filtered=deleteClassAtt(metroK_algoritmam);
		Instances metroH_algoritmam_filtered=deleteClassAtt(metroH_algoritmam);
		Instances marmaray_algoritmam_filtered=deleteClassAtt(marmaray_algoritmam);
		Instances metrobus_algoritmam_filtered=deleteClassAtt(metrobus_algoritmam);
		Instances ferry_algoritmam_filtered=deleteClassAtt(ferry_algoritmam);
		Instances stationary_algoritmam_filtered=deleteClassAtt(stationary_algoritmam);
		
		ArrayList<Double> walkingProbClasses=new ArrayList<Double>();
		ArrayList<Double> walkingProbClusters=new ArrayList<Double>();
		ArrayList<Double> minibusProbClasses=new ArrayList<Double>();
		ArrayList<Double> minibusProbClusters=new ArrayList<Double>();
		ArrayList<Double> carProbClasses=new ArrayList<Double>();
		ArrayList<Double> carProbClusters=new ArrayList<Double>();
		ArrayList<Double> busProbClasses=new ArrayList<Double>();
		ArrayList<Double> busProbClusters=new ArrayList<Double>();
		ArrayList<Double> tramProbClasses=new ArrayList<Double>();
		ArrayList<Double> tramProbClusters=new ArrayList<Double>();
		ArrayList<Double> metroKProbClasses=new ArrayList<Double>();
		ArrayList<Double> metroKProbClusters=new ArrayList<Double>();
		ArrayList<Double> metroHProbClasses=new ArrayList<Double>();
		ArrayList<Double> metroHProbClusters=new ArrayList<Double>();
		ArrayList<Double> marmarayProbClasses=new ArrayList<Double>();
		ArrayList<Double> marmarayProbClusters=new ArrayList<Double>();
		ArrayList<Double> metrobusProbClasses=new ArrayList<Double>();
		ArrayList<Double> metrobusProbClusters=new ArrayList<Double>();	
		ArrayList<Double> ferryProbClasses=new ArrayList<Double>();
		ArrayList<Double> ferryProbClusters=new ArrayList<Double>();		
		ArrayList<Double> stationaryProbClasses=new ArrayList<Double>();
		ArrayList<Double> stationaryProbClusters=new ArrayList<Double>();		
		for(int k=0;k<iterasyonSayisi;k++) {		
			long iterasyonzamani1=System.currentTimeMillis();
			XMeans walkingIcin=new XMeans();
			walkingIcin.buildClusterer(walking_train_filtered);
			XMeans minibusIcin=new XMeans();
			minibusIcin.buildClusterer(minibus_train_filtered);
			XMeans carIcin=new XMeans();
			carIcin.buildClusterer(car_train_filtered);
			XMeans busIcin=new XMeans();
			busIcin.buildClusterer(bus_train_filtered);
			XMeans trainIcin=new XMeans();
			trainIcin.buildClusterer(tram_train_filtered);
			XMeans metroKIcin=new XMeans();
			metroKIcin.buildClusterer(metroK_train_filtered);
			XMeans metroHIcin=new XMeans();
			metroHIcin.buildClusterer(metroH_train_filtered);
			XMeans marmarayIcin=new XMeans();
			marmarayIcin.buildClusterer(marmaray_train_filtered);
			XMeans metrobusIcin=new XMeans();
			metrobusIcin.buildClusterer(metrobus_train_filtered);
			XMeans ferryIcin=new XMeans();
			ferryIcin.buildClusterer(ferry_train_filtered);
//			XMeans stationaryIcin=new XMeans();
//			stationaryIcin.buildClusterer(stationary_train_filtered);
			
			Instances walkingCentersOfClusters=walkingIcin.getClusterCenters();
			Instances minibusCentersOfClusters=minibusIcin.getClusterCenters();
			Instances carCentersOfClusters=carIcin.getClusterCenters();
			Instances busCentersOfClusters=busIcin.getClusterCenters();
			Instances trainCentersOfClusters=trainIcin.getClusterCenters();
			Instances metroKCentersOfClusters=metroKIcin.getClusterCenters();
			Instances metroHCentersOfClusters=metroHIcin.getClusterCenters();
			Instances marmarayCentersOfClusters=marmarayIcin.getClusterCenters();
			Instances metrobusCentersOfClusters=metrobusIcin.getClusterCenters();
			Instances ferryCentersOfClusters=ferryIcin.getClusterCenters();
//			Instances stationaryCentersOfClusters=stationaryIcin.getClusterCenters();

			
			for(int i=walking_artis*k;i<k*walking_artis+walking_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(walking_algoritmam_filtered.get(i), walkingCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<walkingIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(walking_algoritmam_filtered.get(i), walkingCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(walking_algoritmam_filtered.get(i), walkingCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(walking_algoritmam_filtered.get(i), walkingCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				walkingProbClasses.add(probClass);
				walkingProbClusters.add(probCluster);
			
			}

			for(int i=minibus_artis*k;i<k*minibus_artis+minibus_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(minibus_algoritmam_filtered.get(i), minibusCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<minibusIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(minibus_algoritmam_filtered.get(i), minibusCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(minibus_algoritmam_filtered.get(i), minibusCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(minibus_algoritmam_filtered.get(i), minibusCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				minibusProbClasses.add(probClass);
				minibusProbClusters.add(probCluster);
			}			
			
			for(int i=car_artis*k;i<k*car_artis+car_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(car_algoritmam_filtered.get(i), carCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<carIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(car_algoritmam_filtered.get(i), carCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(car_algoritmam_filtered.get(i), carCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(car_algoritmam_filtered.get(i), carCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				carProbClasses.add(probClass);
				carProbClusters.add(probCluster);
			}
			
			for(int i=bus_artis*k;i<k*bus_artis+bus_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(bus_algoritmam_filtered.get(i), busCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<busIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(bus_algoritmam_filtered.get(i), busCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(bus_algoritmam_filtered.get(i), busCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(bus_algoritmam_filtered.get(i), busCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				busProbClasses.add(probClass);
				busProbClusters.add(probCluster);
			}
			
			for(int i=tram_artis*k;i<k*tram_artis+tram_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(tram_algoritmam_filtered.get(i), trainCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<trainIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(tram_algoritmam_filtered.get(i), trainCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(tram_algoritmam_filtered.get(i), trainCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(tram_algoritmam_filtered.get(i), trainCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				tramProbClasses.add(probClass);
				tramProbClusters.add(probCluster);
			}
			
			for(int i=metroK_artis*k;i<k*metroK_artis+metroK_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(metroK_algoritmam_filtered.get(i), metroKCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<metroKIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(metroK_algoritmam_filtered.get(i), metroKCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(metroK_algoritmam_filtered.get(i), metroKCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(metroK_algoritmam_filtered.get(i), metroKCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				metroKProbClasses.add(probClass);
				metroKProbClusters.add(probCluster);
			}

			for(int i=metroH_artis*k;i<k*metroH_artis+metroH_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(metroH_algoritmam_filtered.get(i), metroHCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<metroHIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(metroH_algoritmam_filtered.get(i), metroHCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(metroH_algoritmam_filtered.get(i), metroHCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(metroH_algoritmam_filtered.get(i), metroHCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				metroHProbClasses.add(probClass);
				metroHProbClusters.add(probCluster);
			}
			
			for(int i=marmaray_artis*k;i<k*marmaray_artis+marmaray_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(marmaray_algoritmam_filtered.get(i), marmarayCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<marmarayIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(marmaray_algoritmam_filtered.get(i), marmarayCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(marmaray_algoritmam_filtered.get(i), marmarayCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(marmaray_algoritmam_filtered.get(i), marmarayCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				marmarayProbClasses.add(probClass);
				marmarayProbClusters.add(probCluster);
			}
			
			for(int i=metrobus_artis*k;i<k*metrobus_artis+metrobus_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(metrobus_algoritmam_filtered.get(i), metrobusCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<metrobusIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(metrobus_algoritmam_filtered.get(i), metrobusCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(metrobus_algoritmam_filtered.get(i), metrobusCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(metrobus_algoritmam_filtered.get(i), metrobusCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				metrobusProbClasses.add(probClass);
				metrobusProbClusters.add(probCluster);
			}
			
			for(int i=ferry_artis*k;i<k*ferry_artis+ferry_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(ferry_algoritmam_filtered.get(i), ferryCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<ferryIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(ferry_algoritmam_filtered.get(i), ferryCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(ferry_algoritmam_filtered.get(i), ferryCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(ferry_algoritmam_filtered.get(i), ferryCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				ferryProbClasses.add(probClass);
				ferryProbClusters.add(probCluster);
			}
			
//			for(int i=stationary_artis*k;i<k*stationary_artis+stationary_artis;i++) {
//				double uzaklikMin=oklidUzaklikBul(stationary_algoritmam_filtered.get(i), stationaryCentersOfClusters.get(0));
//				double uzaklik=uzaklikMin;
//				for(int j=1;j<stationaryIcin.numberOfClusters();j++) {
//					if(oklidUzaklikBul(stationary_algoritmam_filtered.get(i), stationaryCentersOfClusters.get(j))<uzaklikMin) {
//						uzaklikMin=oklidUzaklikBul(stationary_algoritmam_filtered.get(i), stationaryCentersOfClusters.get(j));
//					}
//					uzaklik+=oklidUzaklikBul(stationary_algoritmam_filtered.get(i), stationaryCentersOfClusters.get(j));
//				}
//				double probCluster=uzaklikMin;
//				double probClass=uzaklik;
//				stationaryProbClasses.add(probClass);
//				stationaryProbClusters.add(probCluster);
//			}
			
			ArrayList<Double> walkingClassThresholdBul=new ArrayList<Double>(walkingProbClasses);
			ArrayList<Double> walkingClusterThresholdBul=new ArrayList<Double>(walkingProbClusters);
			ArrayList<Double> minibusClassThresholdBul=new ArrayList<Double>(minibusProbClasses);
			ArrayList<Double> minibusClusterThresholdBul=new ArrayList<Double>(minibusProbClusters);
			ArrayList<Double> carClassThresholdBul=new ArrayList<Double>(carProbClasses);
			ArrayList<Double> carClusterThresholdBul=new ArrayList<Double>(carProbClusters);
			ArrayList<Double> busClassThresholdBul=new ArrayList<Double>(busProbClasses);
			ArrayList<Double> busClusterThresholdBul=new ArrayList<Double>(busProbClusters);
			ArrayList<Double> trainClassThresholdBul=new ArrayList<Double>(tramProbClasses);
			ArrayList<Double> trainClusterThresholdBul=new ArrayList<Double>(tramProbClusters);
			ArrayList<Double> metroKClassThresholdBul=new ArrayList<Double>(metroKProbClasses);
			ArrayList<Double> metroKClusterThresholdBul=new ArrayList<Double>(metroKProbClusters);
			ArrayList<Double> metroHClassThresholdBul=new ArrayList<Double>(metroHProbClasses);
			ArrayList<Double> metroHClusterThresholdBul=new ArrayList<Double>(metroHProbClusters);
			ArrayList<Double> marmarayClassThresholdBul=new ArrayList<Double>(marmarayProbClasses);
			ArrayList<Double> marmarayClusterThresholdBul=new ArrayList<Double>(marmarayProbClusters);
			ArrayList<Double> metrobusClassThresholdBul=new ArrayList<Double>(metrobusProbClasses);
			ArrayList<Double> metrobusClusterThresholdBul=new ArrayList<Double>(metrobusProbClusters);
			ArrayList<Double> ferryClassThresholdBul=new ArrayList<Double>(ferryProbClasses);
			ArrayList<Double> ferryClusterThresholdBul=new ArrayList<Double>(ferryProbClusters);
//			ArrayList<Double> stationaryClassThresholdBul=new ArrayList<Double>(stationaryProbClasses);
//			ArrayList<Double> stationaryClusterThresholdBul=new ArrayList<Double>(stationaryProbClusters);
						
			
			Collections.sort(walkingClassThresholdBul);
			Collections.sort(walkingClusterThresholdBul);
			double walking_class_threshold=walkingClassThresholdBul.get(walkingClassThresholdBul.size()/2);
			double walking_cluster_threshold=walkingClusterThresholdBul.get(walkingClusterThresholdBul.size()/2);
			
			Collections.sort(minibusClassThresholdBul);
			Collections.sort(minibusClusterThresholdBul);
			double minibus_class_threshold=minibusClassThresholdBul.get(minibusClassThresholdBul.size()/2);
			double minibus_cluster_threshold=minibusClusterThresholdBul.get(minibusClusterThresholdBul.size()/2);

			Collections.sort(carClassThresholdBul);
			Collections.sort(carClusterThresholdBul);
			double car_class_threshold=carClassThresholdBul.get(carClassThresholdBul.size()/2);
			double car_cluster_threshold=carClusterThresholdBul.get(carClusterThresholdBul.size()/2);

			
			Collections.sort(busClassThresholdBul);
			Collections.sort(busClusterThresholdBul);
			double bus_class_threshold=busClassThresholdBul.get(busClassThresholdBul.size()/2);
			double bus_cluster_threshold=busClusterThresholdBul.get(busClusterThresholdBul.size()/2);

			Collections.sort(trainClassThresholdBul);
			Collections.sort(trainClusterThresholdBul);
			double train_class_threshold=trainClassThresholdBul.get(trainClassThresholdBul.size()/2);
			double train_cluster_threshold=trainClusterThresholdBul.get(trainClusterThresholdBul.size()/2);

			Collections.sort(metroKClassThresholdBul);
			Collections.sort(metroKClusterThresholdBul);
			double metroK_class_threshold=metroKClassThresholdBul.get(metroKClassThresholdBul.size()/2);
			double metroK_cluster_threshold=metroKClusterThresholdBul.get(metroKClusterThresholdBul.size()/2);

			Collections.sort(metroHClassThresholdBul);
			Collections.sort(metroHClusterThresholdBul);
			double metroH_class_threshold=metroHClassThresholdBul.get(metroHClassThresholdBul.size()/2);
			double metroH_cluster_threshold=metroHClusterThresholdBul.get(metroHClusterThresholdBul.size()/2);

			
			Collections.sort(marmarayClassThresholdBul);
			Collections.sort(marmarayClusterThresholdBul);
			double marmaray_class_threshold=marmarayClassThresholdBul.get(marmarayClassThresholdBul.size()/2);
			double marmaray_cluster_threshold=marmarayClusterThresholdBul.get(marmarayClusterThresholdBul.size()/2);

			Collections.sort(metrobusClassThresholdBul);
			Collections.sort(metrobusClusterThresholdBul);
			double metrobus_class_threshold=metrobusClassThresholdBul.get(metrobusClassThresholdBul.size()/2);
			double metrobus_cluster_threshold=metrobusClusterThresholdBul.get(metrobusClusterThresholdBul.size()/2);
			
			Collections.sort(ferryClassThresholdBul);
			Collections.sort(ferryClusterThresholdBul);
			double ferry_class_threshold=ferryClassThresholdBul.get(ferryClassThresholdBul.size()/2);
			double ferry_cluster_threshold=ferryClusterThresholdBul.get(ferryClusterThresholdBul.size()/2);
			
//			Collections.sort(stationaryClassThresholdBul);
//			Collections.sort(stationaryClusterThresholdBul);
//			double stationary_class_threshold=stationaryClassThresholdBul.get(stationaryClassThresholdBul.size()/2);
//			double stationary_cluster_threshold=stationaryClusterThresholdBul.get(stationaryClusterThresholdBul.size()/2);
//			System.out.println("walking_class_Threshold -> "+walking_class_threshold);
//			System.out.println("walking_cluster_Threshold -> "+walking_cluster_threshold);
//			
//			System.out.println("minibus_class_Threshold -> "+minibus_class_threshold);
//			System.out.println("minibus_cluster_Threshold -> "+minibus_cluster_threshold);
//			
//			System.out.println("car_class_Threshold -> "+car_class_threshold);
//			System.out.println("car_cluster_Threshold -> "+car_cluster_threshold);
//			
//			System.out.println("bus_class_Threshold -> "+bus_class_threshold);
//			System.out.println("bus_cluster_Threshold -> "+bus_cluster_threshold);
//			
//			System.out.println("train_class_Threshold -> "+train_class_threshold);
//			System.out.println("train_cluster_Threshold -> "+train_cluster_threshold);
//			
//			System.out.println("metroK_class_Threshold -> "+metroK_class_threshold);
//			System.out.println("metroK_cluster_Threshold -> "+metroK_cluster_threshold);
//			
//			System.out.println("metroH_class_Threshold -> "+metroH_class_threshold);
//			System.out.println("metroH_cluster_Threshold -> "+metroH_cluster_threshold);
//			
//			System.out.println("marmaray_class_Threshold -> "+marmaray_class_threshold);
//			System.out.println("marmaray_cluster_Threshold -> "+marmaray_cluster_threshold);
//			
//			System.out.println("metrobus_class_Threshold -> "+metrobus_class_threshold);
//			System.out.println("metrobus_cluster_Threshold -> "+metrobus_cluster_threshold);
//			
//			System.out.println("ferry_class_Threshold -> "+ferry_class_threshold);
//			System.out.println("ferry_cluster_Threshold -> "+ferry_cluster_threshold);
//			
//			System.out.println("stationary_class_Threshold -> "+stationary_class_threshold);
//			System.out.println("stationary_cluster_Threshold -> "+stationary_cluster_threshold);
			for(int i=walking_artis*k;i<k*walking_artis+walking_artis;i++) {
			
				if(walkingProbClasses.get(i)<walking_class_threshold && walkingProbClusters.get(i)>walking_cluster_threshold) {
					walking_train.add(walking_algoritmam.get(i));
					training_data.add(walking_algoritmam.get(i));
				}else if(walkingProbClasses.get(i)>walking_class_threshold && walkingProbClusters.get(i)>walking_cluster_threshold) {
					walking_train.add(walking_algoritmam.get(i));
					training_data.add(walking_algoritmam.get(i));
				}
				secmedenTrain.add(walking_algoritmam.get(i));
			}
			
			for(int i=minibus_artis*k;i<k*minibus_artis+minibus_artis;i++) {
				if(minibusProbClasses.get(i)<minibus_class_threshold && minibusProbClusters.get(i)>minibus_cluster_threshold) {
					minibus_train.add(minibus_algoritmam.get(i));
					training_data.add(minibus_algoritmam.get(i));
				}else if(minibusProbClasses.get(i)>minibus_class_threshold && minibusProbClusters.get(i)>minibus_cluster_threshold) {
					minibus_train.add(minibus_algoritmam.get(i));
					training_data.add(minibus_algoritmam.get(i));
				}
				secmedenTrain.add(minibus_algoritmam.get(i));

			}
			
			for(int i=car_artis*k;i<k*car_artis+car_artis;i++) {
				if(carProbClasses.get(i)<car_class_threshold && carProbClusters.get(i)>car_cluster_threshold) {
					car_train.add(car_algoritmam.get(i));
					training_data.add(car_algoritmam.get(i));
				}else if(carProbClasses.get(i)>car_class_threshold && carProbClusters.get(i)>car_cluster_threshold) {
					car_train.add(car_algoritmam.get(i));
					training_data.add(car_algoritmam.get(i));
				}
				secmedenTrain.add(car_algoritmam.get(i));

			}
			
			for(int i=bus_artis*k;i<k*bus_artis+bus_artis;i++) {
				if(busProbClasses.get(i)<bus_class_threshold && busProbClusters.get(i)>bus_cluster_threshold) {
					bus_train.add(bus_algoritmam.get(i));
					training_data.add(bus_algoritmam.get(i));
				}else if(busProbClasses.get(i)>bus_class_threshold && busProbClusters.get(i)>bus_cluster_threshold) {
					bus_train.add(bus_algoritmam.get(i));
					training_data.add(bus_algoritmam.get(i));
				}
				secmedenTrain.add(bus_algoritmam.get(i));

			}
			
			for(int i=tram_artis*k;i<k*tram_artis+tram_artis;i++) {
				if(tramProbClasses.get(i)<train_class_threshold && tramProbClusters.get(i)>train_cluster_threshold) {
					tram_train.add(tram_algoritmam.get(i));
					training_data.add(tram_algoritmam.get(i));
				}else if(tramProbClasses.get(i)>train_class_threshold && tramProbClusters.get(i)>train_cluster_threshold) {
					tram_train.add(tram_algoritmam.get(i));
					training_data.add(tram_algoritmam.get(i));
				}
				secmedenTrain.add(tram_algoritmam.get(i));

			}
			
			for(int i=metroK_artis*k;i<k*metroK_artis+metroK_artis;i++) {
				if(metroKProbClasses.get(i)<metroK_class_threshold && metroKProbClusters.get(i)>metroK_cluster_threshold) {
					metroK_train.add(metroK_algoritmam.get(i));
					training_data.add(metroK_algoritmam.get(i));
				}else if(metroKProbClasses.get(i)>metroK_class_threshold && metroKProbClusters.get(i)>metroK_cluster_threshold) {
					metroK_train.add(metroK_algoritmam.get(i));
					training_data.add(metroK_algoritmam.get(i));
				}
				secmedenTrain.add(metroK_algoritmam.get(i));

			}
			
			for(int i=metroH_artis*k;i<k*metroH_artis+metroH_artis;i++) {
				if(metroHProbClasses.get(i)<metroH_class_threshold && metroHProbClusters.get(i)>metroH_cluster_threshold) {
					metroH_train.add(metroH_algoritmam.get(i));
					training_data.add(metroH_algoritmam.get(i));
				}else if(metroHProbClasses.get(i)>metroH_class_threshold && metroHProbClusters.get(i)>metroH_cluster_threshold) {
					metroH_train.add(metroH_algoritmam.get(i));
					training_data.add(metroH_algoritmam.get(i));
				}
				secmedenTrain.add(metroH_algoritmam.get(i));

			}	
			
			for(int i=marmaray_artis*k;i<k*marmaray_artis+marmaray_artis;i++) {
				if(marmarayProbClasses.get(i)<marmaray_class_threshold && marmarayProbClusters.get(i)>marmaray_cluster_threshold) {
					marmaray_train.add(marmaray_algoritmam.get(i));
					training_data.add(marmaray_algoritmam.get(i));
				}else if(marmarayProbClasses.get(i)>marmaray_class_threshold && marmarayProbClusters.get(i)>marmaray_cluster_threshold) {
					marmaray_train.add(marmaray_algoritmam.get(i));
					training_data.add(marmaray_algoritmam.get(i));
				}
				secmedenTrain.add(marmaray_algoritmam.get(i));

			}
			
			for(int i=metrobus_artis*k;i<k*metrobus_artis+metrobus_artis;i++) {
				if(metrobusProbClasses.get(i)<metrobus_class_threshold && metrobusProbClusters.get(i)>metrobus_cluster_threshold) {
					metrobus_train.add(metrobus_algoritmam.get(i));
					training_data.add(metrobus_algoritmam.get(i));
				}else if(metrobusProbClasses.get(i)>metrobus_class_threshold && metrobusProbClusters.get(i)>metrobus_cluster_threshold) {
					metrobus_train.add(metrobus_algoritmam.get(i));
					training_data.add(metrobus_algoritmam.get(i));
				}
				secmedenTrain.add(metrobus_algoritmam.get(i));

			}
			
			for(int i=ferry_artis*k;i<k*ferry_artis+ferry_artis;i++) {
				if(ferryProbClasses.get(i)<ferry_class_threshold && ferryProbClusters.get(i)>ferry_cluster_threshold) {
					ferry_train.add(ferry_algoritmam.get(i));
					training_data.add(ferry_algoritmam.get(i));
				}else if(ferryProbClasses.get(i)>ferry_class_threshold && ferryProbClusters.get(i)>ferry_cluster_threshold) {
					ferry_train.add(ferry_algoritmam.get(i));
					training_data.add(ferry_algoritmam.get(i));
				}
				secmedenTrain.add(ferry_algoritmam.get(i));

			}
			
//			for(int i=stationary_artis*k;i<k*stationary_artis+stationary_artis;i++) {
//				if(stationaryProbClasses.get(i)<stationary_class_threshold && stationaryProbClusters.get(i)>stationary_cluster_threshold) {
//					stationary_train.add(stationary_algoritmam.get(i));
//					training_data.add(stationary_algoritmam.get(i));
//				}else if(stationaryProbClasses.get(i)>stationary_class_threshold && stationaryProbClusters.get(i)>stationary_cluster_threshold) {
//					stationary_train.add(stationary_algoritmam.get(i));
//					training_data.add(stationary_algoritmam.get(i));
//				}
//				secmedenTrain.add(stationary_algoritmam.get(i));
//			}
			walking_train_filtered=deleteClassAtt(walking_train);
			minibus_train_filtered=deleteClassAtt(minibus_train);
			car_train_filtered=deleteClassAtt(car_train);
			bus_train_filtered=deleteClassAtt(bus_train);
			tram_train_filtered=deleteClassAtt(tram_train);
			metroK_train_filtered=deleteClassAtt(metroK_train);
			metroH_train_filtered=deleteClassAtt(metroH_train);
			marmaray_train_filtered=deleteClassAtt(marmaray_train);
			metrobus_train_filtered=deleteClassAtt(metrobus_train);
			ferry_train_filtered=deleteClassAtt(ferry_train);
			stationary_train_filtered=deleteClassAtt(stationary_train);
			RandomForest rf=new RandomForest();
			rf.buildClassifier(training_data);
		
			Evaluation eval = new Evaluation(training_data);
			long timer2=System.currentTimeMillis();
			eval.evaluateModel(rf, test_data);
			long timer3=System.currentTimeMillis();
			timerlarim1.add((timer3-timer2));
			System.out.println("Cost -> "+eval.avgCost());

			System.out.println("k = "+ k +" icin sonuclar SEÇEREK-> ");
			System.out.println("Training Data - Num Instances -> "+training_data.numInstances());
			System.out.println(eval.toSummaryString());
			System.out.println("rf.bagSizePercentTipText()"+rf.bagSizePercentTipText());
			System.out.println("rf.batchSizeTipText()"+rf.batchSizeTipText());
			System.out.println("(rf.getBatchSize())"+rf.getBatchSize());
			System.out.println("rf.getMaxDepth());"+rf.getMaxDepth());
			basarilar.add(eval.pctCorrect());
			System.out.println("k = "+ k +" icin sonuclar SEÇMEDEN-> ");
			System.out.println("Training Data - Num Instances -> "+secmedenTrain.numInstances());
			RandomForest rf2=new RandomForest();
			rf2.buildClassifier(secmedenTrain);
			Evaluation eval2 = new Evaluation(secmedenTrain);
			long timerx=System.currentTimeMillis();
			eval2.evaluateModel(rf2, test_data);
			System.out.println("Cost -> "+eval2.avgCost());
			long timery=System.currentTimeMillis();

			System.out.println(eval2.toSummaryString());
			timerlarim2.add((timery-timerx));
			basarilar2.add(eval2.pctCorrect());
			long iterasyonzamani2=System.currentTimeMillis();
			long sonuciterasyon=iterasyonzamani2-iterasyonzamani1;
			sureler.add(sonuciterasyon/1000);
			toplamsure+=sonuciterasyon/1000;
		}
		for(int i=0;i<basarilar.size();i++) {
			System.out.println("Seçerek i="+i+" icin basari -> "+basarilar.get(i));
			System.out.println("Seçmeden i="+i+" icin basari -> "+basarilar2.get(i));
		}
		
		for(int i=0;i<basarilar.size();i++) {
			System.out.println(basarilar.get(i));
		}
		System.out.println("--------");
		for(int i=0;i<basarilar2.size();i++) {
			System.out.println(basarilar2.get(i));
		}
		System.out.println("Süreler -> ");
		for(int im=0;im<sureler.size();im++) {
			System.out.println(sureler.get(im));
		}
		System.out.println("Toplam Sure -> "+toplamsure);
		
		System.out.println("Seçerek");
		for(int tt=0;tt<timerlarim1.size();tt++) {
			System.out.println(timerlarim1.get(tt));
		}
		System.out.println("Seçmeden");
		for(int tt=0;tt<timerlarim2.size();tt++) {
			System.out.println(timerlarim2.get(tt));
		}	
		
		System.out.println("Yuzde ");
		for(int tt=0;tt<timerlarim2.size();tt++) {
			System.out.println((timerlarim1.get(tt)-timerlarim2.get(tt))/timerlarim2.get(tt)*100);
		}
		
	}

	public static Instances loadArff(Instances data, String arffInput) {
		DataSource source = null;
		try {
			source = new DataSource(arffInput);
			data = source.getDataSet();
			if (data.classIndex() == -1) {
				data.setClassIndex(data.numAttributes() - 1);
			}
		} catch (Exception e1) {

		}
		return data;
	}
	public static Instances splitData(Instances data, double percentage, boolean invertSelection) throws Exception {
		RemovePercentage rp = new RemovePercentage();
		rp.setInvertSelection(invertSelection);
		rp.setPercentage(percentage);
		rp.setInputFormat(data);
		data = Filter.useFilter(data, rp);
		return data;
	}
	
	public static Instances addOn(Instances data, Instances dataToAdd) {
		for (int i = 0; i < dataToAdd.size(); i++) {
			data.add(dataToAdd.get(i));
		}
		return data;
	}
	
	public static Instances deleteClassAtt(Instances data) throws Exception {
		Remove filter = new Remove();
		filter.setAttributeIndices("" + (data.classIndex() + 1));
		filter.setInputFormat(data);
		Instances filtered_data = Filter.useFilter(data, filter);
		return filtered_data;
	}
	
	public static double oklidUzaklikBul(Instance first, Instance second) {
		double uzaklik = 0;
		for (int i = 0; i < first.numAttributes(); i++) {
			uzaklik += Math.pow(first.value(first.attribute(i)) - second.value(second.attribute(i)), 2);
		}
		uzaklik = Math.pow(uzaklik, 0.5);
		return uzaklik;
	}
}
*/
