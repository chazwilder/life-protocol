package com.life_protocol.app.service;

import com.life_protocol.app.model.JournalEntry;
import com.life_protocol.app.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryServiceImpl implements JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;

    @Autowired
    public JournalEntryServiceImpl(JournalEntryRepository journalEntryRepository) {
        this.journalEntryRepository = journalEntryRepository;
    }

    @Override
    public JournalEntry createJournalEntry(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }

    @Override
    public Optional<JournalEntry> getJournalEntryById(String id) {
        return journalEntryRepository.findById(id);
    }

    @Override
    public List<JournalEntry> getJournalEntriesByUser(String userId) {
        return journalEntryRepository.findByUserId(userId);
    }

    @Override
    public List<JournalEntry> getJournalEntriesByUserAndDateRange(String userId, LocalDateTime start, LocalDateTime end) {
        return journalEntryRepository.findByUserIdAndCreatedAtBetween(userId, start, end);
    }

    @Override
    public List<JournalEntry> searchJournalEntries(String keyword) {
        return journalEntryRepository.findByTitleContainingIgnoreCase(keyword);
    }

    @Override
    public JournalEntry updateJournalEntry(JournalEntry journalEntry) {
        journalEntry.setUpdatedAt(LocalDateTime.now());
        return journalEntryRepository.save(journalEntry);
    }

    @Override
    public void deleteJournalEntry(String id) {
        journalEntryRepository.deleteById(id);
    }
}