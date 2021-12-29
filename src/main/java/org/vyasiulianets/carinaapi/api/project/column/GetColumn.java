package org.vyasiulianets.carinaapi.api.project.column;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class GetColumn extends AbstractApiMethodV2 {

    public GetColumn(String token, String columnId) {
        super(null, null);
        String base_url = Configuration.getEnvArg("API");
        replaceUrlPlaceholder("base_url", base_url);
        replaceUrlPlaceholder("column_id", columnId);
        setHeaders("authorization=Bearer " + token);
    }
}
