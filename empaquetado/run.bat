set PASAPPTS_HOME=%cd%
set JRE_PATH=%PASAPPTS_HOME%/jdk-11.0.6

"%JRE_PATH%/bin/java" -cp "%PASAPPTS_HOME%/PasaPPTs.jar" -Dpasappts.config.propertiesfile=%PASAPPTS_HOME%/config.properties com.pasappts.Main