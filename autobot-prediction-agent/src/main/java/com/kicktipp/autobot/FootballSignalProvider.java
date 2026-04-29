package com.kicktipp.autobot;

import org.springframework.stereotype.Service;

@Service
public class FootballSignalProvider {

  public TeamPredictionSignal buildSignal(
      String teamName,
      Fixture fixture,
      TeamSide side
  ) {
    return new TeamPredictionSignal(
        teamName,
        side,
        calculateLast10Form(teamName),
        calculateLast5Momentum(teamName),
        calculateGoalsScoredAverage(teamName),
        calculateGoalsConcededAverage(teamName),
        calculateInjuryImpact(teamName),
        side == TeamSide.HOME ? 0.25 : 0.0
    );
  }

  //TODO - Actual logic for later stage
  private double calculateLast10Form(String teamName) {
    return 0.65;
  }

  //TODO - Actual logic for later stage
  private double calculateLast5Momentum(String teamName) {
    return 0.60;
  }

  //TODO - Actual logic for later stage
  private double calculateGoalsScoredAverage(String teamName) {
    return 1.6;
  }

  //TODO - Actual logic for later stage
  private double calculateGoalsConcededAverage(String teamName) {
    return 1.2;
  }

  //TODO - Actual logic for later stage
  private double calculateInjuryImpact(String teamName) {
    return 0.10;
  }
}