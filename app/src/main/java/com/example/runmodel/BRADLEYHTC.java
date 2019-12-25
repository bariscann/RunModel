/*
package com.example.runmodel;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.FileWriter;
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

public class BRADLEYHTC {
	private String EXTERNAL_STORAGE_DIRECTORY = Environment.getExternalStorageDirectory().toString() + "/";
	private String APPLICATION_FOLDER = EXTERNAL_STORAGE_DIRECTORY + "TransportModeDetection/";
	private String REQUIRED_FOLDER = APPLICATION_FOLDER + "Required/";

	private static String TAG = "MainActivity";
	private String TRAIN_DATASET = REQUIRED_FOLDER + "all.arff";
	private String TEST_DATASET = REQUIRED_FOLDER + "test.arff";

	public static final int iterasyonSayisi = 5;
	public static final String arffDosyaIsmi="dosya5likhtcrandom";

	public BRADLEYHTC() throws Exception {
		long toplamsure = 0;
		int dataIndex = 0;
		ArrayList<Long> timerlarim1 = new ArrayList<Long>();
		ArrayList<Long> timerlarim2 = new ArrayList<Long>();
		ArrayList<Integer> secerekInstanceSayilari = new ArrayList<Integer>();
		ArrayList<Integer> secmedenInstanceSayilari = new ArrayList<Integer>();

		Instances general_data = null;
		general_data = loadArff(general_data, "htc.arff");
		Instances blank_data = new Instances(general_data);
		RemoveWithValues rwv = new RemoveWithValues();
		rwv.setInputFormat(general_data);
		try {
			blank_data = Filter.useFilter(blank_data, rwv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Instances mrtData = new Instances(blank_data);
		Instances walkData = new Instances(blank_data);
		Instances carData = new Instances(blank_data);
		Instances motorData = new Instances(blank_data);
		Instances trainData = new Instances(blank_data);
		Instances bicycleData = new Instances(blank_data);
		Instances hsrData = new Instances(blank_data);
		Instances runData = new Instances(blank_data);
		Instances stillData = new Instances(blank_data);
		Instances secmedenTrain = new Instances(blank_data);
		while (dataIndex < general_data.numInstances()){
			Instance instance = general_data.instance(dataIndex);
			double classIndex = instance.classValue();
			if (classIndex == 1) {
				mrtData.add(instance);
			} else if (classIndex == 0) {
				walkData.add(instance);
			} else if (classIndex == 2) {
				carData.add(instance);
			} else if (classIndex == 3) {
				motorData.add(instance);
			} else if (classIndex == 4) {
				trainData.add(instance);
			} else if (classIndex == 5) {
				bicycleData.add(instance);
			} else if (classIndex == 6) {
				hsrData.add(instance);
			} else if (classIndex == 7) {
				runData.add(instance);
			} else if (classIndex == 8) {
				stillData.add(instance);
			}
			dataIndex++;
		}
for (Instance instance : general_data) {
			double classIndex = instance.classValue();
			if (classIndex == 1) {
				mrtData.add(instance);
			} else if (classIndex == 0) {
				walkData.add(instance);
			} else if (classIndex == 2) {
				carData.add(instance);
			} else if (classIndex == 3) {
				motorData.add(instance);
			} else if (classIndex == 4) {
				trainData.add(instance);
			} else if (classIndex == 5) {
				bicycleData.add(instance);
			} else if (classIndex == 6) {
				hsrData.add(instance);
			} else if (classIndex == 7) {
				runData.add(instance);
			} else if (classIndex == 8) {
				stillData.add(instance);
			}
		}

		Instances mrt_gecici = splitData(mrtData, 70, false);
		Instances mrt_train = splitData(mrt_gecici, 33.33, true);
		Instances mrt_test = splitData(mrt_gecici, 33.33, false);
		Instances mrt_algoritmam = splitData(mrtData, 70, true);
		// System.out.println("mrt_gecici numInstances -> "+mrt_gecici.numInstances());
		//System.out.println("mrt_train numInstances -> " + mrt_train.numInstances());
		//System.out.println("mrt_test numInstances -> " + mrt_test.numInstances());
		//System.out.println("mrt_algoritmam numInstances -> " + mrt_algoritmam.numInstances());

		Instances walk_gecici = splitData(walkData, 70, false);
		Instances walk_train = splitData(walk_gecici, 33.33, true);
		Instances walk_test = splitData(walk_gecici, 33.33, false);
		Instances walk_algoritmam = splitData(walkData, 70, true);
		// System.out.println("walk_gecici numInstances -> "+walk_gecici.numInstances());
		//System.out.println("walk_train numInstances -> " + walk_train.numInstances());
		//System.out.println("walk_test numInstances -> " + walk_test.numInstances());
		//System.out.println("walk_algoritmam numInstances -> " + walk_algoritmam.numInstances());

		Instances car_gecici = splitData(carData, 70, false);
		Instances car_train = splitData(car_gecici, 33.33, true);
		Instances car_test = splitData(car_gecici, 33.33, false);
		Instances car_algoritmam = splitData(carData, 70, true);
		// System.out.println("car_gecici numInstances -> "+car_gecici.numInstances());
		//System.out.println("car_train numInstances -> " + car_train.numInstances());
		//System.out.println("car_test numInstances -> " + car_test.numInstances());
		//System.out.println("car_algoritmam numInstances -> " + car_algoritmam.numInstances());

		Instances motor_gecici = splitData(motorData, 70, false);
		Instances motor_train = splitData(motor_gecici, 33.33, true);
		Instances motor_test = splitData(motor_gecici, 33.33, false);
		Instances motor_algoritmam = splitData(motorData, 70, true);
		// System.out.println("motor_gecici numInstances -> "+motor_gecici.numInstances());
		//System.out.println("motor_train numInstances -> " + motor_train.numInstances());
		//System.out.println("motor_test numInstances -> " + motor_test.numInstances());
		//System.out.println("motor_algoritmam numInstances -> " + motor_algoritmam.numInstances());

		Instances train_gecici = splitData(trainData, 70, false);
		Instances train_train = splitData(train_gecici, 33.33, true);
		Instances train_test = splitData(train_gecici, 33.33, false);
		Instances train_algoritmam = splitData(trainData, 70, true);
		// System.out.println("train_gecici numInstances -> "+train_gecici.numInstances());
		//System.out.println("train_train numInstances -> " + train_train.numInstances());
		//System.out.println("train_test numInstances -> " + train_test.numInstances());
		//System.out.println("train_algoritmam numInstances -> " + train_algoritmam.numInstances());

		Instances bicycle_gecici = splitData(bicycleData, 70, false);
		Instances bicycle_train = splitData(bicycle_gecici, 33.33, true);
		Instances bicycle_test = splitData(bicycle_gecici, 33.33, false);
		Instances bicycle_algoritmam = splitData(bicycleData, 70, true);
		// System.out.println("bicycle_gecici numInstances -> "+bicycle_gecici.numInstances());
		//System.out.println("bicycle_train numInstances -> " + bicycle_train.numInstances());
		//System.out.println("bicycle_test numInstances -> " + bicycle_test.numInstances());
		//System.out.println("bicycle_algoritmam numInstances -> " + bicycle_algoritmam.numInstances());

		Instances hsr_gecici = splitData(hsrData, 70, false);
		Instances hsr_train = splitData(hsr_gecici, 33.33, true);
		Instances hsr_test = splitData(hsr_gecici, 33.33, false);
		Instances hsr_algoritmam = splitData(hsrData, 70, true);
		// System.out.println("hsr_gecici numInstances -> "+hsr_gecici.numInstances());
		//System.out.println("hsr_train numInstances -> " + hsr_train.numInstances());
		//System.out.println("hsr_test numInstances -> " + hsr_test.numInstances());
		//System.out.println("hsr_algoritmam numInstances -> " + hsr_algoritmam.numInstances());

		Instances run_gecici = splitData(runData, 70, false);
		Instances run_train = splitData(run_gecici, 33.33, true);
		Instances run_test = splitData(run_gecici, 33.33, false);
		Instances run_algoritmam = splitData(runData, 70, true);
		// System.out.println("run_gecici numInstances -> "+run_gecici.numInstances());
		//System.out.println("run_train numInstances -> " + run_train.numInstances());
		//System.out.println("run_test numInstances -> " + run_test.numInstances());
		//System.out.println("run_algoritmam numInstances -> " + run_algoritmam.numInstances());

		Instances still_gecici = splitData(stillData, 70, false);
		Instances still_train = splitData(still_gecici, 33.33, true);
		Instances still_test = splitData(still_gecici, 33.33, false);
		Instances still_algoritmam = splitData(stillData, 70, true);
		// System.out.println("still_gecici numInstances -> "+still_gecici.numInstances());
		//System.out.println("still_train numInstances -> " + still_train.numInstances());
		//System.out.println("still_test numInstances -> " + still_test.numInstances());
		//System.out.println("still_algoritmam numInstances -> " + still_algoritmam.numInstances());


		int mrt_artis = mrt_algoritmam.numInstances() / iterasyonSayisi;
		int walk_artis = walk_algoritmam.numInstances() / iterasyonSayisi;
		int car_artis = car_algoritmam.numInstances() / iterasyonSayisi;
		int motor_artis = motor_algoritmam.numInstances() / iterasyonSayisi;
		int train_artis = train_algoritmam.numInstances() / iterasyonSayisi;
		int hsr_artis = hsr_algoritmam.numInstances() / iterasyonSayisi;
		int run_artis = run_algoritmam.numInstances() / iterasyonSayisi;
		int still_artis = still_algoritmam.numInstances() / iterasyonSayisi;
		int bicycle_artis = still_algoritmam.numInstances() / iterasyonSayisi;

		Instances training_data = new Instances(blank_data);
		training_data = addOn(training_data, mrt_train);
		training_data = addOn(training_data, walk_train);
		training_data = addOn(training_data, car_train);
		training_data = addOn(training_data, motor_train);
		training_data = addOn(training_data, train_train);
		training_data = addOn(training_data, hsr_train);
		training_data = addOn(training_data, run_train);
		training_data = addOn(training_data, still_train);
		secmedenTrain = addOn(secmedenTrain, mrt_train);
		secmedenTrain = addOn(secmedenTrain, walk_train);
		secmedenTrain = addOn(secmedenTrain, car_train);
		secmedenTrain = addOn(secmedenTrain, motor_train);
		secmedenTrain = addOn(secmedenTrain, train_train);
		secmedenTrain = addOn(secmedenTrain, hsr_train);
		secmedenTrain = addOn(secmedenTrain, run_train);
		secmedenTrain = addOn(secmedenTrain, still_train);

		ArrayList<Double> basarilar = new ArrayList<Double>();
		ArrayList<Double> basarilar2 = new ArrayList<Double>();
		Instances test_data = new Instances(blank_data);
		test_data = addOn(test_data, mrt_test);
		test_data = addOn(test_data, walk_test);
		test_data = addOn(test_data, car_test);
		test_data = addOn(test_data, motor_test);
		test_data = addOn(test_data, train_test);
		test_data = addOn(test_data, hsr_test);
		test_data = addOn(test_data, run_test);
		test_data = addOn(test_data, still_test);
		BufferedWriter test = new BufferedWriter(new FileWriter("./"+arffDosyaIsmi+"/test.arff"));
		test.write(test_data.toString());
		test.close();
		Instances walk_train_filtered = deleteClassAtt(walk_train);
		Instances mrt_train_filtered = deleteClassAtt(mrt_train);
		Instances car_train_filtered = deleteClassAtt(car_train);
		Instances motor_train_filtered = deleteClassAtt(motor_train);
		Instances train_train_filtered = deleteClassAtt(train_train);
		Instances bicycle_train_filtered = deleteClassAtt(bicycle_train);
		Instances hsr_train_filtered = deleteClassAtt(hsr_train);
		Instances run_train_filtered = deleteClassAtt(run_train);
		Instances still_train_filtered = deleteClassAtt(still_train);

		Instances walk_algoritmam_filtered = deleteClassAtt(walk_algoritmam);
		Instances mrt_algoritmam_filtered = deleteClassAtt(mrt_algoritmam);
		Instances car_algoritmam_filtered = deleteClassAtt(car_algoritmam);
		Instances motor_algoritmam_filtered = deleteClassAtt(motor_algoritmam);
		Instances train_algoritmam_filtered = deleteClassAtt(train_algoritmam);
		Instances bicycle_algoritmam_filtered = deleteClassAtt(bicycle_algoritmam);
		Instances hsr_algoritmam_filtered = deleteClassAtt(hsr_algoritmam);
		Instances run_algoritmam_filtered = deleteClassAtt(run_algoritmam);
		Instances still_algoritmam_filtered = deleteClassAtt(still_algoritmam);
		ArrayList<Double> walkProbClasses = new ArrayList<Double>();
		ArrayList<Double> walkProbClusters = new ArrayList<Double>();
		ArrayList<Double> mrtProbClasses = new ArrayList<Double>();
		ArrayList<Double> mrtProbClusters = new ArrayList<Double>();
		ArrayList<Double> carProbClasses = new ArrayList<Double>();
		ArrayList<Double> carProbClusters = new ArrayList<Double>();
		ArrayList<Double> motorProbClasses = new ArrayList<Double>();
		ArrayList<Double> motorProbClusters = new ArrayList<Double>();
		ArrayList<Double> trainProbClasses = new ArrayList<Double>();
		ArrayList<Double> trainProbClusters = new ArrayList<Double>();
		ArrayList<Double> bicycleProbClasses = new ArrayList<Double>();
		ArrayList<Double> bicycleProbClusters = new ArrayList<Double>();
		ArrayList<Double> hsrProbClasses = new ArrayList<Double>();
		ArrayList<Double> hsrProbClusters = new ArrayList<Double>();
		ArrayList<Double> runProbClasses = new ArrayList<Double>();
		ArrayList<Double> runProbClusters = new ArrayList<Double>();
		ArrayList<Double> stillProbClasses = new ArrayList<Double>();
		ArrayList<Double> stillProbClusters = new ArrayList<Double>();
		for (int k = 0; k < iterasyonSayisi; k++) {
			long iterasyonzamani1 = System.currentTimeMillis();
			XMeans walkIcin = new XMeans();
			walkIcin.buildClusterer(walk_train_filtered);
			XMeans mrtIcin = new XMeans();
			mrtIcin.buildClusterer(mrt_train_filtered);
			XMeans carIcin = new XMeans();
			carIcin.buildClusterer(car_train_filtered);
			XMeans motorIcin = new XMeans();
			motorIcin.buildClusterer(motor_train_filtered);
			XMeans trainIcin = new XMeans();
			trainIcin.buildClusterer(train_train_filtered);
			XMeans bicycleIcin = new XMeans();
			bicycleIcin.buildClusterer(bicycle_train_filtered);
			XMeans hsrIcin = new XMeans();
			hsrIcin.buildClusterer(hsr_train_filtered);
			XMeans runIcin = new XMeans();
			runIcin.buildClusterer(run_train_filtered);
			XMeans stillIcin = new XMeans();
			stillIcin.buildClusterer(still_train_filtered);

			Instances walkCentersOfClusters = walkIcin.getClusterCenters();
			Instances mrtCentersOfClusters = mrtIcin.getClusterCenters();
			Instances carCentersOfClusters = carIcin.getClusterCenters();
			Instances motorCentersOfClusters = motorIcin.getClusterCenters();
			Instances trainCentersOfClusters = trainIcin.getClusterCenters();
			Instances bicycleCentersOfClusters = bicycleIcin.getClusterCenters();
			Instances hsrCentersOfClusters = hsrIcin.getClusterCenters();
			Instances runCentersOfClusters = runIcin.getClusterCenters();
			Instances stillCentersOfClusters = stillIcin.getClusterCenters();

			for (int i = walk_artis * k; i < k * walk_artis + walk_artis; i++) {
				double uzaklikMin = oklidUzaklikBul(walk_algoritmam_filtered.instance(i), walkCentersOfClusters.instance(0));
				double uzaklik = uzaklikMin;
				for (int j = 1; j < walkIcin.numberOfClusters(); j++) {
					if (oklidUzaklikBul(walk_algoritmam_filtered.instance(i), walkCentersOfClusters.instance(j)) < uzaklikMin) {
						uzaklikMin = oklidUzaklikBul(walk_algoritmam_filtered.instance(i), walkCentersOfClusters.instance(j));
					}
					uzaklik += oklidUzaklikBul(walk_algoritmam_filtered.instance(i), walkCentersOfClusters.instance(j));
				}
				double probCluster = uzaklikMin;
				double probClass = uzaklik;
				walkProbClasses.add(probClass);
				walkProbClusters.add(probCluster);

			}

			for (int i = mrt_artis * k; i < k * mrt_artis + mrt_artis; i++) {
				double uzaklikMin = oklidUzaklikBul(mrt_algoritmam_filtered.instance(i), mrtCentersOfClusters.instance(0));
				double uzaklik = uzaklikMin;
				for (int j = 1; j < mrtIcin.numberOfClusters(); j++) {
					if (oklidUzaklikBul(mrt_algoritmam_filtered.instance(i), mrtCentersOfClusters.instance(j)) < uzaklikMin) {
						uzaklikMin = oklidUzaklikBul(mrt_algoritmam_filtered.instance(i), mrtCentersOfClusters.instance(j));
					}
					uzaklik += oklidUzaklikBul(mrt_algoritmam_filtered.instance(i), mrtCentersOfClusters.instance(j));
				}
				double probCluster = uzaklikMin;
				double probClass = uzaklik;
				mrtProbClasses.add(probClass);
				mrtProbClusters.add(probCluster);
			}

			for (int i = car_artis * k; i < k * car_artis + car_artis; i++) {
				double uzaklikMin = oklidUzaklikBul(car_algoritmam_filtered.instance(i), carCentersOfClusters.instance(0));
				double uzaklik = uzaklikMin;
				for (int j = 1; j < carIcin.numberOfClusters(); j++) {
					if (oklidUzaklikBul(car_algoritmam_filtered.instance(i), carCentersOfClusters.instance(j)) < uzaklikMin) {
						uzaklikMin = oklidUzaklikBul(car_algoritmam_filtered.instance(i), carCentersOfClusters.instance(j));
					}
					uzaklik += oklidUzaklikBul(car_algoritmam_filtered.instance(i), carCentersOfClusters.instance(j));
				}
				double probCluster = uzaklikMin;
				double probClass = uzaklik;
				carProbClasses.add(probClass);
				carProbClusters.add(probCluster);
			}

			for (int i = motor_artis * k; i < k * motor_artis + motor_artis; i++) {
				double uzaklikMin = oklidUzaklikBul(motor_algoritmam_filtered.instance(i), motorCentersOfClusters.instance(0));
				double uzaklik = uzaklikMin;
				for (int j = 1; j < motorIcin.numberOfClusters(); j++) {
					if (oklidUzaklikBul(motor_algoritmam_filtered.instance(i), motorCentersOfClusters.instance(j)) < uzaklikMin) {
						uzaklikMin = oklidUzaklikBul(motor_algoritmam_filtered.instance(i), motorCentersOfClusters.instance(j));
					}
					uzaklik += oklidUzaklikBul(motor_algoritmam_filtered.instance(i), motorCentersOfClusters.instance(j));
				}
				double probCluster = uzaklikMin;
				double probClass = uzaklik;
				motorProbClasses.add(probClass);
				motorProbClusters.add(probCluster);
			}

			for (int i = train_artis * k; i < k * train_artis + train_artis; i++) {
				double uzaklikMin = oklidUzaklikBul(train_algoritmam_filtered.instance(i), trainCentersOfClusters.instance(0));
				double uzaklik = uzaklikMin;
				for (int j = 1; j < trainIcin.numberOfClusters(); j++) {
					if (oklidUzaklikBul(train_algoritmam_filtered.instance(i), trainCentersOfClusters.instance(j)) < uzaklikMin) {
						uzaklikMin = oklidUzaklikBul(train_algoritmam_filtered.instance(i), trainCentersOfClusters.instance(j));
					}
					uzaklik += oklidUzaklikBul(train_algoritmam_filtered.instance(i), trainCentersOfClusters.instance(j));
				}
				double probCluster = uzaklikMin;
				double probClass = uzaklik;
				trainProbClasses.add(probClass);
				trainProbClusters.add(probCluster);
			}

			for (int i = bicycle_artis * k; i < k * bicycle_artis + bicycle_artis; i++) {
				double uzaklikMin = oklidUzaklikBul(bicycle_algoritmam_filtered.instance(i),
						bicycleCentersOfClusters.instance(0));
				double uzaklik = uzaklikMin;
				for (int j = 1; j < bicycleIcin.numberOfClusters(); j++) {
					if (oklidUzaklikBul(bicycle_algoritmam_filtered.instance(i),
							bicycleCentersOfClusters.instance(j)) < uzaklikMin) {
						uzaklikMin = oklidUzaklikBul(bicycle_algoritmam_filtered.instance(i),
								bicycleCentersOfClusters.instance(j));
					}
					uzaklik += oklidUzaklikBul(bicycle_algoritmam_filtered.instance(i), bicycleCentersOfClusters.instance(j));
				}
				double probCluster = uzaklikMin;
				double probClass = uzaklik;
				bicycleProbClasses.add(probClass);
				bicycleProbClusters.add(probCluster);
			}

			for (int i = hsr_artis * k; i < k * hsr_artis + hsr_artis; i++) {
				double uzaklikMin = oklidUzaklikBul(hsr_algoritmam_filtered.instance(i), hsrCentersOfClusters.instance(0));
				double uzaklik = uzaklikMin;
				for (int j = 1; j < hsrIcin.numberOfClusters(); j++) {
					if (oklidUzaklikBul(hsr_algoritmam_filtered.instance(i), hsrCentersOfClusters.instance(j)) < uzaklikMin) {
						uzaklikMin = oklidUzaklikBul(hsr_algoritmam_filtered.instance(i), hsrCentersOfClusters.instance(j));
					}
					uzaklik += oklidUzaklikBul(hsr_algoritmam_filtered.instance(i), hsrCentersOfClusters.instance(j));
				}
				double probCluster = uzaklikMin;
				double probClass = uzaklik;
				hsrProbClasses.add(probClass);
				hsrProbClusters.add(probCluster);
			}

			for (int i = run_artis * k; i < k * run_artis + run_artis; i++) {
				double uzaklikMin = oklidUzaklikBul(run_algoritmam_filtered.instance(i), runCentersOfClusters.instance(0));
				double uzaklik = uzaklikMin;
				for (int j = 1; j < runIcin.numberOfClusters(); j++) {
					if (oklidUzaklikBul(run_algoritmam_filtered.instance(i), runCentersOfClusters.instance(j)) < uzaklikMin) {
						uzaklikMin = oklidUzaklikBul(run_algoritmam_filtered.instance(i), runCentersOfClusters.instance(j));
					}
					uzaklik += oklidUzaklikBul(run_algoritmam_filtered.instance(i), runCentersOfClusters.instance(j));
				}
				double probCluster = uzaklikMin;
				double probClass = uzaklik;
				runProbClasses.add(probClass);
				runProbClusters.add(probCluster);
			}

			for (int i = still_artis * k; i < k * still_artis + still_artis; i++) {
				double uzaklikMin = oklidUzaklikBul(still_algoritmam_filtered.instance(i), stillCentersOfClusters.instance(0));
				double uzaklik = uzaklikMin;
				for (int j = 1; j < stillIcin.numberOfClusters(); j++) {
					if (oklidUzaklikBul(still_algoritmam_filtered.instance(i), stillCentersOfClusters.instance(j)) < uzaklikMin) {
						uzaklikMin = oklidUzaklikBul(still_algoritmam_filtered.instance(i), stillCentersOfClusters.instance(j));
					}
					uzaklik += oklidUzaklikBul(still_algoritmam_filtered.instance(i), stillCentersOfClusters.instance(j));
				}
				double probCluster = uzaklikMin;
				double probClass = uzaklik;
				stillProbClasses.add(probClass);
				stillProbClusters.add(probCluster);
			}

			ArrayList<Double> walkClassThresholdBul = new ArrayList<Double>(walkProbClasses);
			ArrayList<Double> walkClusterThresholdBul = new ArrayList<Double>(walkProbClusters);
			ArrayList<Double> mrtClassThresholdBul = new ArrayList<Double>(mrtProbClasses);
			ArrayList<Double> mrtClusterThresholdBul = new ArrayList<Double>(mrtProbClusters);
			ArrayList<Double> carClassThresholdBul = new ArrayList<Double>(carProbClasses);
			ArrayList<Double> carClusterThresholdBul = new ArrayList<Double>(carProbClusters);
			ArrayList<Double> motorClassThresholdBul = new ArrayList<Double>(motorProbClasses);
			ArrayList<Double> motorClusterThresholdBul = new ArrayList<Double>(motorProbClusters);
			ArrayList<Double> trainClassThresholdBul = new ArrayList<Double>(trainProbClasses);
			ArrayList<Double> trainClusterThresholdBul = new ArrayList<Double>(trainProbClusters);
			ArrayList<Double> bicycleClassThresholdBul = new ArrayList<Double>(bicycleProbClasses);
			ArrayList<Double> bicycleClusterThresholdBul = new ArrayList<Double>(bicycleProbClusters);
			ArrayList<Double> hsrClassThresholdBul = new ArrayList<Double>(hsrProbClasses);
			ArrayList<Double> hsrClusterThresholdBul = new ArrayList<Double>(hsrProbClusters);
			ArrayList<Double> runClassThresholdBul = new ArrayList<Double>(runProbClasses);
			ArrayList<Double> runClusterThresholdBul = new ArrayList<Double>(runProbClusters);
			ArrayList<Double> stillClassThresholdBul = new ArrayList<Double>(stillProbClasses);
			ArrayList<Double> stillClusterThresholdBul = new ArrayList<Double>(stillProbClusters);

			Collections.sort(walkClassThresholdBul);
			Collections.sort(walkClusterThresholdBul);
			double walk_class_threshold = walkClassThresholdBul.get(walkClassThresholdBul.size() / 2);
			double walk_cluster_threshold = walkClusterThresholdBul.get(walkClusterThresholdBul.size() / 2);

			Collections.sort(mrtClassThresholdBul);
			Collections.sort(mrtClusterThresholdBul);
			double mrt_class_threshold = mrtClassThresholdBul.get(mrtClassThresholdBul.size() / 2);
			double mrt_cluster_threshold = mrtClusterThresholdBul.get(mrtClusterThresholdBul.size() / 2);

			Collections.sort(carClassThresholdBul);
			Collections.sort(carClusterThresholdBul);
			double car_class_threshold = carClassThresholdBul.get(carClassThresholdBul.size() / 2);
			double car_cluster_threshold = carClusterThresholdBul.get(carClusterThresholdBul.size() / 2);

			Collections.sort(motorClassThresholdBul);
			Collections.sort(motorClusterThresholdBul);
			double motor_class_threshold = motorClassThresholdBul.get(motorClassThresholdBul.size() / 2);
			double motor_cluster_threshold = motorClusterThresholdBul.get(motorClusterThresholdBul.size() / 2);

			Collections.sort(trainClassThresholdBul);
			Collections.sort(trainClusterThresholdBul);

			double train_class_threshold = trainClassThresholdBul.get(trainClassThresholdBul.size() / 2);
			double train_cluster_threshold = trainClusterThresholdBul.get(trainClusterThresholdBul.size() / 2);

			Collections.sort(bicycleClassThresholdBul);
			Collections.sort(bicycleClusterThresholdBul);
			double bicycle_class_threshold = bicycleClassThresholdBul.get(bicycleClassThresholdBul.size() / 2);
			double bicycle_cluster_threshold = bicycleClusterThresholdBul.get(bicycleClusterThresholdBul.size() / 2);

			Collections.sort(hsrClassThresholdBul);
			Collections.sort(hsrClusterThresholdBul);
			double hsr_class_threshold = hsrClassThresholdBul.get(hsrClassThresholdBul.size() / 2);
			double hsr_cluster_threshold = hsrClusterThresholdBul.get(hsrClusterThresholdBul.size() / 2);

			Collections.sort(runClassThresholdBul);
			Collections.sort(runClusterThresholdBul);
			double run_class_threshold = runClassThresholdBul.get(runClassThresholdBul.size() / 2);
			double run_cluster_threshold = runClusterThresholdBul.get(runClusterThresholdBul.size() / 2);

			Collections.sort(stillClassThresholdBul);
			Collections.sort(stillClusterThresholdBul);
			double still_class_threshold = stillClassThresholdBul.get(stillClassThresholdBul.size() / 2);
			double still_cluster_threshold = stillClusterThresholdBul.get(stillClusterThresholdBul.size() / 2);
			// System.out.println("walk_class_Threshold -> "+walk_class_threshold);
			// System.out.println("walk_cluster_Threshold -> "+walk_cluster_threshold);
			//
			// System.out.println("mrt_class_Threshold -> "+mrt_class_threshold);
			// System.out.println("mrt_cluster_Threshold -> "+mrt_cluster_threshold);
			//
			// System.out.println("car_class_Threshold -> "+car_class_threshold);
			// System.out.println("car_cluster_Threshold -> "+car_cluster_threshold);
			//
			// System.out.println("motor_class_Threshold -> "+motor_class_threshold);
			// System.out.println("motor_cluster_Threshold -> "+motor_cluster_threshold);
			//
			// System.out.println("train_class_Threshold -> "+train_class_threshold);
			// System.out.println("train_cluster_Threshold -> "+train_cluster_threshold);
			//
			// System.out.println("bicycle_class_Threshold -> "+bicycle_class_threshold);
			// System.out.println("bicycle_cluster_Threshold ->
			// "+bicycle_cluster_threshold);
			//
			// System.out.println("hsr_class_Threshold -> "+hsr_class_threshold);
			// System.out.println("hsr_cluster_Threshold -> "+hsr_cluster_threshold);
			//
			// System.out.println("run_class_Threshold -> "+run_class_threshold);
			// System.out.println("run_cluster_Threshold -> "+run_cluster_threshold);
			//
			// System.out.println("still_class_Threshold -> "+still_class_threshold);
			// System.out.println("still_cluster_Threshold -> "+still_cluster_threshold);

			for (int i = walk_artis * k; i < k * walk_artis + walk_artis; i++) {

				if (walkProbClasses.get(i) < walk_class_threshold && walkProbClusters.get(i) > walk_cluster_threshold) {
					walk_train.add(walk_algoritmam.instance(i));
					training_data.add(walk_algoritmam.instance(i));
				} else if (walkProbClasses.get(i) > walk_class_threshold
						&& walkProbClusters.get(i) > walk_cluster_threshold) {
					walk_train.add(walk_algoritmam.instance(i));
					training_data.add(walk_algoritmam.instance(i));
				}
				secmedenTrain.add(walk_algoritmam.instance(i));
			}

			for (int i = mrt_artis * k; i < k * mrt_artis + mrt_artis; i++) {
				if (mrtProbClasses.get(i) < mrt_class_threshold && mrtProbClusters.get(i) > mrt_cluster_threshold) {
					mrt_train.add(mrt_algoritmam.instance(i));
					training_data.add(mrt_algoritmam.instance(i));
				} else if (mrtProbClasses.get(i) > mrt_class_threshold
						&& mrtProbClusters.get(i) > mrt_cluster_threshold) {
					mrt_train.add(mrt_algoritmam.instance(i));
					training_data.add(mrt_algoritmam.instance(i));
				}
				secmedenTrain.add(mrt_algoritmam.instance(i));

			}

			for (int i = car_artis * k; i < k * car_artis + car_artis; i++) {
				if (carProbClasses.get(i) < car_class_threshold && carProbClusters.get(i) > car_cluster_threshold) {
					car_train.add(car_algoritmam.instance(i));
					training_data.add(car_algoritmam.instance(i));
				} else if (carProbClasses.get(i) > car_class_threshold
						&& carProbClusters.get(i) > car_cluster_threshold) {
					car_train.add(car_algoritmam.instance(i));
					training_data.add(car_algoritmam.instance(i));
				}
				secmedenTrain.add(car_algoritmam.instance(i));

			}

			for (int i = motor_artis * k; i < k * motor_artis + motor_artis; i++) {
				if (motorProbClasses.get(i) < motor_class_threshold
						&& motorProbClusters.get(i) > motor_cluster_threshold) {
					motor_train.add(motor_algoritmam.instance(i));
					training_data.add(motor_algoritmam.instance(i));
				} else if (motorProbClasses.get(i) > motor_class_threshold
						&& motorProbClusters.get(i) > motor_cluster_threshold) {
					motor_train.add(motor_algoritmam.instance(i));
					training_data.add(motor_algoritmam.instance(i));
				}
				secmedenTrain.add(motor_algoritmam.instance(i));

			}

			for (int i = train_artis * k; i < k * train_artis + train_artis; i++) {
				if (trainProbClasses.get(i) < train_class_threshold
						&& trainProbClusters.get(i) > train_cluster_threshold) {
					train_train.add(train_algoritmam.instance(i));
					training_data.add(train_algoritmam.instance(i));
				} else if (trainProbClasses.get(i) > train_class_threshold
						&& trainProbClusters.get(i) > train_cluster_threshold) {
					train_train.add(train_algoritmam.instance(i));
					training_data.add(train_algoritmam.instance(i));
				}
				secmedenTrain.add(train_algoritmam.instance(i));

			}

			for (int i = bicycle_artis * k; i < k * bicycle_artis + bicycle_artis; i++) {
				if (bicycleProbClasses.get(i) < bicycle_class_threshold
						&& bicycleProbClusters.get(i) > bicycle_cluster_threshold) {
					bicycle_train.add(bicycle_algoritmam.instance(i));
					training_data.add(bicycle_algoritmam.instance(i));
				} else if (bicycleProbClasses.get(i) > bicycle_class_threshold
						&& bicycleProbClusters.get(i) > bicycle_cluster_threshold) {
					bicycle_train.add(bicycle_algoritmam.instance(i));
					training_data.add(bicycle_algoritmam.instance(i));
				}
				secmedenTrain.add(bicycle_algoritmam.instance(i));

			}

			for (int i = hsr_artis * k; i < k * hsr_artis + hsr_artis; i++) {
				if (hsrProbClasses.get(i) < hsr_class_threshold && hsrProbClusters.get(i) > hsr_cluster_threshold) {
					hsr_train.add(hsr_algoritmam.instance(i));
					training_data.add(hsr_algoritmam.instance(i));
				} else if (hsrProbClasses.get(i) > hsr_class_threshold
						&& hsrProbClusters.get(i) > hsr_cluster_threshold) {
					hsr_train.add(hsr_algoritmam.instance(i));
					training_data.add(hsr_algoritmam.instance(i));
				}
				secmedenTrain.add(hsr_algoritmam.instance(i));

			}

			for (int i = run_artis * k; i < k * run_artis + run_artis; i++) {
				if (runProbClasses.get(i) < run_class_threshold && runProbClusters.get(i) > run_cluster_threshold) {
					run_train.add(run_algoritmam.instance(i));
					training_data.add(run_algoritmam.instance(i));
				} else if (runProbClasses.get(i) > run_class_threshold
						&& runProbClusters.get(i) > run_cluster_threshold) {
					run_train.add(run_algoritmam.instance(i));
					training_data.add(run_algoritmam.instance(i));
				}
				secmedenTrain.add(run_algoritmam.instance(i));

			}

			for (int i = still_artis * k; i < k * still_artis + still_artis; i++) {
				if (stillProbClasses.get(i) < still_class_threshold
						&& stillProbClusters.get(i) > still_cluster_threshold) {
					still_train.add(still_algoritmam.instance(i));
					training_data.add(still_algoritmam.instance(i));
				} else if (stillProbClasses.get(i) > still_class_threshold
						&& stillProbClusters.get(i) > still_cluster_threshold) {
					still_train.add(still_algoritmam.instance(i));
					training_data.add(still_algoritmam.instance(i));
				}
				secmedenTrain.add(still_algoritmam.instance(i));

			}
			walk_train_filtered = deleteClassAtt(walk_train);
			mrt_train_filtered = deleteClassAtt(mrt_train);
			car_train_filtered = deleteClassAtt(car_train);
			motor_train_filtered = deleteClassAtt(motor_train);
			train_train_filtered = deleteClassAtt(train_train);
			bicycle_train_filtered = deleteClassAtt(bicycle_train);
			hsr_train_filtered = deleteClassAtt(hsr_train);
			run_train_filtered = deleteClassAtt(run_train);
			still_train_filtered = deleteClassAtt(still_train);
			RandomForest rf = new RandomForest();
			BufferedWriter secerekdosya = new BufferedWriter(new FileWriter("./"+arffDosyaIsmi+"/secerek" + k + ".arff"));
			secerekdosya.write(training_data.toString());
			secerekdosya.close();
			rf.buildClassifier(training_data);
			long timer2 = System.currentTimeMillis();
			Evaluation eval = new Evaluation(training_data);
			eval.evaluateModel(rf, test_data);
			long timer3 = System.currentTimeMillis();
			timerlarim1.add((timer3 - timer2));
			System.out.println("k = " + k + " icin sonuclar SEÇEREK-> ");
			System.out.println("Training Data - Num Instances -> " + training_data.numInstances());
			System.out.println(eval.toSummaryString());
			basarilar.add(eval.pctCorrect());
			System.out.println("k = " + k + " icin sonuclar SEÇMEDEN-> ");
			System.out.println("Training Data - Num Instances -> " + secmedenTrain.numInstances());
			RandomForest rf2 = new RandomForest();
			BufferedWriter secmedendosya = new BufferedWriter(
					new FileWriter("./"+arffDosyaIsmi+"/secmeden" + k + ".arff"));
			secmedendosya.write(secmedenTrain.toString());
			secmedendosya.close();
			rf2.buildClassifier(secmedenTrain);
			long timerx = System.currentTimeMillis();
			Evaluation eval2 = new Evaluation(secmedenTrain);
			eval2.evaluateModel(rf2, test_data);
			long timery = System.currentTimeMillis();
			System.out.println(eval2.toSummaryString());
			timerlarim2.add((timery - timerx));
			basarilar2.add(eval2.pctCorrect());
			secerekInstanceSayilari.add(training_data.numInstances());
			secmedenInstanceSayilari.add(secmedenTrain.numInstances());
		}
		for (int i = 0; i < basarilar.size(); i++) {
			System.out.println("Seçerek i=" + i + " icin basari -> " + basarilar.get(i));
			System.out.println("Seçmeden i=" + i + " icin basari -> " + basarilar2.get(i));
		}
		System.out.println("Seçerek Başarılar");
		for (int i = 0; i < basarilar.size(); i++) {
			System.out.println(basarilar.get(i));
		}
		System.out.println("--------");
		System.out.println("Seçmeden Başarılar");
		for (int i = 0; i < basarilar2.size(); i++) {
			System.out.println(basarilar2.get(i));
		}
		System.out.println("Seçerek Süreler");
		for (int tt = 0; tt < timerlarim1.size(); tt++) {
			System.out.println(timerlarim1.get(tt));
		}
		System.out.println("Seçmeden Süreler");
		for (int tt = 0; tt < timerlarim2.size(); tt++) {
			System.out.println(timerlarim2.get(tt));
		}
		System.out.println("Seçerek Instance Sayilari");
		for (int tt = 0; tt < secerekInstanceSayilari.size(); tt++) {
			System.out.println(secerekInstanceSayilari.get(tt));
		}
		System.out.println("Seçmeden Instance Sayilari");
		for (int tt = 0; tt < secmedenInstanceSayilari.size(); tt++) {
			System.out.println(secmedenInstanceSayilari.get(tt));
		}
		System.out.println("Seçerek Artan Başarı");
		for(int tt=0;tt<basarilar.size();tt++) {
			System.out.println(basarilar2.get(tt)-basarilar.get(tt));
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
		for (int i = 0; i < dataToAdd.numInstances(); i++) {
			data.add(dataToAdd.instance(i));
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
