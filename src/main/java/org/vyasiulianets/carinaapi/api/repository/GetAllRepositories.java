package org.vyasiulianets.carinaapi.api.repository;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class GetAllRepositories extends AbstractApiMethodV2 {

	public GetAllRepositories(String token, String user) {
		super(null, null);
		String base_url = Configuration.getEnvArg("API");
		replaceUrlPlaceholder("base_url", base_url);
		replaceUrlPlaceholder("api_user", user);
		setHeaders("authorization=Bearer " + token);
	}
}
