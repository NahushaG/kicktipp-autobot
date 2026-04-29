package com.kicktipp.autobot;

import org.springframework.stereotype.Service;

@Service
public class AiFootballSignalService {

  private final FootballSignalPromptBuilder promptBuilder;
  private final FootballSignalAiClient aiClient;

  public AiFootballSignalService(
      FootballSignalPromptBuilder promptBuilder,
      FootballSignalAiClient aiClient
  ) {
    this.promptBuilder = promptBuilder;
    this.aiClient = aiClient;
  }

  public AiFootballSignal generateSignal(
      String teamName,
      Fixture fixture,
      TeamSide side
  ) {
    String prompt = promptBuilder.buildPrompt(teamName, fixture, side);
    return aiClient.analyze(prompt);
  }
}
