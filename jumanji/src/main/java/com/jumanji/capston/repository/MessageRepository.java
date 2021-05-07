package com.jumanji.capston.repository;

import com.jumanji.capston.data.Message;
//import com.jumanji.capston.data.MessageId;
import com.jumanji.capston.data.Publish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Publish> {


}
