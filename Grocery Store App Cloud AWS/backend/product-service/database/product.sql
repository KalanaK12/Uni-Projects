-- Active: 1694925775430@@127.0.0.1@3306
PRAGMA foreign_keys = OFF;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS SubCategory;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS UserReview;
DROP TABLE IF EXISTS Retailer;
DROP TABLE IF EXISTS RetailerStore;
PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS Category(
                                       cat_id int NOT NULL,
                                       cat_name VARCHAR(255),
                                       PRIMARY KEY (cat_id)
);

CREATE TABLE IF NOT EXISTS SubCategory(
                                          subcat_id int NOT NULL,
                                          cat_id int NOT NULL,
                                          subcat_name VARCHAR(255),
                                          PRIMARY KEY (subcat_id),
                                          FOREIGN KEY (cat_id) REFERENCES Category(cat_id)
);

CREATE TABLE IF NOT EXISTS Product(
                                      p_id int NOT NULL,
                                      p_retailer_id int NOT NULL,
                                      p_cat_id int NOT NULL,
                                      p_subcat_id int NOT NULL,
                                      p_price double,
                                      p_org_price double DEFAULT NULL,
                                      p_name VARCHAR(255),
                                      p_desc VARCHAR(255),
                                      p_weight VARCHAR(255),
                                      p_img VARCHAR(255),
                                      PRIMARY KEY (p_id),
                                      FOREIGN KEY (p_retailer_id) REFERENCES Retailer(ret_id),
                                      FOREIGN KEY (p_cat_id) REFERENCES Category(cat_id),
                                      FOREIGN KEY (p_subcat_id) REFERENCES SubCategory(subcat_id)
);

CREATE TABLE IF NOT EXISTS UserReview(
                                         review_id int NOT NULL,
                                         c_id int,
                                         p_id int NOT NULL,
                                         review_det VARCHAR(255),
                                         review_rat double,
                                         PRIMARY KEY (review_id),
                                         FOREIGN KEY (p_id) REFERENCES Product(p_id)
);

CREATE TABLE IF NOT EXISTS Retailer(
                                       ret_id int NOT NULL,
                                       ret_name VARCHAR(255),
                                       ret_web VARCHAR(255),
                                       ret_det VARCHAR(255),
                                       ret_small_img VARCHAR(255),
                                       ret_caro_img VARCHAR(255),
                                       PRIMARY KEY (ret_id)
);

-- Create Retailers
INSERT OR IGNORE INTO "Retailer"(ret_id, ret_name, ret_web, ret_det, ret_small_img, ret_caro_img)
VALUES (1, "Woolworths", "woolworths.com.au", "The fresh food people", "path_to_img", "path_to_img");

INSERT OR IGNORE INTO "Retailer"(ret_id, ret_name, ret_web, ret_det, ret_small_img, ret_caro_img)
VALUES (2, "Coles", "coles.com.au", "Good things are happening at Coles", "path_to_img", "path_to_img");

-- Create 4 categories
INSERT OR IGNORE INTO "Category"(cat_id, cat_name)
VALUES (1, "Frozen");

INSERT OR IGNORE INTO "Category"(cat_id, cat_name)
VALUES (2, "Fruit");

INSERT OR IGNORE INTO "Category"(cat_id, cat_name)
VALUES (3, "Dairy");

INSERT OR IGNORE INTO "Category"(cat_id, cat_name)
VALUES (5, "Meat and Seafood");

INSERT OR IGNORE INTO "Category"(cat_id, cat_name)
VALUES (6, "Deli");

INSERT OR IGNORE INTO "Category"(cat_id, cat_name)
VALUES (7, "Drinks");

INSERT OR IGNORE INTO "Category"(cat_id, cat_name)
VALUES (8, "Household");

INSERT OR IGNORE INTO "Category"(cat_id, cat_name)
VALUES (9, "Bakery");

-- Create SubCategories
-- Dairy
INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (1, 3, "Milk");

INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (2, 3, "Yoghurt");

INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (3, 3, "Cheese");

INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (4, 3, "Butter");

INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (5, 3, "Flavoured Milk");

-- Bakery
INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (6, 9, "Bread");

INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (7, 9, "Cakes and Desserts");

INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (8, 9, "Breakfast Snacks");

-- Drinks
INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (9, 7, "Juice");

INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (10, 7, "Sports Drinks");

INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (11, 7, "Water");

-- Fruits
INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (12, 2, "Apples");

INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (13, 2, "Blueberries");

INSERT OR IGNORE INTO SubCategory(subcat_id, cat_id, subcat_name)
VALUES (14, 2, "Watermelon");

-- Create 10 prodcuts (5 coles, 5 woolworths)

-- Dairy
-- Woolworths
INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (1, 1, 3, 5, 3.50, "Dairy Farmers Chocolate Milk 500 mL", "Chocolate flavoured milk that is pasteurised reduced fat", '0.500', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (2, 1, 3, 5, 7.00, 8.00, "Big M Strawberry Milk 750mL", "Start the day with a Big M Strawberry Milk", '0.750', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (3, 1, 3, 4, 3.00, 5.00, "Woolworths Essentials Butter 500g", "Fresh butter made in Australia", '0.500', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (4, 1, 3, 1, 4.00, "Woolworths 2L Full Cream Milk", "Woolworths 100% fresh Aussie milk", '2.00', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (5, 1, 3, 1, 4.00, "Pura Full Cream Milk 2L", "Fresh Pura Full cream milk. 100% Australian.", '2.00', "path/to/img");

-- Coles
INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (6, 2, 3, 1, 4.50, "Coles Full Cream Milk 3L", "High quality milk fresh from the farm.", '3.00', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (7, 2, 3, 5, 3.00, "Dare Double Espresso 750mL", "Made with real Robusta & Arabica coffee and fresh milk.", '0.750', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (8, 2, 3, 2, 1.10, 1.50, "Yoplait Petit Miam Mango Yoghurt Pouch", "An ideal snack for busy kids on the go.", '0.065', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (9, 2, 3, 3, 8.00, 9.50, "Cheer Tasty Cheese Slices 500g", "Classic cheesy taste with mild flavouring. Perfect for the family.", '0.500', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (10, 2, 3, 1, 3.00, 4.50, "Pura Full Cream Milk 2L", "Rich and creamy milk containing 100% Aussie milk.", '2.00', "path/to/img");
-- Dairy End

-- Bakery
-- Coles
INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (11, 2, 9, 6, 4.40, "Coles High Fibre Wholemeal Bread", "This loaf is packed full of goodness, helping you to make nutritious breakfasts, lunches and snacks every day.Contains B Vitamins and Iron.", '0.700', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (12, 2, 9, 6, 2.40, "Coles White Bread", "This super soft white sandwich loaf is made with: Australian flour No artificial colours, flavours or preservatives No added sugar.", '0.700', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (13, 2, 9, 6, 2.00, 2.70, "Coles Soft Wholemeal Sandwich Loaf ", "Full of Goodness Bringing quality to every slice, the Wonder High Fibre & Iron Wholemeal Sliced Bread Sandwich is a great bread choice for kids and adults alike.", '0.700', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (14, 2, 9, 7, 4.50, 5.85, "Coles Bakery Custard Tarts 6 Pack", "A golden short crust pastry filled with vanilla custard and sprinkled with nutmeg. Perfect treat for any occasion.", '0.360', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (15, 2, 9, 7, 3.00, 3.75, "Coles Lemon & Raspberry Tarts 6 Pack", "Tangy raspberry and zesty lemon flavoured filling baked in shortcrust pastry. Perfect treat for any occasion.", '0.270', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (16, 2, 9, 7, 4.50, "Coles Mini Tart Selection 6 Pack", "Indulge in a selection of tangy lemon, rich dulce de leche and sweet cherry crumble tarts.", '0.270', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (17, 2, 9, 8, 2.50, "Coles Bakery Croissants 3 Pack", "Bake in the oven for a delicious golden flaky finish, cooked to perfection and ready to add your favourite filling", '0.189', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (18, 2, 9, 8, 3.30, "Coles Chocolate Mini Cupcakes 9 Pack", "Delicious mini chocolate cupcakes with rich choc icing and choc flakes. Perfet size treat for lunch boxes and entertaining.", '0.216', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (19, 2, 9, 8, 4.10, 5.50, "Coles Brioche Hamburger Buns 4 Pack", "Coles Brioche Hamburger Buns are ideal for any occasion.", '0.200', "path/to/img");

-- Woolies
INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (20, 1, 9, 6, 4.40, "Woolworths Wholemeal Soft Sandwich Bread 650g", "Wholemeal sandwich sliced bread A source of fibre that''ll keep your kids digestive systems happy", '0.650', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (21, 1, 9, 6, 4.40, "Woolworths White Soft Sandwich Bread 650g", "White sandwich sliced bread A source of fibre that''ll keep your kids digestive systems happy", '0.650', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (22, 1, 9, 6, 4.40, "Woolworths Wholemeal Sandwich Hi-fibre Bread 700g", "Discover similar products with a higher Health Star Rating", '0.700', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (23, 1, 9, 7, 5.50, "Woolworths Creamy Vanilla Slice 2 Pack", "Delicious vanilla slice for 2", '0.135', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (24, 2, 9, 7, 3.75, "Woolworths Mixed Tarts 6 Pack", "Shortcrust pastry with a zesty lemon & raspberry flavoured filling", '0.045', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (25, 1, 9, 7, 4.00, 5.50, "Woolworths Tart Selection 6 Pack", "Tangy raspberry and zesty lemon flavoured filling baked in shortcrust pastry.", '0.163', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (26, 1, 9, 8, 4.00, 5.50, "Woolworths All Butter Croissant 3 Pack", "3 All Butter Croissants prefect with your favourite filling, morning, noon and night", '0.063', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (27, 1, 9, 8, 4.00, 5.50, "Woolworths Chocolate Mini Cupcakes 6 Pack", "Delicious mini chocolate cupcakes with rich choc icing and choc flakes", '0.200', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (28, 1, 9, 8, 4.00, 5.50, "Woolworths Brioche Hamburger Buns 4 Pack", "Woolworths Brioche Hamburger Buns are ideal for any occasion", '0.200', "path/to/img");
-- Bakery End

-- Drinks
-- Coles
INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (29, 2, 7, 9, 2.80, "Coles Apple Juice 2L", "Coles 2L Apple juice. Made with no added sugar and is a good source of vitamin C", '2.00', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (30, 2, 7, 9, 5.00, "Coles Dairy Orange Juice 3L", "Coles 3L orange juice with no added sugar, artificial colours or flavours.", '2.00', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (31, 2, 7, 9, 2.80, "Coles Apple & Mango Juice 2L", "Coles 2L Apple and Mango juice", '0.200', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (32, 2, 7, 10, 3.20, 3.85, "Powerade Mountain Blast Zero Sports Drink Sipper Cap 600mL", "Powerade Zero Sugar Mountain Blast flavour sports drink is scientifically formulated to replace an essential electrolyte lost in sweat", '0.600', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (33, 2, 7, 10, 2.50, "Maximus Grape Sports Drink 1L", "Don''t just replace fluids - replenish electrolytes with Maximus - the 1L isotonic sports drink that packs a punch.", '1.00', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (34, 2, 7, 10, 2.00, 2.30, "Gatorade Sports Drink Fierce Green Apple 600mL", "Gatorade Green Apple electrolyte sports drink contains critical electrolytes to help replace what''s lost in sweat and fuel you to perform at your best", '0.600', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (35, 2, 7, 11, 6.40, "Coles Spring Water 24X600Ml", "Refreshingly Good Spring Water Cool Good Clear Natural Water Good Natural Crisp & Cool", '14.4', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (36, 2, 7, 11, 3.00, 3.85, "Powerade Active Water Sports Drink Lemon 600mL", "Sipper cap bottle, Lemon flavour, Zero sugar.", '0.600', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (37, 2, 7, 11, 0.70, 0.90, "Coles Natural Mineral Water 1.25L", "Perfectly crisp and refreshing every time. Carbonated to your liking, containing natural minerals", '1.25', "path/to/img");

-- Woolies
INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (38, 2, 7, 9, 2.60, 3.00, "Woolworths Apple Juice 2L", "Woolworths 2L Apple juice. Made with no added sugar and is a good source of vitamin C", '2.00', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (39, 2, 7, 9, 5.50, "Woolworths Dairy Orange Juice 3L", "Woolworths 3L orange juice with no added sugar, artificial colours or flavours.", '2.00', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (40, 2, 7, 9, 3.00, "Woolworths Apple & Mango Juice 2L", "Woolworths 2L Apple and Mango juice", '0.200', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (41, 2, 7, 10, 3.25, 3.85, "Maximus Mountain Blast Zero Sports Drink Sipper Cap 600mL", "Powerade Zero Sugar Mountain Blast flavour sports drink is scientifically formulated to replace an essential electrolyte lost in sweat", '0.600', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (42, 2, 7, 10, 2.00, 2.90, "Gatorade Grape Sports Drink 1L", "Don''t just replace fluids - replenish electrolytes with Maximus - the 1L isotonic sports drink that packs a punch.", '1.00', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (43, 2, 7, 10, 3.00, "Powerade Sports Drink Fierce Green Apple 600mL", "Gatorade Green Apple electrolyte sports drink contains critical electrolytes to help replace what''s lost in sweat and fuel you to perform at your best", '0.600', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (44, 2, 7, 11, 6.00, 7.50, "Woolworths Spring Water 24X600Ml", "Refreshingly Good Spring Water Cool Good Clear Natural Water Good Natural Crisp & Cool", '14.4', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (45, 2, 7, 11, 3.50, "Powerade Active Water Sports Drink Lemon 600mL", "Sipper cap bottle, Lemon flavour, Zero sugar.", '0.600', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (46, 2, 7, 11, 1.00, "Woolworths Natural Mineral Water 1.25L", "Perfectly crisp and refreshing every time. Carbonated to your liking, containing natural minerals", '1.25', "path/to/img");


--Apples
    --Coles
INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (47, 2, 2, 12, 5.90, "Royal Gala Apples", "First of the season, medium in size with a sweet flavour profile which is ideal for pies, sauces and salads.", '1', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (48, 2, 2, 12, 5.99, "Perfect Apples Prepacked", "Zany Apples Perfectly imperfect Australian fruit and veg that helps reduce food waste and tastes delicious!", '2', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (49, 2, 2, 12, 7.90, "Red Apples", "Certified organic range has been awarded Retailer of the Year at the 2022 Australian Organic Industry Awards.", '1', "path/to/img");

    --Woolies
INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (50, 1, 2, 12, 4.00, "Fresh Pink Lady Apples Punnet", "1 Apple = 1 Serve Of Fruit. One serve of fruit is equal to approximately 150g. Australian Dietary Guidelines recommend 2 serves of fruit per day.", '1', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (51, 1, 2, 12, 6.50, "Fresh Pink Lady Apples Punnet", "1 Apple = 1 Serve Of Fruit. One serve of fruit is equal to approximately 150g. Australian Dietary Guidelines recommend 2 serves of fruit per day.", '2', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (52, 1, 2, 12, 6.90, "Royal Gala Apples Punnet", "Royal Gala are juicy, crisp and highly sweet apples with bright white flesh that remains white even after being cut, making it great for cheese platters and fruit salads.", '1', "path/to/img");


--Berries
    --Coles
INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (53, 2, 2, 13, 3.00, "Fresh Blueberries Punnet", "A great breakfast cereal or ice cream topper, pancake or muffin ingredient.", '0.170', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (54, 2, 2, 13, 5.70, "Jumbo Blueberries", "Technically related to roses, there is no wonder why blueberries look, smell and taste appealing.", '0.400', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (55, 2, 2, 13, 6.00, "Blueberries Prepacked", "Blueberries have a slightly tart flavour and juicy texture, they are best stored in the fridge and washed just before eating", '0.250', "path/to/img");

    --Woolies
INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (56, 1, 2, 13, 2.80, "Fresh Blueberries", "Blueberries are sweet with a nice crunchy texture. 100% Natural!", '0.170', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (57, 1, 2, 13, 7.50, "Australian Blueberries", "Fresh Australian Blueberries, grown in Australia and Free from Pesticides", '0.550', "/backend/product-service/database/images/watermelon-27-1024x987.png");

--Watermelon
    --Coles
INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (58, 2, 2, 14, 5.50, "Watermelon Seedless Whole", "Watermelon is a great refreshing snack but also great in juices, smoothies and cocktails!", '5', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (59, 2, 2, 14, 6.50, "Watermelon Half", "Watermelon is a great refreshing snack but also great in juices, smoothies and cocktails!", '3.5', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (60, 2, 2, 14, 28.30, 36.00, "Watermelon Seedless Whole", "Seedless watermelon does not contain real watermelon seeds which are large, black and glossy but can contain smaller white infertile seed casings.", '8', "path/to/img");

    --Woolies
INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_name, p_desc, p_weight, p_img)
VALUES (61, 1, 2, 14, 4.95, "Small Watermelon Whole", "A whole watermelon can be stored at room temperature but once cut, is best stores in the refrigerator.", '3.5', "path/to/img");

INSERT OR IGNORE INTO "Product"(p_id, p_retailer_id, p_cat_id, p_subcat_id, p_price, p_org_price, p_name, p_desc, p_weight, p_img)
VALUES (62, 1, 2, 14, 7.50, 7.99, "Small Watermelon Half", "Packed with vitamins. Experience the harmonious blend of sweetness and hydration with every bite of the watermelons.", '1.5', "path/to/img");



-- Insert some user reviews
INSERT OR IGNORE INTO UserReview(review_id, c_id, p_id, review_det, review_rat)
VALUES (1, 1, 3, "These banana's are amazing. Very sweet and fresh.", 4.5);

INSERT OR IGNORE INTO UserReview(review_id, c_id, p_id, review_det, review_rat)
VALUES (2, 1, 6, "Handwash does the job! Smells nice too.", 4.2);

INSERT OR IGNORE INTO UserReview(review_id, c_id, p_id, review_det, review_rat)
VALUES (3, 1, 57, "Great blueberries, nice and fresh would never get anything else!", 5.0);

INSERT OR IGNORE INTO UserReview(review_id, c_id, p_id, review_det, review_rat)
VALUES (4, 1, 56, "Okay blueberries, would reccomend the other ones of this one. THe Aussie ones OYE!", 3.0);

INSERT OR IGNORE INTO UserReview(review_id, c_id, p_id, review_det, review_rat)
VALUES (5, 1, 35, "LEGIT the best water on the planet, always my go to, everyone should try this.", 5.0);

INSERT OR IGNORE INTO UserReview(review_id, c_id, p_id, review_det, review_rat)
VALUES (6, 1, 60, "I have never seen a seedles watermelon, this is so great I don't have to worry about my kids eating seeds.", 5.0);

INSERT OR IGNORE INTO UserReview(review_id, c_id, p_id, review_det, review_rat)
VALUES (7, 1, 61, "Okay watermelong, would reccomend the other ones of this one. THe small ones", 3.0);

INSERT OR IGNORE INTO UserReview(review_id, c_id, p_id, review_det, review_rat)
VALUES (8, 1, 62, "LEGIT the best watermelon on the planet, always my go to, everyone should try this.", 5.0);

INSERT OR IGNORE INTO UserReview(review_id, c_id, p_id, review_det, review_rat)
VALUES (9, 1, 59, "Watermelon is okay but is too pricy for the size. Maybe if it was cheaper...", 1.0);

INSERT OR IGNORE INTO UserReview(review_id, c_id, p_id, review_det, review_rat)
VALUES (10, 1, 24, "", 3.0);

INSERT OR IGNORE INTO UserReview(review_id, c_id, p_id, review_det, review_rat)
VALUES (11, 1, 25, "", 3.5);

INSERT OR IGNORE INTO UserReview(review_id, c_id, p_id, review_det, review_rat)
VALUES (12, 1, 26, "", 2.0);