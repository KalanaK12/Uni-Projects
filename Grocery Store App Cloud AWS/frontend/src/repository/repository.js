import axios from "axios";

function getReviewStats(reviewsJSON) {
  if (reviewsJSON.length === 0) {
    return {
      totalReviews: 0,
      averageRating: 0,
      starRatings: {
        starOne: 0,
        starTwo: 0,
        starThree: 0,
        starFour: 0,
        starFive: 0,
      },
    };
  }



  const totalReviews = reviewsJSON.length;
  const totalRating = reviewsJSON.reduce(
    (sum, review) => sum + review.review_rat,
    0
  );
  const averageRating = totalRating / totalReviews;

  // Initialize an object to store star rating counts
  const starRatingsCount = {
    starOne: 0,
    starTwo: 0,
    starThree: 0,
    starFour: 0,
    starFive: 0,
  };

  // Calculate the counts for each star rating
  reviewsJSON.forEach((review) => {
    const rating = review.review_rat;
    if (rating === 1) {
      starRatingsCount.starOne++;
    }
    if (rating === 2) {
      starRatingsCount.starTwo++;
    }
    if (rating === 3) {
      starRatingsCount.starThree++;
    }
    if (rating === 4) {
      starRatingsCount.starFour++;
    }
    if (rating === 5) {
      starRatingsCount.starFive++;
    }
  });

  const percentageRatings = {
    starOne_p: (starRatingsCount.starOne / totalReviews) * 100,
    starTwo_p: (starRatingsCount.starTwo / totalReviews) * 100,
    starThree_p: (starRatingsCount.starThree / totalReviews) * 100,
    starFour_p: (starRatingsCount.starFour / totalReviews) * 100,
    starFive_p: (starRatingsCount.starFive / totalReviews) * 100,
  };

  return {
    totalReviews: totalReviews,
    averageRating: averageRating,
    starRatings: percentageRatings,
  };
}
function formatPrice(price) {
  price = parseFloat(price);
  return price.toFixed(2);
}

function fetchProducts(searchTerm, setData) {
  fetch("http://localhost:8080/product/search/" + searchTerm)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((responseData) => {
      setData(responseData);
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

async function fetchSimilarProducts(id, sub_cat) {
  const json = {
    p_id: id,
    p_sub_cat: sub_cat,
  };

  const response = await axios.post(
    "http://localhost:8080/product/similarproduct",
    json
  );

  return response.data;
}
async function fetchReviews(p_id) {
  const response = await axios.get(
    "http://localhost:8080/product/review/" + p_id
  );

  return response.data;
}

async function fetchRetailer(p_id) {
  const response = await axios.get(
    "http://localhost:8080/product/retailer/" + p_id
  );

  return response.data;
}

function getDefaultFilters(searchTerm) {
  const jsonFilter = {
    searchTerm: searchTerm,
    categoryFilter: null,
    retailerFilter: null,
    discountFilter: false,
    sortingFilter: "Price low-high",
  };

  return jsonFilter;
}

function getFiltersJSON(item, category, retailer, discount, sorting) {
  let sortingFormatted;

  switch (sorting) {
    case "Price Low to High":
      sortingFormatted = "Price low-high";
      break;
    case "Price High to Low":
      sortingFormatted = "Price high-low";
      break;
    case "Name (A-Z)":
      sortingFormatted = "A-Z";
      break;
    case "Name (Z-A)":
      sortingFormatted = "Z-A";
      break;
    case "Unit Price Low to High":
      sortingFormatted = "Unit Price low-high";
      break;
    case "Unit Price High to Low":
      sortingFormatted = "Unit Price high-low";
      break;
    default:
      sortingFormatted = sorting;
  }

  // if(category==="All"){
  //   categoryFormatted= null;
  // }else{
  //   categoryFormatted = category;
  // }

  return {
    searchTerm: item,
    categoryFilter: category,
    retailerFilter: retailer,
    discountFilter: discount,
    sortingFilter: sortingFormatted,
  };
  // return {
  //     "searchTerm": "milk",
  //     "categoryFilter": ["Dairy"],
  //     "retailerFilter": ["Woolworths"],
  //     "discountFilter": false,
  //     "sortingFilter": "A-Z"
  //   }
}

function fetchSearchSuggestions(searchTerm, setResults) {
  fetch("http://localhost:8080/product/livesearch?searchInput=" + searchTerm)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((responseData) => {
      setResults(responseData);
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

function fetchProductsByFilters(filterJSON, setData) {
  fetch("http://localhost:8080/product/search", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(filterJSON),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((responseData) => {
      setData(responseData);
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

function getSortOptions() {
  const sortOptions = [
    "Price Low to High",
    "Price High to Low",
    "Name (A-Z)",
    "Name (Z-A)",
    "Unit Price Low to High",
    "Unit Price High to Low",
  ];

  return sortOptions;
}

 function getDeliveryTimeSlots() {
    
    const timeSlots = [
        '8:00 AM - 9:00 AM',
        '9:00 AM - 10:00 AM',
        '10:00 AM - 11:00 AM',
        '11:00 AM - 12:00 PM',
        '12:00 PM - 1:00 PM',
        '1:00 PM - 2:00 PM',
        '2:00 PM - 3:00 PM',
        '3:00 PM - 4:00 PM',
        '4:00 PM - 5:00 PM',
        '5:00 PM - 6:00 PM',
        '6:00 PM - 7:00 PM',
        '7:00 PM - 8:00 PM',
        '8:00 PM - 9:00 PM',
        '9:00 PM - 10:00 PM',
        '10:00 PM - 11:00 PM',
      ];

    return timeSlots;
  }

function getCategoryOptions() {
  const categories = ["Groceries", "Fruit and Veg", "Dairy", "Bakery"];

  return categories;
}

function getRetailerOptions() {
  const retailer = ["Woolworths", "Coles"];

  return retailer;
}

async function fetchAllCategories() {
  const apiURL = "http://localhost:8080/product/category";
  try {
    const response = await axios.get(apiURL);
    return response;
  } catch (error) {
    return null;
  }
}

async function fetchDiscountProducts() {
  const apiURL = "http://localhost:8080/product/discount";
  try {
    const response = await axios.get(apiURL);
    return response;
  } catch (error) {
    return null;
  }
}

async function registerUser(data) {
  const apiURL = "http://localhost:8080/customer/register";

  try {
    const response = await axios.post(apiURL, data);
    return response;
  } catch (error) {
    console.error("Error:", error);
  }
}

async function loginUser(data) {
  const apiURL = "http://localhost:8080/customer/login";

  const response = await axios.post(apiURL, data);
  return response;
}

function addUserDataToLocalStorage(token, username,id) {
  localStorage.setItem("token", token);
  localStorage.setItem("username", username);
  localStorage.setItem("c_id", id);
}

async function fetchCart() {
  const apiURL = "http://localhost:8080/order/cart";

  const c_id = Number(localStorage.getItem("c_id"));

  const payload = {
    cID: c_id,
    };

  const headers = {
    'Authorisation': `Bearer ${localStorage.getItem("token")}`,
  };

  let user, products;


  await axios.post(apiURL, payload, { headers })
  .then(response => {
    [user, ...products] = response.data;
    
  })
  .catch(error => {
    console.error('Error fetching cart:', error);
  });

  return {user,products};

}

async function postItemToCart(product,quantity) {
  const apiURL = "http://localhost:8080/order/addItem";

  const c_id = Number(localStorage.getItem("c_id"));

  //get total cost
  const totalCost = product.p_price * quantity;

  const payload = {
    cID: c_id,
    pID: product.p_id,
    quantity: quantity,
    productCost: totalCost,
  };

  const headers = {
    'Authorisation': `Bearer ${localStorage.getItem("token")}`,
  };

  let responseFinal = null; 

  await axios.post(apiURL, payload, { headers })
  .then(response => {
    responseFinal = response.data;
  })
  .catch(error => {
    console.error('Error fetching cart:', error);
  });

  return responseFinal;

}
async function deleteItemFromCart(p_id){
  const apiURL = "http://localhost:8080/order/deleteItem";

  const c_id = Number(localStorage.getItem("c_id"));

  const payload = {
    cID: c_id,
    pID: p_id,
  };

  const headers = {
    'Authorisation': `Bearer ${localStorage.getItem("token")}`,
  };

  let responseFinal = null; 

  axios.delete(apiURL, {
    headers: headers,
    data: payload,
  })
    .then(response => {
      // Handle the success response
      console.log('Delete request successful', response);
      responseFinal = response;
    })
    .catch(error => {
      // Handle any errors that occur during the request
      console.error('Error deleting data:', error);
    });

  return responseFinal;

};


export {
  formatPrice,
  fetchProducts,
  getDefaultFilters,
  getFiltersJSON,
  fetchProductsByFilters,
  getSortOptions,
  getCategoryOptions,
  getRetailerOptions,
  fetchSearchSuggestions,
  fetchSimilarProducts,
  getReviewStats,
  fetchReviews,
  fetchRetailer,
  fetchAllCategories,
  fetchDiscountProducts,
  registerUser,
  loginUser,
  addUserDataToLocalStorage,  
  getDeliveryTimeSlots,
  fetchCart,
  postItemToCart,
  deleteItemFromCart,
};