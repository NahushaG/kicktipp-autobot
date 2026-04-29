package com.kicktipp.autobot.controller;

import com.kicktipp.autobot.MatchWeekOrchestrator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchWeekController {

    private final MatchWeekOrchestrator matchWeekOrchestrator;

    public MatchWeekController(MatchWeekOrchestrator matchWeekOrchestrator) {
        this.matchWeekOrchestrator = matchWeekOrchestrator;
    }

    @PostMapping("/api/match-week/prepare")
    public ResponseEntity<String> prepareMatchWeek() {
        matchWeekOrchestrator.prepareCurrentMatchWeek();
        return ResponseEntity.ok("Match week prediction preparation triggered in dry-run mode.");
    }
}
