package org.vyasiulianets.carinaapi.tests.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;

import org.vyasiulianets.carinaapi.tests.BaseCarinaAPITest;
import org.vyasiulianets.carinaapi.api.repository.DeleteRepository;
import org.vyasiulianets.carinaapi.api.repository.GetAllRepositories;
import org.vyasiulianets.carinaapi.api.repository.PostCreateRepository;
import org.vyasiulianets.carinaapi.util.TestDataUtil;
import io.restassured.response.Response;

public class RepositoryTest extends BaseCarinaAPITest {

	private static final List<String> CREATED_REPOS = new ArrayList<>();

	@Test(description = "Get all repositories for github account")
	public void testGetAllRepos() {
		GetAllRepositories getAllRepositories = new GetAllRepositories(getToken(), getUser());
		getAllRepositories.expectResponseStatus(HttpResponseStatusType.OK_200);
		getAllRepositories.callAPI();
		getAllRepositories.validateResponseAgainstSchema("testdata/repository/_get/get_all_repos_schema.json");
	}

	@Test(description = "Create repository")
	public void testCreateRepo() {
		Properties properties = TestDataUtil.getRepositoryProperties();
		String createdRepo = properties.get("name").toString();
		CREATED_REPOS.add(createdRepo);
		PostCreateRepository postCreateRepository = new PostCreateRepository(getToken(), properties);
		postCreateRepository.expectResponseStatus(HttpResponseStatusType.CREATED_201);
		postCreateRepository.expectInResponse("name", Matchers.equalTo(createdRepo));
		postCreateRepository.callAPI();
		postCreateRepository.validateResponseAgainstSchema("testdata/repository/_post/create_repo_schema.json");
	}

	@Test(description = "Delete repository", dependsOnMethods = "testCreateRepo", priority = 9)
	public void testDeleteRepos() {
		Properties properties = TestDataUtil.getRepositoryProperties();
		String repository = properties.get("name").toString();
		PostCreateRepository postCreateRepository = new PostCreateRepository(getToken(), properties);
		postCreateRepository.expectResponseStatus(HttpResponseStatusType.CREATED_201);
		postCreateRepository.callAPI();

		DeleteRepository deleteRepository = new DeleteRepository(getToken(), getUser(), repository);
		deleteRepository.expectResponseStatus(HttpResponseStatusType.NO_CONTENT_204);
		deleteRepository.callAPI();
	}

	@AfterClass
	public void deleteCreatedRepo() {
		GetAllRepositories getAllRepositories = new GetAllRepositories(getToken(), getUser());
		Response response = getAllRepositories.callAPI();
		for (String createdRepo: CREATED_REPOS) {
			if (response.getBody().asString().contains(createdRepo)) {
				DeleteRepository deleteRepository = new DeleteRepository(getToken(), getUser(), createdRepo);
				deleteRepository.expectResponseStatus(HttpResponseStatusType.NO_CONTENT_204);
				deleteRepository.callAPI();
			}
		}
	}
}
