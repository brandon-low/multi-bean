package example.multi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class SellPhone {

    @Autowired
    @Qualifier(value = "mySqlRepository")
    private MessageRepository mySqlRepository;

    @Autowired
    @Qualifier(value = "inMemoryRepository")
    private MessageRepository inMemoryRepository;

    public void sendMessage(){
        mySqlRepository.save();
        inMemoryRepository.save();
    }
}