package org.vyasiulianets.carinaapi.api.project.column;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PatchColumn extends AbstractApiMethodV2 {

    public PatchColumn(String token, String columnId, String columnName) {
        super("testdata/project/column/_patch/rq_patch_column.json", null);
        String base_url = Configuration.getEnvArg("API");
        replaceUrlPlaceholder("base_url", base_url);
        replaceUrlPlaceholder("column_id", columnId);
        setHeaders("authorization=Bearer " + token);
        addProperty("name", columnName);
    }
}
