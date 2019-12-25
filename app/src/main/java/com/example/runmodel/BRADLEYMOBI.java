/*
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

public class BRADLEYMOBI {
	public static final int iterasyonSayisi=15;
	public static void main(String[] args) throws Exception {
		ArrayList<Long> timerlarim1=new ArrayList<Long>();
		ArrayList<Long> timerlarim2=new ArrayList<Long>();
		ArrayList<Integer> secerekInstanceSayilari = new ArrayList<Integer>();
		ArrayList<Integer> secmedenInstanceSayilari = new ArrayList<Integer>();
		ArrayList<Double> basarilar=new ArrayList<Double>();
		ArrayList<Double> basarilar2=new ArrayList<Double>();
		Instances general_data=null;
		general_data=loadArff(general_data,"mobi.arff");		
		Instances blank_data=new Instances(general_data);
		RemoveWithValues rwv=new RemoveWithValues();
		rwv.setInputFormat(general_data);
		try {
			blank_data=Filter.useFilter(blank_data, rwv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Instances walData=new Instances(blank_data);
		Instances stdData=new Instances(blank_data);
		Instances jogData=new Instances(blank_data);
		Instances jumData=new Instances(blank_data);
		Instances stuData=new Instances(blank_data);
		Instances stnData=new Instances(blank_data);
		Instances schData=new Instances(blank_data);
		Instances csiData=new Instances(blank_data);
		Instances csoData=new Instances(blank_data);
		Instances secmedenTrain=new Instances(blank_data);
		for (Instance instance : general_data) {
			double classIndex=instance.classValue();
			if(classIndex==1) {
				walData.add(instance);
			}else if(classIndex==0) {
				stdData.add(instance);
			}else if(classIndex==2) {
				jogData.add(instance);
			}else if(classIndex==3) {
				jumData.add(instance);
			}else if(classIndex==4) {
				stuData.add(instance);
			}else if(classIndex==5) {
				stnData.add(instance);
			}else if(classIndex==6) {
				schData.add(instance);
			}else if(classIndex==7) {
				csiData.add(instance);
			}else if(classIndex==8) {
				csoData.add(instance);
			}
		}

		Instances wal_gecici = splitData(walData, 70, false);
		Instances wal_train = splitData(wal_gecici, 33.33, true);
		Instances wal_test = splitData(wal_gecici, 33.33, false);
		Instances wal_algoritmam = splitData(walData, 70, true);	
		
		Instances std_gecici = splitData(stdData, 70, false);
		Instances std_train = splitData(std_gecici, 33.33, true);
		Instances std_test = splitData(std_gecici, 33.33, false);
		Instances std_algoritmam = splitData(stdData, 70, true);	
		
		Instances jog_gecici = splitData(jogData, 70, false);
		Instances jog_train = splitData(jog_gecici, 33.33, true);
		Instances jog_test = splitData(jog_gecici, 33.33, false);
		Instances jog_algoritmam = splitData(jogData, 70, true);	
	
		Instances jum_gecici = splitData(jumData, 70, false);
		Instances jum_train = splitData(jum_gecici, 33.33, true);
		Instances jum_test = splitData(jum_gecici, 33.33, false);
		Instances jum_algoritmam = splitData(jumData, 70, true);	
//		System.out.println("jum_gecici size -> "+jum_gecici.size());
		System.out.println("jum_train size -> "+jum_train.size());
		System.out.println("jum_test size -> "+jum_test.size());
		System.out.println("jum_algoritmam size -> "+jum_algoritmam.size());
		
		Instances stu_gecici = splitData(stuData, 70, false);
		Instances stu_train = splitData(stu_gecici, 33.33, true);
		Instances stu_test = splitData(stu_gecici, 33.33, false);
		Instances stu_algoritmam = splitData(stuData, 70, true);	
//		System.out.println("stu_gecici size -> "+stu_gecici.size());
		System.out.println("stu_train size -> "+stu_train.size());
		System.out.println("stu_test size -> "+stu_test.size());
		System.out.println("stu_algoritmam size -> "+stu_algoritmam.size());
		
		Instances stn_gecici = splitData(stnData, 70, false);
		Instances stn_train = splitData(stn_gecici, 33.33, true);
		Instances stn_test = splitData(stn_gecici, 33.33, false);
		Instances stn_algoritmam = splitData(stnData, 70, true);	
//		System.out.println("stn_gecici size -> "+stn_gecici.size());
		System.out.println("stn_train size -> "+stn_train.size());
		System.out.println("stn_test size -> "+stn_test.size());
		System.out.println("stn_algoritmam size -> "+stn_algoritmam.size());
		
		Instances sch_gecici = splitData(schData, 70, false);
		Instances sch_train = splitData(sch_gecici, 33.33, true);
		Instances sch_test = splitData(sch_gecici, 33.33, false);
		Instances sch_algoritmam = splitData(schData, 70, true);	
//		System.out.println("sch_gecici size -> "+sch_gecici.size());
		System.out.println("sch_train size -> "+sch_train.size());
		System.out.println("sch_test size -> "+sch_test.size());
		System.out.println("sch_algoritmam size -> "+sch_algoritmam.size());
		
		Instances csi_gecici = splitData(csiData, 70, false);
		Instances csi_train = splitData(csi_gecici, 33.33, true);
		Instances csi_test = splitData(csi_gecici, 33.33, false);
		Instances csi_algoritmam = splitData(csiData, 70, true);	
//		System.out.println("csi_gecici size -> "+csi_gecici.size());
		System.out.println("csi_train size -> "+csi_train.size());
		System.out.println("csi_test size -> "+csi_test.size());
		System.out.println("csi_algoritmam size -> "+csi_algoritmam.size());
		
		Instances cso_gecici = splitData(csoData, 70, false);
		Instances cso_train = splitData(cso_gecici, 33.33, true);
		Instances cso_test = splitData(cso_gecici, 33.33, false);
		Instances cso_algoritmam = splitData(csoData, 70, true);	
		
		int wal_artis=wal_algoritmam.size()/iterasyonSayisi;
		int std_artis=std_algoritmam.size()/iterasyonSayisi;
		int jog_artis=jog_algoritmam.size()/iterasyonSayisi;
		int jum_artis=jum_algoritmam.size()/iterasyonSayisi;
		int stu_artis=stu_algoritmam.size()/iterasyonSayisi;
		int sch_artis=sch_algoritmam.size()/iterasyonSayisi;
		int csi_artis=csi_algoritmam.size()/iterasyonSayisi;
		int cso_artis=cso_algoritmam.size()/iterasyonSayisi;
		int stn_artis=cso_algoritmam.size()/iterasyonSayisi;
		
		Instances training_data=new Instances(blank_data);
		training_data=addOn(training_data, wal_train);
		training_data=addOn(training_data, std_train);
		training_data=addOn(training_data, jog_train);
		training_data=addOn(training_data, jum_train);
		training_data=addOn(training_data, stu_train);
		training_data=addOn(training_data, sch_train);
		training_data=addOn(training_data, csi_train);
		training_data=addOn(training_data, cso_train);
		secmedenTrain=addOn(secmedenTrain, wal_train);
		secmedenTrain=addOn(secmedenTrain, std_train);
		secmedenTrain=addOn(secmedenTrain, jog_train);
		secmedenTrain=addOn(secmedenTrain, jum_train);
		secmedenTrain=addOn(secmedenTrain, stu_train);
		secmedenTrain=addOn(secmedenTrain, sch_train);
		secmedenTrain=addOn(secmedenTrain, csi_train);
		secmedenTrain=addOn(secmedenTrain, cso_train);
		Instances test_data=new Instances(blank_data);
		test_data=addOn(test_data, wal_test);
		test_data=addOn(test_data, std_test);
		test_data=addOn(test_data, jog_test);
		test_data=addOn(test_data, jum_test);
		test_data=addOn(test_data, stu_test);
		test_data=addOn(test_data, sch_test);
		test_data=addOn(test_data, csi_test);
		test_data=addOn(test_data, cso_test);
		BufferedWriter test=new BufferedWriter(new FileWriter("./dosya2/15iterasyon/test.arff"));
		test.write(test_data.toString());
		test.close();
		Instances std_train_filtered=deleteClassAtt(std_train);
		Instances wal_train_filtered=deleteClassAtt(wal_train);
		Instances jog_train_filtered=deleteClassAtt(jog_train);
		Instances jum_train_filtered=deleteClassAtt(jum_train);
		Instances stu_train_filtered=deleteClassAtt(stu_train);
		Instances stn_train_filtered=deleteClassAtt(stn_train);
		Instances sch_train_filtered=deleteClassAtt(sch_train);
		Instances csi_train_filtered=deleteClassAtt(csi_train);
		Instances cso_train_filtered=deleteClassAtt(cso_train);
		
		Instances std_algoritmam_filtered=deleteClassAtt(std_algoritmam);
		Instances wal_algoritmam_filtered=deleteClassAtt(wal_algoritmam);
		Instances jog_algoritmam_filtered=deleteClassAtt(jog_algoritmam);
		Instances jum_algoritmam_filtered=deleteClassAtt(jum_algoritmam);
		Instances stu_algoritmam_filtered=deleteClassAtt(stu_algoritmam);
		Instances stn_algoritmam_filtered=deleteClassAtt(stn_algoritmam);
		Instances sch_algoritmam_filtered=deleteClassAtt(sch_algoritmam);
		Instances csi_algoritmam_filtered=deleteClassAtt(csi_algoritmam);
		Instances cso_algoritmam_filtered=deleteClassAtt(cso_algoritmam);
		ArrayList<Double> stdProbClasses=new ArrayList<Double>();
		ArrayList<Double> stdProbClusters=new ArrayList<Double>();
		ArrayList<Double> walProbClasses=new ArrayList<Double>();
		ArrayList<Double> walProbClusters=new ArrayList<Double>();
		ArrayList<Double> jogProbClasses=new ArrayList<Double>();
		ArrayList<Double> jogProbClusters=new ArrayList<Double>();
		ArrayList<Double> jumProbClasses=new ArrayList<Double>();
		ArrayList<Double> jumProbClusters=new ArrayList<Double>();
		ArrayList<Double> stuProbClasses=new ArrayList<Double>();
		ArrayList<Double> stuProbClusters=new ArrayList<Double>();
		ArrayList<Double> stnProbClasses=new ArrayList<Double>();
		ArrayList<Double> stnProbClusters=new ArrayList<Double>();
		ArrayList<Double> schProbClasses=new ArrayList<Double>();
		ArrayList<Double> schProbClusters=new ArrayList<Double>();
		ArrayList<Double> csiProbClasses=new ArrayList<Double>();
		ArrayList<Double> csiProbClusters=new ArrayList<Double>();
		ArrayList<Double> csoProbClasses=new ArrayList<Double>();
		ArrayList<Double> csoProbClusters=new ArrayList<Double>();				
		for(int k=0;k<iterasyonSayisi;k++) {		
			long iterasyonzamani1=System.currentTimeMillis();
			XMeans stdIcin=new XMeans();
			stdIcin.buildClusterer(std_train_filtered);
			XMeans walIcin=new XMeans();
			walIcin.buildClusterer(wal_train_filtered);
			XMeans jogIcin=new XMeans();
			jogIcin.buildClusterer(jog_train_filtered);
			XMeans jumIcin=new XMeans();
			jumIcin.buildClusterer(jum_train_filtered);
			XMeans trainIcin=new XMeans();
			trainIcin.buildClusterer(stu_train_filtered);
			XMeans stnIcin=new XMeans();
			stnIcin.buildClusterer(stn_train_filtered);
			XMeans schIcin=new XMeans();
			schIcin.buildClusterer(sch_train_filtered);
			XMeans csiIcin=new XMeans();
			csiIcin.buildClusterer(csi_train_filtered);
			XMeans csoIcin=new XMeans();
			csoIcin.buildClusterer(cso_train_filtered);
			
			
			Instances stdCentersOfClusters=stdIcin.getClusterCenters();
			Instances walCentersOfClusters=walIcin.getClusterCenters();
			Instances jogCentersOfClusters=jogIcin.getClusterCenters();
			Instances jumCentersOfClusters=jumIcin.getClusterCenters();
			Instances trainCentersOfClusters=trainIcin.getClusterCenters();
			Instances stnCentersOfClusters=stnIcin.getClusterCenters();
			Instances schCentersOfClusters=schIcin.getClusterCenters();
			Instances csiCentersOfClusters=csiIcin.getClusterCenters();
			Instances csoCentersOfClusters=csoIcin.getClusterCenters();

			
			for(int i=std_artis*k;i<k*std_artis+std_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(std_algoritmam_filtered.get(i), stdCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<stdIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(std_algoritmam_filtered.get(i), stdCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(std_algoritmam_filtered.get(i), stdCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(std_algoritmam_filtered.get(i), stdCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				stdProbClasses.add(probClass);
				stdProbClusters.add(probCluster);
			
			}

			for(int i=wal_artis*k;i<k*wal_artis+wal_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(wal_algoritmam_filtered.get(i), walCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<walIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(wal_algoritmam_filtered.get(i), walCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(wal_algoritmam_filtered.get(i), walCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(wal_algoritmam_filtered.get(i), walCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				walProbClasses.add(probClass);
				walProbClusters.add(probCluster);
			}			
			
			for(int i=jog_artis*k;i<k*jog_artis+jog_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(jog_algoritmam_filtered.get(i), jogCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<jogIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(jog_algoritmam_filtered.get(i), jogCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(jog_algoritmam_filtered.get(i), jogCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(jog_algoritmam_filtered.get(i), jogCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				jogProbClasses.add(probClass);
				jogProbClusters.add(probCluster);
			}
			
			for(int i=jum_artis*k;i<k*jum_artis+jum_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(jum_algoritmam_filtered.get(i), jumCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<jumIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(jum_algoritmam_filtered.get(i), jumCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(jum_algoritmam_filtered.get(i), jumCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(jum_algoritmam_filtered.get(i), jumCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				jumProbClasses.add(probClass);
				jumProbClusters.add(probCluster);
			}
			
			for(int i=stu_artis*k;i<k*stu_artis+stu_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(stu_algoritmam_filtered.get(i), trainCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<trainIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(stu_algoritmam_filtered.get(i), trainCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(stu_algoritmam_filtered.get(i), trainCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(stu_algoritmam_filtered.get(i), trainCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				stuProbClasses.add(probClass);
				stuProbClusters.add(probCluster);
			}
			
			for(int i=stn_artis*k;i<k*stn_artis+stn_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(stn_algoritmam_filtered.get(i), stnCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<stnIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(stn_algoritmam_filtered.get(i), stnCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(stn_algoritmam_filtered.get(i), stnCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(stn_algoritmam_filtered.get(i), stnCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				stnProbClasses.add(probClass);
				stnProbClusters.add(probCluster);
			}

			for(int i=sch_artis*k;i<k*sch_artis+sch_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(sch_algoritmam_filtered.get(i), schCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<schIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(sch_algoritmam_filtered.get(i), schCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(sch_algoritmam_filtered.get(i), schCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(sch_algoritmam_filtered.get(i), schCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				schProbClasses.add(probClass);
				schProbClusters.add(probCluster);
			}
			
			for(int i=csi_artis*k;i<k*csi_artis+csi_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(csi_algoritmam_filtered.get(i), csiCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<csiIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(csi_algoritmam_filtered.get(i), csiCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(csi_algoritmam_filtered.get(i), csiCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(csi_algoritmam_filtered.get(i), csiCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				csiProbClasses.add(probClass);
				csiProbClusters.add(probCluster);
			}
			
			for(int i=cso_artis*k;i<k*cso_artis+cso_artis;i++) {
				double uzaklikMin=oklidUzaklikBul(cso_algoritmam_filtered.get(i), csoCentersOfClusters.get(0));
				double uzaklik=uzaklikMin;
				for(int j=1;j<csoIcin.numberOfClusters();j++) {
					if(oklidUzaklikBul(cso_algoritmam_filtered.get(i), csoCentersOfClusters.get(j))<uzaklikMin) {
						uzaklikMin=oklidUzaklikBul(cso_algoritmam_filtered.get(i), csoCentersOfClusters.get(j));
					}
					uzaklik+=oklidUzaklikBul(cso_algoritmam_filtered.get(i), csoCentersOfClusters.get(j));
				}
				double probCluster=uzaklikMin;
				double probClass=uzaklik;
				csoProbClasses.add(probClass);
				csoProbClusters.add(probCluster);
			}
			
			ArrayList<Double> stdClassThresholdBul=new ArrayList<Double>(stdProbClasses);
			ArrayList<Double> stdClusterThresholdBul=new ArrayList<Double>(stdProbClusters);
			ArrayList<Double> walClassThresholdBul=new ArrayList<Double>(walProbClasses);
			ArrayList<Double> walClusterThresholdBul=new ArrayList<Double>(walProbClusters);
			ArrayList<Double> jogClassThresholdBul=new ArrayList<Double>(jogProbClasses);
			ArrayList<Double> jogClusterThresholdBul=new ArrayList<Double>(jogProbClusters);
			ArrayList<Double> jumClassThresholdBul=new ArrayList<Double>(jumProbClasses);
			ArrayList<Double> jumClusterThresholdBul=new ArrayList<Double>(jumProbClusters);
			ArrayList<Double> trainClassThresholdBul=new ArrayList<Double>(stuProbClasses);
			ArrayList<Double> trainClusterThresholdBul=new ArrayList<Double>(stuProbClusters);
			ArrayList<Double> stnClassThresholdBul=new ArrayList<Double>(stnProbClasses);
			ArrayList<Double> stnClusterThresholdBul=new ArrayList<Double>(stnProbClusters);
			ArrayList<Double> schClassThresholdBul=new ArrayList<Double>(schProbClasses);
			ArrayList<Double> schClusterThresholdBul=new ArrayList<Double>(schProbClusters);
			ArrayList<Double> csiClassThresholdBul=new ArrayList<Double>(csiProbClasses);
			ArrayList<Double> csiClusterThresholdBul=new ArrayList<Double>(csiProbClusters);
			ArrayList<Double> csoClassThresholdBul=new ArrayList<Double>(csoProbClasses);
			ArrayList<Double> csoClusterThresholdBul=new ArrayList<Double>(csoProbClusters);
			
			
			Collections.sort(stdClassThresholdBul);
			Collections.sort(stdClusterThresholdBul);
			double std_class_threshold=stdClassThresholdBul.get(stdClassThresholdBul.size()/2);
			double std_cluster_threshold=stdClusterThresholdBul.get(stdClusterThresholdBul.size()/2);
			
			Collections.sort(walClassThresholdBul);
			Collections.sort(walClusterThresholdBul);
			double wal_class_threshold=walClassThresholdBul.get(walClassThresholdBul.size()/2);
			double wal_cluster_threshold=walClusterThresholdBul.get(walClusterThresholdBul.size()/2);

			Collections.sort(jogClassThresholdBul);
			Collections.sort(jogClusterThresholdBul);
			double jog_class_threshold=jogClassThresholdBul.get(jogClassThresholdBul.size()/2);
			double jog_cluster_threshold=jogClusterThresholdBul.get(jogClusterThresholdBul.size()/2);

			
			Collections.sort(jumClassThresholdBul);
			Collections.sort(jumClusterThresholdBul);
			double jum_class_threshold=jumClassThresholdBul.get(jumClassThresholdBul.size()/2);
			double jum_cluster_threshold=jumClusterThresholdBul.get(jumClusterThresholdBul.size()/2);

			Collections.sort(trainClassThresholdBul);
			Collections.sort(trainClusterThresholdBul);
			double train_class_threshold=trainClassThresholdBul.get(trainClassThresholdBul.size()/2);
			double train_cluster_threshold=trainClusterThresholdBul.get(trainClusterThresholdBul.size()/2);

			Collections.sort(stnClassThresholdBul);
			Collections.sort(stnClusterThresholdBul);
			double stn_class_threshold=stnClassThresholdBul.get(stnClassThresholdBul.size()/2);
			double stn_cluster_threshold=stnClusterThresholdBul.get(stnClusterThresholdBul.size()/2);

			Collections.sort(schClassThresholdBul);
			Collections.sort(schClusterThresholdBul);
			double sch_class_threshold=schClassThresholdBul.get(schClassThresholdBul.size()/2);
			double sch_cluster_threshold=schClusterThresholdBul.get(schClusterThresholdBul.size()/2);

			
			Collections.sort(csiClassThresholdBul);
			Collections.sort(csiClusterThresholdBul);
			double csi_class_threshold=csiClassThresholdBul.get(csiClassThresholdBul.size()/2);
			double csi_cluster_threshold=csiClusterThresholdBul.get(csiClusterThresholdBul.size()/2);

			Collections.sort(csoClassThresholdBul);
			Collections.sort(csoClusterThresholdBul);
			double cso_class_threshold=csoClassThresholdBul.get(csoClassThresholdBul.size()/2);
			double cso_cluster_threshold=csoClusterThresholdBul.get(csoClusterThresholdBul.size()/2);
//			System.out.println("std_class_Threshold -> "+std_class_threshold);
//			System.out.println("std_cluster_Threshold -> "+std_cluster_threshold);
//			
//			System.out.println("wal_class_Threshold -> "+wal_class_threshold);
//			System.out.println("wal_cluster_Threshold -> "+wal_cluster_threshold);
//			
//			System.out.println("jog_class_Threshold -> "+jog_class_threshold);
//			System.out.println("jog_cluster_Threshold -> "+jog_cluster_threshold);
//			
//			System.out.println("jum_class_Threshold -> "+jum_class_threshold);
//			System.out.println("jum_cluster_Threshold -> "+jum_cluster_threshold);
//			
//			System.out.println("train_class_Threshold -> "+train_class_threshold);
//			System.out.println("train_cluster_Threshold -> "+train_cluster_threshold);
//			
//			System.out.println("stn_class_Threshold -> "+stn_class_threshold);
//			System.out.println("stn_cluster_Threshold -> "+stn_cluster_threshold);
//			
//			System.out.println("sch_class_Threshold -> "+sch_class_threshold);
//			System.out.println("sch_cluster_Threshold -> "+sch_cluster_threshold);
//			
//			System.out.println("csi_class_Threshold -> "+csi_class_threshold);
//			System.out.println("csi_cluster_Threshold -> "+csi_cluster_threshold);
//			
//			System.out.println("cso_class_Threshold -> "+cso_class_threshold);
//			System.out.println("cso_cluster_Threshold -> "+cso_cluster_threshold);
			
			for(int i=std_artis*k;i<k*std_artis+std_artis;i++) {
			
				if(stdProbClasses.get(i)<std_class_threshold && stdProbClusters.get(i)>std_cluster_threshold) {
					std_train.add(std_algoritmam.get(i));
					training_data.add(std_algoritmam.get(i));
				}else if(stdProbClasses.get(i)>std_class_threshold && stdProbClusters.get(i)>std_cluster_threshold) {
					std_train.add(std_algoritmam.get(i));
					training_data.add(std_algoritmam.get(i));
				}
				secmedenTrain.add(std_algoritmam.get(i));
			}
			
			for(int i=wal_artis*k;i<k*wal_artis+wal_artis;i++) {
				if(walProbClasses.get(i)<wal_class_threshold && walProbClusters.get(i)>wal_cluster_threshold) {
					wal_train.add(wal_algoritmam.get(i));
					training_data.add(wal_algoritmam.get(i));
				}else if(walProbClasses.get(i)>wal_class_threshold && walProbClusters.get(i)>wal_cluster_threshold) {
					wal_train.add(wal_algoritmam.get(i));
					training_data.add(wal_algoritmam.get(i));
				}
				secmedenTrain.add(wal_algoritmam.get(i));

			}
			
			for(int i=jog_artis*k;i<k*jog_artis+jog_artis;i++) {
				if(jogProbClasses.get(i)<jog_class_threshold && jogProbClusters.get(i)>jog_cluster_threshold) {
					jog_train.add(jog_algoritmam.get(i));
					training_data.add(jog_algoritmam.get(i));
				}else if(jogProbClasses.get(i)>jog_class_threshold && jogProbClusters.get(i)>jog_cluster_threshold) {
					jog_train.add(jog_algoritmam.get(i));
					training_data.add(jog_algoritmam.get(i));
				}
				secmedenTrain.add(jog_algoritmam.get(i));

			}
			
			for(int i=jum_artis*k;i<k*jum_artis+jum_artis;i++) {
				if(jumProbClasses.get(i)<jum_class_threshold && jumProbClusters.get(i)>jum_cluster_threshold) {
					jum_train.add(jum_algoritmam.get(i));
					training_data.add(jum_algoritmam.get(i));
				}else if(jumProbClasses.get(i)>jum_class_threshold && jumProbClusters.get(i)>jum_cluster_threshold) {
					jum_train.add(jum_algoritmam.get(i));
					training_data.add(jum_algoritmam.get(i));
				}
				secmedenTrain.add(jum_algoritmam.get(i));

			}
			
			for(int i=stu_artis*k;i<k*stu_artis+stu_artis;i++) {
				if(stuProbClasses.get(i)<train_class_threshold && stuProbClusters.get(i)>train_cluster_threshold) {
					stu_train.add(stu_algoritmam.get(i));
					training_data.add(stu_algoritmam.get(i));
				}else if(stuProbClasses.get(i)>train_class_threshold && stuProbClusters.get(i)>train_cluster_threshold) {
					stu_train.add(stu_algoritmam.get(i));
					training_data.add(stu_algoritmam.get(i));
				}
				secmedenTrain.add(stu_algoritmam.get(i));

			}
			
			for(int i=stn_artis*k;i<k*stn_artis+stn_artis;i++) {
				if(stnProbClasses.get(i)<stn_class_threshold && stnProbClusters.get(i)>stn_cluster_threshold) {
					stn_train.add(stn_algoritmam.get(i));
					training_data.add(stn_algoritmam.get(i));
				}else if(stnProbClasses.get(i)>stn_class_threshold && stnProbClusters.get(i)>stn_cluster_threshold) {
					stn_train.add(stn_algoritmam.get(i));
					training_data.add(stn_algoritmam.get(i));
				}
				secmedenTrain.add(stn_algoritmam.get(i));

			}
			
			for(int i=sch_artis*k;i<k*sch_artis+sch_artis;i++) {
				if(schProbClasses.get(i)<sch_class_threshold && schProbClusters.get(i)>sch_cluster_threshold) {
					sch_train.add(sch_algoritmam.get(i));
					training_data.add(sch_algoritmam.get(i));
				}else if(schProbClasses.get(i)>sch_class_threshold && schProbClusters.get(i)>sch_cluster_threshold) {
					sch_train.add(sch_algoritmam.get(i));
					training_data.add(sch_algoritmam.get(i));
				}
				secmedenTrain.add(sch_algoritmam.get(i));

			}	
			
			for(int i=csi_artis*k;i<k*csi_artis+csi_artis;i++) {
				if(csiProbClasses.get(i)<csi_class_threshold && csiProbClusters.get(i)>csi_cluster_threshold) {
					csi_train.add(csi_algoritmam.get(i));
					training_data.add(csi_algoritmam.get(i));
				}else if(csiProbClasses.get(i)>csi_class_threshold && csiProbClusters.get(i)>csi_cluster_threshold) {
					csi_train.add(csi_algoritmam.get(i));
					training_data.add(csi_algoritmam.get(i));
				}
				secmedenTrain.add(csi_algoritmam.get(i));

			}
			
			for(int i=cso_artis*k;i<k*cso_artis+cso_artis;i++) {
				if(csoProbClasses.get(i)<cso_class_threshold && csoProbClusters.get(i)>cso_cluster_threshold) {
					cso_train.add(cso_algoritmam.get(i));
					training_data.add(cso_algoritmam.get(i));
				}else if(csoProbClasses.get(i)>cso_class_threshold && csoProbClusters.get(i)>cso_cluster_threshold) {
					cso_train.add(cso_algoritmam.get(i));
					training_data.add(cso_algoritmam.get(i));
				}
				secmedenTrain.add(cso_algoritmam.get(i));

			}
			std_train_filtered=deleteClassAtt(std_train);
			wal_train_filtered=deleteClassAtt(wal_train);
			jog_train_filtered=deleteClassAtt(jog_train);
			jum_train_filtered=deleteClassAtt(jum_train);
			stu_train_filtered=deleteClassAtt(stu_train);
			stn_train_filtered=deleteClassAtt(stn_train);
			sch_train_filtered=deleteClassAtt(sch_train);
			csi_train_filtered=deleteClassAtt(csi_train);
			cso_train_filtered=deleteClassAtt(cso_train);
			RandomForest rf=new RandomForest();
			rf.buildClassifier(training_data);
			BufferedWriter secerekdosya=new BufferedWriter(new FileWriter("./dosya2/15iterasyon/secerek"+k+".arff"));
			secerekdosya.write(training_data.toString());
			secerekdosya.close();
			long timer2=System.currentTimeMillis();
			Evaluation eval = new Evaluation(training_data);
			eval.evaluateModel(rf, test_data);
			long timer3=System.currentTimeMillis();
			timerlarim1.add((timer3-timer2));
			System.out.println("k = "+ k +" icin sonuclar SEÇEREK-> ");
			System.out.println("Training Data - Num Instances -> "+training_data.numInstances());
			System.out.println(eval.toSummaryString());
			basarilar.add(eval.pctCorrect());
			System.out.println("k = "+ k +" icin sonuclar SEÇMEDEN-> ");
			System.out.println("Training Data - Num Instances -> "+secmedenTrain.numInstances());
			RandomForest rf2=new RandomForest();
			BufferedWriter secmedendosya=new BufferedWriter(new FileWriter("./dosya2/15iterasyon/secmeden"+k+".arff"));
			secmedendosya.write(secmedenTrain.toString());
			secmedendosya.close();
			rf2.buildClassifier(secmedenTrain);
			long timerx=System.currentTimeMillis();
			Evaluation eval2 = new Evaluation(secmedenTrain);
			eval2.evaluateModel(rf2, test_data);
			long timery=System.currentTimeMillis();
			System.out.println(eval2.toSummaryString());
			timerlarim2.add((timery-timerx));
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
