#!/bin/bash

# Script to setup Claude Code and build Android app inside Docker container
# Usage: Run this script inside the mingc/android-build-box container

set -e

echo "=== Setting up Claude Code in Android Build Container ==="

# Update package lists
apt-get update

# Install curl if not present
apt-get install -y curl

# Install Node.js via nvm
echo "Installing Node.js..."
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.0/install.sh | bash
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"
[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"

# Install latest LTS Node.js
nvm install --lts
nvm use --lts

# Install Claude Code CLI
echo "Installing Claude Code..."
npm install -g @anthropic-ai/claude-code

# Verify installation
echo "Verifying installations..."
node --version
npm --version
claude --version

echo "=== Setup Complete ==="
echo ""
echo "Next steps:"
echo "1. cd /project"
echo "2. claude code"
echo "3. Ask Claude to run: cordova build android"
echo ""
echo "Your project files are mounted at /project"