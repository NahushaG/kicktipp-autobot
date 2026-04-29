package com.kicktipp.autobot;

import java.time.LocalDateTime;

public record Fixture(
        String id,
        String matchWeekId,
        String homeTeam,
        String awayTeam,
        LocalDateTime kickOffTime,
        LocalDateTime predictionDeadline
) {
}
