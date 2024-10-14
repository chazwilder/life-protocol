package com.life_protocol.app.repository;

import com.life_protocol.app.model.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {
    List<JournalEntry> findByUserId(String userId);
    List<JournalEntry> findByUserIdAndCreatedAtBetween(String userId, LocalDateTime start, LocalDateTime end);
    List<JournalEntry> findByUserIdOrderByCreatedAtDesc(String userId);
    List<JournalEntry> findByTitleContainingIgnoreCase(String keyword);
}