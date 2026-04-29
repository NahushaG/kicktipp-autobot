package com.kicktipp.autobot;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MatchWeekScheduler {

    private final KickTippClient kickTippClient;
    private final PredictionAgent predictionAgent;
    private final KickTippPredictionWriter predictionWriter;

    public MatchWeekScheduler(
            KickTippClient kickTippClient,
            PredictionAgent predictionAgent,
            KickTippPredictionWriter predictionWriter
    ) {
        this.kickTippClient = kickTippClient;
        this.predictionAgent = predictionAgent;
        this.predictionWriter = predictionWriter;
    }

    @Scheduled(cron = "0 0 9 * * MON")
    public void prepareMatchWeekPredictions() {
        kickTippClient.getCurrentMatchWeekFixtures()
                .stream()
                .filter(fixture -> !kickTippClient.hasUserPrediction(fixture.id()))
                .map(predictionAgent::predict)
                .forEach(predictionWriter::submit);
    }
}
