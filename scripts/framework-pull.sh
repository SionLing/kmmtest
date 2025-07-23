#!/bin/bash

# x3live-framework Subtree Pull Script
# Usage: ./scripts/framework-pull.sh [branch]
# Default branch: main

set -e

FRAMEWORK_REPO="https://github.com/ViddioTeam/x3live-framework.git"
FRAMEWORK_PREFIX="x3live-framework"

# Handle help option
if [[ "$1" == "--help" || "$1" == "-h" ]]; then
    echo "📖 x3live-framework Subtree Pull Script"
    echo ""
    echo "Usage: ./scripts/framework-pull.sh [branch]"
    echo ""
    echo "Arguments:"
    echo "  branch    Branch to pull from (default: main)"
    echo ""
    echo "Examples:"
    echo "  ./scripts/framework-pull.sh          # Pull from main branch"
    echo "  ./scripts/framework-pull.sh develop  # Pull from develop branch"
    echo ""
    echo "Repository: $FRAMEWORK_REPO"
    exit 0
fi

BRANCH=${1:-main}

echo "🔄 Pulling x3live-framework updates from branch: $BRANCH"
echo "📍 Repository: $FRAMEWORK_REPO"
echo "📁 Prefix: $FRAMEWORK_PREFIX"
echo ""

# Check if we're in the root directory of the project
if [ ! -f "settings.gradle.kts" ]; then
    echo "❌ Error: Please run this script from the root directory of the project"
    exit 1
fi

# Check if the subtree directory exists
if [ ! -d "$FRAMEWORK_PREFIX" ]; then
    echo "❌ Error: $FRAMEWORK_PREFIX directory not found"
    echo "💡 Use 'git subtree add' first to add the subtree"
    exit 1
fi

echo "🚀 Executing: git subtree pull --prefix=$FRAMEWORK_PREFIX $FRAMEWORK_REPO $BRANCH --squash"
git subtree pull --prefix="$FRAMEWORK_PREFIX" "$FRAMEWORK_REPO" "$BRANCH" --squash

echo ""
echo "✅ Successfully pulled x3live-framework updates!"
echo "📝 Don't forget to test the build: ./gradlew build"