package com.kicktipp.autobot;

import java.util.List;

public interface KickTippClient {
    List<Fixture> getCurrentMatchWeekFixtures();
    boolean hasUserPrediction(String fixtureId);
}
