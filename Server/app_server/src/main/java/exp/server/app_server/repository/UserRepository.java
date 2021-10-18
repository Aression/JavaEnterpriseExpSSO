package exp.server.app_server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import exp.server.app_server.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
