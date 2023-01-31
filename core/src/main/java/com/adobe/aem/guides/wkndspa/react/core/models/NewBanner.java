package com.adobe.aem.guides.wkndspa.react.core.models;



import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class, adapters = {
        ComponentExporter.class }, resourceType = NewBanner.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class NewBanner implements ComponentExporter {
    protected static final String RESOURCE_TYPE = "wknd-spa-react/components/newBanner";

    @ValueMapValue
    
    private String headerText;

    @ValueMapValue
    
    private String buttonText;

    
    String exportedType;

    @PostConstruct
    protected void init() {
        exportedType = NewBanner.RESOURCE_TYPE;
    }

    @Override
    public String getExportedType() {
        // TODO Auto-generated method stub
        return exportedType;
    }
public String getHeadertext(){
    return headerText;
}
public String getButtonText(){
    return buttonText;
}
    
}

