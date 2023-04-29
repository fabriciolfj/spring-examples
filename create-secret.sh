aws secretsmanager create-secret \
    --name rds/credentials \
    --secret-string file://credentials.json