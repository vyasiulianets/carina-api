package org.vyasiulianets.carinaapi.api.project;

import java.util.Properties;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PostCreateUserProject extends AbstractApiMethodV2 {

	public PostCreateUserProject(String token, Properties projectProperties) {
		super("testdata/project/_post/rq_create_user_project.json", null, projectProperties);
		String base_url = Configuration.getEnvArg("API");
		replaceUrlPlaceholder("base_url", base_url);
		setHeaders("authorization=Bearer " + token);
	}
}
