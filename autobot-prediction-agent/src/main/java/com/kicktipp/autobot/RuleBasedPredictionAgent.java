package com.kicktipp.autobot;

import org.springframework.stereotype.Component;

@Component
public class RuleBasedPredictionAgent implements PredictionAgent {

    @Override
    public Prediction predict(Fixture fixture) {
        String home = fixture.homeTeam().toLowerCase();
        String away = fixture.awayTeam().toLowerCase();

        if (home.contains("bayern")) {
            return new Prediction(fixture.id(), 3, 1, PredictionStatus.GENERATED, "HIGH",
                    "Home team considered stronger by rule engine.");
        }

        if (away.contains("bayern")) {
            return new Prediction(fixture.id(), 1, 3, PredictionStatus.GENERATED, "HIGH",
                    "Away team considered stronger by rule engine.");
        }

        return new Prediction(fixture.id(), 1, 1, PredictionStatus.GENERATED, "MEDIUM",
                "Balanced match fallback prediction.");
    }
}
