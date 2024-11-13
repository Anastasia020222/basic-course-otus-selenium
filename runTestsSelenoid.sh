#/bin/bash

URL=""
BROWSER=""
VERSION_BROWSER=""
REMOTE=true;

  while [[ $# -gt 0 ]]
  do
    case $1 in
    --url)
      if [[ -z "$2" || "$2" == --* ]]; then
        echo "Error: после --url должен следовать url стенда"
        exit 1
      else
        URL="$2"
        fi
        shift ;;

    --browser)
      if [[ -z "$2" || "$2" == --* ]]; then
        echo "Error: после --browser должно следовать имя браузера"
        exit 1
      else
        BROWSER="$2"
        fi
        shift ;;

    --version_browser)
      if [[ -z "$2" || "$2" == --* ]]; then
        echo "Error: после --version_browser должна следовать версия браузера"
        exit 1
      else
        VERSION_BROWSER="$2"
        fi
        shift ;;

    *) echo "Error: параметр $1 не найден"
      esac
      shift
    done

  if [[ -z "$URL" || -z "$BROWSER" || -z "$VERSION_BROWSER" ]]; then
		echo "Один из обязательных параметров не задан. Обязательные параметры: --url --browser --version_browser"
	exit 1
	fi

  mvn clean test -Dremote.url=$URL -Dbrowser=$BROWSER -DversionBrowser=$VERSION_BROWSER -Dremote=$REMOTE