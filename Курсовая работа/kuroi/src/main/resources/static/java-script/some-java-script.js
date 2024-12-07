// Функция для добавления картинки в коллекцию
function addToCollection(userID, artID) {
    // Создаем запрос с использованием метода POST
    fetch(`/api/collection`, {
        method: 'POST',  // Метод POST
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'user': userID,
            'art': artID
        }).toString()
    })
        .then(response => response.text())
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error))
}
