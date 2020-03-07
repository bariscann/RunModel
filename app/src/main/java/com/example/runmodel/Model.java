package com.example.runmodel;

import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.classifiers.lazy.KStar;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class Model {
    private Instances data;
    private IBk rfModel;
    private J48 j48Model;
//    private IBk knnModel;
    private Evaluation evaluation;

    public Model(Instances data) throws Exception {
        this.data = data;
    }


    public void fitRF() throws Exception {
//        this.rfModel = new RandomForest();
        this.rfModel = new IBk(3);
        this.rfModel.buildClassifier(this.data);
    }

    public void fitJ48() throws Exception {
        this.j48Model = new J48();
        this.j48Model.buildClassifier(this.data);
    }

//    public void fitKNN() throws Exception {
//        this.knnModel = new IBk();
//        this.knnModel.buildClassifier(this.data);
//    }

    public void evaluateRF(Instances testData) throws Exception {
        this.evaluation = new Evaluation(this.data);
        this.evaluation.evaluateModel(this.rfModel, testData);
    }

    public void evaluateJ48(Instances testData) throws Exception {
        this.evaluation = new Evaluation(this.data);
        this.evaluation.evaluateModel(this.j48Model, testData);
    }

//    public void evaluateKNN(Instances testData) throws Exception {
//        this.evaluation = new Evaluation(this.data);
//        this.evaluation.evaluateModel(this.j48Model, testData);
//    }

    public String getSummary(){
        return this.evaluation.toSummaryString();
    }
}
