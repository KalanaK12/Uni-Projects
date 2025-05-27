import temp_img from './../Pages/HomePage/Images/muesli_product.png'


const tempReviews = [
  {
    "review_id": 1,
    "c_id": 1,
    "p_id": 3,
    "review_det": "These banana's are amazing. Very sweet and fresh.",
    "review_rat": 1

  },
  {
    "review_id": 2,
    "c_id": 2,
    "p_id": 3,
    "review_det": "These banana's are NOT amazing. Very bad.",
    "review_rat": 2
  },
  {
    "review_id": 3,
    "c_id": 2,
    "p_id": 3,
    "review_det": "These banana's are NOT amazing. Very bad.",
    "review_rat": 3
  },

]


const products = [
  {
    name: "Product 1",
    price: 19.99,
    storeName: "Store A",
    rating: 4.5,
    img: temp_img,
  },
  {
    name: "Product 2",
    price: 29.99,
    storeName: "Store B",
    rating: 4.2,
    img: temp_img,

  },
  {
    name: "Product 3",
    price: 14.99,
    storeName: "Store A",
    rating: 4.8,
    img: temp_img,

  },
  {
    name: "Product 4",
    price: 39.99,
    storeName: "Store C",
    rating: 4.0,
    img: temp_img,

  },
  {
    name: "Product 5",
    price: 9.99,
    storeName: "Store B",
    rating: 4.7,
    img: temp_img,

  },
];

export {
  products,
  tempReviews,
};




