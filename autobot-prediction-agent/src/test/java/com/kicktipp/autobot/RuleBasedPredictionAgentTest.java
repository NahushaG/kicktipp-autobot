package com.kicktipp.autobot;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RuleBasedPredictionAgentTest {

  private  RuleBasedPredictionAgent agent ;

  @BeforeEach
  void setUp() {
    FootballSignalProvider signalProvider =
        new FootballSignalProvider();

    ExpectedGoalCalculator expectedGoalCalculator =
        new ExpectedGoalCalculator();

    ScorelineMapper scorelineMapper =
        new ScorelineMapper();

    PredictionConfidenceResolver confidenceResolver =
        new PredictionConfidenceResolver();

    agent = new RuleBasedPredictionAgent(
        signalProvider,
        expectedGoalCalculator,
        scorelineMapper,
        confidenceResolver
    );
  }

  @Test
  void shouldGeneratePredictionSuccessfully() {
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
  }


  private Fixture buildFixture(
      String homeTeam,
      String awayTeam
  ) {
    LocalDateTime kickOff =
        LocalDateTime.now().plusDays(2);

    LocalDateTime deadline =
        kickOff.minusMinutes(15);

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
