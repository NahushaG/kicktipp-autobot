package com.kicktipp.autobot;

import org.springframework.stereotype.Component;

@Component
public class ExpectedGoalCalculator {

  public ExpectedGoals calculate(
      TeamPredictionSignal home,
      TeamPredictionSignal away
  ) {
    double homeExpectedGoals =
        home.goalsScoredAverage()
            + away.goalsConcededAverage() * 0.35
            + home.last10FormScore() * 0.40
            + home.last5MomentumScore() * 0.30
            + home.homeAdvantage()
            - home.injuryImpact();

    double awayExpectedGoals =
        away.goalsScoredAverage()
            + home.goalsConcededAverage() * 0.35
            + away.last10FormScore() * 0.40
            + away.last5MomentumScore() * 0.30
            - away.injuryImpact();

    return new ExpectedGoals(
        round(homeExpectedGoals),
        round(awayExpectedGoals)
    );
  }

  private double round(double value) {
    return Math.round(value * 100.0) / 100.0;
  }
}