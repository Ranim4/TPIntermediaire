const favoritesBody = document.getElementById("favorites-body");
const removeButton = document.getElementById("remove-selected-favorites");
const checkboxes = document.querySelectorAll(".favorite-checkbox");
let idsToDelete = [];
const categoryList = document.getElementById("categorySelect");
const sortedList = document.getElementById("sortedBy");

//Function to load the list of favorites
function loadFavorites() {
  fetch("http://localhost:8080/api/favorites")
    .then((response) => response.json())
    .then((data) => {
      displayFavorites(data);
    })
    .catch((error) => {
      console.error("An error occurred during the request:", error);
      console.log(error);
    });
}

//Function to display the list of favorites
function displayFavorites(favorites) {
  favoritesBody.innerHTML = ""; //Clear previous table content
  favorites.forEach((favorite) => {
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

function loadCategories() {
  fetch("http://localhost:8080/api/favorites/categories")
    .then((response) => response.json())
    .then((data) => {
      displayCategories(data);
      console.log(data);
    })
    .catch((error) => {
      console.error("An error occurred during the request:", error);
      console.log(error);
    });
}

function displayCategories(categories) {
  //  favoritesBody.innerHTML = ""; //Clear previous table content
  categories.forEach((category) => {
    const option = document.createElement("option");
    option.value = category.label;
    option.textContent = category.label;
    categorySelect.appendChild(option);
  });
  // Ajouter un écouteur d'événement pour le changement de catégorie
  categorySelect.addEventListener("change", () => {
    const selectedCategory = categorySelect.value;
    console.log(selectedCategory);
    if (selectedCategory !== "all") {
      filterFavoritesByCategory(selectedCategory);
    } else {
      loadFavorites();
    }
  });
}
function filterFavoritesByCategory(selectedCategory) {
  console.log(selectedCategory);
  fetch(`http://localhost:8080/api/favorites/${selectedCategory}`)
    .then((response) => response.json())
    .then((data) => {
      displayFavorites(data);
     // console.log(data);
    })
    .catch((error) => {
      console.error("An error occurred during the request:", error);
      console.log(error);
    });
}

loadCategories();

  // Ajouter un écouteur d'événement pour le changement trier la list de favories
  sortedList.addEventListener("change", () => {
    const selectedCategory = sortedList.value;
    //console.log(selectedCategory);
    if (selectedCategory === "category") {
        sortListBy("sortedByCat");
    }
    if (selectedCategory === "date") {
        sortListBy("sortedByDate");
    }
 else {
      loadFavorites();
    }
  });

  function sortListBy(sortBy) {
    console.log(sortBy);
  fetch(`http://localhost:8080/api/favorites/${sortBy}`)
  .then((response) => response.json())
  .then((data) => {
    displayFavorites(data);
    //loadFavorites();
  })
  .catch((error) => {
    console.error("An error occurred during the request:", error);
    console.log(error);
  });
}
