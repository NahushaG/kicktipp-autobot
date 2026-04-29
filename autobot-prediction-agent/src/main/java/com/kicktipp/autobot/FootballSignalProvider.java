package com.kicktipp.autobot;

import org.springframework.stereotype.Service;

@Service
public class FootballSignalProvider {

  private final AiFootballSignalService aiFootballSignalService;

  public FootballSignalProvider(AiFootballSignalService aiFootballSignalService) {
    this.aiFootballSignalService = aiFootballSignalService;
  }

  public TeamPredictionSignal buildSignal(
      String teamName,
      Fixture fixture,
      TeamSide side
  ) {
    AiFootballSignal aiSignal = aiFootballSignalService.generateSignal(
        teamName,
        fixture,
        side
    );

    return new TeamPredictionSignal(
        teamName,
        side,
        aiSignal.last10FormScore(),
        aiSignal.last5MomentumScore(),
        aiSignal.goalsScoredAverage(),
        aiSignal.goalsConcededAverage(),
        aiSignal.injuryImpact(),
        side == TeamSide.HOME ? 0.25 : 0.0
    );
  }
}
