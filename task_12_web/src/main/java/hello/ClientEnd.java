package hello;

import io.spring.guides.gs_producing_web_service.GetClientRequest;
import io.spring.guides.gs_producing_web_service.GetClientResponse;
import io.spring.guides.gs_producing_web_service.GetPlaceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClientEnd {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private ClientRepository clientRepository;

    @Autowired
    public ClientEnd(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientRequest")
    @ResponsePayload
    public GetClientResponse getClient(@RequestPayload GetClientRequest request) {
        GetClientResponse response = new GetClientResponse();
        response.setClient(clientRepository.findCountry(request.getId()));

        return response;
    }

    @PayloadRoot(localPart = "", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetPlaceResponse getPlace() {
        GetPlaceResponse response = new GetPlaceResponse();

        return response;
    }
}