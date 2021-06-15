package br.com.wa.service.impl;

import br.com.wa.domain.conveter.UserConverter;
import br.com.wa.domain.user.DataBaseSequence;
import br.com.wa.domain.user.User;
import br.com.wa.http.domain.request.UserRequest;
import br.com.wa.http.domain.request.UsersRequest;
import br.com.wa.repository.UserRepository;
import br.com.wa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoOperations mongo;

    @Override
    public User save(UserRequest userRequest) {
        int id = NextSequenceService(User.SEQUENCE_NAME);
        return userRepository.save(UserConverter.convertFromRequest(userRequest, id));
    }

    @Override
    public List<User> saveAll(UsersRequest usersRequest) {

        usersRequest.getUsers().forEach(u -> {
            u.setId(NextSequenceService(User.SEQUENCE_NAME));
        });

        return userRepository.saveAll(usersRequest.getUsers());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    public int NextSequenceService(String sequence) {
        DataBaseSequence counter = mongo.findAndModify(query(where("_id").is(sequence)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DataBaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq(): 1;

    }
}
