package com.kicktipp.autobot;

import org.springframework.stereotype.Component;

@Component
public class PredictionConfidenceResolver {

  public String resolve(
      ExpectedGoals expectedGoals,
      TeamPredictionSignal home,
      TeamPredictionSignal away
  ) {
    double difference = Math.abs(
        expectedGoals.homeExpectedGoals()
            - expectedGoals.awayExpectedGoals()
    );

    if (difference >= 1.2) {
      return "HIGH";
    }

    if (difference >= 0.5) {
      return "MEDIUM";
    }

    return "LOW";
  }
}