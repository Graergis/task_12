package hello;

import io.spring.guides.gs_producing_web_service.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlaceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Place findCountry(Integer name) {
        List<Place> jdbc = jdbcTemplate.query("SELECT * FROM place \n" +
                        "LEFT JOIN (\n" +
                        "SELECT id_place, max(end_date) as d FROM parking\n" +
                        "GROUP BY id_place ) a on place.id = a.id_place\n" +
                        "WHERE a.d < now() or a.d is null", new Object[]{name},
                new RowMapper<Place>() {
                    @Override
                    public Place mapRow(ResultSet resultSet, int i) throws SQLException {
                        Place place = new Place();
                        place.setIdPlace(resultSet.getInt(2));
                        return place;
                    }
                });
        return jdbc.get(0);
    }
}
