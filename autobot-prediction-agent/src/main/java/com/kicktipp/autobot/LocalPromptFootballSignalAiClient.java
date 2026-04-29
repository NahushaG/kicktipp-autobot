package com.kicktipp.autobot;

import org.springframework.stereotype.Component;

/**
 * Local AI-client substitute used during early development.
 *
 * It keeps the codebase AI-driven through a prompt/client boundary without requiring
 * an external API key in tests or local dry-runs. Replace this class later with an
 * OpenAI-backed implementation that returns the same AiFootballSignal contract.
 */
@Component
public class LocalPromptFootballSignalAiClient implements FootballSignalAiClient {

  @Override
  public AiFootballSignal analyze(String prompt) {
    int seed = Math.abs(prompt.toLowerCase().hashCode());

    double last10FormScore = bounded(0.45 + ((seed % 35) / 100.0), 0.0, 1.0);
    double last5MomentumScore = bounded(0.40 + (((seed / 10) % 40) / 100.0), 0.0, 1.0);
    double goalsScoredAverage = round(1.0 + ((seed % 18) / 10.0));
    double goalsConcededAverage = round(0.7 + (((seed / 100) % 16) / 10.0));
    double injuryImpact = round(((seed / 1000) % 25) / 100.0);

    return new AiFootballSignal(
        round(last10FormScore),
        round(last5MomentumScore),
        goalsScoredAverage,
        goalsConcededAverage,
        injuryImpact,
        "Local prompt-based AI signal generated for development mode."
    );
  }

  private double bounded(double value, double min, double max) {
    return Math.max(min, Math.min(max, value));
  }

  private double round(double value) {
    return Math.round(value * 100.0) / 100.0;
  }
}
