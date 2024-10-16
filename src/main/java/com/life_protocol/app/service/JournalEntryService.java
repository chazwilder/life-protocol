package com.life_protocol.app.service;

import com.life_protocol.app.model.JournalEntry;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JournalEntryService {
    JournalEntry createJournalEntry(JournalEntry journalEntry);
    Optional<JournalEntry> getJournalEntryById(String id);
    List<JournalEntry> getJournalEntriesByUser(String userId);
    List<JournalEntry> getJournalEntriesByUserAndDateRange(String userId, LocalDateTime start, LocalDateTime end);
    List<JournalEntry> searchJournalEntries(String keyword);
    JournalEntry updateJournalEntry(JournalEntry journalEntry);
    void deleteJournalEntry(String id);
}