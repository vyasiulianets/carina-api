package org.vyasiulianets.carinaapi.tests;

import org.vyasiulianets.carinaapi.service.ProjectService;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.R;

public class BaseCarinaAPITest extends AbstractTest {

	private final ProjectService projectService = new ProjectService();

	protected String getToken() {
		return R.TESTDATA.getDecrypted("access_token");
	}

	protected String getUser() {
		return R.TESTDATA.getDecrypted("api_user");
	}

	protected ProjectService getProjectService() {
		return projectService;
	}
}