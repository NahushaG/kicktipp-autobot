package com.kicktipp.autobot;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MatchWeekScheduler {

    private final MatchWeekOrchestrator matchWeekOrchestrator;

    public MatchWeekScheduler(MatchWeekOrchestrator matchWeekOrchestrator) {
        this.matchWeekOrchestrator = matchWeekOrchestrator;
    }

    @Scheduled(cron = "0 0 9 * * MON")
    public void prepareMatchWeekPredictions() {
        matchWeekOrchestrator.prepareCurrentMatchWeek();
    }
}
