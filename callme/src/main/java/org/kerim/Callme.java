package org.kerim;

public class Callme {
    public Long id;
    public String serviceName;
    public String callmePodId;
    public String time;
    public String uri;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCallmePodId() {
        return callmePodId;
    }

    public void setCallmePodId(String callmePodId) {
        this.callmePodId = callmePodId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
