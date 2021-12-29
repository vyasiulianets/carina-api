package org.vyasiulianets.carinaapi.api.repository;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class DeleteRepository extends AbstractApiMethodV2 {

	public DeleteRepository(String token, String apiUser, String repo) {
		super(null, null);
		String base_url = Configuration.getEnvArg("API");
		replaceUrlPlaceholder("base_url", base_url);
		replaceUrlPlaceholder("api_user", apiUser);
		replaceUrlPlaceholder("repo", repo);
		setHeaders("authorization=Bearer " + token);
	}
}
