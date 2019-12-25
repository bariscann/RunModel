package com.example.runmodel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.RemovePercentage;


public class Data {

    private Instances allData;
    private Instances trainData;
    private Instances testData;
    private double trainRate;

    private String fileName;
    private ArffLoader.ArffReader arff;

    public Data(String fileName, double trainRate) throws IOException {
        this.fileName = fileName;
        BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
        this.arff = new ArffLoader.ArffReader(reader);
        this.trainRate = trainRate;
    }

    public void loadData() throws IOException {
        this.allData = this.arff.getData();
        this.allData.setClass(this.allData.attribute("label"));
    }

    public Instances getAllData() {
        return this.allData;
    }

    public int numAllInstances() {
        return this.allData.numInstances();
    }

    public int numTrainInstances() {
        return this.trainData.numInstances();
    }

    public int numTestInstances() {
        return this.testData.numInstances();
    }

    public void loadTrain() throws Exception {
        this.trainData = splitData(this.allData, this.trainRate, false);
    }

    public void loadTest() throws Exception {
        this.testData = splitData(this.allData, 1-this.trainRate, true);
    }

    public static Instances splitData(Instances data, double percentage, boolean invertSelection) throws Exception {
        RemovePercentage rp = new RemovePercentage();
        rp.setInvertSelection(invertSelection);
        rp.setPercentage(percentage);
        rp.setInputFormat(data);
        return Filter.useFilter(data, rp);
    }




}
