package fi.capgemini.atorste.jaxrstest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

public class RequestData implements Serializable {
    private String metric;
    private Instant timestamp;
    private Number value;
    private Map<String, String> tags;

    public RequestData(String metric, Instant timestamp, Number value, Map<String, String> tags) {
        this.metric = metric;
        this.timestamp = timestamp;
        this.value = value;
        this.tags = tags;
    }

    @JsonProperty("timestamp")
    public long getTimestampMillis() {
        return timestamp.toEpochMilli();
    }

    @JsonProperty("timestamp")
    public void setTimestampMillis(long millis) {
        timestamp = Instant.ofEpochMilli(millis);
    }

    @JsonIgnore
    public Instant getTimestamp() {
        return timestamp;
    }

    @JsonIgnore
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
