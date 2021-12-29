package org.vyasiulianets.carinaapi.tests.project.column;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.vyasiulianets.carinaapi.api.project.DeleteUserProject;
import org.vyasiulianets.carinaapi.api.project.column.GetColumn;
import org.vyasiulianets.carinaapi.api.project.column.PatchColumn;
import org.vyasiulianets.carinaapi.api.project.column.PostCreateColumn;
import org.vyasiulianets.carinaapi.tests.BaseCarinaAPITest;
import org.vyasiulianets.carinaapi.util.TestDataUtil;

import com.jayway.jsonpath.JsonPath;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;

import io.restassured.response.Response;

public class ColumnTest extends BaseCarinaAPITest {

	private List<String> createdProjects = new ArrayList<>();

	@Test(description = "Create project column, Validate created column")
	public void testCreateProjectColumn() {
		String projectId = getProjectService().createProject(getToken(), TestDataUtil.getUserProjectProperties());
		createdProjects.add(projectId);
		String columnName = "test_column_" + RandomStringUtils.randomAlphabetic(5);

		PostCreateColumn createColumn = new PostCreateColumn(getToken(), columnName, projectId);
		createColumn.expectResponseStatus(HttpResponseStatusType.CREATED_201);
		createColumn.expectInResponse("name", Matchers.equalTo(columnName));

		Response postResponse = createColumn.callAPI();
		JsonPath path = JsonPath.compile("$.id");
		String columnId = path.read(postResponse.getBody().asString()).toString();

		GetColumn getColumn = new GetColumn(getToken(), columnId);
		createColumn.expectResponseStatus(HttpResponseStatusType.OK_200);
		getColumn.expectInResponse("name", Matchers.equalTo(columnName));
		Response getResponse = getColumn.callAPI();

		Assert.assertEquals(postResponse.getBody().asString(), getResponse.getBody().asString(),
				"Responses isn't equals:");
	}

	@Test(description = "Update column")
	public void testUpdateColumn() {
		String projectId = getProjectService().createProject(getToken(), TestDataUtil.getUserProjectProperties());
		createdProjects.add(projectId);
		String columnName = "test_column_" + RandomStringUtils.randomAlphabetic(5);

		PostCreateColumn createColumn = new PostCreateColumn(getToken(), columnName, projectId);
		createColumn.expectResponseStatus(HttpResponseStatusType.CREATED_201);
		Response postResponse = createColumn.callAPI();
		JsonPath path = JsonPath.compile("$.id");
		String columnId = path.read(postResponse.getBody().asString()).toString();

		String newColumnName = "test_patch_column_" + RandomStringUtils.randomAlphabetic(5);

		PatchColumn patchColumn = new PatchColumn(getToken(), columnId, newColumnName);
		patchColumn.expectResponseStatus(HttpResponseStatusType.OK_200);
		Response patchResponse = patchColumn.callAPI();

		GetColumn getColumn = new GetColumn(getToken(), columnId);
		createColumn.expectResponseStatus(HttpResponseStatusType.OK_200);
		getColumn.expectInResponse("name", Matchers.equalTo(newColumnName));
		Response getResponse = getColumn.callAPI();

		Assert.assertEquals(patchResponse.getBody().asString(), getResponse.getBody().asString(),
				"Responses isn't equals:");
	}

	@Test(description = "Delete column")
	public void testDeleteColumn() {
		String projectId = getProjectService().createProject(getToken(), TestDataUtil.getUserProjectProperties());
		createdProjects.add(projectId);
		String columnName = "test_column_" + RandomStringUtils.randomAlphabetic(5);

		PostCreateColumn createColumn = new PostCreateColumn(getToken(), columnName, projectId);
		createColumn.callAPI();

		DeleteUserProject deleteUserProject = new DeleteUserProject(getToken(), projectId);
		deleteUserProject.expectResponseStatus(HttpResponseStatusType.NO_CONTENT_204);
		deleteUserProject.callAPI();
	}

	@AfterClass
	public void deleteProject() {
		if (!createdProjects.isEmpty()) {
			for (String createdProjectId : createdProjects) {
				DeleteUserProject deleteUserProject = new DeleteUserProject(getToken(), createdProjectId);
				deleteUserProject.callAPI();
			}
		}
	}
}
