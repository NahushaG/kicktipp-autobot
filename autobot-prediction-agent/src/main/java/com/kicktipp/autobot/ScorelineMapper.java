package com.kicktipp.autobot;

import org.springframework.stereotype.Component;

@Component
public class ScorelineMapper {

  public Scoreline map(ExpectedGoals expectedGoals) {
    int homeGoals = mapExpectedGoalToGoal(expectedGoals.homeExpectedGoals());
    int awayGoals = mapExpectedGoalToGoal(expectedGoals.awayExpectedGoals());

    return new Scoreline(homeGoals, awayGoals);
  }

  private int mapExpectedGoalToGoal(double expectedGoals) {
    if (expectedGoals < 0.6) return 0;
    if (expectedGoals < 1.3) return 1;
    if (expectedGoals < 2.1) return 2;
    if (expectedGoals < 3.0) return 3;
    return 4;
  }
}