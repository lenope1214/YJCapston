package com.jumanji.capston.repository;

import com.jumanji.capston.data.Message;
import com.jumanji.capston.data.MessageId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, MessageId> {


}
