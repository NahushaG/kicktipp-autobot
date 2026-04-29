package com.kicktipp.autobot;

import org.springframework.stereotype.Service;

@Service
public class MatchWeekOrchestrator {

    private final KickTippClient kickTippClient;
    private final PredictionAgent predictionAgent;
    private final KickTippPredictionWriter predictionWriter;

    public MatchWeekOrchestrator(
            KickTippClient kickTippClient,
            PredictionAgent predictionAgent,
            KickTippPredictionWriter predictionWriter
    ) {
        this.kickTippClient = kickTippClient;
        this.predictionAgent = predictionAgent;
        this.predictionWriter = predictionWriter;
    }

    public void prepareCurrentMatchWeek() {
        kickTippClient.getCurrentMatchWeekFixtures()
                .stream()
                .filter(fixture -> !kickTippClient.hasUserPrediction(fixture.id()))
                .map(predictionAgent::predict)
                .forEach(predictionWriter::submit);
    }
}
