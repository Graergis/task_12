package client;

import generate.Client;
import generate.GetClientRequest;
import generate.GetClientResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ClientWeb extends WebServiceGatewaySupport {

    public GetClientResponse getClientResponse(int i) {
        GetClientRequest request = new GetClientRequest();
        request.setId(i);

        GetClientResponse response = (GetClientResponse) getWebServiceTemplate().marshalSendAndReceive(
                request,
                new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/getClientResponse"));

        return response;
    }

    public void printResponse(GetClientResponse response) {
        Client client = response.getClient();

        System.out.println("Name client : " + client.getName() + "\r\nPhone client : " + client.getPhoneNumber() + "\r\nCar number : " + client.getCarNumber());
    }
}