package api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Login {
    @JsonProperty("token")
    private String token;

    @JsonProperty("error")
    private String error;

    public String getToken() {
        return token;
    }

    public String getError() {
        return error;
    }
}
