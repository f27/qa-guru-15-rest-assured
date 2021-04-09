package api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Register {
    @JsonProperty("id")
    private String id;

    @JsonProperty("token")
    private String token;

    @JsonProperty("error")
    private String error;

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getError() {
        return error;
    }
}
