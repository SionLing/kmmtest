#!/bin/bash

# x3live-framework Subtree Push Script
# Usage: ./scripts/framework-push.sh [branch]
# Default branch: main

set -e

FRAMEWORK_REPO="https://github.com/ViddioTeam/x3live-framework.git"
FRAMEWORK_PREFIX="x3live-framework"

# Handle help option
if [[ "$1" == "--help" || "$1" == "-h" ]]; then
    echo "📖 x3live-framework Subtree Push Script"
    echo ""
    echo "Usage: ./scripts/framework-push.sh [branch]"
    echo ""
    echo "Arguments:"
    echo "  branch    Branch to push to (default: main)"
    echo ""
    echo "Examples:"
    echo "  ./scripts/framework-push.sh          # Push to main branch"
    echo "  ./scripts/framework-push.sh develop  # Push to develop branch"
    echo ""
    echo "Repository: $FRAMEWORK_REPO"
    echo ""
    echo "⚠️  Note: All changes must be committed before pushing"
    exit 0
fi

BRANCH=${1:-main}

echo "📤 Pushing x3live-framework changes to branch: $BRANCH"
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
    echo "💡 Subtree must exist before pushing"
    exit 1
fi

# Check if there are uncommitted changes
if ! git diff-index --quiet HEAD --; then
    echo "❌ Error: You have uncommitted changes"
    echo "💡 Please commit your changes first"
    exit 1
fi

echo "🚀 Executing: git subtree push --prefix=$FRAMEWORK_PREFIX $FRAMEWORK_REPO $BRANCH"
git subtree push --prefix="$FRAMEWORK_PREFIX" "$FRAMEWORK_REPO" "$BRANCH"

echo ""
echo "✅ Successfully pushed x3live-framework changes!"
echo "🌐 Changes are now available at: $FRAMEWORK_REPO"