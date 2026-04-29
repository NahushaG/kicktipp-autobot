package com.kicktipp.autobot;

public record Prediction(
        String fixtureId,
        int homeScore,
        int awayScore,
        PredictionStatus status,
        String confidence,
        String reasoning
) {
}
