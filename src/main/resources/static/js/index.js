import {BASE_URL, GET, JSON_CONTENT_TYPE, POST} from "./consts.js";


const newGame = async () => {
  const firstPlayer = prompt("Enter name for Player One:", "Player Name");
  if (!firstPlayer) {
    return;
  }
  const secondPlayer = prompt("Enter name for Player Two:", "Player Name");
  if (!secondPlayer) {
    return;
  }

  const url = `${BASE_URL}/start`;
  const gameStartRequest = {
        firstPlayer: `${firstPlayer}`,
        secondPlayer: `${secondPlayer}`
  };

  const response = await fetch(url, {
    headers: {
      "Content-Type": JSON_CONTENT_TYPE
    },
    method: POST,
    body: JSON.stringify(gameStartRequest)
  });

  const data = await response.json();

  if (response.ok) {
    const uuid = data.uuid;
    const gameUrl = `${BASE_URL}/play?uuid=` + uuid + ``;
    window.location.href = gameUrl;

  } else {
    alert(data.detailedMessage);
  }
};

const loadGame = async () => {
  const gameId = prompt("Enter Game ID:", "Game ID");
  if (gameId) {
      const url = `${BASE_URL}/` + gameId;

      const response = await fetch(url, {
          headers: {
              "Content-Type": JSON_CONTENT_TYPE
          },
          method: GET
      });

      const data = await response.json();

      if (response.ok) {
          const gameUrl = `${BASE_URL}/play?uuid=` + gameId + ``;
          window.location.href = gameUrl;
      } else {
          alert(data.detailedMessage);
      }
  }
};

if(newGame !== null) {
    document.getElementById("new-game").addEventListener("click", newGame);
}

if(loadGame !== null) {
    document.getElementById("load-game").addEventListener("click", loadGame);
}