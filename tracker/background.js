const MINIMUM_TIME_ON_PAGE_TRIGGER_MS = 5 * 1000;

chrome.tabs.onActivated.addListener(
  (result) => {
    chrome.tabs.get(
      result.tabId,
      (tab) => {
        chrome.storage.local.get(["last_tab"]).then((result) => {
          var last_tab = result.last_tab;
          if (last_tab != null) {
            if ((Date.now() - last_tab.lastAccessed) > MINIMUM_TIME_ON_PAGE_TRIGGER_MS) {
              sendTrack(last_tab.url, last_tab.lastAccessed)
            }
          }
        });

        chrome.storage.local.set({
          "last_tab": {
            url: tab.url,
            lastAccessed: tab.lastAccessed
          }
        });
      }
    )
  }
)

function formatDateToISO(date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');

  return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}+03:00`;
}

function sendTrack(url, lastAccessed) {
  var data = {
    user_id: 1,
    url: url,
    start: formatDateToISO(new Date(lastAccessed))
  };

  const ENDPOINT = "http://localhost:8080/track";

  fetch(ENDPOINT, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
    .then(responseData => {
      console.log('POST request successful:', responseData);
    })
    .catch(error => {
      console.error('Error in POST request:', error);
    });
}



