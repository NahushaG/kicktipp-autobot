package com.kicktipp.autobot;

public record TeamPredictionSignal(
    String teamName,
    TeamSide side,
    double last10FormScore,
    double last5MomentumScore,
    double goalsScoredAverage,
    double goalsConcededAverage,
    double injuryImpact,
    double homeAdvantage
) {
}