package com.anushka.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.anushka.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long>{


}
