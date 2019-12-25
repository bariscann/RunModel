package com.example.runmodel;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class Model {
    private Instances data;
    private RandomForest rfModel;
    private Evaluation evaluation;

    public Model(Instances data) throws Exception {
        this.data = data;
    }


    public void fitRF() throws Exception {
        this.rfModel = new RandomForest();
        this.rfModel.buildClassifier(this.data);
    }

    public void evaluateRF(Instances testData) throws Exception {
        this.evaluation = new Evaluation(this.data);
        this.evaluation.evaluateModel(this.rfModel, testData);
    }

    public String getSummary(){
        return this.evaluation.toSummaryString();
    }
}
