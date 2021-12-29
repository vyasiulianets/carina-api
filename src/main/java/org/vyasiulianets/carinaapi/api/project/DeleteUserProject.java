package org.vyasiulianets.carinaapi.api.project;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class DeleteUserProject extends AbstractApiMethodV2 {

	public DeleteUserProject(String token, String projectNumber) {
		super(null, null);
		String base_url = Configuration.getEnvArg("API");
		replaceUrlPlaceholder("base_url", base_url);
		replaceUrlPlaceholder("project_id", projectNumber);
		setHeaders("authorization=Bearer " + token);
	}
}
