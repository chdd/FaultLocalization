package com.ncfxy.FaultLocalization.methods;

import java.util.ArrayList;
import java.util.List;

import com.ncfxy.FaultLocalization.Experiment;

public abstract class StatisticsBase implements FaultLocalizationMethod {
	int totalFailed;
	int totalPassed;
	List<Integer> failed = new ArrayList<Integer>();
	List<Integer> passed = new ArrayList<Integer>();
	List<Double> result = new ArrayList<Double>();
	
	protected void init(Experiment experiment) {
		totalFailed = experiment.getFailCoverageMatrix().size();
		totalPassed = experiment.getPassCoverageMatrix().size();
		for (int i = 0; i < experiment.getNumberOfExecutableEntities(); i++) {
			failed.add(new Integer(0));
			passed.add(new Integer(0));
		}
		for (int i = 0; i < experiment.getFailCoverageMatrix().size(); i++) {
			List<Byte> list = experiment.getFailCoverageMatrix().get(i);
			for (int j = 0; j < list.size(); j++) {
				failed.set(j, failed.get(j) + list.get(j));
			}
		}
		for (int i = 0; i < experiment.getPassCoverageMatrix().size(); i++) {
			List<Byte> list = experiment.getPassCoverageMatrix().get(i);
			for (int j = 0; j < list.size(); j++) {
				passed.set(j, passed.get(j) + list.get(j));
			}
		}
	}

	public abstract List<Double> getSuspicious(Experiment experiment);

}