package alexsantos.databaseclient.connection.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConnectionObject(
    String host,
    String port,
    @JsonProperty("username") String user,
    @JsonProperty("password") String pass
) { }
