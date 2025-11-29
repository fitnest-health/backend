#!/bin/bash

# Fitnest Backend Deployment Script
# This script deploys the application with PostgreSQL using Docker Compose

set -e  # Exit on any error

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Configuration
DEPLOY_DIR="${DEPLOY_DIR:-/home/$USER/fitnest-backend}"
COMPOSE_FILE="docker-compose.development.yml"

echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}Fitnest Backend Development Deployment${NC}"
echo -e "${GREEN}========================================${NC}"
echo ""

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo -e "${RED}Error: Docker is not installed${NC}"
    echo "Please install Docker first: https://docs.docker.com/get-docker/"
    exit 1
fi

# Check if Docker Compose is installed
if ! command -v docker-compose &> /dev/null && ! docker compose version &> /dev/null; then
    echo -e "${RED}Error: Docker Compose is not installed${NC}"
    echo "Please install Docker Compose first"
    exit 1
fi

# Use 'docker compose' if available, otherwise 'docker-compose'
if docker compose version &> /dev/null; then
    DOCKER_COMPOSE="docker compose"
else
    DOCKER_COMPOSE="docker-compose"
fi

# Create deployment directory
echo -e "${YELLOW}Creating deployment directory...${NC}"
mkdir -p "$DEPLOY_DIR"
cd "$DEPLOY_DIR"

# Check if .env file exists
if [ ! -f .env ]; then
    echo -e "${RED}Error: .env file not found in $DEPLOY_DIR${NC}"
    echo "Please create .env file with required environment variables"
    exit 1
fi

# Check if docker-compose file exists
if [ ! -f "$COMPOSE_FILE" ]; then
    echo -e "${RED}Error: $COMPOSE_FILE not found in $DEPLOY_DIR${NC}"
    exit 1
fi

# Load environment variables
set -a
source .env
set +a

echo -e "${YELLOW}Stopping existing containers...${NC}"
$DOCKER_COMPOSE -f "$COMPOSE_FILE" down || true

echo -e "${YELLOW}Pulling latest images...${NC}"
$DOCKER_COMPOSE -f "$COMPOSE_FILE" pull backend || true

echo -e "${YELLOW}Starting services...${NC}"
$DOCKER_COMPOSE -f "$COMPOSE_FILE" up -d

echo -e "${YELLOW}Waiting for services to be healthy...${NC}"
sleep 10

# Check container status
echo -e "${YELLOW}Container status:${NC}"
$DOCKER_COMPOSE -f "$COMPOSE_FILE" ps

# Check if containers are running
if $DOCKER_COMPOSE -f "$COMPOSE_FILE" ps | grep -q "Up"; then
    echo ""
    echo -e "${GREEN}========================================${NC}"
    echo -e "${GREEN}Deployment successful!${NC}"
    echo -e "${GREEN}========================================${NC}"
    echo ""
    echo "Services are running:"
    echo "  - Backend: http://localhost:9898"
    echo "  - PostgreSQL: localhost:5432"
    echo ""
    echo "To view logs:"
    echo "  cd $DEPLOY_DIR"
    echo "  $DOCKER_COMPOSE -f $COMPOSE_FILE logs -f"
    echo ""
    echo "To stop services:"
    echo "  cd $DEPLOY_DIR"
    echo "  $DOCKER_COMPOSE -f $COMPOSE_FILE down"
    echo ""
else
    echo -e "${RED}Error: Some containers failed to start${NC}"
    echo "Check logs with: $DOCKER_COMPOSE -f $COMPOSE_FILE logs"
    exit 1
fi

