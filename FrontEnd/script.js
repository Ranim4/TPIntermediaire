const favoritesBody = document.getElementById("favorites-body");
const removeButton = document.getElementById("remove-selected-favorites");
const checkboxes = document.querySelectorAll(".favorite-checkbox");
let idsToDelete = [];


//Function to load the list of favorites
function loadFavorites() {
    fetch("http://localhost:8080/api/favorites")
        .then(response => response.json())
        .then(data => {
            displayFavorites(data);
        })
        .catch(error => {
            console.error('An error occurred during the request:', error);
            console.log(error);
        });
}

//Function to display the list of favorites
function displayFavorites(favorites) {
    favoritesBody.innerHTML = ""; //Clear previous table content
    favorites.forEach(favorite => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td><input type="checkbox" class="favorite-checkbox" data-id="${favorite.id}")"></td>
            <td>${favorite.id}</td>
            <td>${favorite.category.label}</td>
            <td>${favorite.link}</td>
            <td>${favorite.update}</td>
        `;
        favoritesBody.appendChild(row);
    });
}

//Load favorites list when page loads
loadFavorites();
