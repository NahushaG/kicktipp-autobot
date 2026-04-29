# KickTipp AutoBot Architecture

## Goal

KickTipp AutoBot is a safety-net agent that prevents missed KickTipp match week predictions.

It connects to KickTipp, reads the current match week fixtures, generates fallback predictions, and updates missing predictions only when the user has not manually submitted them.

## High-Level Flow

```text
Scheduler / Manual Trigger
        ↓
MatchWeekOrchestrator
        ↓
KickTipp Connector
        ↓
Prediction Agent
        ↓
KickTipp Update Service
```

## Core Modules

```text
autobot-domain
autobot-kicktipp-connector
autobot-prediction-agent
autobot-update-service
autobot-scheduler
autobot-app
```

## MVP Execution Flow

```text
1. Scheduler or REST endpoint triggers match week preparation.
2. KickTipp connector fetches current match week fixtures.
3. System checks whether user already submitted a prediction.
4. If prediction is missing, Prediction Agent generates fallback score.
5. Update Service submits the prediction in DRY_RUN mode.
```

## Production Safety Principles

- Never overwrite manual predictions.
- Start with DRY_RUN mode.
- Keep audit logs for every generated prediction.
- Add idempotency before real submission.
- Add retries and backoff around external KickTipp calls.
- Store credentials securely.
- Add notifications before auto-submit.

## Future Architecture

```text
KickTipp
   ↓
Connector
   ↓
Match Week Service
   ↓
Prediction Agent
   ↓
Audit Store
   ↓
Update Service
   ↓
Notification Service
```

Future additions:

- PostgreSQL persistence
- AI-assisted prediction reasoning
- Email / Telegram notification
- Real KickTipp integration
- Kafka event-driven orchestration
- Dashboard and analytics
