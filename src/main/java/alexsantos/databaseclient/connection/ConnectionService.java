package alexsantos.databaseclient.connection;

import org.springframework.stereotype.Service;

import alexsantos.databaseclient.database.Database;
import alexsantos.databaseclient.enums.DatabasePlatform;

@Service
public class ConnectionService implements ConnectionDao {

    public static Database database;

    @Override
    public String connectDatabase(ConnectionRequest request) {
        if(request.connect()) {
            database = new Database(
                    request.connection().host(),
                    request.connection().port(),
                    request.optionals().database(),
                    request.connection().user(),
                    request.connection().pass()
            );

            if(request.server().equals("postgresql")) {
                database.openConnection(DatabasePlatform.POSTGRESQL);
            }
        }

        return "conectado.";
    }

    @Override
    public String disconnectDatabase(ConnectionRequest request) {
        String returnState = null;
        try{
            if(!request.connect()) {
                if(database != null && !database.getConnection().isClosed()) {
                    database.closeConnection();

                    returnState = "desconectado.";
                }else {
                    returnState = "conexão já encerrada ou inexistente.";
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return returnState;
    }
}
