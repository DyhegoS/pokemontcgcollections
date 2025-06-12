package com.reginasoft.pokemontcg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reginasoft.pokemontcg.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
