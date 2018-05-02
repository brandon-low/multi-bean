package example.multi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
//@Service
public class MySqlRepository implements MessageRepository{

    @Override
    public void save() {
        System.out.println("saving in mysql");
    }
}
