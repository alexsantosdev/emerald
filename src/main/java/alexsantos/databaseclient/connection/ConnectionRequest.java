package alexsantos.databaseclient.connection;

import com.fasterxml.jackson.annotation.JsonProperty;

import alexsantos.databaseclient.connection.objects.ConnectionObject;
import alexsantos.databaseclient.connection.objects.OptionalsObject;

public record ConnectionRequest(
    @JsonProperty("server_type") String server,
    @JsonProperty("connection") ConnectionObject connection,
    @JsonProperty("optionals") OptionalsObject optionals,
    @JsonProperty("hide_system_schema") Boolean hideSchema,
    @JsonProperty("connect") Boolean connect
) { }
