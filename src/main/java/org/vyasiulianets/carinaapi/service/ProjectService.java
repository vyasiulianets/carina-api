package org.vyasiulianets.carinaapi.service;

import java.util.Properties;

import com.jayway.jsonpath.JsonPath;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;

import org.vyasiulianets.carinaapi.api.project.PostCreateUserProject;

public class ProjectService {

	public String createProject(String token, Properties properties) {
		PostCreateUserProject createUserProject = new PostCreateUserProject(token, properties);
		createUserProject.expectResponseStatus(HttpResponseStatusType.CREATED_201);
		JsonPath path = JsonPath.compile("$.id");
		return path.read(createUserProject.callAPI().getBody().asString()).toString();
	}
}
