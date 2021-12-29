package org.vyasiulianets.carinaapi.tests.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.vyasiulianets.carinaapi.api.project.DeleteUserProject;
import org.vyasiulianets.carinaapi.api.project.GetAllUserProjects;
import org.vyasiulianets.carinaapi.api.project.PatchUserProject;
import org.vyasiulianets.carinaapi.api.project.PostCreateUserProject;
import org.vyasiulianets.carinaapi.tests.BaseCarinaAPITest;
import org.vyasiulianets.carinaapi.util.TestDataUtil;

import com.jayway.jsonpath.JsonPath;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;

public class ProjectsTest extends BaseCarinaAPITest {

	private List<String> createdProjects = new ArrayList<>();

	@Test(description = "Get all user's projects")
	public void testGetAllProjectsForUser() {
		GetAllUserProjects getAllUserProjects = new GetAllUserProjects(getToken(), getUser());
		getAllUserProjects.expectResponseStatus(HttpResponseStatusType.OK_200);
		getAllUserProjects.callAPI();
		getAllUserProjects.validateResponseAgainstSchema("testdata/project/_get/get_all_projects_schema.json");
	}

	@Test(description = "Create user's project")
	public void testCreateUserProject() {
		Properties projectProperties = TestDataUtil.getUserProjectProperties();
		PostCreateUserProject createUserProject = new PostCreateUserProject(getToken(), projectProperties);
		createUserProject.expectResponseStatus(HttpResponseStatusType.CREATED_201);
		createUserProject.expectInResponse("creator.login", Matchers.equalTo(getUser()));
		createUserProject.expectInResponse("name", Matchers.equalTo(projectProperties.get("name")));
		if (projectProperties.containsKey("body"))
			createUserProject.expectInResponse("body", Matchers.equalTo(projectProperties.get("body")));

		JsonPath path = JsonPath.compile("$.id");
		createdProjects.add(path.read(createUserProject.callAPI().getBody().asString()).toString());
	}

	@Test(description = "Update user's project")
	public void testUpdateUserProject() {
		Properties newProperties = TestDataUtil.getUserProjectProperties();
		String createdProjectId = getProjectService().createProject(getToken(), newProperties);
		createdProjects.add(createdProjectId);

		PatchUserProject updateUserProject = new PatchUserProject(getToken(), createdProjectId, newProperties);
		updateUserProject.expectResponseStatus(HttpResponseStatusType.OK_200);
		updateUserProject.callAPI();
	}

	@Test(description = "Delete user's project")
	public void testDeleteUserProject() {
		String projectId = getProjectService().createProject(getToken(), TestDataUtil.getUserProjectProperties());

		DeleteUserProject deleteUserProject = new DeleteUserProject(getToken(), projectId);
		deleteUserProject.expectResponseStatus(HttpResponseStatusType.NO_CONTENT_204);
		deleteUserProject.callAPI();
	}

	@AfterClass
	public void deleteProject() {
		if (!createdProjects.isEmpty()) {
			for (String createdProjectId: createdProjects) {
				DeleteUserProject deleteUserProject = new DeleteUserProject(getToken(), createdProjectId);
				deleteUserProject.callAPI();
			}
		}
	}
}
