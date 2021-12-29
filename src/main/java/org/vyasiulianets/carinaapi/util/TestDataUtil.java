package org.vyasiulianets.carinaapi.util;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

public class TestDataUtil {

	public static Properties getRepositoryProperties() {
		Properties properties = new Properties();

		String repositoryName = "test_repository_" + RandomStringUtils.randomAlphabetic(5);
		properties.put("name", repositoryName);
		properties.put("description", "Description for repository " + repositoryName);
		return properties;
	}

	public static Properties getUserProjectProperties() {
		Properties properties = new Properties();

		String projectName = "test_user_project_" + RandomStringUtils.randomAlphabetic(5);
		properties.put("name", projectName);
		properties.put("body", "Project's description " + projectName);
		return properties;
	}
}
