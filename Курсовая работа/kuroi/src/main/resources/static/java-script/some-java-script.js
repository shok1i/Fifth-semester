const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

function toggleCollection(action, userID, artID) {
    const isAdding = action === 'POST';
    fetch(`/api/collection`, {
        method: action,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-CSRF-TOKEN': csrfToken
        },
        body: new URLSearchParams({
            'user': userID,
            'art': artID
        }).toString()
    })
        .then(response => response.text())
        .then(data => {
            console.log(data);
            const button = document.getElementById('btn_collection');
            button.textContent = isAdding ? "Удалить штуку с полки" : "Добавить штуку на полку";
            button.setAttribute('onclick', `toggleCollection('${isAdding ? 'DELETE' : 'POST'}', ${userID}, ${artID})`);
        })
        .catch(error => console.error('Error:', error));
}

function toggleLike(action, userID, artID) {
    const isAdding = action === 'POST';
    fetch(`/api/like`, {
        method: action,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-CSRF-TOKEN': csrfToken
        },
        body: new URLSearchParams({
            'user': userID,
            'art': artID
        }).toString()
    })
        .then(response => response.text())
        .then(data => {
            console.log(data);
            const card = document.getElementById('CARD' + artID)

            const like = card.querySelector('#likesDiv');
            like.classList.toggle('liked', isAdding);
            like.setAttribute('onclick', `toggleLike('${isAdding ? 'DELETE' : 'POST'}', ${userID}, ${artID})`);

            const likesCount = card.querySelector('#likesCount');
            const value = parseInt(likesCount.textContent);
            likesCount.textContent = isAdding ? value + 1 : value - 1;
        })
        .catch(error => console.error('Error:', error));
}