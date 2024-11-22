package org.kerim;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
public class TraceIdFilter implements ContainerResponseFilter {

    private static final Logger log = Logger.getLogger(TraceIdFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        SpanContext spanContext = Span.current().getSpanContext();
        if (spanContext.isValid()) {
            String traceId = spanContext.getTraceId();
            log.info("TraceId: " + traceId);
            containerResponseContext.getHeaders().putSingle("X-Trace-Id", traceId);
        }
    }
}
