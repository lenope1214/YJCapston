@echo off
for /f "tokens=5" %%A in ('netstat -ab ^| find /i 8088') do (set PID=%%A)
taskkill /f /fi "PID eq %PID%"