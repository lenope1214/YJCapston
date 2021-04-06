@ECHO OFF
SET killport=8088
for /f "tokens=5" %%p in ('netstat -aon ^| find /i "listening" ^| find "%killport%"') do taskkill /F /PID %%p

java -jar C:/Users/Administrator/Documents/GitHub/YJCapston\capston-0.0.1-SNAPSHOT.jar

pause