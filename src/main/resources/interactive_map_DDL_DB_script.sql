-- Author: Murugaraj Kandaswamy Date : 6/27/2017 Login -- DDL --- START
--
-- Table structure for table `role`
--
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `user`
--
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Table structure for table `user_role`
--
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_role_roleid_idx` (`role_id`),
  CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Login -- DDL --- END

/**
 * ALTER TABLE : ADDING FOREIGN KEY
 * 
ALTER TABLE provider_Hypothesis
ADD CONSTRAINT FK_YearProviderHypothesis
FOREIGN KEY (year) REFERENCES year_lookup(id);

ALTER TABLE provider_Hypothesis
ADD CONSTRAINT FK_ReportingOptionProviderHypothesis
FOREIGN KEY (reporting_option) REFERENCES reporting_type_lookup(id);

**/

--- Author: Murugaraj Kandaswamy Date : 6/27/2017  ## COMPLETE SCRIPT ---- START

--
-- Table structure for table `data_analysis`
--
CREATE TABLE `data_analysis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_analysis_name` varchar(255) DEFAULT NULL,
  `data_analysis_description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='This table is the parent Data Analysis.';

--
-- Table structure for table `sub_data_analysis`
--
CREATE TABLE `sub_data_analysis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_analysis_id` int(11) DEFAULT NULL,
  `sub_data_analysis_name` varchar(255) DEFAULT NULL,
  `sub_data_analysis_description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DataAnalysisSubDataAnalysis` (`data_analysis_id`),
  CONSTRAINT `FK_DataAnalysisSubDataAnalysis` FOREIGN KEY (`data_analysis_id`) REFERENCES `data_analysis` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='This table is the sub data analysis table.';


-- LOOK UP TABLES  ## START

--
-- Table structure for table `year_lookup`
--
CREATE TABLE `year_lookup` (
  `id` int(11) NOT NULL,
  `year_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `parameter_lookup`
--
CREATE TABLE `parameter_lookup` (
  `id` int(11) NOT NULL,
  `parameter_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `reporting_option_lookup`
--
CREATE TABLE `reporting_option_lookup` (
  `id` int(11) NOT NULL,
  `reporting_option_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `states_geojson`
--
CREATE TABLE `states_geojson` (
  `STATE_GEO_JSON_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATE_NAME` varchar(255) NOT NULL,
  `STATE_GEO_JSON_OBJECT` longblob,
  PRIMARY KEY (`STATE_GEO_JSON_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=latin1;

-- LOOK UP TABLES  ## END

--
-- Table structure for table `speciality`
--
CREATE TABLE `speciality` (
  `id` int(11) NOT NULL,
  `primary_speciality` varchar(45) DEFAULT NULL,
  `count` bigint(255) DEFAULT NULL,
  `percent` double DEFAULT NULL,
  `year_id` int(11) DEFAULT NULL,
  `reporting_option_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `speciality_index_reporting_option` (`reporting_option_id`),
  KEY `speciality_index_year` (`year_id`),
  CONSTRAINT `FK_ReportingOptionSpeciality` FOREIGN KEY (`reporting_option_id`) REFERENCES `reporting_option_lookup` (`id`),
  CONSTRAINT `FK_YearSpeciality` FOREIGN KEY (`year_id`) REFERENCES `year_lookup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `statewise_statistics`
--
CREATE TABLE `statewise_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(45) DEFAULT NULL,
  `year_id` int(11) DEFAULT NULL,
  `ep_or_gpro` int(11) DEFAULT NULL,
  `rural_urban` int(11) DEFAULT NULL,
  `yes_or_nooption` int(11) DEFAULT NULL,
  `reporting_option_id` int(11) DEFAULT NULL,
  `count` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `statewise_statistics_index_year` (`year_id`),
  KEY `statewise_statistics_index_reportingoption` (`reporting_option_id`),
  CONSTRAINT `reporting_option_fk` FOREIGN KEY (`reporting_option_id`) REFERENCES `reporting_option_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `year_fk` FOREIGN KEY (`year_id`) REFERENCES `year_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2178 DEFAULT CHARSET=latin1;

--
-- Table structure for table `template_file`
--
CREATE TABLE `template_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uploaded_filename` varchar(255) NOT NULL,
  `uploaded_filecontent` blob,
  `uploaded_filetype` varchar(255) DEFAULT NULL,
  `uploaded_description` varchar(1000) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `record_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Table structure for table `provider_Hypothesis`
--
CREATE TABLE `provider_Hypothesis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year_id` int(11) DEFAULT NULL,
  `reporting_option_id` int(11) DEFAULT NULL,
  `parameter_id` int(11) DEFAULT NULL,
  `yes_value` int(11) DEFAULT NULL,
  `no_value` int(11) DEFAULT NULL,
  `yes_count` bigint(255) DEFAULT NULL,
  `no_count` bigint(255) DEFAULT NULL,
  `yes_percent` double DEFAULT NULL,
  `no_percent` double DEFAULT NULL,
  `total_sum` bigint(255) DEFAULT NULL,
  `rp_percent` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `provider_hypothesis_reporting_option_index` (`reporting_option_id`),
  KEY `provider_hypothesis_year_lookup_index` (`year_id`),
  KEY `FK_ParameterProviderHypothesis` (`parameter_id`),
  CONSTRAINT `FK_ParameterProviderHypothesis` FOREIGN KEY (`parameter_id`) REFERENCES `parameter_lookup` (`id`),
  CONSTRAINT `FK_ReportingOptionProviderHypothesis` FOREIGN KEY (`reporting_option_id`) REFERENCES `reporting_option_lookup` (`id`),
  CONSTRAINT `FK_YearProviderHypothesis` FOREIGN KEY (`year_id`) REFERENCES `year_lookup` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--- Author: Murugaraj Kandaswamy Date : 6/27/2017  ## COMPLETE SCRIPT ---- END