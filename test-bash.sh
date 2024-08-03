#!/bin/bash

# Start a new tmux session
tmux new-session -d -s mysession

# Split the window into panes
tmux split-window -v
tmux split-window -h
tmux send-keys "tree Documents/Vikas/Task\ Manager" C-m

# Select the first pane and run the todo manager
tmux select-pane -t 1
tmux split-window -v
tmux select-pane -t 1
tmux split-window -h
tmux send-keys "cat Documents/Vikas/Task\ Manager/User_status.txt"
tmux select-pane -t 0
tmux send-keys "figlet -t -c -f big ...Welcome to Task Manager" C-m
# tmux send-keys "todo.sh list" C-m

# Select the second pane and run cowsay
# tmux select-pane -t 1
# tmux send-keys "cowsay 'Can you complete your tasks today?'" C-m

# Select the third pane and run a text editor or any other command
# tmux select-pane -t 2
# tmux send-keys "nano" C-m
tmux select-pane -t 3
# Attach to the session
tmux attach-session -t mysession
