package alexsantos.databaseclient.connection;

public interface ConnectionDao {
    String connectDatabase(ConnectionRequest request);
    String disconnectDatabase(ConnectionRequest request);
}
