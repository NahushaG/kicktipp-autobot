package com.kicktipp.autobot;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class FakeKickTippClient implements KickTippClient {

    @Override
    public List<Fixture> getCurrentMatchWeekFixtures() {
        return List.of(
                new Fixture("1", "MW-01", "Bayern Munich", "Mainz", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(4)),
                new Fixture("2", "MW-01", "Dortmund", "Leipzig", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(4))
        );
    }

    @Override
    public boolean hasUserPrediction(String fixtureId) {
        return false;
    }
}
