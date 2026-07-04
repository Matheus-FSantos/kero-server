package github.io.matheusfsantos.kr_server.user.adapters.out.repository;

import github.io.matheusfsantos.kr_server.user.adapters.out.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> { }
