package com.kicktipp.autobot;

public record AiFootballSignal(
    double last10FormScore,
    double last5MomentumScore,
    double goalsScoredAverage,
    double goalsConcededAverage,
    double injuryImpact,
    String summary
) {
}
