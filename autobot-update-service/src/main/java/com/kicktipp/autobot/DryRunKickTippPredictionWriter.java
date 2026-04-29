package com.kicktipp.autobot;

import org.springframework.stereotype.Component;

@Component
public class DryRunKickTippPredictionWriter implements KickTippPredictionWriter {

    @Override
    public void submit(Prediction prediction) {
        System.out.printf(
                "DRY RUN: would submit prediction fixture=%s score=%d:%d reason=%s%n",
                prediction.fixtureId(),
                prediction.homeScore(),
                prediction.awayScore(),
                prediction.reasoning()
        );
    }
}
