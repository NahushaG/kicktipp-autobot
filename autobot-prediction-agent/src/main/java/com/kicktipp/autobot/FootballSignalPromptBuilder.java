package com.kicktipp.autobot;

import org.springframework.stereotype.Component;

@Component
public class FootballSignalPromptBuilder {

  public String buildPrompt(
      String teamName,
      Fixture fixture,
      TeamSide side
  ) {
    return """
        You are a football prediction analyst.

        Analyze the team performance at this point in time.

        Team: %s
        Side: %s
        Fixture: %s vs %s
        Kick off: %s

        Return a structured signal with:
        - last10FormScore: 0.0 to 1.0 based on last 10 matches
        - last5MomentumScore: 0.0 to 1.0 based on recent momentum
        - goalsScoredAverage: average goals scored per match
        - goalsConcededAverage: average goals conceded per match
        - injuryImpact: 0.0 to 1.0, where 1.0 means severe injury impact
        - summary: short explanation

        Prefer recent performance over historical reputation.
        Do not assign fixed strength only because of club name.
        """.formatted(
        teamName,
        side,
        fixture.homeTeam(),
        fixture.awayTeam(),
        fixture.kickOffTime()
    );
  }
}
