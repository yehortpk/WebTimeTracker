function formatDateToISO(date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');

    return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}+03:00`;
}

var data = {
    user_id: 1,
    url: window.location.href,
    start: formatDateToISO(new Date())
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