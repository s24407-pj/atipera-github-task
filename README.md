# atipera-github-task

A Quarkus-based Java application to list non-fork GitHub repositories and their branches for a given user.

## Quick Start
1. **Clone & Build**:
   ```bash
   git clone https://github.com/your-username/atipera-github-task.git
   cd atipera-github-task
   mvn clean install
    ```
2. **Run**:
    ```bash
   mvn quarkus:dev
    ```



## API Endpoint
**GET** ```/api/v1/{user}/repositories```

Fetches non-fork repositories and branch info.

