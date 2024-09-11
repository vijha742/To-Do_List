#!/bin/bash
gnome-terminal --full-screen -- /bin/bash -c "tmux new-session -d -s taskManager; tmux split-window -v; tmux split-window -h; tmux send-keys \"tree Documents/Vikas/Task\ Manager\" Enter; tmux select-pane -t 1; tmux split-window -v; tmux select-pane -t 1; tmux split-window -h; tmux send-keys \"cat Documents/Vikas/Task\ Manager/User_status.txt\" Enter; tmux select-pane -t 0; tmux send-keys \"figlet -t -c -f big ...Welcome to Task Manager\" Enter;tmux select-pane -t 3; tmux attach -t taskManager; exec bash"


