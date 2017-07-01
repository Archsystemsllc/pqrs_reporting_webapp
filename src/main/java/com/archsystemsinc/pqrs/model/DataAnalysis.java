package com.archsystemsinc.pqrs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the data_analysis database table.
 * 
 */
@Entity
@Table(name="data_analysis")
@NamedQuery(name="DataAnalysis.findAll", query="SELECT d FROM DataAnalysis d")
public class DataAnalysis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="data_analysis_description")
	private String dataAnalysisDescription;

	@Column(name="data_analysis_name")
	private String dataAnalysisName;

//	//bi-directional many-to-one association to ProviderHypothesi
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="dataAnalysis")
//	private List<ProviderHypothesis> providerHypothesis;
//
//	//bi-directional many-to-one association to SubDataAnalysi
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="dataAnalysis")
//	private List<SubDataAnalysis> subDataAnalysis;

	public DataAnalysis() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataAnalysisDescription() {
		return this.dataAnalysisDescription;
	}

	public void setDataAnalysisDescription(String dataAnalysisDescription) {
		this.dataAnalysisDescription = dataAnalysisDescription;
	}

	public String getDataAnalysisName() {
		return this.dataAnalysisName;
	}

	public void setDataAnalysisName(String dataAnalysisName) {
		this.dataAnalysisName = dataAnalysisName;
	}

//	public List<ProviderHypothesis> getProviderHypothesis() {
//		return this.providerHypothesis;
//	}
//
//	public void setProviderHypothesis(List<ProviderHypothesis> providerHypothesis) {
//		this.providerHypothesis = providerHypothesis;
//	}

//	public ProviderHypothesis addProviderHypothesis(ProviderHypothesis providerHypothesis) {
//		getProviderHypothesis().add(providerHypothesis);
//		providerHypothesis.setDataAnalysis(this);
//
//		return providerHypothesis;
//	}
//
//	public ProviderHypothesis removeProviderHypothesis(ProviderHypothesis providerHypothesis) {
//		getProviderHypothesis().remove(providerHypothesis);
//		providerHypothesis.setDataAnalysis(null);
//
//		return providerHypothesis;
//	}

//	public List<SubDataAnalysis> getSubDataAnalysis() {
//		return this.subDataAnalysis;
//	}
//
//	public void setSubDataAnalysis(List<SubDataAnalysis> subDataAnalysis) {
//		this.subDataAnalysis = subDataAnalysis;
//	}

//	public SubDataAnalysis addSubDataAnalysis(SubDataAnalysis subDataAnalysis) {
//		getSubDataAnalysis().add(subDataAnalysis);
//		subDataAnalysis.setDataAnalysis(this);
//
//		return subDataAnalysis;
//	}
//
//	public SubDataAnalysis removeSubDataAnalysis(SubDataAnalysis subDataAnalysis) {
//		getSubDataAnalysis().remove(subDataAnalysis);
//		subDataAnalysis.setDataAnalysis(null);
//
//		return subDataAnalysis;
//	}

}