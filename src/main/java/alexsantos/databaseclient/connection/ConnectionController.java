package alexsantos.databaseclient.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @PostMapping(path = "/connect")
    public String connect(@RequestBody ConnectionRequest request) {
        return connectionService.connectDatabase(request);
    }

    @PostMapping(path = "/disconnect")
    public String disconnect(@RequestBody ConnectionRequest request) {
        return connectionService.disconnectDatabase(request);
    }
}
