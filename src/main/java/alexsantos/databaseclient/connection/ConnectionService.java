package alexsantos.databaseclient.connection;

import org.springframework.stereotype.Service;

import alexsantos.databaseclient.database.Database;
import alexsantos.databaseclient.enums.DatabasePlatform;

@Service
public class ConnectionService implements ConnectionDao {

    public static Database database;

    @Override
    public String connectDatabase(ConnectionRequest req) {
        if(req.connect()) {
            database = new Database(
                    req.connection().host(),
                    req.connection().port(),
                    req.optionals().database(),
                    req.connection().user(),
                    req.connection().pass()
            );

            if(req.server().equals("postgresql")) {
                database.openConnection(DatabasePlatform.POSTGRESQL);
            }

            if(req.server().equals("oracle")) {
                database.openConnection(DatabasePlatform.ORACLE);
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
