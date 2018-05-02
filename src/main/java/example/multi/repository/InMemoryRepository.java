package example.multi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
//@Service
public class InMemoryRepository implements MessageRepository{

    @Override
    public void save() {
        System.out.println("saving in memory");
    }
}