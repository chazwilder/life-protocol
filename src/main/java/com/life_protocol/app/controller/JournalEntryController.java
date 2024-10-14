package com.life_protocol.app.controller;

import com.life_protocol.app.model.JournalEntry;
import com.life_protocol.app.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/journal-entries")
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    @Autowired
    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry) {
        JournalEntry createdEntry = journalEntryService.createJournalEntry(journalEntry);
        return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable String id) {
        return journalEntryService.getJournalEntryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JournalEntry>> getJournalEntriesByUser(@PathVariable String userId) {
        List<JournalEntry> entries = journalEntryService.getJournalEntriesByUser(userId);
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<JournalEntry>> getJournalEntriesByUserAndDateRange(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<JournalEntry> entries = journalEntryService.getJournalEntriesByUserAndDateRange(userId, start, end);
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JournalEntry>> searchJournalEntries(@RequestParam String keyword) {
        List<JournalEntry> entries = journalEntryService.searchJournalEntries(keyword);
        return ResponseEntity.ok(entries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable String id, @RequestBody JournalEntry journalEntry) {
        journalEntry.setId(id);
        JournalEntry updatedEntry = journalEntryService.updateJournalEntry(journalEntry);
        return ResponseEntity.ok(updatedEntry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJournalEntry(@PathVariable String id) {
        journalEntryService.deleteJournalEntry(id);
        return ResponseEntity.noContent().build();
    }
}