package org.vyasiulianets.carinaapi.api.repository;

import java.util.Properties;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PostCreateRepository extends AbstractApiMethodV2 {

	public PostCreateRepository(String token, Properties repositoryProperties) {
		super("testdata/repository/_post/rq_create_repository.json", null);
		String base_url = Configuration.getEnvArg("API");
		replaceUrlPlaceholder("base_url", base_url);
		setHeaders("authorization=Bearer " + token);
		setProperties(repositoryProperties);
	}
}
