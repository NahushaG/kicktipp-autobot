package com.kicktipp.autobot;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleBasedPredictionAgentTest {

  private RuleBasedPredictionAgent agent;

  @BeforeEach
  void setUp() {
    FootballSignalPromptBuilder promptBuilder = new FootballSignalPromptBuilder();
    FootballSignalAiClient aiClient = new LocalPromptFootballSignalAiClient();
    AiFootballSignalService aiFootballSignalService = new AiFootballSignalService(
        promptBuilder,
        aiClient
    );

    FootballSignalProvider signalProvider = new FootballSignalProvider(aiFootballSignalService);

    agent = new RuleBasedPredictionAgent(
        signalProvider,
        new ExpectedGoalCalculator(),
        new ScorelineMapper(),
        new PredictionConfidenceResolver()
    );
  }

  @Test
  void shouldGeneratePredictionSuccessfullyUsingAiDrivenSignals() {
    Fixture fixture = buildFixture(
        "Bayern Munich",
        "Dortmund"
    );

    Prediction prediction = agent.predict(fixture);

    assertNotNull(prediction);
    assertEquals("fixture-1", prediction.fixtureId());
    assertEquals(PredictionStatus.GENERATED, prediction.status());
    assertTrue(prediction.homeScore() >= 0);
    assertTrue(prediction.awayScore() >= 0);
    assertNotNull(prediction.confidence());
    assertNotNull(prediction.reasoning());
    assertTrue(prediction.reasoning().contains("Expected goals"));
  }

  @Test
  void shouldBuildDifferentSignalsForDifferentTeamsFromPromptClient() {
    Fixture fixture = buildFixture(
        "Bayern Munich",
        "Dortmund"
    );

    FootballSignalPromptBuilder promptBuilder = new FootballSignalPromptBuilder();
    FootballSignalAiClient aiClient = new LocalPromptFootballSignalAiClient();
    AiFootballSignalService aiFootballSignalService = new AiFootballSignalService(
        promptBuilder,
        aiClient
    );
    FootballSignalProvider provider = new FootballSignalProvider(aiFootballSignalService);

    TeamPredictionSignal homeSignal = provider.buildSignal(
        fixture.homeTeam(),
        fixture,
        TeamSide.HOME
    );

    TeamPredictionSignal awaySignal = provider.buildSignal(
        fixture.awayTeam(),
        fixture,
        TeamSide.AWAY
    );

    assertNotEquals(homeSignal, awaySignal);
    assertTrue(homeSignal.last10FormScore() >= 0.0 && homeSignal.last10FormScore() <= 1.0);
    assertTrue(awaySignal.last5MomentumScore() >= 0.0 && awaySignal.last5MomentumScore() <= 1.0);
  }

  private Fixture buildFixture(
      String homeTeam,
      String awayTeam
  ) {
    LocalDateTime kickOff = LocalDateTime.now().plusDays(2);
    LocalDateTime deadline = kickOff.minusMinutes(15);

    return new Fixture(
        "fixture-1",
        "matchweek-1",
        homeTeam,
        awayTeam,
        kickOff,
        deadline
    );
  }
}
