package com.archsystemsinc.pqrs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sub_data_analysis database table.
 * 
 */
@Entity
@Table(name="sub_data_analysis")
@NamedQuery(name="SubDataAnalysis.findAll", query="SELECT s FROM SubDataAnalysis s")
public class SubDataAnalysis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="sub_data_analysis_description")
	private String subDataAnalysisDescription;

	@Column(name="sub_data_analysis_name")
	private String subDataAnalysisName;

	//bi-directional many-to-one association to ProviderHypothesi
	@OneToMany(mappedBy="subDataAnalysis")
	private List<ProviderHypothesis> providerHypothesis;

	//bi-directional many-to-one association to DataAnalysi
	@ManyToOne
	@JoinColumn(name="data_analysis_id")
	private DataAnalysis dataAnalysis;

	public SubDataAnalysis() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubDataAnalysisDescription() {
		return this.subDataAnalysisDescription;
	}

	public void setSubDataAnalysisDescription(String subDataAnalysisDescription) {
		this.subDataAnalysisDescription = subDataAnalysisDescription;
	}

	public String getSubDataAnalysisName() {
		return this.subDataAnalysisName;
	}

	public void setSubDataAnalysisName(String subDataAnalysisName) {
		this.subDataAnalysisName = subDataAnalysisName;
	}

	public List<ProviderHypothesis> getProviderHypothesis() {
		return this.providerHypothesis;
	}

	public void setProviderHypothesis(List<ProviderHypothesis> providerHypothesis) {
		this.providerHypothesis = providerHypothesis;
	}

	public ProviderHypothesis addProviderHypothesis(ProviderHypothesis providerHypothesis) {
		getProviderHypothesis().add(providerHypothesis);
		providerHypothesis.setSubDataAnalysis(this);

		return providerHypothesis;
	}

	public ProviderHypothesis removeProviderHypothesis(ProviderHypothesis providerHypothesis) {
		getProviderHypothesis().remove(providerHypothesis);
		providerHypothesis.setSubDataAnalysis(null);

		return providerHypothesis;
	}

	public DataAnalysis getDataAnalysis() {
		return this.dataAnalysis;
	}

	public void setDataAnalysis(DataAnalysis dataAnalysis) {
		this.dataAnalysis = dataAnalysis;
	}

}