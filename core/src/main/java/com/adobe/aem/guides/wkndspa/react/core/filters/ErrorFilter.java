package com.adobe.aem.guides.wkndspa.react.core.filters;



import com.adobe.granite.auth.oauth.TokenValidator;
import com.adobe.xfa.Option;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceNotFoundException;
import org.apache.sling.servlets.annotations.SlingServletFilter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.component.propertytypes.ServiceRanking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Example filter for redirecting to 404 pages
 */
@Component(service = Filter.class) // this tells AEM to treat this component as an OSGi Filter
@ServiceDescription("Error Filter") 
@SlingServletFilter(methods = {"GET"}, pattern = "/content/.*") // we check for paths below content
@ServiceRanking(1) // service ranking is for order of execution - higher the rank, higher priority in exectuion
public class ErrorFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

   

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
                         final FilterChain filterChain) throws IOException, ServletException {

        SlingHttpServletRequest req = (SlingHttpServletRequest) request;
        SlingHttpServletResponse res = (SlingHttpServletResponse) response;

        try {

            logger.info("== CHECKING IF CONTENT IS ACCESSIBLE ==");

            // if the resource requested does not exist,
            // the below line will throw a ResourceNotFoundException

            filterChain.doFilter(request, response);

        } catch (ResourceNotFoundException e) {
            
            // if filterChain.doFilter(request, response) is not called, the request stops

            // we can implement custom logic here if we want to redirect to other pages
            // e.g. custom error pages for PS/CWS/etc each, respectively

            // in this example, let us redirect to the EN 404 page if there is /en/ in path, then AR otherwise
            // we can make this better but I think this is a good example for now
            

            res.sendRedirect("/content/wknd-spa-react/us/en/errors/404.html");

        } catch (Exception e) {
            // handle all other errors
            logger.info("Error in JSONExporterRequestFilter > ", e);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
