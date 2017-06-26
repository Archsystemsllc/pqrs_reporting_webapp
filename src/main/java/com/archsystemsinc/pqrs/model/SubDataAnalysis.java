package com.archsystemsinc.pqrs.model;

import java.io.Serializable;
import javax.persistence.*;


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
	private int id;

	@Column(name="sub_data_analysis_description")
	private String subDataAnalysisDescription;

	@Column(name="sub_data_analysis_name")
	private String subDataAnalysisName;

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

	public DataAnalysis getDataAnalysis() {
		return this.dataAnalysis;
	}

	public void setDataAnalysis(DataAnalysis dataAnalysi) {
		this.dataAnalysis = dataAnalysi;
	}

}