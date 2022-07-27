package alexsantos.databaseclient.connection.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OptionalsObject(
    String database,
    @JsonProperty("showed_database") String sDatabase,
    @JsonProperty("socket_path") String sPath,
    String timezone
) { }
