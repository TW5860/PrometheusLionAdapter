package webservice_live;

public class WebserviceRegistrationInfo {
    private String name;
    private String namespace;
    private String requestName;
    private String url;

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
}
