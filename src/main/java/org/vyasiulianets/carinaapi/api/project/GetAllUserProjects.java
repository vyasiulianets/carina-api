package org.vyasiulianets.carinaapi.api.project;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class GetAllUserProjects extends AbstractApiMethodV2 {

    public GetAllUserProjects(String token, String apiUser) {
		super(null, null);
		String base_url = Configuration.getEnvArg("API");
		replaceUrlPlaceholder("base_url", base_url);
		replaceUrlPlaceholder("api_user", apiUser);
		setHeaders("authorization=Bearer " + token);
    }
}
