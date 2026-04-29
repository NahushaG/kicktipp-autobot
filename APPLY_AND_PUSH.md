# Apply and Push

This package already contains the fixes merged into the project.

## After extracting

Open terminal in project root and run:

```bash
git status
git add .
git commit -m "Add CI, manual trigger endpoint and architecture docs"
git push
```

## Test locally

```bash
cd autobot-app
mvn spring-boot:run
```

Then trigger:

```bash
curl -X POST http://localhost:8080/api/match-week/prepare
```
