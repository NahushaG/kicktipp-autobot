package com.kicktipp.autobot;

import org.springframework.stereotype.Component;

@Component
public class RuleBasedPredictionAgent implements PredictionAgent {

  private final FootballSignalProvider signalProvider;
  private final ExpectedGoalCalculator expectedGoalCalculator;
  private final ScorelineMapper scorelineMapper;
  private final PredictionConfidenceResolver confidenceResolver;

  public RuleBasedPredictionAgent(
      FootballSignalProvider signalProvider,
      ExpectedGoalCalculator expectedGoalCalculator,
      ScorelineMapper scorelineMapper,
      PredictionConfidenceResolver confidenceResolver
  ) {
    this.signalProvider = signalProvider;
    this.expectedGoalCalculator = expectedGoalCalculator;
    this.scorelineMapper = scorelineMapper;
    this.confidenceResolver = confidenceResolver;
  }

  @Override
  public Prediction predict(Fixture fixture) {

    TeamPredictionSignal homeSignal =
        signalProvider.buildSignal(fixture.homeTeam(), fixture, TeamSide.HOME);

    TeamPredictionSignal awaySignal =
        signalProvider.buildSignal(fixture.awayTeam(), fixture, TeamSide.AWAY);

    ExpectedGoals expectedGoals =
        expectedGoalCalculator.calculate(homeSignal, awaySignal);

    Scoreline scoreline =
        scorelineMapper.map(expectedGoals);

    String confidence =
        confidenceResolver.resolve(expectedGoals, homeSignal, awaySignal);

    String reason = buildReason(homeSignal, awaySignal, expectedGoals);

    return new Prediction(
        fixture.id(),
        scoreline.homeGoals(),
        scoreline.awayGoals(),
        PredictionStatus.GENERATED,
        confidence,
        reason
    );
  }

  private String buildReason(
      TeamPredictionSignal home,
      TeamPredictionSignal away,
      ExpectedGoals expectedGoals
  ) {
    return "Prediction generated using last 10 match form, last 5 match momentum, " +
        "attack/defence trend, home advantage, and injury impact. " +
        "Expected goals: " +
        home.teamName() + "=" + expectedGoals.homeExpectedGoals() +
        ", " +
        away.teamName() + "=" + expectedGoals.awayExpectedGoals();
  }
}