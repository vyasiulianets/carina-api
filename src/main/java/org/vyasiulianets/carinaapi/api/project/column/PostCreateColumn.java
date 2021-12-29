package org.vyasiulianets.carinaapi.api.project.column;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PostCreateColumn extends AbstractApiMethodV2 {

    public PostCreateColumn(String token, String columnName, String projectId) {
        super("testdata/project/column/_post/rq_create_column.json", null);
        String base_url = Configuration.getEnvArg("API");
        replaceUrlPlaceholder("base_url", base_url);
        replaceUrlPlaceholder("project_id", projectId);
        setHeaders("authorization=Bearer " + token);
        addProperty("name", columnName);
    }
}
