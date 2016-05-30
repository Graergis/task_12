package hello;

import io.spring.guides.gs_producing_web_service.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client findCountry(Integer name) {
        List<Client> jdbc = jdbcTemplate.query("SELECT * FROM client\n" +
                        "LEFT JOIN client_phone ON client.id_client = client_phone.id_client\n" +
                        "LEFT JOIN client_car ON client.id_client = client_car.id_client\n" +
                        "WHERE client.id_client = ?", new Object[]{name},
                new RowMapper<Client>() {
                    @Override
                    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
                        Client client = new Client();
                        client.setName(resultSet.getString(2));
                        client.setPhoneNumber(resultSet.getString(4));
                        client.setCarNumber(resultSet.getString(8));
                        return client;
                    }
                });
        return jdbc.get(0);
    }
}