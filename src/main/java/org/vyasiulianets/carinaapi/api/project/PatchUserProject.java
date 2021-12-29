package org.vyasiulianets.carinaapi.api.project;

import java.util.Properties;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PatchUserProject extends AbstractApiMethodV2 {

	public PatchUserProject(String token, String projectNumber, Properties properties) {
		super("testdata/project/_patch/rq_update_user_project.json", null, properties);
		String base_url = Configuration.getEnvArg("API");
		replaceUrlPlaceholder("base_url", base_url);
		replaceUrlPlaceholder("project_id", projectNumber);
		setHeaders("authorization=Bearer " + token);
	}
}
