# Git Subtree Management Scripts

This document describes the convenient scripts for managing the x3live-framework git subtree.

## Scripts Overview

### 🔄 framework-pull.sh - Pull Updates from Framework Repository

**Location**: `scripts/framework-pull.sh`

**Purpose**: Pulls the latest changes from the x3live-framework repository into your main project.

**Usage**:
```bash
# Pull from main branch (default)
./scripts/framework-pull.sh

# Pull from specific branch
./scripts/framework-pull.sh develop

# Show help
./scripts/framework-pull.sh --help
```

**What it does**:
1. Validates you're in the correct directory
2. Checks if the subtree exists
3. Executes `git subtree pull` with proper parameters
4. Provides clear status messages

### 📤 framework-push.sh - Push Changes to Framework Repository

**Location**: `scripts/framework-push.sh`

**Purpose**: Pushes changes made to the x3live-framework directory back to the framework repository.

**Usage**:
```bash
# Push to main branch (default)
./scripts/framework-push.sh

# Push to specific branch
./scripts/framework-push.sh develop

# Show help
./scripts/framework-push.sh --help
```

**What it does**:
1. Validates you're in the correct directory
2. Checks if the subtree exists
3. Verifies all changes are committed
4. Executes `git subtree push` with proper parameters
5. Provides clear status messages

## Repository Configuration

- **Framework Repository**: https://github.com/ViddioTeam/x3live-framework.git
- **Subtree Prefix**: `x3live-framework/`
- **Default Branch**: `main`

## Common Workflows

### 1. Update Framework in Your Project
```bash
# Get latest framework updates
./scripts/framework-pull.sh

# Test the build
./gradlew build

# Commit the updates to your main project
git add .
git commit -m "update: x3live-framework to latest version"
git push
```

### 2. Contribute Changes Back to Framework
```bash
# Make changes in x3live-framework/ directory
# Edit files in x3live-framework/src/main/java/com/x3live/core/

# Commit changes to your main project first
git add .
git commit -m "feat: enhance navigation throttling in framework"

# Push changes back to framework repository
./scripts/framework-push.sh

# The framework repository now has your changes
```

### 3. Working with Different Branches
```bash
# Pull from development branch
./scripts/framework-pull.sh develop

# Push to development branch
./scripts/framework-push.sh develop
```

## Error Handling

### Common Issues and Solutions

**"Please run this script from the root directory"**
- Solution: Make sure you're in `/Users/sion/projects/autodesign/`

**"x3live-framework directory not found"**
- Solution: The subtree was not properly added. Re-run the subtree add command.

**"You have uncommitted changes" (push script)**
- Solution: Commit all your changes first with `git add . && git commit -m "your message"`

**"Cannot push to remote repository" (push script)**
- Solution: Check your GitHub permissions for the ViddioTeam/x3live-framework repository

## Manual Commands (If Scripts Don't Work)

### Pull Updates Manually
```bash
git subtree pull --prefix=x3live-framework https://github.com/ViddioTeam/x3live-framework.git main --squash
```

### Push Changes Manually
```bash
git subtree push --prefix=x3live-framework https://github.com/ViddioTeam/x3live-framework.git main
```

### Add Subtree Initially (One-time Setup)
```bash
git subtree add --prefix=x3live-framework https://github.com/ViddioTeam/x3live-framework.git main --squash
```

## Script Features

✅ **Error checking**: Validates environment before executing  
✅ **Clear messaging**: Provides status updates throughout execution  
✅ **Help documentation**: Use `--help` flag for usage information  
✅ **Branch support**: Work with any branch in the framework repository  
✅ **Safety checks**: Prevents common mistakes like uncommitted changes  

## Advantages of This Approach

- **Simple commands**: Easy-to-remember script names
- **Error prevention**: Scripts catch common mistakes
- **Consistency**: Always uses the correct parameters
- **Documentation**: Built-in help and clear messaging
- **Flexibility**: Support for different branches when needed

Use these scripts to keep your framework up-to-date and contribute improvements back to the shared repository!