package org.zero.parksc.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zero.parksc.entity.GuestBook;

import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTest {
    private static final String GuestBook = null;
    @Autowired
    private GuestBookRepository guestBookRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            GuestBook guestBook = new GuestBook("Title..."+i, "Content..."+i, "user..."+(i%10));
            GuestBook insertedGuestBook = guestBookRepository.save(guestBook);
            System.out.println(insertedGuestBook);
        });
    }
}
